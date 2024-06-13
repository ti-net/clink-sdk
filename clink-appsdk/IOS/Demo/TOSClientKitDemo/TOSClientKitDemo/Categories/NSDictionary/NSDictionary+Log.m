//
//  NSDictionary+Log.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/4.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "NSDictionary+Log.h"

@implementation NSDictionary (Log)

//- (NSString *)descriptionWithLocale:(id)locale indent:(NSUInteger)level {
//    NSMutableString * mutString = [NSMutableString stringWithString:@"\n{\n"];
//    [self enumerateKeysAndObjectsUsingBlock:^(id  _Nonnull key, id  _Nonnull obj, BOOL * _Nonnull stop) {
//        [mutString appendFormat:@"\t%@ = %@\n",key,obj];
//    }];
//    [mutString stringByAppendingString:@"\n}\n"];
//    return  mutString.copy;
//}

//- (NSString *)descriptionWithLocale:(id)locale {
//    NSUserDefaults * users = [NSUserDefaults standardUserDefaults];
//    NSInteger count = [users integerForKey:@"nslogCount"];
//    count++;
//    [users setInteger:count forKey:@"nslogCount"];
//    [users synchronize];
//
//
//    NSMutableString *strM = [NSMutableString string];
//    [strM appendFormat:@"\n"];
//    for (int i = 0; i < count; i++) {
//        [strM appendFormat:@"%@",@"  "];
//    }
//    [strM appendString:@"{\n"];
//
//    for (id obj in [self allKeys]) {
//        for (int i = 0; i < count + 1; i++) {
//            [strM appendFormat:@"%@",@"  "];
//        }
//        if ([self[obj] isKindOfClass:[NSString class]]) {
//            [strM appendFormat:@"%@ = \"%@\"\n", obj,self[obj]];
//        }else{
//        [strM appendFormat:@"%@ = %@\n", obj,self[obj]];
//        }
//    }
//
//    for (int i = 0; i < count; i++) {
//        [strM appendFormat:@"%@",@"  "];
//    }
//
//    [strM appendString:@"}"];
//    count--;
//    [users setInteger:count forKey:@"nslogCount"];
//    [users synchronize];
//
//    return strM;
//}
//
////计算@"{"出现的次数:
//- (NSUInteger)number:(NSMutableString *)str {
//    NSInteger count = 0;
//    for (int i = 0; i < [str length]; i++) {
//        char a = [str characterAtIndex:i];
//        if (a == '{') {
//            count++;
//        }
//    }
//    return count;
//}
//
//- (NSUInteger)number1:(NSMutableString *)str {
//    NSInteger count = 0;
//    for (int i = 0; i < [str length]; i++) {
//        char a = [str characterAtIndex:i];
//        if (a == '}') {
//            count++;
//        }
//    }
//    return count;
//}

@end
