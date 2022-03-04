//
//  LogoutViewModel.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/27.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "LogoutViewModel.h"
#import <AFNetworking/AFNetworking.h>

@implementation LogoutViewModel

- (void)requestData {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer = [AFHTTPRequestSerializer serializer];
    manager.responseSerializer = [AFImageResponseSerializer serializer];
    manager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"application/json", @"text/plain", @"text/javascript", @"text/json", @"text/html",@"multipart/form-data",@"application/x-www-form-urlencoded", nil];
    // 2.设置非校验证书模式
    manager.securityPolicy = [AFSecurityPolicy policyWithPinningMode:AFSSLPinningModeNone];
    manager.securityPolicy.allowInvalidCertificates = YES;
    [manager.securityPolicy setValidatesDomainName:NO];
    [manager POST:[NSString stringWithFormat:@"%@/%@",[DomainNameSave shareDomainNameSave].domainName,kLogout] parameters:nil progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        self.networkState = NetworkStateSuccess;
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        self.networkState = NetworkStateConnectFail;
        self.errorMessage = TRLocalizedString(@"networking_connectFailed");
    }];
}
@end
