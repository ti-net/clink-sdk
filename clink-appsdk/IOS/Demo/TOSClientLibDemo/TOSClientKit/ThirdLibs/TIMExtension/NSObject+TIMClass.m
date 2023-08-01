//
//  NSObject+TIMClass.m
//  TIMExtensionExample
//
//  Created by MJ Lee on 15/8/11.
//  Copyright (c) 2015年 小码哥. All rights reserved.
//

#import "NSObject+TIMClass.h"
#import "NSObject+TIMCoding.h"
#import "NSObject+TIMKeyValue.h"
#import "TIMFoundation.h"
#import <objc/runtime.h>

static const char TIMMJAllowedPropertyNamesKey = '\0';
static const char TIMMJIgnoredPropertyNamesKey = '\0';
static const char TIMMJAllowedCodingPropertyNamesKey = '\0';
static const char TIMMJIgnoredCodingPropertyNamesKey = '\0';

static NSMutableDictionary *allowedPropertyNamesDict_;
static NSMutableDictionary *ignoredPropertyNamesDict_;
static NSMutableDictionary *allowedCodingPropertyNamesDict_;
static NSMutableDictionary *ignoredCodingPropertyNamesDict_;

@implementation NSObject (TIMClass)

+ (void)load
{
    allowedPropertyNamesDict_ = [NSMutableDictionary dictionary];
    ignoredPropertyNamesDict_ = [NSMutableDictionary dictionary];
    allowedCodingPropertyNamesDict_ = [NSMutableDictionary dictionary];
    ignoredCodingPropertyNamesDict_ = [NSMutableDictionary dictionary];
}

+ (NSMutableDictionary *)dictForKey:(const void *)key
{
    @synchronized (self) {
        if (key == &TIMMJAllowedPropertyNamesKey) return allowedPropertyNamesDict_;
        if (key == &TIMMJIgnoredPropertyNamesKey) return ignoredPropertyNamesDict_;
        if (key == &TIMMJAllowedCodingPropertyNamesKey) return allowedCodingPropertyNamesDict_;
        if (key == &TIMMJIgnoredCodingPropertyNamesKey) return ignoredCodingPropertyNamesDict_;
        return nil;
    }
}

+ (void)mjtim_enumerateClasses:(TIMClassesEnumeration)enumeration
{
    // 1.没有block就直接返回
    if (enumeration == nil) return;
    
    // 2.停止遍历的标记
    BOOL stop = NO;
    
    // 3.当前正在遍历的类
    Class c = self;
    
    // 4.开始遍历每一个类
    while (c && !stop) {
        // 4.1.执行操作
        enumeration(c, &stop);
        
        // 4.2.获得父类
        c = class_getSuperclass(c);
        
        if ([TIMFoundation isClassFromFoundation:c]) break;
    }
}

+ (void)mjtim_enumerateAllClasses:(TIMClassesEnumeration)enumeration
{
    // 1.没有block就直接返回
    if (enumeration == nil) return;
    
    // 2.停止遍历的标记
    BOOL stop = NO;
    
    // 3.当前正在遍历的类
    Class c = self;
    
    // 4.开始遍历每一个类
    while (c && !stop) {
        // 4.1.执行操作
        enumeration(c, &stop);
        
        // 4.2.获得父类
        c = class_getSuperclass(c);
    }
}

#pragma mark - 属性黑名单配置
+ (void)mjtim_setupIgnoredPropertyNames:(TIMMJIgnoredPropertyNames)ignoredPropertyNames
{
    [self mjtim_setupBlockReturnValue:ignoredPropertyNames key:&TIMMJIgnoredPropertyNamesKey];
}

+ (NSMutableArray *)mjtim_totalIgnoredPropertyNames
{
    return [self mj_totalObjectsWithSelector:@selector(mjtim_ignoredPropertyNames) key:&TIMMJIgnoredPropertyNamesKey];
}

#pragma mark - 归档属性黑名单配置
+ (void)mjtim_setupIgnoredCodingPropertyNames:(TIMMJIgnoredCodingPropertyNames)ignoredCodingPropertyNames
{
    [self mjtim_setupBlockReturnValue:ignoredCodingPropertyNames key:&TIMMJIgnoredCodingPropertyNamesKey];
}

+ (NSMutableArray *)mjtim_totalIgnoredCodingPropertyNames
{
    return [self mj_totalObjectsWithSelector:@selector(mjtim_ignoredCodingPropertyNames) key:&TIMMJIgnoredCodingPropertyNamesKey];
}

#pragma mark - 属性白名单配置
+ (void)mjtim_setupAllowedPropertyNames:(TIMMJAllowedPropertyNames)allowedPropertyNames;
{
    [self mjtim_setupBlockReturnValue:allowedPropertyNames key:&TIMMJAllowedPropertyNamesKey];
}

+ (NSMutableArray *)mjtim_totalAllowedPropertyNames
{
    return [self mj_totalObjectsWithSelector:@selector(mjtim_allowedPropertyNames) key:&TIMMJAllowedPropertyNamesKey];
}

#pragma mark - 归档属性白名单配置
+ (void)mjtim_setupAllowedCodingPropertyNames:(TIMMJAllowedCodingPropertyNames)allowedCodingPropertyNames
{
    [self mjtim_setupBlockReturnValue:allowedCodingPropertyNames key:&TIMMJAllowedCodingPropertyNamesKey];
}

+ (NSMutableArray *)mjtim_totalAllowedCodingPropertyNames
{
    return [self mj_totalObjectsWithSelector:@selector(mjtim_allowedCodingPropertyNames) key:&TIMMJAllowedCodingPropertyNamesKey];
}
#pragma mark - block和方法处理:存储block的返回值
+ (void)mjtim_setupBlockReturnValue:(id (^)())block key:(const char *)key
{
    if (block) {
        objc_setAssociatedObject(self, key, block(), OBJC_ASSOCIATION_RETAIN_NONATOMIC);
    } else {
        objc_setAssociatedObject(self, key, nil, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
    }
    
    // 清空数据
    [[self dictForKey:key] removeAllObjects];
}

+ (NSMutableArray *)mj_totalObjectsWithSelector:(SEL)selector key:(const char *)key
{
    NSMutableArray *array = [self dictForKey:key][NSStringFromClass(self)];
    if (array) return array;
    
    // 创建、存储
    [self dictForKey:key][NSStringFromClass(self)] = array = [NSMutableArray array];
    
    if ([self respondsToSelector:selector]) {
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Warc-performSelector-leaks"
        NSArray *subArray = [self performSelector:selector];
#pragma clang diagnostic pop
        if (subArray) {
            [array addObjectsFromArray:subArray];
        }
    }
    
    [self mjtim_enumerateAllClasses:^(__unsafe_unretained Class c, BOOL *stop) {
        NSArray *subArray = objc_getAssociatedObject(c, key);
        [array addObjectsFromArray:subArray];
    }];
    return array;
}
@end
