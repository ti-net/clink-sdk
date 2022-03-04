//
//  LoginViewModel.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/10.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "BaseViewModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface LoginViewModel : BaseViewModel

/// 企业编号
@property (nonatomic, copy) NSString *appId;

/// 用户名
@property (nonatomic, copy) NSString *userName;

/// 密码(需要md5加密,32位小写)
@property (nonatomic, copy) NSString *password;

/// 验证码
@property (nonatomic, copy) NSString *securityCode;



/// 返回值。    是否显示验证码
@property (nonatomic, strong) NSNumber *showSecurityCode;

@end

NS_ASSUME_NONNULL_END
