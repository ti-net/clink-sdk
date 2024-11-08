//
//  TIMMessageFrame.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/11.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <TOSClientLib/TIMLibBaseModel.h>

@interface TOSSatisfactionTabBarFrameModel : TIMLibBaseModel

@property (nonatomic, strong) NSNumber *x;

@property (nonatomic, strong) NSNumber *y;

@property (nonatomic, strong) NSNumber *w;

@property (nonatomic, strong) NSNumber *h;

@end

@class TIMMessageModel;
@interface TIMMessageFrame : NSObject

//聊天信息的背景图
@property (nonatomic, assign, readonly) CGRect bubbleViewF;

//聊天信息label
@property (nonatomic, assign) CGRect chatLabelF;

//小程序相关组件Frame

@property (nonatomic, assign) CGRect smallProgram_appLogoF;

@property (nonatomic, assign) CGRect smallProgram_appNameF;


@property (nonatomic, assign) CGRect smallProgram_titleF;

@property (nonatomic, assign) CGRect smallProgram_picurlF;

@property (nonatomic, assign) CGRect smallProgram_lineF;

@property (nonatomic, assign) CGRect smallProgram_iconF;
@property (nonatomic, assign) CGRect smallProgram_iconTextF;
@property (nonatomic, assign) CGRect smallProgram_copyBtnF;

//物流卡片相关组件Frame
@property (nonatomic, assign) CGRect logisticsCard_startDateF;
@property (nonatomic, assign) CGRect logisticsCard_commodityNameF;

@property (nonatomic, assign) CGRect logisticsCard_startIconF;
@property (nonatomic, assign) CGRect logisticsCard_verticalLineF;
@property (nonatomic, assign) CGRect logisticsCard_endIconF;

@property (nonatomic, assign) CGRect logisticsCard_startingPointF;
@property (nonatomic, assign) CGRect logisticsCard_endPointF;

@property (nonatomic, assign) CGRect logisticsCard_commodityAmountF;
@property (nonatomic, assign) CGRect logisticsCard_commodityQuantityBtnF;

@property (nonatomic, assign) CGRect logisticsCard_horizontalLineF;

@property (nonatomic, assign) CGRect logisticsCard_orderNumberF;
@property (nonatomic, assign) CGRect logisticsCard_copyBtnF;


//敏感词label
@property (nonatomic, assign) CGRect sensitiveWordsLabelF;

//已读、未读label
@property (nonatomic, assign) CGRect unReadLabelF;

//发送的菊花视图
@property (nonatomic, assign, readonly) CGRect activityF;

//重新发送按钮
@property (nonatomic, assign, readonly) CGRect retryButtonF;

// 名称
@property (nonatomic, assign, readonly) CGRect headNameF;
// 头像
@property (nonatomic, assign, readonly) CGRect headImageViewF;

// topTimeView   时间线
@property (nonatomic, assign, readonly) CGRect topTimeViewF;  

// topView   /***第一版***/
@property (nonatomic, assign, readonly) CGRect topViewF;   // 这里仅表示名称

//计算总的高度
@property (nonatomic, assign) CGFloat cellHight;

/// 消息模型
@property (nonatomic, strong) TIMMessageModel *model;

/// 图片
@property (nonatomic, assign, readonly) CGRect picViewF;

/// 语音图标
@property (nonatomic, assign) CGRect voiceIconF;

/// 语音时长数字
@property (nonatomic, assign) CGRect durationLabelF;

/// 语音未读红点
@property (nonatomic, assign) CGRect redViewF;

/// 上传进度
@property (nonatomic, assign) CGFloat uploadProgress;

/// 审核状态显示
@property (nonatomic, assign) CGRect aduitStatusViewF;

/// 审核操作按钮
@property (nonatomic, assign) CGRect aduitApprovedViewF;

/// 审核操作按钮
@property (nonatomic, assign) CGRect aduitRejectedViewF;

/// 机器人组合消息整体高度 14类型
@property (nonatomic, assign) CGRect robotCombinationF;

/// 自定义消息的shareTitle
@property (nonatomic, assign) CGRect custShareTitleF;

/// 自定义消息的title
@property (nonatomic, assign) CGRect custTitleF;

