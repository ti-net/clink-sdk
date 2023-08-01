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

- (CGSize)sizeWithMaxWidth:(CGFloat)width andFont:(UIFont *)font;

- (CGSize)sizeWithMaxWidth:(CGFloat)width
                   andFont:(UIFont *)font
                   fontDic:(NSMutableArray <NSDictionary *>*)fontDic;

- (CGSize)defaultLabelSizeWithMaxWidth:(CGFloat)width andFont:(UIFont *)font;

- (NSString *)originName;

+ (NSString *)currentName;

- (NSString *)firstStringSeparatedByString:(NSString *)separeted;



@end
