
//
//  NSString+UrlChineseCoding.m
//  mobileCMS
//
//  Created by 赵言 on 2020/1/19.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "NSString+UrlChineseCoding.h"

@implementation NSString (UrlChineseCoding)

- (NSString *)tr_urlChineseCoding {
    return [self stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLQueryAllowedCharacterSet]];
}

@end

