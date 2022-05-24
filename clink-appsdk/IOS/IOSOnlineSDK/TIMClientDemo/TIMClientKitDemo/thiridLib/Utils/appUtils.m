//
//  appUtils.m
//  TIMClientDemo
//
//  Created by YanBo on 2020/5/12.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "appUtils.h"

@implementation appUtils

+(NSString*)formattedDuration:(NSTimeInterval)interval{
    NSDateComponentsFormatter *formatter = [[NSDateComponentsFormatter alloc] init];
    formatter.allowedUnits = NSCalendarUnitHour | NSCalendarUnitMinute;
    formatter.zeroFormattingBehavior = NSDateComponentsFormatterZeroFormattingBehaviorPad;
    NSString *string = [formatter stringFromTimeInterval:interval];
    NSLog(@"%@", string);
    // output: 0:20:34
    return string;
}

+ (NSString*)DataTOjsonString:(id)object{
    NSString *jsonString = nil;
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:object options:NSJSONWritingPrettyPrinted  error:&error];
    if(!jsonData) {
        NSLog(@"Got an error: %@", error);
    } else {
        jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    }
    return jsonString;
}

+ (NSDictionary *)dictionaryWithJsonString:(NSString *)jsonString {
    if (jsonString == nil) {
        return nil;
    }
    NSData *jsonData = [jsonString dataUsingEncoding:NSUTF8StringEncoding];
    NSError *err;
    NSDictionary *dic = [NSJSONSerialization JSONObjectWithData:jsonData
                                                        options:NSJSONReadingMutableContainers
                                                          error:&err];
    if(err) {
        NSLog(@"json解析失败5：%@",err);
        return nil;
    }
    return dic;
}

// 判断为字串空
+ (BOOL)isBlankString:(NSString *)str {
    NSString *string = str;
    if (string == nil || string == NULL) {
        return YES;
    }
    if ([string isKindOfClass:[NSNull class]]) {
        return YES;
    }
    if ([string isEqualToString:@"NIL"] ||
        [string isEqualToString:@"Nil"] ||
        [string isEqualToString:@"nil"] ||
        [string isEqualToString:@"NULL"] ||
        [string isEqualToString:@"Null"] ||
        [string isEqualToString:@"null"] ||
        [string isEqualToString:@"(NULL)"] ||
        [string isEqualToString:@"(Null)"] ||
        [string isEqualToString:@"(null)"] ||
        [string isEqualToString:@"<NULL>"] ||
        [string isEqualToString:@"<Null>"] ||
        [string isEqualToString:@"<null>"] ) {
        return YES;
    }
    if ([[string stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceCharacterSet]] length]==0) {
        return YES;
    }
    
    return NO;
}

+ (NSString *)RandomString{
    //随机字符串kRandomLength位
    int kRandomLength = 10;
    static const NSString *kRandomAlphabet = @"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    NSMutableString *randomString = [NSMutableString stringWithCapacity: kRandomLength];
    for (int i = 0; i < kRandomLength; i++) {
        [randomString appendFormat: @"%C", [kRandomAlphabet characterAtIndex:arc4random_uniform((u_int32_t)[kRandomAlphabet length])]];
    }
    return randomString;
}

+(NSString *)getNowTimestampWithSec{
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateStyle:NSDateFormatterMediumStyle];
    [formatter setTimeStyle:NSDateFormatterShortStyle];
    [formatter setDateFormat:@"YYYY-MM-dd HH:mm:ss"]; // ----------设置你想要的格式,hh与HH的区别:分别表示12小时制,24小时制
    //设置时区,这个对于时间的处理有时很重要
    NSTimeZone *timeZone = [NSTimeZone timeZoneWithName:@"Asia/Shanghai"];
    [formatter setTimeZone:timeZone];
    NSDate *datenow = [NSDate date]; //现在时间,你可以输出来看下是什么格式
    NSString *timeSp = [NSString stringWithFormat:@"%lld", (long long)[datenow timeIntervalSince1970]];
    return timeSp;
}


@end
