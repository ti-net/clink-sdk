//
//  appUtils.h
//  TIMClientDemo
//
//  Created by YanBo on 2020/5/12.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

#define TIMKitLog(format,...)  [kitUtils customLogWithFunction:__FUNCTION__ lineNumber:__LINE__ formatString:[NSString stringWithFormat:format, ##__VA_ARGS__]]

NS_ASSUME_NONNULL_BEGIN

@interface kitUtils : NSObject
// 设置日志输出状态
+ (void)setLogEnable:(BOOL)enable;

// 设置环境 为了判断KT
+ (void)setEnvConf:(NSString *)env;

// 获取环境 为了判断KT
+ (NSString*)getEnvConf;

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

/// ⚠️这个方法有问题，会在客户的app中造成不准确的问题，后续不要使用这个类方法⚠️
+ (UIViewController *)XG_GetController;

+ (UIViewController *)XG_GetCurrentViewControllerFrom:(UIViewController *)rootViewController;

/// 字典转字符串
/// @param dic 字典
+ (NSString *)convertToJsonDataWithDic:(NSDictionary *)dic;

/// 判断富文本的标签是否属于文本类型
/// @param type 类型
+ (BOOL)isTextTypeLabel:(NSString *)type;
@end

NS_ASSUME_NONNULL_END
