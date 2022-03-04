//
//  MineUploadAvatarModel.m
//  mobileCMS
//
//  Created by YanBo on 2020/1/19.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "MineUploadAvatarModel.h"
#import <AFNetworking/AFNetworking.h>
#import "NSDate+TimeFormatting.h"

@implementation MineUploadAvatarModel
- (YTKRequestMethod)requestMethod {
    return YTKRequestMethodPOST;
}

- (NSString *)requestUrl {
    return kUploadAvatar;
}

- (AFConstructingBlock)constructingBodyBlock {
    return ^(id<AFMultipartFormData> formData) {
        
        NSData *data = UIImageJPEGRepresentation(self.avatarImage, .1f);
        NSString *fileName = [NSString stringWithFormat:@"%@.jpg",[[NSDate date] stringFromDateWithFormat:@"yyyyMMddHHmmss"]];
        NSString *name = @"file";
        NSString *type = @"image/jpg";
        [formData appendPartWithFileData:data name:name fileName:fileName mimeType:type];
    };
}

- (void)requestData {
    [self startWithCompletionBlockWithSuccess:^(__kindof YTKBaseRequest * _Nonnull request) {
        if ([request.responseObject[@"status"] isEqual:@200]) {
            YYImageCache *cache = [YYWebImageManager sharedManager].cache;
            [cache.memoryCache removeAllObjects];
            [cache.diskCache removeAllObjects];
            self.avatarFileName = [request.responseObject by_ObjectForKey:@"result"]?:@"";
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

- (NSTimeInterval)requestTimeoutInterval {
    return kImageUploadNetworkRequestTimeout;
}

@end
