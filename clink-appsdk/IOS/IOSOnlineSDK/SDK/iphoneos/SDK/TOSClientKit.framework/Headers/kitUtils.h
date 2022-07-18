//
//  appUtils.h
//  TIMClientDemo
//
//  Created by YanBo on 2020/5/12.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

#import "TOSKitCustomInfo.h"

#define XZRGB(rgbValue) [UIColor colorWithRed:((float)((rgbValue & 0xFF0000) >> 16)) / 255.0 green:((float)((rgbValue & 0xFF00) >> 8)) / 255.0 blue:((float)(rgbValue & 0xFF)) / 255.0 alpha:1.0]

#define Global_tintColor [UIColor colorWithRed:0 green:(190 / 255.0) blue:(12 / 255.0) alpha:1]

#define TIMKitLog(format,...)  [kitUtils customLogWithFunction:__FUNCTION__ lineNumber:__LINE__ formatString:[NSString stringWithFormat:format, ##__VA_ARGS__]]

NS_ASSUME_NONNULL_BEGIN

@interface kitUtils : NSObject
// 设置日志输出状态
+ (void)setLogEnable:(BOOL)enable;

// 获取日志输出状态
+ (BOOL)getLogEnable;

// 日志输出方法
+ (void)customLogWithFunction:(const char *)function lineNumber:(int)lineNumber formatString:(NSString *)formatString;

// 生成随机uuid
+ (NSString *)getMsgUUID;

+ (NSString *)getNowTimestampWithSec;

+ (NSString *)md5StringFromString:(NSString *)string ;

+ (NSString *)RandomString;

+ (NSString*)sha256HashFor:(NSString*)input;

+ (NSString *)getDeviceUDID;

+ (NSString *)removeTheLastOneStr:(NSString*)string;

+(NSString*)formattedDuration:(NSTimeInterval)interval;

+(NSDate *)getDateTimeFromMilliSeconds:(long long)miliSeconds;

// 时间戳转时间
+ (NSString *)getTimestampString:(long long)timestamp;

// dictionary to NSString
+ (NSString*)DataTOjsonString:(id)object;

// NSString to Dictionary
+ (NSDictionary *)dictionaryWithJsonString:(NSString *)jsonString;

// 字符串判空
+ (BOOL)isBlankString:(NSString *)str;

// 时间戳转时间格式
+(NSString *)dateStringFromNumberTimer:(NSString *)timerStr;

/// 获取NSDate的字符串格式
/// @param date 时间
/// @param format 类型，@"YYYY-MM-dd HH:mm:ss"
+ (NSString *)getDateString:(NSDate *)date dateFormat:(NSString *)format;

// 秒数值 转 时分秒格式
+(NSString *)transferHHMMSSFromSS:(int)totalSeconds;

// 判断是否是当天
+ (BOOL)isSameDay:(long)iTime1 Time2:(long)iTime2;

+(NSString *)readNSUserDefaultsVoiceReadStatus:(NSString*)msgId from:(NSString*)from to:(NSString *)to;
+(void)saveNSUserDefaults:(NSDictionary *)Dictionary;

// 获取url中的fileId
+ (NSString *)getFileIdFromUrl:(NSString *)fileUrl;

// 音视频的字串转换 stopAction
+ (NSString *)transferStopAction:(NSString *)stopAction isSender:(BOOL)isSender duration:(int)duration;


+ (UIImage *)imageNamed:(NSString *)name ofBundle:(NSString *)bundleName;

+ (NSString *)mimeTypeForFileAtPath:(NSString *)path;

+ (NSDictionary *)getPlistFile:(NSString *)plistName;

+ (UIViewController *)XG_GetController;

+ (UIViewController *)XG_GetCurrentViewControllerFrom:(UIViewController *)rootViewController;
@end

NS_ASSUME_NONNULL_END
