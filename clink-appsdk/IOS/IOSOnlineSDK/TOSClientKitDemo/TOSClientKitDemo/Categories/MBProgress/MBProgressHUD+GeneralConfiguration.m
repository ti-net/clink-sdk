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
    self.bezelView.color = [UIColor colorNamed:@"TR333333Color"];
    self.bezelView.style = MBProgressHUDBackgroundStyleSolidColor;
    self.minShowTime = 0;
    self.userInteractionEnabled = NO;
    self.detailsLabel.font = [UIFont fontWithName:kFontNameLight size:14.f];
    self.removeFromSuperViewOnHide = YES;
}

@end
