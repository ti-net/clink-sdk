//
//  TOSClientKit.m
//  TOSClientKit
//
//  Created by YanBo on 2020/5/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "TOSKit.h"
#import "TIMConstants.h"
#import "NSDate+TimeFormatting.h"
#import "TOSCustomerChatVC.h"

//#import "TIMRTCMediaViewController.h"
#import "YYKit.h"
#import "kitUtils.h"
#import "ICMediaManager.h"
#import <TOSClientLib/TOSClientLib.h>
#import "NSObject+TIMShowError.h"
#import <TOSClientLib/NSObject+TIMModel.h>
#import <TOSClientLib/OctoIMParamDefines.h>
#import "ICMessageHelper.h"
#import "ICMessageConst.h"
#import <TOSClientLib/TOSMessageSmallProgramModel.h>
#import <TOSClientLib/TIMLogisticsCardMessage.h>
#import <TOSClientLib/TIMCommodityCardMessage.h>
#import <TOSClientLib/CombinationMessage.h>
#import <TOSClientLib/TOSMessageTextTagModel.h>
#import <TOSClientKit.h>

#ifdef NSFoundationVersionNumber_iOS_9_x_Max
#import <UserNotifications/UserNotifications.h>
#endif

typedef void(^SendMsgProgressCallBack)(float progress);
typedef void(^SendMsgSuccessCallBack)(TOSMessage * timMessage);
typedef void(^SendMsgErrorCallBack)(TOSMessage * message,TIMConnectErrorCode nErrorCode, NSString *errorDes);

@interface TOSClientKit()<TIMConnectionStatusChangeDelegate,
TIMClientReceiveMessageDelegate,
TIMLibTotalUnreadCountChangedDelegate>{
    TIMConnectOption*       connectOption;
    TOSInitOption*          initOSOption;
    TOSConnectOption*       connectOSOption;
    __weak id<TIMConnectionStatusChangeDelegate> connKitStatusDelagate;
    __weak id<TIMReceiveMessageDelegate> reckitMessageDelegate;
    __weak id<TIMTotalUnreadCountChangedDelegate> totalUnreadChangedDelegate;
    __weak id<TIMOnlineQueueDelegate> onlineQueueDelegate;
}
/// Flutter Activity
//@property (nonatomic, strong) TIMRTCMediaViewController * rtcMediaVC;

@end

@implementation TOSClientKit

static TOSClientKit *__onetimeClass;
static NSString * kTIMServerUrlSave = @"kTIM_Last_ServerUrl_Save";

+ (instancetype)sharedTOSKit{
    static dispatch_once_t oneToken;
    dispatch_once(&oneToken, ^{
        __onetimeClass = [[TOSClientKit alloc] init];
        __onetimeClass.disableMessageAlertSound = NO;
        __onetimeClass.disableMessageNotificaiton = NO;
        __onetimeClass.mqttConnected = @(NO);
        // 每次出现会话列表页 需要设置接收消息 因为保证出现会话列表页和聊天页都可以接收消息
        [[TIMClient sharedTIMClient] setTIMReceiveMessageDelegate:__onetimeClass];
    });
    return __onetimeClass;
}

- (instancetype)init {
    self = [super init];
    if (self) {
        /// 接受需要清空未读消息数
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(clearUnReadCount:) name:kTIMMessageClearUnReadCountNotification object:nil];
    }
    return self;
}

- (void)clearUnReadCount:(NSNotification *)notification {
    
    self.unReadCount = 0;
    
    NSDictionary * dict = @{@"lastMessage" : @"",
                         @"unReadCount" : @(self.unReadCount)
    };
    [[NSNotificationCenter defaultCenter] postNotificationName:KTOSClientLibLastMessageReceivedNotification object:dict];
    
}


- (void)connect:(TOSConnectOption *)option success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock tokenIncorrect:(void (^)(void))tokenIncorrectBlock{
    self->connectOSOption = option;
    NSString * moreInfo = [kitUtils DataTOjsonString:option.advanceParams?:@{}];
    [[OnlineDataSave shareOnlineDataSave] saveConnectAdParams:moreInfo];
    
    /// 客户资料缓存
    OnlineRequestManager.sharedCustomerManager.customerFields = option.customerFields;
    
    [[TIMClient sharedTIMClient] setTIMConnectionStatusChangeDelegate:self];
    [[OnlineRequestManager sharedCustomerManager] getUserInfoWithUserId:option.visitorId
                                                             externalId:option.externalId
                                                               nickname:option.nickname
                                                               phoneNum:option.mobile
                                                              headerUrl:option.headUrl
                                                         connectSuccess:^{
        NSLog(@"getUserInfoWithUserId connectSuccess");
        NSLog(@"链接成功");
//        if (self->reckitMessageDelegate && [self->reckitMessageDelegate respondsToSelector:@selector(getCurrentOnlineStatus:)]) {
//            [self->reckitMessageDelegate getCurrentOnlineStatus:(TinetChatStatusTypeOutline)];
//        }
        successBlock();
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        NSLog(@"getUserInfoWithUserId error");
        errorBlock(errCode,errorDes);
    } tokenIncorrect:^{
        NSLog(@"getUserInfoWithUserId tokenIncorrect");
        tokenIncorrectBlock();
    }];

//
//    [[TIMClient sharedTIMClient] connect:option success:successBlock error:errorBlock tokenIncorrect:tokenIncorrectBlock];
}

- (void)closeSession:(void (^)(void))successBlock
               error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock {
    [[OnlineEventSendManager sharedOnlineEventSendManager] chatCloseEventWithSuccess:^{
        //存储会话状态
        NSString * curMainUniqueId = [[OnlineDataSave shareOnlineDataSave] getMainUniqueId];
        TOSSessionInfoModel * model = [[TOSSessionInfoModel alloc] init];
        [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[model yy_modelToJSONString]];
        
        if (successBlock) {
            successBlock();
        }
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        if (errorBlock) {
            errorBlock(errCode, errorDes);
        }
    }];
}



/// 关闭上一个会话
/// @param visitorId 访客ID
/// @param successBlock 成功
/// @param errorBlock 失败
- (void)closeLastSession:(NSString *)visitorId succuess:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock {
    
    [[OnlineEventSendManager sharedOnlineEventSendManager] closeLastSessionWithVisitorId:visitorId success:^{
        //存储会话状态
        NSString * curMainUniqueId = [[OnlineDataSave shareOnlineDataSave] getMainUniqueId];
        TOSSessionInfoModel * model = [[TOSSessionInfoModel alloc] init];
        [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[model yy_modelToJSONString]];
        
        if (successBlock) {
            successBlock();
        }
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        if (errorBlock) {
            errorBlock(errCode, errorDes);
        }
    }];
    
    
    
}

/// 关闭会话
/// @param mainUniqueId 会话ID
/// @param visitorId 访客ID
/// @param successBlock 成功
/// @param errorBlock 失败
- (void)closeSessionMainUniqueId:(NSString *)mainUniqueId
                   withVisitorId:(NSString *)visitorId
                        succuess:(void (^)(void))successBlock
                           error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock {
    [[OnlineEventSendManager sharedOnlineEventSendManager] chatCloseSessionEventWithMainUniqueId:mainUniqueId withVisitorId:visitorId success:^{
        //存储会话状态
        NSString * curMainUniqueId = [[OnlineDataSave shareOnlineDataSave] getMainUniqueId];
        TOSSessionInfoModel * model = [[TOSSessionInfoModel alloc] init];
        [[OnlineDataSave shareOnlineDataSave] saveMainUniqueModel:[model yy_modelToJSONString]];
        
        if (successBlock) {
            successBlock();
        }
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        if (errorBlock) {
            errorBlock(errCode, errorDes);
        }
    }];
}

