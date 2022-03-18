//
//  GetTcbusAccessTokenViewModel.m
//  TIMClientKitDemo
//
//  Created by YanBo on 2020/9/22.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "GetTcbusAccessTokenViewModel.h"
#import "LoginModel.h"

@implementation GetTcbusAccessTokenViewModel
- (NSDictionary *)requestHeaderFieldValueDictionary {
    return @{
        @"Token":[LoginModel loginModel].loginToken
    };
}

- (YTKRequestMethod)requestMethod {
    return YTKRequestMethodGET;
}

- (NSString *)requestUrl {
    return kGetTcbusUser;
}

- (void)requestData {
    
    [self startWithCompletionBlockWithSuccess:^(__kindof YTKBaseRequest * _Nonnull request) {
        id responseObject = request.responseObject;
        NSLog(@"get accessToken result = %@",responseObject);
        if ([responseObject[@"result"] isEqualToString:@"0"] &&
            [[responseObject allKeys] containsObject:@"data"] &&
            [responseObject[@"data"] dictionaryContainsKey:@"accessToken"]) {
            // 登录成功
            LoginModel *loginModel = [LoginModel loginModel];
            loginModel.isLogin = YES;
            loginModel.timAccessToken = responseObject[@"data"][@"accessToken"];
            if ([responseObject[@"data"] dictionaryContainsKey:@"userId"]) {
                loginModel.timUserId = responseObject[@"data"][@"userId"]?:@"";
            }
            
            [loginModel saveLoginModel:loginModel];
            self.networkState = NetworkStateSuccess;
        } else {
            self.networkState = NetworkStateFail;
            self.errorMessage = request.responseObject[@"description"];
        }
    } failure:^(__kindof YTKBaseRequest * _Nonnull request) {
        NSLog(@"get accessToken error = %@",request.responseObject);
         self.networkState = NetworkStateConnectFail;
         self.connectError = [ErrorModel yy_modelWithJSON:request.responseObject];
        self.errorMessage = [request.responseObject by_ObjectForKey:@"description"] ? :@"网络请求错误，请检查网络";
    }];
}
@end
