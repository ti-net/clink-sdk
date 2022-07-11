//
//  TIMMessageDeleteOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/27.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMMessageDeleteOption : NSObject

/**
 目标用户Id
 */
@property (nonatomic, copy, readonly) NSString* targetId;
/**
 消息Id数组
 */
@property (nonatomic, copy, readonly) NSArray* messageIds;

/**
 是否最后一条消息之前的全部删除
 */
@property (nonatomic, assign, readonly) BOOL delAll;

/**
 参数对象初始化方法

 @param messageIds                 会话目标Id
 @return               参数对象
 */
- (instancetype)initWithOption:(NSString *)targetId messageIds:(NSArray *)messageIds delAll:(BOOL)delAll;

@end

NS_ASSUME_NONNULL_END
