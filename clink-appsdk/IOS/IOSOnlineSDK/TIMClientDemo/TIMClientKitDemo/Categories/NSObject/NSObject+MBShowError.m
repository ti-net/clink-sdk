//
//  NSObject+ShowError.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/4.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "NSObject+MBShowError.h"
#import "MBProgressHUD+GeneralConfiguration.h"
#import "AppDelegate.h"

@implementation NSObject (MBShowError)

- (void)showMBErrorView:(NSString *)str {
    if ([str isEqualToString:@"Access Denied"] || [str isEqualToString:@"OK"] || [str isEqualToString:@"ok"]) {
        return;
    }
    dispatch_async(dispatch_get_main_queue(), ^{
        MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:[AppDelegate getShareAppDelegate].window animated:YES];
        [hud setupMBProgress];
        hud.mode = MBProgressHUDModeText;
        hud.margin = 10.f;
        hud.detailsLabel.text = str;
        hud.offset = CGPointMake(kWindowWidth/2 - hud.width/2, kWindowHeight/4);
        [hud hideAnimated:YES afterDelay:2.f];
    });
}

@end
