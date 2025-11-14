//
//  NSString+TimeFormatting.h
//  SmartHome
//
//  Created by 赵言 on 2019/7/15.
//  Copyright © 2019 赵言. All rights reserved.
//  时间格式化

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSString (TimeFormatting)

/**
 字符串转NSDate

 @param format 时间格式，如：yyyy-MM-dd HH:mm:ss
 @return 时间Date
 */
- (NSDate *)dateFromStringWithFormat:(NSString *)format;

/// 格式化日期字符串
/// @param oldFormatter 旧的格式，如：yyyy-MM-dd HH:mm:ss
/// @param newFormatter 新的格式，如：yyyy-MM-dd HH:mm:ss
- (NSString *)formattingDateStringWithOldFormatter:(NSString *)oldFormatter newFormatter:(NSString *)newFormatter;

/// 时间戳转时间字符串
/// @param format 格式，如：yyyy-MM-dd HH:mm:ss
- (NSString *)getTimestampStringWithFormat:(NSString *)format;

/// 时间转时间戳
/// @param format 格式，如：yyyy-MM-dd HH:mm:ss
- (NSInteger)timeSwitchTimestampFormatter:(NSString *)format;

@end

NS_ASSUME_NONNULL_END
