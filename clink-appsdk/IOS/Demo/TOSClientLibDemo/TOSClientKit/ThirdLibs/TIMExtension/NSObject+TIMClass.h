//
//  NSObject+TIMClass.h
//  TIMExtensionExample
//
//  Created by MJ Lee on 15/8/11.
//  Copyright (c) 2015年 小码哥. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 *  遍历所有类的block（父类）
 */
typedef void (^TIMClassesEnumeration)(Class c, BOOL *stop);

/** 这个数组中的属性名才会进行字典和模型的转换 */
typedef NSArray * (^TIMMJAllowedPropertyNames)();
/** 这个数组中的属性名才会进行归档 */
typedef NSArray * (^TIMMJAllowedCodingPropertyNames)();

/** 这个数组中的属性名将会被忽略：不进行字典和模型的转换 */
typedef NSArray * (^TIMMJIgnoredPropertyNames)();
/** 这个数组中的属性名将会被忽略：不进行归档 */
typedef NSArray * (^TIMMJIgnoredCodingPropertyNames)();

/**
 * 类相关的扩展
 */
@interface NSObject (TIMClass)
/**
 *  遍历所有的类
 */
+ (void)mjtim_enumerateClasses:(TIMClassesEnumeration)enumeration;
+ (void)mjtim_enumerateAllClasses:(TIMClassesEnumeration)enumeration;

#pragma mark - 属性白名单配置
/**
 *  这个数组中的属性名才会进行字典和模型的转换
 *
 *  @param allowedPropertyNames          这个数组中的属性名才会进行字典和模型的转换
 */
+ (void)mjtim_setupAllowedPropertyNames:(TIMMJAllowedPropertyNames)allowedPropertyNames;

/**
 *  这个数组中的属性名才会进行字典和模型的转换
 */
+ (NSMutableArray *)mjtim_totalAllowedPropertyNames;

#pragma mark - 属性黑名单配置
/**
 *  这个数组中的属性名将会被忽略：不进行字典和模型的转换
 *
 *  @param ignoredPropertyNames          这个数组中的属性名将会被忽略：不进行字典和模型的转换
 */
+ (void)mjtim_setupIgnoredPropertyNames:(TIMMJIgnoredPropertyNames)ignoredPropertyNames;

/**
 *  这个数组中的属性名将会被忽略：不进行字典和模型的转换
 */
+ (NSMutableArray *)mjtim_totalIgnoredPropertyNames;

#pragma mark - 归档属性白名单配置
/**
 *  这个数组中的属性名才会进行归档
 *
 *  @param allowedCodingPropertyNames          这个数组中的属性名才会进行归档
 */
+ (void)mjtim_setupAllowedCodingPropertyNames:(TIMMJAllowedCodingPropertyNames)allowedCodingPropertyNames;

/**
 *  这个数组中的属性名才会进行字典和模型的转换
 */
+ (NSMutableArray *)mjtim_totalAllowedCodingPropertyNames;

#pragma mark - 归档属性黑名单配置
/**
 *  这个数组中的属性名将会被忽略：不进行归档
 *
 *  @param ignoredCodingPropertyNames          这个数组中的属性名将会被忽略：不进行归档
 */
+ (void)mjtim_setupIgnoredCodingPropertyNames:(TIMMJIgnoredCodingPropertyNames)ignoredCodingPropertyNames;

/**
 *  这个数组中的属性名将会被忽略：不进行归档
 */
+ (NSMutableArray *)mjtim_totalIgnoredCodingPropertyNames;

#pragma mark - 内部使用
+ (void)mjtim_setupBlockReturnValue:(id (^)())block key:(const char *)key;
@end
