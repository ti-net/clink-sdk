//
//  NSString+Extension.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/9/27.
//  Copyright © 2016年 gxz. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSString (Extension)

- (NSString *)emoji;

- (NSMutableAttributedString *)transferMessageFont:(UIFont *)font
                                         foreColor:(UIColor *)fColor
                                           fontDic:(NSMutableArray <NSDictionary *>*)fontDic
                                        lineHeight:(CGFloat)lineHeight
                                  filteredLineFeed:(BOOL)filteredLineFeed;

- (CGSize)tim_sizeWithMaxWidth:(CGFloat)width
                  attributeStr:(NSMutableAttributedString *)attributeStr;

- (CGSize)tim_sizeWithMaxWidth:(CGFloat)width
                       andFont:(UIFont *)font;

- (CGSize)defaultLabelSizeWithMaxWidth:(CGFloat)width andFont:(UIFont *)font;

- (CGSize)defaultLabelSizeWithMaxHeight:(CGFloat)height andFont:(UIFont *)font;

- (NSString *)originName;

+ (NSString *)currentName;

- (NSString *)firstStringSeparatedByString:(NSString *)separeted;

/// 计算富文本所占的大小
- (CGSize)tim_sizeForWithMaxWidth:(CGFloat)width lineSpacing:(CGFloat)lineSpacing withFont:(UIFont *)font;

@end
