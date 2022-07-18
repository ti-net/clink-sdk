//
//  NSString+RegexValidation.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/10.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "NSString+RegexValidation.h"

@implementation NSString (RegexValidation)

- (BOOL)regexValidate:(NSString *)regex {
    
    if (self.length == 0) {
        NSLog(@"不能为空！");
        return NO;
    }
//    NSRange range = [self rangeOfString:@" "];
//    if (range.location != NSNotFound) {
//        NSLog(@"不能有空格！");
//        return NO;
//    }
    
    NSPredicate *accountStringPredicate = [NSPredicate predicateWithFormat:@"SELF MATCHES %@",regex];
    if(![accountStringPredicate evaluateWithObject:self]) {
        NSLog(@"%@格式不正确！",self);
        return NO;
    };
    return YES;
}

@end
