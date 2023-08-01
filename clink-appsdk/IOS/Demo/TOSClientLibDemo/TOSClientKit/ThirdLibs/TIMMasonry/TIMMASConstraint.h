//
//  TIMMASConstraint.h
//  Masonry
//
//  Created by Jonas Budelmann on 22/07/13.
//  Copyright (c) 2013 cloudling. All rights reserved.
//

#import "TIMMASUtilities.h"

/**
 *	Enables Constraints to be created with chainable syntax
 *  Constraint can represent single NSLayoutConstraint (TIMMASViewConstraint) 
 *  or a group of NSLayoutConstraints (MASComposisteConstraint)
 */
@interface TIMMASConstraint : NSObject

// Chaining Support

/**
 *	Modifies the NSLayoutConstraint constant,
 *  only affects TIMMASConstraints in which the first item's NSLayoutAttribute is one of the following
 *  NSLayoutAttributeTop, NSLayoutAttributeLeft, NSLayoutAttributeBottom, NSLayoutAttributeRight
 */
- (TIMMASConstraint * (^)(MASEdgeInsets insets))insets;

/**
 *	Modifies the NSLayoutConstraint constant,
 *  only affects TIMMASConstraints in which the first item's NSLayoutAttribute is one of the following
 *  NSLayoutAttributeWidth, NSLayoutAttributeHeight
 */
- (TIMMASConstraint * (^)(CGSize offset))sizeOffset;

/**
 *	Modifies the NSLayoutConstraint constant,
 *  only affects TIMMASConstraints in which the first item's NSLayoutAttribute is one of the following
 *  NSLayoutAttributeCenterX, NSLayoutAttributeCenterY
 */
- (TIMMASConstraint * (^)(CGPoint offset))centerOffset;

/**
 *	Modifies the NSLayoutConstraint constant
 */
- (TIMMASConstraint * (^)(CGFloat offset))offset;

/**
 *  Modifies the NSLayoutConstraint constant based on a value type
 */
- (TIMMASConstraint * (^)(NSValue *value))valueOffset;

/**
 *	Sets the NSLayoutConstraint multiplier property
 */
- (TIMMASConstraint * (^)(CGFloat multiplier))multipliedBy;

/**
 *	Sets the NSLayoutConstraint multiplier to 1.0/dividedBy
 */
- (TIMMASConstraint * (^)(CGFloat divider))dividedBy;

/**
 *	Sets the NSLayoutConstraint priority to a float or TIMMASLayoutPriority
 */
- (TIMMASConstraint * (^)(TIMMASLayoutPriority priority))priority;

/**
 *	Sets the NSLayoutConstraint priority to TIMMASLayoutPriorityLow
 */
- (TIMMASConstraint * (^)(void))priorityLow;

/**
 *	Sets the NSLayoutConstraint priority to TIMMASLayoutPriorityMedium
 */
- (TIMMASConstraint * (^)(void))priorityMedium;

/**
 *	Sets the NSLayoutConstraint priority to TIMMASLayoutPriorityHigh
 */
- (TIMMASConstraint * (^)(void))priorityHigh;

/**
 *	Sets the constraint relation to NSLayoutRelationEqual
 *  returns a block which accepts one of the following:
 *    TIMMASViewAttribute, UIView, NSValue, NSArray
 *  see readme for more details.
 */
- (TIMMASConstraint * (^)(id attr))equalTo;

/**
 *	Sets the constraint relation to NSLayoutRelationGreaterThanOrEqual
 *  returns a block which accepts one of the following:
 *    TIMMASViewAttribute, UIView, NSValue, NSArray
 *  see readme for more details.
 */
- (TIMMASConstraint * (^)(id attr))greaterThanOrEqualTo;

/**
 *	Sets the constraint relation to NSLayoutRelationLessThanOrEqual
 *  returns a block which accepts one of the following:
 *    TIMMASViewAttribute, UIView, NSValue, NSArray
 *  see readme for more details.
 */
- (TIMMASConstraint * (^)(id attr))lessThanOrEqualTo;

/**
 *	Optional semantic property which has no effect but improves the readability of constraint
 */
- (TIMMASConstraint *)with;

/**
 *	Optional semantic property which has no effect but improves the readability of constraint
 */
- (TIMMASConstraint *)and;

/**
 *	Creates a new TIMMASCompositeConstraint with the called attribute and reciever
 */
- (TIMMASConstraint *)left;
- (TIMMASConstraint *)top;
- (TIMMASConstraint *)right;
- (TIMMASConstraint *)bottom;
- (TIMMASConstraint *)leading;
- (TIMMASConstraint *)trailing;
- (TIMMASConstraint *)width;
- (TIMMASConstraint *)height;
- (TIMMASConstraint *)centerX;
- (TIMMASConstraint *)centerY;
- (TIMMASConstraint *)baseline;