/// 设置附加参数
/// @param advanceParams 附加参数
- (void)setAdvanceParams:(NSDictionary *)advanceParams {
    [self->connectOSOption resetAdvanceParams:advanceParams];
    NSString * moreInfo = [kitUtils DataTOjsonString:advanceParams?:@{}];
    [[OnlineDataSave shareOnlineDataSave] saveConnectAdParams:moreInfo];
}

- (void)disconnect:(TOSDisConnectOption*)option success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock{
    [[TIMClient sharedTIMClient] disconnect:option success:successBlock error:errorBlock];
}

- (void)setDeviceTokenData:(NSData *)deviceTokenData{
    [[TIMClient sharedTIMClient] setDeviceTokenData:deviceTokenData];
}

- (void)getUnreadMessage:(void (^)(NSString *lastMessage , NSInteger unreadCount))successBlock withError:(void (^)(NSString *errorStr))errorBlock {
    
    NSString *mainUniqueId = [[OnlineDataSave shareOnlineDataSave] getMainUniqueId];
    
    [[OnlineRequestManager sharedCustomerManager] sessionInfoUnreadCountCurrentVisitorId:[[OnlineDataSave shareOnlineDataSave] getVisitorId] WithMainUniqueId:[NSString stringWithFormat:@"%@", mainUniqueId.length > 0 ? mainUniqueId : @""] withSuccess:successBlock withError:errorBlock];
}

/// 获取最后一条消息
- (void)getLastMessage:(void (^)(TIMMessageFrame * lastMessage))successBlock withError:(void (^)(NSString *errorStr))errorBlock {
    
    [[OnlineRequestManager sharedCustomerManager] getChatRecordListLastTime:@""
                                                                      limit:@"1"
                                                                    success:^(NSArray * _Nonnull chatList) {
        if (chatList.count>0) {
            OnlineChatRecordModel *model = chatList.lastObject;
            
            TIMMessageFrame * testModel = [self analysisHistoryWithModel:model withItemReload:NO];
            successBlock(testModel);
            
        }else{
            
        }
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        errorBlock(errorDes);
    }];
    
}

#pragma mark TIMConnectionStatusChangeDelegate

/*
 连接中
 */
- (void)onConnecting{
    NSLog(@"链接状态 ----- 正在连接");
    self.mqttConnected = @(NO);
    [[NSNotificationCenter defaultCenter] postNotificationName:kTIMUpdateConnectStatusNotification object:@{@"connectStatus":@NO}];
    [self->connKitStatusDelagate onConnecting];
}

/*
 连接成功
 */
- (void)onConnected{
    NSLog(@"链接状态 ----- 已连接");
    self.mqttConnected = @(YES);
    [[NSNotificationCenter defaultCenter] postNotificationName:kTIMUpdateConnectStatusNotification object:@{@"connectStatus":@YES}];
    [self->connKitStatusDelagate onConnected];
}

/*
 数据同步中
 */
- (void)onDataSyncing{
    NSLog(@"链接状态 ----- 同步中");
    [self->connKitStatusDelagate onDataSyncing];
}

/*
 数据同步完成
 */
- (void)onDataSynced{
    NSLog(@"链接状态 ----- 同步完成");
    [self->connKitStatusDelagate onDataSynced];
}

/*
 连接断开
 */
- (void)onDisConnected{
    NSLog(@"链接状态 ----- 已断开连接");
    self.mqttConnected = @(NO);
    [[NSNotificationCenter defaultCenter] postNotificationName:kTIMUpdateConnectStatusNotification object:@{@"connectStatus":@NO}];
    [self->connKitStatusDelagate onDisConnected];
}

/*
 断线重连中
 */
- (void)onReConnecting:(int)round interval:(int)interval{
    NSLog(@"链接状态 ----- 正在重连 %d, interval:%d",round,interval);
    self.mqttConnected = @(NO);
    [[NSNotificationCenter defaultCenter] postNotificationName:kTIMUpdateConnectStatusNotification object:@{@"connectStatus":@NO}];
    [self->connKitStatusDelagate onReConnecting:round interval:interval];
}

/*
 断线重连成功
 */
- (void)onReConnected{
    NSLog(@"链接状态 ----- 已重连");
    self.mqttConnected = @(YES);
    
    [[NSNotificationCenter defaultCenter] postNotificationName:kTIMUpdateConnectStatusNotification object:@{@"connectStatus":@YES}];
    [self->connKitStatusDelagate onReConnected];
}

/*
 被踢下线 服务器产生
 */
- (void)onKickOut{
    NSLog(@"链接状态 被踢下线");
    self.mqttConnected = @(NO);
    [self tim_showMBErrorView:@"您的账号已在其他设备登录"];
    [[NSNotificationCenter defaultCenter] postNotificationName:kTIMUpdateConnectStatusNotification object:@{
        @"connectStatus":@NO,
        @"kickOut":@YES
    }];
    [self->connKitStatusDelagate onKickOut];
    if (!self.disableMessageNotificaiton){
        UNUserNotificationCenter *center = [UNUserNotificationCenter currentNotificationCenter];
        UNMutableNotificationContent *content = [[UNMutableNotificationContent alloc] init];
        // 标题
        content.title = @"被踢下线";
        content.subtitle = @"您的IM账号已在其他设备登录";
        if (self.disableMessageAlertSound) {
        }else{
            content.sound = [UNNotificationSound defaultSound];
        }
        // 多少秒后发送,可以将固定的日期转化为时间
        NSTimeInterval time = [[NSDate dateWithTimeIntervalSinceNow:1] timeIntervalSinceNow];
        // repeats，是否重复，如果重复的话时间必须大于60s，要不会报错
        UNTimeIntervalNotificationTrigger *trigger = [UNTimeIntervalNotificationTrigger triggerWithTimeInterval:time repeats:NO];

        // 添加通知的标识符，可以用于移除，更新等操作
        NSString *identifier = [NSString stringWithFormat:@"%@%d",[[NSDate date] stringFromDateWithFormat:@"yyyyMMddHHmmss"],arc4random()%10000];
        UNNotificationRequest *request = [UNNotificationRequest requestWithIdentifier:identifier content:content trigger:trigger];
        if(![self getStateActive]){
            [center addNotificationRequest:request withCompletionHandler:^(NSError *_Nullable error) {
                NSLog(@"成功添加推送");
            }];
        }
    }
}

#pragma mark - 连接状态监听

-(void)setTOSKitConnectionChangeDelegate:(id<TIMConnectionStatusChangeDelegate>)delegate{
    [[TIMClient sharedTIMClient] setTIMConnectionStatusChangeDelegate:self];
}

#pragma mark - 消息接收监听

-(void)setTIMKitMessageRecvDelegate:(id<TIMReceiveMessageDelegate>)delegate{
    self->reckitMessageDelegate = delegate;
}

#pragma mark - 总未读数变化监听
-(void)setTIMTotalUnreadCountChangedDelegate:(id<TIMTotalUnreadCountChangedDelegate>)delegate{
    self->totalUnreadChangedDelegate = delegate;
    [[TIMClient sharedTIMClient] setTIMLibTotalUnreadCountChangedDelegate:self];
}


#pragma mark - 排队事件监听
- (void)setTIMOnlineQueueDelegate:(id<TIMOnlineQueueDelegate>)delegate {
    self->onlineQueueDelegate = delegate;
    
}

#pragma mark - 主动获取总未读数
-(NSNumber *)totalUnreadCount{
    return [TIMClient sharedTIMClient].totalUnreadCount;
}

