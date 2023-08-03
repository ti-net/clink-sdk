//
//  TIMMASConstraint+Private.h
//  Masonry
//
//  Created by Nick Tymchenko on 29/04/14.
//  Copyright (c) 2014 cloudling. All rights reserved.
//

#import "TIMMASConstraint.h"

@protocol TIMMASConstraintDelegate;


@interface TIMMASConstraint ()

/**
 *  Whether or not to check for an existing constraint instead of adding constraint
 */
@property (nonatomic, assign) BOOL updateExisting;

/**
 *	Usually TIMMASConstraintMaker but could be a parent TIMMASConstraint
 */
@property (nonatomic, weak) id<TIMMASConstraintDelegate> delegate;

/**
 *  Based on a provided value type, is equal to calling:
 *  NSNumber - setOffset:
 *  NSValue with CGPoint - setPointOffset:
 *  NSValue with CGSize - setSizeOffset:
 *  NSValue with MASEdgeInsets - setInsets:
 */
- (void)setLayoutConstantWithValue:(NSValue *)value;

@end


@interface TIMMASConstraint (Abstract)

/**
 *	Sets the constraint relation to given NSLayoutRelation
 *  returns a block which accepts one of the following:
 *    TIMMASViewAttribute, UIView, NSValue, NSArray
 *  see readme for more details.
 */
- (TIMMASConstraint * (^)(id, NSLayoutRelation))equalToWithRelation;

/**
 *	Override to set a custom chaining behaviour
 */
- (TIMMASConstraint *)addConstraintWithLayoutAttribute:(NSLayoutAttribute)layoutAttribute;

@end


@protocol TIMMASConstraintDelegate <NSObject>

/**
 *	Notifies the delegate when the constraint needs to be replaced with another constraint. For example
 *  A TIMMASViewConstraint may turn into a TIMMASCompositeConstraint when an array is passed to one of the equality blocks
 */
- (void)constraint:(TIMMASConstraint *)constraint shouldBeReplacedWithConstraint:(TIMMASConstraint *)replacementConstraint;

- (TIMMASConstraint *)constraint:(TIMMASConstraint *)constraint addConstraintWithLayoutAttribute:(NSLayoutAttribute)layoutAttribute;

@end
