//
//  TIMMASConstraintBuilder.m
//  Masonry
//
//  Created by Jonas Budelmann on 20/07/13.
//  Copyright (c) 2013 cloudling. All rights reserved.
//

#import "TIMMASConstraintMaker.h"
#import "TIMMASViewConstraint.h"
#import "TIMMASCompositeConstraint.h"
#import "TIMMASConstraint+Private.h"
#import "TIMMASViewAttribute.h"
#import "View+TIMMASAdditions.h"

@interface TIMMASConstraintMaker () <TIMMASConstraintDelegate>

@property (nonatomic, weak) MAS_VIEW *view;
@property (nonatomic, strong) NSMutableArray *constraints;

@end

@implementation TIMMASConstraintMaker

- (id)initWithView:(MAS_VIEW *)view {
    self = [super init];
    if (!self) return nil;
    
    self.view = view;
    self.constraints = NSMutableArray.new;
    
    return self;
}

- (NSArray *)install {
    if (self.removeExisting) {
        NSArray *installedConstraints = [TIMMASViewConstraint installedConstraintsForView:self.view];
        for (TIMMASConstraint *constraint in installedConstraints) {
            [constraint uninstall];
        }
    }
    NSArray *constraints = self.constraints.copy;
    for (TIMMASConstraint *constraint in constraints) {
        constraint.updateExisting = self.updateExisting;
        [constraint install];
    }
    [self.constraints removeAllObjects];
    return constraints;
}

#pragma mark - TIMMASConstraintDelegate

- (void)constraint:(TIMMASConstraint *)constraint shouldBeReplacedWithConstraint:(TIMMASConstraint *)replacementConstraint {
    NSUInteger index = [self.constraints indexOfObject:constraint];
    NSAssert(index != NSNotFound, @"Could not find constraint %@", constraint);
    [self.constraints replaceObjectAtIndex:index withObject:replacementConstraint];
}

- (TIMMASConstraint *)constraint:(TIMMASConstraint *)constraint addConstraintWithLayoutAttribute:(NSLayoutAttribute)layoutAttribute {
    TIMMASViewAttribute *viewAttribute = [[TIMMASViewAttribute alloc] initWithView:self.view layoutAttribute:layoutAttribute];
    TIMMASViewConstraint *newConstraint = [[TIMMASViewConstraint alloc] initWithFirstViewAttribute:viewAttribute];
    if ([constraint isKindOfClass:TIMMASViewConstraint.class]) {
        //replace with composite constraint
        NSArray *children = @[constraint, newConstraint];
        TIMMASCompositeConstraint *compositeConstraint = [[TIMMASCompositeConstraint alloc] initWithChildren:children];
        compositeConstraint.delegate = self;
        [self constraint:constraint shouldBeReplacedWithConstraint:compositeConstraint];
        return compositeConstraint;
    }
    if (!constraint) {
        newConstraint.delegate = self;
        [self.constraints addObject:newConstraint];
    }
    return newConstraint;
}

- (TIMMASConstraint *)addConstraintWithAttributes:(TIMMASAttribute)attrs {
    __unused TIMMASAttribute anyAttribute = (TIMMASAttributeLeft | TIMMASAttributeRight | TIMMASAttributeTop | TIMMASAttributeBottom | TIMMASAttributeLeading
                                          | TIMMASAttributeTrailing | TIMMASAttributeWidth | TIMMASAttributeHeight | TIMMASAttributeCenterX
                                          | TIMMASAttributeCenterY | TIMMASAttributeBaseline
#if (__IPHONE_OS_VERSION_MIN_REQUIRED >= 80000) || (__TV_OS_VERSION_MIN_REQUIRED >= 9000) || (__MAC_OS_X_VERSION_MIN_REQUIRED >= 101100)
                                          | TIMMASAttributeFirstBaseline | TIMMASAttributeLastBaseline
#endif
#if TARGET_OS_IPHONE || TARGET_OS_TV
                                          | TIMMASAttributeLeftMargin | TIMMASAttributeRightMargin | TIMMASAttributeTopMargin | TIMMASAttributeBottomMargin
                                          | TIMMASAttributeLeadingMargin | TIMMASAttributeTrailingMargin | TIMMASAttributeCenterXWithinMargins
                                          | TIMMASAttributeCenterYWithinMargins
#endif
                                          );
    
    NSAssert((attrs & anyAttribute) != 0, @"You didn't pass any attribute to make.attributes(...)");
    
    NSMutableArray *attributes = [NSMutableArray array];
    
    if (attrs & TIMMASAttributeLeft) [attributes addObject:self.view.mas_TIMleft];
    if (attrs & TIMMASAttributeRight) [attributes addObject:self.view.mas_TIMright];
    if (attrs & TIMMASAttributeTop) [attributes addObject:self.view.mas_TIMtop];
    if (attrs & TIMMASAttributeBottom) [attributes addObject:self.view.mas_TIMbottom];
    if (attrs & TIMMASAttributeLeading) [attributes addObject:self.view.mas_TIMleading];
    if (attrs & TIMMASAttributeTrailing) [attributes addObject:self.view.mas_TIMtrailing];
    if (attrs & TIMMASAttributeWidth) [attributes addObject:self.view.mas_TIMwidth];
    if (attrs & TIMMASAttributeHeight) [attributes addObject:self.view.mas_TIMheight];
    if (attrs & TIMMASAttributeCenterX) [attributes addObject:self.view.mas_TIMcenterX];
    if (attrs & TIMMASAttributeCenterY) [attributes addObject:self.view.mas_TIMcenterY];
    if (attrs & TIMMASAttributeBaseline) [attributes addObject:self.view.mas_TIMbaseline];
    
#if (__IPHONE_OS_VERSION_MIN_REQUIRED >= 80000) || (__TV_OS_VERSION_MIN_REQUIRED >= 9000) || (__MAC_OS_X_VERSION_MIN_REQUIRED >= 101100)
    
    if (attrs & TIMMASAttributeFirstBaseline) [attributes addObject:self.view.mas_TIMfirstBaseline];
    if (attrs & TIMMASAttributeLastBaseline) [attributes addObject:self.view.mas_TIMlastBaseline];
    
#endif
    
#if TARGET_OS_IPHONE || TARGET_OS_TV
    
    if (attrs & TIMMASAttributeLeftMargin) [attributes addObject:self.view.mas_TIMleftMargin];
    if (attrs & TIMMASAttributeRightMargin) [attributes addObject:self.view.mas_TIMrightMargin];
    if (attrs & TIMMASAttributeTopMargin) [attributes addObject:self.view.mas_TIMtopMargin];
    if (attrs & TIMMASAttributeBottomMargin) [attributes addObject:self.view.mas_TIMbottomMargin];
    if (attrs & TIMMASAttributeLeadingMargin) [attributes addObject:self.view.mas_TIMleadingMargin];
    if (attrs & TIMMASAttributeTrailingMargin) [attributes addObject:self.view.mas_TIMtrailingMargin];
    if (attrs & TIMMASAttributeCenterXWithinMargins) [attributes addObject:self.view.mas_TIMcenterXWithinMargins];
    if (attrs & TIMMASAttributeCenterYWithinMargins) [attributes addObject:self.view.mas_TIMcenterYWithinMargins];
    
#endif
    
    NSMutableArray *children = [NSMutableArray arrayWithCapacity:attributes.count];
    
    for (TIMMASViewAttribute *a in attributes) {
        [children addObject:[[TIMMASViewConstraint alloc] initWithFirstViewAttribute:a]];
    }
    
    TIMMASCompositeConstraint *constraint = [[TIMMASCompositeConstraint alloc] initWithChildren:children];
    constraint.delegate = self;
    [self.constraints addObject:constraint];
    return constraint;
}

