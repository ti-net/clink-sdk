//
//  DomainNameSave.h
//  TIMClientKitDemo
//
//  Created by 李成 on 2022/5/17.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/// paas 平台保存
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


@interface DomainNameSave : NSObject


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
