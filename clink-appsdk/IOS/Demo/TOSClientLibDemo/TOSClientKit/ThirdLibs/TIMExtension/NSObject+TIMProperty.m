//
//  NSObject+TIMProperty.m
//  TIMExtensionExample
//
//  Created by MJ Lee on 15/4/17.
//  Copyright (c) 2015年 小码哥. All rights reserved.
//

#import "NSObject+TIMProperty.h"
#import "NSObject+TIMKeyValue.h"
#import "NSObject+TIMCoding.h"
#import "NSObject+TIMClass.h"
#import "TIMProperty.h"
#import "TIMFoundation.h"
#import <objc/runtime.h>

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wundeclared-selector"
#pragma clang diagnostic ignored "-Warc-performSelector-leaks"

static const char TIMMJReplacedKeyFromPropertyNameKey = '\0';
static const char TIMMJReplacedKeyFromPropertyName121Key = '\0';
static const char TIMMJNewValueFromOldValueKey = '\0';
static const char TIMMJObjectClassInArrayKey = '\0';

static const char MJCachedPropertiesKey = '\0';

@implementation NSObject (Property)

static NSMutableDictionary *replacedKeyFromPropertyNameDict_;
static NSMutableDictionary *replacedKeyFromPropertyName121Dict_;
static NSMutableDictionary *newValueFromOldValueDict_;
static NSMutableDictionary *objectClassInArrayDict_;
static NSMutableDictionary *cachedPropertiesDict_;

+ (void)load
{
    replacedKeyFromPropertyNameDict_ = [NSMutableDictionary dictionary];
    replacedKeyFromPropertyName121Dict_ = [NSMutableDictionary dictionary];
    newValueFromOldValueDict_ = [NSMutableDictionary dictionary];
    objectClassInArrayDict_ = [NSMutableDictionary dictionary];
    cachedPropertiesDict_ = [NSMutableDictionary dictionary];
}

+ (NSMutableDictionary *)dictForKey:(const void *)key
{
    @synchronized (self) {
        if (key == &TIMMJReplacedKeyFromPropertyNameKey) return replacedKeyFromPropertyNameDict_;
        if (key == &TIMMJReplacedKeyFromPropertyName121Key) return replacedKeyFromPropertyName121Dict_;
        if (key == &TIMMJNewValueFromOldValueKey) return newValueFromOldValueDict_;
        if (key == &TIMMJObjectClassInArrayKey) return objectClassInArrayDict_;
        if (key == &MJCachedPropertiesKey) return cachedPropertiesDict_;
        return nil;
    }
}

#pragma mark - --私有方法--
+ (id)propertyKey:(NSString *)propertyName
{
    TIMExtensionAssertParamNotNil2(propertyName, nil);
    
    __block id key = nil;
    // 查看有没有需要替换的key
    if ([self respondsToSelector:@selector(mjtim_replacedKeyFromPropertyName121:)]) {
        key = [self mjtim_replacedKeyFromPropertyName121:propertyName];
    }
    // 兼容旧版本
    if ([self respondsToSelector:@selector(replacedKeyFromPropertyName121:)]) {
        key = [self performSelector:@selector(replacedKeyFromPropertyName121) withObject:propertyName];
    }
    
    // 调用block
    if (!key) {
        [self mjtim_enumerateAllClasses:^(__unsafe_unretained Class c, BOOL *stop) {
            TIMMJReplacedKeyFromPropertyName121 block = objc_getAssociatedObject(c, &TIMMJReplacedKeyFromPropertyName121Key);
            if (block) {
                key = block(propertyName);
            }
            if (key) *stop = YES;
        }];
    }
    
    // 查看有没有需要替换的key
    if ((!key || [key isEqual:propertyName]) && [self respondsToSelector:@selector(mjtim_replacedKeyFromPropertyName)]) {
        key = [self mjtim_replacedKeyFromPropertyName][propertyName];
    }
    // 兼容旧版本
    if ((!key || [key isEqual:propertyName]) && [self respondsToSelector:@selector(replacedKeyFromPropertyName)]) {
        key = [self performSelector:@selector(replacedKeyFromPropertyName)][propertyName];
    }
    
    if (!key || [key isEqual:propertyName]) {
        [self mjtim_enumerateAllClasses:^(__unsafe_unretained Class c, BOOL *stop) {
            NSDictionary *dict = objc_getAssociatedObject(c, &TIMMJReplacedKeyFromPropertyNameKey);
            if (dict) {
                key = dict[propertyName];
            }
            if (key && ![key isEqual:propertyName]) *stop = YES;
        }];
    }
    
    // 2.用属性名作为key
    if (!key) key = propertyName;
    
    return key;
}

+ (Class)propertyObjectClassInArray:(NSString *)propertyName
{
    __block id clazz = nil;
    if ([self respondsToSelector:@selector(mjtim_objectClassInArray)]) {
        clazz = [self mjtim_objectClassInArray][propertyName];
    }
    // 兼容旧版本
    if ([self respondsToSelector:@selector(objectClassInArray)]) {
        clazz = [self performSelector:@selector(objectClassInArray)][propertyName];
    }
    
    if (!clazz) {
        [self mjtim_enumerateAllClasses:^(__unsafe_unretained Class c, BOOL *stop) {
            NSDictionary *dict = objc_getAssociatedObject(c, &TIMMJObjectClassInArrayKey);
            if (dict) {
                clazz = dict[propertyName];
            }
            if (clazz) *stop = YES;
        }];
    }
    
    // 如果是NSString类型
    if ([clazz isKindOfClass:[NSString class]]) {
        clazz = NSClassFromString(clazz);
    }
    return clazz;
}

