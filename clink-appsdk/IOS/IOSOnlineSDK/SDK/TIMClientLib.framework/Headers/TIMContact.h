//
//  TIMContact.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/17.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMContact : NSObject

/**
 作为主键
 */
@property (nonatomic, copy) NSString *keyId;

/**
 appId 应用Id
 */
@property (nonatomic, copy) NSString *appId;

/**
 当前用户Id
 */
@property (nonatomic, copy) NSString *userId;

/**
 联系人Id
 */
@property (nonatomic, copy) NSString *contactUserId;

/**
 联系人别名 昵称
 */
@property (nonatomic, copy) NSString *alias;

/**
 用户类型 1 普通用户
 */
@property (nonatomic, assign) int type;

/**
  描述信息
 */
@property (nonatomic, copy) NSString *desCription;

/**
  一个联系人在一个联系人组内
 */
@property (nonatomic, assign) int contactGroupId;

/**
  头像地址
 */
@property (nonatomic, copy) NSString* avatarUrl;

/**
  创建时间
 */
@property (nonatomic, assign) long long createTime;

@end

NS_ASSUME_NONNULL_END
