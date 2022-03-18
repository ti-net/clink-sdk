//
//  LoginInfoSave.m
//  mobileCMS
//
//  Created by 赵言 on 2020/2/24.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "LoginInfoSave.h"

static LoginInfoSave *loginInfoSave = nil;

@implementation LoginInfoSave

+ (LoginInfoSave *)shareLoginInfoSave {
    return [[self alloc] init];
}

- (instancetype)init {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        loginInfoSave = [super init];
    });
    return loginInfoSave;
}

- (void)saveData {
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    NSMutableDictionary * mutablLoginDic = [[NSMutableDictionary alloc] initWithDictionary:[userDefaults objectForKey:kLoginInfoDic]];
    NSDictionary * loginDic = @{
        kAppIdNumbers:self.dicAppId?:@{},
        kLoginUserName:self.dicUserLoginName?:@{},
        kLoginToken:self.loginToken?:@"",
        kLoginPassword:self.dicUserPassword?:@{},
        kPlatformShowName:self.platformShowName?:@""
    };
    [mutablLoginDic setObject:loginDic forKey:self.platformURL];
    [userDefaults setObject:mutablLoginDic?:@"" forKey:kLoginInfoDic];
    [userDefaults synchronize];
}

- (void)getData:(NSString*)platformURL{
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    NSDictionary * getInfoDic = [userDefaults objectForKey:kLoginInfoDic];
    if (getInfoDic && [getInfoDic objectForKey:platformURL]) {
        self.dicAppId = getInfoDic[platformURL][kAppIdNumbers]?:@{};
        self.dicUserLoginName = getInfoDic[platformURL][kLoginUserName]?:@{};
        self.loginToken = getInfoDic[platformURL][kLoginToken]?:@"";
        self.dicUserPassword = getInfoDic[platformURL][kLoginPassword]?:@{};
        self.platformShowName = getInfoDic[platformURL][kPlatformShowName]?:@"";
    }
}

@end
