//
//  AppDelegate.m
//  TIMClientKitDemo
//
//  Created by YanBo on 2020/5/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "AppDelegate.h"
#import "LoginViewController.h"
#ifdef NSFoundationVersionNumber_iOS_9_x_Max
#import <UserNotifications/UserNotifications.h>
#endif
#import <AudioToolbox/AudioToolbox.h>
#import "IQKeyboardManager.h"

@interface AppDelegate ()<UNUserNotificationCenterDelegate>

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
 

//    //共达
//    [[OnlineInitOption shareOnlineInitOption] initWithOptionIsDebug:YES
//                                                                 apiUrl:@"https://octopus-api-1.vlink.cn/api/sdk/v1"
//                                                              onlineUrl:@"https://chat-app-bj.clink.cn"
//                                                           accessSecret:@"0B2A488841FC44C899FC76C5EBEF6491"
//                                                               accessId:@"56ab9623cf2840a88aa0897f6da6ef25"
//                                                           enterpriseId:@"8004022"];
    
    //天润测试
//    [[OnlineInitOption shareOnlineInitOption] initWithOptionIsDebug:YES
//                                                             apiUrl:@"https://octopus-api-1.vlink.cn/api/sdk/v1"
//                                                          onlineUrl:@"http://chat-app-bj-test3.clink.cn"
//                                                       accessSecret:@"9999D7A14DAA4295A568BB0854FA6666"
//                                                           accessId:@"9999d7a14daa4295a568bb0854fa6666"
//                                                       enterpriseId:@"8000581"];
    

    
    //天润生产
//    [[OnlineInitOption shareOnlineInitOption] initWithOptionIsDebug:YES
//                                                             apiUrl:@"https://octopus-api-1.vlink.cn/api/sdk/v1"
//                                                          onlineUrl:@"https://chat-app-bj-test0.clink.cn"
//                                                       accessSecret:@"8809B37AA79C48C691EB18638DBF76F7"
//                                                           accessId:@"c149815c989f4d86a7b5a76d0262b879"
//                                                       enterpriseId:@"8000559"];
   
    //天润上海环境
//    [[OnlineInitOption shareOnlineInitOption] initWithOptionIsDebug:YES
//                                                             apiUrl:@"https://octopus-api-1.vlink.cn/api/sdk/v1"
//                                                          onlineUrl:@"https://chat-app-sh.clink.cn"
//                                                       accessSecret:@"50ECA889F8F04EF29EE53FAEB009FBAE"
//                                                           accessId:@"b9a4b617017f4316bb567e69fa5e685b"
//                                                       enterpriseId:@"8003846"];
    
    //天润北京环境
    [[OnlineInitOption shareOnlineInitOption] initWithOptionIsDebug:YES
                                                             apiUrl:@"https://octopus-api-1.vlink.cn/api/sdk/v1"
                                                          onlineUrl:@"https://chat-app-bj.clink.cn"
                                                       accessSecret:@"72EBF29CB4614F7AB404EEC07BFF0B1B"
                                                           accessId:@"8758096679544ff189d4a9457747f109"
                                                       enterpriseId:@"8000002"];
    
    //企知道
//    [[OnlineInitOption shareOnlineInitOption] initWithOptionIsDebug:YES
//                                                             apiUrl:@"https://octopus-api-1.vlink.cn/api/sdk/vi"
//                                                          onlineUrl:@"https://chat-app-sh.clink.cn"
//                                                       accessSecret:@"53FFAECE8D7E4EA8813F35E42F28A8CD"
//                                                           accessId:@"8ededc2efa82426790af86c055bbd8e3"
//                                                       enterpriseId:@"8006597"];

    
    
//    //万顺测试
//    [[OnlineInitOption shareOnlineInitOption] initWithOptionIsDebug:YES
//                                                             apiUrl:@"https://tcbus-api-dev.vlink.cn/api/sdk/v1"
//                                                          onlineUrl:@"https://chat-app-bj-test3.clink.cn"
//                                                       accessSecret:@"123DD7A14DAA4295A568BB0854FA8123"
//                                                           accessId:@"086dd7a14daa4295a568bb0854fa8d65"
//                                                       enterpriseId:@"8000581"];
    
//    //万顺正式
//    [[OnlineInitOption shareOnlineInitOption] initWithOptionIsDebug:NO
//                                                             apiUrl:@"https://octopus-api-1.vlink.cn/api/sdk/v1"
//                                                          onlineUrl:@"https://chat-app-bj.clink.cn"
//                                                       accessSecret:@"C49E892CDB474D8AAFA275DE02221822"
//                                                           accessId:@"b7da415ffef74071a43452624e08b157"
//                                                       enterpriseId:@"8004622"];
    
 
    
    NSDictionary* pushNotificationKey = [launchOptions objectForKey:UIApplicationLaunchOptionsRemoteNotificationKey];
    NSLog(@"程序未启动接收的推送:");
    for (NSString *key in pushNotificationKey) {
        NSLog(@"key:%@ value:%@",key,pushNotificationKey[key]);
    }
    // Bugly
