//
//  TIMInviteUserToGroupOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/7/13.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMInviteUserToGroupOption : NSObject

/**
 群组Id
 */
@property(nonatomic, readonly) NSString *groupId;

/**
 群成员列表
 */
@property(nonatomic, readonly) NSString *memberList;

/**
 进群后的类型设置 批量
 */
@property(nonatomic, readonly) NSString *type;

/**
 进群后的描述设置 批量
 */
@property(nonatomic, readonly) NSString *desCription;

/**
 参数对象初始化方法

 @param groupId                    群Id
 @param memberList             被邀请的群成员列表
 @return                参数对象
 */
- (instancetype)initWithOption:(NSString *)groupId memberList:(NSArray *)memberList;

/**
 参数对象初始化方法

 @param groupId                    群Id
 @param memberList             被邀请的群成员列表
 @param type                          进群后的类型设置 批量
 @param desCription           进群后的描述设置 批量
 @return                参数对象
 */
- (instancetype)initWithOption:(NSString *)groupId memberList:(NSArray *)memberList type:(NSString *)type desCription:(NSString *)desCription;

@end

NS_ASSUME_NONNULL_END
