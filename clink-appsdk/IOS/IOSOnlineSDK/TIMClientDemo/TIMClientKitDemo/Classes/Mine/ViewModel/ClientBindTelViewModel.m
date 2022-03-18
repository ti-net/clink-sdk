//
//  ClientBindTelViewModel.m
//  mobileCMS
//
//  Created by 赵言 on 2020/4/6.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "ClientBindTelViewModel.h"

@implementation ClientBindTelViewModel
- (YTKRequestMethod)requestMethod {
    return YTKRequestMethodPOST;
}

- (id)requestArgument {
    return @{
        @"bindTel": self.bindTel?:@"",
        @"bindType": @"1",
    };
}

- (NSString *)requestUrl {
    return [NSString stringWithFormat:@"%@?bindTel=%@&bindType=1",kClientBindTel,self.bindTel?:@""];
}

- (void)requestData {
    [self startWithCompletionBlockWithSuccess:^(__kindof YTKBaseRequest * _Nonnull request) {
        if ([request.responseObject[@"status"] isEqual:@200]) {
            self.errorMessage = request.responseObject[@"result"][@"msg"];
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
