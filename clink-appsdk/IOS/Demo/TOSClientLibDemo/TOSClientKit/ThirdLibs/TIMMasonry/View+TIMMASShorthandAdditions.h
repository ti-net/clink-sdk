//
//  UIView+TIMMASShorthandAdditions.h
//  Masonry
//
//  Created by Jonas Budelmann on 22/07/13.
//  Copyright (c) 2013 Jonas Budelmann. All rights reserved.
//

#import "View+TIMMASAdditions.h"

#ifdef MAS_SHORTHAND

/**
 *	Shorthand view additions without the 'mas_TIM' prefixes,
 *  only enabled if MAS_SHORTHAND is defined
 */
@interface MAS_VIEW (TIMMASShorthandAdditions)

@property (nonatomic, strong, readonly) TIMMASViewAttribute *left;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *top;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *right;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *bottom;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *leading;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *trailing;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *width;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *height;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *centerX;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *centerY;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *baseline;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *(^attribute)(NSLayoutAttribute attr);

#if (__IPHONE_OS_VERSION_MIN_REQUIRED >= 80000) || (__TV_OS_VERSION_MIN_REQUIRED >= 9000) || (__MAC_OS_X_VERSION_MIN_REQUIRED >= 101100)

@property (nonatomic, strong, readonly) TIMMASViewAttribute *firstBaseline;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *lastBaseline;

#endif

#if TARGET_OS_IPHONE || TARGET_OS_TV

@property (nonatomic, strong, readonly) TIMMASViewAttribute *leftMargin;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *rightMargin;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *topMargin;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *bottomMargin;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *leadingMargin;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *trailingMargin;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *centerXWithinMargins;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *centerYWithinMargins;

#endif

- (NSArray *)makeConstraints:(void(^)(TIMMASConstraintMaker *make))block;
- (NSArray *)updateConstraints:(void(^)(TIMMASConstraintMaker *make))block;
- (NSArray *)remakeConstraints:(void(^)(TIMMASConstraintMaker *make))block;

@end

#define MAS_ATTR_FORWARD(attr)  \
- (TIMMASViewAttribute *)attr {    \
    return [self mas_TIM##attr];   \
}

@implementation MAS_VIEW (TIMMASShorthandAdditions)

MAS_ATTR_FORWARD(top);
MAS_ATTR_FORWARD(left);
MAS_ATTR_FORWARD(bottom);
MAS_ATTR_FORWARD(right);
MAS_ATTR_FORWARD(leading);
MAS_ATTR_FORWARD(trailing);
MAS_ATTR_FORWARD(width);
MAS_ATTR_FORWARD(height);
MAS_ATTR_FORWARD(centerX);
MAS_ATTR_FORWARD(centerY);
MAS_ATTR_FORWARD(baseline);

#if (__IPHONE_OS_VERSION_MIN_REQUIRED >= 80000) || (__TV_OS_VERSION_MIN_REQUIRED >= 9000) || (__MAC_OS_X_VERSION_MIN_REQUIRED >= 101100)

MAS_ATTR_FORWARD(firstBaseline);
MAS_ATTR_FORWARD(lastBaseline);

#endif

#if TARGET_OS_IPHONE || TARGET_OS_TV

MAS_ATTR_FORWARD(leftMargin);
MAS_ATTR_FORWARD(rightMargin);
MAS_ATTR_FORWARD(topMargin);
MAS_ATTR_FORWARD(bottomMargin);
MAS_ATTR_FORWARD(leadingMargin);
MAS_ATTR_FORWARD(trailingMargin);
MAS_ATTR_FORWARD(centerXWithinMargins);
MAS_ATTR_FORWARD(centerYWithinMargins);

#endif

- (TIMMASViewAttribute *(^)(NSLayoutAttribute))attribute {
    return [self mas_TIMattribute];
}

- (NSArray *)makeConstraints:(void(^)(TIMMASConstraintMaker *))block {
    return [self mas_TIMmakeTIMConstraints:block];
}

- (NSArray *)updateConstraints:(void(^)(TIMMASConstraintMaker *))block {
    return [self mas_TIMupdateTIMConstraints:block];
}

- (NSArray *)remakeConstraints:(void(^)(TIMMASConstraintMaker *))block {
    return [self mas_TIMremakeTIMConstraints:block];
}

@end

#endif
