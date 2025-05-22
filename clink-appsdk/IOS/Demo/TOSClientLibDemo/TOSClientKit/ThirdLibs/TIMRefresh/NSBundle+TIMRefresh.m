//
//  NSBundle+TIMRefresh.m
//  TIMRefreshExample
//
//  Created by MJ Lee on 16/6/13.
//  Copyright © 2016年 小码哥. All rights reserved.
//

#import "NSBundle+TIMRefresh.h"
#import "TIMRefreshComponent.h"
#import "TIMRefreshConfig.h"

@implementation NSBundle (TIMRefresh)
+ (instancetype)mj_refreshTIMBundle
{
    static NSBundle *refreshBundle = nil;
    if (refreshBundle == nil) {
        // 这里不使用mainBundle是为了适配pod 1.x和0.x
        refreshBundle = [NSBundle bundleWithPath:[[NSBundle bundleForClass:[TIMRefreshComponent class]] pathForResource:@"TOSClient" ofType:@"bundle"]];
    }
    return refreshBundle;
}

+ (UIImage *)mj_arrowTIMImage
{
    static UIImage *arrowImage = nil;
    if (arrowImage == nil) {
        arrowImage = [[UIImage imageWithContentsOfFile:[[self mj_refreshTIMBundle] pathForResource:@"arrow@2x" ofType:@"jpg"]] imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate];
    }
    return arrowImage;
}

+ (NSString *)tim_mj_localizedStringForTIMKey:(NSString *)key
{
    return [self tim_mj_localizedStringForTIMKey:key value:nil];
}

+ (NSString *)tim_mj_localizedStringForTIMKey:(NSString *)key value:(NSString *)value
{
    static NSBundle *bundle = nil;
    if (bundle == nil) {
        NSString *language = TIMRefreshConfig.defaultConfig.languageCode;
        
        // 如果配置中没有配置语言
        if (!language) {
            // （iOS获取的语言字符串比较不稳定）目前框架只处理en、zh-Hans、zh-Hant三种情况，其他按照系统默认处理
            language = [NSLocale preferredLanguages].firstObject;
        }
        
        if ([language hasPrefix:@"en"]) {
            language = @"en";
        } else if ([language hasPrefix:@"zh"]) {
            if ([language rangeOfString:@"Hans"].location != NSNotFound) {
                language = @"zh-Hans"; // 简体中文
            } else { // zh-Hant\zh-HK\zh-TW
                language = @"zh-Hant"; // 繁體中文
            }
        } else if ([language hasPrefix:@"ko"]) {
            language = @"ko";
        } else if ([language hasPrefix:@"ru"]) {
            language = @"ru";
        } else if ([language hasPrefix:@"uk"]) {
            language = @"uk";
        } else {
            language = @"en";
        }
        
        // 从TIMRefresh.bundle中查找资源
        bundle = [NSBundle bundleWithPath:[[NSBundle mj_refreshTIMBundle] pathForResource:language ofType:@"lproj"]];
        [bundle load];
    }

//    value = [bundle localizedStringForKey:key value:@"" table:nil];
    value = key; //动态库使用国际化加载暂时有问题 后续优化
    return [[NSBundle mainBundle] localizedStringForKey:key value:value table:nil];
}
@end