#pragma mark TIMClientReceiveMessageDelegate 接收消息
- (void)onReceived:(TOSMessage *)message left:(int)nLeft{
    TIMKitLog(@"TOSClientKit TIMClientReceiveMessageDelegate");
    // 增加过滤当前不支持的会话类型
    if (!message.receiverId || message.receiverId.length <= 0 || [self isUnSupportSessionType:message.msg_from]) {
        return ;
    }
    TIMKitLog(@"接收到新数据 msgId:%@ receiverId = %@ senderId = %@",message.msg_id,message.receiverId,message.senderId);
    
    /// 排队事件监听
    [self onlineQueues:message];
    
    /// 接通机器人
    if ([message.type isEqualToString:@"RobotBridgeMessage"]) {
        if (self->reckitMessageDelegate && [self->reckitMessageDelegate respondsToSelector:@selector(getCurrentOnlineStatus:)]) {
            [self->reckitMessageDelegate getCurrentOnlineStatus:(TinetChatStatusTypeRobot)];
        }
    }
    /// 接通客服
    else if ([message.type isEqualToString:@"ChatBridgeMessage"]) {
        if (self->reckitMessageDelegate && [self->reckitMessageDelegate respondsToSelector:@selector(getCurrentOnlineStatus:)]) {
            [self->reckitMessageDelegate getCurrentOnlineStatus:(TinetChatStatusTypeOnline)];
        }
    }
    /// 满意度评价
    else if ([message.type isEqualToString:@"ChatInvestigationMessage"]) {
        ChatInvestigationMessage * InvestigationMsg = (ChatInvestigationMessage *)message.content;
        if ([InvestigationMsg.isClose boolValue]) {
            if (self->reckitMessageDelegate && [self->reckitMessageDelegate respondsToSelector:@selector(getCurrentOnlineStatus:)]) {
                [self->reckitMessageDelegate getCurrentOnlineStatus:(TinetChatStatusTypeOutline)];
            }
        }
    }
    /// 会话结束
    else if ([message.type isEqualToString:@"ChatCloseMessage"]) {
        if (self->reckitMessageDelegate && [self->reckitMessageDelegate respondsToSelector:@selector(getCurrentOnlineStatus:)]) {
            [self->reckitMessageDelegate getCurrentOnlineStatus:(TinetChatStatusTypeOutline)];
        }
    }
    
    /// 通知聊天页面有新消息需要做数据处理
    [[NSNotificationCenter defaultCenter] postNotificationName:kTIMMessageReceivedNotification object:message];
    
    
    if (self->reckitMessageDelegate && message) {
        NSLog(@"messageReceivedNotification2 ======= ");
        [self->reckitMessageDelegate onTIMReceiveMessage:message left:0];
    }
    dispatch_async(dispatch_get_main_queue(), ^{
        if (!self.disableMessageNotificaiton) {
            [self addNotificationWithMessage:message];
        }
    });
}

- (void)onReceived:(TOSMessage *)message withMessageType:(int)messageType {
    NSLog(@"进行消息处理messageType： %i", messageType);
    NSDictionary * dict = @{@"lastMessage" : [self lastMessageString:message withMessageType:messageType],
                         @"unReadCount" : @(self.unReadCount)
    };
    if (self.unReadCount > 0) {
        [[NSNotificationCenter defaultCenter] postNotificationName:KTOSClientLibLastMessageReceivedNotification object:dict];
    }
    
}


