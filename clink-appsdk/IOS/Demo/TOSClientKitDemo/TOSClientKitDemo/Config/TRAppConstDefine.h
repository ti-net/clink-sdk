//
//  TRAppConstDefine.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//

#ifndef TRAppConstDefine_h
#define TRAppConstDefine_h

/// AppDelegate
#define TRSharedAppDelegate ((AppDelegate *)[UIApplication sharedApplication].delegate)


/*
 苹方-简 中黑体 PingFangSC-Medium
 苹方-简 中粗体 PingFangSC-Semibold
 苹方-简 细体   PingFangSC-Light
 苹方-简 极细体 PingFangSC-Ultralight
 苹方-简 常规体 PingFangSC-Regular
 苹方-简 纤细体 PingFangSC-Thin
 */

static NSString * const kFontNameRegular = @"PingFangSC-Regular";      //常规
static NSString * const kFontNameMedium = @"PingFangSC-Medium";        //中黑体
static NSString * const kFontNameLight = @"PingFangSC-Light";          //细体

static NSString * const kTRLocalizeLanguageCodeKey = @"LangCode";
static NSString * const kTRLocalizeLanguageDescriptionKey = @"LangDescription";
static NSString * const kUserDefaultLanguageKey = @"kUserDefaultLanguageKey";

static NSInteger const kStateBarColor = 0xFFFFFF;
static NSInteger const kBackgroundColor = 0xF3F6F9;
static NSInteger const kThemeColor = 0x2397FF;
static NSInteger const kDefaultBlue = 0x007AFF;

static CGFloat const kNetworkRequestTimeout = 30.f;
static CGFloat const kImageUploadNetworkRequestTimeout = 30.f;

static CGFloat const kWMPageHeight = 44.f;

//cookie保存
static NSString *kCookieString = @"TRCookieString";

#pragma mark - 正则
//13位时间戳
static NSString * const kTimeStampRegular = @"^[0-9]{13}+$";
//企业编号/座席编
static NSString * const kEnterpriseNumbersRegular = @"^[a-zA-Z0-9]{1,}+$";
//6到10位数字字母组合的密码
static NSString * const kPasswordRegular = @"^[a-zA-Z0-9]{6,10}+$";
//4位数字字母组合的验证码
static NSString * const kVerificationCodeRegular = @"^[a-zA-Z0-9]{4}+$";
//手机号验证
static NSString * const kPhoneNumber = @"^1[3-9]\\d{9}+$";
//固话号验证
static NSString * const kFixedPhoneNumber = @"^010\\d{8}$|^02\\d{9}$|^0[3,4,5,6,7,8,9][0-9]\\d{8,9}+$";

#endif /* TRAppConstDefine_h */
