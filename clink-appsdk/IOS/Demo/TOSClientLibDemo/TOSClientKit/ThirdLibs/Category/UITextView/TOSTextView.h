//
//  TOSTextView.h
//  TOSClientKit
//
//  Created by 李成 on 2023/7/19.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@class XZEmotion;

@protocol SWEmotionTextViewDelegate <NSObject>

@optional

/**
 限制TextView的表情文字输出长度

 @param textView UITextView
 @param willChangedAttributedText 将要替换self.attributedText的NSAttributedString
 @return YES允许修改self.attributedText,否则不允许
 */
- (BOOL)sw_emotionTextView:(UITextView *)textView shouldChangeToAttributedText:(NSAttributedString *)willChangedAttributedText;

@end

@interface TOSTextView : UITextView

/**
 点击定位光标的手势
 */
@property (nonatomic,readonly,strong) UITapGestureRecognizer *sw_emoticonTapGesture;

@property (nonatomic,weak) id<SWEmotionTextViewDelegate> sw_emoticonDelegate;


/**
 向UITextView中插入一个表情

 @param emoticon 表情
 */
- (void)sw_insertEmoticon:(XZEmotion *)emoticon;

/**
 将所有表情字符串转换成普通字符串

 @return NSString
 */
- (NSString *)transalteAllEmoticonsToNormalString;

/// 将所有字符串转换为表情
/// @param string 现有字符
- (void)transalteStringEmoticonAttributedWithString:(NSString *)string;

- (void)sendSystemMessage;

@end

NS_ASSUME_NONNULL_END
