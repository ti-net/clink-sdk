//
//  ICChatBox.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/10.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TIMChatConst.h"
#import "ICChatServerDefs.h"
#import "TIMChatBoxViewControllerDelegate.h"
#import "ICChatBarFunctionView.h"
#import "TOSTextView.h"

@class ICChatBox;
@protocol ICChatBoxDelegate <NSObject>
/**
 *  输入框状态(位置)改变
 *
 *  @param chatBox    chatBox
 *  @param fromStatus 起始状态
 *  @param toStatus   目的状态
 */
- (void)chatBox:(ICChatBox *)chatBox changeStatusForm:(ICChatBoxStatus)fromStatus to:(ICChatBoxStatus)toStatus;

/**
 *  发送消息
 *
 *  @param chatBox     chatBox
 *  @param textMessage 消息
 */
- (void)chatBox:(ICChatBox *)chatBox sendTextMessage:(NSString *)textMessage;

/**
 *  发送Gif消息
 *
 *  @param chatBox     chatBox
 *  @param textMessage 消息
 */
- (void)chatBox:(ICChatBox *)chatBox sendGifImageMessage:(NSString *)gifName;

/**
 *  输入框高度改变
 *
 *  @param chatBox chatBox
 *  @param height  height
 */
- (void)chatBox:(ICChatBox *)chatBox changeChatBoxHeight:(CGFloat)height;

/**
 *  文本输入框高度改变 需要更新chatBox本身高度
 *
 *  @param chatBox chatBox
 *  @param height  height
 */
- (void)chatBox:(ICChatBox *)chatBox changeChatBoxTextViewHeight:(CGFloat)height;

/// 文本输入框插入@符号
/// @param chatBox chatBox
/// @param symbol 要插入的文本
/// @param range 插入的位置
- (void)chatBox:(ICChatBox *)chatBox inputATSymbol:(NSString *)symbol inputRange:(NSRange)range;

/// 文本输入框删除带有@符号的字段
/// @param chatBox chatBox
/// @param symbol 要删除的文本
/// @param range 删除的位置
- (void)chatBox:(ICChatBox *)chatBox deleteATSymbol:(NSString *)symbol inputRange:(NSRange)range;

/**
 *  开始录音
 *
 *  @param chatBox chatBox
 */
- (void)chatBoxDidStartRecordingVoice:(ICChatBox *)chatBox;
- (void)chatBoxDidStopRecordingVoice:(ICChatBox *)chatBox;
- (void)chatBoxDidCancelRecordingVoice:(ICChatBox *)chatBox;
- (void)chatBoxDidDrag:(BOOL)inside;


@end


@interface ICChatBox : UIView
/** 保存状态 */
@property (nonatomic, assign) ICChatBoxStatus status;

@property (nonatomic, weak) id<ICChatBoxDelegate>delegate;

/// 快捷入口的代理
@property (nonatomic, weak) id <ICChatBoxBarViewDelegate>                barDelegate;

/// 快捷入口的数据源
@property (nonatomic, weak) id <ICChatBoxBarViewDataSource>                barDataSource;

/** 输入框 */
@property (nonatomic, strong) TOSTextView *textView;

/// 是否隐藏语音按钮
@property (nonatomic, assign) BOOL                robotHiddenVoice;


-(void)resumeTextHeight:(BOOL)bResume;
- (void)switchTextEditing;

- (NSString *)transalteEmoticonAttributedString:(NSAttributedString *)emoticonAttributedString;

/// 找到文本中所有的@
- (NSArray *)getMatchs;

// 增加监听通知
- (void)addNotification;

// 取消监听通知
- (void)removeNotification;

/// 发送状态的检测
- (void)checkSendButtonType;

@end
