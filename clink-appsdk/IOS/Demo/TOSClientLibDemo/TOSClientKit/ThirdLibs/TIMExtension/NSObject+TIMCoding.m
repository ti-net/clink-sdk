//
//  NSObject+TIMCoding.m
//  TIMExtension
//
//  Created by mj on 14-1-15.
//  Copyright (c) 2014年 小码哥. All rights reserved.
//

#import "NSObject+TIMCoding.h"
#import "NSObject+TIMClass.h"
#import "NSObject+TIMProperty.h"
#import "TIMProperty.h"

@implementation NSObject (TIMCoding)

- (void)mjtim_encode:(NSCoder *)encoder
{
    Class clazz = [self class];
    
    NSArray *allowedCodingPropertyNames = [clazz mjtim_totalAllowedCodingPropertyNames];
    NSArray *ignoredCodingPropertyNames = [clazz mjtim_totalIgnoredCodingPropertyNames];
    
    [clazz mjtim_enumerateProperties:^(TIMProperty *property, BOOL *stop) {
        // 检测是否被忽略
        if (allowedCodingPropertyNames.count && ![allowedCodingPropertyNames containsObject:property.name]) return;
        if ([ignoredCodingPropertyNames containsObject:property.name]) return;
        
        id value = [property valueForObject:self];
        if (value == nil) return;
        [encoder encodeObject:value forKey:property.name];
    }];
}

- (void)mjtim_decode:(NSCoder *)decoder
{
    Class clazz = [self class];
    
    NSArray *allowedCodingPropertyNames = [clazz mjtim_totalAllowedCodingPropertyNames];
    NSArray *ignoredCodingPropertyNames = [clazz mjtim_totalIgnoredCodingPropertyNames];
    
    [clazz mjtim_enumerateProperties:^(TIMProperty *property, BOOL *stop) {
        // 检测是否被忽略
        if (allowedCodingPropertyNames.count && ![allowedCodingPropertyNames containsObject:property.name]) return;
        if ([ignoredCodingPropertyNames containsObject:property.name]) return;
        
        id value = [decoder decodeObjectForKey:property.name];
        if (value == nil) { // 兼容以前的TIMExtension版本
            value = [decoder decodeObjectForKey:[@"_" stringByAppendingString:property.name]];
        }
        if (value == nil) return;
        [property setValue:value forObject:self];
    }];
}
@end
