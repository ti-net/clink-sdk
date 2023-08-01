//
//  appUtils.h
//  TIMClientDemo
//
//  Created by YanBo on 2020/5/12.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

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

// deviceId
+(NSString *)getDeviceUDID;

// 随机字符串 默认10位
+ (NSString *)RandomString;

+(NSString *)getNowTimestampWithSec;

+(NSString*)sha256HashFor:(NSString*)input;

+ (NSString *)md5StringFromString:(NSString *)string;


@end

NS_ASSUME_NONNULL_END