#pragma mark - standard Attributes

- (TIMMASConstraint *)addConstraintWithLayoutAttribute:(NSLayoutAttribute)layoutAttribute {
    return [self constraint:nil addConstraintWithLayoutAttribute:layoutAttribute];
}

- (TIMMASConstraint *)left {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeLeft];
}

- (TIMMASConstraint *)top {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeTop];
}

- (TIMMASConstraint *)right {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeRight];
}

- (TIMMASConstraint *)bottom {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeBottom];
}

- (TIMMASConstraint *)leading {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeLeading];
}

- (TIMMASConstraint *)trailing {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeTrailing];
}

- (TIMMASConstraint *)width {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeWidth];
}

- (TIMMASConstraint *)height {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeHeight];
}

- (TIMMASConstraint *)centerX {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeCenterX];
}

- (TIMMASConstraint *)centerY {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeCenterY];
}

- (TIMMASConstraint *)baseline {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeBaseline];
}

- (TIMMASConstraint *(^)(TIMMASAttribute))attributes {
    return ^(TIMMASAttribute attrs){
        return [self addConstraintWithAttributes:attrs];
    };
}

#if (__IPHONE_OS_VERSION_MIN_REQUIRED >= 80000) || (__TV_OS_VERSION_MIN_REQUIRED >= 9000) || (__MAC_OS_X_VERSION_MIN_REQUIRED >= 101100)

- (TIMMASConstraint *)firstBaseline {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeFirstBaseline];
}

- (TIMMASConstraint *)lastBaseline {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeLastBaseline];
}

#endif


#if TARGET_OS_IPHONE || TARGET_OS_TV

- (TIMMASConstraint *)leftMargin {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeLeftMargin];
}

- (TIMMASConstraint *)rightMargin {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeRightMargin];
}

- (TIMMASConstraint *)topMargin {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeTopMargin];
}

- (TIMMASConstraint *)bottomMargin {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeBottomMargin];
}

- (TIMMASConstraint *)leadingMargin {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeLeadingMargin];
}

- (TIMMASConstraint *)trailingMargin {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeTrailingMargin];
}

- (TIMMASConstraint *)centerXWithinMargins {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeCenterXWithinMargins];
}

- (TIMMASConstraint *)centerYWithinMargins {
    return [self addConstraintWithLayoutAttribute:NSLayoutAttributeCenterYWithinMargins];
}

#endif


#pragma mark - composite Attributes

- (TIMMASConstraint *)edges {
    return [self addConstraintWithAttributes:TIMMASAttributeTop | TIMMASAttributeLeft | TIMMASAttributeRight | TIMMASAttributeBottom];
}

- (TIMMASConstraint *)size {
    return [self addConstraintWithAttributes:TIMMASAttributeWidth | TIMMASAttributeHeight];
}

- (TIMMASConstraint *)center {
    return [self addConstraintWithAttributes:TIMMASAttributeCenterX | TIMMASAttributeCenterY];
}

#pragma mark - grouping

- (TIMMASConstraint *(^)(dispatch_block_t group))group {
    return ^id(dispatch_block_t group) {
        NSInteger previousCount = self.constraints.count;
        group();

        NSArray *children = [self.constraints subarrayWithRange:NSMakeRange(previousCount, self.constraints.count - previousCount)];
        TIMMASCompositeConstraint *constraint = [[TIMMASCompositeConstraint alloc] initWithChildren:children];
        constraint.delegate = self;
        return constraint;
    };
}

@end
