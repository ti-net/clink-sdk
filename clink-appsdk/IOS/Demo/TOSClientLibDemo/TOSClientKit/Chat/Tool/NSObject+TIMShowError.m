//
//  NSObject+TIMShowError.m
//  TIMClientKit
//
//  Created by 赵言 on 2020/12/17.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "NSObject+TIMShowError.h"
#import "TIMMBProgressHUD.h"
#import "TIMConstants.h"
#import "AppDelegate.h"

@implementation NSObject (TIMShowError)

- (void)tim_showMBErrorView:(NSString *)str {
    if (strcmp(dispatch_queue_get_label(DISPATCH_CURRENT_QUEUE_LABEL), dispatch_queue_get_label(dispatch_get_main_queue())) == 0) {
        [self tim_showMBProgress:str];
    } else {
        dispatch_async(dispatch_get_main_queue(), ^{
            [self tim_showMBProgress:str];
        });
    }
}

- (void)tim_showMBProgress:(NSString *)str {
    TIMMBProgressHUD *hud = [TIMMBProgressHUD showHUDAddedTo:((AppDelegate*)[UIApplication sharedApplication].delegate).window animated:YES];
    [self tim_setupMBProgress:hud];
    hud.mode = MBProgressHUDModeText;
    hud.margin = 10.f;
    hud.detailsLabel.text = str?:@"";
    hud.offset = CGPointMake(kWindowWidth/2 - hud.frame.size.width/2, kWindowHeight/4);
    [hud hideAnimated:YES afterDelay:2.f];
}

- (void)tim_setupMBProgress:(TIMMBProgressHUD *)mb {
    mb.contentColor = [UIColor whiteColor];
    mb.bezelView.color = TOSHexColor(0x333333);
    mb.bezelView.style = MBProgressHUDBackgroundStyleSolidColor;
    mb.minShowTime = 0;
    mb.userInteractionEnabled = NO;
    mb.detailsLabel.font = [UIFont fontWithName:@"PingFangSC-Light" size:14.f];
    mb.removeFromSuperViewOnHide = YES;
}

@end
