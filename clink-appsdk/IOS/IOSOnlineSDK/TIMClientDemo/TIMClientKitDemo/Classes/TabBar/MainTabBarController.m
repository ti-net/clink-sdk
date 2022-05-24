//
//  MainTabBarController.m
//  TIMClientDemo
//
//  Created by YanBo on 2020/3/28.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "MainTabBarController.h"
#import "TIMBaseNavigationController.h"
#import "appUtils.h"


#define kClassKey   @"rootVCClassString"
#define kTitleKey   @"title"
#define kImgKey     @"imageName"
#define kSelImgKey  @"selectedImageName"

#import "kefuVC.h"


@interface MainTabBarController ()<TIMAuditMessageSuccessDelegate,
//TIMCustomMessageWillSendDelegate,
TIMCustomMessageClickDelegate,
TIMRTCMediaMessageDelegate>

@end

@implementation MainTabBarController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
  NSArray *childItemsArray = @[
      
                              @{kClassKey  : @"kefuVC",
                                kTitleKey  : @"客服",
                                kImgKey    : @"tabbar_contacts",
                                kSelImgKey : @"tabbar_contactsHL"},
//                                @{kClassKey  : @"sessionListViewController",
//                                  kTitleKey  : @"消息",
//                                  kImgKey    : @"tabbar_mainframe",
//                                  kSelImgKey : @"tabbar_mainframeHL"},
//
//                                @{kClassKey  : @"TIMContactsViewController",
//                                  kTitleKey  : @"通讯录",
//                                  kImgKey    : @"tabbar_contacts",
//                                  kSelImgKey : @"tabbar_contactsHL"},
//
//                                @{kClassKey  : @"SDDiscoverTableViewController",
//                                  kTitleKey  : @"发现",
//                                  kImgKey    : @"tabbar_discover",
//                                  kSelImgKey : @"tabbar_discoverHL"},
 
//                                @{kClassKey  : @"MineViewController",
//                                  kTitleKey  : @"我",
//                                  kImgKey    : @"tabbar_me",
//                                  kSelImgKey : @"tabbar_meHL"}
  ];
   
   [childItemsArray enumerateObjectsUsingBlock:^(NSDictionary *dict, NSUInteger idx, BOOL *stop) {
       UIViewController *vc = [NSClassFromString(dict[kClassKey]) new];
       vc.title = dict[kTitleKey];
       TIMBaseNavigationController *nav = [[TIMBaseNavigationController alloc] initWithRootViewController:vc];
       UITabBarItem *item = nav.tabBarItem;
       item.title = dict[kTitleKey];
       item.image = [UIImage imageNamed:dict[kImgKey]];
       item.selectedImage = [[UIImage imageNamed:dict[kSelImgKey]] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
       [item setTitleTextAttributes:@{NSForegroundColorAttributeName : Global_tintColor} forState:UIControlStateSelected];
       [self addChildViewController:nav];
   }];
    
//    [self configIM];
}


//- (void)configIM {
//
//    if (1) {
//        DomainNameSave *domainName = [DomainNameSave shareDomainNameSave];
//        BOOL bIsApi2 = [domainName.domainName isEqualToString:@"测试"]?NO:YES;
//        // 初始化SDK
//        TIMInitOption *initOption = [[TIMInitOption alloc] initWithOption:YES apiUrl:domainName.IMloginDomainName isApiVersion2:bIsApi2];
//        [[TIMKit sharedTIMKit] initWithOption:initOption];
//        //    [TIMKit sharedTIMKit].disableMessageNotificaiton = YES;  // 取消本地推送
//        [TIMKit sharedTIMKit].disableMessageAlertSound = YES;  // 取消本地推送声音
//        [TIMKit sharedTIMKit].auditMessageSuccessDelagate = self;
////        [TIMKit sharedTIMKit].customMessageWillSendDelagate = self;
//        [TIMKit sharedTIMKit].customMessageClickDelagate = self;
//        [TIMKit sharedTIMKit].rtcMediaMessageDelagate = self;
//        [TIMKit sharedTIMKit].customerKitUIType = TIMKitUIDefaultType;
//        if ([domainName.domainName isEqualToString:@"测试"]) {
//            [self.getTIMAccessTokenVM requestData];
//        }else{
//            [self.getTcbusAccessTokenVM requestData];
//        }
//    }
//
//}

