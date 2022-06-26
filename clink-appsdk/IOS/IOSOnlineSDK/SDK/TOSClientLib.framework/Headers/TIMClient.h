//
//  TIMClient.h
//  TIMClient
//
//  Created by YanBo on 2020/3/30.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMStatusDefine.h"
#import "TIMSessionListOption.h"
#import "TOSMessage.h"
//#import "TIMMessageHistoryOption.h"
#import "TIMMessageSendOption.h"
#import "TIMMessageRevokeOption.h"
#import "TIMMessageReadOption.h"
#import "TIMATMessageReadOption.h"
#import "TIMMessageSearchOption.h"
#import "TIMInitOption.h"
#import "TIMConnectOption.h"
//#import "TIMRole.h"
#import "TIMUserInfo.h"
#import "TIMContactDetail.h"
#import "TIMContact.h"
#import "TIMContactGroup.h"
#import "TIMCreateGroupOption.h"
#import "TIMUserGroup.h"
#import "TIMUserGroupMember.h"
#import "TOSDisConnectOption.h"
#import "TIMMessageDeleteOption.h"
#import "TIMMessageUpdateContentOption.h"
#import "TIMJoinGroupOption.h"
#import "TIMUpdateGroupOption.h"


NS_ASSUME_NONNULL_BEGIN

#pragma mark -总未读数变化监听器

/*
 TIMLib总未读数变化监听器

 */
@protocol TIMLibTotalUnreadCountChangedDelegate <NSObject>

/*
 总未读数变化的回调方法

 */
- (void)onChanged:(NSNumber *)totalUnreadCount;
@end

/*
 TIMLib连接状态的的监听器

 @discussion
 设置TIMLib的连接状态监听器

 @warning 如果您使用TIMLib，可以设置并实现此Delegate监听连接状态变化；请使用TIMConnectionStatusDelegate监听消息接收
 */
@protocol TIMConnectionStatusChangeDelegate <NSObject>

/*
 连接中
 */
- (void)onConnecting;

/*
 连接成功
 */
- (void)onConnected;

/*
 数据同步中
 */
- (void)onDataSyncing;

/*
 数据同步完成
 */
- (void)onDataSynced;

/*
 连接断开
 */
- (void)onDisConnected;

/*
 断线重连中
 */
- (void)onReConnecting:(int)round interval:(int)interval;

/*
 断线重连成功
 */
- (void)onReConnected;

/*
 被踢下线 服务器产生
 */
- (void)onKickOut;

@end

#pragma mark - 消息接收监听器

/**
 TIMlib消息接收的监听器

 @discussion
 设置TIMLib的消息接收监听器请参考TIMClient的setReceiveMessageDelegate:object:方法。

 @warning 如果您使用TIMlib，可以设置并实现此Delegate监听消息接收；
 */
@protocol TIMClientReceiveMessageDelegate <NSObject>

/**
 接收消息的回调方法

 @param message     当前接收到的消息
 @param nLeft       还剩余的未接收的消息数，left>=0

 @discussion 如果您设置了TIMlib消息监听之后，SDK在接收到消息时候会执行此方法。
 其中，left为还剩余的、还未接收的消息数量。比如刚上线一口气收到多条消息时，通过此方法，您可以获取到每条消息，left会依次递减直到0。
 您可以根据left数量来优化您的App体验和性能，比如收到大量消息时等待left为0再刷新UI。
 */
- (void)onReceived:(TOSMessage *)message left:(int)nLeft;

/*
 获取未读消息数量/最后一条消息的信息
 **/
- (void)onReceived:(TOSMessage *)message withMessageType:(int)messageType;

@optional
/**
 消息被删除的回调方法

 @param messages 被删除的消息实体数组

 */
- (void)onMessageDeleted:(NSArray *)messages isSuccess:(BOOL)isSuccess;

/**
 消息被清空的回调方法

 @param targetId 被清空的用户id或会话id

 */
- (void)onMessageClear:(NSString *)targetId;

