//
//  ICChatBox.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/10.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICChatBox.h"
#import "UIImage+Extension.h"
#import "ICMessageConst.h"
#import "XZEmotion.h"
#import "TIMConstants.h"
#import "UIView+SDExtension.h"
#import "NSString+Extension.h"
#import "TIMConstants.h"
#import "SWTextAttachment.h"
#import "kitUtils.h"
#import <TOSClientKit/TOSKit.h>
#import <TOSClientLib/TOSClientLib.h>
#import <YYKit.h>
#import "TIMMasonry.h"
#import <TOSClientKit/TOSKitCustomInfo.h>

@interface ICChatBox ()<UITextViewDelegate,SWEmotionTextViewDelegate, ICChatBarFunctionViewDelegate>

/** chotBox的顶部边线 */
@property (nonatomic, strong) UIView *topLine;
/** 常用语按钮 */
@property (nonatomic, strong) UIButton *wordsButton;
/** 录音按钮 */
@property (nonatomic, strong) UIButton *voiceButton;
/** 表情按钮 */
@property (nonatomic, strong) UIButton *faceButton;
/** (+)按钮 */
@property (nonatomic, strong) UIButton *moreButton;
/** 按住说话 */
@property (nonatomic, strong) UIButton *talkButton;

/// 发送按钮
@property (nonatomic, strong) UIButton                * sendButton;

// 时钟
@property (nonatomic, strong) NSTimer *recordVoiceTimer;
//当下输入框内容
@property (nonatomic, copy) NSString *nowInputText;

/// 快捷功能区域
@property (nonatomic, strong) ICChatBarFunctionView                * barFunctionView;

@property (nonatomic, strong) NSArray                * barDataCount;

// 退出或压后台标识
@property (nonatomic, assign) BOOL bExitOrBackground;

@property (nonatomic, strong) YYLabel *placeholderLable;

@end

//static NSInteger textLineNumber = 5;

@implementation ICChatBox


- (id) initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        [self setBackgroundColor:[TOSKitCustomInfo shareCustomInfo].ChatBox_backGroundColor];
        
        [self addSubview:self.barFunctionView];
        
        [self addSubview:self.topLine];
        // 先注释掉可选的常用语列表
        //[self addSubview:self.wordsButton];
        if ([TOSKitCustomInfo shareCustomInfo].ChatBox_voiceButton_enable) {
            // 当前有语音的权限
            [self addSubview:self.voiceButton];
        }
        [self addSubview:self.textView];
        if ([TOSKitCustomInfo shareCustomInfo].ChatBox_textview_placeholder.length > 0) {
            [self.textView addSubview:self.placeholderLable];//添加到UITextView上面
        }
        
        
        if ([TOSKitCustomInfo shareCustomInfo].chatBox_emotionButton_enable) {
            [self addSubview:self.faceButton];
        }
        if ([TOSKitCustomInfo shareCustomInfo].chatBox_moreButton_enable) {
            [self addSubview:self.moreButton];
        }
        
        /// 是否加载发送按钮
        BOOL sendShow = ![TOSKitCustomInfo shareCustomInfo].chatBox_emotionButton_enable && ![TOSKitCustomInfo shareCustomInfo].chatBox_moreButton_enable && [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_enable;
        
        if (sendShow) {
            [self addSubview:self.sendButton];
        }
        
        
        [self addSubview:self.talkButton];
        self.status = ICChatBoxStatusShowKeyboard; // 起始状态
        
        
        
        [self.topLine mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
            make.left.right.mas_TIMequalTo(0);
            make.top.mas_TIMequalTo(0);
            make.height.mas_TIMequalTo([TOSKitCustomInfo shareCustomInfo].chatBox_topLineHeight);
        }];
        
        [self.barFunctionView mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
            make.left.right.mas_TIMequalTo(0);
            make.top.equalTo(self.topLine.mas_TIMbottom);
            make.height.mas_TIMequalTo(0);
        }];
        /// 常用语
