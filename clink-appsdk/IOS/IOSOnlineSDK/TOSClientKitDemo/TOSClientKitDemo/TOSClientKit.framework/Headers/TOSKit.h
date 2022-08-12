//
//  TOSKit.h
//  TOSClientKit
//
//  Created by YanBo on 2020/5/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <TOSClientLib/TOSClientLib.h>
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

#pragma mark - 消息接收监听器

/*
 TOSKit消息接收的监听器

 @discussion 设置 TOSClientKit 的消息接收监听器

 @warning 如果您使用 TOSClientKit，可以设置并实现此 Delegate 监听消息接收；
 如果您使用 TIMClientLib，请使用 TIMClient 中的 TIMClientReceiveMessageDelegate 监听消息接收，而不要使用此监听器。
 */
@protocol TIMReceiveMessageDelegate <NSObject>

/*
 接收消息的回调方法

 @param message     当前接收到的消息
 @param left        还剩余的未接收的消息数，left>=0

 @discussion 如果您设置了TIMKit消息监听之后，SDK在接收到消息时候会执行此方法（无论App处于前台或者后台）。
 其中，left为还剩余的、还未接收的消息数量。比如刚上线一口气收到多条消息时，通过此方法，您可以获取到每条消息，left会依次递减直到0。
 您可以根据left数量来优化您的App体验和性能，比如收到大量消息时等待left为0再刷新UI。
 */
- (void)onTIMReceiveMessage:(TOSMessage *)message left:(int)left;

/**
 消息被撤回的回调方法

 @param message 被撤回的消息

 @discussion 被撤回的消息会变更为TIMRecallNotificationMessage，App需要在UI上刷新这条消息。
 */
- (void)onMessageRecalled:(TOSMessage *)message;

@end

#pragma mark -总未读数变化监听器

/*
 TIMKit总未读数变化监听器

 */
@protocol TIMTotalUnreadCountChangedDelegate <NSObject>

/*
 总未读数变化的回调方法

 */
- (void)onChanged:(NSNumber *)totalUnreadCount;
@end

/**
TIMKit核心类

@discussion 您需要通过sharedTOSKit方法，获取单例对象
*/
@interface TOSClientKit : NSObject

#pragma mark - TIMKit核心类

/**
 获取界面组件TIMKit的核心类单例

 @return    界面组件TIMKit的核心类单例

 @discussion 您可以通过此方法，获取TIMKit的单例，访问对象中的属性和方法。
 */
+ (instancetype)sharedTOSKit;

/**
初始化天润TIMSDK

@param option 初始化参数对象实例 (将废弃)

@discussion
您在使用天润TIMSDK所有功能之前，您必须先调用此方法初始化SDK。
在App整个生命周期中，您只需要执行一次初始化。
*/
- (void)initWithOption:(TIMInitOption *)option;

#pragma mark - 连接/断开服务器

/**
与TIM服务器建立连接

@param option                                 连接参数对象实例
@param successBlock                    连接建立成功的回调
@param errorBlock                         连接建立失败的回调 [errCode:连接失败的错误码 errorDes:失败的说明]
@param tokenIncorrectBlock     token错误或者过期的回调
*/
- (void)connect:(TOSConnectOption *)option success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock tokenIncorrect:(void (^)(void))tokenIncorrectBlock;

/**
 断开与TIM服务器的链接
 
 @param option  断开连接的对象实例
 
 @discussion
 因为SDK在前后台切换或者网络出现异常都会自动重连，会保证连接的可靠性。
 所以除非您的App逻辑需要登出，否则一般不需要调用此方法进行手动断开。
 */

- (void)disconnect:(TOSDisConnectOption*)option success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
注册了推送后 会从APNs返回设备ID,
 设置从APNs返回的deviceToken

@param deviceTokenData 设备id 推送服务回调的id

@discussion
需要在本地保存,在连接服务器时传送出去。
*/
- (void)setDeviceTokenData:(NSData *)deviceTokenData;

#pragma mark 连接状态监听

/**
 TIMKit连接状态的监听器

 @warning 如果您使用TIMKit，可以设置并实现此Delegate监听消息接收；
 */
