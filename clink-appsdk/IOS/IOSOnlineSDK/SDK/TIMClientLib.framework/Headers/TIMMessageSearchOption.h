//
//  TIMMessageSearchOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/27.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMMessageSearchOption : NSObject

/**
 关键字
 */
@property(nonatomic,copy,readonly) NSString* keyword;

/**
 搜索结果条数控制
 */
@property(nonatomic,assign, readonly) int limit;

/**
 参数对象初始化方法

 @param keyword                            关键字
 @return                   参数对象
 */
- (instancetype)initWithOption:(NSString *)keyword;

/**
 参数对象初始化方法

 @param keyword                            关键字
 @param limit                                 最后一条消息id
 @return                    参数对象
 */
- (instancetype)initWithOption:(NSString *)keyword limit:(int)limit;

@end

NS_ASSUME_NONNULL_END
