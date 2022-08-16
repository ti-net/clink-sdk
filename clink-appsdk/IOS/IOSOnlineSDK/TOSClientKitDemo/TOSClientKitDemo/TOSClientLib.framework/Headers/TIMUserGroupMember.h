//
//  TIMUserGroupMember.h
//  TIMClientLib
//
//  Created by YanBo on 2020/6/12.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMUserGroupMember : NSObject

/**
 作为主键 groupId-id
 */
@property (nonatomic, copy) NSString *keyId;

/**
 appId 应用Id
 */
@property (nonatomic, copy) NSString *appId;

/**
 群组Id
 */
@property (nonatomic, copy) NSString *groupId;

/**
 用户Id
 */
@property (nonatomic, copy) NSString *userId;

/**
 群里的index
 */
@property (nonatomic, copy) NSString *index;

/**
 类型
 */
@property (nonatomic, copy) NSString *type;

/**
 权限
 */
@property (nonatomic, copy) NSString *auth;   // 之前的默认是0

///**
// SA权限，默认是0不具有权限
// */
//@property (nonatomic, copy) NSString *saAuth;

/**
 头像
 */
@property (nonatomic, copy) NSString *avatar;

/**
 名称
 */
@property (nonatomic, copy) NSString *name;

/**
 别名
 */
@property (nonatomic, copy) NSString *alias;

/**
 描述
 */
@property (nonatomic, copy) NSString *desCription;

/**
  创建时间
 */
@property (nonatomic, assign) long long createTime;


@end

NS_ASSUME_NONNULL_END
