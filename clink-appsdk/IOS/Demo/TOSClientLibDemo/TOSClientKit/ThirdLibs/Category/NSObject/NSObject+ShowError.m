//
//  NSObject+ShowError.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/4.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "NSObject+ShowError.h"
//#import "MBProgressHUD+GeneralConfiguration.h"
#import "AppDelegate.h"

@implementation NSObject (ShowError)

- (void)showErrorView:(NSString *)str {
    if ([str isEqualToString:@"Access Denied"] || [str isEqualToString:@"OK"] || [str isEqualToString:@"ok"]) {
        return;
    }
}

@end
