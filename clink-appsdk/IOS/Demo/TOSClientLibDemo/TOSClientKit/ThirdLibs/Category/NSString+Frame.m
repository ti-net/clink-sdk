//
//  NSString+Frame.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/16.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "NSString+Frame.h"

@implementation NSString (Frame)

- (CGFloat)contentHeightWithFont:(UIFont *)font width:(CGFloat)width {
    CGRect rect = [self boundingRectWithSize:CGSizeMake(width, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName:font} context:nil];
    return rect.size.height;
}

- (CGFloat)contentWidthWithFont:(UIFont *)font height:(CGFloat)height {
    CGRect rect = [self boundingRectWithSize:CGSizeMake(MAXFLOAT, height) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName:font} context:nil];
    return rect.size.width;
}

@end
