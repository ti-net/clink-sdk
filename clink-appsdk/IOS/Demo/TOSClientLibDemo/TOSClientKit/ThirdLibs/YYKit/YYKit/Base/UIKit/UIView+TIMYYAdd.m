//
//  UIView+TIMYYAdd.m
//  YYKit <https://github.com/ibireme/YYKit>
//
//  Created by 侯力 on 2024/4/17.
//  Copyright © 2019年 侯力. All rights reserved.
//
//  This source code is licensed under the MIT-style license found in the
//  LICENSE file in the root directory of this source tree.
//

#import "UIView+TIMYYAdd.h"
#import <QuartzCore/QuartzCore.h>
#import "YYKitMacro.h"

YYSYNTH_DUMMY_CLASS(UIView_TIMYYAdd)


@implementation UIView (TIMYYAdd)

- (UIImage *)snapshotImage {
    // iOS 17.0以上针对某些接口UIGraphicsBeginImageContextWithOptions的废弃兼容
    CGFloat scale = 0.0f;  // 默认值 1.0f
    UIImage *newImage = nil;
    if (@available(iOS 17.0, *)) {
        // 实际应该是在10.0作为分界点 但是为了谨慎起见只针对17做处理
        UIGraphicsImageRendererFormat *format = [[UIGraphicsImageRendererFormat alloc] init];
        format.scale = scale;
        format.opaque = self.opaque;
        UIGraphicsImageRenderer *renderer = [[UIGraphicsImageRenderer alloc] initWithSize:self.bounds.size
                                                                                   format:format];
        newImage = [renderer imageWithActions:^(UIGraphicsImageRendererContext * _Nonnull rendererContext) {
            [self.layer renderInContext:UIGraphicsGetCurrentContext()];
        }];
    } else {
        UIGraphicsBeginImageContextWithOptions(self.bounds.size, self.opaque, 0);
        [self.layer renderInContext:UIGraphicsGetCurrentContext()];
        newImage = UIGraphicsGetImageFromCurrentImageContext();
        UIGraphicsEndImageContext();
    }
    return newImage;
}

- (UIImage *)snapshotImageAfterScreenUpdates:(BOOL)afterUpdates {
    if (![self respondsToSelector:@selector(drawViewHierarchyInRect:afterScreenUpdates:)]) {
        return [self snapshotImage];
    }
    // iOS 17.0以上针对某些接口UIGraphicsBeginImageContextWithOptions的废弃兼容
    CGFloat scale = 0.0f;  // 默认值 1.0f
    UIImage *newImage = nil;
    if (@available(iOS 17.0, *)) {
        // 实际应该是在10.0作为分界点 但是为了谨慎起见只针对17做处理
        UIGraphicsImageRendererFormat *format = [[UIGraphicsImageRendererFormat alloc] init];
        format.scale = scale;
        format.opaque = self.opaque;
        UIGraphicsImageRenderer *renderer = [[UIGraphicsImageRenderer alloc] initWithSize:self.bounds.size
                                                                                   format:format];
        newImage = [renderer imageWithActions:^(UIGraphicsImageRendererContext * _Nonnull rendererContext) {
            [self drawViewHierarchyInRect:self.bounds afterScreenUpdates:afterUpdates];
        }];
    } else {
        UIGraphicsBeginImageContextWithOptions(self.bounds.size, self.opaque, 0);
        [self drawViewHierarchyInRect:self.bounds afterScreenUpdates:afterUpdates];
        newImage = UIGraphicsGetImageFromCurrentImageContext();
        UIGraphicsEndImageContext();
    }
    return newImage;
}

- (NSData *)snapshotPDF {
    CGRect bounds = self.bounds;
    NSMutableData *data = [NSMutableData data];
    CGDataConsumerRef consumer = CGDataConsumerCreateWithCFData((__bridge CFMutableDataRef)data);
    CGContextRef context = CGPDFContextCreate(consumer, &bounds, NULL);
    CGDataConsumerRelease(consumer);
    if (!context) return nil;
    CGPDFContextBeginPage(context, NULL);
    CGContextTranslateCTM(context, 0, bounds.size.height);
    CGContextScaleCTM(context, 1.0, -1.0);
    [self.layer renderInContext:context];
    CGPDFContextEndPage(context);
    CGPDFContextClose(context);
    CGContextRelease(context);
    return data;
}

