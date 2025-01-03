//
//  NSArray+TRTool.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/25.
//  Copyright © 2019 赵言. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSArray (TRTool)

/// 读取Plist文件内的数据
/// @param fileName 文件名称
+ (NSArray *)readPlistFileWithFileName:(NSString *)fileName;

- (id)by_ObjectAtIndex:(NSUInteger)index;

@end

NS_ASSUME_NONNULL_END
