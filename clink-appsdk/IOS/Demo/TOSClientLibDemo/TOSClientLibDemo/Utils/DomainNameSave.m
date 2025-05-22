//
//  DomainNameSave.m
//  mobileCMS
//
//  Created by 赵言 on 2020/3/6.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "DomainNameSave.h"

static NSString * const kApiUrlDomainName = @"kApiUrlDomainName";
/// 在线客服平台保存
static NSString * const kOnlineUrlDomainName = @"kOnlineUrlDomainName";
/// 秘钥
static NSString * const kAccessSecretDomainName = @"kAccessSecretDomainName";
/// 渠道入口Id
static NSString * const kAccessIdDomainName = @"kAccessIdDomainName";
/// 相应的企业Id保存
static NSString * const kEnterpriseIdDomainName = @"kEnterpriseIdDomainName";
/// 项目域名保存
static NSString * const kDomainName = @"kDomainName";


static DomainNameSave *domainName = nil;

@implementation DomainNameSave

+ (DomainNameSave *)shareDomainNameSave {
    return [[self alloc] init];
}

- (instancetype)init {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        domainName = [super init];
        NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
        // 默认北京环境
        domainName.apiUrlDomainName = [userDefaults objectForKey:kApiUrlDomainName]?:@"https://octopus-api-1.vlink.cn/api/sdk/v1";
        domainName.onlineUrlDomainName = [userDefaults objectForKey:kOnlineUrlDomainName]?:@"https://chat-app-bj.clink.cn";       
        domainName.accessSecretDomainName = [userDefaults objectForKey:kAccessSecretDomainName]?:@"";
        domainName.accessIdDomainName = [userDefaults objectForKey:kAccessIdDomainName]?:@"";
        domainName.enterpriseIdDomainName = [userDefaults objectForKey:kEnterpriseIdDomainName]?:@"";
        domainName.domainName = [userDefaults objectForKey:kDomainName]?:@"天润北京";
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
    
    [userDefaults setObject:domainName.apiUrlDomainName?:@"" forKey:kApiUrlDomainName];
    [userDefaults setObject:domainName.onlineUrlDomainName?:@"" forKey:kOnlineUrlDomainName];
    [userDefaults setObject:domainName.accessSecretDomainName?:@"" forKey:kAccessSecretDomainName];
    [userDefaults setObject:domainName.accessIdDomainName?:@"" forKey:kAccessIdDomainName];
    [userDefaults setObject:domainName.enterpriseIdDomainName?:@"" forKey:kEnterpriseIdDomainName];
    [userDefaults setObject:domainName.domainName?:@"" forKey:kDomainName];
    [userDefaults synchronize];
}

@end
