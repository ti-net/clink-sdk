//
//  OnlineInitOption.h
//  TIMClientLib
//
//  Created by apple on 2021/10/29.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface OnlineInitOption : NSObject

+ (OnlineInitOption *)shareOnlineInitOption;

/**
 参数对象初始化方法

 @param debug                        是否是调试环境
 @param apiUrl                      api服务器地址
 */
- (void)initWithOptionIsDebug:(BOOL)debug
                       apiUrl:(NSString *)apiUrl
                     onlineUrl:(NSString *)onlineUrl
                  accessSecret:(NSString *)accessSecret
                      accessId:(NSString *)accessId
                 enterpriseId:(NSString *)enterpriseId;
                
@end

NS_ASSUME_NONNULL_END