//- (void)onSuccess:(NSString *)groupId{
//    NSLog(@"AuditOnSuccess === %@",groupId);
//}

// 将发送自定义消息
//- (void)onWillSend:(ICChatBoxItem)item{
//    NSLog(@"自定义消息类型 %ld",(long)item);
//    NSMutableAttributedString*abs = [[NSMutableAttributedString alloc] initWithString:@"测试富文本[微笑]"];
//    [abs addAttribute:NSBackgroundColorAttributeName value:[UIColor redColor] range:NSMakeRange(0,3)];
//
//    TIMCustomizeMessage * willSend;
//    if (item == ICChatBoxItemCustomFile) {
//        willSend = [[TIMCustomizeMessage alloc] initMessageWithBody:@"tim-bmwReportFile" body:@{
//            @"fileTypeDesc": @"车辆健康检查报告",
//            @"fileType": @"pdf",
//            @"fileName": @"VCIC_IM_001.pdf",
//            @"createTime": @"2021-03-04",
//        } extra:@""];
//    } else {
//        willSend = [[TIMCustomizeMessage alloc] initMessageWithBody:@"default" imgUrl:@"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" shareTitle:@"分享主题" title:@"主题" content:@"<h2>全场<span style=\"font-size:24px\"><span style=\"color:#e74c3c\">99</span></span><span style=\"color:#e74c3c\">元</span>起，另有特价<u><em><strong><span style=\"color:#e74c3c\">8</span></strong></em></u>折优惠，快来抢购，来不来？</h2>" extra:@"附加信息"];
//    }
//    NSLog(@"将发送自定义消息");
//    TIMMessage * timMsg = [[TIMMessage alloc] initWithOption:[[NSUUID UUID] UUIDString] msg_id:@"" type:@"customize" senderId:[LoginModel loginModel].timUserId receiverId:@"gaoyb7" content:willSend msg_from:TIMSessionType_SINGLE_CHAT status:TIMMessageStatus_Sending timestamp:0];
//    TIMMessagePushOption * pushOption = [[TIMMessagePushOption alloc] initWithOption:NO title:@"" content:@"[文件]" pushData:nil];
//    TIMMessageSendOption * sendOption = [[TIMMessageSendOption alloc] initWithOption:@"gaoyb1" content:timMsg pushOption:pushOption];
//
//
//     [[TIMKit sharedTIMKit] sendMessage:sendOption  progress:^(float progress) {
//                NSLog(@"send customize progress = %f",progress);
//            } success:^(TIMMessage * timMessage){
//                NSLog(@"send customize success messageId = %@",timMessage.msg_id);
//            } error:^(TIMMessage *message, TIMConnectErrorCode nErrorCode, NSString * _Nonnull errorDes) {
//                NSLog(@"send customize message = %@ nErrorCode = %ld errorDes= %@",message,(long)nErrorCode,errorDes);
//            }];
//}

// 点击自定义消息
//- (void)onClickCustomMessage:(TIMMessage *)timMessage{
//
//    TIMCustomizeMessage * custMessage = (TIMCustomizeMessage *)timMessage.content;
//    NSLog(@"点击自定义消息");
//}


/*
 回调
 */
//- (void)onRtcMediaMessage:(BOOL)onlyAudio receiveId:(NSString*)receiveId{
//    if (onlyAudio) {
////        [[TRTCKit sharedTRTCKit] startSingleCall:receiveId mediaType:TRTCMediaAudioType];
//    } else {
////        [[TRTCKit sharedTRTCKit] startSingleCall:receiveId mediaType:TRTCMediaVideoType];
//    }
//}

- (void)dealloc {
    NSLog(@"tab vc dealloc!!!!!");
}

@end