#if (__IPHONE_OS_VERSION_MIN_REQUIRED >= 80000) || (__TV_OS_VERSION_MIN_REQUIRED >= 9000) || (__MAC_OS_X_VERSION_MIN_REQUIRED >= 101100)

- (TIMMASConstraint *)firstBaseline;
- (TIMMASConstraint *)lastBaseline;

#endif

#if TARGET_OS_IPHONE || TARGET_OS_TV

- (TIMMASConstraint *)leftMargin;
- (TIMMASConstraint *)rightMargin;
- (TIMMASConstraint *)topMargin;
- (TIMMASConstraint *)bottomMargin;
- (TIMMASConstraint *)leadingMargin;
- (TIMMASConstraint *)trailingMargin;
- (TIMMASConstraint *)centerXWithinMargins;
- (TIMMASConstraint *)centerYWithinMargins;

#endif


/**
 *	Sets the constraint debug name
 */
- (TIMMASConstraint * (^)(id key))key;

// NSLayoutConstraint constant Setters
// for use outside of mas_TIMupdateTIMConstraints/mas_TIMmakeTIMConstraints blocks

/**
 *	Modifies the NSLayoutConstraint constant,
 *  only affects TIMMASConstraints in which the first item's NSLayoutAttribute is one of the following
 *  NSLayoutAttributeTop, NSLayoutAttributeLeft, NSLayoutAttributeBottom, NSLayoutAttributeRight
 */
- (void)setInsets:(MASEdgeInsets)insets;

/**
 *	Modifies the NSLayoutConstraint constant,
 *  only affects TIMMASConstraints in which the first item's NSLayoutAttribute is one of the following
 *  NSLayoutAttributeWidth, NSLayoutAttributeHeight
 */
- (void)setSizeOffset:(CGSize)sizeOffset;

/**
 *	Modifies the NSLayoutConstraint constant,
 *  only affects TIMMASConstraints in which the first item's NSLayoutAttribute is one of the following
 *  NSLayoutAttributeCenterX, NSLayoutAttributeCenterY
 */
- (void)setCenterOffset:(CGPoint)centerOffset;

/**
 *	Modifies the NSLayoutConstraint constant
 */
- (void)setOffset:(CGFloat)offset;


// NSLayoutConstraint Installation support

#if TARGET_OS_MAC && !(TARGET_OS_IPHONE || TARGET_OS_TV)
/**
 *  Whether or not to go through the animator proxy when modifying the constraint
 */
@property (nonatomic, copy, readonly) TIMMASConstraint *animator;
#endif

/**
 *  Activates an NSLayoutConstraint if it's supported by an OS. 
 *  Invokes install otherwise.
 */
- (void)activate;

/**
 *  Deactivates previously installed/activated NSLayoutConstraint.
 */
- (void)deactivate;

/**
 *	Creates a NSLayoutConstraint and adds it to the appropriate view.
 */
- (void)install;

/**
 *	Removes previously installed NSLayoutConstraint
 */
- (void)uninstall;

@end


/**
 *  Convenience auto-boxing macros for TIMMASConstraint methods.
 *
 *  Defining MAS_SHORTHAND_GLOBALS will turn on auto-boxing for default syntax.
 *  A potential drawback of this is that the unprefixed macros will appear in global scope.
 */
#define mas_TIMequalTo(...)                 equalTo(MASBoxValue((__VA_ARGS__)))
#define mas_TIMgreaterThanOrEqualTo(...)    greaterThanOrEqualTo(MASBoxValue((__VA_ARGS__)))
#define mas_TIMlessThanOrEqualTo(...)       lessThanOrEqualTo(MASBoxValue((__VA_ARGS__)))

#define mas_TIMoffset(...)                  valueOffset(MASBoxValue((__VA_ARGS__)))


#ifdef MAS_SHORTHAND_GLOBALS

#define equalTo(...)                     mas_TIMequalTo(__VA_ARGS__)
#define greaterThanOrEqualTo(...)        mas_TIMgreaterThanOrEqualTo(__VA_ARGS__)
#define lessThanOrEqualTo(...)           mas_TIMlessThanOrEqualTo(__VA_ARGS__)

#define offset(...)                      mas_TIMoffset(__VA_ARGS__)

#endif


@interface TIMMASConstraint (AutoboxingSupport)

/**
 *  Aliases to corresponding relation methods (for shorthand macros)
 *  Also needed to aid autocompletion
 */
- (TIMMASConstraint * (^)(id attr))mas_TIMequalTo;
- (TIMMASConstraint * (^)(id attr))mas_TIMgreaterThanOrEqualTo;
- (TIMMASConstraint * (^)(id attr))mas_TIMlessThanOrEqualTo;

/**
 *  A dummy method to aid autocompletion
 */
- (TIMMASConstraint * (^)(id offset))mas_TIMoffset;

@end
