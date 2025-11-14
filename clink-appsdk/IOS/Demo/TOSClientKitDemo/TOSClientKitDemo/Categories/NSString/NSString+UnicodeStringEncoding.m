//
//  NSString+UnicodeStringEncoding.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/10.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "NSString+UnicodeStringEncoding.h"

@implementation NSString (UnicodeStringEncoding)

- (NSString *)tr_unicode {
    return [self stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
}

@end
