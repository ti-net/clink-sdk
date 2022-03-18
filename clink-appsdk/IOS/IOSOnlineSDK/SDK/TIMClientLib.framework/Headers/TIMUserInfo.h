//
//  TIMUserInfo.h
//  TIMClient
//
//  Created by YanBo on 2020/3/28.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/**
 用户信息类
 */
@interface TIMUserInfo : NSObject

/**
 用户ID
 */
@property(nonatomic, strong) NSString *userId;

/**
 用户名称
 */
@property(nonatomic, strong) NSString *username;

/**
 用户名称
 */
@property(nonatomic, strong) NSNumber *userAuth;

/**
 描述
 */
@property(nonatomic, strong) NSString *desCription;

/**
 用户头像URL
 */
@property(nonatomic, strong) NSString *portraitUri;

/**
 用户信息的初始化方法

 @param userId      用户ID
 @param username    用户名称
 @param portrait    用户头像的URL
 @param description    用户描述信息
 @return            用户信息对象
 */
- (instancetype)initWithUserId:(NSString *)userId name:(NSString *)username userAuth:(NSNumber *)userAuth portrait:(NSString *)portrait description:(NSString *)description;
@end

NS_ASSUME_NONNULL_END