/// 最后一条消息转换
- (NSString *)lastMessageString:(TOSMessage *)message withMessageType:(int)messageType {
//    TOSMessage * testMessage;
    NSString * lastMessageStr = @"";
    switch (messageType) {
        case 1: {
            TextMessage * textMsg = (TextMessage *)message.content;
            lastMessageStr = textMsg.textContent;
            self.unReadCount += 1;
//            testMessage = [[TOSMessage alloc] initWithOption:message.messageUUID msg_id:message.msg_id type:message.type senderId:message.senderId receiverId:message.receiverId content:message.content msg_from:message.msg_from status:message.status timestamp:message.timestamp];
//            [testMessage setValue:@(messageType) forKey:NSStringFromSelector(@selector(messageType))];
            _lastMessage = message;
            NSLog(@"testMessage：%i", _lastMessage.messageType);
            break;
        }
        case 2: {
            lastMessageStr = @"【图片】";
            self.unReadCount += 1;
//            testMessage = [[TOSMessage alloc] initWithOption:message.messageUUID msg_id:message.msg_id type:message.type senderId:message.senderId receiverId:message.receiverId content:message.content msg_from:message.msg_from status:message.status timestamp:message.timestamp];
//            [testMessage setValue:@(messageType) forKey:NSStringFromSelector(@selector(messageType))];
            _lastMessage = message;
            break;
        }
        case 3: {
            lastMessageStr = @"【文件】";
            self.unReadCount += 1;
//            testMessage = [[TOSMessage alloc] initWithOption:message.messageUUID msg_id:message.msg_id type:message.type senderId:message.senderId receiverId:message.receiverId content:message.content msg_from:message.msg_from status:message.status timestamp:message.timestamp];
//            [testMessage setValue:@(messageType) forKey:NSStringFromSelector(@selector(messageType))];
            _lastMessage = message;
            break;
        }
        case 4: {
            lastMessageStr = @"【视频】";
            self.unReadCount += 1;
//            testMessage = [[TOSMessage alloc] initWithOption:message.messageUUID msg_id:message.msg_id type:message.type senderId:message.senderId receiverId:message.receiverId content:message.content msg_from:message.msg_from status:message.status timestamp:message.timestamp];
//            [testMessage setValue:@(messageType) forKey:NSStringFromSelector(@selector(messageType))];
            _lastMessage = message;
            break;
        }
        case 5: {
            lastMessageStr = @"【机器人富文本】";
            self.unReadCount += 1;
//            testMessage = [[TOSMessage alloc] initWithOption:message.messageUUID msg_id:message.msg_id type:message.type senderId:message.senderId receiverId:message.receiverId content:message.content msg_from:message.msg_from status:message.status timestamp:message.timestamp];
//            [testMessage setValue:@(messageType) forKey:NSStringFromSelector(@selector(messageType))];
            _lastMessage = message;
            break;
        }
        case 7: {
            lastMessageStr = @"【语音】";
            self.unReadCount += 1;
//            testMessage = [[TOSMessage alloc] initWithOption:message.messageUUID msg_id:message.msg_id type:message.type senderId:message.senderId receiverId:message.receiverId content:message.content msg_from:message.msg_from status:message.status timestamp:message.timestamp];
//            [testMessage setValue:@(messageType) forKey:NSStringFromSelector(@selector(messageType))];
            _lastMessage = message;
            break;
        }
        case 10: {
            lastMessageStr = @"【卡片】";
            self.unReadCount += 1;
//            testMessage = [[TOSMessage alloc] initWithOption:message.messageUUID msg_id:message.msg_id type:message.type senderId:message.senderId receiverId:message.receiverId content:message.content msg_from:message.msg_from status:message.status timestamp:message.timestamp];
//            [testMessage setValue:@(messageType) forKey:NSStringFromSelector(@selector(messageType))];
            _lastMessage = message;
            break;
        }
        default:
            break;
    }
    
//    if ([message.type isEqualToString:@"TextMessage"]) {        /// 文本
//        TextMessage * textMsg = (TextMessage *)message.content;
//        lastMessageStr = textMsg.textContent;
//        self.unReadCount += 1;
//    }
//    else if ([message.type isEqualToString:@"ChatLeadingWordsMessage"]) {       /// 欢迎语
//        ChatLeadingWordsMessage * robotBridgeMsg = (ChatLeadingWordsMessage *)message.content;
//        lastMessageStr = [NSString stringWithFormat:@"[%@]", robotBridgeMsg.textContent];
//    }
//    else if ([message.type isEqualToString:@"ChatInvestigationMessage"]) {      /// 满意度评价
////        ChatInvestigationMessage * InvestigationMsg = (ChatInvestigationMessage *)message.content;
//        lastMessageStr = @"[满意度评价]";
//    }
//    else if ([message.type isEqualToString:@"SystemMessage"]) {     /// 系统消息
//        TextMessage * textMsg = (TextMessage *)message.content;
//        lastMessageStr = textMsg.textContent;
//    }
//    else if ([message.type isEqualToString:@"RichTextMessage"]) {       /// 富文本
//        RichTextMessage * richTextMsg = (RichTextMessage *)message.content;
//        if ([richTextMsg.type isEqualToString:@"GXVideo"]) {        /// 视频类型
//            lastMessageStr = @"[视频]";
//        }
//        else {
//            //去掉标签
//            NSString * contentStr = richTextMsg.textContent;
//            NSRegularExpression * regularExpretion = [NSRegularExpression regularExpressionWithPattern:@"<[^>]*>|\n"
//                                                                                            options:0
//                                                                                              error:nil];
//            contentStr = [regularExpretion stringByReplacingMatchesInString:contentStr options:NSMatchingReportProgress range:NSMakeRange(0, contentStr.length) withTemplate:@""];
//            lastMessageStr = contentStr;
//        }
//        self.unReadCount += 1;
//    }
//    else if ([message.type isEqualToString:@"ImageMessage"]) {          /// 图片消息
//        lastMessageStr = @"[图片消息]";
//        self.unReadCount += 1;
//    }
//    else if ([message.type isEqualToString:@"VideoMessage"]) {          /// 视频消息
//        lastMessageStr = @"[视频消息]";
//        self.unReadCount += 1;
//    }
//    else if ([message.type isEqualToString:@"FileMessage"]) {           /// 文件消息
//
//        FileMessage * fileMsg = (FileMessage *)message.content;
//        if ([fileMsg.type isEqualToString:@"GXVideo"]) {
//            lastMessageStr = @"[视频消息]";
//        }
//        else {
//            lastMessageStr = @"[文件消息]";
//        }
//        self.unReadCount += 1;
//    }
//    else if ([message.type isEqualToString:@"VoiceMessage"]) {          /// 语音消息
//        lastMessageStr = @"[语音消息]";
//        self.unReadCount += 1;
//    }
//    else if ([message.type isEqualToString:@"RobotRichMessage"]) {          /// 机器人选项消息
//
//        RobotRichMessage * robotRIchMsg = (RobotRichMessage *)message.content;
//        if ([robotRIchMsg.type isEqualToString:@"GXVoice"]) {
//            lastMessageStr = @"[语音消息]";
//        }
//        else {
//            lastMessageStr = @"[机器人选项消息]";
//        }
//        self.unReadCount += 1;
//    }
//    else if ([message.type isEqualToString:@"RobotRichMessage14"]) {            /// 机器人选项消息
//
//        RobotRichMessage * robotRIchMsg = (RobotRichMessage *)message.content;
//        if ([robotRIchMsg.type isEqualToString:@"GXVoice"]) {
//            lastMessageStr = @"[语音]";
//        }
//        else if([robotRIchMsg.type isEqualToString:@"GXRobotSelectCombination"]) {
//            lastMessageStr = robotRIchMsg.textContent;
//        }
//        else {
//            lastMessageStr = @"机器人选项消息";
//        }
//        self.unReadCount += 1;
//    }
//    else if ([message.type isEqualToString:@"RobotRichMessage20"]) {
//        lastMessageStr = @"机器人选项消息";
//        self.unReadCount += 1;
//    }
//    else if ([message.type isEqualToString:@"RobotBridgeMessage"]) {
//        lastMessageStr = @"接通机器人";
//    }
//    else if ([message.type isEqualToString:@"ChatBridgeMessage"]) {     /// 接通客服
//        lastMessageStr = @"接通客服";
//    }
//    else if ([message.type isEqualToString:@"ChatQueueMessage"]) {        /// 进入排队
//        lastMessageStr = @"进入排队";
//    }
//    else if ([message.type isEqualToString:@"ChatLocationMessage"]) {        /// 排队位置播报
//        ChatLocationMessage * eventMsg = (ChatLocationMessage *)message.content;
//        lastMessageStr = eventMsg.locationstr;
//    }
//    else if ([message.type isEqualToString:@"ChatLocationMessage"]) {        /// 座席撤回消息
//        lastMessageStr = @"[撤回消息]";
//    }
//    else if ([message.type isEqualToString:@"ChatLeaveMessage"]) {        /// 留言消息事件
//        lastMessageStr = @"[留言消息]";
//    }
//    else if ([message.type isEqualToString:@"ChatLeaveMessage"]) {        /// 留言消息
//        ChatLeaveReceiveMessage * chatLeaveReceiveMsg = (ChatLeaveReceiveMessage *)message.content;
//        lastMessageStr = chatLeaveReceiveMsg.textContent;
//    }
    return lastMessageStr;
}


/// 消息撤回
/// @param message 消息
- (void)onMessageRecalled:(TOSMessage *)message {
    TIMKitLog(@"TOSClientKit TIMClientReceiveMessageDelegate");
    if (!message.receiverId || message.receiverId.length < 2) {
        return ;
    }
    TIMKitLog(@"接收到新数据 msgId:%@ receiverId = %@ senderId = %@",message.msg_id,message.receiverId,message.senderId);
    [[NSNotificationCenter defaultCenter] postNotificationName:kTIMMessageRecalledNotification object:message];
    if (self->reckitMessageDelegate && message) {
        NSLog(@"messageReceivedNotification2 ======= ");
        [self->reckitMessageDelegate onMessageRecalled:message];
    }
}

#pragma mark - 总未读数变动
- (void)onChanged:(NSNumber *)totalUnreadCount{
    [self->totalUnreadChangedDelegate onChanged:totalUnreadCount];
}

#pragma mark - 排队监听处理
/// 排队监听
- (void)onlineQueues:(TOSMessage *)message {
    /// 进入排队事件
    if ([message.type isEqualToString:@"ChatQueueMessage"]) {
        ChatQueueMessage * subMessage = (ChatQueueMessage *)message.content;
        /// 排队监听 进入排队事件
        if (self->onlineQueueDelegate && [self->onlineQueueDelegate respondsToSelector:@selector(chatQueue:)]) {
            [self->onlineQueueDelegate chatQueue:subMessage];
        }
    }
    /// 排队播报事件
    if ([message.type isEqualToString:@"ChatLocationMessage"]) {
        ChatLocationMessage * subMessage = (ChatLocationMessage *)message.content;
        /// 排队监听 排队播报事件
        if (self->onlineQueueDelegate && [self->onlineQueueDelegate respondsToSelector:@selector(chatQueueLocation:)]) {
            [self->onlineQueueDelegate chatQueueLocation:subMessage];
        }
    }
    /// 接入人工
    if ([message.type isEqualToString:@"ChatBridgeMessage"]) {
        /// 排队监听 接入成功
        if (self->onlineQueueDelegate && [self->onlineQueueDelegate respondsToSelector:@selector(chatBridge:)]) {
            ChatBridgeMessage * subMessage = (ChatBridgeMessage *)message.content;
            [self->onlineQueueDelegate chatBridge:subMessage];
        }
    }
    /// 满意度
    if ([message.type isEqualToString:@"ChatInvestigationMessage"]) {
        ChatInvestigationMessage * InvestigationMsg = (ChatInvestigationMessage *)message.content;
        if ([InvestigationMsg.isClose boolValue] && [[TIMClient sharedTIMClient] getLibOnlineStatus] == TinetChatStatusTypeRobot) {
            /// 排队监听 退出排队
            if (self->onlineQueueDelegate && [self->onlineQueueDelegate respondsToSelector:@selector(exitChatQueue)]) {
                [self->onlineQueueDelegate exitChatQueue];
            }
        }
    }
    if ([message.type isEqualToString:@"ChatCloseMessage"] && [[TIMClient sharedTIMClient] getLibOnlineStatus] == TinetChatStatusTypeRobot ) {
        /// 排队监听 退出排队
        if (self->onlineQueueDelegate && [self->onlineQueueDelegate respondsToSelector:@selector(exitChatQueue)]) {
            [self->onlineQueueDelegate exitChatQueue];
        }
    }
}

