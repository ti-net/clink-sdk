//
//  NSString+RegexValidation.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/10.
//  Copyright © 2019 赵言. All rights reserved.
//
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSString (RegexValidation)

/// 正则验证
/// @param regex 正则
- (BOOL)regexValidate:(NSString *)regex;

@end

NS_ASSUME_NONNULL_END
