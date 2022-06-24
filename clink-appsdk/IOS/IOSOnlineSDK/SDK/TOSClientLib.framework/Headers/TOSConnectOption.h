//
//  TOSConnectOption.h
//  TOSClientLib
//
//  Created by 高延波 on 2022/6/23.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TOSConnectOption : NSObject

/**
 访客Id
 */
@property(nonatomic,readonly) NSString * visitorId;

/**
 昵称
 */
@property(nonatomic, readonly) NSString *nickname;

/**
 头像url
 */
@property(nonatomic, readonly) NSString *headUrl;

/**
 手机号
 */
@property(nonatomic, readonly) NSString *mobile;

/**
 附加参数
 */
@property(nonatomic, readonly) NSDictionary *advanceParams;

/**
 参数对象初始化方法
 @return                参数对象
 */

- (instancetype)initWithOption:(NSString *)visitorId nickname:(NSString *)nickname headUrl:(NSString *)headUrl mobile:(NSString *)mobile advanceParams:(NSDictionary *)advanceParams;
@end

NS_ASSUME_NONNULL_END