#pragma mark - 发送消息
- (void)sendMessage:(TIMMessageSendOption *)option progress:(void(^)(float progress))progressBlock success:(void (^)(TOSMessage * timMessage))successBlock error:(void (^)(TOSMessage * message,TIMConnectErrorCode nErrorCode, NSString *errorDes))errorBlock{
    // 主要是为了刷新界面 不需要做多线程处理的问题
    __block SendMsgProgressCallBack progressCb = progressBlock;
    __block SendMsgSuccessCallBack successCb = successBlock;
    __block SendMsgErrorCallBack errorCb = errorBlock;
    [[TIMClient sharedTIMClient] sendMessage:option progress:^(float progress) {
        progressCb(progress);
    } success:^(TOSMessage * _Nonnull timMessage) {
        // 通知Chat界面刷新
        [[NSNotificationCenter defaultCenter] postNotificationName:kTIMMessageUpdateChatUINotification object:timMessage];
        successCb(timMessage);
    } error:^(TOSMessage * _Nonnull message, TIMConnectErrorCode nErrorCode, NSString * _Nonnull errorDes) {
        // 通知Chat界面刷新
        [[NSNotificationCenter defaultCenter] postNotificationName:kTIMMessageUpdateChatUINotification object:message];
        errorCb(message,nErrorCode,errorDes);
    }];  
}


#pragma mark - 本地推送
-(void)addNotificationWithMessage:(TOSMessage *)message{
    // 如果是private消息则不通知
    if ([message.type isEqualToString:@"private"]) {
        TIMKitLog(@"private不做本地通知");
        return ;
    }
    UNUserNotificationCenter *center = [UNUserNotificationCenter currentNotificationCenter];
    UNMutableNotificationContent *content = [[UNMutableNotificationContent alloc] init];
    
//    UNNotificationAction *foregroundAction = [UNNotificationAction actionWithIdentifier:identifier title:@"前台模式" options:UNNotificationActionOptionForeground];
//    if (@available(iOS 11.0, *)) {
//        UNNotificationCategory *category = [UNNotificationCategory categoryWithIdentifier:identifier actions:@[foregroundAction] intentIdentifiers:@[kTIMForeMessageReceivedNotification] options:UNNotificationCategoryOptionHiddenPreviewsShowSubtitle];
//
//        NSSet *set = [NSSet setWithObjects:category, nil];
//        [center setNotificationCategories:set];
//    } else {
//        // Fallback on earlier versions
//    }

}
#pragma mark - 获取当前活动的viewcontroller
- (UIViewController*)topViewController {
    return [self topViewControllerWithRootViewController:[UIApplication sharedApplication].keyWindow.rootViewController];
}

- (UIViewController*)topViewControllerWithRootViewController:(UIViewController*)rootViewController {
    if ([rootViewController isKindOfClass:[UITabBarController class]]) {

        UITabBarController* tabBarController = (UITabBarController*)rootViewController;
        return [self topViewControllerWithRootViewController:tabBarController.selectedViewController];
    } else if ([rootViewController isKindOfClass:[UINavigationController class]]) {

        UINavigationController* navigationController = (UINavigationController*)rootViewController;
        return [self topViewControllerWithRootViewController:navigationController.visibleViewController];
    } else if (rootViewController.presentedViewController) {

        UIViewController* presentedViewController = rootViewController.presentedViewController;
        return [self topViewControllerWithRootViewController:presentedViewController];
    } else {

        return rootViewController;
    }
}

-(BOOL)getStateActive{
    // 获取是否app在前台活跃
    UIApplicationState state = [UIApplication sharedApplication].applicationState;
    return (state == UIApplicationStateActive);
}

-(BOOL)isUnSupportSessionType:(TIMSessionType)sessionType{
    if (sessionType == TIMSessionType_SINGLE_CHAT) {
        return NO;
    } else if (sessionType == TIMSessionType_GROUP_CHAT){
        return NO;
    }else if (sessionType == TIMSessionType_OnLINESERVICE_CHAT){
        return NO;
    } else {
        return YES;
    }
}

-(void)cleanFolder:(NSString *)path{
    NSString *documentsPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
    NSString *bundlePath = [documentsPath stringByAppendingPathComponent:path];
    NSDirectoryEnumerator *dirEnum = [[NSFileManager defaultManager] enumeratorAtPath:bundlePath];
    NSString *fileName;
    while (fileName= [dirEnum nextObject]) {
        [[NSFileManager defaultManager] removeItemAtPath:fileName error:nil];
    }
}

/**
 其他
 
 生成临时的文件地址
 */
-(NSString *)genFileUrlWithFileId:(NSString * )strFileId{
    NSString * strNonce = [kitUtils RandomString];
    NSString * timeStamp = [kitUtils getNowTimestampWithSec];
    NSString * signedToken = [kitUtils sha256HashFor:self->connectOption.accessToken];
    NSString * signature = [kitUtils sha256HashFor:[NSString stringWithFormat:@"%@%@%@%@%@", self->connectOption.appId,self->_curUserId,timeStamp,strNonce,signedToken]];
    if ([kitUtils isBlankString:strFileId]) {
        TIMKitLog(@"strFileId is Null");
        return @"";
    }
    NSDictionary *parameters = @{ @"appId" : self->connectOption.appId,
                                  @"userId" : self->_curUserId,
                                  @"timestamp":timeStamp,
                                  @"signature":signature,
                                  @"nonce": strNonce,
                                  @"fileId":strFileId,
                                  @"deviceId":[kitUtils getDeviceUDID]
    };
    
    NSString * genFileURL = [NSString stringWithFormat:@"%@/%@",self->initOSOption.apiUrl,@"file/download"];
    NSString * strParams = @"";
    for (NSString* key in parameters){
        NSString * value = parameters[key];
        strParams = [NSString stringWithFormat:@"%@=%@&%@",key,value,strParams];
    }
    strParams = [kitUtils removeTheLastOneStr:strParams];

    return [NSString stringWithFormat:@"%@?%@",genFileURL,strParams];
}

//获取当前屏幕显示的viewcontroller
//+ (UIViewController *)getCurrentVC
//{
//    UIViewController *result = nil;
//
//    UIWindow * window = [[UIApplication sharedApplication] keyWindow];
//    if (window.windowLevel != UIWindowLevelNormal)
//    {
//        NSArray *windows = [[UIApplication sharedApplication] windows];
//        for(UIWindow * tmpWin in windows)
//        {
//            if (tmpWin.windowLevel == UIWindowLevelNormal)
//            {
//                window = tmpWin;
//                break;
//            }
//        }
//    }
//
//    UIView *frontView = [[window subviews] objectAtIndex:0];
//    id nextResponder = [frontView nextResponder];
//
//    if ([nextResponder isKindOfClass:[UIViewController class]])
//        result = nextResponder;
//    else
//        result = window.rootViewController;
//
//    NSLog(@"class name = %@",[result class]);
//    return result;
//}

