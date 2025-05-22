//
//  UIView+RoundedCorner.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/25.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "UIView+RoundedCorner.h"

@implementation UIView (RoundedCorner)

/**
 *  设置部分圆角(相对布局)
 *
 *  @param corners 需要设置为圆角的角 UIRectCornerTopLeft | UIRectCornerTopRight | UIRectCornerBottomLeft | UIRectCornerBottomRight | UIRectCornerAllCorners
 *  @param radii   需要设置的圆角大小 例如 CGSizeMake(20.0f, 20.0f)
 *  @param rect    需要设置的圆角view的rect
 */
- (void)addRoundedCorners:(UIRectCorner)corners
                withRadii:(CGSize)radii
                 viewRect:(CGRect)rect {
    UIBezierPath* rounded = [UIBezierPath bezierPathWithRoundedRect:rect byRoundingCorners:corners cornerRadii:radii];
    CAShapeLayer* shape = [[CAShapeLayer alloc] init];
    [shape setPath:rounded.CGPath];
    self.layer.mask = shape;
}

/**
 绘制裁剪圆角后图片
 
 @param radius 圆角
 @param rectSize 视图尺寸
 @param fillColor 填充色
 @return 图片
 */
- (UIImage *)drawAntiRoundedCornerImageWithRadius:(float)radius rectSize:(CGSize)rectSize fillColor:(UIColor *)fillColor {
    UIGraphicsBeginImageContextWithOptions(rectSize, false, [UIScreen mainScreen].scale);
    CGContextRef contextRef = UIGraphicsGetCurrentContext();
    //2.描述路径
    UIBezierPath *bezierPath = [UIBezierPath bezierPath];
    
    CGPoint hLeftUpPoint = CGPointMake(radius, 0);
    CGPoint hRightUpPoint = CGPointMake(rectSize.width - radius, 0);
    CGPoint hLeftDownPoint = CGPointMake(radius, rectSize.height);
    
    CGPoint vLeftUpPoint = CGPointMake(0, radius);
    CGPoint vRightDownPoint = CGPointMake(rectSize.width, rectSize.height - radius);
    
    CGPoint centerLeftUp = CGPointMake(radius, radius);
    CGPoint centerRightUp = CGPointMake(rectSize.width - radius, radius);
    CGPoint centerLeftDown = CGPointMake(radius, rectSize.height - radius);
    CGPoint centerRightDown = CGPointMake(rectSize.width - radius, rectSize.height - radius);
    
    [bezierPath moveToPoint:hLeftUpPoint];
    [bezierPath addLineToPoint:hRightUpPoint];
    [bezierPath addArcWithCenter:centerRightUp radius:radius startAngle:(CGFloat)(M_PI * 3 / 2) endAngle:(CGFloat)(M_PI * 2) clockwise: true];
    [bezierPath addLineToPoint:vRightDownPoint];
    [bezierPath addArcWithCenter:centerRightDown radius: radius startAngle: 0 endAngle: (CGFloat)(M_PI / 2) clockwise: true];
    [bezierPath addLineToPoint:hLeftDownPoint];
    [bezierPath addArcWithCenter:centerLeftDown radius: radius startAngle: (CGFloat)(M_PI / 2) endAngle: (CGFloat)(M_PI) clockwise: true];
    [bezierPath addLineToPoint:vLeftUpPoint];
    [bezierPath addArcWithCenter:centerLeftUp radius: radius startAngle: (CGFloat)(M_PI) endAngle: (CGFloat)(M_PI * 3 / 2) clockwise: true];
    [bezierPath addLineToPoint:hLeftUpPoint];
    [bezierPath closePath];
    
    //If draw drection of outer path is same with inner path, final result is just outer path.
    [bezierPath moveToPoint:CGPointZero];
    [bezierPath addLineToPoint:CGPointMake(0, rectSize.height)];
    [bezierPath addLineToPoint:CGPointMake(rectSize.width, rectSize.height)];
    [bezierPath addLineToPoint:CGPointMake(rectSize.width, 0)];
    [bezierPath addLineToPoint:CGPointZero];
    [bezierPath closePath];
    
    [fillColor setFill];
    [bezierPath fill];
    
    CGContextDrawPath(contextRef, kCGPathFillStroke);
    
    UIImage *antiRoundedCornerImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    
    return antiRoundedCornerImage;
}

@end
