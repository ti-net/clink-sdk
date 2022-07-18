//
//  NSDate+TimeFormatting.h
//  SmartHome
//
//  Created by 赵言 on 2019/7/15.
//  Copyright © 2019 赵言. All rights reserved.
//  时间格式化

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSDate (TimeFormatting)

/**
 NSDate转字符串

 @param format 时间格式，如：yyyy-MM-dd HH:mm:ss
 @return 时间字符串
 */
- (NSString *)stringFromDateWithFormat:(NSString *)format;

/// 时间转时间戳
- (NSInteger)timeSwitchTimestamp;

@end

NS_ASSUME_NONNULL_END
