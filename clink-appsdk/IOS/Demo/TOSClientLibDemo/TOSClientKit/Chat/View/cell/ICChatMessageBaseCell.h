//
//  ICChatMessageBaseCell.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/12.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TIMMessageFrame.h"
#import "UIResponder+GXRouter.h"
#import "ICMessageConst.h"
#import "ICHeadImageView.h"
#import "ICMessageTopView.h"
#import "ICMessageTopTimeView.h"

@class ICChatMessageBaseCell;
@protocol BaseCellDelegate <NSObject>

- (void)longPress:(UILongPressGestureRecognizer *)longRecognizer;

//机器人发送关联消息
-(void)sendCombinaMessage:(NSString *)text;
//文件代理
-(void)downFileMessage:(NSString *)fileUrl;

@optional
- (void)headImageClicked:(TIMMessageModel *)model;
- (void)reSendMessage:(ICChatMessageBaseCell *)baseCell;



@end

@interface ICChatMessageBaseCell : UITableViewCell

@property (nonatomic, weak) id<BaseCellDelegate> longPressDelegate;

// 消息模型
@property (nonatomic, strong) TIMMessageFrame *modelFrame;
// 名称
@property (nonatomic, strong) UILabel *nameLabel;
// 头像
@property (nonatomic, strong) ICHeadImageView *headImageView;
// 内容气泡视图
@property (nonatomic, strong) UIImageView *bubbleView;
// 菊花视图所在的view
@property (nonatomic, strong) UIActivityIndicatorView *activityView;
// 重新发送
@property (nonatomic, strong) UIButton *retryButton;
// 顶部中间时间线
@property (nonatomic, strong) ICMessageTopTimeView *messageTopTimeView;
// 时间线
@property (nonatomic, strong) ICMessageTopView *messageTopView;

/// 已读、未读
@property (nonatomic, strong) UILabel *readLabel;

- (void)setupUI;

@end
