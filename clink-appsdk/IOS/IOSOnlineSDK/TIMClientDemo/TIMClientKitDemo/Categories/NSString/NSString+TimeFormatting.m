//
//  NSString+TimeFormatting.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/15.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "NSString+TimeFormatting.h"

@implementation NSString (TimeFormatting)

- (NSDate *)dateFromStringWithFormat:(NSString *)format {
    NSDateFormatter *df = [[NSDateFormatter alloc] init];
    //设置时区,这个对于时间的处理有时很重要
    NSTimeZone *timeZone = [NSTimeZone timeZoneWithName:@"Asia/Beijing"];
    [df setTimeZone:timeZone];
    
    df.dateFormat = format;
    return [df dateFromString:self];
}

- (NSString *)formattingDateStringWithOldFormatter:(NSString *)oldFormatter newFormatter:(NSString *)newFormatter {
    if (self) {
        return [[self dateFromStringWithFormat:oldFormatter] stringWithFormat:newFormatter];
    } else {
        return @"";
    }
}

- (NSString *)getTimestampStringWithFormat:(NSString *)format {
    NSTimeInterval time = [self doubleValue]; // 传入的时间戳str如果是精确到毫秒的记得要/1000
    NSDate *detailDate = [NSDate dateWithTimeIntervalSince1970:time];
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init]; // 实例化一个NSDateFormatter对象
    NSTimeZone *timeZone = [NSTimeZone timeZoneWithName:@"Asia/Beijing"];
    [dateFormatter setTimeZone:timeZone];
    // 设定时间格式,这里可以设置成自己需要的格式
    [dateFormatter setDateFormat:format];
    NSString *currentDateStr = [dateFormatter stringFromDate:detailDate];
    return currentDateStr;
}

- (NSInteger)timeSwitchTimestampFormatter:(NSString *)format {
    
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:format];
    NSTimeZone *timeZone = [NSTimeZone timeZoneWithName:@"Asia/Beijing"];
    [formatter setTimeZone:timeZone];
    NSDate *date = [formatter dateFromString:self]; //------------将字符串按formatter转成nsdate
    
    //时间转时间戳的方法
    NSInteger timeSp = [[NSNumber numberWithDouble:[date timeIntervalSince1970]] integerValue];
    return timeSp;
}

@end
