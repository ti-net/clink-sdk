//
//  ICChatBoxViewControllerDelegate.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/22.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <Foundation/Foundation.h>
#import <TOSClientLib/TOSClientLib.h>
#import "ICChatBoxMoreViewItem.h"

@class ICMessage;
@class TIMChatBoxViewController;
@class ICVideoView;
@class ICChatBarItemModel;

/// 快捷入口的代理方法
@protocol ICChatBoxBarViewDelegate <NSObject>

/// item的点击事件
- (void)ICChatBoxBarWithItemDidselectedIndex:(NSInteger)index;

@end

/// 快捷入口的数据源
@protocol ICChatBoxBarViewDataSource <NSObject>

/// 数据源
- (NSArray <ICChatBarItemModel *> *)ICChatBoxBarDataSource;


@end

@protocol TIMChatBoxViewControllerDelegate <NSObject>

/// 从语音切换到表情时(文本框有多行文字)需要改变高度
- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
        didChangeChatBoxFaceTextViewHeight:(CGFloat)height withFaceHeight:(CGFloat)faceHeight;

/// 快捷入口点击事件
- (void)chatBoxDidClickBarItemViewController:(TIMChatBoxViewController *)chatboxViewController withItemIndex:(NSInteger)index;

// change chatBox KBToVoice height
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
        didChangeChatBoxKBToVoiceHeight:(CGFloat)height;

// change chatBox textView height
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
        didChangeChatBoxTextViewHeight:(CGFloat)height;

// change chatBox height
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
        didChangeChatBoxHeight:(CGFloat)height;
/**
 *  send text message
 *
 *  @param chatboxViewController chatboxViewController
 *  @param messageStr            text
 */
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
               sendTextMessage:(NSString *)messageStr;
/**
 *  send custom message
 *
 *  @param chatboxViewController chatboxViewController
 *  @param custMessage           custommessage
 */
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
               sendCustomMessage:(TIMCustomizeMessage *)custMessage;
/**
 *  send image message
 *
 *  @param chatboxViewController ICChatBoxViewController
 *  @param image                 image
 *  @param imgPath               image path
 */
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
              sendImageMessage:(UIImage *)image
                     imagePath:(NSString *)imgPath;

- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
sendAuditImageMessage:(UIImage *)image
       imagePath:(NSString *)imgPath;

/**
 *  send image message
 *
 *  @param chatboxViewController ICChatBoxViewController
 *  @param image                 image
 *  @param imgPath               image path
 */
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
              sendGifImageMessage:(UIImage *)image
                     imagePath:(NSString *)imgPath;

/// 点击文件发送
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
                    senderFile:(NSInteger)senderFile;

//转人工
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
              zhuanRenGong:(NSInteger)defaultItem;

//结束会话
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
              closeChat:(NSInteger)defaultItem;

//自定义按钮
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
                          item:(ICChatBoxMoreViewItem *)item;

/**
 *  send voice message
 *
 *  @param chatboxViewController ICChatBoxViewController
 *  @param voicePath             voice path
 */
- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController sendVoiceMessage:(NSString *)voicePath;

- (void) voiceDidStartRecording;
// voice太短
- (void) voiceRecordSoShort;

- (void) voiceWillDragout:(BOOL)inside;

- (void) voiceDidCancelRecording;


- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController
            didVideoViewAppeared:(ICVideoView *)videoView;


- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController sendVideoMessage:(NSString *)videoPath duration:(CGFloat)videoTimeLength  thumbnailImagePath:(NSString *)thumbnailImagePath;

- (void) chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController sendFileMessage:(NSString *)fileName;

/// 文本输入框插入@符号
/// @param chatboxViewController chatboxViewController
/// @param symbol 要插入的文本
/// @param range 插入的位置
- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController inputATSymbol:(NSString *)symbol inputRange:(NSRange)range;

/// 文本输入框删除带有@符号的字段
/// @param chatboxViewController chatboxViewController
/// @param symbol 要删除的文本
/// @param range 删除的位置
- (void)chatBoxViewController:(TIMChatBoxViewController *)chatboxViewController deleteATSymbol:(NSString *)symbol inputRange:(NSRange)range;

@end
