//
//  LoginViewModel.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/10.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "LoginViewModel.h"
#import "LoginModel.h"

@interface LoginViewModel ()

@end

@implementation LoginViewModel
- (id)requestArgument {
    NSString * strNonce = [appUtils RandomString];
    NSString * timeStamp = [appUtils getNowTimestampWithSec];
    NSString * preSignature = [appUtils sha256HashFor:[NSString stringWithFormat:@"%@%@",self.userName,self.password]];
    NSString * signature = [appUtils sha256HashFor:[NSString stringWithFormat:@"%@%@%@%@", self.appId,timeStamp,strNonce,preSignature]];
    
    NSLog(@"appId=%@&username=%@&timestamp=%@&nonce=%@&signature=%@",self.appId,self.userName,timeStamp,strNonce,signature);
    return @{
        @"appId": self.appId?:@"",
        @"username": self.userName?:@"",
        @"timestamp": timeStamp,
        @"nonce": strNonce,
        @"signature": signature,
    };
}

- (YTKRequestMethod)requestMethod {
    return YTKRequestMethodGET;
}

- (NSString *)requestUrl {
    return kLogin;
}

- (void)requestData {
    [super requestData];
    
    [self startWithCompletionBlockWithSuccess:^(__kindof YTKBaseRequest * _Nonnull request) {
        NSLog(@"login result = %@",request.responseObject);
        if ([request.responseObject[@"result"] isEqualToString:@"0"] &&
            [[request.responseObject allKeys] containsObject:@"data"] &&
            [request.responseObject[@"data"] dictionaryContainsKey:@"token"]) {
            LoginModel *loginModel = [LoginModel loginModel];
            loginModel.appId = self.appId;
            loginModel.loginToken = request.responseObject[@"data"][@"token"];
            loginModel.isLogin = YES;
            [loginModel saveLoginModel:loginModel];
            self.networkState = NetworkStateSuccess;
        } else {
            self.networkState = NetworkStateFail;
            self.errorMessage = request.responseObject[@"description"];
        }
    } failure:^(__kindof YTKBaseRequest * _Nonnull request) {
        self.networkState = NetworkStateConnectFail;
        self.connectError = [ErrorModel yy_modelWithJSON:request.responseObject];
        self.errorMessage = [request.responseObject by_ObjectForKey:@"description"] ?:@"网络请求错误，请检查网络";
    }];
}

@end
