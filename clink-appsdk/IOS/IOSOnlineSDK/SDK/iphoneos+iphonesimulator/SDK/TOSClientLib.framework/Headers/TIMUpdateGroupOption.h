//
//  TIMUpdateGroupOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/8/13.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMUpdateGroupOption : NSObject
/**
 群组id
 */
@property(nonatomic, readonly) NSString *groupId;

/**
 群组名称
 */
@property(nonatomic, readonly) NSString *name;

/**
群组描述信息
 */
@property(nonatomic, readonly) NSString *desCription;


/**
 参数对象初始化方法

 @param name                          群名称
 @param desCription           描述信息
 @return                参数对象
 */
- (instancetype)initWithOption:(NSString *)groupId name:(NSString *)name desCription:(NSString *)desCription;
@end

NS_ASSUME_NONNULL_END
