//
//  ICFaceManager.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/4/1.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
@class ICEmotion;

@interface ICFaceManager : NSObject

+ (NSArray *)emojiEmotion;

+ (NSArray *)customEmotion;

+ (NSArray *)gifEmotion;

+ (NSMutableAttributedString *)transferMessageString:(NSString *)message
                                                font:(UIFont *)font
                                           foreColor:(UIColor *)fColor
                                          lineHeight:(CGFloat)lineHeight;


@end
