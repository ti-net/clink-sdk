//
//  ImitateServerAuthRequest.m
//  TIMClientDemo
//
//  Created by YanBo on 2020/5/11.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "BaseUrlRequest.h"
#import "ImitateServerAuthRequest.h"

@implementation ImitateServerAuthRequest{
    NSDictionary *_dict;
}

- (instancetype)initWithDict:(NSDictionary *)dict {
    if (self = [super init]) {
        _dict = dict;
    }
    return self;
}

- (id)requestArgument {
    return _dict;
}

- (NSTimeInterval)requestTimeoutInterval{
    return 5;
}

- (YTKRequestMethod)requestMethod {
    return YTKRequestMethodPOST;
}

- (NSString *)requestUrl {
    return kImitateServerAuth;
}

@end
