//
//  TIMUserGroup.h
//  TIMClientLib
//
//  Created by YanBo on 2020/6/12.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMUserGroup : NSObject

/**
 作为主键
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
 群组名称
 */
@property (nonatomic, copy) NSString *name;

/**
 群主Id
 */
@property (nonatomic, copy) NSString *ownerUserId;

/**
 群信息描述
 */
@property (nonatomic, copy) NSString *desCription;

/**
  最近一次消息的时间
 */
@property (nonatomic, assign) long long lastUsedDate;

/**
  创建时间
 */
@property (nonatomic, assign) long long createTime;

/**
  头像地址
 */
@property (nonatomic, strong) NSString* portraitURL;

@end

NS_ASSUME_NONNULL_END
