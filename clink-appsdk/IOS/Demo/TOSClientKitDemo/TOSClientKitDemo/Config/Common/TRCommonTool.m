//
//  TRCommonTool.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "TRCommonTool.h"
#import <CommonCrypto/CommonDigest.h>

@implementation TRCommonTool

#pragma make - 其他
// md5 小写
+ (NSString *)md5:(NSString *)string {
    const char *cStr = [string UTF8String];
    unsigned char digest[CC_MD5_DIGEST_LENGTH];
    CC_MD5(cStr, (CC_LONG)strlen(cStr), digest);
    NSMutableString *result = [NSMutableString stringWithCapacity:CC_MD5_DIGEST_LENGTH * 2];
    for (int i = 0; i < CC_MD5_DIGEST_LENGTH; i++) {
        [result appendFormat:@"%02x", digest[i]];
    }
    return result;
}

// 获取当前控制器
+ (UIViewController *)getCurrentVC {
    UIViewController *result = nil;
    
    UIWindow * window = [[UIApplication sharedApplication] keyWindow];
    if (window.windowLevel != UIWindowLevelNormal) {
        NSArray *windows = [[UIApplication sharedApplication] windows];
        for(UIWindow * tmpWin in windows) {
            if (tmpWin.windowLevel == UIWindowLevelNormal) {
                window = tmpWin;
                break;
            }
        }
    }
    
    UIView *frontView = [[window subviews] objectAtIndex:0];
    id nextResponder = [frontView nextResponder];
    
    if ([nextResponder isKindOfClass:[UIViewController class]]) {
        result = nextResponder;
    } else {
        result = window.rootViewController;
    }
    return result;
}

/// 秒 -> 时分秒
/// @param totalSeconds 秒
+ (NSString *)timeFormatted:(NSInteger)totalSeconds {
    NSInteger seconds = totalSeconds % 60;
    NSInteger minutes = (totalSeconds / 60) % 60;
    NSInteger hours = totalSeconds / 3600;
    return [NSString stringWithFormat:@"%02ld:%02ld:%02ld",(long)hours, minutes, seconds];
}

/// 秒 -> 时分秒
/// @param totalSeconds 秒
+ (NSString *)callTimeFormatted:(NSInteger)totalSeconds {
    
    NSInteger seconds = totalSeconds % 60;
    NSInteger minutes = (totalSeconds / 60) % 60;
    NSInteger hours = totalSeconds / 3600;
    
    if (totalSeconds > 3600) {
        return [NSString stringWithFormat:@"%02ld:%02ld:%02ld",(long)hours, minutes, seconds];
    } else {
        return [NSString stringWithFormat:@"%02ld:%02ld", minutes, seconds];
    }
}

+ (void)pushViewCAnimation {
    CATransition *transition = [CATransition animation];
    transition.duration = .2f;
    transition.type = kCATransitionFade;
    transition.subtype = kCATransitionFromTop;
    [[UIApplication sharedApplication].keyWindow.layer addAnimation:transition forKey:kCATransition];
}

@end
