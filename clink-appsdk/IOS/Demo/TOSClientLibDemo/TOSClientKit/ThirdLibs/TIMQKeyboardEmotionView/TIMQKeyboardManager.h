//
//  TIMQKeyboardBaseManager.h
//  QKeyboardQKeyboard
//
//  Created by 侯力 on 2024/03/20.
//  Copyright (c) 2021年 侯力. All rights reserved.
//

#import "TIMQKeyboardBaseManager.h"

@class TIMQKeyboardManager;

@protocol TIMInputBoardDataSource <NSObject>
@optional

//@return 点加号按钮弹出的拓展面板View，且无需设置frame
- (UIView *)keyboardManagerExtendBoardView:(TIMQKeyboardManager *)keyboardManager;

//@return 点表情按钮弹出的表情面板View，且无需设置frame
- (UIView *)keyboardManagerEmotionBoardView:(TIMQKeyboardManager *)keyboardManager;

//@return 点加号按钮弹出的拓展面板View的高度
- (CGFloat)keyboardManagerExtendBoardHeight:(TIMQKeyboardManager *)keyboardManager;

//@return 点表情按钮弹出的表情面板View的高度
- (CGFloat)keyboardManagerEmotionBoardHeight:(TIMQKeyboardManager *)keyboardManager;

@end

//整个”输入View“的高度发生变化的原因（整个输入View包含bar和表情栏或者键盘）
typedef NS_ENUM(NSUInteger, TIMWholeInputViewHeightDidChangeReason) {
    TIMWholeInputViewHeightDidChangeReasonWillAddToSuperView = 0, //因为输入条被add到vc中
    TIMWholeInputViewHeightDidChangeReasonTextDidChange,//因为文本框输入的内容高度发生变化
    TIMWholeInputViewHeightDidChangeReasonTextDidSend,//因为点“发送”按钮所以清空了文本框 2022-03-24与上面那条区分开
    TIMWholeInputViewHeightDidChangeReasonBoardViewDidShow,//显示了面板（表情面板或者拓展面板）
    TIMWholeInputViewHeightDidChangeReasonBoardViewDidHide,//隐藏了面板（表情面板或者拓展面板）
    TIMWholeInputViewHeightDidChangeReasonKeyboardWillShow,//弹出键盘
    TIMWholeInputViewHeightDidChangeReasonKeyboardWillHide,//隐藏键盘
};

@protocol TIMInputBoardDelegate <NSObject>

@optional

//整个输入View的高度发生变化（整个View包含bar和表情栏或者键盘，但是不包含底部安全区高度）
//会触发这个的原因：1、addBottomInputBarView；2、输入文字换行了；3、切换面板；4、呼出键盘
//Warning：这个回调方法的触发已经在animate中了，无需再在本方法里写animate
- (void)keyboardManager:(TIMQKeyboardManager *)keyboardManager onWholeInputViewHeightDidChange:(CGFloat)wholeInputViewHeight reason:(TIMWholeInputViewHeightDidChangeReason)reason;

@end

@interface TIMQKeyboardManager : TIMQKeyboardBaseManager

@property (nonatomic, weak) id<TIMInputBoardDataSource> dataSource;

@property (nonatomic, weak) id<TIMInputBoardDelegate> delegate;

/**
 *  public - 添加底部输入框View
 *  @param belowViewController ：YES表示输入框平时不显示（比如朋友圈）；NO表示平时也显示（比如聊天）
 */
- (void)addBottomInputBarView:(UIView *)inputBarView belowViewController:(BOOL)belowViewController;

// public - 为了方便动画切换，本Manager类需要拿到textview或textField的引用，如果有输入条，请传过来；没有输入条可以不调用该方法
- (void)bindTextView:(UIResponder *)inputTextView;

// public - 底部的输入框高度发生变化，becauseSendText: 是因为点击了“发送”按钮清空了文本进而导致了输入栏高度变化
- (void)inputTextViewHeightDidChange:(BOOL)becauseSendText;

// public - 隐藏所有面板，包括表情面板和拓展面板
- (void)hideAllBoardView;

// public - 表情面板和键盘之间的切换
- (void)switchToEmotionBoardKeyboard;

// public - 拓展面板和键盘之间的切换
- (void)switchToExtendBoardKeyboard;


@end
