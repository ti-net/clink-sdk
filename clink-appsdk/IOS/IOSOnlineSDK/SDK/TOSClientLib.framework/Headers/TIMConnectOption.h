//
//  TIMConnectOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMStatusDefine.h"

NS_ASSUME_NONNULL_BEGIN

/*
链接参数对象
*/

@interface TIMConnectOption : NSObject

/**
 应用Id 账户的应用Id
 */
@property(nonatomic,readonly) NSString *appId;

/**
 鉴权Token 调用Server Api接口获取的鉴权Token
 */
@property(nonatomic, readonly) NSString *accessToken;

/**
 IM的用户Id 不传服务端会自动生成userId，生成的userId通过回调方法返回
 */
@property(nonatomic, readonly) NSString *userId;

/**
 IM用户类型 当userId不传时有效，表示自动生成的用户类型，
 TIMUserType_LONGTERM_AVAILABLE = 表示长期有效的用户
 TIMUserType_TEMPORARY_AVAILABLE = 表示临时用户
 */
@property(nonatomic,readonly) TIMUserType userType;

/**
 是否将同类型设备踢下线 默认为是，默认只支持1个Web端和移动端同时登录
 */
@property(nonatomic, readonly) BOOL kickout;

/**
 自定义状态 支持在后台自定义状态
 */
@property(nonatomic, readonly) NSString *status;

/**
 必选参数对象初始化方法
 
 @param appId                        应用Id
 @param accessToken           鉴权Token
 @return                参数对象
 */
- (instancetype)initWithOption:(NSString *)appId userId:(NSString *)userId accessToken:(NSString *)accessToken;

/**
 参数对象初始化方法

 @param appId                        应用Id
 @param accessToken           鉴权Token
 @param userId                      IM的用户Id
 @param userType                  IM用户类型
 @param kickout                    是否将同类型设备踢下线
 @param status                      自定义状态
 @return                参数对象
 */
- (instancetype)initWithOption:(NSString *)appId userId:(NSString *)userId accessToken:(NSString *)accessToken  userType:(TIMUserType)userType kickout:(BOOL)kickout status:(nullable NSString *)status;


@end

NS_ASSUME_NONNULL_END
