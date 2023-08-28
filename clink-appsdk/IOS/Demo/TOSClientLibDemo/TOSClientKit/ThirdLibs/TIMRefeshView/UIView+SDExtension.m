//
//  UIView+SDExtension.m
//  TIMRefreshView
//
//  Created by aier on 15-2-23.
//  Copyright (c) 2015年 GSD. All rights reserved.
//

/**
 
 *******************************************************
 *                                                      *
 * 感谢您的支持， 如果下载的代码在使用过程中出现BUG或者其他问题    *
 * 您可以发邮件到 或者 到                       *
 * https://github.com/gsdios?tab=repositories 提交问题     *
 *                                                      *
 *******************************************************
 
 */


#import "UIView+SDExtension.h"

@implementation UIView (SDExtension)

- (void)setTosSD_x:(CGFloat)tosSD_x {
    CGRect frame = self.frame;
    frame.origin.x = tosSD_x;
    self.frame = frame;
}

- (void)setTosSD_y:(CGFloat)tosSD_y {
    CGRect frame = self.frame;
    frame.origin.y = tosSD_y;
    self.frame = frame;
}

- (void)setTosSD_left:(CGFloat)tosSD_left {
    CGRect frame = self.frame;
    frame.origin.x = tosSD_left;
    self.frame = frame;
}

- (void)setTosSD_top:(CGFloat)tosSD_top {
    CGRect frame = self.frame;
    frame.origin.y = tosSD_top;
    self.frame = frame;
}

- (void)setTosSD_right:(CGFloat)tosSD_right {
    CGRect frame = self.frame;
    frame.origin.x = tosSD_right - frame.size.width;
    self.frame = frame;
}

- (void)setTosSD_bottom:(CGFloat)tosSD_bottom {
    CGRect frame = self.frame;
    frame.origin.y = tosSD_bottom - frame.size.height;
    self.frame = frame;
}

- (void)setTosSD_origin:(CGPoint)tosSD_origin {
    CGRect frame = self.frame;
    frame.origin = tosSD_origin;
    self.frame = frame;
}

- (void)setTosSD_size:(CGSize)tosSD_size {
    CGRect frame = self.frame;
    frame.size = tosSD_size;
    self.frame = frame;
}

- (void)setTosSD_centerX:(CGFloat)tosSD_centerX {
    CGPoint center = self.center;
    center.x = tosSD_centerX;
    self.center = center;
}

- (void)setTosSD_centerY:(CGFloat)tosSD_centerY {
    CGPoint center = self.center;
    center.y = tosSD_centerY;
    self.center = center;
}


- (CGFloat)tosSD_x {
    return self.frame.origin.x;
}

- (CGFloat)tosSD_y {
    return self.frame.origin.y;
}

- (CGFloat)tosSD_left {
    return self.frame.origin.x;
}

- (CGFloat)tosSD_right {
    return self.frame.origin.x + self.frame.size.width;
}

- (CGFloat)tosSD_top {
    return self.frame.origin.y;
}

- (CGFloat)tosSD_bottom {
    return self.frame.origin.y + self.frame.size.height;
}

- (CGPoint)tosSD_origin {
    return self.frame.origin;
}

- (CGSize)tosSD_size {
    return self.frame.size;
}

- (CGFloat)tosSD_centerX {
    return self.center.x;
}

- (CGFloat)tosSD_centerY {
    return self.center.y;
}


//- (void)setX:(CGFloat)x
//{
//    CGRect frame = self.frame;
//    frame.origin.x = x;
//    self.frame = frame;
//}
//
//- (void)setY:(CGFloat)y
//{
//    CGRect frame = self.frame;
//    frame.origin.y = y;
//    self.frame = frame;
//}
//
//- (CGFloat)x
//{
//    return self.frame.origin.x;
//}
//
//- (CGFloat)y
//{
//    return self.frame.origin.y;
//}


//- (CGFloat)left
//{
//    return self.frame.origin.x;
//}
//
//- (void)setLeft:(CGFloat)left
//{
//    CGRect frame = self.frame;
//    frame.origin.x = left;
//    self.frame = frame;
//}
//
//- (CGFloat)top
//{
//    return self.frame.origin.y;
//}
//
//- (void)setTop:(CGFloat)top
//{
//    CGRect frame = self.frame;
//    frame.origin.y = top;
//    self.frame = frame;
//}
//
//- (CGFloat)right {
//    return self.frame.origin.x + self.frame.size.width;
//}
//
//- (void)setRight:(CGFloat)right {
//    CGRect frame = self.frame;
//    frame.origin.x = right - frame.size.width;
//    self.frame = frame;
//}
//
//- (CGFloat)bottom {
//    return self.frame.origin.y + self.frame.size.height;
//}
//
//- (void)setBottom:(CGFloat)bottom {
//    CGRect frame = self.frame;
//    frame.origin.y = bottom - frame.size.height;
//    self.frame = frame;
//}

- (CGFloat)tosSD_width {
    return self.frame.size.width;
}

- (void)setTosSD_width:(CGFloat)tosSD_width {
    CGRect frame = self.frame;
    frame.size.width = tosSD_width;
    self.frame = frame;
}

//- (CGFloat)width {
//    return self.frame.size.width;
//}
//
//- (void)setWidth:(CGFloat)width {
//    CGRect frame = self.frame;
//    frame.size.width = width;
//    self.frame = frame;
//}

- (CGFloat)tosSD_height {
    return self.frame.size.height;
}

- (void)setTosSD_height:(CGFloat)tosSD_height {
    CGRect frame = self.frame;
    frame.size.height = tosSD_height;
    self.frame = frame;
}

//- (CGFloat)height {
//    return self.frame.size.height;
//}
//
//- (void)setHeight:(CGFloat)height {
//    CGRect frame = self.frame;
//    frame.size.height = height;
//    self.frame = frame;
//}

//- (CGPoint)origin {
//    return self.frame.origin;
//}
//
//- (void)setOrigin:(CGPoint)origin {
//    CGRect frame = self.frame;
//    frame.origin = origin;
//    self.frame = frame;
//}
//
//- (CGSize)size {
//    return self.frame.size;
//}
//
//- (void)setSize:(CGSize)size {
//    CGRect frame = self.frame;
//    frame.size = size;
//    self.frame = frame;
//}
//
//- (void)setCenterX:(CGFloat)centerX
//{
//    CGPoint center = self.center;
//    center.x = centerX;
//    self.center = center;
//}
//
//- (CGFloat)centerX
//{
//    return self.center.x;
//}
//
//- (void)setCenterY:(CGFloat)centerY
//{
//    CGPoint center = self.center;
//    center.y = centerY;
//    self.center = center;
//}
//
//- (CGFloat)centerY
//{
//    return self.center.y;
//}


@end
