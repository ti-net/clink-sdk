//
//  DomainNameSave.m
//  mobileCMS
//
//  Created by 赵言 on 2020/3/6.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "DomainNameSave.h"

static DomainNameSave *domainName = nil;

static NSString * const kLoginDomainName = @"kLoginDomainName";
/// 项目域名保存
static NSString * const kDomainName = @"kDomainName";
/// 融云AppKey保存
static NSString * const kRCIMAppKey = @"kRCIMAppKey";

@implementation DomainNameSave

+ (DomainNameSave *)shareDomainNameSave {
    return [[self alloc] init];
}

- (instancetype)init {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        domainName = [super init];
        NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
        #ifdef DEBUG
                domainName.loginDomainName = [userDefaults objectForKey:kLoginDomainName]?:@"https://bj-test0.clink.cn";
                domainName.domainName = [userDefaults objectForKey:kDomainName]?:@"http://app-bj-test0.clink.cn";
                domainName.rcimAppKey = [userDefaults objectForKey:kRCIMAppKey]?:@"cpj2xarlctxbn";
        #else
                domainName.loginDomainName = [userDefaults objectForKey:kLoginDomainName]?:@"https://bj.clink.cn";
                domainName.domainName = [userDefaults objectForKey:kDomainName]?:@"https://app-bj.clink.cn";
                domainName.rcimAppKey = [userDefaults objectForKey:kRCIMAppKey]?:@"pgyu6atqp56lu";
        #endif
    });
    return domainName;
}

+ (instancetype)allocWithZone:(struct _NSZone *)zone {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        domainName = [super allocWithZone:zone];
    });
    return domainName;
}

- (id)copyWithZone:(NSZone *)zone {
    return domainName;
}

- (id)mutableCopyWithZone:(NSZone *)zone {
    return domainName;
}


- (void)saveData {
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    [userDefaults setObject:[domainName.loginDomainName?:@"" verifyString] forKey:kLoginDomainName];
    [userDefaults setObject:[domainName.domainName?:@"" verifyString] forKey:kDomainName];
    [userDefaults setObject:[domainName.rcimAppKey?:@"" verifyString] forKey:kRCIMAppKey];
    [userDefaults synchronize];
}

@end
