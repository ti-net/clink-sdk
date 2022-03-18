//
//  LoginModel.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/11.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface LoginModel : BaseModel

+ (LoginModel *)loginModel;
// 保存LoginModel
- (BOOL)saveLoginModelWithDic:(NSDictionary *)dic;
- (BOOL)saveLoginModel:(LoginModel *)loginModel;
// 删除LoginModel
- (void)removeLoginModel;

/// 是否登录
@property (nonatomic, assign) BOOL isLogin;

/// 是否登录
@property (nonatomic, copy) NSString *lastIMServerURL;

/// 应用编号
@property (nonatomic, copy) NSString *appId;

/// 用户名
@property (nonatomic, copy) NSString *userName;

/// 登录密码
@property (nonatomic, copy) NSString *loginPassword;

/// TIM loginToken
@property (nonatomic, copy) NSString *loginToken;

/// TIM accessToken
@property (nonatomic, copy) NSString *timAccessToken;

/// TIM userId
@property (nonatomic, copy) NSString *timUserId;

@end

NS_ASSUME_NONNULL_END
