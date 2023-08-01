
#ifndef __TIMExtensionConst__H__
#define __TIMExtensionConst__H__

#import <Foundation/Foundation.h>

// 过期
#define TIMExtensionDeprecated(instead) NS_DEPRECATED(2_0, 2_0, 2_0, 2_0, instead)

// 构建错误
#define TIMExtensionBuildError(clazz, msg) \
NSError *error = [NSError errorWithDomain:msg code:250 userInfo:nil]; \
[clazz setMjTim_error:error];

// 日志输出
#ifdef DEBUG
#define TIMExtensionLog(...) NSLog(__VA_ARGS__)
#else
#define TIMExtensionLog(...)
#endif

/**
 * 断言
 * @param condition   条件
 * @param returnValue 返回值
 */
#define TIMExtensionAssertError(condition, returnValue, clazz, msg) \
[clazz setMjTim_error:nil]; \
if ((condition) == NO) { \
    TIMExtensionBuildError(clazz, msg); \
    return returnValue;\
}

#define TIMExtensionAssert2(condition, returnValue) \
if ((condition) == NO) return returnValue;

/**
 * 断言
 * @param condition   条件
 */
#define TIMExtensionAssert(condition) TIMExtensionAssert2(condition, )

/**
 * 断言
 * @param param         参数
 * @param returnValue   返回值
 */
#define TIMExtensionAssertParamNotNil2(param, returnValue) \
TIMExtensionAssert2((param) != nil, returnValue)

/**
 * 断言
 * @param param   参数
 */
#define TIMExtensionAssertParamNotNil(param) TIMExtensionAssertParamNotNil2(param, )

/**
 * 打印所有的属性
 */
#define MJLogAllIvars \
-(NSString *)description \
{ \
    return [self mjtim_keyValues].description; \
}
#define TIMExtensionLogAllProperties MJLogAllIvars

/**
 *  类型（属性类型）
 */
extern NSString *const TIMPropertyTypeInt;
extern NSString *const TIMPropertyTypeShort;
extern NSString *const TIMPropertyTypeFloat;
extern NSString *const TIMPropertyTypeDouble;
extern NSString *const TIMPropertyTypeLong;
extern NSString *const TIMPropertyTypeLongLong;
extern NSString *const TIMPropertyTypeChar;
extern NSString *const TIMPropertyTypeBOOL1;
extern NSString *const TIMPropertyTypeBOOL2;
extern NSString *const TIMPropertyTypePointer;

extern NSString *const TIMPropertyTypeIvar;
extern NSString *const TIMPropertyTypeMethod;
extern NSString *const TIMPropertyTypeBlock;
extern NSString *const TIMPropertyTypeClass;
extern NSString *const TIMPropertyTypeSEL;
extern NSString *const TIMPropertyTypeId;

#endif
