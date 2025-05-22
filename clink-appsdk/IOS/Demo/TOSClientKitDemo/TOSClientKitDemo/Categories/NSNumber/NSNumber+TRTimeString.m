//
//  NSNumber+TRTimeString.m
//  mobileCMS
//
//  Created by 赵言 on 2020/4/3.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "NSNumber+TRTimeString.h"
#import "NSString+TimeFormatting.h"

@implementation NSNumber (TRTimeString)

- (NSString *)getTimestampStringWithFormat:(NSString *)format {
    NSString *timeStr;
    if (self && self.integerValue > 0 && self.stringValue.length > 0) {
        timeStr = [self.stringValue getTimestampStringWithFormat:format];
    } else {
        timeStr = @"--";
    }
    return timeStr;
}

@end
