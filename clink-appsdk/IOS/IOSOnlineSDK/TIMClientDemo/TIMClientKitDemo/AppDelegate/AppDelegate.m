//
//  AppDelegate.m
//  TIMClientKitDemo
//
//  Created by YanBo on 2020/5/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "AppDelegate.h"
#import "LoginModel.h"
#import "YTKNetworkConfig.h"
#import "LoginViewController.h"
#ifdef NSFoundationVersionNumber_iOS_9_x_Max
#import <UserNotifications/UserNotifications.h>
#endif
#import <AudioToolbox/AudioToolbox.h>
#import <Bugly/Bugly.h>
#import <IQKeyboardManager/IQKeyboardManager.h>
#import "ChatInfoViewController.h"



//#ifdef DEBUG
//#import <DoraemonKit/DoraemonManager.h>
//#endif

@interface AppDelegate ()<UNUserNotificationCenterDelegate>

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    
    //初始化
    [[OnlineInitOption shareOnlineInitOption] initWithOptionIsDebug:YES
                                                                 apiUrl:@"https://octopus-api-1.vlink.cn/api/sdk/v1"
                                                              onlineUrl:@"https://chat-app-bj.clink.cn"
                                                           accessSecret:@"0B2A488841FC44C899FC76C5EBEF6491"
                                                               accessId:@"56ab9623cf2840a88aa0897f6da6ef25"
                                                           enterpriseId:@"8004022"];
    
//    [[OnlineInitOption shareOnlineInitOption] initWithOptionIsDebug:YES
//                                                             apiUrl:@"https://octopus-api-1.vlink.cn/api/sdk/v1"
//                                                          onlineUrl:@"https://chat-app-sh.clink.cn"
//                                                       accessSecret:@"07871659F8E144BDB9B72D667E06D175"
//                                                           accessId:@"b7a28d0409d44b1c830810c84028bdef"
//                                                       enterpriseId:@"8005713"];
    
    [application ignoreSnapshotOnNextApplicationLaunch];
    if (@available(iOS 10.0,*)){
            //10.0以后的注册通知方法
            UNUserNotificationCenter *center = [UNUserNotificationCenter currentNotificationCenter];
            //请求通知的权限
            [center requestAuthorizationWithOptions:(UNAuthorizationOptionBadge|UNAuthorizationOptionSound|UNAuthorizationOptionAlert)
                                  completionHandler:^(BOOL granted, NSError * _Nullable error) {
                if (granted) {
                    NSLog(@"成功:%@",error);
                }else{
                    NSLog(@"失败");
                }
            }];
            center.delegate = self;
            [application registerForRemoteNotifications];
    }
    
    if (@available(iOS 13.0, *)) {
        [UIApplication sharedApplication].statusBarStyle = UIStatusBarStyleDarkContent;
    } else {
        [UIApplication sharedApplication].statusBarStyle = UIStatusBarStyleDefault;
    }
    
    //第三方键盘管理
    IQKeyboardManager *keyboardManager = [IQKeyboardManager sharedManager]; // 获取类库的单例变量
    keyboardManager.enable = YES; // 控制整个功能是否启用
    keyboardManager.shouldResignOnTouchOutside = YES; // 控制点击背景是否收起键盘
    keyboardManager.shouldToolbarUsesTextFieldTintColor = YES; // 控制键盘上的工具条文字颜色是否用户自定义
    keyboardManager.toolbarManageBehaviour = IQAutoToolbarBySubviews; // 有多个输入框时，可以通过点击Toolbar 上的“前一个”“后一个”按钮来实现移动到不同的输入框
    keyboardManager.enableAutoToolbar = YES; // 控制是否显示键盘上的工具条
    keyboardManager.shouldShowToolbarPlaceholder = YES;// 是否显示占位文字
    keyboardManager.placeholderFont = [UIFont fontWithName:kFontNameRegular size:16.f]; // 设置占位文字的字体
    keyboardManager.keyboardDistanceFromTextField = 10.f; // 输入框距离键盘的距离

    //设置window
    self.window  = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    self.window.backgroundColor = [UIColor whiteColor];
    [self.window makeKeyAndVisible];

    
    if ([LoginModel loginModel].isLogin) {
        MainTabBarController *tabBarC = [[MainTabBarController alloc] init];
        tabBarC.selectedIndex = 0;
        self.window.rootViewController = tabBarC;
    } else {
        self.window.rootViewController = [[LoginViewController alloc] initWithNibName:[LoginViewController className] bundle:nil];
    }
    
    return YES;
}


#pragma mark - 注册推送回调获取 DeviceToken
- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken {
    
    NSLog(@"注册推送成功 %@",deviceToken);
    [[TIMKit sharedTIMKit] setDeviceTokenData:deviceToken];
}

- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error {
    // 注册失败
    NSLog(@"did Fail To Register For Remote Notifications With Error: %@", error);
}

- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo fetchCompletionHandler:(void (^)(UIBackgroundFetchResult))completionHandler {
    NSLog(@"收到的远端推送消息 = %@",userInfo);
    completionHandler(UIBackgroundFetchResultNewData);
}

-(void)userNotificationCenter:(UNUserNotificationCenter *)center didReceiveNotificationResponse:(nonnull UNNotificationResponse *)response withCompletionHandler:(nonnull void (^)(void))completionHandler {
//    TIMMessage *message = [TIMMessage yy_modelWithDictionary:response.notification.request.content.userInfo];
//    
//    if (message && arc4random()%100 % 2 == 0) {
//        TIMSession *session = [[TIMSession alloc] init];
//        session.sessionType = message.msg_from;
//        
//        NSString *userId = [TIMKit sharedTIMKit].curUserId;
//        session.userId = userId;
//        
//        NSString *targetId;
//        if ([userId isEqualToString:message.senderId]) {
//            targetId = message.receiverId;
//        } else {
//            targetId = message.senderId;
//        }
//        
//        session.targetId = targetId;
//        session.priority = 0;
//        session.sessionTitle = @"";
//        session.messageStatus = message.status;
//        session.unreadCount = 0;
//        session.isContact = -1;
//        session.sendTime = message.timestamp;
//        session.latestMessage = [[TIMMessageContent alloc] init];
//        session.lastMessageId = message.msg_id;
//        session.lastMessageDesc = @"";
//        session.lastMessageType = @"text";
//        session.portraitUrl = @"";
//        
//        ChatInfoViewController *chatVC = [[ChatInfoViewController alloc] init];
//        chatVC.session = session;
//        [[TRCommonTool getCurrentVC].navigationController pushViewController:chatVC animated:YES];
//    }
}

+ (AppDelegate* )getShareAppDelegate {
    return (AppDelegate*)[UIApplication sharedApplication].delegate;
}

- (void)userNotificationCenter:(UNUserNotificationCenter *)center willPresentNotification:(UNNotification *)notification withCompletionHandler:(void (^)(UNNotificationPresentationOptions))completionHandler{
     //......程序在前台时收到推送执行的操作 包含离线和本地推送
    NSLog(@"willPresentNotification");
    completionHandler(UNNotificationPresentationOptionSound |  UNNotificationPresentationOptionBadge | UNNotificationPresentationOptionAlert);
}

@end
