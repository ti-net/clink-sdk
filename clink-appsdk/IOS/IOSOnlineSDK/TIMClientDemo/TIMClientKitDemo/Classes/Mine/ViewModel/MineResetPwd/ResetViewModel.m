//
//  ResetViewModel.m
//  mobileCMS
//
//  Created by YanBo on 2020/1/16.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "ResetViewModel.h"

@interface ResetViewModel ()

@end

@implementation ResetViewModel

- (id)requestArgument {
    return @{
        @"oldPassword":self.oldPassword,
        @"newPassword":self.nPassword
    };
}

- (YTKRequestMethod)requestMethod {
    return YTKRequestMethodPUT;
}

- (NSString *)requestUrl {
    return kGetMineInfo;
}

/// 验证服务器返回的数据是否存在
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
