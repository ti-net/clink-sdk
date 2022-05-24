//
//  DomainNameSave.m
//  TIMClientKitDemo
//
//  Created by 李成 on 2022/5/17.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "DomainNameSave.h"

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
        domainName.accessSecretDomainName = [userDefaults objectForKey:kAccessSecretDomainName]?:@"72EBF29CB4614F7AB404EEC07BFF0B1B";
        domainName.accessIdDomainName = [userDefaults objectForKey:kAccessIdDomainName]?:@"8758096679544ff189d4a9457747f109";
        domainName.enterpriseIdDomainName = [userDefaults objectForKey:kEnterpriseIdDomainName]?:@"8000002";
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
