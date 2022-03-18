//
//  GetClientBindTelViewModel.m
//  mobileCMS
//
//  Created by 赵言 on 2020/4/4.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "GetClientBindTelViewModel.h"
#import "BindTelModel.h"

@implementation GetClientBindTelViewModel
- (YTKRequestMethod)requestMethod {
    return YTKRequestMethodGET;
}

- (NSString *)requestUrl {
    return kGetClientBindTel;
}

- (id)requestArgument {
    return @{
        @"telType": @"1",
    };
}

- (void)requestData {
    
    [self startWithCompletionBlockWithSuccess:^(__kindof YTKBaseRequest * _Nonnull request) {
        if ([request.responseObject[@"status"] isEqual:@200] &&
            [request.responseObject[@"result"] isKindOfClass:[NSArray class]]) {
            NSArray <BindTelModel *>*array = [NSArray yy_modelArrayWithClass:[BindTelModel class] json:request.responseObject[@"result"]];
            if (array && array.count > 0) {
                [array enumerateObjectsUsingBlock:^(BindTelModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    if (obj.isBind.integerValue == 1) {
                        self.tel = obj.tel?:@"";
                        *stop = YES;
                    }
                }];
            } else {
                self.tel = @"";
            }
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
