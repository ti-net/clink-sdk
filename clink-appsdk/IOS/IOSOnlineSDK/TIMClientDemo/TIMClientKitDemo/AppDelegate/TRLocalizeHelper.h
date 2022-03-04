//
//  TRLocalizeHelper.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/6.
//  Copyright © 2019 赵言. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

//设置语言
#define TRLocalizationSetLanguage(lang) [[TRLocalizeHelper sharedInstance] setLanguage:lang]

//获取语言
#define TRLocalizedString(key) [[TRLocalizeHelper sharedInstance] localizedStringForKey:key]

//获取当前选择的语言
#define TRLocalizationCurrentLanguageDes [TRLocalizeHelper sharedInstance].currentLanguageDescription

@interface TRLocalizeHelper : NSObject

@property (nonatomic, readonly, strong) NSString *currentLanguage;
@property (nonatomic, readonly, strong) NSString *currentLanguageDescription;


/// 单利
+ (instancetype)sharedInstance;

/// 设置语言
/// @param lang 语言简体
- (void)setLanguage:(NSString *)lang;

/// 获取当前语言对应的文本
/// @param key key
- (NSString *)localizedStringForKey:(NSString *)key;

@end

NS_ASSUME_NONNULL_END
