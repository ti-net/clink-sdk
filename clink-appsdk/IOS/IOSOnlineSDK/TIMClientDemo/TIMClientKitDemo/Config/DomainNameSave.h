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

/// appServer登录域名
@property (nonatomic, copy) NSString *loginDomainName;

/// IMServer登录域名
@property (nonatomic, copy) NSString *IMloginDomainName;

/// 项目域名
@property (nonatomic, copy) NSString *domainName;

@end

NS_ASSUME_NONNULL_END
