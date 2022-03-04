//
//  GetTIMAccessTokenViewModel.m
//  TIMClientKitDemo
//
//  Created by YanBo on 2020/6/3.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "GetTIMAccessTokenViewModel.h"
#import "LoginModel.h"

@implementation GetTIMAccessTokenViewModel

- (NSDictionary *)requestHeaderFieldValueDictionary {
    return @{
        @"Token":[LoginModel loginModel].loginToken
    };
}

- (YTKRequestMethod)requestMethod {
    return YTKRequestMethodGET;
}

- (NSString *)requestUrl {
    return kGetIMUser;
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
//            loginModel.appId = @"20000002";
            loginModel.timAccessToken = responseObject[@"data"][@"accessToken"]; //@"d8626e2f-b228-4b18-9dfe-32c1ea9bd5bb";
            loginModel.timUserId = responseObject[@"data"][@"userId"]?:@""; // @"10294437";
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