//- (void)cancelLocalNotificaitons {
//
//    // 取消一个特定的通知
//    NSArray *notificaitons = [[UIApplication sharedApplication] scheduledLocalNotifications];
//    // 获取当前所有的本地通知
//    if (!notificaitons || notificaitons.count <= 0) { return; }
//    for (UILocalNotification *notify in notificaitons) {
//        if ([[notify.userInfo objectForKey:@"id"] isEqualToString:@"LOCAL_NOTIFY_TIMMESSAGE_ID"]) {
//            if (@available(iOS 10.0, *)) {
//                [[UNUserNotificationCenter currentNotificationCenter] removePendingNotificationRequestsWithIdentifiers:@[LocalNotiReqIdentifer]];
//            }
//            break;
//        }
//    }
//    // 取消所有的本地通知
//    //[[UIApplication sharedApplication] cancelAllLocalNotifications];
//}
//
//@optional

/**
 当App处于后台时，接收到消息并弹出本地通知的回调方法

 @param message     接收到的消息
 @param senderName  消息发送者的用户名称
 @return            当返回值为NO时，SDK会弹出默认的本地通知提示；当返回值为YES时，SDK针对此消息不再弹本地通知提示

 @discussion 如果您设置了TIMKit消息监听之后，当App处于后台，收到消息时弹出本地通知之前，会执行此方法。
 如果App没有实现此方法，SDK会弹出默认的本地通知提示。
 流程：
 SDK接收到消息 -> App处于后台状态 -> 通过用户/群组/群名片信息提供者获取消息的用户/群组/群名片信息
 -> 用户/群组信息为空 -> 不弹出本地通知
 -> 用户/群组信息存在 -> 回调此方法准备弹出本地通知 -> App实现并返回YES        -> SDK不再弹出此消息的本地通知
                                             -> App未实现此方法或者返回NO -> SDK弹出默认的本地通知提示


 您可以通过TIMKit的disableMessageNotificaiton属性，关闭所有的本地通知(此时不再回调此接口)。

 @warning 如果App在后台想使用SDK默认的本地通知提醒，需要实现用户/群组提供者，并返回正确的用户信息或群组信息。
 */
//- (BOOL)onTIMCustomLocalNotification:(TIMMessage *)message withSenderName:(NSString *)senderName;

/**
 当App处于前台时，接收到消息并播放提示音的回调方法

 @param message 接收到的消息
 @return        当返回值为NO时，SDK会播放默认的提示音；当返回值为YES时，SDK针对此消息不再播放提示音

 @discussion 收到消息时播放提示音之前，会执行此方法。
 如果App没有实现此方法，SDK会播放默认的提示音。
 流程：
 SDK接收到消息 -> App处于前台状态 -> 回调此方法准备播放提示音 -> App实现并返回YES        -> SDK针对此消息不再播放提示音
                                                      -> App未实现此方法或者返回NO -> SDK会播放默认的提示音

 您可以通过TIMKit的disableMessageAlertSound属性，关闭所有前台消息的提示音(此时不再回调此接口)。
 */
//- (BOOL)onTIMCustomAlertSound:(TIMMessage *)message;
//#pragma mark 消息通知提醒
///**
// 是否关闭所有的本地通知，默认值是NO
//
// @discussion 当App处于后台时，默认会弹出本地通知提示，您可以通过将此属性设置为YES，关闭所有的本地通知。
// */
//@property (nonatomic, assign) BOOL disableMessageNotificaiton;
//
///**
// 是否关闭所有的前台消息提示音，默认值是NO
//
// @discussion 当App处于前台时，默认会播放消息提示音，您可以通过将此属性设置为YES，关闭所有的前台消息提示音。
// */
//@property (nonatomic, assign) BOOL disableMessageAlertSound;

// 改版接口

// 静态方法
+ (NSString *)getSDKVersion {
    return strSDKVersion;
}

- (void)initSDK:(TOSInitOption *)option{
    self->initOSOption = option;
    NSDictionary * advanceParams = option.advanceParams;
    if ([advanceParams objectForKey:@"configENVString"] && [advanceParams[@"configENVString"] isKindOfClass:[NSString class]]) {
        [kitUtils setEnvConf:advanceParams[@"configENVString"]];
    }
    [[OnlineInitOption shareOnlineInitOption] initWithOptionIsDebug:option.debug
                                                             apiUrl:option.apiUrl
                                                          onlineUrl:option.onlineUrl
                                                       accessSecret:option.accessSecret
                                                           accessId:option.accessId
                                                       enterpriseId:option.enterpriseId];
}

/// 获取当前在线状态
- (TinetChatStatusType)getCurrentOnlineStatus {
    return [[TIMClient sharedTIMClient] getLibOnlineStatus];
}


- (TOSSessionInfoModel *)getCurrentSessionInfo{
    TOSSessionInfoModel * sessModel = [[TOSSessionInfoModel alloc] init];
    NSString * sessModelStr = [[OnlineDataSave shareOnlineDataSave] getMainUniqueModel];
    if ([kitUtils isBlankString:sessModelStr]) {
        return sessModel;
    }
    NSDictionary * modelJson = [kitUtils dictionaryWithJsonString:sessModelStr];
    return [TOSSessionInfoModel yy_modelWithJSON:modelJson];
}


