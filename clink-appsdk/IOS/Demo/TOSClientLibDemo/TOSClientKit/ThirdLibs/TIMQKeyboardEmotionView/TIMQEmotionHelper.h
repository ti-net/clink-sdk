//
//  TIMQEmotionHelper.h
//  pinpin
//
//  Created by 侯力 on 2024/3/19.
//  Copyright (c) 2021年 侯力. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMQEmotionBoardView.h"
#import <UIKit/UIKit.h>

//表情转换类
@interface TIMQEmotionHelper : NSObject
{

}

+ (TIMQEmotionHelper *)sharedEmotionHelper;

//显示表情键盘面板的时候，用这个。测试结果是占用0.5MB的内存（永驻）
@property (strong, nonatomic) NSArray<TIMQEmotion *> *emotionArray;

//imageKey：[微笑] font：label的Font，返回😊
//把 @"[微笑]" 转为 @"😊"
- (NSAttributedString *)obtainAttributedStringByImageKey:(NSString *)imageKey font:(UIFont *)font useCache:(BOOL)useCache;

//把 @"害~你好啊[微笑]" 转为 @"害~你好啊😊"
- (NSMutableAttributedString *)attributedStringByText:(NSString *)text font:(UIFont *)font;

@end
