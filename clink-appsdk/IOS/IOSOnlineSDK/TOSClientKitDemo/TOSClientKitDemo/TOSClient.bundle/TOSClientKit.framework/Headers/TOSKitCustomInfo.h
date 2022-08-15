//
//  TOSKitCustomInfo.h
//  TOSClientKit
//
//  Created by 言 on 2022/7/1.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <TOSClientLib/TOSClientLib.h>

NS_ASSUME_NONNULL_BEGIN

@interface TOSKitCustomInfo : TIMLibBaseModel

+ (TOSKitCustomInfo *)shareCustomInfo;

/// 快速入口Item的圆角弧度
@property (nonatomic, assign) CGFloat quickEntryItem_cornerRadius;

/// 发送方气泡的颜色
@property (nonatomic, strong) UIColor *senderBubble_backGround;
/// 发送方气泡的圆角弧度
@property (nonatomic, assign) CGFloat senderBubble_cornerRadius;

/// 头像的圆角弧度
@property (nonatomic, assign) CGFloat portrait_cornerRadius;

/// 聊天背景颜色
@property (nonatomic, strong) UIColor *chat_backGround;

/// 快速入口Item的背景颜色
@property (nonatomic, strong) UIColor *quickEntryItem_backgroundColor;

/// 接收方气泡的颜色
@property (nonatomic, strong) UIColor *receiveBubble_backGround;
/// 接收方气泡的圆角弧度
@property (nonatomic, assign) CGFloat receiveBubble_cornerRadius;

/// 快速入口底部的背景颜色
@property (nonatomic, strong) UIColor *quickEntryBottom_backgroundColor;

/// 接收方字体颜色
@property (nonatomic, strong) UIColor *receiveText_Color;

/// 发送方字体颜色
@property (nonatomic, strong) UIColor *senderText_Color;

/// 聊天底部输入中的语音按钮控制
@property (nonatomic, assign) BOOL ChatBox_voiceButton_enable;

/// 聊天底部输入中文本输入框的暗文设置
@property (nonatomic, strong) NSString *ChatBox_textview_placeholder;

/// 聊天底部背景颜色
@property (nonatomic, strong) UIColor *ChatBox_backGroundColor;

/// 聊天底部中线条颜色
@property (nonatomic, strong) UIColor *ChatBox_lineColor;

/// 聊天中显示的时间字体颜色
@property (nonatomic, strong) UIColor *Chat_time_textColor;

/// 启用或关闭客服或机器人昵称的显示
@property (nonatomic, assign) BOOL Chat_tosRobotName_show;

/// 启用或关闭访客昵称的显示
@property (nonatomic, assign) BOOL Chat_visitorName_show;

/// 启用或关闭客服昵称(机器人昵称)UI区域的显示
@property (nonatomic, assign) BOOL Chat_tosRobotName_enable;

/// 启用或关闭访客昵称UI区域的显示
@property (nonatomic, assign) BOOL Chat_visitorName_enable;

/// 启用或关闭客服和机器人头像UI区域的显示
@property (nonatomic, assign) BOOL Chat_tosRobot_portrait_enable;

/// 启用或关闭访客头像UI区域的显示
@property (nonatomic, assign) BOOL Chat_visitor_portrait_enable;

/// 相册展示导航栏中的文字颜色
@property (nonatomic, strong) UIColor *imagePicker_barItemTextColor;

/// 相册展示导航栏中的背景颜色
@property (nonatomic, strong) UIColor *imagePicker_naviBgColor;

/// 吐司提示气泡背景颜色
@property (nonatomic, strong) UIColor *Toast_backGroundColor;

/// 吐司提示中文字颜色
@property (nonatomic, strong) UIColor *Toast_textColor;

/// 语音按钮中文字颜色
@property (nonatomic, strong) UIColor *VoiceButton_textColor;

/// 语音按钮中文字高亮颜色
@property (nonatomic, strong) UIColor *VoiceButton_highlight_textColor;
@end

NS_ASSUME_NONNULL_END
