//
//  LoginModel.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/11.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "LoginModel.h"
#import "AppDelegate.h"
#import "LoginViewController.h"

// 登陆返回保存路径
#define LoginModelFilePath [[NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingPathComponent:@"TOSClientKitDemoLoginModel.arch"]

@implementation LoginModel

+ (LoginModel *)loginModel {
    LoginModel *loginModel = [[self alloc] initLoginModel];
    if (!loginModel) {
        return [[self alloc] init];
    }
    return loginModel;
}

- (instancetype)initLoginModel {
    if (self = [super init]) {
        self = [NSKeyedUnarchiver unarchiveObjectWithFile:LoginModelFilePath];
    }
    return self;
}

// 保存LoginModel
- (BOOL)saveLoginModelWithDic:(NSDictionary *)dic {
    LoginModel *loginModel = [LoginModel modelWithDictionary:dic];
    return [self saveLoginModel:loginModel];
}

- (BOOL)saveLoginModel:(LoginModel *)loginModel {
    return [NSKeyedArchiver archiveRootObject:loginModel toFile:LoginModelFilePath];
}

// 删除LoginModel
- (void)removeLoginModel {
    NSFileManager *manager = [NSFileManager defaultManager];
    [manager removeItemAtPath:LoginModelFilePath error:nil];
//    self.nickname = @"";
}

@end
