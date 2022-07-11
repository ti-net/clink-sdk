//
//  TIMSessionListOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/27.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMSessionListOption : NSObject

/**
 开始时间，时间戳，到毫秒（Unix时间戳、毫秒）获取startTime之后的的会话，默认1个月内的会话
 默认值 当前时间戳-30*24*3600
 */
@property (nonatomic, assign, readonly) long long startTime;

/**
 获取会话的数量
 默认值是500 最大1000
 */
@property (nonatomic, assign, readonly) int limit;

/**
 默认参数对象初始化方法

 @return                参数对象
 */
- (instancetype)defaultOption;

/**
 参数对象初始化方法

 @param startTime               开始时间，时间戳，到毫秒
 @param limit                        获取会话的数量
 @return                参数对象
 */
- (instancetype)initWithOption:(long long)startTime limit:(int)limit;

@end

NS_ASSUME_NONNULL_END
