//
//  TIMMASConstraintBuilder.h
//  Masonry
//
//  Created by Jonas Budelmann on 20/07/13.
//  Copyright (c) 2013 cloudling. All rights reserved.
//

#import "TIMMASConstraint.h"
#import "TIMMASUtilities.h"

typedef NS_OPTIONS(NSInteger, TIMMASAttribute) {
    TIMMASAttributeLeft = 1 << NSLayoutAttributeLeft,
    TIMMASAttributeRight = 1 << NSLayoutAttributeRight,
    TIMMASAttributeTop = 1 << NSLayoutAttributeTop,
    TIMMASAttributeBottom = 1 << NSLayoutAttributeBottom,
    TIMMASAttributeLeading = 1 << NSLayoutAttributeLeading,
    TIMMASAttributeTrailing = 1 << NSLayoutAttributeTrailing,
    TIMMASAttributeWidth = 1 << NSLayoutAttributeWidth,
    TIMMASAttributeHeight = 1 << NSLayoutAttributeHeight,
    TIMMASAttributeCenterX = 1 << NSLayoutAttributeCenterX,
    TIMMASAttributeCenterY = 1 << NSLayoutAttributeCenterY,
    TIMMASAttributeBaseline = 1 << NSLayoutAttributeBaseline,
    
#if (__IPHONE_OS_VERSION_MIN_REQUIRED >= 80000) || (__TV_OS_VERSION_MIN_REQUIRED >= 9000) || (__MAC_OS_X_VERSION_MIN_REQUIRED >= 101100)
    
    TIMMASAttributeFirstBaseline = 1 << NSLayoutAttributeFirstBaseline,
    TIMMASAttributeLastBaseline = 1 << NSLayoutAttributeLastBaseline,
    
#endif
    
#if TARGET_OS_IPHONE || TARGET_OS_TV
    
    TIMMASAttributeLeftMargin = 1 << NSLayoutAttributeLeftMargin,
    TIMMASAttributeRightMargin = 1 << NSLayoutAttributeRightMargin,
    TIMMASAttributeTopMargin = 1 << NSLayoutAttributeTopMargin,
    TIMMASAttributeBottomMargin = 1 << NSLayoutAttributeBottomMargin,
    TIMMASAttributeLeadingMargin = 1 << NSLayoutAttributeLeadingMargin,
    TIMMASAttributeTrailingMargin = 1 << NSLayoutAttributeTrailingMargin,
    TIMMASAttributeCenterXWithinMargins = 1 << NSLayoutAttributeCenterXWithinMargins,
    TIMMASAttributeCenterYWithinMargins = 1 << NSLayoutAttributeCenterYWithinMargins,

#endif
    
};

/**
 *  Provides factory methods for creating TIMMASConstraints.
 *  Constraints are collected until they are ready to be installed
 *
 */
@interface TIMMASConstraintMaker : NSObject

/**
 *	The following properties return a new TIMMASViewConstraint
 *  with the first item set to the makers associated view and the appropriate TIMMASViewAttribute
 */
@property (nonatomic, strong, readonly) TIMMASConstraint *left;
@property (nonatomic, strong, readonly) TIMMASConstraint *top;
@property (nonatomic, strong, readonly) TIMMASConstraint *right;
@property (nonatomic, strong, readonly) TIMMASConstraint *bottom;
@property (nonatomic, strong, readonly) TIMMASConstraint *leading;
@property (nonatomic, strong, readonly) TIMMASConstraint *trailing;
@property (nonatomic, strong, readonly) TIMMASConstraint *width;
@property (nonatomic, strong, readonly) TIMMASConstraint *height;
@property (nonatomic, strong, readonly) TIMMASConstraint *centerX;
@property (nonatomic, strong, readonly) TIMMASConstraint *centerY;
@property (nonatomic, strong, readonly) TIMMASConstraint *baseline;

#if (__IPHONE_OS_VERSION_MIN_REQUIRED >= 80000) || (__TV_OS_VERSION_MIN_REQUIRED >= 9000) || (__MAC_OS_X_VERSION_MIN_REQUIRED >= 101100)

@property (nonatomic, strong, readonly) TIMMASConstraint *firstBaseline;
@property (nonatomic, strong, readonly) TIMMASConstraint *lastBaseline;

#endif

#if TARGET_OS_IPHONE || TARGET_OS_TV

@property (nonatomic, strong, readonly) TIMMASConstraint *leftMargin;
@property (nonatomic, strong, readonly) TIMMASConstraint *rightMargin;
@property (nonatomic, strong, readonly) TIMMASConstraint *topMargin;
@property (nonatomic, strong, readonly) TIMMASConstraint *bottomMargin;
@property (nonatomic, strong, readonly) TIMMASConstraint *leadingMargin;
@property (nonatomic, strong, readonly) TIMMASConstraint *trailingMargin;
@property (nonatomic, strong, readonly) TIMMASConstraint *centerXWithinMargins;
@property (nonatomic, strong, readonly) TIMMASConstraint *centerYWithinMargins;

#endif

/**
 *  Returns a block which creates a new TIMMASCompositeConstraint with the first item set
 *  to the makers associated view and children corresponding to the set bits in the
 *  TIMMASAttribute parameter. Combine multiple attributes via binary-or.
 */
@property (nonatomic, strong, readonly) TIMMASConstraint *(^attributes)(TIMMASAttribute attrs);

/**
 *	Creates a TIMMASCompositeConstraint with type TIMMASCompositeConstraintTypeEdges
 *  which generates the appropriate TIMMASViewConstraint children (top, left, bottom, right)
 *  with the first item set to the makers associated view
 */
@property (nonatomic, strong, readonly) TIMMASConstraint *edges;

/**
 *	Creates a TIMMASCompositeConstraint with type TIMMASCompositeConstraintTypeSize
 *  which generates the appropriate TIMMASViewConstraint children (width, height)
 *  with the first item set to the makers associated view
 */
@property (nonatomic, strong, readonly) TIMMASConstraint *size;

/**
 *	Creates a TIMMASCompositeConstraint with type TIMMASCompositeConstraintTypeCenter
 *  which generates the appropriate TIMMASViewConstraint children (centerX, centerY)
 *  with the first item set to the makers associated view
 */
@property (nonatomic, strong, readonly) TIMMASConstraint *center;

/**
 *  Whether or not to check for an existing constraint instead of adding constraint
 */
@property (nonatomic, assign) BOOL updateExisting;

/**
 *  Whether or not to remove existing constraints prior to installing
 */
@property (nonatomic, assign) BOOL removeExisting;

/**
 *	initialises the maker with a default view
 *
 *	@param	view	any MASConstrait are created with this view as the first item
 *
 *	@return	a new TIMMASConstraintMaker
 */
- (id)initWithView:(MAS_VIEW *)view;

/**
 *	Calls install method on any TIMMASConstraints which have been created by this maker
 *
 *	@return	an array of all the installed TIMMASConstraints
 */
- (NSArray *)install;

- (TIMMASConstraint * (^)(dispatch_block_t))group;

@end
