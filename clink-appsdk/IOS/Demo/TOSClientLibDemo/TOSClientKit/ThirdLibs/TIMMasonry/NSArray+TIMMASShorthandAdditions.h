//
//  NSArray+TIMMASShorthandAdditions.h
//  Masonry
//
//  Created by Jonas Budelmann on 22/07/13.
//  Copyright (c) 2013 Jonas Budelmann. All rights reserved.
//

#import "NSArray+TIMMASAdditions.h"

#ifdef MAS_SHORTHAND

/**
 *	Shorthand array additions without the 'mas_TIM' prefixes,
 *  only enabled if MAS_SHORTHAND is defined
 */
@interface NSArray (TIMMASShorthandAdditions)

- (NSArray *)makeConstraints:(void(^)(TIMMASConstraintMaker *make))block;
- (NSArray *)updateConstraints:(void(^)(TIMMASConstraintMaker *make))block;
- (NSArray *)remakeConstraints:(void(^)(TIMMASConstraintMaker *make))block;

@end

@implementation NSArray (TIMMASShorthandAdditions)

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