//        [self.wordsButton mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
//            // CGRectMake( 0, (HEIGHT_TABBAR - CHATBOX_BUTTON_WIDTH) / 2, CHATBOX_BUTTON_WIDTH, CHATBOX_BUTTON_WIDTH)
//            make.left.mas_TIMequalTo(0);
//            make.bottom.mas_TIMequalTo(-10);
//            make.width.height.mas_TIMequalTo(CHATBOX_BUTTON_WIDTH);
//        }];

        if ([TOSKitCustomInfo shareCustomInfo].ChatBox_voiceButton_enable) {
            // 当前有语音的权限
            [self.voiceButton mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
                make.left.mas_TIMequalTo([TOSKitCustomInfo shareCustomInfo].chatBox_itemLeftPadding);
                make.bottom.mas_TIMequalTo(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemBottomSpacing);
                make.width.height.mas_TIMequalTo([TOSKitCustomInfo shareCustomInfo].chatBox_Item_Width);
            }];
        }
        
        if ([TOSKitCustomInfo shareCustomInfo].chatBox_moreButton_enable) {
            [self.moreButton mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
                // CGRectMake(App_Frame_Width - CHATBOX_BUTTON_WIDTH, (HEIGHT_TABBAR - CHATBOX_BUTTON_WIDTH) / 2, CHATBOX_BUTTON_WIDTH, CHATBOX_BUTTON_WIDTH)
                make.right.mas_TIMequalTo(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemRightPadding);
                make.bottom.mas_TIMequalTo(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemBottomSpacing);
                make.width.height.mas_TIMequalTo([TOSKitCustomInfo shareCustomInfo].chatBox_Item_Width);
                
            }];
        }
        
        if ([TOSKitCustomInfo shareCustomInfo].chatBox_emotionButton_enable) {
            [self.faceButton mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
                // CGRectMake(self.moreButton.x - CHATBOX_BUTTON_WIDTH, (HEIGHT_TABBAR - CHATBOX_BUTTON_WIDTH) / 2, CHATBOX_BUTTON_WIDTH, CHATBOX_BUTTON_WIDTH)
                make.width.height.mas_TIMequalTo([TOSKitCustomInfo shareCustomInfo].chatBox_Item_Width);
//                make.right.equalTo(self.moreButton.mas_TIMleft).offset(-10);
                if ([TOSKitCustomInfo shareCustomInfo].chatBox_moreButton_enable) {
                    make.right.equalTo(self.moreButton.mas_TIMleft).offset(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
                }
                else {
                    make.right.equalTo(self.mas_TIMright).offset(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
                }
                make.bottom.mas_TIMequalTo(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemBottomSpacing);
            }];
        }
        
        if (sendShow) {
            [self.sendButton mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
                make.bottom.mas_TIMequalTo(-[TOSKitCustomInfo shareCustomInfo].chatBox_textView_topAndBottomMargin);
                make.width.mas_TIMequalTo([TOSKitCustomInfo shareCustomInfo].chatBox_sendButtonSize.width);
                make.height.mas_TIMequalTo([TOSKitCustomInfo shareCustomInfo].chatBox_sendButtonSize.height);
                make.right.equalTo(self.mas_TIMright).offset(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemRightPadding);
            }];
        }
        
        
        [self.talkButton mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
            if ([TOSKitCustomInfo shareCustomInfo].ChatBox_voiceButton_enable) {
                make.left.equalTo(self.voiceButton.mas_TIMright).offset([TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
            } else {
                make.left.mas_TIMequalTo([TOSKitCustomInfo shareCustomInfo].chatBox_itemLeftPadding);
            }
            make.bottom.mas_TIMequalTo(-[TOSKitCustomInfo shareCustomInfo].chatBox_textView_topAndBottomMargin);
//            make.right.equalTo(self.faceButton.mas_TIMleft).offset(-10);
            if ([TOSKitCustomInfo shareCustomInfo].chatBox_emotionButton_enable) {
                make.right.equalTo(self.faceButton.mas_TIMleft).offset(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
            }
            else if ([TOSKitCustomInfo shareCustomInfo].chatBox_moreButton_enable) {
                make.right.equalTo(self.moreButton.mas_TIMleft).offset(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
            }
            else if (sendShow) {
                make.right.equalTo(self.sendButton.mas_TIMleft).offset(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
            }
            else {
                make.right.equalTo(self.mas_TIMright).offset(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemRightPadding);
            }
            make.height.mas_TIMequalTo([TOSKitCustomInfo shareCustomInfo].chatBox_textView_height);
        }];
        
        [self.textView mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
            //CGRectMake(self.voiceButton.x + self.voiceButton.width + 4, (HEIGHT_TABBAR - CHATBOX_BUTTON_WIDTH) / 2, self.faceButton.x - self.voiceButton.x - self.voiceButton.width - 8, HEIGHT_TEXTVIEW)
            if ([TOSKitCustomInfo shareCustomInfo].ChatBox_voiceButton_enable) {
                make.left.equalTo(self.voiceButton.mas_TIMright).offset([TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
            } else {
                make.left.mas_TIMequalTo([TOSKitCustomInfo shareCustomInfo].chatBox_itemLeftPadding);
            }
            make.bottom.mas_TIMequalTo(-[TOSKitCustomInfo shareCustomInfo].chatBox_textView_topAndBottomMargin);
//            make.right.equalTo(self.faceButton.mas_TIMleft).offset(-10);
            if ([TOSKitCustomInfo shareCustomInfo].chatBox_emotionButton_enable) {
                make.right.equalTo(self.faceButton.mas_TIMleft).offset(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
            }
            else if ([TOSKitCustomInfo shareCustomInfo].chatBox_moreButton_enable) {
                make.right.equalTo(self.moreButton.mas_TIMleft).offset(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
            }
            else if (sendShow) {
                make.right.equalTo(self.sendButton.mas_TIMleft).offset(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
            }
            else {
                make.right.equalTo(self.mas_TIMright).offset(-[TOSKitCustomInfo shareCustomInfo].chatBox_itemRightPadding);
            }
            make.height.mas_TIMequalTo([TOSKitCustomInfo shareCustomInfo].chatBox_textView_height);
        }];
        
        [self configurationBarViewDataSrouce];
        
    }
    return self;
}

/// 顶部的快捷菜单数据源/代理配置
- (void)configurationBarViewDataSrouce {

    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.3f * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        if (self.barDataSource && [self.barDataSource respondsToSelector:@selector(ICChatBoxBarDataSource)]) {
            self.barDataCount = [self.barDataSource ICChatBoxBarDataSource];
            if (self.barDataCount.count > 0) {
                NSLog(@"数据源的item数量：%ld", self.barDataCount.count);
                [self.barFunctionView mas_TIMupdateTIMConstraints:^(TIMMASConstraintMaker *make) {
                    make.height.mas_TIMequalTo(CHATBOX_BAR_HEIGHT);
                }];
                NSMutableArray * testArr = [NSMutableArray array];
                for (NSInteger i = 0; i < self.barDataCount.count; i++) {
                    ICChatBarItemView * testView = [[ICChatBarItemView alloc] initWithFrame:(CGRectZero)];
                    testView.model = self.barDataCount[i];

                    [testArr addObject:testView];
                }
                [self.barFunctionView addBarItemView:testArr];
            }
        }

    });

}


#pragma mark - Getter and Setter


- (ICChatBarFunctionView *)barFunctionView {
    if (!_barFunctionView) {
        _barFunctionView = [[ICChatBarFunctionView alloc] initWithFrame:(CGRectZero)];
        _barFunctionView.delegate = self;
        
    }
    return _barFunctionView;
}


- (UIView *) topLine
{
    if (_topLine == nil) {
//        _topLine = [[UIView alloc] initWithFrame:CGRectMake(0, 0, self.width, 0.5)];
        _topLine = [[UIView alloc] initWithFrame:CGRectZero];
        [_topLine setBackgroundColor:[TOSKitCustomInfo shareCustomInfo].ChatBox_lineColor];
    }
    return _topLine;
}

- (UIButton *)wordsButton{
    if (_wordsButton == nil) {
//        _wordsButton = [[UIButton alloc] initWithFrame:CGRectMake( 0, (HEIGHT_TABBAR - CHATBOX_BUTTON_WIDTH) / 2, CHATBOX_BUTTON_WIDTH, CHATBOX_BUTTON_WIDTH)];
        _wordsButton = [[UIButton alloc] initWithFrame:CGRectZero];
        [_wordsButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"CommonWords"]] forState:UIControlStateNormal];
        [_wordsButton addTarget:self action:@selector(wordsButtonDown:) forControlEvents:UIControlEventTouchUpInside];
    }
    return _wordsButton;
}

- (UIButton *) voiceButton
{
    if (_voiceButton == nil) {
        _voiceButton = [[UIButton alloc] initWithFrame:CGRectZero];
        [_voiceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_voice"]] forState:UIControlStateNormal];
        [_voiceButton addTarget:self action:@selector(voiceButtonDown:) forControlEvents:UIControlEventTouchUpInside];
    }
    return _voiceButton;
}

- (UIButton *) moreButton
{
    if (_moreButton == nil) {
        _moreButton = [[UIButton alloc] initWithFrame:CGRectZero];
        [_moreButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_extendBoard"]] forState:UIControlStateNormal];
        [_moreButton addTarget:self action:@selector(moreButtonDown:) forControlEvents:UIControlEventTouchUpInside];
    }
    return _moreButton;
}

- (UIButton *) faceButton
{
    if (_faceButton == nil) {
        _faceButton = [[UIButton alloc] initWithFrame:CGRectZero];
        [_faceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_emotion"]] forState:UIControlStateNormal];
        [_faceButton addTarget:self action:@selector(faceButtonDown:) forControlEvents:UIControlEventTouchUpInside];
    }
    return _faceButton;
}

- (TOSTextView *) textView
{
    if (_textView == nil) {
        _textView = [[TOSTextView alloc] initWithFrame:(CGRectZero)];
        [_textView setFont:[TOSKitCustomInfo shareCustomInfo].chatBox_textView_font];
        _textView.textColor = [TOSKitCustomInfo shareCustomInfo].chatBox_textView_textColor;
        [_textView.layer setMasksToBounds:YES];
        [_textView.layer setCornerRadius:[TOSKitCustomInfo shareCustomInfo].chatBox_textView_cornerRadius];
        [_textView.layer setBorderWidth:[TOSKitCustomInfo shareCustomInfo].chatBox_textView_borderWidth];
        [_textView.layer setBorderColor:[TOSKitCustomInfo shareCustomInfo].chatBox_textView_borderColor.CGColor];
        _textView.textContainerInset = [TOSKitCustomInfo shareCustomInfo].chatBox_textView_textContainerInset;
        _textView.backgroundColor = [TOSKitCustomInfo shareCustomInfo].chatBox_textView_backgroundColor;
        [_textView setScrollsToTop:NO];
        [_textView setReturnKeyType:UIReturnKeySend];
        [_textView setDelegate:self];
        _textView.tintColor = [TOSKitCustomInfo shareCustomInfo].chatBox_textView_tintColor;
        _textView.sw_emoticonDelegate = self;
        if ([TOSKitCustomInfo shareCustomInfo].chatBox_textView_maxRows == 1) {
            _textView.showsVerticalScrollIndicator = NO;
        }
    }
    return _textView;
}

- (UIButton *) talkButton
{
    if (_talkButton == nil) {
        _talkButton = [[UIButton alloc] initWithFrame:CGRectZero];
        [_talkButton setTitle:[TOSKitCustomInfo shareCustomInfo].chatBox_talkText forState:UIControlStateNormal];
        [_talkButton setTitle:[TOSKitCustomInfo shareCustomInfo].chatBox_talkHighlightedText forState:UIControlStateHighlighted];
        [_talkButton setTitleColor:[TOSKitCustomInfo shareCustomInfo].VoiceButton_textColor forState:UIControlStateNormal];
        [_talkButton setTitleColor:[TOSKitCustomInfo shareCustomInfo].chatBox_talk_fontHighlightedColor forState:(UIControlStateHighlighted)];
//        [_talkButton setBackgroundImage:[UIImage gxz_imageWithColor:[TOSKitCustomInfo shareCustomInfo].VoiceButton_highlight_textColor] forState:UIControlStateHighlighted];
        [_talkButton setBackgroundImage:[UIImage gxz_imageWithColor:[TOSKitCustomInfo shareCustomInfo].chatBox_talk_backgroundColor] forState:(UIControlStateNormal)];
        [_talkButton setBackgroundImage:[UIImage gxz_imageWithColor:[TOSKitCustomInfo shareCustomInfo].chatBox_talk_backgroundHighlightedColor] forState:UIControlStateHighlighted];
        [_talkButton.titleLabel setFont:[TOSKitCustomInfo shareCustomInfo].chatBox_talkFont];
        [_talkButton.layer setMasksToBounds:YES];
//        [_talkButton.layer setCornerRadius:4.0f];
        [_talkButton.layer setCornerRadius:[TOSKitCustomInfo shareCustomInfo].chatBox_textView_cornerRadius];
        [_talkButton.layer setBorderWidth:[TOSKitCustomInfo shareCustomInfo].chatBox_talk_borderWidth];
        [_talkButton.layer setBorderColor:[TOSKitCustomInfo shareCustomInfo].chatBox_talk_borderColor.CGColor];
        [_talkButton setHidden:YES];
        [_talkButton addTarget:self action:@selector(talkButtonDown:) forControlEvents:UIControlEventTouchDown];
        [_talkButton addTarget:self action:@selector(talkButtonUpInside:) forControlEvents:UIControlEventTouchUpInside];
        [_talkButton addTarget:self action:@selector(talkButtonUpOutside:) forControlEvents:UIControlEventTouchUpOutside];
        [_talkButton addTarget:self action:@selector(talkButtonTouchCancel:) forControlEvents:UIControlEventTouchCancel];
        [_talkButton addTarget:self action:@selector(talkButtonDragOutside:) forControlEvents:UIControlEventTouchDragOutside];
        [_talkButton addTarget:self action:@selector(talkButtonDragInside:) forControlEvents:UIControlEventTouchDragInside];
    }
    return _talkButton;
}

- (UIButton *)sendButton {
    if (_sendButton == nil) {
        _sendButton = [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton;
        [_sendButton addTarget:self action:@selector(sendButtonTouch) forControlEvents:(UIControlEventTouchUpInside)];
        
    }
    return _sendButton;
}


- (YYLabel *) placeholderLable
{
    if (_placeholderLable == nil) {
        _placeholderLable = [[YYLabel alloc] initWithFrame:CGRectMake([TOSKitCustomInfo shareCustomInfo].chatBox_textView_placeholderMargin, [TOSKitCustomInfo shareCustomInfo].chatBox_textView_textContainerInset.top, [self placeholderWidth], [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height-[TOSKitCustomInfo shareCustomInfo].chatBox_textView_textContainerInset.top - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_textContainerInset.bottom)];
//        _placeholderLable = [[UILabel alloc] initWithFrame:(CGRectZero)];
//        _placeholderLable.enabled = NO;//禁止lable可以改变,必须设置
//        _placeholderLable.text = [TOSKitCustomInfo shareCustomInfo].ChatBox_textview_placeholder;//设置内容
//        _placeholderLable.font = [TOSKitCustomInfo shareCustomInfo].chatBox_textview_placeholderFont;//设置字体大小
//        _placeholderLable.textColor = [TOSKitCustomInfo shareCustomInfo].chatBox_textview_placeholderTextColor;//设置字体颜色为灰色
        _placeholderLable.userInteractionEnabled = NO;
        NSMutableParagraphStyle * style = [[NSMutableParagraphStyle alloc] init];
        style.lineSpacing = self.textView.font.lineHeight;
        NSMutableAttributedString * attributedStr = [[NSMutableAttributedString alloc] initWithString:[TOSKitCustomInfo shareCustomInfo].ChatBox_textview_placeholder];
        [attributedStr setAttributes:@{
            NSFontAttributeName : [TOSKitCustomInfo shareCustomInfo].chatBox_textView_font,
            NSForegroundColorAttributeName : [TOSKitCustomInfo shareCustomInfo].chatBox_textview_placeholderTextColor,
//            NSParagraphStyleAttributeName : style,
        } range:(NSMakeRange(0, [TOSKitCustomInfo shareCustomInfo].ChatBox_textview_placeholder.length))];
        _placeholderLable.attributedText = attributedStr;
        _placeholderLable.backgroundColor = [UIColor clearColor];//清楚背景颜色
        _placeholderLable.numberOfLines = 0;
        if ([TOSKitCustomInfo shareCustomInfo].chatBox_textView_maxRows == 1) {
            _placeholderLable.textVerticalAlignment = YYTextVerticalAlignmentCenter;
        }
        else {
            _placeholderLable.textVerticalAlignment = YYTextVerticalAlignmentTop;
        }
    }
    return _placeholderLable;
}

/// 暗文的宽度
- (CGFloat )placeholderWidth {
    /// 是否加载发送按钮
    BOOL sendShow = ![TOSKitCustomInfo shareCustomInfo].chatBox_emotionButton_enable && ![TOSKitCustomInfo shareCustomInfo].chatBox_moreButton_enable && [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_enable;
    
    CGFloat placeholderWidth = self.width - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_textContainerInset.left - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_textContainerInset.right - [TOSKitCustomInfo shareCustomInfo].chatBox_itemLeftPadding - [TOSKitCustomInfo shareCustomInfo].chatBox_itemRightPadding;
    
    if ([TOSKitCustomInfo shareCustomInfo].ChatBox_voiceButton_enable) {
        placeholderWidth -= (CHATBOX_BUTTON_WIDTH + [TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
    }
    if ([TOSKitCustomInfo shareCustomInfo].chatBox_emotionButton_enable) {
        placeholderWidth -= (CHATBOX_BUTTON_WIDTH + [TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
    }
    if ([TOSKitCustomInfo shareCustomInfo].chatBox_moreButton_enable) {
        placeholderWidth -= (CHATBOX_BUTTON_WIDTH + [TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
    }
    if (sendShow) {
        placeholderWidth -= ([TOSKitCustomInfo shareCustomInfo].chatBox_sendButtonSize.width + [TOSKitCustomInfo shareCustomInfo].chatBox_itemSpacing);
    }
    
    return placeholderWidth;
}

#pragma mark - ICChatBarFunctionViewDelegate

- (void)ICChatBarFunctionViewDidSelect:(NSInteger)index {
    
    NSLog(@"点击了第%ld个", index);
    
    if (self.barDelegate && [self.barDelegate respondsToSelector:@selector(ICChatBoxBarWithItemDidselectedIndex:)]) {
        [self.barDelegate ICChatBoxBarWithItemDidselectedIndex:index];
    }
}

#pragma mark - UITextViewDelegate

- (void) textViewDidBeginEditing:(UITextView *)textView
{
    //    ICChatBoxStatus lastStatus = self.status;
    self.status = ICChatBoxStatusShowKeyboard; 
}

- (void)textViewDidChange:(UITextView *)textView
{
    [self checkSendButtonType];
    //每0.5秒发送一次消息预知事件
    if (textView.text.length > 0) {     // send Text
//        [self.placeholderLable removeFromSuperview];
        self.placeholderLable.hidden = YES;
        self.nowInputText = [self transalteEmoticonAttributedString:self.textView.attributedText];
    }else{
        self.nowInputText = @"";
//        [self.textView addSubview:self.placeholderLable];
        self.placeholderLable.hidden = NO;
    }
    if (!self.bExitOrBackground) {
        [NSObject cancelPreviousPerformRequestsWithTarget:self selector:@selector(sendInputHintEvent) object:nil];
        [self performSelector:@selector(sendInputHintEvent) withObject:nil afterDelay:0.5];
    }
    
    if ([TOSKitCustomInfo shareCustomInfo].chatBox_textView_maxRows == 1) {
        return;
    }
    
    /// 获取快捷入口的高度
    CGFloat barHeight = self.barDataCount.count?CHATBOX_BAR_HEIGHT:0.0f;
    
    /// 这里修改是因为最小的高度，要跟配置单例里面的文本框初始高度做匹配
    CGFloat height = MAX([textView sizeThatFits:CGSizeMake(self.textView.width, MAXFLOAT)].height, [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height);
        
    if (height >= (ceilf(textView.font.lineHeight)*[TOSKitCustomInfo shareCustomInfo].chatBox_textView_maxRows)) {
        textView.scrollEnabled = YES;   ///> 当textView高度大于等于最大高度的时候让其可以滚动
        height = (ceilf(textView.font.lineHeight)*[TOSKitCustomInfo shareCustomInfo].chatBox_textView_maxRows);                   ///> 设置最大高度
    }
    if (textView.text.length > 5000) { // 限制5000字内
         textView.text = [textView.text substringToIndex:5000];
     }
    // 重新设置frame, textView往上增长高度
    [textView mas_TIMupdateTIMConstraints:^(TIMMASConstraintMaker *make) {
        make.height.mas_TIMequalTo(height);
    }];

    [self.textView mas_TIMupdateTIMConstraints:^(TIMMASConstraintMaker *make) {
        make.height.mas_TIMequalTo(height);
    }];
    [textView layoutIfNeeded];
    if (_delegate && [_delegate respondsToSelector:@selector(chatBox:changeChatBoxTextViewHeight:)]) {
        [_delegate chatBox:self changeChatBoxTextViewHeight:height + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height + barHeight];
    }
}

- (NSArray *)getMatchs {
    // 找到文本中所有的@
    NSRegularExpression *regex = [NSRegularExpression regularExpressionWithPattern:kTIMATRegular options:NSRegularExpressionCaseInsensitive error:nil];
    NSArray *matches = [regex matchesInString:self.textView.text options:NSMatchingReportProgress range:NSMakeRange(0, [self.textView.attributedText.string length])];
    return matches;
}


#pragma mark textViewDelegate

- (BOOL)textViewShouldBeginEditing:(UITextView *)textView{
    
    self.status = ICChatBoxStatusShowKeyboard;
    
    return YES;
}

- (void)textViewDidChangeSelection:(UITextView *)textView {
    // 光标不能点落在@词中间
    NSRange range = textView.selectedRange;
    if (range.length > 0) {
        // 选择文本时可以
        return;
    }
    
    NSArray *matches = [self getMatchs];
    
    for (NSTextCheckingResult *match in matches) {
        NSRange newRange = NSMakeRange(match.range.location + 1, match.range.length - 1);
        if (NSLocationInRange(range.location, newRange)) {
            if (range.location == match.range.location + 1) {
                textView.selectedRange = NSMakeRange(match.range.location + match.range.length, 0);
            } else {
                textView.selectedRange = NSMakeRange(match.range.location , 0);
            }
            break;
        }
    }
}

- (BOOL)textView:(UITextView *)textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *)text {
    //判断键入的字符
    if ([text isEqualToString:@"@"]) {
        if (self.delegate && [self.delegate respondsToSelector:@selector(chatBox:inputATSymbol:inputRange:)]) {
            
            [self.delegate  chatBox:self inputATSymbol:text inputRange:range];
        }
        return YES;
    }
    
    if ([text isEqualToString:@""]) {
        NSRange selectRange = textView.selectedRange;
        if (selectRange.length > 0) {
            //用户长按选择文本时不处理
            return YES;
        }
        
        // 判断删除的是一个@中间的字符就整体删除
        NSArray *matches = [self getMatchs];
        
        BOOL inAt = NO;
        NSInteger index = range.location;
        for (NSTextCheckingResult *match in matches) {
            NSRange newRange = NSMakeRange(match.range.location + 1, match.range.length - 1);
            if (NSLocationInRange(range.location, newRange)) {
                
                if (self.delegate && [self.delegate respondsToSelector:@selector(chatBox:deleteATSymbol:inputRange:)]) {
                    [self.delegate chatBox:self
                            deleteATSymbol:[textView.text substringWithRange:match.range]
                                inputRange:match.range];
                }
                
                inAt = YES;
                index = match.range.location;
                [textView.textStorage replaceCharactersInRange:match.range withString:@""];
                textView.selectedRange = NSMakeRange(index, 0);
                [self textViewDidChange:textView];
                return NO;
                break;
            }
        }
    }
    
    if ([text isEqualToString:@"\n"]) {
//        if (self.textView.attributedText.length > 0) {  // send Text
//            if (_delegate && [_delegate respondsToSelector:@selector(chatBox:sendTextMessage:)]) {
//                NSString * content = [self transalteEmoticonAttributedString:self.textView.attributedText];
//                [_delegate chatBox:self sendTextMessage:content];
//            }
//        }
//        [self.textView setHeight:[TOSKitCustomInfo shareCustomInfo].chatBox_textView_height];
//        if (_delegate && [_delegate respondsToSelector:@selector(chatBox:changeChatBoxHeight:)]) {
//            [_delegate chatBox:self changeChatBoxHeight:[TOSKitCustomInfo shareCustomInfo].chatBox_Height];
//        }
//        [self resumeTextHeight:YES];
//        [self.textView setText:@""];
        [self sendButtonTouch];
        
        return NO;
    }
    
    return YES;
}


//发送输入预知事件
-(void)sendInputHintEvent{
    [[OnlineEventSendManager sharedOnlineEventSendManager]inputHintEventWithText:self.nowInputText success:^{
        
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        
    }];
}

//发送输入预知结束事件
-(void)sendInputHintEndEvent{
    [[OnlineEventSendManager sharedOnlineEventSendManager]inputHintEventWithText:@"" success:^{
        
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        
    }];
}


#pragma mark - SWEmotionTextViewDelegate
- (BOOL)sw_emotionTextView:(UITextView *)textView shouldChangeToAttributedText:(NSAttributedString *)willChangedAttributedText {
    //限制表情文字输出的长度不超过48
//    if(willChangedAttributedText.length > 48){
//        return NO;
//    }
    return YES;
}

#pragma mark - Public Methods

- (BOOL)resignFirstResponder
{
    [self.textView resignFirstResponder];
    [_moreButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_extendBoard"]] forState:UIControlStateNormal];
    [_faceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_emotion"]] forState:UIControlStateNormal];
    return [super resignFirstResponder];
}

#pragma mark - Event Response
- (void)switchTextEditing {
    ICChatBoxStatus lastStatus = self.status;
    if (lastStatus == ICChatBoxStatusShowVoice) {//正在显示talkButton，改为键盘状态
        self.status = ICChatBoxStatusShowKeyboard;
        [self.talkButton setHidden:YES];
        [self.textView setHidden:NO];
        [self.textView becomeFirstResponder];
        [_voiceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_voice"]] forState:UIControlStateNormal];
    } else if (lastStatus == ICChatBoxStatusShowMore) { // 当前显示的就是more页面
        self.status = ICChatBoxStatusShowKeyboard;
        [self.textView becomeFirstResponder];
    } else if (lastStatus == ICChatBoxStatusShowFace) {       // 正在显示表情,改为现实键盘状态
        self.status = ICChatBoxStatusShowKeyboard;
        [_faceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_emotion"]] forState:UIControlStateNormal];
        [self.textView becomeFirstResponder];
    }
    if (_delegate && [_delegate respondsToSelector:@selector(chatBox:changeStatusForm:to:)]) {
        [_delegate chatBox:self changeStatusForm:lastStatus to:self.status];
    }
}

// 常用语按钮点击事件
-(void) wordsButtonDown:(UIButton *)sender{
    ICChatBoxStatus lastStatus = self.status;
    self.status = ICChatBoxStatusShowKeyboard;
    [self.talkButton setHidden:YES];
    [self.textView setHidden:NO];
//    [self.textView becomeFirstResponder];
    [_voiceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_voice"]] forState:UIControlStateNormal];
    
    if (_delegate && [_delegate respondsToSelector:@selector(chatBox:changeStatusForm:to:)]) {
           if (self.status != lastStatus) {
               [_delegate chatBox:self changeStatusForm:lastStatus to:self.status];
           }
    }
}

// 录音按钮点击事件
- (void) voiceButtonDown:(UIButton *)sender
{
    ICChatBoxStatus lastStatus = self.status;
    if (lastStatus == ICChatBoxStatusShowVoice) {//正在显示talkButton，改为键盘状态
        self.status = ICChatBoxStatusShowKeyboard;
        [self.talkButton setHidden:YES];
        [self.textView setHidden:NO];
        [self.textView becomeFirstResponder];
        [_voiceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_voice"]] forState:UIControlStateNormal];
    } else {    // 变成talkButton的状态
        self.status = ICChatBoxStatusShowVoice;
        [self.textView resignFirstResponder];
        [self.textView setHidden:YES];
        [self.talkButton setHidden:NO];
        [_faceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_emotion"]] forState:UIControlStateNormal];
        [_voiceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_keyboard"]] forState:UIControlStateNormal];
    }
    if (_delegate && [_delegate respondsToSelector:@selector(chatBox:changeStatusForm:to:)]) {
        if (self.status == lastStatus && lastStatus == ICChatBoxStatusShowKeyboard) {
            self.status = ICChatBoxStatusShowVoice;
            [_delegate chatBox:self changeStatusForm:lastStatus to:self.status];
        }else{
            [_delegate chatBox:self changeStatusForm:lastStatus to:self.status];
        }
    }
}

// 更多（+）按钮
- (void) moreButtonDown:(UIButton *)sender
{
    ICChatBoxStatus lastStatus = self.status;
    if (lastStatus == ICChatBoxStatusShowMore) { // 当前显示的就是more页面
        self.status = ICChatBoxStatusShowKeyboard;
        [self.textView becomeFirstResponder];
    } else {
        [self.talkButton setHidden:YES];
        [self.textView setHidden:NO];
        [_voiceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_voice"]] forState:UIControlStateNormal];
        
        self.status = ICChatBoxStatusShowMore;
        if (lastStatus == ICChatBoxStatusShowFace) {  // 改变按钮样式
            [_faceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_emotion"]] forState:UIControlStateNormal];
        } else if (lastStatus == ICChatBoxStatusShowVoice) {
            [_talkButton setHidden:YES];
            [_textView setHidden:NO];
            [_voiceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_voice"]] forState:UIControlStateNormal];
        } else if (lastStatus == ICChatBoxStatusShowKeyboard) {
            [self.textView resignFirstResponder];
            self.status = ICChatBoxStatusShowMore;
        }
    }
    if (_delegate && [_delegate respondsToSelector:@selector(chatBox:changeStatusForm:to:)]) {
        [_delegate chatBox:self changeStatusForm:lastStatus to:self.status];
    }
}

// 表情按钮
- (void) faceButtonDown:(UIButton *)sender
{
    ICChatBoxStatus lastStatus = self.status;
    if (lastStatus == ICChatBoxStatusShowFace) {       // 正在显示表情,改为现实键盘状态
        self.status = ICChatBoxStatusShowKeyboard;
        [_faceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_emotion"]] forState:UIControlStateNormal];
        [self.textView becomeFirstResponder];
    } else {
        [self.talkButton setHidden:YES];
        [self.textView setHidden:NO];
        [_voiceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_voice"]] forState:UIControlStateNormal];
        self.status = ICChatBoxStatusShowFace;
        [_faceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_keyboard"]] forState:UIControlStateNormal];
        if (lastStatus == ICChatBoxStatusShowMore) {
        } else if (lastStatus == ICChatBoxStatusShowVoice) {
            [_voiceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_voice"]] forState:UIControlStateNormal];
            [_talkButton setHidden:YES];
            [_textView setHidden:NO];
        }  else if (lastStatus == ICChatBoxStatusShowKeyboard) {
            [self.textView resignFirstResponder];
            self.status = ICChatBoxStatusShowFace;
        } else if (lastStatus == ICChatBoxStatusShowVoice) {
            [self.talkButton setHidden:YES];
            [self.textView setHidden:NO];
            [_voiceButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_voice"]] forState:UIControlStateNormal];
            self.status         = ICChatBoxStatusShowFace;
        }
        
    }
    if (_delegate && [_delegate respondsToSelector:@selector(chatBox:changeStatusForm:to:)]) {
        [_delegate chatBox:self changeStatusForm:lastStatus to:self.status];
    }
}

// 说话按钮
- (void)talkButtonDown:(UIButton *)sender
{
    self.recordVoiceTimer = [NSTimer scheduledTimerWithTimeInterval:60.5 target:self selector:@selector(recordVoiceTimeOver) userInfo:nil repeats:NO];
    if (_delegate && [_delegate respondsToSelector:@selector(chatBoxDidStartRecordingVoice:)]) {
        [_delegate chatBoxDidStartRecordingVoice:self];
    }
}

- (void)talkButtonUpInside:(UIButton *)sender
{
    if (_delegate && [_delegate respondsToSelector:@selector(chatBoxDidStopRecordingVoice:)]) {
        [_delegate chatBoxDidStopRecordingVoice:self];
    }
}

- (void)talkButtonUpOutside:(UIButton *)sender
{
    if (_delegate && [_delegate respondsToSelector:@selector(chatBoxDidCancelRecordingVoice:)]) {
        [_delegate chatBoxDidCancelRecordingVoice:self];
    }
}

- (void)talkButtonDragOutside:(UIButton *)sender
{
    if ([_delegate respondsToSelector:@selector(chatBoxDidDrag:)]) {
        [_delegate chatBoxDidDrag:NO];
    }
}

- (void)talkButtonDragInside:(UIButton *)sender
{
    if ([_delegate respondsToSelector:@selector(chatBoxDidDrag:)]) {
        [_delegate chatBoxDidDrag:YES];
    }
}

- (void)talkButtonTouchCancel:(UIButton *)sender
{
}

- (void)sendButtonTouch {
    
    if (self.textView.attributedText.length <= 0) {
        return;
    }
    
    if (self.textView.attributedText.length > 0) {  // send Text
        if (_delegate && [_delegate respondsToSelector:@selector(chatBox:sendTextMessage:)]) {
            NSString * content = [self transalteEmoticonAttributedString:self.textView.attributedText];
            [_delegate chatBox:self sendTextMessage:content];
        }
    }
    [self.textView setHeight:[TOSKitCustomInfo shareCustomInfo].chatBox_textView_height];
    if (_delegate && [_delegate respondsToSelector:@selector(chatBox:changeChatBoxHeight:)]) {
        [_delegate chatBox:self changeChatBoxHeight:[TOSKitCustomInfo shareCustomInfo].chatBox_Height];
    }
    [self resumeTextHeight:YES];
    [self.textView setText:@""];
    [self checkSendButtonType];
    
}

// time is over
- (void)recordVoiceTimeOver
{
    [self destroyTimer];
    // 结束录制
    if (_delegate && [_delegate respondsToSelector:@selector(chatBoxDidStopRecordingVoice:)]) {
        [_delegate chatBoxDidStopRecordingVoice:self];
    }
}

// 销毁定时器
- (void)destroyTimer
{
    [self.recordVoiceTimer invalidate];
    self.recordVoiceTimer = nil;
}

#pragma mark - Private

/// 发送按钮显示样式根据文本框是否有内容
- (void)checkSendButtonType {
    
    if (self.textView.attributedText.length > 0) {
        if (self.sendButton != nil) {
            self.sendButton.selected = YES;
            self.sendButton.layer.borderColor = [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_HighlightedColor.CGColor;
        }
    }
    else {
        if (self.sendButton != nil) {
            self.sendButton.selected = NO;
            self.sendButton.layer.borderColor = [TOSKitCustomInfo shareCustomInfo].chatBox_sendButton_borderColor.CGColor;
        }
    }
    
    self.placeholderLable.hidden = self.textView.attributedText.length > 0 ? YES : NO;
    
    [[NSNotificationCenter defaultCenter] postNotificationName:kTextViewChangeNotification object:nil userInfo:@{@"isHighlighted": @(self.placeholderLable.hidden)}];
    
}


- (void)emotionDidSelected:(NSNotification *)notifi
{
    XZEmotion *emotion = notifi.userInfo[GXSelectEmotionKey];
    if (emotion.code) {
        NSLog(@"emotion.code");
        [self.textView insertText:emotion.code.emoji];
        [self.textView scrollRangeToVisible:NSMakeRange(self.textView.text.length, 0)];
    } else if (emotion.face_name) {
        if ([emotion.type isEqualToString:kGIFTimageType]) {
            NSLog(@"kGIFTimageType");
            if (_delegate && [_delegate respondsToSelector:@selector(chatBox:sendGifImageMessage:)]) {
                [_delegate chatBox:self sendGifImageMessage:emotion.face_name];
            }
        }else{
            NSLog(@"sw_insertEmoticon");
            [self.textView sw_insertEmoticon:emotion];
        }
    }
    
    [self.textView scrollRangeToVisible:self.textView.selectedRange];
    [[NSNotificationCenter defaultCenter] postNotificationName:kTextViewChangeNotification object:nil userInfo:@{@"isHighlighted": @(YES)}];
}

// 删除
- (void)deleteBtnClicked
{
    [self.textView deleteBackward];
    BOOL isHighlighted = self.textView.attributedText.length > 0 ? YES : NO;
    [[NSNotificationCenter defaultCenter] postNotificationName:kTextViewChangeNotification object:nil userInfo:@{@"isHighlighted": @(self.placeholderLable.hidden)}];
}

- (void)sendMessage
{
    if (self.textView.text.length > 0) {     // send Text
        NSString * content = [self transalteEmoticonAttributedString:self.textView.attributedText];
        if (_delegate && [_delegate respondsToSelector:@selector(chatBox:sendTextMessage:)]) {
            [_delegate chatBox:self sendTextMessage:content];
        }
    }
    [self.textView setHeight:[TOSKitCustomInfo shareCustomInfo].chatBox_textView_height];
//    if (_delegate && [_delegate respondsToSelector:@selector(chatBox:changeChatBoxHeight:)]) {
//        [_delegate chatBox:self changeChatBoxHeight:HEIGHT_TABBAR];
//    }
    [self resumeTextHeight:YES];
    [self.textView setText:@""];
    [self checkSendButtonType];
    /// 执行顺序的问题，[self resumeTextHeight:YES]这个方法会调整 chatBox的高度，再去执行下方的文本内容发生改变就不会跳了
    if (_delegate && [_delegate respondsToSelector:@selector(chatBox:changeChatBoxHeight:)]) {
//        [_delegate chatBox:self changeChatBoxHeight:HEIGHT_TABBAR];
        [_delegate chatBox:self changeChatBoxHeight:[TOSKitCustomInfo shareCustomInfo].chatBox_Height];
    }
}

- (NSString *)transalteEmoticonAttributedString:(NSAttributedString *)emoticonAttributedString {
    NSRange range = NSMakeRange(0, emoticonAttributedString.length);
    NSMutableString *result = [[NSMutableString alloc] initWithString:@""];
    //遍历NSAttributedString,SWTextAttachment对应的字符串
    [emoticonAttributedString enumerateAttributesInRange:range options:0 usingBlock:^(NSDictionary<NSAttributedStringKey,id> * _Nonnull attrs, NSRange range, BOOL * _Nonnull stop) {
        SWTextAttachment *attachment = attrs[NSAttachmentAttributeName];
        if(attachment){
            if (attachment.chs) {
                [result appendString:attachment.chs];

            }else{
                TIMKitLog(@"attachment.chs 不存在");
            }
        }else{
            NSString *str = [self.textView.text substringWithRange:range];
            [result appendString:str];
        }
    }];
    return result;
}

-(void)resumeTextHeight:(BOOL)bResume{
    if ([TOSKitCustomInfo shareCustomInfo].chatBox_textView_maxRows == 1) {
        return;
    }
    
    /// 获取快捷入口的高度
    CGFloat barHeight = self.barDataCount.count?CHATBOX_BAR_HEIGHT:0.0f;
   // 键盘切换到录音时 恢复textView高度
    CGFloat textHeight = [self.textView sizeThatFits:CGSizeMake(self.textView.width, MAXFLOAT)].height;
    if (textHeight >= (ceilf(self.textView.font.lineHeight)*[TOSKitCustomInfo shareCustomInfo].chatBox_textView_maxRows)) {
        self.textView.scrollEnabled = YES;   ///> 当textView高度大于等于最大高度的时候让其可以滚动
        textHeight = ceilf(self.textView.font.lineHeight)*[TOSKitCustomInfo shareCustomInfo].chatBox_textView_maxRows;                   ///> 设置最大高度
    }
    if (self.textView.text.length > 5000) { // 限制5000字内
        self.textView.text = [self.textView.text substringToIndex:5000];
    }
    
    CGFloat height = bResume?[TOSKitCustomInfo shareCustomInfo].chatBox_textView_height:MAX(textHeight, [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height);
//    [self setHeight:(height + HEIGHT_TABBAR - HEIGHT_TEXTVIEW)];
    [self setBackgroundColor:[TOSKitCustomInfo shareCustomInfo].ChatBox_backGroundColor];
//    if (bResume) {
//        
//    }
//    [self mas_TIMupdateTIMConstraints:^(TIMMASConstraintMaker *make) {
//        make.height.mas_TIMequalTo(height + barHeight + HEIGHT_TABBAR - HEIGHT_TEXTVIEW);
//    }];
    self.height = height + barHeight + [TOSKitCustomInfo shareCustomInfo].chatBox_Height - [TOSKitCustomInfo shareCustomInfo].chatBox_textView_height;
//   self.textView.frame = CGRectMake(self.textView.origin.x,(HEIGHT_TABBAR - CHATBOX_BUTTON_WIDTH) / 2 , self.textView.width, height);
    
    
    [self.textView mas_TIMupdateTIMConstraints:^(TIMMASConstraintMaker *make) {
        make.height.mas_TIMequalTo(height);
    }];
   [self.textView layoutIfNeeded];
}

//禁止重按拖动textView上文本的交互
- (void)disableDragInteraction {
#ifdef __IPHONE_11_0
    if([self.textView respondsToSelector:@selector(textDragInteraction)]){
        self.textView.textDragInteraction.enabled = NO;
    }
#endif
}

// 监听通知
- (void)addNotification
{
    TIMKitLog(@"addNotification NSNotificationCenter");
    self.bExitOrBackground = NO;
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(emotionDidSelected:) name:GXEmotionDidSelectNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(deleteBtnClicked) name:GXEmotionDidDeleteNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(sendMessage) name:GXEmotionDidSendNotification object:nil];
    // 前台监听
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(willEnterBecomeActive) name:UIApplicationDidBecomeActiveNotification object:nil];
    // 压后台监听
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(willEnterBackground) name:UIApplicationDidEnterBackgroundNotification object:nil];
}

- (void)removeNotification{
    TIMKitLog(@"removeObserver NSNotificationCenter");
    self.bExitOrBackground = YES;
    self.nowInputText = @"";
    // 先取消发送预知信息
    [NSObject cancelPreviousPerformRequestsWithTarget:self selector:@selector(sendInputHintEvent) object:nil];
    // 在发送结束预知信息事件
    [self performSelector:@selector(sendInputHintEndEvent) withObject:nil afterDelay:0.5];
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

// 进入前台
- (void)willEnterBecomeActive {
    NSLog(@"进入前台");
    self.bExitOrBackground = NO;
}

/// 进入后台
- (void)willEnterBackground {
    NSLog(@"进入后台");
    self.bExitOrBackground = YES;
    self.nowInputText = @"";
    // 先取消发送预知信息
    [NSObject cancelPreviousPerformRequestsWithTarget:self selector:@selector(sendInputHintEvent) object:nil];
    // 在发送结束预知信息事件
    [self performSelector:@selector(sendInputHintEndEvent) withObject:nil afterDelay:0.5];
}

- (void)dealloc
{
    [self destroyTimer];
}

@end
