//
//  DomainNameSave.h
//  mobileCMS
//
//  Created by 赵言 on 2020/3/6.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface DomainNameSave : BaseModel

+ (DomainNameSave *)shareDomainNameSave;

- (void)saveData;

/// 登录域名
@property (nonatomic, copy) NSString *loginDomainName;

/// 项目域名
@property (nonatomic, copy) NSString *domainName;

/// 融云AppKey
@property (nonatomic, copy) NSString *rcimAppKey;

/// 推送Token临时保存，不会存储在本地
@property (nonatomic, strong) NSData *deviceToken;

@end

NS_ASSUME_NONNULL_END
