//
//  TIMOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/17.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/*
 初始化参数对象
 */

@interface TIMInitOption : NSObject

/**
 是否是调试环境 只读
 */
@property(nonatomic,readonly) BOOL debug;

/**
 是否是新版接口  只读
 */
@property(nonatomic,readonly) BOOL isApiVersion2;

/**
 api服务器地址 只读
 */
@property(nonatomic, readonly) NSString *apiUrl;


/**
 默认参数对象初始化方法

 @return                参数对象
 */
- (instancetype)defaultOption;


/**
 参数对象初始化方法

 @param debug                        是否是调试环境
 @param apiUrl                      api服务器地址
 @return                参数对象
 */
- (instancetype)initWithOption:(BOOL)debug apiUrl:(NSString *)apiUrl isApiVersion2:(BOOL)isApiVersion2;


@end

NS_ASSUME_NONNULL_END
