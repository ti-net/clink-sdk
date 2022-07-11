//
//  TOSMessage.h
//  TIMClient
//
//  Created by YanBo on 2020/3/30.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMMessageContent.h"
#import "TIMStatusDefine.h"

NS_ASSUME_NONNULL_BEGIN

@interface TOSMessage : NSObject

/**
 本地生成的消息唯一ID
 */
@property (nonatomic, copy, readonly) NSString* messageUUID;

/**
 服务器生成的消息id 消息唯一ID 按照时间顺序生成
 */
@property (nonatomic, copy, readonly) NSString* msg_id;

/**
 消息类型
 */
@property (nonatomic, copy, readonly) NSString* type;

/**
 发送者Id
 */
@property (nonatomic, copy, readonly) NSString* senderId;


/**
 接收者Id
 */
@property (nonatomic, copy, readonly) NSString* receiverId;


/**
 消息内容
 */
@property (nonatomic, strong, readonly) TIMMessageContent* content;

/**
 消息归属 1.个人消息   2.群消息
 */
@property (nonatomic, assign, readonly) TIMSessionType msg_from;

/**
 消息状态  1.已发送 2.未读 3.已读 4.已撤回 5.已删除
 */
@property (nonatomic, assign, readonly) TIMMessageStatus status;

/**
 消息子类型
 messageType    =   1       文本消息
 messageType    =   2       图片消息
 messageType    =   3       文件消息
 messageType    =   4       视频消息
 messageType    =   5       机器人富文本消息
 messageType    =   7       语音消息
 messageType    =   10      卡片消息
 */
@property (nonatomic, assign, readonly) int messageType;

/**
 创建时间
 */
@property (nonatomic, assign, readonly) NSTimeInterval timestamp;

///**
// 参数对象初始化方法
//
// @param content                   消息内容
// @param msg_from                消息归属 1.个人消息   2.群消息
// @return               参数对象
// */
//- (instancetype)initWithOption:(TIMMessageContent *)content msgUUID:(NSString *)msgUUID msg_from:(TIMSessionType)msg_from;

/**
 参数对象初始化方法
 
 @param messageUUID                   本地消息唯一ID
 @param msg_id                     服务器生成的消息唯一ID  可以为空以本地消息ID为主
 @param type                         消息类型
 @param senderId                 发送方Id
 @param receiverId             接收方Id
 @param content                   消息内容
 @return               参数对象
 */
- (instancetype)initWithOption:(NSString *)messageUUID msg_id:(NSString *)msg_id type:(NSString *)type senderId:(NSString *)senderId receiverId:(NSString *)receiverId content:(TIMMessageContent *)content msg_from:(TIMSessionType)msg_from status:(TIMMessageStatus)status timestamp:(NSTimeInterval)timestamp;

- (void)updateMessageType:(int)messageType;

@end

NS_ASSUME_NONNULL_END
