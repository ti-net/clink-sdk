//
//  ImitateServerAuthRequest.h
//  TIMClientDemo
//
//  Created by YanBo on 2020/5/11.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <YTKRequest.h>
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface ImitateServerAuthRequest : YTKRequest

/**
 用户登录
 */
- (instancetype)initWithDict:(NSDictionary *)dict;

@end

NS_ASSUME_NONNULL_END