- (void)setLayerShadow:(UIColor*)color offset:(CGSize)offset radius:(CGFloat)radius {
    self.layer.shadowColor = color.CGColor;
    self.layer.shadowOffset = offset;
    self.layer.shadowRadius = radius;
    self.layer.shadowOpacity = 1;
    self.layer.shouldRasterize = YES;
    self.layer.rasterizationScale = [UIScreen mainScreen].scale;
}

- (void)removeAllSubviews {
    //[self.subviews makeObjectsPerformSelector:@selector(removeFromSuperview)];
    while (self.subviews.count) {
        [self.subviews.lastObject removeFromSuperview];
    }
}


- (UIViewController *)viewController {
    for (UIView *view = self; view; view = view.superview) {
        UIResponder *nextResponder = [view nextResponder];
        if ([nextResponder isKindOfClass:[UIViewController class]]) {
            return (UIViewController *)nextResponder;
        }
    }
    return nil;
}

- (CGFloat)visibleAlpha {
    if ([self isKindOfClass:[UIWindow class]]) {
        if (self.hidden) return 0;
        return self.alpha;
    }
    if (!self.window) return 0;
    CGFloat alpha = 1;
    UIView *v = self;
    while (v) {
        if (v.hidden) {
            alpha = 0;
            break;
        }
        alpha *= v.alpha;
        v = v.superview;
    }
    return alpha;
}

- (CGPoint)convertPoint:(CGPoint)point toViewOrWindow:(UIView *)view {
    if (!view) {
        if ([self isKindOfClass:[UIWindow class]]) {
            return [((UIWindow *)self) convertPoint:point toWindow:nil];
        } else {
            return [self convertPoint:point toView:nil];
        }
    }
    
    UIWindow *from = [self isKindOfClass:[UIWindow class]] ? (id)self : self.window;
    UIWindow *to = [view isKindOfClass:[UIWindow class]] ? (id)view : view.window;
    if ((!from || !to) || (from == to)) return [self convertPoint:point toView:view];
    point = [self convertPoint:point toView:from];
    point = [to convertPoint:point fromWindow:from];
    point = [view convertPoint:point fromView:to];
    return point;
}

- (CGPoint)convertPoint:(CGPoint)point fromViewOrWindow:(UIView *)view {
    if (!view) {
        if ([self isKindOfClass:[UIWindow class]]) {
            return [((UIWindow *)self) convertPoint:point fromWindow:nil];
        } else {
            return [self convertPoint:point fromView:nil];
        }
    }
    
    UIWindow *from = [view isKindOfClass:[UIWindow class]] ? (id)view : view.window;
    UIWindow *to = [self isKindOfClass:[UIWindow class]] ? (id)self : self.window;
    if ((!from || !to) || (from == to)) return [self convertPoint:point fromView:view];
    point = [from convertPoint:point fromView:view];
    point = [to convertPoint:point fromWindow:from];
    point = [self convertPoint:point fromView:to];
    return point;
}

- (CGRect)convertRect:(CGRect)rect toViewOrWindow:(UIView *)view {
    if (!view) {
        if ([self isKindOfClass:[UIWindow class]]) {
            return [((UIWindow *)self) convertRect:rect toWindow:nil];
        } else {
            return [self convertRect:rect toView:nil];
        }
    }
    
    UIWindow *from = [self isKindOfClass:[UIWindow class]] ? (id)self : self.window;
    UIWindow *to = [view isKindOfClass:[UIWindow class]] ? (id)view : view.window;
    if (!from || !to) return [self convertRect:rect toView:view];
    if (from == to) return [self convertRect:rect toView:view];
    rect = [self convertRect:rect toView:from];
    rect = [to convertRect:rect fromWindow:from];
    rect = [view convertRect:rect fromView:to];
    return rect;
}

