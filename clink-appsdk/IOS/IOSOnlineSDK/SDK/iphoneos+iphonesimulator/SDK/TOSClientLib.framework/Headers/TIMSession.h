//
//  TIMSession.h
//  TIMClient
//
//  Created by YanBo on 2020/3/30.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMStatusDefine.h"
#import "TOSMessage.h"

NS_ASSUME_NONNULL_BEGIN

#pragma mark - 会话类

@interface TIMSession : NSObject

/**
 会话类型  1.单聊 2.群聊
 */
@property (nonatomic, assign) TIMSessionType sessionType;

/**
 用户Id
 */
@property (nonatomic, copy) NSString* userId;

/**
 会话目标Id
 */
@property (nonatomic, copy) NSString* targetId;

/**
 优先级
 越大越优先级越高（置顶），默认0
 */
@property (nonatomic, assign) int priority;

/**
 会话标题 单聊时是用户名称，群聊时是群组名称
 */
@property (nonatomic, copy) NSString* sessionTitle;

/**
 消息状态
 */
@property (nonatomic, assign) int messageStatus;

/**
 消息未读数
 */
@property (nonatomic, assign) int unreadCount;

/**
 会话是否需要单独调用getUserInfo获取昵称和头像，根据UI是否显示动态获取 默认0 需要获取:-1
 */
@property (nonatomic, assign) int isContact;

/**
 发送时间
 */
@property (nonatomic, assign) long long sendTime;

/**
 会话最后一条消息
 */
@property (nonatomic, strong) TIMMessageContent* latestMessage;

/**
 最后一条消息的ID
 */
@property (nonatomic, copy) NSString* lastMessageId;


/**
 最后一条消息的描述
 */
@property (nonatomic, copy) NSString* lastMessageDesc;

/**
 最后一条消息的类型
 */
@property (nonatomic, copy) NSString* lastMessageType;

/**
 头像
 */
@property (nonatomic, copy) NSString* portraitUrl;

/// @消息 的消息id
@property (nonatomic, copy) NSString* at;


/**
 头像待定 建议从缓存中取
 */

/**
 参数对象初始化方法

 @param sessionType                  会话类型
 @param targetId                         目标会话Id
 @param priority                         优先级
 @param sessionTitle                会话标题
 @param latestMessage              会话最后一条消息
 @param unreadCount                   消息未读数
 @return                参数对象
 */
//- (instancetype)initWithOption:(TIMSessionType)sessionType targetId:(NSString *)targetId priority:(int)priority sessionTitle:(NSString*)sessionTitle latestMessage:(TIMMessage *)latestMessage unreadCount:(int)unreadCount;

@end

NS_ASSUME_NONNULL_END
