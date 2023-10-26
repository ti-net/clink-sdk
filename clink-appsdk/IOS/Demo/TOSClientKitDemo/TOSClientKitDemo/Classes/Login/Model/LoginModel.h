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

@end

NS_ASSUME_NONNULL_END
