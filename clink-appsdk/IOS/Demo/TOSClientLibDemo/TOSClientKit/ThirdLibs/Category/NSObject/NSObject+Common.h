//
//  NSObject+Common.h
//  SmartHome
//
//  Created by 赵言 on 2019/7/10.
//  Copyright © 2019 赵言. All rights reserved.
//  工具分类

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSObject (Common)


/**
 验证字典类型包含key

 @param key 键
 @return YES包含，NO不包含
 */
- (BOOL)dictionaryContainsKey:(NSString *)key;

/**
 验证字符串

 @return 字符串
 */
- (NSString *)stringValue;

/**
 对象转JSON字符串

 @return JSON字符串
 */
- (NSString *)jsonStr;

/**
 对象转JSON数据

 @return JSONData
 */
- (NSData *)jsonData;

/// TextField限制字符数ƒ
/// @param textField textField
/// @param maxLength 字数限制
//- (void)tr_TextFieldDidChange:(UITextField *)textField maxLength:(NSInteger)maxLength;

@end

NS_ASSUME_NONNULL_END