//拉取历史消息
-(TIMMessageFrame *)analysisHistoryWithModel:(OnlineChatRecordModel*)model withItemReload:(BOOL)reload {
    NSLog(@"model ======== %@",[model yy_modelToJSONObject]);
    BOOL messageRecvDirection = nil;
    if ([model.senderType.stringValue isEqualToString:@"2"]) {//访客发送的消息
        messageRecvDirection = NO;
    }else if ([model.senderType.stringValue isEqualToString:@"4"]) {//机器人发送的消息
        messageRecvDirection = YES;
    }else{
        messageRecvDirection = YES;
    }
    
    NSString*messageStr = @"";
    TIMMessageFrame *messageF = nil;
    
    /// 移动端目前只处理到32种类型，超过32的都是未知消息类型
    if ([model.messageType intValue] > 32) {
        
        messageF = [ICMessageHelper createMessageFrame:TypeUnknown originalType:@"text" content:@"未知消息类型" extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:NO picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
        
        return messageF;
    }
    
    if ([model.messageType intValue] == 1) {//文本消息
        if (model.content == nil || model.content.length == 0) {
            return messageF;
        }
        messageStr = model.content;
        
        if (messageRecvDirection) {
            //系统消息
            if ([model.senderType.stringValue isEqualToString:@"3"] || [model.senderType.stringValue isEqualToString:@"5"]) {//机器人发送的消息
                messageF = [ICMessageHelper createMessageFrame:TypeSystem originalType:@"text" content:messageStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
                return messageF;
            }else{
                
                messageF = [ICMessageHelper createMessageFrame:TypeText originalType:@"text" content:messageStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
                return messageF;
            }
            
        } else {
            
            messageF = [ICMessageHelper createMessageFrame:TypeText originalType:@"text" content:messageStr extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
            return messageF;
        }
        
    }else if ([model.messageType intValue] == 2) {//图片消息
        NSString*fileKey = @"null";
        NSString*fileName = @"null";
        if (![kitUtils isBlankString:model.fileKey]) {
            fileKey = model.fileKey;
        }
        if (![kitUtils isBlankString:model.fileName]) {
            fileName = model.fileName;
        }
        NSString * timestamp = [kitUtils getNowTimestampWithSec];
        
        NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
        NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
        NSString*urlPath =   [[NSString stringWithFormat:@"%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[NSString stringWithFormat:@"%@/api/app/message/file",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl]],fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        
        //根据msgId去本地搜寻
        UIImage *image;
        NSString *localPath = @"";
        CGFloat fixelW = 100;
        CGFloat fixelH = 100;
        NSString * fileKeyName = [model.fileKey stringByDeletingPathExtension];
        localPath = [[ICMediaManager sharedManager] smallImgPath:fileKeyName];
        image = [UIImage imageWithContentsOfFile:localPath];
        if (image) {
            
            fixelW = image.size.width;
            fixelH = image.size.height;
        } else {
            image = [UIImage imageWithData:[NSData dataWithContentsOfURL:[NSURL URLWithString:urlPath]]];
            if (image) {
                [[ICMediaManager sharedManager] saveImage:image msgId:fileKeyName picType:@"jpg"];
                fixelW = image.size.width;
                fixelH = image.size.height;
            }
        }
        
        if (messageRecvDirection){
            
            messageF = [ICMessageHelper createMessageFrame:TypePic originalType:@"image" content:@"[图片]" extra:@"" auditExtra:@"" path:localPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
            return messageF;
        }else{
            
            messageF = [ICMessageHelper createMessageFrame:TypePic originalType:@"image" content:@"[图片]" extra:@"" auditExtra:@"" path:localPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
            return messageF;
        }
    }
    else if ([model.messageType intValue] == 3 ||
             [model.messageType intValue] == 8){//文件消息
        
        NSString*fileUrlPath = @"";
        NSString*fileKey = @"null";
        NSString*fileName = @"null";
        if (![kitUtils isBlankString:model.fileKey]) {
            fileKey = model.fileKey;
        }
        if (![kitUtils isBlankString:model.fileName]) {
            fileName = model.fileName ;
        }
        
        NSString *path = @"";
        if ([model.messageType intValue] == 8) {
            path = @"/api/kb/files/attachment";
        } else {
            path = @"/api/app/message/file";
        }
        
        //视频消息类型
        if (fileName.length>0 && [fileName rangeOfString:@"mp4"].location !=NSNotFound) {
            
            NSString * timestamp = [kitUtils getNowTimestampWithSec];
            NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
            NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
            
            NSString*urlPath =  [[NSString stringWithFormat:@"%@%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl],path,fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            
            UIImage *videoArrowImage;
            NSString * localPath;
            localPath = [[ICMediaManager sharedManager] smallImgPath:model.uniqueId];
            videoArrowImage = [UIImage imageWithContentsOfFile:localPath];
            
            CGFloat fixelW = videoArrowImage.size.width;
            CGFloat fixelH = videoArrowImage.size.height;
            
            if (messageRecvDirection) {
                messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
                return messageF;
            }else{
                
                messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
                return messageF;
            }
        }else{//文件类型
            //文件消息类型
            if (fileName.length>0 ) {
                NSString * timestamp = [kitUtils getNowTimestampWithSec];
                NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
                NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
                NSString*urlPath =   [[NSString stringWithFormat:@"%@%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl],path,fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
                fileUrlPath = urlPath;
            }else{
                fileUrlPath = @"";
            }
            
            NSMutableDictionary*dict = [[NSMutableDictionary alloc]init];
            [dict setObject:model.fileName?:@"" forKey:@"fileName"];
            [dict setObject:model.fileKey?:@"" forKey:@"fileKey"];
            NSError *parseError = nil;
            NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dict options:NSJSONWritingPrettyPrinted error:&parseError];
            NSString*messageContent = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
            
            
            if (messageRecvDirection) {
                messageF = [ICMessageHelper createMessageFrame:TypeCustomFile originalType:@"file" content:messageContent extra:@"" auditExtra:@"" path:fileUrlPath urlPath:fileUrlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
                return messageF;
            }else{
                messageF = [ICMessageHelper createMessageFrame:TypeCustomFile originalType:@"file" content:messageContent extra:@"" auditExtra:@"" path:fileUrlPath urlPath:fileUrlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
                return messageF;
            }
        }
    }
    else if( [model.messageType intValue] == 4) {//4视频消息
        NSString*fileKey = @"null";
        NSString*fileName = @"null";
        if (![kitUtils isBlankString:model.fileKey]) {
            fileKey = model.fileKey;
        }
        if (![kitUtils isBlankString:model.fileName]) {
            fileName = model.fileName;
        }
        
        //视频消息类型
        if (fileName.length>0 && ([fileName rangeOfString:@"mp4"].location !=NSNotFound || [fileName rangeOfString:@"MP4"].location !=NSNotFound)) {
            
            NSString * timestamp = [kitUtils getNowTimestampWithSec];
            NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
            NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
            NSString*urlPath =  [[NSString stringWithFormat:@"%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[NSString stringWithFormat:@"%@/api/app/message/file",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl]],fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            
            UIImage *videoArrowImage;
            NSString * localPath;
            localPath = [[ICMediaManager sharedManager] smallImgPath:model.uniqueId];
            videoArrowImage = [UIImage imageWithContentsOfFile:localPath];
            
            CGFloat fixelW = videoArrowImage.size.width;
            CGFloat fixelH = videoArrowImage.size.height;
            
            if (messageRecvDirection) {
                
                messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
                return messageF;
            }else{
                messageF = [ICMessageHelper createMessageFrame:TypeVideo originalType:@"video" content:@"[视频]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(fixelW, fixelH) picType:@"jpg" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
                return messageF;
            }
        }else{//统一认定为文件类型
            
        }
        
    }else if ([model.messageType intValue] == 5 ||
              [model.messageType intValue] == 26) {//机器人富文本消息
        
        
        if ([model.messageType intValue] == 26) {
            TOSMessageKnowledgeBaseModel *knowledgeBaseModel = [TOSMessageKnowledgeBaseModel yy_modelWithJSON:model.content];
            model.content = knowledgeBaseModel.head;
            
            if (knowledgeBaseModel.list &&
                [knowledgeBaseModel.list isKindOfClass:[NSArray class]] &&
                knowledgeBaseModel.list.count > 0) {
                
                [knowledgeBaseModel.list enumerateObjectsUsingBlock:^(TOSMessageKnowledgeBaseListModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    
                    if ([obj.type isEqualToString:@"click"]) {
                        model.content = [NSString stringWithFormat:@"%@ <knowledge data-frontend=%@>#%@</knowledge>",model.content,obj.click.content?:@"",obj.click.content?:@""];
                    }
                }];
            }
        }
        
        RichTextMessage *richTextMsg = [[RichTextMessage alloc] initMessageWithContent:model.content];
        
        NSString *contentStr = (NSString *)richTextMsg.elements;
        
        messageF = [ICMessageHelper createMessageFrame:GXRichText originalType:@"text" content:contentStr extra:(NSString *)model.repliedMessage auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
        return messageF;
    }else if ([model.messageType intValue] == 7) {//语音消息
        NSString*fileKey = @"null";
        NSString*fileName = @"null";
        if (![kitUtils isBlankString:model.fileKey]) {
            fileKey = model.fileKey;
        }
        if (![kitUtils isBlankString:model.fileName]) {
            fileName = model.fileName;
        }
        
        NSString * timestamp = [kitUtils getNowTimestampWithSec];
        NSString*accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
        NSString * sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
        NSString*urlPath =   [[NSString stringWithFormat:@"%@?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[NSString stringWithFormat:@"%@/api/app/message/file",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl]],fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        
        NSNumber *voiceDuration = @1;
        if (messageRecvDirection) {
            
            messageF = [ICMessageHelper createMessageFrame:TypeVoice originalType:@"voice" content:@"[语音]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:voiceDuration msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
            return messageF;
        }else {
            messageF = [ICMessageHelper createMessageFrame:TypeVoice originalType:@"voice" content:@"[语音]" extra:@"" auditExtra:@"" path:urlPath urlPath:urlPath from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:voiceDuration msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeZero picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
            return messageF;
        }
    } else if ([model.messageType intValue] == 10) {
            BOOL isSender = NO;
            NSString *senderId;
            NSString *receiverId;
            if (messageRecvDirection) {
                isSender = NO;
                senderId = model.sender;
                receiverId = model.visitorId;
            } else {
                isSender = YES;
                senderId = model.visitorId;
                receiverId = model.sender;
            }
            
            NSData *jsonData = [model.content dataUsingEncoding:NSUTF8StringEncoding];
            NSError *err;
            NSDictionary *messageContentDic = [NSJSONSerialization JSONObjectWithData:jsonData
                                                                              options:NSJSONReadingMutableContainers
                                                                                error:&err];
            
            if (messageContentDic &&
                [messageContentDic isKindOfClass:[NSDictionary class]] &&
                [[messageContentDic allKeys] containsObject:@"cardType"] &&
                [messageContentDic[@"cardType"] isKindOfClass:[NSString class]] &&
                [messageContentDic[@"cardType"] isEqualToString:@"1"]) {    //物流卡片
                
                TIMLogisticsCardMessage *cardMsg = [TIMLogisticsCardMessage yy_modelWithJSON:model.content];
                messageF = [ICMessageHelper createMessageFrame:TypeLogisticsCard originalType:model.type content:cardMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:senderId to:receiverId fileKey:model.mainUniqueId bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:model.createTime.integerValue showTime:[self p_needShowTimeStamp:(NSTimeInterval)model.createTime.integerValue] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1 senderType:model.senderType];
                return messageF;
            } else {
                TIMCommodityCardMessage *commodityMsg = [TIMCommodityCardMessage yy_modelWithJSON:model.content];
                
                messageF = [ICMessageHelper createMessageFrame:TypeCommodityCardDetails originalType:model.type content:commodityMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:senderId to:receiverId fileKey:model.mainUniqueId bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:model.createTime.integerValue showTime:[self p_needShowTimeStamp:(NSTimeInterval)model.createTime.integerValue] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1 senderType:model.senderType];
                return messageF;
            }
            
        } else if ([model.messageType intValue] == 13) {
            
            TOSMessageSmallProgramModel *smallProgramMsg = [TOSMessageSmallProgramModel yy_modelWithJSON:model.content];
            
            messageF = [ICMessageHelper createMessageFrame:TypeSmallProgramCard originalType:@"text" content:(NSString *)smallProgramMsg extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:nil bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
            return messageF;
        } else if ([model.messageType intValue] == 12) {//留言消息
            
            if (model.content == nil || model.content.length == 0) {
                return messageF;
            }
            
            NSString*jsonTextContent = @"";
            //        messageStr = model.content;
            NSError *err;
            NSData *leavejsonData = [model.content dataUsingEncoding:NSUTF8StringEncoding];
            NSDictionary *leavejsonDict = [NSJSONSerialization JSONObjectWithData:leavejsonData
                                                                          options:NSJSONReadingMutableContainers
                                                                            error:&err];
            if ([leavejsonDict isKindOfClass:[NSDictionary class]]) {
                NSArray* titleArr = [leavejsonDict allKeys];
                if (titleArr.count>0) {
                    for (NSString*key in titleArr) {
                        jsonTextContent = [NSString stringWithFormat:@"%@\n%@:%@",jsonTextContent,key,leavejsonDict[key]];
                    }
                }
            }
            jsonTextContent  = [jsonTextContent substringFromIndex:1];
            
            if (messageRecvDirection) {
                messageF = [ICMessageHelper createMessageFrame:TypeText originalType:@"text" content:jsonTextContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
                return messageF;
            } else {
                messageF = [ICMessageHelper createMessageFrame:TypeText originalType:@"text" content:jsonTextContent extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:YES receivedSenderByYourself:NO status:1 senderType:model.senderType];
                return messageF;
            }
            
            
        }else if ([model.messageType intValue] == 6  ||
                  [model.messageType intValue] == 15 ||//机器人相关问题
                  [model.messageType intValue] == 16 ||//机器人猜你想问(热门问题)
                  [model.messageType intValue] == 17 ||//机器人常见问题
                  [model.messageType intValue] == 18 ||//机器人近似问题
                  [model.messageType intValue] == 19 ||//机器人选项消息
                  [model.messageType intValue] == 20) {//机器人相关问题
            
            NSArray <CombinationMessage *>*message = [NSArray yy_modelArrayWithClass:[CombinationMessage class] json:model.content];
            
            [message enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                obj.robotProvider = model.robotProvider;
            }];
            
            BOOL isSender = messageRecvDirection?NO:YES;
            
            messageF = [ICMessageHelper createMessageFrame:TypeRobotCombination originalType:@"text" content:(NSString *)message extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:nil bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1 senderType:model.senderType];
            return messageF;
        }else if ([model.messageType intValue] == 14) {//机器人组合消息
            
            NSArray <CombinationMessage *>*message = [NSArray yy_modelArrayWithClass:[CombinationMessage class] json:model.content];
            
            [message enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                obj.robotProvider = model.robotProvider;
            }];
            
            BOOL isSender = messageRecvDirection?NO:YES;
            
            messageF = [ICMessageHelper createMessageFrame:TypeRobotCombination originalType:@"text" content:(NSString *)message extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:nil bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:isSender receivedSenderByYourself:NO status:1 senderType:model.senderType];
            return messageF;
        }else if ([model.messageType intValue] == 27) {//满意度评价
            if (model.content == nil || model.content.length == 0) {
                return messageF;
            }
            messageStr = model.content;
            
            NSString *extra = [kitUtils convertToJsonDataWithDic:@{@"alreadyInvestigation" : model.alreadyInvestigation?:@"", @"uniqueId": model.uniqueId?:@"",@"mainUniqueId": model.mainUniqueId?:@""}];
            NSString *content = [[OnlineDataSave shareOnlineDataSave] getAppSetting];
            messageF = [ICMessageHelper createMessageFrame:TypeInvestigation originalType:model.type content:content extra:extra auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:(NSTimeInterval)[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
            return messageF;
        } else if ([model.messageType intValue] == 30) {//
            
            TOSMessageTextTagModel *textTagModel = [TOSMessageTextTagModel yy_modelWithJSON:model.content];
            
            NSMutableArray <TOSMessageTextSubTagModel *>*tags = [NSMutableArray array];
            
            [textTagModel.data enumerateObjectsUsingBlock:^(NSString * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                
                TOSMessageTextSubTagModel *model = [TOSMessageTextSubTagModel yy_modelWithJSON:obj];
                [tags addObject:model];
            }];
            
            textTagModel.tags = [NSArray arrayWithArray:tags];
            
            TIMMessageFrame *messageF = [ICMessageHelper createMessageFrame:TypeTextTag originalType:model.type content:(NSString *)textTagModel extra:@"" auditExtra:@"" path:nil urlPath:nil from:model.sender to:model.visitorId fileKey:model.fileKey bigImgFileId:@"" voiceDuration:[NSNumber numberWithInt:0] msgId:model.uniqueId sendTime:[model.createTime  doubleValue] showTime:[self p_needShowTimeStamp:(NSTimeInterval)[model.createTime  doubleValue]] picSize:CGSizeMake(0, 0) picType:@"" isSender:NO receivedSenderByYourself:NO status:1 senderType:model.senderType];
        }
    
    return messageF;
}

    
- (BOOL)p_needShowTimeStamp:(NSTimeInterval)timestamp
{
    return NO;
}


@end
