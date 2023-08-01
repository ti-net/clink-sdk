//
//  NSObject+TIMCoding.h
//  TIMExtension
//
//  Created by mj on 14-1-15.
//  Copyright (c) 2014年 小码哥. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMExtensionConst.h"

/**
 *  Codeing协议
 */
@protocol TIMCoding <NSObject>
@optional
/**
 *  这个数组中的属性名才会进行归档
 */
+ (NSArray *)mjtim_allowedCodingPropertyNames;
/**
 *  这个数组中的属性名将会被忽略：不进行归档
 */
+ (NSArray *)mjtim_ignoredCodingPropertyNames;
@end

@interface NSObject (TIMCoding) <TIMCoding>
/**
 *  解码（从文件中解析对象）
 */
- (void)mjtim_decode:(NSCoder *)decoder;
/**
 *  编码（将对象写入文件中）
 */
- (void)mjtim_encode:(NSCoder *)encoder;
@end

/**
 归档的实现
 */
#define TIMCodingImplementation \
- (id)initWithCoder:(NSCoder *)decoder \
{ \
if (self = [super init]) { \
[self mjtim_decode:decoder]; \
} \
return self; \
} \
\
- (void)encodeWithCoder:(NSCoder *)encoder \
{ \
[self mjtim_encode:encoder]; \
}

#define TIMExtensionCodingImplementation TIMCodingImplementation
