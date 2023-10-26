//
//  MBProgressHUD+GeneralConfiguration.m
//  Demo
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "MBProgressHUD+GeneralConfiguration.h"
#import <UIKit/UIKit.h>


@implementation MBProgressHUD (GeneralConfiguration)

- (void)setupMBProgress {
    self.contentColor = [UIColor whiteColor];
    self.bezelView.color = [UIColor whiteColor];
    self.bezelView.style = MBProgressHUDBackgroundStyleSolidColor;
    self.minShowTime = 0;
    self.userInteractionEnabled = NO;
    self.detailsLabel.font = [UIFont fontWithName:kFontNameRegular size:12.f];
    self.detailsLabel.textColor = kHexColor(0xFF4D4F);
    self.removeFromSuperViewOnHide = YES;
}

@end
