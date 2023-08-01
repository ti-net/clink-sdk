//
//  UIViewController+TIMMASAdditions.h
//  Masonry
//
//  Created by Craig Siemens on 2015-06-23.
//
//

#import "TIMMASUtilities.h"
#import "TIMMASConstraintMaker.h"
#import "TIMMASViewAttribute.h"

#ifdef MAS_VIEW_CONTROLLER

@interface MAS_VIEW_CONTROLLER (TIMMASAdditions)

/**
 *	following properties return a new TIMMASViewAttribute with appropriate UILayoutGuide and NSLayoutAttribute
 */
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMtopLayoutGuide;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMbottomLayoutGuide;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMtopLayoutGuideTop;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMtopLayoutGuideBottom;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMbottomLayoutGuideTop;
@property (nonatomic, strong, readonly) TIMMASViewAttribute *mas_TIMbottomLayoutGuideBottom;


@end

#endif
