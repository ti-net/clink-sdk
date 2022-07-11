//
//  TIMMessageReadOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/27.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMMessageReadOption : NSObject

/**
 消息的来源Id
 */
@property (nonatomic, copy, readonly) NSString* senderId;

/**
 消息目的Id
 */
@property (nonatomic, copy, readonly) NSString* targetId;

/**
 消息id列表，列表中的消息标记为已读
 */
@property (nonatomic, copy, readonly) NSArray* messageIds;

/**
 最新的消息Id
 */
@property (nonatomic, copy, readonly) NSString* latestMessageId;

/**
 参数对象初始化方法

 @param senderId               消息的来源Id
 @param messageIds          消息id列表
 @return                参数对象
 */
- (instancetype)initWithOption:(NSString *)senderId targetId:(NSString *)targetId messageIds:(NSArray *)messageIds;

/**
 参数对象初始化方法

 @param senderId                          消息的来源Id
 @param latestMessageId          最后一条消息id
 @return                    参数对象
 */
- (instancetype)initWithOption:(NSString *)senderId targetId:(NSString *)targetId latestMessageId:(NSString *)latestMessageId;

@end

NS_ASSUME_NONNULL_END
