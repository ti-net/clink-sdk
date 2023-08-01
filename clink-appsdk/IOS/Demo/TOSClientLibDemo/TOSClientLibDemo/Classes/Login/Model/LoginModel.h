//
//  LoginModel.h
//  TOSClientKitDemo
//
//  Created by 言 on 2022/6/29.
//  Copyright © 2022 YanBo. All rights reserved.
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



@property (nonatomic, copy) NSString * nickname;
@property (nonatomic, copy) NSString * headerUrl;
@property (nonatomic, copy) NSString * userId;
@property (nonatomic, copy) NSString * phoneNumber;

/// 用户附加信息
@property (nonatomic, strong) NSDictionary * advanceParams;

@end

NS_ASSUME_NONNULL_END
