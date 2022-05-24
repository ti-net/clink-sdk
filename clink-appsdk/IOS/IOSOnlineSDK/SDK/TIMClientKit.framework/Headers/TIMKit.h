//
//  TIMKit.h
//  TIMClientKit
//
//  Created by YanBo on 2020/5/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <TIMClientLib/TIMClientLib.h>
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(NSInteger, TIMKitUIType){
    TIMKitUIDefaultType = 0,   // 默认样式 标准IM
    TIMKitUIEVHCType,          // EVHC样式 增加常用语等
    TIMKitUIDMO20Type,         // DOM20 仅仅在弹出相册的样式颜色上与EVHC有区别
    TIMKitUIRDSA20Type,        // RDSA20样式
};

typedef NS_ENUM(NSInteger, ICChatBoxItem){
    ICChatBoxItemAlbum = 0,    // Album 相册
    ICChatBoxItemCamera,       // Camera 拍摄
    ICChatBoxItemAuditAlbum,   // auditAlbum 审核相册
    ICChatBoxItemAuditCamera,  // auditCamera 审核拍摄
    ICChatBoxItemForm,         // 表单
    ICChatBoxItemCommodity,    // 商品
    ICChatBoxItemBrochure,     // 企业宣传单
    ICChatBoxItemCustomFile,     // 自定义文件
};

/*
 TIMKit点击自定义消息的监听器

 */
@protocol TIMCustomMessageClickDelegate <NSObject>

/*
 点击自定义消息的回调
 */
- (void)onClickCustomMessage:(MYHTIMMessage *)timMessage;
@end

/*
 rtcMedia回调

 */
@protocol TIMRTCMediaMessageDelegate <NSObject>

/*
 回调
 */
- (void)onRtcMediaMessage:(BOOL)onlyAudio receiveId:(NSString*)receiveId;

@end



/*
 TIMKit发送自定义消息前的监听器

 */
@protocol TIMCustomMessageWillSendDelegate <NSObject>

/*
 发送自定义消息前的回调
 */
- (void)onWillSend:(ICChatBoxItem)item;

@end

/*
 TIMKit发送监听消息成功的监听器

 @discussion
 设置TIMKit的发送监听消息成功监听器

 */
@protocol TIMAuditMessageSuccessDelegate <NSObject>

/*
 发送审核消息成功之后的回调
 */
- (void)onSuccess:(NSString *)groupId;

@end

#pragma mark - 消息接收监听器

/*
 TIMKit消息接收的监听器

 @discussion 设置 TIMKit 的消息接收监听器

 @warning 如果您使用 TIMKit，可以设置并实现此 Delegate 监听消息接收；
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
- (void)onTIMReceiveMessage:(MYHTIMMessage *)message left:(int)left;

/**
 消息被撤回的回调方法

 @param message 被撤回的消息

 @discussion 被撤回的消息会变更为TIMRecallNotificationMessage，App需要在UI上刷新这条消息。
 */
- (void)onMessageRecalled:(MYHTIMMessage *)message;

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

@discussion 您需要通过sharedTIMKit方法，获取单例对象
*/
@interface TIMKit : NSObject

#pragma mark - TIMKit核心类

/**
 获取界面组件TIMKit的核心类单例

 @return    界面组件TIMKit的核心类单例

 @discussion 您可以通过此方法，获取TIMKit的单例，访问对象中的属性和方法。
 */
+ (instancetype)sharedTIMKit;

/**
初始化天润TIMSDK

@param option 初始化参数对象实例

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
- (void)connect:(TIMConnectOption *)option success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock tokenIncorrect:(void (^)(void))tokenIncorrectBlock;

/**
 断开与TIM服务器的链接
 
 @param option  断开连接的对象实例
 
 @discussion
 因为SDK在前后台切换或者网络出现异常都会自动重连，会保证连接的可靠性。
 所以除非您的App逻辑需要登出，否则一般不需要调用此方法进行手动断开。
 */

- (void)disconnect:(TIMDisConnectOption*)option success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
注册了推送后 会从APNs返回设备ID,
 设置从APNs返回的deviceToken

@param deviceTokenData 设备id 推送服务回调的id

