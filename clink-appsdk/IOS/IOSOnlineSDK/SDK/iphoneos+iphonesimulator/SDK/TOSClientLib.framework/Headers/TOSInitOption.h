//
//  TOSInitOption.h
//  TOSClientLib
//
//  Created by 高延波 on 2022/6/23.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TOSInitOption : NSObject

/**
 是否开启debug
 */
@property(nonatomic, readonly) BOOL debug;

/**
 平台apiUrl
 */
@property(nonatomic,readonly) NSString * apiUrl;

/**
 平台OnlineUrl
 */
@property(nonatomic, readonly) NSString *onlineUrl;

/**
 访问标识
 */
@property(nonatomic,readonly) NSString * accessId;

/**
 访问秘钥
 */
@property(nonatomic,readonly) NSString * accessSecret;

/**
 企业号码
 */
@property(nonatomic, readonly) NSString *enterpriseId;

/**
 附加参数
 */
@property(nonatomic, readonly) NSDictionary *advanceParams;

/**
 参数对象初始化方法
 @return                参数对象
 */
- (instancetype)initWithOption:(BOOL)debug apiUrl:(NSString *)apiUrl onlineUrl:(NSString *)onlineUrl accessId:(NSString *)accessId accessSecret:(NSString *)accessSecret enterpriseId:(NSString *)enterpriseId advanceParams:(NSDictionary *)advanceParams;

@end

NS_ASSUME_NONNULL_END
