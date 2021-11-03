## 使用说明
欢迎使用“智慧服务平台”的在线客服移动端开发者工具套件（SDK）。本文针对IOS端使用做详细说明，通过SDK，可以在您的APP中快速集成访客端在线聊天的功能，以具备文本、图片、视频等类型消息收发及消息通知能力。
## 开发环境


- 适配IOS 10.0以上
- 开发工具:XCode 13
- macOS: 11.6.1



### 1.申请AppKey
accessId   //移动端唯一标识，对应座席端渠道ID
accessSecret  //加密信息
enterpriseId  //企业ID
### 2.接入Online SDK


1. 在工程项目中添加以下包:

TIMClientLib.framework
TIMClientKit.framework
WebP.framework
TIMClient.bundle
另外需要增加libc++.tbd来支持c++环境
​


2.  在AppDelegate中添加以下代码



```objectivec
AppDelegate.h

@interface AppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;

+ (AppDelegate* )shareAppDelegate;
@end

AppDelegate.m

+ (AppDelegate* )shareAppDelegate {
    return (AppDelegate*)[UIApplication sharedApplication].delegate;
}

//设置window
self.window  = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
```

3.  初始化SDK
3.1. 初始化
```objectivec
- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    // 添加参数 
    // 请关注北京平台/上海平台
     //初始化
    [[OnlineInitOption shareOnlineInitOption] initWithOptionIsDebug:YES
                                                             apiUrl:@"IM服务器地址"
                                                          onlineUrl:@"客服地址"
                                                       accessSecret:@"在座席端管理平台创建渠道时生成" 
     													   accessId:@"移动端唯一标识，对应座席端渠道ID"
                                                       enterpriseId:@"企业id"];
}
```
3.2.  在Info.plist中添加Online SDK所需要的权限  
```objectivec
Privacy - Camera Usage Description

Privacy - Microphone Usage Description

Privacy - Photo Library Additions Usage Description

Privacy - Photo Library Usage Description
```


### 3.接入Online Push，获取deviceToken上传到IM服务器。并且把苹果推送的P12证书放入后台服务器根目录


```objectivec
#pragma mark - 注册推送回调获取 DeviceToken
- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken {
    
    NSLog(@"注册推送成功 %@",deviceToken);
    [[TIMKit sharedTIMKit] setDeviceTokenData:deviceToken];
}
```


### 4.客服会话页面继承TIMCustomerChatVC类


```objectivec
#import <TIMClientKit/TIMClientKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface ChatInfoViewController : TIMCustomerChatVC

@end


```


### 5. 接通客服并创建会话


连接在线客服，同一用户多次调用connect不会触发多次连接，id为用户侧的用户ID（可为空，为空的情况下，系统默认为UUID去除-号，不可包含中文或特殊符号，建议使用用户系统ID方便APP拓展功能），nickname为用户昵称（可为空），headUrl用户头像全链接地址（可为空）。


```objectivec
1://连接客服
[[OnlineRequestManager sharedCustomerManager] getUserInfoWithUserId:visitorId
                                                           nickname:name
                                                          headerUrl:headerUrl
                                                     connectSuccess:^{
    NSLog(@"链接成功");

} error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
    [self showMBErrorView:@"网络请求错误，请检查网络"];
} tokenIncorrect:^{
    [self showMBErrorView:@"accessToken不正确"];
}];

2:/*创建会话
 当APP端IM mqtt 连接就绪，主动通知服务端，APP端调用该接口后服务端
 就开始会话流程逻辑创建会话*/
[[OnlineRequestManager sharedCustomerManager]visitorReadyWithSuccess:^(NSString * _Nonnull mainUniqueId) {
    //创建会话成功，进入聊天页面
} error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {

}];
```
### 6. 退出客服
断开IM


```objectivec
/**
 断开与TIM服务器的链接
 
 @param option  断开连接的对象实例
 
 @discussion
 因为SDK在前后台切换或者网络出现异常都会自动重连，会保证连接的可靠性。
 所以除非您的App逻辑需要登出，否则一般不需要调用此方法进行手动断开。
 */

- (void)disconnect:(TIMDisConnectOption*)option success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;
```
### 7. 资源下载
demo详见online-demo.zip
