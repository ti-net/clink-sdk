//
//  TIMMASCompositeConstraint.h
//  Masonry
//
//  Created by Jonas Budelmann on 21/07/13.
//  Copyright (c) 2013 cloudling. All rights reserved.
//

#import "TIMMASConstraint.h"
#import "TIMMASUtilities.h"

/**
 *	A group of TIMMASConstraint objects
 */
@interface TIMMASCompositeConstraint : TIMMASConstraint

/**
 *	Creates a composite with a predefined array of children
 *
 *	@param	children	child TIMMASConstraints
 *
 *	@return	a composite constraint
 */
- (id)initWithChildren:(NSArray *)children;

@end
