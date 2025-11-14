//
//  TRMacroToolsDefine.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//  UI界面适配宏

#ifndef TRMacroToolsDefine_h
#define TRMacroToolsDefine_h


#pragma mark - UI适配

#define IS_IPAD (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
#define IS_IPHONE (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPhone)

#define SCREEN_WIDTH ([[UIScreen mainScreen] bounds].size.width)
#define SCREEN_HEIGHT ([[UIScreen mainScreen] bounds].size.height)
#define SCREEN_MAX_LENGTH (MAX(SCREEN_WIDTH, SCREEN_HEIGHT))
#define SCREEN_MIN_LENGTH (MIN(SCREEN_WIDTH, SCREEN_HEIGHT))
#define SCALE(x)  x*self.scale
#define HEIGHTSCALE(x)  (IS_IPHONE_X || IS_IPHONE_XsMax ? x*self.heightScale : x*self.scale)


#define IS_IPHONE_4_OR_LESS (IS_IPHONE && SCREEN_MAX_LENGTH < 568.0)
#define IS_IPHONE_5 (IS_IPHONE && SCREEN_MAX_LENGTH == 568.0)

#define IS_IPHONE_6 (IS_IPHONE && SCREEN_MAX_LENGTH == 667.0)
#define IS_IPHONE_6P (IS_IPHONE && SCREEN_MAX_LENGTH == 736.0)
#define IS_IPHONE_X (IS_IPHONE && SCREEN_MAX_LENGTH == 812)
#define IS_IPHONE_XsMax (IS_IPHONE && SCREEN_MAX_LENGTH == 896)

#define IS_IPHONE_BangsScreen (IS_IPHONE && SCREEN_MAX_LENGTH >= 780)

#define kNavTop (IS_IPHONE_BangsScreen ? 88.f : 64.f)
#define kBottomBarHeight (IS_IPHONE_BangsScreen ? 34.0f : 0)
#define kStatusBarHeight    (IS_IPHONE_BangsScreen ? 44.f : 20.f)
#define kTabBarHeight 49.f


#pragma mark - 其他
//url
#define SettingURL [NSURL URLWithString:UIApplicationOpenSettingsURLString]
//跳转系统的设置页面
#define GoToSetting if([[UIApplication sharedApplication] canOpenURL:SettingURL]) { \
[[UIApplication sharedApplication] openURL:SettingURL];}


//RGB进制颜色值
#define kRGBColor(r,g,b)  [UIColor colorWithRed:(r)/255.0f green:(g)/255.0f blue:(b)/255.0f alpha:1]
#define kRGBAColor(r,g,b,a)  [UIColor colorWithRed:(r)/255.0f green:(g)/255.0f blue:(b)/255.0f alpha:(a)]


//16进制颜色值，注意：在使用的时候hexValue写成：0x000000
#define kHexColor(hexValue) [UIColor colorWithRed:((float)(((hexValue) & 0xFF0000) >> 16))/255.0 green:((float)(((hexValue) & 0xFF00) >> 8))/255.0 blue:((float)((hexValue) & 0xFF))/255.0 alpha:1.0]
#define kHexAColor(hexValue,a) [UIColor colorWithRed:((float)(((hexValue) & 0xFF0000) >> 16))/255.0 green:((float)(((hexValue) & 0xFF00) >> 8))/255.0 blue:((float)((hexValue) & 0xFF))/255.0 alpha:a]

//初始化xib文件
#define kInitXibName(nibName) [[UINib nibWithNibName:nibName bundle:nil] instantiateWithOwner:nil options:nil].firstObject

//网络检测
#define kNetworkIsOK ((AppDelegate*)[UIApplication sharedApplication].delegate).networkIsOK



#define kWindowWidth    [UIScreen mainScreen].bounds.size.width
#define kWindowHeight   [[UIScreen mainScreen] bounds].size.height
#define kMainWindow      [[[UIApplication sharedApplication] delegate] window]

#define rcd_dispatch_main_sync_safe(block) if ([NSThread isMainThread]) { block(); } else { dispatch_sync(dispatch_get_main_queue(), block); }

#define rcd_dispatch_main_async_safe(block) if ([NSThread isMainThread]) { block(); } else { dispatch_async(dispatch_get_main_queue(), block); }
#define rcd_dispatch_global_async_safe(block) if (![NSThread isMainThread]) { block(); } else { dispatch_async(dispatch_get_global_queue(0, 0), block); }

#ifdef DEBUG
#define NSLog(format, ...) printf("\n[%s] %s [第%d行] %s\n", __TIME__, __FUNCTION__, __LINE__, [[NSString stringWithFormat:format, ## __VA_ARGS__] UTF8String]);
#else
#define NSLog(format, ...)
#endif

#endif /* TRMacroToolsDefine_h */
