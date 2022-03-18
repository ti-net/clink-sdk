//
//  LoginInfoSave.h
//  mobileCMS
//
//  Created by 赵言 on 2020/2/24.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface LoginInfoSave : BaseModel

+ (LoginInfoSave *)shareLoginInfoSave;

- (void)saveData;

- (void)getData:(NSString*)platformURL;

/// 平台Md5
@property (nonatomic, copy) NSString *platformURL;

/// 平台名称
@property (nonatomic, copy) NSString *platformShowName;

///// 应用编号
//@property (nonatomic, copy) NSString *appIdNumbers;
//
///// 用户登录名
//@property (nonatomic, copy) NSString *userLoginName;
//
/////    密码 临时 // TODO delete
//@property (nonatomic, copy) NSString *loginPassword;

/// 应用编号
@property (nonatomic, strong) NSDictionary *dicAppId;

/// 用户登录名
@property (nonatomic, strong) NSDictionary *dicUserLoginName;

///    密码 临时
@property (nonatomic, strong) NSDictionary *dicUserPassword;

///  授权的token
@property (nonatomic, copy) NSString *loginToken;

@end

NS_ASSUME_NONNULL_END