/**
 消息被撤回的回调方法

 @param message 被撤回的消息

 @discussion 被撤回的消息会变更为TIMRecallNotificationMessage，App需要在UI上刷新这条消息。
 */
- (void)onMessageRecalled:(TOSMessage *)message;


/**
 消息已读回执响应（收到阅读回执响应，可以按照 messageId 更新消息的阅读数）
 @param messageId       请求已读回执的消息ID
 @param targetId         targetId
 @param userIdList     已读userId列表
 */
- (void)onMessageReceiptResponse:(NSString *)targetId
                      messageId:(NSString *)messageId
                      readerList:(NSMutableDictionary *)userIdList;

@end

#pragma mark - TIMClientLib核心类

/**
 天润TIMClientLib核心类
 @discussion 您需要通过sharedTIMClient方法，获取单例对象
 */
@interface TIMClient : NSObject

/**
 获取天润通讯能力库TIMLib的核心类单例

 @return 融云通讯能力库TIMLib的核心类单例

 @discussion 您可以通过此方法，获取TIMLib的单例，访问对象中的属性和方法.
 */
+ (instancetype)sharedTIMClient;

#pragma mark - SDK初始化

/**
初始化天润IMSDK

@param option 初始化参数对象实例

@discussion
您在使用天润IMSDK所有功能之前，您必须先调用此方法初始化SDK。
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

- (void)disconnect:(TOSDisConnectOption*)option success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;


/**
 发送Mqtt的ping事件
 */
- (void)sendMQTTMessagePing;

#pragma mark - 离线推送
/**
注册了推送后 会从APNs返回设备ID,
 设置从APNs返回的deviceToken

@param deviceTokenData 设备id 推送服务回调的id

@discussion
需要在本地保存,在连接服务器时传送出去。
*/
- (void)setDeviceTokenData:(NSData *)deviceTokenData;


#pragma mark - 连接状态监听

/**
 设置TIMLib的连接状态监听器

 @param delegate    TIMLib连接状态监听器
 */
-(void)setTIMConnectionStatusChangeDelegate:(id<TIMConnectionStatusChangeDelegate>)delegate;

#pragma mark - 接收消息监听

/**
 设置TIMLib的监听接收消息的监听器
 
 @param delegate TIMLib接收消息监听器
 */
 -(void)setTIMReceiveMessageDelegate:(id<TIMClientReceiveMessageDelegate>)delegate;

#pragma mark - 用户管理

/**
 获取用户信息
 
 @param contactId               用户Id
 */

- (void)getUserInfo:(NSString *)contactId success:(void (^)(TIMContactDetail * detail))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
 修改用户信息
 
 @param user               用户实例
 */

//- (void)updateUserInfo:(TIMUserInfo*)user success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
 上传用户头像
 
 @param userId               用户实例
 @param localImageData 本地图像实例数据
 */

- (void)uploadUserAvatar:(NSString *)userId avatarImageData:(NSData *)localImageData success:(void (^)(NSString * avatarUrl))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;


#pragma mark - 会话管理

/**
 获取会话列表
 
 @param option                      读取会话列表的参数对象实例

 @discussion 此方法会从本地数据库中，读取会话列表。
 返回的会话列表按照时间从前往后排列，如果有置顶的会话，则置顶的会话会排列在前面。
 */
-(void)getSessionList:(TIMSessionListOption *)option success:(void (^)(NSArray *sessionList))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;


/**
删除会话
 
@param targetId                      接收方的 userId

@discussion 此方法会从同时从服务器删除和本地数据库删除。
*/

- (void)deleteSession:(NSString *)targetId success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
会话置顶

@param targetId                     接收方的 userId
@param priority                     优先级

@discussion 优先级，大于0的值，越大越优先
*/

- (void)setSessionPriority:(NSString *)targetId priority:(int)priority success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark - 消息管理

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
 撤回一条消息
 
 @param option              撤回消息的参数对象实例
 @param progressBlock      消息发送进度的回调
 @param successBlock        消息发送成功的回调
 @param errorBlock             消息发送失败的回调
 */

