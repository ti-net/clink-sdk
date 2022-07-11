//
//  WMPageController+TRSetupStyle.m
//  mobileCMS
//
//  Created by 赵言 on 2020/4/1.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "WMPageController+TRSetupStyle.h"

@implementation WMPageController (TRSetupStyle)

- (void)tr_SetupStyle {
    self.menuViewStyle      = WMMenuViewStyleLine;
    self.menuViewLayoutMode = WMMenuViewLayoutModeScatter;
    self.titleSizeNormal     = 14.f;
    self.scrollView.scrollsToTop = NO;
    self.menuView.scrollView.scrollsToTop = NO;
    self.titleSizeSelected   = 14.f;
    self.titleColorNormal   = kHexColor(0x666666);
    self.titleColorSelected = kHexColor(kThemeColor);
    self.progressColor = kHexColor(kThemeColor);
    self.titleFontName      = kFontNameRegular;
    self.preloadPolicy       = WMPageControllerPreloadPolicyNear;
    self.view.frame = CGRectMake(0, 0, kWindowWidth, kWindowHeight);
    self.menuItemWidth = 80.f;
    self.progressWidth = 30.f;
    self.view.backgroundColor = [UIColor whiteColor];
    self.menuView.backgroundColor = [UIColor whiteColor];
}

@end