//    [Bugly startWithAppId:@"5ba9c13d26"];
    // Override point for customization after application launch.
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

    //设置window
    self.window  = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    self.window.backgroundColor = [UIColor whiteColor];
    [self.window makeKeyAndVisible];
    self.window.rootViewController = [[LoginViewController alloc] initWithNibName:[LoginViewController className] bundle:nil];


    //第三方键盘管理
    IQKeyboardManager *keyboardManager = [IQKeyboardManager sharedManager]; // 获取类库的单例变量
    keyboardManager.enable = YES; // 控制整个功能是否启用
    keyboardManager.shouldResignOnTouchOutside = YES; // 控制点击背景是否收起键盘
    keyboardManager.shouldToolbarUsesTextFieldTintColor = YES; // 控制键盘上的工具条文字颜色是否用户自定义
    keyboardManager.toolbarManageBehaviour = IQAutoToolbarBySubviews; // 有多个输入框时，可以通过点击Toolbar 上的“前一个”“后一个”按钮来实现移动到不同的输入框
    keyboardManager.enableAutoToolbar = YES; // 控制是否显示键盘上的工具条
    keyboardManager.shouldShowToolbarPlaceholder = YES;// 是否显示占位文字
    keyboardManager.placeholderFont = [UIFont systemFontOfSize:15]; // 设置占位文字的字体
    keyboardManager.keyboardDistanceFromTextField = 10.f; // 输入框距离键盘的距离
    
//    [IQKeyboardManager sharedManager].enableAutoToolbar = NO;//禁用键盘上的toolbar 也就是按钮
//        [IQKeyboardManager sharedManager].enable = NO;// 禁止键盘出现时的界面滚动
    
    return YES;
}


#pragma mark - UISceneSession lifecycle


//- (UISceneConfiguration *)application:(UIApplication *)application configurationForConnectingSceneSession:(UISceneSession *)connectingSceneSession options:(UISceneConnectionOptions *)options  API_AVAILABLE(ios(13.0)){
//    // Called when a new scene session is being created.
//    // Use this method to select a configuration to create the new scene with.
//    return [[UISceneConfiguration alloc] initWithName:@"Default Configuration" sessionRole:connectingSceneSession.role];
//}


//- (void)application:(UIApplication *)application didDiscardSceneSessions:(NSSet<UISceneSession *> *)sceneSessions  API_AVAILABLE(ios(13.0)){
//    // Called when the user discards a scene session.
//    // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
//    // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
//}

#pragma mark - 注册推送回调获取 DeviceToken
- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken {
    
    NSLog(@"注册推送成功 %@",deviceToken);
    [[TOSClientKit sharedTOSKit] setDeviceTokenData:deviceToken];
}

- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error {
    // 注册失败
    NSLog(@"did Fail To Register For Remote Notifications With Error: %@", error);
}

#pragma mark - function 2 iOS 10之前以前的用户
- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo fetchCompletionHandler:(void (^)(UIBackgroundFetchResult))completionHandler {
    NSLog(@"收到的远端推送消息 = %@",userInfo);
    completionHandler(UIBackgroundFetchResultNewData);
}

#pragma mark - function 3 iOS10及以后的用户
- (void)userNotificationCenter:(UNUserNotificationCenter *)center willPresentNotification:(UNNotification *)notification withCompletionHandler:(void (^)(UNNotificationPresentationOptions))completionHandler{
     //......程序在前台时收到推送执行的操作 包含离线和本地推送
    NSLog(@"willPresentNotification");
    completionHandler(UNNotificationPresentationOptionSound |  UNNotificationPresentationOptionBadge | UNNotificationPresentationOptionAlert);
}

#pragma mark - function 4 iOS10及以后的用户
- (void)userNotificationCenter:(UNUserNotificationCenter *)center didReceiveNotificationResponse:(UNNotificationResponse *)response withCompletionHandler:(void(^)(void))completionHandler {
    NSLog(@"didReceiveNotificationResponse");
    NSDictionary * userInfo = response.notification.request.content.userInfo;
    for (NSString *key in userInfo) {
        NSLog(@"key:%@ value:%@",key,userInfo[key]);
    }
    completionHandler();
}


+ (AppDelegate* )shareAppDelegate {
    return (AppDelegate*)[UIApplication sharedApplication].delegate;
}


@end
