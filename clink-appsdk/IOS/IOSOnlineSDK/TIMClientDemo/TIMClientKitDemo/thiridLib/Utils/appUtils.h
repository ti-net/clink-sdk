//
//  appUtils.h
//  TIMClientDemo
//
//  Created by YanBo on 2020/5/12.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

#define XZRGB(rgbValue) [UIColor colorWithRed:((float)((rgbValue & 0xFF0000) >> 16)) / 255.0 green:((float)((rgbValue & 0xFF00) >> 8)) / 255.0 blue:((float)(rgbValue & 0xFF)) / 255.0 alpha:1.0]

#define Global_tintColor [UIColor colorWithRed:0 green:(190 / 255.0) blue:(12 / 255.0) alpha:1]

#define WeakObj(o) autoreleasepool{} __weak typeof(o) o##Weak = o;  //宏定义self
#define StrongObj(o) autoreleasepool{} __strong typeof(o) o = o##Weak;

NS_ASSUME_NONNULL_BEGIN

@interface appUtils : NSObject

+(NSString*)formattedDuration:(NSTimeInterval)interval;

// dictionary to NSString
+ (NSString*)DataTOjsonString:(id)object;

// NSString to Dictionary
+ (NSDictionary *)dictionaryWithJsonString:(NSString *)jsonString;

// 字符串判空
+ (BOOL)isBlankString:(NSString *)str;

// 随机字符串 默认10位
+ (NSString *)RandomString;

+(NSString *)getNowTimestampWithSec;
@end

NS_ASSUME_NONNULL_END
