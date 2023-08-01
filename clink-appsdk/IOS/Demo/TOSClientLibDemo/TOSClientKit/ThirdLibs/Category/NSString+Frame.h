//
//  NSString+Frame.h
//  SmartHome
//
//  Created by 赵言 on 2019/7/16.
//  Copyright © 2019 赵言. All rights reserved.
//  字符串计算高度宽度

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSString (Frame)

/**
 计算字符串高度
 
 @param font 字体大小
 @param width 内容宽度
 @return 字符串高度
 */
- (CGFloat)contentHeightWithFont:(UIFont *)font width:(CGFloat)width;

/**
 计算字符串宽度
 
 @param font 字体大小
 @param height 内容高度
 @return 字符串宽度
 */
- (CGFloat)contentWidthWithFont:(UIFont *)font height:(CGFloat)height;

@end

NS_ASSUME_NONNULL_END
