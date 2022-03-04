//
//  TIMKitConstants.h
//  TIMClientKitDemo
//
//  Created by YanBo on 2020/5/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#ifndef TIMKitConstants_h
#define TIMKitConstants_h

/// 登录应用编号保存
static NSString * const kAppIdNumbers = @"kAppIdNumbers";
/// 登录用户名保存
static NSString * const kLoginUserName = @"kLoginUserName";
/// 登录密码保存
static NSString * const kLoginPassword = @"kLoginPassword";
/// 登录token保存
static NSString * const kLoginToken = @"kLoginToken";
/// 登录platformShowName
static NSString * const kPlatformShowName = @"kPlatformShowName";
/// 登录信息字典保存
static NSString * const kLoginInfoDic = @"kLoginInfoDic";
/// AppServer登录域名保存
static NSString * const kLoginDomainName = @"kLoginDomainName";
/// IM登录域名保存
static NSString * const kIMLoginDomainName = @"kIMLoginDomainName";
/// 项目域名保存
static NSString * const kDomainName = @"kDomainName";

static NSString * const kFontNameRegular = @"PingFangSC-Regular";      //常规
static NSString * const kFontNameMedium = @"PingFangSC-Medium";        //中黑体
static NSString * const kFontNameLight = @"PingFangSC-Light";   //细体

//static NSInteger const kStateBarColor = 0xFFFFFF;
//static NSInteger const kBackgroundColor = 0xF5F8F9;
//static NSInteger const kThemeColor = 0x2397FF;
//static NSInteger const kDefaultBlue = 0x007AFF;

static NSString * const kTRLocalizeLanguageCodeKey = @"LangCode";
static NSString * const kTRLocalizeLanguageDescriptionKey = @"LangDescription";
static NSString * const kUserDefaultLanguageKey = @"kUserDefaultLanguageKey";

static CGFloat const kNetworkRequestTimeout = 30.f;
static NSInteger const kBackgroundColor = 0xF5F8F9;

//获取语言
#define TRLocalizedString(key) [[TRLocalizeHelper sharedInstance] localizedStringForKey:key]


//16进制颜色值，注意：在使用的时候hexValue写成：0x000000
#define kHexColor(hexValue) [UIColor colorWithRed:((float)(((hexValue) & 0xFF0000) >> 16))/255.0 green:((float)(((hexValue) & 0xFF00) >> 8))/255.0 blue:((float)((hexValue) & 0xFF))/255.0 alpha:1.0]
#define kHexAColor(hexValue,a) [UIColor colorWithRed:((float)(((hexValue) & 0xFF0000) >> 16))/255.0 green:((float)(((hexValue) & 0xFF00) >> 8))/255.0 blue:((float)((hexValue) & 0xFF))/255.0 alpha:a]

#define kWindowWidth    [UIScreen mainScreen].bounds.size.width
#define kWindowHeight   [[UIScreen mainScreen] bounds].size.height
#define kMainWindow      [[[UIApplication sharedApplication] delegate] window]

//RGB进制颜色值
#define kRGBColor(r,g,b)  [UIColor colorWithRed:(r)/255.0f green:(g)/255.0f blue:(b)/255.0f alpha:1]
#define kRGBAColor(r,g,b,a)  [UIColor colorWithRed:(r)/255.0f green:(g)/255.0f blue:(b)/255.0f alpha:(a)]

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


#define kNavTop (IS_IPHONE_X || IS_IPHONE_XsMax ? 88.f : 64.f)
#define kBottomBarHeight (IS_IPHONE_X || IS_IPHONE_XsMax ? 34.0f : 0)
#define kStatusBarHeight    (IS_IPHONE_X || IS_IPHONE_XsMax ? 44.f : 20.f)
#define kTabBarHeight 49.f



#define kTiNet_UserDef_OBJ(s) [[NSUserDefaults standardUserDefaults] objectForKey:s]
#define kTiNet_UserDef_Bool(s) [[NSUserDefaults standardUserDefaults] boolForKey:s]
#define kTiNet_UserDef [NSUserDefaults standardUserDefaults]

#endif /* TIMKitConstants_h */
