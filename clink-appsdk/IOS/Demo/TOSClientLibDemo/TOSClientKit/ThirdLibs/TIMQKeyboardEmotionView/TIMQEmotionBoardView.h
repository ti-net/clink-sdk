//
//  QMUIEmotionView.h
//  qmui
//
//  Created by 侯力 on 2024/03/20.
//  Copyright (c) 2021年 侯力. All rights reserved.
//  本库修改了修复QMUI的两个BUG，1：计算半透明表情的计算方式有问题，2：重新优化点击后的灰色背景的大小改为计算，而不是设置死
//

#import <UIKit/UIKit.h>
#import "TIMQEmotion.h"

@class TIMQEmotionBoardView;

@protocol TIMQEmotionBoardViewDelegate <NSObject>

@optional
/**
 *  选中表情时的回调
 *  @param  index   被选中的表情在`emotions`里的索引
 *  @param  emotion 被选中的表情对应的`QMUIEmotion`对象
 */
- (void)emotionView:(TIMQEmotionBoardView *)emotionView didSelectEmotion:(TIMQEmotion *)emotion atIndex:(NSInteger)index;

// 删除按钮的点击事件回调
- (void)emotionViewDidSelectDeleteButton:(TIMQEmotionBoardView *)emotionView;

// 发送按钮的点击事件回调
- (void)emotionViewDidSelectSendButton:(TIMQEmotionBoardView *)emotionView;

@end

/**
 *  表情控件，支持任意表情的展示，每个表情以相同的大小显示。
 *
 *  使用方式：
 *
 *  - 通过`initWithFrame:`初始化，如果面板高度不变，建议在init时就设置好，若最终布局以父类的`layoutSubviews`为准，则也可通过`init`方法初始化，再在`layoutSubviews`里计算布局
 *  - 通过调整`paddingInPage`、`emotionSize`等变量来自定义UI
 *  - 通过`emotions`设置要展示的表情
 *  - 通过`didSelectEmotionBlock`设置选中表情时的回调，通过`didSelectDeleteButtonBlock`来响应面板内的删除按钮
 *  - 为`sendButton`添加`addTarget:action:forState:`事件，从而触发发送逻辑
 *
 *  本控件支持通过`UIAppearance`设置全局的默认样式。若要修改控件内的`UIPageControl`的样式，可通过`[UIPageControl appearanceWhenContainedInInstancesOfClasses:@[[QMUIEmotionView class]]]`的方式来修改。
 */
@interface TIMQEmotionBoardView : UIView

/// 要展示的所有表情
@property(nonatomic, copy) NSArray<TIMQEmotion *> *emotions;

/**
 *  选中表情时的回调
 *  @argv  index   被选中的表情在`emotions`里的索引
 *  @argv  emotion 被选中的表情对应的`QMUIEmotion`对象
 *  @see QMUIEmotion
 */
//@property(nonatomic, copy) void (^didSelectEmotionBlock)(NSInteger index, QMUIEmotion *emotion);
//
///// 删除按钮的点击事件回调
//@property(nonatomic, copy) void (^didSelectDeleteButtonBlock)(void);


@property(nonatomic, weak) id<TIMQEmotionBoardViewDelegate> delegate;

/// 用于展示表情面板的竖向滚动的 scrollView，布局撑满整个控件
@property(nonatomic, strong, readonly) UIScrollView *scrollView;

/// 表情与表情之间的垂直间距，默认为10，仅在 verticalAlignment 为 YES 时生效，当 verticalAlignment 为 N0 时，表情的垂直间距由 numberOfRowsPerPage 决定
@property(nonatomic, assign) CGFloat emotionVerticalSpacing UI_APPEARANCE_SELECTOR;

/// 控件右下角的发送按钮
@property(nonatomic, strong, readonly) UIButton *sendButton;

/// 控件右下角的删除按钮
@property(nonatomic, strong, readonly) UIButton *deleteButton;

/// 每一页表情的上下左右padding，默认为{18, 18, 65, 18}
@property(nonatomic, assign) UIEdgeInsets paddingInPage UI_APPEARANCE_SELECTOR;

/// 每一页表情允许的最大行数，默认为4
@property(nonatomic, assign) NSInteger numberOfRowsPerPage UI_APPEARANCE_SELECTOR;

/// 表情的图片大小，不管`QMUIEmotion.image.size`多大，都会被缩放到`emotionSize`里显示，默认为{30, 30}
@property(nonatomic, assign) CGSize emotionSize UI_APPEARANCE_SELECTOR;

/// 表情点击时的背景遮罩相对于`emotionSize`往外拓展的区域，负值表示遮罩比表情还大，正值表示遮罩比表情还小，默认为{-3, -3, -3, -3}
@property(nonatomic, assign) UIEdgeInsets emotionSelectedBackgroundExtension UI_APPEARANCE_SELECTOR;

/// 表情与表情之间的最小水平间距，默认为10
@property(nonatomic, assign) CGFloat minimumEmotionHorizontalSpacing UI_APPEARANCE_SELECTOR;

/// 表情面板右下角的删除按钮的图片，默认为`[QMUIHelper imageWithName:@"QMUI_emotion_delete"]`
@property(nonatomic, strong) UIImage *deleteButtonImage UI_APPEARANCE_SELECTOR;

/// 删除按钮的背景色，默认为 nil
@property(nonatomic, strong) UIColor *deleteButtonBackgroundColor UI_APPEARANCE_SELECTOR;

/// 删除按钮位置的 (x,y) 的偏移，默认为 CGPointZero
@property(nonatomic, assign) CGPoint deleteButtonOffset UI_APPEARANCE_SELECTOR;

/// 删除按钮的圆角大小，默认为4
@property(nonatomic, assign) CGFloat deleteButtonCornerRadius UI_APPEARANCE_SELECTOR;

/// 发送按钮的文字样式，默认为{NSFontAttributeName: UIFontMake(15), NSForegroundColorAttributeName: UIColorWhite}
@property(nonatomic, strong) NSDictionary *sendButtonTitleAttributes UI_APPEARANCE_SELECTOR;

/// 发送按钮的背景色，默认为`UIColorBlue`
@property(nonatomic, strong) UIColor *sendButtonBackgroundColor UI_APPEARANCE_SELECTOR;

/// 发送按钮的圆角大小，默认为4
@property(nonatomic, assign) CGFloat sendButtonCornerRadius UI_APPEARANCE_SELECTOR;

/// 发送按钮布局时的外边距，相对于控件右下角。仅right/bottom有效，默认为{0, 0, 16, 16}
@property(nonatomic, assign) UIEdgeInsets sendButtonMargins UI_APPEARANCE_SELECTOR;

/// 分页控件距离底部的间距，默认为22
@property(nonatomic, assign) CGFloat pageControlMarginBottom UI_APPEARANCE_SELECTOR;

/// 顶部那条细线
@property(nonatomic, strong) UIView *topLineView;

/// 当前按钮的状态（文本框中是否有值）
@property (nonatomic, assign) BOOL                isHighlighted;

@end
