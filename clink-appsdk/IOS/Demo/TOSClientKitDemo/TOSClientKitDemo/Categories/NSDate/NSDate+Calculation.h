//
//  NSDate+Calculation.h
//  SmartHome
//
//  Created by 赵言 on 2019/10/11.
//  Copyright © 2019 赵言. All rights reserved.
//  时间计算

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSDate (Calculation)

//计算一天以内的时间
//[NSDate dateWithTimeInterval:24*60*60*i sinceDate:self.currentDate]


/// 获取当前时间之前或之后的日期
/// @param year  年  正的是之后，负的是之前
/// @param month 月  正的是之后，负的是之前
/// @param day   日  正的是之后，负的是之前
- (NSDate *)getDateWithYear:(NSInteger)year month:(NSInteger)month day:(NSInteger)day;


/// 获取当前日期的星期
/// @param weak [NSArray arrayWithObjects: [NSNull null], @"周日", @"周一", @"周二", @"周三", @"周四", @"周五", @"周六", nil]
- (NSString *)weekdayStringFromDateWithWeek:(NSArray *)weak;

/// 获取当前日期所在周的周一和周日日期
/// @param isFirst 周一日期
- (NSDate *)getWeekTimeWithIsFirst:(BOOL)isFirst;

@end

NS_ASSUME_NONNULL_END
