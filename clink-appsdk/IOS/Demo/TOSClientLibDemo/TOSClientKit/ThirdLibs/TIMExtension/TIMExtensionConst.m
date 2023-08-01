#ifndef __TIMExtensionConst__M__
#define __TIMExtensionConst__M__

#import <Foundation/Foundation.h>

/**
 *  成员变量类型（属性类型）
 */
NSString *const TIMPropertyTypeInt = @"i";
NSString *const TIMPropertyTypeShort = @"s";
NSString *const TIMPropertyTypeFloat = @"f";
NSString *const TIMPropertyTypeDouble = @"d";
NSString *const TIMPropertyTypeLong = @"l";
NSString *const TIMPropertyTypeLongLong = @"q";
NSString *const TIMPropertyTypeChar = @"c";
NSString *const TIMPropertyTypeBOOL1 = @"c";
NSString *const TIMPropertyTypeBOOL2 = @"b";
NSString *const TIMPropertyTypePointer = @"*";

NSString *const TIMPropertyTypeIvar = @"^{objc_ivar=}";
NSString *const TIMPropertyTypeMethod = @"^{objc_method=}";
NSString *const TIMPropertyTypeBlock = @"@?";
NSString *const TIMPropertyTypeClass = @"#";
NSString *const TIMPropertyTypeSEL = @":";
NSString *const TIMPropertyTypeId = @"@";

#endif
