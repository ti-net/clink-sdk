//
//  NSObject+TIMProperty.h
//  TIMExtensionExample
//
//  Created by MJ Lee on 15/4/17.
//  Copyright (c) 2015年 小码哥. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMExtensionConst.h"

@class TIMProperty;

/**
 *  遍历成员变量用的block
 *
 *  @param property 成员的包装对象
 *  @param stop   YES代表停止遍历，NO代表继续遍历
 */
typedef void (^TIMMJPropertiesEnumeration)(TIMProperty *property, BOOL *stop);

/** 将属性名换为其他key去字典中取值 */
typedef NSDictionary * (^TIMMJReplacedKeyFromPropertyName)();
typedef id (^TIMMJReplacedKeyFromPropertyName121)(NSString *propertyName);
/** 数组中需要转换的模型类 */
typedef NSDictionary * (^TIMMJObjectClassInArray)();
/** 用于过滤字典中的值 */
typedef id (^TIMMJNewValueFromOldValue)(id object, id oldValue, TIMProperty *property);

/**
 * 成员属性相关的扩展
 */
@interface NSObject (TIMProperty)
#pragma mark - 遍历
/**
 *  遍历所有的成员
 */
+ (void)mjtim_enumerateProperties:(TIMMJPropertiesEnumeration)enumeration;

#pragma mark - 新值配置
/**
 *  用于过滤字典中的值
 *
 *  @param newValueFormOldValue 用于过滤字典中的值
 */
+ (void)mjtim_setupNewValueFromOldValue:(TIMMJNewValueFromOldValue)newValueFormOldValue;
+ (id)mjtim_getNewValueFromObject:(__unsafe_unretained id)object oldValue:(__unsafe_unretained id)oldValue property:(__unsafe_unretained TIMProperty *)property;

#pragma mark - key配置
/**
 *  将属性名换为其他key去字典中取值
 *
 *  @param replacedKeyFromPropertyName 将属性名换为其他key去字典中取值
 */
+ (void)mjtim_setupReplacedKeyFromPropertyName:(TIMMJReplacedKeyFromPropertyName)replacedKeyFromPropertyName;
/**
 *  将属性名换为其他key去字典中取值
 *
 *  @param replacedKeyFromPropertyName121 将属性名换为其他key去字典中取值
 */
+ (void)mjtim_setupReplacedKeyFromPropertyName121:(TIMMJReplacedKeyFromPropertyName121)replacedKeyFromPropertyName121;

#pragma mark - array model class配置
/**
 *  数组中需要转换的模型类
 *
 *  @param objectClassInArray          数组中需要转换的模型类
 */
+ (void)mjtim_setupObjectClassInArray:(TIMMJObjectClassInArray)objectClassInArray;
@end

@interface NSObject (TIMPropertyDeprecated_v_2_5_16)
+ (void)enumerateProperties:(TIMMJPropertiesEnumeration)enumeration TIMExtensionDeprecated("请在方法名前面加上mj_前缀，使用mj_***");
+ (void)setupNewValueFromOldValue:(TIMMJNewValueFromOldValue)newValueFormOldValue TIMExtensionDeprecated("请在方法名前面加上mj_前缀，使用mj_***");
+ (id)getNewValueFromObject:(__unsafe_unretained id)object oldValue:(__unsafe_unretained id)oldValue property:(__unsafe_unretained TIMProperty *)property TIMExtensionDeprecated("请在方法名前面加上mj_前缀，使用mj_***");
+ (void)setupReplacedKeyFromPropertyName:(TIMMJReplacedKeyFromPropertyName)replacedKeyFromPropertyName TIMExtensionDeprecated("请在方法名前面加上mj_前缀，使用mj_***");
+ (void)setupReplacedKeyFromPropertyName121:(TIMMJReplacedKeyFromPropertyName121)replacedKeyFromPropertyName121 TIMExtensionDeprecated("请在方法名前面加上mj_前缀，使用mj_***");
+ (void)setupObjectClassInArray:(TIMMJObjectClassInArray)objectClassInArray TIMExtensionDeprecated("请在方法名前面加上mj_前缀，使用mj_***");
@end
