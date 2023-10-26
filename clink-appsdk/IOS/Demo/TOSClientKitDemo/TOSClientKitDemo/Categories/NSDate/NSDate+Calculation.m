//
//  NSDate+Calculation.m
//  SmartHome
//
//  Created by 赵言 on 2019/10/11.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "NSDate+Calculation.h"

@implementation NSDate (Calculation)

- (NSDate *)getDateWithYear:(NSInteger)year month:(NSInteger)month day:(NSInteger)day {
    NSCalendar *calendar = [[NSCalendar alloc] initWithCalendarIdentifier:NSCalendarIdentifierGregorian];
    NSDateComponents *adcomps = [[NSDateComponents alloc] init];
    [adcomps setYear:year];
    [adcomps setMonth:month];
    [adcomps setDay:day];
    return [calendar dateByAddingComponents:adcomps toDate:self options:0];
}

- (NSString *)weekdayStringFromDateWithWeek:(NSArray *)weak {
    NSCalendar *calendar = [[NSCalendar alloc] initWithCalendarIdentifier:NSCalendarIdentifierGregorian];
    NSCalendarUnit calendarUnit = NSCalendarUnitWeekday;
    NSDateComponents *theComponents = [calendar components:calendarUnit fromDate:self];
    return [weak objectAtIndex:theComponents.weekday];
}

- (NSDate *)getWeekTimeWithIsFirst:(BOOL)isFirst {
    NSCalendar *calendar = [NSCalendar currentCalendar];
    NSDateComponents *comp = [calendar components:NSCalendarUnitYear | NSCalendarUnitMonth | NSCalendarUnitDay | NSCalendarUnitWeekday fromDate:self];
    // 获取今天是周几
    NSInteger weekDay = [comp weekday];
    // 获取几天是几号
    NSInteger day = [comp day];
    NSLog(@"%ld----%ld",(long)weekDay,(long)day);

    // 计算当前日期和本周的星期一和星期天相差天数
    long firstDiff,lastDiff;
    //    weekDay = 1; weekDay == 1 == 周日
    if (weekDay == 1) {
        firstDiff = -6;
        lastDiff = 0;
    } else {
        firstDiff = [calendar firstWeekday] - weekDay + 1;
        lastDiff = 8 - weekDay;
    }
    NSLog(@"firstDiff: %ld   lastDiff: %ld",firstDiff,lastDiff);
    
    // 在当前日期(去掉时分秒)基础上加上差的天数
    NSDateComponents *baseDayComp = [calendar components:NSCalendarUnitYear | NSCalendarUnitMonth | NSCalendarUnitDay fromDate:self];
    
    if (isFirst) {
        //获取周一日期
        [baseDayComp setDay:day + firstDiff];
        NSDate *firstDayOfWeek = [calendar dateFromComponents:baseDayComp];
        return firstDayOfWeek;
    } else {
        //获取周末日期
        [baseDayComp setDay:day + lastDiff];
        NSDate *lastDayOfWeek = [calendar dateFromComponents:baseDayComp];
        return lastDayOfWeek;
    }
}

@end
