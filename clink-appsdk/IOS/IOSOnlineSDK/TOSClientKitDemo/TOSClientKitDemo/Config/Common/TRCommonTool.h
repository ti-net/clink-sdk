//
//  TRCommonTool.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN
@interface TRCommonTool : NSObject

#pragma mark - 其他
// md5 小写
+ (NSString *)md5:(NSString *)string;

/**
 获取当前控制器

 @return 返回当前控制器
 */
+ (UIViewController *)getCurrentVC;

/// 秒 -> 时分秒
/// @param totalSeconds 秒
+ (NSString *)timeFormatted:(NSInteger)totalSeconds;

/// 秒 -> 时分秒
/// @param totalSeconds 秒
+ (NSString *)callTimeFormatted:(NSInteger)totalSeconds;

/// Push页面淡出淡入动画
+ (void)pushViewCAnimation;

@end

NS_ASSUME_NONNULL_END
