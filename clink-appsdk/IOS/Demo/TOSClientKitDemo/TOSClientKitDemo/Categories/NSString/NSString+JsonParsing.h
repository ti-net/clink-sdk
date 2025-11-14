//
//  NSString+JsonParsing.h
//  mobileCMS
//
//  Created by 赵言 on 2020/1/11.
//  Copyright © 2020 赵言. All rights reserved.
//
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSString (JsonParsing)

/// JSON字符串解析
- (id)jsonParsing;

@end

NS_ASSUME_NONNULL_END