- (CGRect)convertRect:(CGRect)rect fromViewOrWindow:(UIView *)view {
    if (!view) {
        if ([self isKindOfClass:[UIWindow class]]) {
            return [((UIWindow *)self) convertRect:rect fromWindow:nil];
        } else {
            return [self convertRect:rect fromView:nil];
        }
    }
    
    UIWindow *from = [view isKindOfClass:[UIWindow class]] ? (id)view : view.window;
    UIWindow *to = [self isKindOfClass:[UIWindow class]] ? (id)self : self.window;
    if ((!from || !to) || (from == to)) return [self convertRect:rect fromView:view];
    rect = [from convertRect:rect fromView:view];
    rect = [to convertRect:rect fromWindow:from];
    rect = [self convertRect:rect fromView:to];
    return rect;
}

- (CGFloat)tos_left {
    return self.frame.origin.x;
}

- (void)setTos_left:(CGFloat)tos_left {
    CGRect frame = self.frame;
    frame.origin.x = tos_left;
    self.frame = frame;
}

- (CGFloat)tos_top {
    return self.frame.origin.y;
}

- (void)setTos_top:(CGFloat)tos_top {
    CGRect frame = self.frame;
    frame.origin.y = tos_top;
    self.frame = frame;
}

//- (CGFloat)top {
//    return self.frame.origin.y;
//}
//
//- (void)setTop:(CGFloat)y {
//    CGRect frame = self.frame;
//    frame.origin.y = y;
//    self.frame = frame;
//}

- (CGFloat)tos_right {
    return self.frame.origin.x + self.frame.size.width;
}

- (void)setTos_Right:(CGFloat)tos_right {
    CGRect frame = self.frame;
    frame.origin.x = tos_right - frame.size.width;
    self.frame = frame;
}

- (CGFloat)tos_bottom {
    return self.frame.origin.y + self.frame.size.height;
}

- (void)setTos_bottom:(CGFloat)tos_bottom {
    CGRect frame = self.frame;
    frame.origin.y = tos_bottom - frame.size.height;
    self.frame = frame;
}

//- (CGFloat)bottom {
//    return self.frame.origin.y + self.frame.size.height;
//}
//
//- (void)setBottom:(CGFloat)bottom {
//    CGRect frame = self.frame;
//    frame.origin.y = bottom - frame.size.height;
//    self.frame = frame;
//}


- (CGFloat)tos_width {
    return self.frame.size.width;
}

- (void)setTos_width:(CGFloat)tos_width {
    CGRect frame = self.frame;
    frame.size.width = tos_width;
    self.frame = frame;
}

//- (CGFloat)width {
//    return self.frame.size.width;
//}

//- (void)setWidth:(CGFloat)width {
//    CGRect frame = self.frame;
//    frame.size.width = width;
//    self.frame = frame;
//}


- (CGFloat)tos_height {
    return self.frame.size.height;
}

- (void)setTos_height:(CGFloat)tos_height {
    CGRect frame = self.frame;
    frame.size.height = tos_height;
    self.frame = frame;
}

//- (CGFloat)height {
//    return self.frame.size.height;
//}

//- (void)setHeight:(CGFloat)height {
//    CGRect frame = self.frame;
//    frame.size.height = height;
//    self.frame = frame;
//}

- (CGFloat)tos_centerX {
    return self.center.x;
}

- (void)setTos_centerX:(CGFloat)tos_centerX {
    self.center = CGPointMake(tos_centerX, self.center.y);
}

- (CGFloat)tos_centerY {
    return self.center.y;
}

- (void)setTos_centerY:(CGFloat)tos_centerY {
    self.center = CGPointMake(self.center.x, tos_centerY);
}

- (CGPoint)origin {
    return self.frame.origin;
}

- (void)setOrigin:(CGPoint)origin {
    CGRect frame = self.frame;
    frame.origin = origin;
    self.frame = frame;
}

- (CGSize)size {
    return self.frame.size;
}

- (void)setSize:(CGSize)size {
    CGRect frame = self.frame;
    frame.size = size;
    self.frame = frame;
}

@end
