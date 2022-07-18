//
//  TIMContactDetail.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/17.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "NSObject+TIMModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface TIMContactDetail : NSObject  <TIMModel, NSCopying, NSMutableCopying>

/**
 id
 */
@property(nonatomic, strong) NSNumber *keyId;

/**
 accountId
 */
@property(nonatomic, copy) NSString *accountId;

/**
应用Id
 */
@property(nonatomic, strong) NSString *appId;

/**
 active
 */
@property(nonatomic, strong) NSNumber *active;

/**
 用户类型 1 普通用户
 */
@property(nonatomic, strong) NSNumber *type;

/**
 创建时间
 */
@property(nonatomic, strong) NSNumber *createTime;

/**
 用户ID
 */
@property(nonatomic, copy) NSString *userId;

/**
 用户昵称
 */
@property(nonatomic, copy) NSString *userAlias;

/**
 用户权限
 */
@property(nonatomic, strong) NSNumber *userAuth;


/**
 用户备注 描述？
 */
@property(nonatomic, copy) NSString *desCription;


/**
 用户头像URL
 */
@property(nonatomic, copy) NSString *portraitUri;


/// 用户信息的初始化方法
/// @param userId 用户ID
/// @param userAlias 用户昵称
/// @param userAuth 用户权限
/// @param portrait 用户头像的URL
/// @param description 备注
/// @param keyId keyId
/// @param accountId accountId
/// @param appId 应用Id
/// @param active active
/// @param type 用户类型
/// @param createTime 用户信息对象
//- (instancetype)initWithUserId:(NSString *)userId alias:(NSString *)userAlias userAuth:(NSNumber *)userAuth portrait:(NSString *)portrait description:(NSString *)description keyId:(int)keyId accountId:(NSString *)accountId appId:(NSString *)appId active:(int)active type:(int)type createTime:(long long)createTime;

@end

NS_ASSUME_NONNULL_END
