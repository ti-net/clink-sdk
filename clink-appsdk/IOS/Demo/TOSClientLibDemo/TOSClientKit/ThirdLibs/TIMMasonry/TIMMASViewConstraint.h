//
//  TIMMASConstraint.h
//  Masonry
//
//  Created by Jonas Budelmann on 20/07/13.
//  Copyright (c) 2013 cloudling. All rights reserved.
//

#import "TIMMASViewAttribute.h"
#import "TIMMASConstraint.h"
#import "TIMMASLayoutConstraint.h"
#import "TIMMASUtilities.h"

/**
 *  A single constraint.
 *  Contains the attributes neccessary for creating a NSLayoutConstraint and adding it to the appropriate view
 */
@interface TIMMASViewConstraint : TIMMASConstraint <NSCopying>

/**
 *	First item/view and first attribute of the NSLayoutConstraint
 */
@property (nonatomic, strong, readonly) TIMMASViewAttribute *firstViewAttribute;

/**
 *	Second item/view and second attribute of the NSLayoutConstraint
 */
@property (nonatomic, strong, readonly) TIMMASViewAttribute *secondViewAttribute;

/**
 *	initialises the TIMMASViewConstraint with the first part of the equation
 *
 *	@param	firstViewAttribute	view.mas_TIMleft, view.mas_TIMwidth etc.
 *
 *	@return	a new view constraint
 */
- (id)initWithFirstViewAttribute:(TIMMASViewAttribute *)firstViewAttribute;

/**
 *  Returns all TIMMASViewConstraints installed with this view as a first item.
 *
 *  @param  view  A view to retrieve constraints for.
 *
 *  @return An array of TIMMASViewConstraints.
 */
+ (NSArray *)installedConstraintsForView:(MAS_VIEW *)view;

@end
