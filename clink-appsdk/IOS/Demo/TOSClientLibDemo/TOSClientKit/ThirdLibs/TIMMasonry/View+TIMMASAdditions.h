//
//  UIView+TIMMASAdditions.h
//  Masonry
//
//  Created by Jonas Budelmann on 20/07/13.
//  Copyright (c) 2013 cloudling. All rights reserved.
//

#import "TIMMASUtilities.h"
#import "TIMMASConstraintMaker.h"
#import "TIMMASViewAttribute.h"

/**
 *	Provides constraint maker block
 *  and convience methods for creating TIMMASViewAttribute which are view + NSLayoutAttribute pairs
 */
@interface MAS_VIEW (TIMMASAdditions)

/**
 *	following properties return a new TIMMASViewAttribute with current view and appropriate NSLayoutAttribute
 */
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMleft;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMtop;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMright;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMbottom;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMleading;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMtrailing;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMwidth;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMheight;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMcenterX;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMcenterY;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMbaseline;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *(^mas_TIMattribute)(NSLayoutAttribute attr);

#if (__IPHONE_OS_VERSION_MIN_REQUIRED >= 80000) || (__TV_OS_VERSION_MIN_REQUIRED >= 9000) || (__MAC_OS_X_VERSION_MIN_REQUIRED >= 101100)

@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMfirstBaseline;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMlastBaseline;

#endif

#if TARGET_OS_IPHONE || TARGET_OS_TV

@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMleftMargin;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMrightMargin;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMtopMargin;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMbottomMargin;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMleadingMargin;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMtrailingMargin;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMcenterXWithinMargins;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMcenterYWithinMargins;

#endif

/**
 *	a key to associate with this view
 */
@property (nonatomic, strong) id mas_TIMkey;

/**
 *	Finds the closest common superview between this view and another view
 *
 *	@param	view	other view
 *
 *	@return	returns nil if common superview could not be found
 */
- (instancetype)mas_TIMclosestCommonSuperview:(MAS_VIEW *)view;

/**
 *  Creates a TIMMASConstraintMaker with the callee view.
 *  Any constraints defined are added to the view or the appropriate superview once the block has finished executing
 *
 *  @param block scope within which you can build up the constraints which you wish to apply to the view.
 *
 *  @return Array of created TIMMASConstraints
 */
- (NSArray *)mas_TIMmakeTIMConstraints:(void(^)(TIMMASConstraintMaker *make))block;

/**
 *  Creates a TIMMASConstraintMaker with the callee view.
 *  Any constraints defined are added to the view or the appropriate superview once the block has finished executing.
 *  If an existing constraint exists then it will be updated instead.
 *
 *  @param block scope within which you can build up the constraints which you wish to apply to the view.
 *
 *  @return Array of created/updated TIMMASConstraints
 */
- (NSArray *)mas_TIMupdateTIMConstraints:(void(^)(TIMMASConstraintMaker *make))block;

/**
 *  Creates a TIMMASConstraintMaker with the callee view.
 *  Any constraints defined are added to the view or the appropriate superview once the block has finished executing.
 *  All constraints previously installed for the view will be removed.
 *
 *  @param block scope within which you can build up the constraints which you wish to apply to the view.
 *
 *  @return Array of created/updated TIMMASConstraints
 */
- (NSArray *)mas_TIMremakeTIMConstraints:(void(^)(TIMMASConstraintMaker *make))block;

@end
