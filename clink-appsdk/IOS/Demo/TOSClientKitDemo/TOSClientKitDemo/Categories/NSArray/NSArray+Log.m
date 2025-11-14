//
//  NSArray+Log.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/4.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "NSArray+Log.h"

@implementation NSArray (Log)

//- (NSString *)descriptionWithLocale:(id)locale indent:(NSUInteger)level {
//    NSMutableString *mutString = [NSMutableString stringWithString:@"\n[\n"];
//    [self enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
//        [mutString appendFormat:@"\t%@\n",obj];
//    }];
//    [mutString stringByAppendingString:@"]\n"];
//    return  mutString.copy;
//}

//- (NSString *)descriptionWithLocale:(id)locale {
//
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
//    [strM appendString:@"(\n"];
//
//    for (id obj in self) {
//        for (int i = 0; i < count + 1; i++) {
//            [strM appendFormat:@"%@",@"  "];
//        }
//        if ([obj isKindOfClass:[NSString class]]) {
//            [strM appendFormat:@"\"%@\"\n", obj];
//        } else {
//        [strM appendFormat:@"%@\n", obj];
//        }
//    }
//
//    for (int i = 0; i < count; i++) {
//        [strM appendFormat:@"%@",@"  "];
//    }
//
//    [strM appendString:@")"];
//    count--;
//    [users setInteger:count forKey:@"nslogCount"];
//    [users synchronize];
//
//    return strM;
//}

@end
