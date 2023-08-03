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

/// paas平台的api
@property (nonatomic, copy) NSString *apiUrlDomainName;

/// 在线客服平台的api
@property (nonatomic, copy) NSString *onlineUrlDomainName;

/// 渠道秘钥
@property (nonatomic, copy) NSString *accessSecretDomainName;

/// 渠道入口ID
@property (nonatomic, copy) NSString *accessIdDomainName;

/// 相应的企业ID
@property (nonatomic, copy) NSString *enterpriseIdDomainName;

/// 平台名称
@property (nonatomic, copy) NSString *domainName;

@end

NS_ASSUME_NONNULL_END
