//
//  NSString+TRTool.m
//  SchedulingServerDemo
//
//  Created by 赵言 on 2020/4/22.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "NSString+TRTool.h"

@implementation NSString (TRTool)

- (NSString *)verifyString {
    if (self &&
        [self isKindOfClass:[NSString class]]) {
        return self;
    } else {
        return @"";
    }
}

@end