/// 自定义消息的pic
@property (nonatomic, assign) CGRect custPictureF;

/// 自定义消息的content
@property (nonatomic, assign) CGRect custContentF;

/// 自定义文件的内容
@property (nonatomic, assign) CGRect custFileNameF;

/// 富文本消息的整体高度
@property (nonatomic, assign) CGRect richTextContentF;

/// 引用消息整体大小
@property (nonatomic, assign) CGRect repliedMessageViewF;

/// 引用消息文本内容大小
@property (nonatomic, assign) CGRect repliedMessageContentF;

/// 引用消息文本内容
@property (nonatomic, copy) NSString *repliedMessageContent;

/// 自定义文件的类型描述
@property (nonatomic, assign) CGRect custFileTypeDescF;

/// 自定义文件的pic
@property (nonatomic, assign) CGRect custFilePictureF;

/// 自定义文件的时间
@property (nonatomic, assign) CGRect custFileTimeF;

/// 折叠状态，默认NO：展开。 YES：折起
@property (nonatomic, assign) BOOL foldAndUnfold;

/// 满意度标题高度
@property (nonatomic, assign) CGFloat satisfactionTitleHeight;

/// 满意度星级宽度
@property (nonatomic, assign) CGFloat satisfactionStarWidth;

/// 满意度选中的星级
@property (nonatomic, assign) NSInteger satisfactionSelectStar;

/// 满意度选中的星级的标签的总高度
@property (nonatomic, assign) NSInteger satisfactionSelectStarTagHeight;

@property (nonatomic, strong) NSArray <TOSSatisfactionTabBarFrameModel *>*tabBarFrame;

@property (nonatomic, strong) NSMutableArray <NSString *>*tabBarSelect;

/// 热点问题的标题高度
@property (nonatomic, assign) CGRect hotIssueTitleF;

/// 换一换高度
@property (nonatomic, assign) CGFloat refreshBtnY;

/// 换一换icon高度
@property (nonatomic, assign) CGFloat refreshIconY;

//机器人快捷回复聊天信息的背景图
@property (nonatomic, assign, readonly) CGRect textTagBubbleViewF;

//机器人快捷回复的标签Frame
@property (nonatomic, assign, readonly) CGRect textTagViewF;



//商品卡片(详情) 底部ViewFrame
@property (nonatomic, assign, readonly) CGRect bottomViewF;
//商品卡片(详情) Cell点击按钮Frame
@property (nonatomic, assign, readonly) CGRect cellClickBtnF;

//商品卡片(详情) 订单号Frame
@property (nonatomic, assign, readonly) CGRect orderNumberF;
//商品卡片(详情) 时间Frame
@property (nonatomic, assign, readonly) CGRect timeF;

//商品卡片(详情) 上分割线Frame
@property (nonatomic, assign, readonly) CGRect topCuttingLineF;

//商品卡片(详情) 商品图片Frame
@property (nonatomic, assign, readonly) CGRect commodityPicF;
//商品卡片(详情) 商品标题Frame
@property (nonatomic, assign, readonly) CGRect commodityTitleF;
//商品卡片(详情) 商品子标题Frame
@property (nonatomic, assign, readonly) CGRect commoditySubTitleF;
//商品卡片(详情) 商品价格Frame
@property (nonatomic, assign, readonly) CGRect priceF;

//商品卡片(详情) 下分割线Frame
@property (nonatomic, assign, readonly) CGRect bottomCuttingLineF;

//商品卡片(详情) 运输的标题Frame
@property (nonatomic, assign, readonly) CGRect transportTitleF;
//商品卡片(详情) 运输的状态Frame
@property (nonatomic, assign, readonly) CGRect transportStatusF;
//商品卡片(详情) 折叠按钮Frame
@property (nonatomic, assign, readonly) CGRect foldAndUnfoldBtnF;
//商品卡片(详情) 折叠按钮图标Frame
@property (nonatomic, assign, readonly) CGRect foldAndUnfoldIconF;
//商品卡片(详情) 运输详情Frame
@property (nonatomic, assign, readonly) CGRect transportDetailsF;

///// 图片重定向用到
//@property (nonatomic, copy) NSString                * robotProvider;

@end
