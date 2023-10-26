//
//  NSDate+TimeFormatting.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/15.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "NSDate+TimeFormatting.h"

@implementation NSDate (TimeFormatting)

- (NSString *)stringFromDateWithFormat:(NSString *)format {
    NSDateFormatter *df = [[NSDateFormatter alloc] init];
    NSTimeZone *timeZone = [NSTimeZone timeZoneWithName:@"Asia/Beijing"];
    [df setTimeZone:timeZone];
    df.dateFormat = format;
    return [df stringFromDate:self];
}

- (NSInteger)timeSwitchTimestamp {
    return [[NSNumber numberWithDouble:[self timeIntervalSince1970]] integerValue];
}

@end
