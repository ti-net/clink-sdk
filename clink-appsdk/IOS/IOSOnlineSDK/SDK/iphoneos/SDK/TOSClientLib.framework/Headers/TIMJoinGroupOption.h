//
//  TIMJoinGroupOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/7/14.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMJoinGroupOption : NSObject
/**
 群组Id
 */
@property(nonatomic, readonly) NSString *groupId;

/**
 进群后的类型设置
 */
@property(nonatomic, readonly) NSString *type;

/**
 进群后的描述设置
 */
@property(nonatomic, readonly) NSString *desCription;


/**
 参数对象初始化方法

 @param groupId                    群Id
 @param type                           进群后的类型设置
 @param desCription           进群后的描述设置
 @return                参数对象
 */
- (instancetype)initWithOption:(NSString *)groupId type:(NSString *)type desCription:(NSString *)desCription;
@end

NS_ASSUME_NONNULL_END
