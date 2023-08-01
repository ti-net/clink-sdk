//
//  NSString+TIMExtension.h
//  TIMExtensionExample
//
//  Created by MJ Lee on 15/6/7.
//  Copyright (c) 2015年 小码哥. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMExtensionConst.h"

@interface NSString (TIMExtension)
/**
 *  驼峰转下划线（loveYou -> love_you）
 */
- (NSString *)mjtim_underlineFromCamel;
/**
 *  下划线转驼峰（love_you -> loveYou）
 */
- (NSString *)mjtim_camelFromUnderline;
/**
 * 首字母变大写
 */
- (NSString *)mjtim_firstCharUpper;
/**
 * 首字母变小写
 */
- (NSString *)mjtim_firstCharLower;

- (BOOL)mjtim_isPureInt;

- (NSURL *)mjtim_url;
@end

@interface NSString (TIMExtensionDeprecated_v_2_5_16)
- (NSString *)underlineFromCamel TIMExtensionDeprecated("请在方法名前面加上mj_前缀，使用mj_***");
- (NSString *)camelFromUnderline TIMExtensionDeprecated("请在方法名前面加上mj_前缀，使用mj_***");
- (NSString *)firstCharUpper TIMExtensionDeprecated("请在方法名前面加上mj_前缀，使用mj_***");
- (NSString *)firstCharLower TIMExtensionDeprecated("请在方法名前面加上mj_前缀，使用mj_***");
- (BOOL)isPureInt TIMExtensionDeprecated("请在方法名前面加上mj_前缀，使用mj_***");
- (NSURL *)url TIMExtensionDeprecated("请在方法名前面加上mj_前缀，使用mj_***");
@end