- (void)revokeMessage:(TIMMessageRevokeOption *)option progress:(void(^)(float progress))progressBlock success:(void (^)(TOSMessage * timMessage))successBlock error:(void (^)(TOSMessage * message,TIMConnectErrorCode nErrorCode, NSString *errorDes))errorBlock;

/**
 取消发送中的信息(较大的文件)

 @param message                   消息体TOSMessage
 @param successBlock        消息取消成功的回调 [messageId:消息的ID]
 @param errorBlock             消息取消失败的回调 [nErrorCode:发送失败的错误码,

 @return YES表示取消成功，NO表示取消失败，即已经发送完成或者消息不存在。
 */
- (BOOL)cancelSendMessage:(TOSMessage *)message success:(void(^)(void))successBlock error:(void(^)(TIMConnectErrorCode nErrorCode, NSString *errorDes))errorBlock;

#pragma mark - 获取历史消息
/**
获取历史消息内部逻辑:

先从本地获取，当本地数据不足时(如请求50条消息，本地有40条)，记录下本地最早的消息Id,再进行从服务器获取消息(此时需要获取的数量count=10);直到服务器也返回的数据<请求的count为止;此时返回成功的消息数组长度为0

@param option                      查询历史消息的参数对象实例

@discussion
此方法会获取该会话中，oldestMessageId之前的、指定数量的最新消息实体，返回的消息实体按照时间从新到旧排列。
返回的消息中不包含oldestMessageId对应那条消息，如果会话中的消息数量小于参数count的值，会将该会话中的所有消息返回。
如：
oldestMessageId为10，count为2，会返回messageId为9和8的TOSMessage对象列表。
*/
//- (void)getMessageHistory:(TIMMessageHistoryOption*)option success:(void (^)(NSArray *messageList))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark 消息更新
/**
更新消息

@param option                     消息更新参数对象实例
*/

- (void)updateMessageContent:(TIMMessageUpdateContentOption *)option success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark 发送消息已读回执

/**
 发送某个会话中消息已读的回执

 @param option                     消息已读参数对象实例

 @discussion 此接口只支持单聊, 如果使用Lib 可以注册监听
 TIMLibDispatchReadReceiptNotification 通知
 */

- (void)sendMessageRead:(TIMMessageReadOption *)option;


/// 发送某个会话中AT消息已读的回执
/// @param option AT消息已读参数对象实例
- (void)sendATMessageRead:(TIMATMessageReadOption *)option;

#pragma mark 关键字搜索

/**
关键字搜索 在会话中

@param option            关键字搜索参数对象实例

@warning 目前仅支持单聊。
*/
- (void)searchMessage:(TIMMessageSearchOption*)option success:(void (^)(NSArray * sessionList))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark 消息撤回 

/**
消息撤回

@param messageId            消息ID

@warning 目前仅支持单聊。
*/
- (void)recallMessage:(NSString *)messageId success:(void (^)(NSString * messageId))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
清空历史消息

@param targetId             userId或groupId
@param pushContent        当下发 push 消息时，在通知栏里会显示这个字段。如果设置该字段为nil或@""，无法接受到 push 推送

@warning 目前仅支持单聊。
*/
- (void)cleanMessage:(NSString *)targetId pushContent:(NSString *)pushContent success:(void (^)(NSString * targetId))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

#pragma mark 删除消息
/**
删除消息

@param option                     消息删除参数对象实例
*/

- (void)deleteMessage:(TIMMessageDeleteOption *)option;

#pragma mark - 群组管理
/**
创建群组
*/
- (void)createUserGroup:(TIMCreateGroupOption*)createUserGroup success:(void (^)(TIMUserGroup * userGroup))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
删除群组
*/
- (void)deleteUserGroup:(NSString *)groupId success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
更新群组信息
*/
- (void)updateUserGroup:(TIMUpdateGroupOption*)updateUserGroup success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
获取群组信息
*/
- (void)getUserGroup:(NSString *)groupId success:(void (^)(TIMUserGroup * userGroup))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
 获取群组列表
 */
