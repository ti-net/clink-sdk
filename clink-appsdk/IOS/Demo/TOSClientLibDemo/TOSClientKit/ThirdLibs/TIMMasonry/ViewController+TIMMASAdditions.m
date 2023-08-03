//
//  UIViewController+TIMMASAdditions.m
//  Masonry
//
//  Created by Craig Siemens on 2015-06-23.
//
//

#import "ViewController+TIMMASAdditions.h"

#ifdef MAS_VIEW_CONTROLLER

@implementation MAS_VIEW_CONTROLLER (TIMMASAdditions)

- (TIMMASViewAttribute *)mas_TIMtopLayoutGuide {
    return [[TIMMASViewAttribute alloc] initWithView:self.view item:self.topLayoutGuide layoutAttribute:NSLayoutAttributeBottom];
}
- (TIMMASViewAttribute *)mas_TIMtopLayoutGuideTop {
    return [[TIMMASViewAttribute alloc] initWithView:self.view item:self.topLayoutGuide layoutAttribute:NSLayoutAttributeTop];
}
- (TIMMASViewAttribute *)mas_TIMtopLayoutGuideBottom {
    return [[TIMMASViewAttribute alloc] initWithView:self.view item:self.topLayoutGuide layoutAttribute:NSLayoutAttributeBottom];
}

- (TIMMASViewAttribute *)mas_TIMbottomLayoutGuide {
    return [[TIMMASViewAttribute alloc] initWithView:self.view item:self.bottomLayoutGuide layoutAttribute:NSLayoutAttributeTop];
}
- (TIMMASViewAttribute *)mas_TIMbottomLayoutGuideTop {
    return [[TIMMASViewAttribute alloc] initWithView:self.view item:self.bottomLayoutGuide layoutAttribute:NSLayoutAttributeTop];
}
- (TIMMASViewAttribute *)mas_TIMbottomLayoutGuideBottom {
    return [[TIMMASViewAttribute alloc] initWithView:self.view item:self.bottomLayoutGuide layoutAttribute:NSLayoutAttributeBottom];
}



@end

#endif
