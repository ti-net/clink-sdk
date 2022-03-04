//
//  TRLocalizeHelper.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/6.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "TRLocalizeHelper.h"

static NSArray *languageList = nil;

@interface TRLocalizeHelper ()
{
    NSBundle *_appBundle;
}

@property (nonatomic, readwrite, strong) NSString *currentLanguage;
@property (nonatomic, readwrite, strong) NSString *currentLanguageDescription;

@end

@implementation TRLocalizeHelper
+ (instancetype)sharedInstance {
    static id instance;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[self alloc] init];
    });
    return instance;
}

- (instancetype)init {
    if (self = [super init]) {
        _appBundle = [NSBundle mainBundle];
        
        NSUserDefaults *userDefaluts = [NSUserDefaults standardUserDefaults];
        _currentLanguage = [userDefaluts objectForKey:kUserDefaultLanguageKey] ? : @"zh-Hans";
        _currentLanguageDescription = [userDefaluts objectForKey:kUserDefaultLanguageKey] ? : @"zh";
        
        [self setupLanguageList];
        [self setLanguage:_currentLanguage];
    }
    return self;
}

#pragma mark - instance method
- (NSString *)localizedStringForKey:(NSString *)key {
    return [_appBundle localizedStringForKey:key value:@"" table:nil];
}

- (NSString *)localizedStringForKey:(NSString *)key fromTable:(NSString *)table {
    return [_appBundle localizedStringForKey:key value:@"" table:table];
}

- (void)setLanguage:(NSString *)lang {
    _currentLanguage = lang;
    
    NSString *bundlePath = [[NSBundle mainBundle] pathForResource:lang ofType:@"lproj"];
    
    if (bundlePath) {
        _appBundle = [NSBundle bundleWithPath:bundlePath] ?: [NSBundle mainBundle];
    }
    else {
        _appBundle = [NSBundle mainBundle];
    }
    
    @weakify(self);
    [languageList enumerateObjectsUsingBlock:^(NSDictionary *obj, NSUInteger idx, BOOL * _Nonnull stop) {
        @strongify(self);
        if ([obj[kTRLocalizeLanguageCodeKey] isEqualToString:self->_currentLanguage]) {
            self->_currentLanguageDescription = obj[kTRLocalizeLanguageDescriptionKey];
            NSUserDefaults *userDefaluts = [NSUserDefaults standardUserDefaults];
            [userDefaluts setObject:self->_currentLanguage forKey:kUserDefaultLanguageKey];
            [userDefaluts synchronize];
            *stop = YES;
        }
    }];
}

- (NSString *)convertLangDataFromKey:(NSString *)fromKey toKey:(NSString *)toKey WithData:(NSString *)data {
    if (!fromKey || !toKey || !data) {
        return @"";
    }
    
    for (NSDictionary *lang in languageList) {
        if ([lang[fromKey] isEqualToString:data]) {
            return lang[toKey] ?: @"";
        }
    }
    return @"";
}

- (NSBundle *)getLocalizedBundle {
    
    NSBundle* languageBundle = [NSBundle mainBundle];
    if ([_currentLanguage isEqualToString:@"ar"]) {
        NSString *path= [[NSBundle mainBundle] pathForResource:_currentLanguage ofType:@"lproj"];
        languageBundle = [NSBundle bundleWithPath:path];
    }
    return languageBundle;
}

#pragma mark - private method -

- (void)setupLanguageList {
    if (!languageList) {
        dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
            languageList = [NSArray arrayWithContentsOfFile:[[NSBundle mainBundle] pathForResource:@"LanguageListConfig" ofType:@"plist"]];
        });
    }
}

@end
