//
//  GetMineInfoViewModel.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/26.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "GetMineInfoViewModel.h"
#import "MineInfoModel.h"

@implementation GetMineInfoViewModel
- (YTKRequestMethod)requestMethod {
    return YTKRequestMethodGET;
}

- (NSString *)requestUrl {
    return kGetMineInfo;
}

- (void)requestData {
    
    [self startWithCompletionBlockWithSuccess:^(__kindof YTKBaseRequest * _Nonnull request) {
        if ([request.responseObject[@"status"] isEqual:@200] && [[request.responseObject allKeys] containsObject:@"result"]) {
            MineInfoModel *model = [MineInfoModel shareMineInfoModel];
            model = [MineInfoModel yy_modelWithJSON:request.responseObject[@"result"]];
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
