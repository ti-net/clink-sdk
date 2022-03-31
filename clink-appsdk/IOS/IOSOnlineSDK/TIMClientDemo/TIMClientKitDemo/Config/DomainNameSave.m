//
//  DomainNameSave.m
//  mobileCMS
//
//  Created by 赵言 on 2020/3/6.
//  Copyright © 2020 赵言. All rights reserved.
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
        // 默认正式环境
        domainName.loginDomainName = [userDefaults objectForKey:kLoginDomainName]?:@"http://appserver-1.octopus.video";
        domainName.IMloginDomainName = [userDefaults objectForKey:kIMLoginDomainName]?:@"http://api-1.octopus.video/api/sdk/v1";
        domainName.domainName = [userDefaults objectForKey:kDomainName]?:@"";
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
    
    [userDefaults setObject:domainName.loginDomainName?:@"" forKey:kLoginDomainName];
    [userDefaults setObject:domainName.IMloginDomainName?:@"" forKey:kIMLoginDomainName];
    [userDefaults setObject:domainName.domainName?:@"" forKey:kDomainName];
    [userDefaults synchronize];
}

@end
