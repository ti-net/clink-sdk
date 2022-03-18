//
//  MineProfileViewModel.m
//  mobileCMS
//
//  Created by YanBo on 2020/1/16.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "MineProfileViewModel.h"

@implementation MineProfileViewModel

- (id)requestArgument {
    NSMutableDictionary *parameter = [NSMutableDictionary dictionaryWithDictionary:@{}];
    if (self.name && self.name.length > 0) {
        [parameter setObject:self.name?:@"" forKey:@"name"];
    }
    if (self.nickName && self.nickName.length > 0) {
        [parameter setObject:self.nickName?:@"" forKey:@"nickname"];
    }
    if (self.phone && self.phone.length > 0) {
        [parameter setObject:self.phone?:@"" forKey:@"phone"];
    }
    if (self.avatar && self.avatar.length > 0) {
        [parameter setObject:self.avatar?:@"" forKey:@"avatar"];
    }
    if (self.keepCCOnline) {
        [parameter setValue:self.keepCCOnline?:@(0) forKey:@"keepCCOnline"];
    }
    return parameter;
}

- (YTKRequestMethod)requestMethod {
    return YTKRequestMethodPUT;
}

- (NSString *)requestUrl {
    return kGetMineInfo;
}

- (id)jsonValidator {
    return @{
             @"status": [NSNumber class],
             @"message": [NSString class]
             };
}


- (void)requestData {
    [self startWithCompletionBlockWithSuccess:^(__kindof YTKBaseRequest * _Nonnull request) {
        if ([request.responseObject[@"status"] isEqual:@200]) {
            self.networkState = NetworkStateSuccess;
        } else {
            self.errorMessage = request.responseObject[@"message"];
            self.networkState = NetworkStateFail;
        }
    } failure:^(__kindof YTKBaseRequest * _Nonnull request) {
        self.networkState = NetworkStateConnectFail;
        self.connectError = [ErrorModel yy_modelWithJSON:request.responseObject];
        self.errorMessage = [request.responseObject by_ObjectForKey:@"message"] ? : TRLocalizedString(@"networking_connectFailed");
    }];
}

@end
