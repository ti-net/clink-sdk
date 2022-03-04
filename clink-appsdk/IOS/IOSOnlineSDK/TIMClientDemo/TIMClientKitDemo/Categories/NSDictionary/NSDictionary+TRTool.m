//
//  NSDictionary+TRTool.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/25.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "NSDictionary+TRTool.h"

@implementation NSDictionary (TRTool)

+ (NSDictionary *)readPlistFileWithFileName:(NSString *)fileName {
    NSDictionary *dic = [NSDictionary dictionaryWithContentsOfFile:[[NSBundle mainBundle] pathForResource:fileName ofType:@"plist"]];
    return dic;
}

- (id)by_ObjectForKey:(NSString *)key {
    if (self && [self isKindOfClass:[NSDictionary class]] && [[self allKeys] containsObject:key]) {
        return [self objectForKey:key];
    } else {
        return nil;
    }
}

- (NSString *)convertToJsonData {
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:self options:NSJSONWritingPrettyPrinted error:&error];
    NSString *jsonString;
    if (!jsonData) {
        NSLog(@"%@",error);
    } else {
        jsonString = [[NSString alloc]initWithData:jsonData encoding:NSUTF8StringEncoding];
    }
    NSMutableString *mutStr = [NSMutableString stringWithString:jsonString];
    NSRange range = {0,jsonString.length};
    //去掉字符串中的空格
    [mutStr replaceOccurrencesOfString:@" " withString:@"" options:NSLiteralSearch range:range];
    NSRange range2 = {0,mutStr.length};
    //去掉字符串中的换行符
    [mutStr replaceOccurrencesOfString:@"\n" withString:@"" options:NSLiteralSearch range:range2];
    return mutStr;
}

@end
