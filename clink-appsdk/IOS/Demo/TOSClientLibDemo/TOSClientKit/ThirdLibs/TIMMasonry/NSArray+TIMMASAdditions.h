//
//  NSArray+TIMMASAdditions.h
//
//
//  Created by Daniel Hammond on 11/26/13.
//
//

#import "TIMMASUtilities.h"
#import "TIMMASConstraintMaker.h"
#import "TIMMASViewAttribute.h"

typedef NS_ENUM(NSUInteger, TIMMASAxisType) {
    TIMMASAxisTypeHorizontal,
    TIMMASAxisTypeVertical
};

@interface NSArray (TIMMASAdditions)

/**
 *  Creates a TIMMASConstraintMaker with each view in the callee.
 *  Any constraints defined are added to the view or the appropriate superview once the block has finished executing on each view
 *
 *  @param block scope within which you can build up the constraints which you wish to apply to each view.
 *
 *  @return Array of created TIMMASConstraints
 */
- (NSArray *)mas_TIMmakeTIMConstraints:(void (^)(TIMMASConstraintMaker *make))block;

/**
 *  Creates a TIMMASConstraintMaker with each view in the callee.
 *  Any constraints defined are added to each view or the appropriate superview once the block has finished executing on each view.
 *  If an existing constraint exists then it will be updated instead.
 *
 *  @param block scope within which you can build up the constraints which you wish to apply to each view.
 *
 *  @return Array of created/updated TIMMASConstraints
 */
- (NSArray *)mas_TIMupdateTIMConstraints:(void (^)(TIMMASConstraintMaker *make))block;

/**
 *  Creates a TIMMASConstraintMaker with each view in the callee.
 *  Any constraints defined are added to each view or the appropriate superview once the block has finished executing on each view.
 *  All constraints previously installed for the views will be removed.
 *
 *  @param block scope within which you can build up the constraints which you wish to apply to each view.
 *
 *  @return Array of created/updated TIMMASConstraints
 */
- (NSArray *)mas_TIMremakeTIMConstraints:(void (^)(TIMMASConstraintMaker *make))block;

/**
 *  distribute with fixed spacing
 *
 *  @param axisType     which axis to distribute items along
 *  @param fixedSpacing the spacing between each item
 *  @param leadSpacing  the spacing before the first item and the container
 *  @param tailSpacing  the spacing after the last item and the container
 */
- (void)mas_TIMdistributeViewsAlongAxis:(TIMMASAxisType)axisType withFixedSpacing:(CGFloat)fixedSpacing leadSpacing:(CGFloat)leadSpacing tailSpacing:(CGFloat)tailSpacing;

/**
 *  distribute with fixed item size
 *
 *  @param axisType        which axis to distribute items along
 *  @param fixedItemLength the fixed length of each item
 *  @param leadSpacing     the spacing before the first item and the container
 *  @param tailSpacing     the spacing after the last item and the container
 */
- (void)mas_TIMdistributeViewsAlongAxis:(TIMMASAxisType)axisType withFixedItemLength:(CGFloat)fixedItemLength leadSpacing:(CGFloat)leadSpacing tailSpacing:(CGFloat)tailSpacing;

@end