@discussion
需要在本地保存,在连接服务器时传送出去。
*/
- (void)setDeviceTokenData:(NSData *)deviceTokenData;

/**
 上传用户头像
 */
- (void)uploadUserAvatar:(NSString *)userId avatarImage:(UIImage *)localImage success:(void (^)(NSString * avatarUrl))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark 连接状态监听

/**
 TIMKit连接状态的监听器

 @warning 如果您使用TIMKit，可以设置并实现此Delegate监听消息接收；
 */
-(void)setTIMKitConnectionChangeDelegate:(id<TIMConnectionStatusChangeDelegate>)delegate;

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

- (void)sendMessage:(TIMMessageSendOption *)option progress:(void(^)(float progress))progressBlock success:(void (^)(MYHTIMMessage * timMessage))successBlock error:(void (^)(MYHTIMMessage * message,TIMConnectErrorCode nErrorCode, NSString *errorDes))errorBlock;

#pragma mark 群组
/**
创建群组
 
 @param option                      创建群组参数对象实例
 @param successBlock        接口调用发送成功的回调 [TIMUserGroup:群组实体]
 @param errorBlock             接口调用失败的回调 [nErrorCode:发送失败的错误码,
 messageId:消息的ID]
*/
- (void)createUserGroup:(TIMCreateGroupOption*)option success:(void (^)(TIMUserGroup * userGroup))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;
/**
主动进群

@param option                      主动进群参数对象实例
@param successBlock        接口调用发送成功的回调 [messageId:消息的ID]
@param errorBlock             接口调用失败的回调 [nErrorCode:发送失败的错误码,
messageId:消息的ID]

 */

-(void)joinGroup:(TIMJoinGroupOption *)option success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
拉人进群

@param groupId                    群组Id
@param memberList             所要拉进群的userId数组
@param successBlock        接口调用发送成功的回调 [messageId:消息的ID]
@param errorBlock             接口调用失败的回调 [nErrorCode:发送失败的错误码,
messageId:消息的ID]

 */
-(void)inviteUserToGroup:(NSString *)groupId memberList:(NSArray *)memberList success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
主动退出群聊

@param groupId                   群组Id
@param successBlock        接口调用发送成功的回调 [messageId:消息的ID]
@param errorBlock             接口调用失败的回调 [nErrorCode:发送失败的错误码,
messageId:消息的ID]

 */
-(void)quitGroup:(NSString *)groupId success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;


/**
 是否关闭所有的本地通知，默认值是NO

 @discussion 当App处于后台时，默认会弹出本地通知提示，您可以通过将此属性设置为YES，关闭所有的本地通知。
 */
@property (nonatomic, assign) BOOL disableMessageNotificaiton;

/**
设置发送待审核消息的回调 含图片和视频
*/
@property (nonatomic, weak) id<TIMAuditMessageSuccessDelegate> auditMessageSuccessDelagate;

/**
设置发送前自定义消息的回调
*/
@property (nonatomic, weak) id<TIMCustomMessageWillSendDelegate> customMessageWillSendDelagate;

/**
设置点击自定义消息的回调
*/
@property (nonatomic, weak) id<TIMCustomMessageClickDelegate> customMessageClickDelagate;

/**
设置rtcMedia回调
*/
@property (nonatomic, weak) id<TIMRTCMediaMessageDelegate> rtcMediaMessageDelagate;


/**
 是否关闭所有的前台消息提示音，默认值是NO

 @discussion 当App处于前台时，默认会播放消息提示音，您可以通过将此属性设置为YES，关闭所有的前台消息提示音。
 */
@property (nonatomic, assign) BOOL disableMessageAlertSound;

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
@property (nonatomic, assign) NSInteger                unReadCount;

/**
 其他
 
 生成临时的文件地址
 */
-(NSString *)genFileUrlWithFileId:(NSString * )strFileId;

/**
 根据不同业务模块显示不同的UI定制样式

 @discussion
 参见TIMKitUIType定义
 */
@property (nonatomic, assign) TIMKitUIType customerKitUIType;


- (UIViewController*)topViewController;

@end

NS_ASSUME_NONNULL_END
