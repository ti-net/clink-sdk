//
//  UIView+TIMMASAdditions.m
//  Masonry
//
//  Created by Jonas Budelmann on 20/07/13.
//  Copyright (c) 2013 cloudling. All rights reserved.
//

#import "View+TIMMASAdditions.h"
#import <objc/runtime.h>

@implementation MAS_VIEW (TIMMASAdditions)

- (NSArray *)mas_TIMmakeTIMConstraints:(void(^)(TIMMASConstraintMaker *))block {
    self.translatesAutoresizingMaskIntoConstraints = NO;
    TIMMASConstraintMaker *constraintMaker = [[TIMMASConstraintMaker alloc] initWithView:self];
    block(constraintMaker);
    return [constraintMaker install];
}

- (NSArray *)mas_TIMupdateTIMConstraints:(void(^)(TIMMASConstraintMaker *))block {
    self.translatesAutoresizingMaskIntoConstraints = NO;
    TIMMASConstraintMaker *constraintMaker = [[TIMMASConstraintMaker alloc] initWithView:self];
    constraintMaker.updateExisting = YES;
    block(constraintMaker);
    return [constraintMaker install];
}

- (NSArray *)mas_TIMremakeTIMConstraints:(void(^)(TIMMASConstraintMaker *make))block {
    self.translatesAutoresizingMaskIntoConstraints = NO;
    TIMMASConstraintMaker *constraintMaker = [[TIMMASConstraintMaker alloc] initWithView:self];
    constraintMaker.removeExisting = YES;
    block(constraintMaker);
    return [constraintMaker install];
}

#pragma mark - NSLayoutAttribute properties

- (TIMMASViewAttribute *)mas_TIMleft {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeLeft];
}

- (TIMMASViewAttribute *)mas_TIMtop {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeTop];
}

- (TIMMASViewAttribute *)mas_TIMright {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeRight];
}

- (TIMMASViewAttribute *)mas_TIMbottom {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeBottom];
}

- (TIMMASViewAttribute *)mas_TIMleading {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeLeading];
}

- (TIMMASViewAttribute *)mas_TIMtrailing {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeTrailing];
}

- (TIMMASViewAttribute *)mas_TIMwidth {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeWidth];
}

- (TIMMASViewAttribute *)mas_TIMheight {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeHeight];
}

- (TIMMASViewAttribute *)mas_TIMcenterX {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeCenterX];
}

- (TIMMASViewAttribute *)mas_TIMcenterY {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeCenterY];
}

- (TIMMASViewAttribute *)mas_TIMbaseline {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeBaseline];
}

- (TIMMASViewAttribute *(^)(NSLayoutAttribute))mas_TIMattribute
{
    return ^(NSLayoutAttribute attr) {
        return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:attr];
    };
}

#if (__IPHONE_OS_VERSION_MIN_REQUIRED >= 80000) || (__TV_OS_VERSION_MIN_REQUIRED >= 9000) || (__MAC_OS_X_VERSION_MIN_REQUIRED >= 101100)

- (TIMMASViewAttribute *)mas_TIMfirstBaseline {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeFirstBaseline];
}
- (TIMMASViewAttribute *)mas_TIMlastBaseline {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeLastBaseline];
}

#endif

#if TARGET_OS_IPHONE || TARGET_OS_TV

- (TIMMASViewAttribute *)mas_TIMleftMargin {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeLeftMargin];
}

- (TIMMASViewAttribute *)mas_TIMrightMargin {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeRightMargin];
}

- (TIMMASViewAttribute *)mas_TIMtopMargin {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeTopMargin];
}

- (TIMMASViewAttribute *)mas_TIMbottomMargin {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeBottomMargin];
}

- (TIMMASViewAttribute *)mas_TIMleadingMargin {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeLeadingMargin];
}

- (TIMMASViewAttribute *)mas_TIMtrailingMargin {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeTrailingMargin];
}

- (TIMMASViewAttribute *)mas_TIMcenterXWithinMargins {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeCenterXWithinMargins];
}

- (TIMMASViewAttribute *)mas_TIMcenterYWithinMargins {
    return [[TIMMASViewAttribute alloc] initWithView:self layoutAttribute:NSLayoutAttributeCenterYWithinMargins];
}

#endif

#pragma mark - associated properties

- (id)mas_TIMkey {
    return objc_getAssociatedObject(self, @selector(mas_TIMkey));
}

- (void)setMas_key:(id)key {
    objc_setAssociatedObject(self, @selector(mas_TIMkey), key, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

#pragma mark - heirachy

- (instancetype)mas_TIMclosestCommonSuperview:(MAS_VIEW *)view {
    MAS_VIEW *closestCommonSuperview = nil;

    MAS_VIEW *secondViewSuperview = view;
    while (!closestCommonSuperview && secondViewSuperview) {
        MAS_VIEW *firstViewSuperview = self;
        while (!closestCommonSuperview && firstViewSuperview) {
            if (secondViewSuperview == firstViewSuperview) {
                closestCommonSuperview = secondViewSuperview;
            }
            firstViewSuperview = firstViewSuperview.superview;
        }
        secondViewSuperview = secondViewSuperview.superview;
    }
    return closestCommonSuperview;
}

@end
