//
//  NSBundle+TIMRefresh.h
//  TIMRefreshExample
//
//  Created by MJ Lee on 16/6/13.
//  Copyright © 2016年 小码哥. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSBundle (TIMRefresh)
+ (instancetype)mj_refreshTIMBundle;
+ (UIImage *)mj_arrowTIMImage;
+ (NSString *)tim_mj_localizedStringForTIMKey:(NSString *)key value:(nullable NSString *)value;
+ (NSString *)tim_mj_localizedStringForTIMKey:(NSString *)key;
@end

NS_ASSUME_NONNULL_END
