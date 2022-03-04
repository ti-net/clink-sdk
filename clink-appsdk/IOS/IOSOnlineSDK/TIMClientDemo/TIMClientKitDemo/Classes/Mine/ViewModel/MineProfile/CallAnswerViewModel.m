//
//  CallAnswerViewModel.m
//  mobileCMS
//
//  Created by 赵言 on 2020/4/6.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "CallAnswerViewModel.h"

@implementation CallAnswerViewModel
- (YTKRequestMethod)requestMethod {
    return YTKRequestMethodPOST;
}

- (NSString *)requestUrl {
    if (self.isBusy) { //置闲
        return kCallUnpause;
    } else {           //置忙
        return kCallPause;
    }
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
