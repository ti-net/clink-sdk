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
#import "AppDelegate.h"
#import "LoginModel.h"
#import "LoginViewModel.h"
#import "LoginInfoSave.h"
#import "GetTIMAccessTokenViewModel.h"
#import "GetTcbusAccessTokenViewModel.h"

#define kClassKey   @"rootVCClassString"
#define kTitleKey   @"title"
#define kImgKey     @"imageName"
#define kSelImgKey  @"selectedImageName"

#import "kefuVC.h"

@interface MainTabBarController () <TIMAuditMessageSuccessDelegate,
TIMCustomMessageClickDelegate,
TIMRTCMediaMessageDelegate>

@property (strong, nonatomic) GetTIMAccessTokenViewModel *getTIMAccessTokenVM;
@property (strong, nonatomic) GetTcbusAccessTokenViewModel *getTcbusAccessTokenVM;

@property (strong, nonatomic) NSMutableArray <NSString *>*childViewCs;

@property (strong, nonatomic) LoginViewModel *viewModel;

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
    
    [self configIM];
}

- (void)configIM {
    
    if (1) {
        DomainNameSave *domainName = [DomainNameSave shareDomainNameSave];
        BOOL bIsApi2 = [domainName.domainName isEqualToString:@"测试"]?NO:YES;
        // 初始化SDK
        TIMInitOption *initOption = [[TIMInitOption alloc] initWithOption:YES apiUrl:domainName.IMloginDomainName isApiVersion2:bIsApi2];
        [[TIMKit sharedTIMKit] initWithOption:initOption];
        //    [TIMKit sharedTIMKit].disableMessageNotificaiton = YES;  // 取消本地推送
        [TIMKit sharedTIMKit].disableMessageAlertSound = YES;  // 取消本地推送声音
        [TIMKit sharedTIMKit].auditMessageSuccessDelagate = self;
//        [TIMKit sharedTIMKit].customMessageWillSendDelagate = self;
        [TIMKit sharedTIMKit].customMessageClickDelagate = self;
        [TIMKit sharedTIMKit].rtcMediaMessageDelagate = self;
        [TIMKit sharedTIMKit].customerKitUIType = TIMKitUIDefaultType;
        if ([domainName.domainName isEqualToString:@"测试"]) {
            [self.getTIMAccessTokenVM requestData];
        }else{
            [self.getTcbusAccessTokenVM requestData];
        }
    }
 
}

- (void)setTabBarControllerWithArray:(NSArray *)childItemsArray {
    
    [childItemsArray enumerateObjectsUsingBlock:^(NSDictionary *dict, NSUInteger idx, BOOL *stop) {
        UIViewController *vc = [NSClassFromString(dict[kClassKey]) new];
        vc.title = dict[kTitleKey];
        
        
        UIImage *normalTabImage = [UIImage imageNamed:dict[kImgKey]];
        UIImage *selectedTabImage = [UIImage imageNamed:dict[kSelImgKey]];
        UITabBarItem *tabbarItem = [[UITabBarItem alloc] initWithTitle:dict[kTitleKey] image:normalTabImage selectedImage:selectedTabImage];
        [tabbarItem setTitleTextAttributes:@{NSForegroundColorAttributeName:kHexColor(0xBDCADB),NSFontAttributeName: [UIFont fontWithName:kFontNameRegular size:11.f]} forState:UIControlStateNormal];
        [tabbarItem setTitleTextAttributes:@{NSForegroundColorAttributeName:kHexColor(0x2373D9),NSFontAttributeName: [UIFont fontWithName:kFontNameRegular size:11.f]} forState:UIControlStateSelected];
        [tabbarItem setTitlePositionAdjustment:UIOffsetMake(0, -2)];
        vc.tabBarItem = tabbarItem;
        
        
        UINavigationController *nav;
//        if ([dict[kTitleKey] isEqualToString:@"消息"]) {
//            nav = [[UINavigationController alloc] initWithRootViewController:vc];
//        } else {
            nav = [[TIMBaseNavigationController alloc] initWithRootViewController:vc];
//        }
        nav.modalPresentationStyle = UIModalPresentationFullScreen;
        
        NSMutableArray *array = [NSMutableArray arrayWithArray:self.childViewControllers];
        if (!array || array.count <= 0) {
            [self addChildViewController:nav];
        } else {
            [array insertObject:nav atIndex:0];
            [self setViewControllers:array animated:YES];
        }
    }];
}

#pragma mark - TIMAuditMessageSuccessDelegate
- (void)onSuccess:(NSString *)groupId{
    NSLog(@"AuditOnSuccess === %@",groupId);
}

// 将发送自定义消息
//- (void)onWillSend:(ICChatBoxItem)item{
//    NSLog(@"自定义消息类型 %ld",(long)item);
//
//    TIMCustomizeMessage * willSend;
//    if (item == ICChatBoxItemCustomFile) {
//        //@"tim-bmwReportFile"
//        willSend = [[TIMCustomizeMessage alloc] initMessageWithBody:@"dsadasd" body:@{
//            @"fileTypeDesc": @"车辆健康检查报告",
//            @"fileType": @"pdf",
//            @"fileName": @"VCIC_IM_001.pdf",
//            @"createTime": @"2021-03-04",
//        } extra:@""];
//    } else {
//        willSend = [[TIMCustomizeMessage alloc] initMessageWithBody:@"default" imgUrl:@"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" shareTitle:@"分享主题" title:@"主题" content:@"<h2>全场<span style=\"font-size:24px\"><span style=\"color:#e74c3c\">99</span></span><span style=\"color:#e74c3c\">元</span>起，另有特价<u><em><strong><span style=\"color:#e74c3c\">8</span></strong></em></u>折优惠，快来抢购，来不来？</h2>" extra:@"附加信息"];
//    }
//    NSLog(@"将发送自定义消息");
//    TIMMessage * timMsg = [[TIMMessage alloc] initWithOption:[[NSUUID UUID] UUIDString] msg_id:@"" type:@"customize" senderId:[LoginModel loginModel].timUserId receiverId:@"gaoyb1" content:willSend msg_from:TIMSessionType_SINGLE_CHAT status:TIMMessageStatus_Sending timestamp:0];
//    TIMMessagePushOption * pushOption = [[TIMMessagePushOption alloc] initWithOption:NO title:@"" content:@"[自定义消息]" pushData:nil];
//    TIMMessageSendOption * sendOption = [[TIMMessageSendOption alloc] initWithOption:@"gaoyb1" content:timMsg pushOption:pushOption];
//
//
//    [[TIMKit sharedTIMKit] sendMessage:sendOption  progress:^(float progress) {
//        NSLog(@"send customize progress = %f",progress);
//    } success:^(TIMMessage * timMessage){
//        NSLog(@"send customize success messageId = %@",timMessage.msg_id);
//    } error:^(TIMMessage *message, TIMConnectErrorCode nErrorCode, NSString * _Nonnull errorDes) {
//        NSLog(@"send customize message = %@ nErrorCode = %ld errorDes= %@",message,(long)nErrorCode,errorDes);
//    }];
//}

@end
