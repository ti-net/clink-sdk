//
//  NSDate+Common.h
//  TIMClientKit
//
//  Created by 赵言 on 2020/10/29.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSDate (Common)

+ (NSDate *)dateWithTimeStamp:(double)timestamp;

//获取天数索引
- (int)dayIndexSince1970;
- (int)dayIndexSinceNow;
- (int)dayIndexSinceDate:(NSDate *)date;

//生成timestamp
- (NSString *)timestamp;

//返回年份
- (NSString *)yearString;

//返回星期的字符串
- (NSString *)weekDayString;
//返回月份的字符串
- (NSString *)monthString;

//获取字符串
- (NSString *)string;
- (NSString *)stringWithDateFormat:(NSString *)format;

//格式化日期 精确到天或小时
- (NSDate *)dateAccurateToDay;
- (NSDate *)dateAccurateToHour;

//计算年龄
- (NSInteger)age;

//判断2个日期是否在同一天
- (BOOL)isSameDayWithDate:(NSDate *)date;
- (BOOL)isToday;

//忽略年月日
- (NSDate *)dateRemoveYMD;

- (NSDateComponents *)allDateComponent;

//加上时区偏移
- (NSDate *)changeZone;

@end

NS_ASSUME_NONNULL_END