- (void)getUserGroupList:(void (^)(NSArray<TIMUserGroup *>*))successBlock;

/**
 获取群组成员列表
 */
- (void)getGroupMemberList:(NSString *)groupId success:(void (^)(NSArray<TIMUserGroupMember *>*))successBlock;

/**
 获取群组成员信息
 */
- (void)getGroupMemberInfo:(NSString *)groupId memberId:(NSString *)memberId success:(void (^)(TIMUserGroupMember *))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
获取本地群组成员列表
*/
- (void)getLocalGroupMemberList:(NSString *)groupId success:(void (^)(NSArray<TIMUserGroupMember *> *))successBlock;
/**
 是否在群组中(此接口为本地查询,前提需要创建群组或调用getGroupMemberList之后方可，谨慎使用)
 */
-(BOOL)isMemberFromGroup:(NSString *)groupId;

/**
 主动退出群聊
 */
-(void)quitGroup:(NSString *)groupId success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
 拉人进群
 
 */
-(void)inviteUserToGroup:(NSString *)groupId memberList:(NSArray *)memberList success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
 自主进群
 
 */
-(void)joinGroup:(TIMJoinGroupOption *)option success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;



#pragma mark - 联系人模块

/**
获取联系人列表

*/
- (void)getContactList:(void (^)(NSArray<TIMContact *>*))successBlock;


/**
 获取联系人详情
 */
- (void)getContact:(NSString *)contactId success:(void (^)(TIMContactDetail *))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
 删除联系人
 
 @param contactUserId            联系人ID
 */
- (void)deleteContact:(NSString*)contactUserId success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
 添加联系人
 
 @param contact               联系人实例
 */
- (void)addContact:(TIMContact*)contact success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
 修改联系人
 
 @param contact               联系人实例
 */

- (void)updateContact:(TIMContact*)contact success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
 新增联系人组
 
  @param contactGroup               联系人组实例
 */
- (void)createContactGroup:(TIMContactGroup*)contactGroup success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
 删除联系人组
 
  @param contactGroupId               联系人组ID
 */

- (void)deleteContactGroup:(NSString*)contactGroupId success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
 更新联系人组
 
 @param contactGroup               联系人组实例
 */

- (void)updateContactGroup:(TIMContactGroup*)contactGroup success:(void (^)(void))successBlock error:(void (^)(TIMConnectErrorCode errCode,NSString *errorDes))errorBlock;

/**
 其他
 */
// 更新URL文件地址
-(NSString *)updateWithFileUrl:(NSString *)fileUrl;

/**
获取所有会话的总未读数

*/
@property (nonatomic, strong) NSNumber *totalUnreadCount;

#pragma mark 设置离线推送扩展标识

typedef NS_ENUM(NSInteger, TIMSendPushExtraType){
    TIMPushExtraDefaultType = 0,   // 默认 不加标识
    TIMPushExtraEVHCType,          // eVHC
    TIMPushExtraDMO20Type,         // DMO
    TIMPushExtraRDSA20Type,        // RDSA
    TIMPushExtraMybmwType,         // MyBmw
};

- (void)setSendPushExtra:(TIMSendPushExtraType)pushExtraType;

#pragma mark 设置总未读数的改变监听

/**
 TIMLib总未读数的改变监听

 @warning 如果您使用TIMLib，可以设置并实现此Delegate监听总未读数的改变；
 */
-(void)setTIMLibTotalUnreadCountChangedDelegate:(id<TIMLibTotalUnreadCountChangedDelegate>)delegate;

// 清零会话未读
- (void)setZeroUnreadDataWithUreadTable:(NSString *)targetId;

/**
是否是apiVersion2的版本

*/
@property (nonatomic, strong) NSNumber *isApiVersion2;

@end

NS_ASSUME_NONNULL_END