#pragma mark - --公共方法--
+ (void)mjtim_enumerateProperties:(TIMMJPropertiesEnumeration)enumeration
{
    // 获得成员变量
    NSArray *cachedProperties = [self properties];
    
    // 遍历成员变量
    BOOL stop = NO;
    for (TIMProperty *property in cachedProperties) {
        enumeration(property, &stop);
        if (stop) break;
    }
}

#pragma mark - 公共方法
+ (NSMutableArray *)properties
{
    NSMutableArray *cachedProperties = [self dictForKey:&MJCachedPropertiesKey][NSStringFromClass(self)];
    
    if (cachedProperties == nil) {
        cachedProperties = [NSMutableArray array];
        
        [self mjtim_enumerateClasses:^(__unsafe_unretained Class c, BOOL *stop) {
            // 1.获得所有的成员变量
            unsigned int outCount = 0;
            objc_property_t *properties = class_copyPropertyList(c, &outCount);
            
            // 2.遍历每一个成员变量
            for (unsigned int i = 0; i<outCount; i++) {
                TIMProperty *property = [TIMProperty cachedPropertyWithProperty:properties[i]];
                // 过滤掉Foundation框架类里面的属性
                if ([TIMFoundation isClassFromFoundation:property.srcClass]) continue;
                property.srcClass = c;
                [property setOriginKey:[self propertyKey:property.name] forClass:self];
                [property setObjectClassInArray:[self propertyObjectClassInArray:property.name] forClass:self];
                [cachedProperties addObject:property];
            }
            
            // 3.释放内存
            free(properties);
        }];
        
        [self dictForKey:&MJCachedPropertiesKey][NSStringFromClass(self)] = cachedProperties;
    }
    
    return cachedProperties;
}

#pragma mark - 新值配置
+ (void)mjtim_setupNewValueFromOldValue:(TIMMJNewValueFromOldValue)newValueFormOldValue
{
    objc_setAssociatedObject(self, &TIMMJNewValueFromOldValueKey, newValueFormOldValue, OBJC_ASSOCIATION_COPY_NONATOMIC);
}

+ (id)mjtim_getNewValueFromObject:(__unsafe_unretained id)object oldValue:(__unsafe_unretained id)oldValue property:(TIMProperty *__unsafe_unretained)property{
    // 如果有实现方法
    if ([object respondsToSelector:@selector(mjtim_newValueFromOldValue:property:)]) {
        return [object mjtim_newValueFromOldValue:oldValue property:property];
    }
    // 兼容旧版本
    if ([self respondsToSelector:@selector(newValueFromOldValue:property:)]) {
        return [self performSelector:@selector(newValueFromOldValue:property:)  withObject:oldValue  withObject:property];
    }
    
    // 查看静态设置
    __block id newValue = oldValue;
    [self mjtim_enumerateAllClasses:^(__unsafe_unretained Class c, BOOL *stop) {
        TIMMJNewValueFromOldValue block = objc_getAssociatedObject(c, &TIMMJNewValueFromOldValueKey);
        if (block) {
            newValue = block(object, oldValue, property);
            *stop = YES;
        }
    }];
    return newValue;
}

#pragma mark - array model class配置
+ (void)mjtim_setupObjectClassInArray:(TIMMJObjectClassInArray)objectClassInArray
{
    [self mjtim_setupBlockReturnValue:objectClassInArray key:&TIMMJObjectClassInArrayKey];
    
    [[self dictForKey:&MJCachedPropertiesKey] removeAllObjects];
}

#pragma mark - key配置
+ (void)mjtim_setupReplacedKeyFromPropertyName:(TIMMJReplacedKeyFromPropertyName)replacedKeyFromPropertyName
{
    [self mjtim_setupBlockReturnValue:replacedKeyFromPropertyName key:&TIMMJReplacedKeyFromPropertyNameKey];
    
    [[self dictForKey:&MJCachedPropertiesKey] removeAllObjects];
}

+ (void)mjtim_setupReplacedKeyFromPropertyName121:(TIMMJReplacedKeyFromPropertyName121)replacedKeyFromPropertyName121
{
    objc_setAssociatedObject(self, &TIMMJReplacedKeyFromPropertyName121Key, replacedKeyFromPropertyName121, OBJC_ASSOCIATION_COPY_NONATOMIC);
    
    [[self dictForKey:&MJCachedPropertiesKey] removeAllObjects];
}
@end

@implementation NSObject (TIMPropertyDeprecated_v_2_5_16)
+ (void)enumerateProperties:(TIMMJPropertiesEnumeration)enumeration
{
    [self mjtim_enumerateProperties:enumeration];
}

+ (void)setupNewValueFromOldValue:(TIMMJNewValueFromOldValue)newValueFormOldValue
{
    [self mjtim_setupNewValueFromOldValue:newValueFormOldValue];
}

+ (id)getNewValueFromObject:(__unsafe_unretained id)object oldValue:(__unsafe_unretained id)oldValue property:(__unsafe_unretained TIMProperty *)property
{
    return [self mjtim_getNewValueFromObject:object oldValue:oldValue property:property];
}

+ (void)setupReplacedKeyFromPropertyName:(TIMMJReplacedKeyFromPropertyName)replacedKeyFromPropertyName
{
    [self mjtim_setupReplacedKeyFromPropertyName:replacedKeyFromPropertyName];
}

+ (void)setupReplacedKeyFromPropertyName121:(TIMMJReplacedKeyFromPropertyName121)replacedKeyFromPropertyName121
{
    [self mjtim_setupReplacedKeyFromPropertyName121:replacedKeyFromPropertyName121];
}

+ (void)setupObjectClassInArray:(TIMMJObjectClassInArray)objectClassInArray
{
    [self mjtim_setupObjectClassInArray:objectClassInArray];
}
@end

#pragma clang diagnostic pop