-(void)setTOSKitConnectionChangeDelegate:(id<TIMConnectionStatusChangeDelegate>)delegate;

#pragma mark 消息接收监听

/**
 TIMKit消息接收的监听器

 @warning 如果您使用TIMKit，可以设置并实现此Delegate监听消息接收；
 */
-(void)setTIMKitMessageRecvDelegate:(id<TIMReceiveMessageDelegate>)delegate;

#pragma mark 设置总未读数的改变监听

/**
 TIMKit总未读数的改变监听

 @warning 如果您使用TIMKit，可以设置并实现此Delegate监听总未读数的改变；
 */
-(void)setTIMTotalUnreadCountChangedDelegate:(id<TIMTotalUnreadCountChangedDelegate>)delegate;


#pragma mark 消息发送
/**
发送消息

@param option                      发送消息的参数对象实例
@param progressBlock      消息发送进度的回调
@param successBlock        消息发送成功的回调 [messageId:消息的ID]
@param errorBlock             消息发送失败的回调 [nErrorCode:发送失败的错误码,
messageId:消息的ID]


SDK内置的消息类型，如果您将pushOption置为nil，会使用默认的推送格式进行远程推送。
自定义类型的消息，需要您自己设置pushOption来定义推送内容，否则将不会进行远程推送。

如果您使用此方法发送图片消息，需要您自己实现图片的上传，构建一个TIMImageMessage对象，
并将TIMImageMessage中的imageUrl字段设置为上传成功的URL地址，然后使用此方法发送。

如果您使用此方法发送文件消息，需要您自己实现文件的上传，构建一个TIMFileMessage对象，
并将TIMFileMessage中的fileUrl字段设置为上传成功的URL地址，然后使用此方法发送。

 */

- (void)sendMessage:(TIMMessageSendOption *)option progress:(void(^)(float progress))progressBlock success:(void (^)(TOSMessage * timMessage))successBlock error:(void (^)(TOSMessage * message,TIMConnectErrorCode nErrorCode, NSString *errorDes))errorBlock;

/**
 是否关闭所有的本地通知，默认值是NO

 @discussion 当App处于后台时，默认会弹出本地通知提示，您可以通过将此属性设置为YES，关闭所有的本地通知。
 */
@property (nonatomic, assign) BOOL disableMessageNotificaiton;

/**
 是否关闭所有的前台消息提示音，默认值是NO

 @discussion 当App处于前台时，默认会播放消息提示音，您可以通过将此属性设置为YES，关闭所有的前台消息提示音。
 */
@property (nonatomic, assign) BOOL disableMessageAlertSound;

/**
 获取当前底层链接的状态

 @discussion YES 已连接 NO 未链接。
 */
@property (nonatomic, strong) NSNumber* mqttConnected;

/**
当前AppId

*/
@property (nonatomic, strong) NSString *curAppId;

/**
当前用户Id

*/
@property (nonatomic, strong) NSString *curUserId;

/**
当前AccessToken

*/
@property (nonatomic, strong) NSString *curAccessToken;

/**
获取所有会话的总未读数

*/
@property (nonatomic, strong) NSNumber *totalUnreadCount;

/// 消息未读数
@property (nonatomic, assign) NSInteger unReadCount;

/// 最后一条消息
@property (nonatomic, strong, readonly) TOSMessage  * lastMessage;

/**
 其他
 
 生成临时的文件地址
 */
-(NSString *)genFileUrlWithFileId:(NSString * )strFileId;
/// TOSClientKit 改版新增接口

// 获取版本号
+ (NSString *)getSDKVersion;

/**
初始化天润TOSSDK

@param option 初始化参数对象实例

@discussion
您在使用天润TOSSDK所有功能之前，您必须先调用此方法初始化SDK。
在App整个生命周期中，您只需要执行一次初始化。
*/
- (void)initSDK:(TOSInitOption *)option;

/**
获取会话信息
 */
- (TOSSessionInfoModel *)getCurrentSessionInfo;

@end

NS_ASSUME_NONNULL_END
