//
//  TIMCreateGroupOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/6/12.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMCreateGroupOption : NSObject

/**
 群组名称
 */
@property(nonatomic, readonly) NSString *name;

/**
 群成员列表
 */
@property(nonatomic, readonly) NSString *memberList;

/**
群组描述信息
 */
@property(nonatomic, readonly) NSString *desCription;


/**
 参数对象初始化方法

 @param name                          群名称
 @param memberList             群成员列表
 @param desCription           描述信息
 @return                参数对象
 */
- (instancetype)initWithOption:(NSString *)name memberList:(NSArray *)memberList desCription:(NSString *)desCription;

@end

NS_ASSUME_NONNULL_END
