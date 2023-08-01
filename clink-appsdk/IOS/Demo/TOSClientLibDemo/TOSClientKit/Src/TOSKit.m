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

#import "kitUtils.h"
#import "ICMediaManager.h"
#import <TOSClientLib/TOSClientLib.h>
#import "NSObject+TIMShowError.h"
#import <TOSClientLib/NSObject+TIMModel.h>
#import <TOSClientLib/OctoIMParamDefines.h>

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

/// 会话状态
@property (nonatomic, assign) TinetChatStatusType                chatStatusType;

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
        self.chatStatusType = TinetChatStatusTypeOutline;
        
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
    
    [[TIMClient sharedTIMClient] setTIMConnectionStatusChangeDelegate:self];
    [[OnlineRequestManager sharedCustomerManager] getUserInfoWithUserId:option.visitorId
                                                               nickname:option.nickname
                                                               phoneNum:option.mobile
                                                              headerUrl:option.headUrl
                                                         connectSuccess:^{
        NSLog(@"getUserInfoWithUserId connectSuccess");
        NSLog(@"链接成功");
        successBlock();
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        NSLog(@"getUserInfoWithUserId error");
        errorBlock(errCode,errorDes);
    } tokenIncorrect:^{
        NSLog(@"getUserInfoWithUserId tokenIncorrect");
        tokenIncorrectBlock();
    }];
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
        if (@available(iOS 10.0, *)) {
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
    if (!message.receiverId || message.receiverId.length < 2 || [self isUnSupportSessionType:message.msg_from]) {
        return ;
    }
    TIMKitLog(@"接收到新数据 msgId:%@ receiverId = %@ senderId = %@",message.msg_id,message.receiverId,message.senderId);
    
    /// 排队事件监听
    [self onlineQueues:message];
    
    /// 接通机器人
    if ([message.type isEqualToString:@"RobotBridgeMessage"]) {
        self.chatStatusType = TinetChatStatusTypeRobot;
        if (self->reckitMessageDelegate && [self->reckitMessageDelegate respondsToSelector:@selector(getCurrentOnlineStatus:)]) {
            [self->reckitMessageDelegate getCurrentOnlineStatus:(TinetChatStatusTypeRobot)];
        }
    }
    /// 接通客服
    else if ([message.type isEqualToString:@"ChatBridgeMessage"]) {
        self.chatStatusType = TinetChatStatusTypeOnline;
        if (self->reckitMessageDelegate && [self->reckitMessageDelegate respondsToSelector:@selector(getCurrentOnlineStatus:)]) {
            [self->reckitMessageDelegate getCurrentOnlineStatus:(TinetChatStatusTypeOnline)];
        }
    }
    /// 满意度评价
    else if ([message.type isEqualToString:@"ChatInvestigationMessage"]) {
        ChatInvestigationMessage * InvestigationMsg = (ChatInvestigationMessage *)message.content;
        if ([InvestigationMsg.isClose boolValue]) {
            self.chatStatusType = TinetChatStatusTypeOutline;
            if (self->reckitMessageDelegate && [self->reckitMessageDelegate respondsToSelector:@selector(getCurrentOnlineStatus:)]) {
                [self->reckitMessageDelegate getCurrentOnlineStatus:(TinetChatStatusTypeOutline)];
            }
        }
    }
    /// 会话结束
    else if ([message.type isEqualToString:@"ChatCloseMessage"]) {
        self.chatStatusType = TinetChatStatusTypeOutline;
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
    NSString * lastMessageStr = @"";
    switch (messageType) {
        case 1: {
            TextMessage * textMsg = (TextMessage *)message.content;
            lastMessageStr = textMsg.textContent;
            self.unReadCount += 1;
            _lastMessage = message;
            NSLog(@"testMessage：%i", _lastMessage.messageType);
            break;
        }
        case 2: {
            lastMessageStr = @"【图片】";
            self.unReadCount += 1;
            _lastMessage = message;
            break;
        }
        case 3: {
            lastMessageStr = @"【文件】";
            self.unReadCount += 1;
            _lastMessage = message;
            break;
        }
        case 4: {
            lastMessageStr = @"【视频】";
            self.unReadCount += 1;
            _lastMessage = message;
            break;
        }
        case 5: {
            lastMessageStr = @"【机器人富文本】";
            self.unReadCount += 1;
            _lastMessage = message;
            break;
        }
        case 7: {
            lastMessageStr = @"【语音】";
            self.unReadCount += 1;
            _lastMessage = message;
            break;
        }
        case 10: {
            lastMessageStr = @"【卡片】";
            self.unReadCount += 1;
            _lastMessage = message;
            break;
        }
        default:
            break;
    }
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
        if ([InvestigationMsg.isClose boolValue] && self.chatStatusType == TinetChatStatusTypeRobot) {
            /// 排队监听 退出排队
            if (self->onlineQueueDelegate && [self->onlineQueueDelegate respondsToSelector:@selector(exitChatQueue)]) {
                [self->onlineQueueDelegate exitChatQueue];
            }
        }
    }
    if ([message.type isEqualToString:@"ChatCloseMessage"] && self.chatStatusType == TinetChatStatusTypeRobot ) {
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

// 静态方法
+ (NSString *)getSDKVersion {
    return strSDKVersion;
}

- (void)initSDK:(TOSInitOption *)option{
    self->initOSOption = option;
    [[OnlineInitOption shareOnlineInitOption] initWithOptionIsDebug:option.debug
                                                             apiUrl:option.apiUrl
                                                          onlineUrl:option.onlineUrl
                                                       accessSecret:option.accessSecret
                                                           accessId:option.accessId
                                                       enterpriseId:option.enterpriseId];
}

/// 获取当前在线状态
- (TinetChatStatusType)getCurrentOnlineStatus {
    return self.chatStatusType;
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

@end
