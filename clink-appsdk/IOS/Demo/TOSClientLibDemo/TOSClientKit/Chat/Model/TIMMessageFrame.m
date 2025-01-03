//
//  TIMMessageFrame.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/11.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "TIMMessageFrame.h"
#import "TIMMessageModel.h"
#import "NSString+Extension.h"
#import "ICMediaManager.h"
#import "UIImage+Extension.h"
#import "ICMessageConst.h"
#import "ICMessageHelper.h"
#import "ICVideoManager.h"
#import "ICRecordManager.h"
#import "TIMConstants.h"
#import "kitUtils.h"
#import <TOSClientLib/TIMCommodityCardMessage.h>
#import "NSString+Frame.h"
#import <TOSClientLib/RichTextMessage.h>
#import <TOSClientLib/CombinationMessage.h>
#import "TOSSatisfactionModel.h"
#import "TOSSatisfactionStatusModel.h"
#import "YYKit.h"
#import "XZEmotion.h"
#import "ICFaceManager.h"
#import <TOSClientLib/TOSGetInvestigationInfoModel.h>
#import "TTGTextTagCollectionView.h"
#import <TOSClientLib/TOSMessageTextTagModel.h>
#import <TOSClientLib/TOSMessageSmallProgramModel.h>
#import <TOSClientLib/TIMLogisticsCardMessage.h>
#import <TOSClientKit/TOSKitCustomInfo.h>

#define APP_WIDTH ([UIScreen mainScreen].bounds.size.width)
#define APP_HEIGHT ([UIScreen mainScreen].bounds.size.height)

@implementation TOSSatisfactionTabBarFrameModel

@end

@implementation TIMMessageFrame


- (void)setModel:(TIMMessageModel *)model
{
    _model = model;
    NSLog(@"model ====== %@",[model yy_modelToJSONObject]);
    /// 头像距离屏幕边缘的距离
    CGFloat headToView    = [TOSKitCustomInfo shareCustomInfo].headMargin > APP_WIDTH/4 ? APP_WIDTH/4 : [TOSKitCustomInfo shareCustomInfo].headMargin;
    /// 头像距离气泡的距离
    CGFloat headToBubble  = [TOSKitCustomInfo shareCustomInfo].headToBubble;
    /// 头像的宽度
    CGFloat headWidth     = [TOSKitCustomInfo shareCustomInfo].headWidth;//45;
    /// 每行的距离间隔
    CGFloat cellMargin    = [TOSKitCustomInfo shareCustomInfo].cellMargin;
    /// 气泡的内间距
    CGFloat bubblePadding = [TOSKitCustomInfo shareCustomInfo].bubblePadding;
    /// 聊天气泡的最大宽度
    //    CGFloat chatLabelMax  = APP_WIDTH - headWidth - 100;
    /// 聊天气泡的最大宽度(⚠️还需要减去重发按钮的宽度)
    CGFloat chatLabelMax  = APP_WIDTH - headWidth - [TOSKitCustomInfo shareCustomInfo].resendButtonSize.width - headToView - headToBubble - [TOSKitCustomInfo shareCustomInfo].resendToBubblePadding;
    if ([TOSKitCustomInfo shareCustomInfo].bubbleMaxWidth < chatLabelMax) {
        chatLabelMax = [TOSKitCustomInfo shareCustomInfo].bubbleMaxWidth;
    }
    CGFloat arrowWidth    = 0;      // 气泡箭头
    /// 距离顶部的距离
    CGFloat topViewH      = 10;
    /// 时间控件的高度
    CGFloat topTimeViewH  = 20;
    CGFloat cellMinW      = 60;     // cell的最小宽度值,针对文本
    //    CGFloat voiceMinW     = 60;     // 针对语音的最小宽度
    CGFloat voiceMinW     = [TOSKitCustomInfo shareCustomInfo].chat_voiceMinWidth;     // 针对语音的最小宽度
    /// 顶部视图加上气泡的高度
    //    CGFloat topViewToBubbleH = 2+20;
    CGFloat topViewToBubbleH = 20;      /// 不清楚之前为什么加2 （teil留）
    /// 时间控件显示的最长宽度
    CGFloat timeShowLength = 135;
    /// 图片最小宽度
    CGFloat picMinWidth  = 150.0;
    /// 图片最大宽度
    CGFloat picMaxWidth  = 300.0;
    /// 未读控件的宽度
    CGFloat unreadLabelWidth  = 53.0;
    /// 未读控件的高度
    CGFloat unreadLabelH  = 15.0;
    
    /// 昵称和气泡的间距
    CGFloat sendNickNameToBubbleSpacing = [TOSKitCustomInfo shareCustomInfo].Chat_visitorName_enable ? [TOSKitCustomInfo shareCustomInfo].nickNameToBubbleSpacing : 0.0f;
    CGFloat receiveNickNameToBubbleSpacing = [TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_enable ? [TOSKitCustomInfo shareCustomInfo].nickNameToBubbleSpacing : 0.0f;
    
    CGSize topCenterTimeSize  = CGSizeMake(0, 0);
    if (model.message.showTime) {
        topCenterTimeSize = CGSizeMake(APP_WIDTH, topTimeViewH);
    }else{
        topTimeViewH = 0;
    }
    _topTimeViewF = CGRectMake(0, 0, topCenterTimeSize.width, topCenterTimeSize.height);
    CGSize timeSize  = CGSizeMake(0, 0);
    if (model.isSender) {
        topViewH = 0;
        cellMinW = timeSize.width + arrowWidth + bubblePadding*2;
        CGFloat headX = APP_WIDTH - headToView - headWidth;
        if ([TOSKitCustomInfo shareCustomInfo].Chat_visitor_portrait_enable) {
            _headImageViewF = CGRectMake(headX, cellMargin+topTimeViewH, headWidth, headWidth);
        } else {
            _headImageViewF = CGRectMake(headX + headWidth, cellMargin+topTimeViewH, 0, 0);
        }
        
        if ([TOSKitCustomInfo shareCustomInfo].Chat_visitorName_enable) {
            
            NSString * nameStr = [[OnlineDataSave shareOnlineDataSave] getVisitorName];
            CGSize nameSize = [nameStr tim_sizeWithMaxWidth:(headX - 7 - 8) andFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_visitorName_font];
            topViewToBubbleH = nameSize.height;
            _headNameF = CGRectMake(7, cellMargin+topTimeViewH, headX - 7 - 8, nameSize.height);//5为名字右侧边距
            if (![TOSKitCustomInfo shareCustomInfo].Chat_visitor_portrait_enable) {
                _headNameF = CGRectMake(7, cellMargin+topTimeViewH, headX - 7 - 8 + headWidth, nameSize.height);//5为名字右侧边距
            }
        } else {
            _headNameF = CGRectZero;
            topViewToBubbleH = 0;
        }
        
        if ([model.message.type isEqualToString:TypeText] ||             // 文字
            [model.message.type isEqualToString:TypeUnsupport]) {    // 未知类型消息
            
            UIFont *font = [TOSKitCustomInfo shareCustomInfo].chatMessage_visitorText_font;
            NSMutableAttributedString *attributedString = [model.message.content transferMessageFont:font
                                                                                           foreColor:[self setTextLabelColor:model.isSender]
                                                                                             fontDic:nil
                                                                                          lineHeight:font.lineHeight
                                                                                    filteredLineFeed:YES];
            model.attributedString = attributedString;
            
            CGSize chateLabelSize = [model.message.content tim_sizeWithMaxWidth:chatLabelMax attributeStr:attributedString];
            
            NSLog(@"文案的高度：%@", NSStringFromCGSize(chateLabelSize));
            
            chateLabelSize.width += 2.f;
            CGSize bubbleSize     = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + bubblePadding * 2);
            CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            
            _bubbleViewF          = CGRectMake(CGRectGetMinX(_headImageViewF) - headToBubble - bubbleSize.width, cellMargin+topViewH+topViewToBubbleH+topTimeViewH + sendNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGFloat x             = CGRectGetMinX(_bubbleViewF)+bubblePadding;
            _topViewF             = CGRectMake(CGRectGetMinX(_headImageViewF) - timeShowLength , cellMargin+topTimeViewH,topViewSize.width,topViewSize.height);
            
            _chatLabelF           = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + (bubbleSize.height -chateLabelSize.height) / 2, chateLabelSize.width, chateLabelSize.height);
            
            if (model.message.deliveryState == ICMessageDeliveryState_Failure_SensitiveWords) {
                _sensitiveWordsLabelF = CGRectMake(CGRectGetMinX(_headImageViewF) - headToBubble - 200.f, CGRectGetMaxY(_bubbleViewF) + 4.f, 200.f, 16.f);
            } else {
                _sensitiveWordsLabelF = CGRectZero;
            }
        } else if ([model.message.type isEqualToString:GXRichText]) {
            // 发送端暂不支持富文本
            
        } else if ([model.message.type isEqualToString:TypeCommodityCardDetails]) {
            
            TIMCommodityCardMessage *cardMessgae = (TIMCommodityCardMessage *)model.message.extra;
            
            CGFloat bottomViewH = 0.f;
            
            CGFloat bottomViewW = _headImageViewF.origin.x - 14.f * 2;
            
            __block NSString *orderNumber = @"";
            if ([cardMessgae.extraInfo isKindOfClass:[NSArray class]]) {
                [cardMessgae.extraInfo enumerateObjectsUsingBlock:^(NSDictionary<NSString *,NSString *> * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    
                    if ([obj[@"name"] isEqualToString:@"订单号"]) {
                        orderNumber = obj[@"value"];
                        *stop = YES;
                    }
                }];
            }
            
            CGFloat orderNumberY = 0;
            CGFloat orderNumberH = 0;
            if (![kitUtils isBlankString:orderNumber]) {
                orderNumberY = 7.f;
                orderNumberH = 18.f;
            }
            
            CGFloat timeY = 0;
            CGFloat timeH = 0;
            if (![kitUtils isBlankString:cardMessgae.time]) {
                timeY = 7.f;
                timeH = 18.f;
            }
            
            NSString *orderNum;
            if (orderNumber &&
                orderNumber.length > 0) {
                orderNum = [NSString stringWithFormat:@"订单号：#%@",orderNumber];
            } else {
                orderNum = @" ";
            }
            
            CGFloat orderNumberW = [orderNum contentWidthWithFont:MessageFont12 height:100];
            orderNumberW = orderNumberW <= 190.f ? orderNumberW : 190.f;
            CGFloat timeW = bottomViewW-14.f*2-orderNumberW-10.f;
            
            _orderNumberF = CGRectMake(14.f, orderNumberY, orderNumberW, orderNumberH);
            _timeF = CGRectMake(bottomViewW - timeW - 14.f, timeY, timeW, timeH);
            
            CGFloat topCuttingLineY = 0.f;
            CGFloat topCuttingLineH = 0.f;
            CGFloat topCuttingLineTop = MAX(CGRectGetMaxY(_orderNumberF), CGRectGetMaxY(_timeF));
            if (topCuttingLineTop > 18.f) {
                topCuttingLineY = topCuttingLineTop + 7.f;
                topCuttingLineH = 0.5f;
            }
            _topCuttingLineF = CGRectMake(14.f, topCuttingLineY, bottomViewW - 14.f * 2, topCuttingLineH);
            
            CGFloat commodityPicX = 0.f;
            CGFloat commodityPicW = 0.f;
            if (![kitUtils isBlankString:cardMessgae.img]) {
                commodityPicX = 14.f;
                commodityPicW = 80.f;
            }
            _commodityPicF = CGRectMake(commodityPicX, CGRectGetMaxY(_topCuttingLineF) + 9.f, commodityPicW, 80.f);
            
            
            CGFloat titleH = 35.f;
            CGFloat subTitleTopSpace = 0.f;
            if (![kitUtils isBlankString:cardMessgae.subTitle]) {
                
                CGSize subTitleSize = [cardMessgae.subTitle tim_sizeWithMaxWidth:chatLabelMax andFont:MessageFont12];
                if (subTitleSize.height > 20.f) {
                    titleH = 35.f;
                } else {
                    titleH = 18.f;
                    subTitleTopSpace = 17.f;
                }
            }
            _commodityTitleF = CGRectMake(CGRectGetMaxX(_commodityPicF) + 10.f, CGRectGetMaxY(_topCuttingLineF) + 9.f, bottomViewW - CGRectGetMaxX(_commodityPicF) - 10.f - 14.f, titleH);
            _commoditySubTitleF = CGRectMake(CGRectGetMinX(_commodityTitleF), CGRectGetMaxY(_commodityTitleF) + 4.f + subTitleTopSpace, CGRectGetWidth(_commodityTitleF), 18.f);
            _priceF = CGRectMake(CGRectGetMinX(_commodityTitleF), CGRectGetMaxY(_commoditySubTitleF) + 8.f, CGRectGetWidth(_commodityTitleF), 18.f);
            
            CGFloat bottomCuttingLineH = 0.f;
            if (![kitUtils isBlankString:cardMessgae.status] ||
                (cardMessgae.extraInfo &&
                 [cardMessgae.extraInfo isKindOfClass:[NSArray class]] &&
                 cardMessgae.extraInfo.count > 0)) {
                bottomCuttingLineH = 0.5f;
            }
            _bottomCuttingLineF = CGRectMake(14.f, CGRectGetMaxY(_priceF) + 9.f, CGRectGetWidth(_topCuttingLineF), bottomCuttingLineH);
            
            CGFloat transportTitleH = 0.f;
            CGFloat transportTitleY = CGRectGetMaxY(_bottomCuttingLineF);
            if (![kitUtils isBlankString:cardMessgae.status]) {
                transportTitleH = 18.f;
                transportTitleY = CGRectGetMaxY(_bottomCuttingLineF) + 5.f;
            }
            _transportTitleF = CGRectMake(14.f, transportTitleY, 61.f, transportTitleH);
            _transportStatusF = CGRectMake(CGRectGetMaxX(_transportTitleF), CGRectGetMinY(_transportTitleF), 140.f, CGRectGetHeight(_transportTitleF));
            
            CGFloat foldAndUnfoldBtnY = CGRectGetMaxY(_bottomCuttingLineF);
            CGFloat foldAndUnfoldBtnH = 0.f;
            CGFloat foldAndUnfoldIconH = 0.f;
            if (cardMessgae.extraInfo &&
                [cardMessgae.extraInfo isKindOfClass:[NSArray class]] &&
                cardMessgae.extraInfo.count > 3) {
                foldAndUnfoldBtnH = 30.f;
                foldAndUnfoldIconH = 16.f;
            }
            _foldAndUnfoldBtnF = CGRectMake(bottomViewW - 16.f - 14.f - 32.f, foldAndUnfoldBtnY, 32.f, foldAndUnfoldBtnH);
            _foldAndUnfoldIconF = CGRectMake(bottomViewW - 16.f - 14.f, CGRectGetCenter(_foldAndUnfoldBtnF).y - foldAndUnfoldIconH/2.f, 16.f, foldAndUnfoldIconH);
            
            CGFloat transportDetailsH = 0;
            if (cardMessgae.extraInfo &&
                [cardMessgae.extraInfo isKindOfClass:[NSArray class]]) {
                
                NSInteger extraInfoCount = cardMessgae.extraInfo.count;
                if (orderNumber.length) {
                    extraInfoCount -= 1;
                }
                
                if (extraInfoCount > 3 &&
                    _foldAndUnfold) {
                    transportDetailsH = 3 * 21.f;
                } else {
                    transportDetailsH = extraInfoCount * 21.f;
                }
            } else {
                transportDetailsH = 0.f;
            }
            
            CGFloat transportDetailsY = MAX(CGRectGetMaxY(_transportTitleF), CGRectGetMaxY(_foldAndUnfoldBtnF));
            if (transportDetailsY > 10.f) {
                transportDetailsY = transportDetailsY + 5.f;
            }
            
            _transportDetailsF = CGRectMake(14.f, transportDetailsY, bottomViewW - 14.f * 2, transportDetailsH);
            
            
            bottomViewH += CGRectGetMaxY(_transportDetailsF);
            
            CGSize chateLabelSize = CGSizeMake(_headNameF.origin.x - 14.f - bubblePadding * 2.f + arrowWidth, bottomViewH);
            
            CGSize bubbleSize     = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height);
            CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            _bubbleViewF          = CGRectMake(CGRectGetMinX(_headImageViewF) - headToBubble - bubbleSize.width, cellMargin+topViewH+topViewToBubbleH+topTimeViewH + sendNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            
            _topViewF             = CGRectMake(CGRectGetMinX(_headImageViewF) - timeShowLength , cellMargin+topTimeViewH,topViewSize.width,topViewSize.height);
            
            
            _bottomViewF = CGRectMake(14.f, _bubbleViewF.origin.y, bottomViewW, _bubbleViewF.size.height);
            _cellClickBtnF = CGRectMake(0, 0, CGRectGetWidth(_bottomViewF), CGRectGetHeight(_bottomViewF));
            
        } else if ([model.message.type isEqualToString:TypeCommodityCard]) {
            _bubbleViewF = CGRectMake(0, 0, APP_WIDTH, 114.f);
        } else if ([model.message.type isEqualToString:TypeRevoke]) { // 撤回消息
            
            [self revokeMessageFrameWithModel:model bubblePadding:bubblePadding cellMinW:cellMinW topViewH:topViewH headToBubble:headToBubble topTimeViewH:topTimeViewH arrowWidth:arrowWidth topViewToBubbleH:2 cellMargin:cellMargin timeShowLength:timeShowLength];
        } else if ([model.message.type isEqualToString:TypeEventQueue]) { // 排队消息
            if (![kitUtils isBlankString:model.message.content]) {
                
                _chatLabelF = CGRectMake(0, 0, 0, 0);
                /// 时间信息的高度
                CGFloat timeHieght = 0.0;
                /// 判断是否显示时间信息
                if (model.message.showTime) {
                    timeHieght = topTimeViewH;
                }
                /// 默认排队中
                NSString * leaveQueueStr = @" 放弃排队";
                /// 如果结束了会话，需要把放弃排队置空
                if ([TIMClient.sharedTIMClient getLibOnlineStatus] == TinetChatStatusTypeCloseChat) {
                    leaveQueueStr = @"";
                }
                
                /// 距离屏幕左右的间距+label内部文字的间距
                UIEdgeInsets edgeInsets = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets;
                UIEdgeInsets textEdgeInsets = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets;
                CGFloat edgeInset = edgeInsets.left+edgeInsets.right+textEdgeInsets.left+textEdgeInsets.right;
                
                /// ⚠️这里计算后，cellheight在下面还会计算一次，之前的逻辑，不知道是哪个业务涉及到的，所以这里改动计算方法，在下方也需要同步改动⚠️
                CGSize size = [[NSString stringWithFormat:@"%@%@", model.message.content, leaveQueueStr] tim_sizeForWithMaxWidth:[UIScreen mainScreen].bounds.size.width - edgeInset lineSpacing:2.0 withFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont];
                _chatLabelF.size.height = (int)size.height+textEdgeInsets.top+textEdgeInsets.bottom;
                _chatLabelF.size.width  = size.width+textEdgeInsets.left+textEdgeInsets.right;
                _chatLabelF.origin.x = (kScreenWidth-_chatLabelF.size.width)/2;
                _chatLabelF.origin.y = (edgeInsets.top + edgeInsets.bottom) * 0.5+timeHieght;
                _bubbleViewF = CGRectMake(0, 0, 0, size.height+edgeInsets.top + edgeInsets.bottom + timeHieght);// 只需要高度就行
            }
//            [self queueMessageFrameWithModel:model bubblePadding:bubblePadding cellMinW:cellMinW topViewH:topViewH headToBubble:headToBubble topTimeViewH:topTimeViewH arrowWidth:arrowWidth topViewToBubbleH:2+20 cellMargin:cellMargin timeShowLength:timeShowLength];
        } else if ([model.message.type isEqualToString:TypeCustomFile]) { // 自定义文件
            NSLog(@"mediaPath ==== %@",model.mediaPath);

            if (model.message.content) {
                CGSize chateLabelSize = CGSizeMake(247.f, 85.f);
                CGSize bubbleSize     = CGSizeMake(chateLabelSize.width, chateLabelSize.height);
                CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
                _bubbleViewF          = CGRectMake(CGRectGetMinX(_headImageViewF) - headToBubble - bubbleSize.width, cellMargin+topViewH+topViewToBubbleH+topTimeViewH + sendNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
                CGFloat x             = CGRectGetMinX(_bubbleViewF)+bubblePadding+8.f;
                _topViewF             = CGRectMake(CGRectGetMinX(_headImageViewF) - timeShowLength , cellMargin+topTimeViewH,topViewSize.width,topViewSize.height);
                _chatLabelF           = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + (bubbleSize.height -chateLabelSize.height) / 2, chateLabelSize.width, chateLabelSize.height);
                
                
                _custFilePictureF         = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + 8.f, 32.f, 32.f);
                
                CGFloat custFileTypeDescY = CGRectGetMinY(_bubbleViewF) + 12.0f;
                CGFloat custFileTypeDescW = _bubbleViewF.size.width - bubblePadding - 8.f - CGRectGetWidth(_custFilePictureF) - 13.f;
                _custFileTypeDescF = CGRectMake(CGRectGetMaxX(_custFilePictureF) + 4.0f, custFileTypeDescY, custFileTypeDescW, 24.f);
                
                _custFileNameF = CGRectMake(x, CGRectGetMaxY(_custFileTypeDescF) + 3.f, custFileTypeDescW, 17.f);
                
                _custFileTimeF = CGRectMake(CGRectGetMinX(_custFileTypeDescF), CGRectGetMaxY(_custFileTypeDescF) + 5.f, custFileTypeDescW, 16.f);
                
                _aduitRejectedViewF  = CGRectMake(CGRectGetMinX(_bubbleViewF), CGRectGetMaxY(_custFileTypeDescF) + 12.0f, bubbleSize.width, 36.f);
                
            }
        } else if ([model.message.type isEqualToString:TypeCustom]) { // 自定义消息
            if (model.message.content) {
                NSDictionary * contentDic = [kitUtils dictionaryWithJsonString:model.message.content];
                // 如果不是默认模板
                if (contentDic && [contentDic objectForKey:@"template"] &&
                    [contentDic[@"template"] isEqualToString:@"tim-rtcMedia"]) {
                    // TODO
                    // 获取stopAction
                    NSString * strStopAction = @"hangup";
                    if ([contentDic valueForKey:@"body"] && [contentDic[@"body"] objectForKey:@"stopAction"]) {
                        strStopAction = [kitUtils transferStopAction:contentDic[@"body"][@"stopAction"] isSender:model.isSender duration:[contentDic[@"body"][@"duration"] intValue]];
                    }
                    CGSize chateLabelSize = [strStopAction tim_sizeWithMaxWidth:chatLabelMax andFont:MessageFont];
                    chateLabelSize.width += 22; // 固定大小的图标
                    CGSize bubbleSize     = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + bubblePadding * 2);
                    CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
                    _bubbleViewF          = CGRectMake(CGRectGetMinX(_headImageViewF) - headToBubble - bubbleSize.width, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+sendNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
                    CGFloat x             = CGRectGetMinX(_bubbleViewF)+bubblePadding;
                    _topViewF             = CGRectMake(CGRectGetMinX(_headImageViewF) - timeShowLength , cellMargin+topTimeViewH,topViewSize.width,topViewSize.height); 
                    _chatLabelF           = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + (bubbleSize.height -chateLabelSize.height) / 2, chateLabelSize.width, chateLabelSize.height);
                } else {
                    CGSize chateLabelSize = CGSizeMake(240.f, 113.f);
                    CGSize bubbleSize     = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + bubblePadding * 2);
                    CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
                    _bubbleViewF          = CGRectMake(CGRectGetMinX(_headImageViewF) - headToBubble - bubbleSize.width, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+sendNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
                    CGFloat x             = CGRectGetMinX(_bubbleViewF)+bubblePadding;
                    _topViewF             = CGRectMake(CGRectGetMinX(_headImageViewF) - timeShowLength , cellMargin+topTimeViewH,topViewSize.width,topViewSize.height);
                    _chatLabelF           = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + (bubbleSize.height -chateLabelSize.height) / 2, chateLabelSize.width, chateLabelSize.height);
                    CGFloat customPadding = (bubbleSize.height -chateLabelSize.height) / 2;
                    _custShareTitleF      = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + customPadding, chateLabelSize.width, 27.f);
                    CGFloat custPicHeight = CGRectGetMaxY(_bubbleViewF) - CGRectGetMaxY(_custShareTitleF) - 2 * customPadding;
                    _custPictureF         = CGRectMake(x, CGRectGetMaxY(_custShareTitleF) + 1 + customPadding, custPicHeight, custPicHeight);
                    _custTitleF           = CGRectMake(CGRectGetMaxX(_custPictureF) + customPadding, CGRectGetMinY(_custPictureF), CGRectGetMaxX(_bubbleViewF)-CGRectGetMaxX(_custPictureF)- 2 *customPadding, _custPictureF.size.height / 3.f);
                    _custContentF         = _custTitleF;
                    _custContentF.origin.y  = CGRectGetMaxY(_custPictureF) - _custPictureF.size.height * 2.f / 3.f;
                    _custContentF.size.height  = _custPictureF.size.height * 2.f / 3.f;
                }
            }
        }else if ([model.message.type isEqualToString:TypePic]) { // 图片
            CGSize imageSize = CGSizeMake(model.picWidth?:150, model.picHeight?:150);
            float rateWH = (float)imageSize.width / imageSize.height;
            if (imageSize.width < imageSize.height) {
                imageSize.width = picMinWidth;
                imageSize.height = picMinWidth / rateWH;
                if (imageSize.height > picMaxWidth) {
                    // 如果太高 则限高
                    imageSize.height = picMaxWidth;
                    imageSize.width = picMaxWidth * rateWH;
                }
            }else{
                imageSize.height = picMinWidth;
                imageSize.width = picMinWidth * rateWH;
                if (imageSize.width > picMaxWidth) {
                    // 如果太宽 则限宽
                    imageSize.width = picMaxWidth;
                    imageSize.height = picMaxWidth / rateWH;
                }
            }
            if ([model.picType isEqualToString:kGIFTimageType]) {
                imageSize = CGSizeMake(80, 80);
            }
            
            CGSize topViewSize     = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            CGSize bubbleSize      = CGSizeMake(imageSize.width, imageSize.height);
            CGFloat bubbleX        = CGRectGetMinX(_headImageViewF)-headToBubble-bubbleSize.width;
            _bubbleViewF           = CGRectMake(bubbleX, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+sendNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGFloat x              = CGRectGetMinX(_bubbleViewF);
            _topViewF             = CGRectMake(CGRectGetMinX(_headImageViewF) - timeShowLength, cellMargin+topTimeViewH,topViewSize.width,topViewSize.height);
            _picViewF              = CGRectMake(x, cellMargin+topViewH+topTimeViewH +topViewToBubbleH+sendNickNameToBubbleSpacing, imageSize.width, imageSize.height);
            
            CGSize chateLabelSize = [@"待审核" tim_sizeWithMaxWidth:chatLabelMax andFont:MessageFont12];
            _aduitStatusViewF      = CGRectMake(x - chateLabelSize.width-bubblePadding, topViewH+bubbleSize.height-chateLabelSize.height+topTimeViewH, chateLabelSize.width+bubblePadding, chateLabelSize.height+bubblePadding);
        } else if ([model.message.type isEqualToString:TypeVoice]) { // 语音消息
            CGFloat bubbleViewW     = voiceMinW;
            
            long addbubbleViewLength = APP_WIDTH - (APP_WIDTH - CGRectGetMinX(_headImageViewF)) * 2 - voiceMinW; // 追增长度最大值
            long needAddWidth = 5 * [model.message.voiceDuration longValue] / 1000;
            if (needAddWidth > addbubbleViewLength) {
                needAddWidth = addbubbleViewLength;
            }
            TIMKitLog(@"needAddWidth = %ld",needAddWidth);
            _bubbleViewF = CGRectMake(CGRectGetMinX(_headImageViewF) - headToBubble - bubbleViewW - needAddWidth, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+sendNickNameToBubbleSpacing, bubbleViewW + needAddWidth, 40);
            CGSize topViewSize     = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            _topViewF               = CGRectMake(CGRectGetMinX(_headImageViewF) - timeShowLength , cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
            //            _durationLabelF         = CGRectMake(CGRectGetMinX(_bubbleViewF)+ bubblePadding , cellMargin + 10+topViewH+topTimeViewH+topViewToBubbleH, 50, 20);
            //            _voiceIconF = CGRectMake(CGRectGetMaxX(_bubbleViewF) - 22, cellMargin + 10 + topViewH+topTimeViewH +topViewToBubbleH, 13, 20);// - 20
            _durationLabelF         = CGRectMake(CGRectGetMinX(_bubbleViewF)+ [TOSKitCustomInfo shareCustomInfo].chat_send_voiceLabelToBubbleLeftX , cellMargin + [TOSKitCustomInfo shareCustomInfo].chat_send_voiceLabelToBubbleTop+topViewH+topTimeViewH+topViewToBubbleH+sendNickNameToBubbleSpacing, 50, 20);
            _voiceIconF = CGRectMake(CGRectGetMaxX(_bubbleViewF) - [TOSKitCustomInfo shareCustomInfo].chat_send_voiceImageToBubbleRightX, cellMargin + [TOSKitCustomInfo shareCustomInfo].chat_send_voiceImageToBubbleTop + topViewH+topTimeViewH +topViewToBubbleH+sendNickNameToBubbleSpacing, 13, 20);// - 20
        }  else if ([model.message.type isEqualToString:TypeVideo]) { // 视频信息
            CGSize imageSize = CGSizeMake(model.picWidth?:150, model.picHeight?:150);
            float rateWH = (float)imageSize.width / imageSize.height;
            if(imageSize.width < imageSize.height){
                imageSize.width = picMinWidth;
                imageSize.height = picMinWidth / rateWH;
            }else {
                imageSize.height = picMinWidth;
                imageSize.width = picMinWidth * rateWH;
            }
            UIImage *videoImage = [[ICMediaManager sharedManager] videoImageWithFileName:model.mediaPath.lastPathComponent];
            if (!videoImage) {
                NSString *path          = [[ICVideoManager shareManager] receiveVideoPathWithFileKey:[model.mediaPath.lastPathComponent stringByDeletingPathExtension]];
                videoImage    = [UIImage videoFramerateWithPath:path];
            }
            if (videoImage) {
                float scale        = (float)videoImage.size.height/videoImage.size.width;
                imageSize = CGSizeMake(150, 140*scale);
            }else{
                float scale        = (float)imageSize.height/imageSize.width;
                imageSize = CGSizeMake(150, 140*scale);
            }
            CGSize bubbleSize = CGSizeMake(imageSize.width, imageSize.height);
            _bubbleViewF = CGRectMake(CGRectGetMinX(_headImageViewF)-headToBubble-bubbleSize.width, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+sendNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGSize topViewSize     = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            CGFloat x              = CGRectGetMinX(_bubbleViewF);
            _topViewF              = CGRectMake(CGRectGetMinX(_headImageViewF) - timeShowLength , cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
            _picViewF              = CGRectMake(x, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+sendNickNameToBubbleSpacing, imageSize.width, imageSize.height);
            
        } else if ([model.message.type isEqualToString:TypeFile]) {
            CGSize bubbleSize = CGSizeMake(253, 95.0);
            _bubbleViewF = CGRectMake(CGRectGetMinX(_headImageViewF)-headToBubble-bubbleSize.width, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+sendNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGSize topViewSize     = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            CGFloat x              = CGRectGetMinX(_bubbleViewF);
            _topViewF              = CGRectMake(CGRectGetMinX(_headImageViewF) - timeShowLength , cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
            _picViewF              = CGRectMake(x, cellMargin+topViewH+topTimeViewH+sendNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
        } else if ([model.message.type isEqualToString:TypePicText]) {
            CGSize bubbleSize = CGSizeMake(253, 120.0);
            _bubbleViewF = CGRectMake(CGRectGetMinX(_headImageViewF)-headToBubble-bubbleSize.width, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+sendNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGSize topViewSize     = CGSizeMake(bubbleSize.width-arrowWidth, topViewH);
            CGFloat x              = CGRectGetMinX(_bubbleViewF);
            _topViewF              = CGRectMake(CGRectGetMinX(_headImageViewF) - timeShowLength , cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
            _picViewF              = CGRectMake(x, cellMargin+topViewH+topTimeViewH+sendNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
        }
        CGFloat activityX = _bubbleViewF.origin.x-[TOSKitCustomInfo shareCustomInfo].resendButtonSize.width-[TOSKitCustomInfo shareCustomInfo].resendToBubblePadding;
        
        CGFloat activityY = CGRectGetCenter(_bubbleViewF).y - 10.f;
        //        if (model.message.showTime) {
        //            activityY = (_bubbleViewF.origin.y + _bubbleViewF.size.height + topTimeViewH)/2;
        //        } else {
        //            activityY = (_bubbleViewF.origin.y + _bubbleViewF.size.height)/2;
        //        }
        
        CGFloat activityW = [TOSKitCustomInfo shareCustomInfo].resendButtonSize.width;
        CGFloat activityH = [TOSKitCustomInfo shareCustomInfo].resendButtonSize.height;
        _activityF        = CGRectMake(activityX, activityY, activityW, activityH);
        _retryButtonF     = _activityF;
        
    } else {    // 接收者
        if (![TOSKitCustomInfo shareCustomInfo].Chat_tosRobot_portrait_enable) {
            headWidth = 0;
        }
        _headImageViewF   = CGRectMake(headToView, cellMargin+topTimeViewH, headWidth, headWidth);
        
        if ([TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_enable) {
            //            NSString * strName = [kitUtils isBlankString:model.message.senderName]?@"":model.message.senderName;
            NSString * strName = [[ICMediaManager sharedManager] getHeadNameWithUserId:model.message.from];
            NSLog(@"机器人昵称：%@", strName);
            if (!strName.length) {
                strName = model.message.senderType.integerValue == 4 ? @"机器人" : @"客服";
            }
            CGSize nameSize = [strName tim_sizeWithMaxWidth:(APP_WIDTH - headToView - headWidth - 7 - 5) andFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotName_font];
            topViewToBubbleH = nameSize.height;
            _headNameF = CGRectMake(headToView + headWidth + 7, cellMargin+topTimeViewH, APP_WIDTH - headToView - headWidth - 7 - 5, nameSize.height); //5为名字右侧边距
        } else {
            _headNameF = CGRectZero;
            topViewToBubbleH = 0;
        }
        
        
        CGFloat bubbleToNameH = 5;  // 群聊接收时 名称与内容的间隔
        
        topViewH = 0;
        bubbleToNameH = 0;
        
        CGSize nameSize       = CGSizeMake(0, 0);
        cellMinW = nameSize.width + 6 + timeSize.width; // 最小宽度
        if ([model.message.type isEqualToString:TypeText] ||           // 文本
            [model.message.type isEqualToString:TypeUnsupport]) { // 未知类型消息
            
            UIFont *font = [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font;
            NSMutableAttributedString *attributedString = [model.message.content transferMessageFont:font
                                                                                           foreColor:[self setTextLabelColor:model.isSender]
                                                                                             fontDic:nil
                                                                                          lineHeight:font.lineHeight
                                                                                    filteredLineFeed:YES];
            model.attributedString = attributedString;
            
            CGSize chateLabelSize = [model.message.content tim_sizeWithMaxWidth:chatLabelMax attributeStr:attributedString];
            
            
            chateLabelSize.width += 2.f;
            CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            CGSize bubbleSize = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + bubblePadding * 2);
            
            _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            
            CGFloat x     = CGRectGetMinX(_bubbleViewF) + bubblePadding + arrowWidth;
            _topViewF     = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
            _chatLabelF   = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + (bubbleSize.height -chateLabelSize.height) / 2, chateLabelSize.width, chateLabelSize.height);
        } else if ([model.message.type isEqualToString:TypeTextTag]) {
            
            TOSMessageTextTagModel *textTagModel = (TOSMessageTextTagModel *)model.message.content;
            
            UIFont *font = [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font;
            NSMutableAttributedString *attributedString = [textTagModel.text transferMessageFont:font
                                                                                       foreColor:[self setTextLabelColor:model.isSender]
                                                                                         fontDic:nil
                                                                                      lineHeight:font.lineHeight
                                                                                filteredLineFeed:YES];
            model.attributedString = attributedString;
            
            CGSize chateLabelSize = [textTagModel.text tim_sizeWithMaxWidth:chatLabelMax attributeStr:attributedString];
            
            
            chateLabelSize.width += 2.f;
            CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            CGSize bubbleSize = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + bubblePadding * 2);
            
            TTGTextTagCollectionView *tagCollectionView = [[TTGTextTagCollectionView alloc] initWithFrame:CGRectMake(0, 0, chatLabelMax, 10.f)];
            [self setupTTGTextTagView:tagCollectionView];
            
            NSMutableArray *textTags = [NSMutableArray new];
            [textTagModel.tags enumerateObjectsUsingBlock:^(TOSMessageTextSubTagModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                
                TTGTextTagStringContent *tagContent = [self setupTTGTextTagContent];
                if (obj.text &&
                    ![kitUtils isBlankString:obj.text] &&
                    obj.text.length > 6) {
                    NSRange range = {0, 5};
                    tagContent.text = [NSString stringWithFormat:@"%@...",[obj.text substringWithRange:range]];
                } else {
                    tagContent.text = obj.text;
                }
                TTGTextTag *textTag = [TTGTextTag tagWithContent:tagContent style:[self setupTTGTextTagStyle]];
                [textTags addObject:textTag];
                
                if (idx >= 7) {
                    *stop = YES;
                }
            }];
            [tagCollectionView addTags:textTags];
            
            
            _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height + tagCollectionView.contentSize.height);
            
            _textTagBubbleViewF = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH, bubbleSize.width, bubbleSize.height);
            
            _textTagViewF = CGRectMake(CGRectGetMinX(_textTagBubbleViewF), CGRectGetMaxY(_textTagBubbleViewF), chatLabelMax, tagCollectionView.contentSize.height);
            
            CGFloat x     = CGRectGetMinX(_textTagBubbleViewF) + bubblePadding + arrowWidth;
            _topViewF     = CGRectMake(CGRectGetMinX(_textTagBubbleViewF), cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
            _chatLabelF   = CGRectMake(x, CGRectGetMinY(_textTagBubbleViewF) + (bubbleSize.height -chateLabelSize.height) / 2, chateLabelSize.width, chateLabelSize.height);
        } else if ([model.message.type isEqualToString:TypeRobotCombination]) {
            
            NSArray <CombinationMessage *>*array = (NSArray *)model.message.content;
            
            __block CGFloat contentHeight = 0;
            __block CGFloat contentWidth = 0;
            [array enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                if ([obj.type isEqualToString:@"2"] ||
                    [obj.type isEqualToString:@"4"]) {
                    obj.contentF = CGRectMake(0, 0.f, chatLabelMax, chatLabelMax);
                    contentHeight += chatLabelMax;
                    if (contentWidth < chatLabelMax) {
                        contentWidth = chatLabelMax;
                    }
                } else if ([obj.type isEqualToString:@"7"]) {
                    
                    obj.contentF = CGRectMake(0, 0.f, chatLabelMax, 40.f);
                    contentHeight += 40.f;
                    if (contentWidth < chatLabelMax) {
                        contentWidth = chatLabelMax;
                    }
                } else if ([obj.type isEqualToString:@"5"] ||
                           [obj.type isEqualToString:@"36"]) {
                    
                    obj.richTextMessage = [[RichTextMessage alloc] initMessageWithContent:obj.text];
                    
                    __block CGFloat richContentHeight = 0;
                    __block CGFloat richContentWidth = 0;
                    
                    [obj.richTextMessage.elements enumerateObjectsUsingBlock:^(RichTextMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                        
                        if ([kitUtils isTextTypeLabel:obj.type]) {
                            
                            if ([obj.type isEqualToString:@"a"] ||
                                [obj.type isEqualToString:@"knowledge"]) {
                                
                            } else {
                                obj.attributedString = [self transferMessageString:obj.content?:@"" font:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font foreColor:[TOSKitCustomInfo shareCustomInfo].receiveText_Color lineHeight:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font.lineHeight];
                            }
                            
                            NSMutableArray *fontDic = [NSMutableArray array];
                            [obj.subElements enumerateObjectsUsingBlock:^(RichTextMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                                if ([obj.type isEqualToString:@"span"] &&
                                    [obj.style hasPrefix:@"font-size"]) {
                                    
                                    NSString *styleStr = [obj.style stringByReplacingOccurrencesOfString:@"font-size: " withString:@""];
                                    NSString *sizeStr = [styleStr stringByReplacingOccurrencesOfString:@"px;" withString:@""];
                                    [fontDic addObject:@{@"content": obj.content?:@"", @"font": [UIFont fontWithName:@"PingFangSC-Regular" size:sizeStr.doubleValue]}];
                                }
                            }];
                            
                            //                            CGSize chateLabelSize = [obj.content sizeWithMaxWidth:chatLabelMax andFont:MessageFont fontDic:fontDic];
                            //                            CGSize chateLabelSize = [obj.content sizeWithMaxWidth:chatLabelMax andFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font fontDic:fontDic];
                            
                            UIFont *font = [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font;
//                            NSMutableAttributedString *attributedString = [obj.content transferMessageFont:font
//                                                                                                 foreColor:[self setTextLabelColor:model.isSender]
//                                                                                                   fontDic:fontDic
//                                                                                                lineHeight:font.lineHeight
//                                                                                          filteredLineFeed:YES];
                            CGSize chateLabelSize = [obj.content tim_sizeWithMaxWidth:chatLabelMax attributeStr:obj.attributedString];
                            
                            obj.contentF = CGRectMake(0, 0, chateLabelSize.width, chateLabelSize.height);
                            richContentHeight += chateLabelSize.height;
                            
                            if (richContentWidth < chateLabelSize.width) {
                                richContentWidth = chateLabelSize.width;
                            }
                        } else {
                            
                            if ([obj.type isEqualToString:@"tr"] ||
                                [obj.type isEqualToString:@"h1"] ||
                                [obj.type isEqualToString:@"h2"] ||
                                [obj.type isEqualToString:@"h3"] ||
                                [obj.type isEqualToString:@"h4"] ||
                                [obj.type isEqualToString:@"h5"] ||
                                [obj.type isEqualToString:@"h6"]) {
                                
                                //                                CGSize chateLabelSize = [obj.content sizeWithMaxWidth:chatLabelMax andFont:MessageFont];
                                obj.attributedString = [self transferMessageString:obj.content?:@"" font:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font foreColor:[TOSKitCustomInfo shareCustomInfo].receiveText_Color lineHeight:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font.lineHeight];
                                
                                CGSize chateLabelSize = [obj.content tim_sizeWithMaxWidth:chatLabelMax attributeStr:obj.attributedString];
                                
                                obj.contentF = CGRectMake(0, 0, chateLabelSize.width, chateLabelSize.height);
                                richContentHeight += chateLabelSize.height;
                                
                                if (richContentWidth < chateLabelSize.width) {
                                    richContentWidth = chateLabelSize.width;
                                }
                            } else if ([obj.type isEqualToString:@"ul"] ||
                                       [obj.type isEqualToString:@"ol"]) {
                                
                                obj.attributedString = [self transferMessageString:obj.content?:@"" font:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font foreColor:[TOSKitCustomInfo shareCustomInfo].receiveText_Color lineHeight:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font.lineHeight];
                                
                                CGFloat olChatLabelMax = chatLabelMax;
                                if ([obj.type isEqualToString:@"ul"]) {
                                    olChatLabelMax -= 20.f;
                                }
                                
                                //                                CGSize chateLabelSize = [obj.content sizeWithMaxWidth:olChatLabelMax andFont:MessageFont];
                                CGSize chateLabelSize = [obj.content tim_sizeWithMaxWidth:olChatLabelMax attributeStr:obj.attributedString];
                                
                                obj.contentF = CGRectMake(0, 0, chateLabelSize.width, chateLabelSize.height);
                                richContentHeight += chateLabelSize.height;
                                
                                if (richContentWidth < chateLabelSize.width) {
                                    richContentWidth = chateLabelSize.width;
                                }
                            } else if ([obj.type isEqualToString:@"img"] ||
                                       [obj.type isEqualToString:@"video"]) {
                                
                                obj.contentF = CGRectMake(0, 0, chatLabelMax, chatLabelMax);
                                richContentHeight += chatLabelMax;
                                if (richContentWidth < chatLabelMax) {
                                    richContentWidth = chatLabelMax;
                                }
                            }
                        }
                    }];
                    
                    CGSize chateLabelSize = CGSizeMake(richContentWidth, richContentHeight);
                    
                    obj.contentF = CGRectMake(0, 0, chateLabelSize.width, chateLabelSize.height);
                    contentHeight += chateLabelSize.height;
                    
                    if (contentWidth < chateLabelSize.width) {
                        contentWidth = chateLabelSize.width;
                    }
                    
                    
                } else if ([obj.type isEqualToString:@"3"]) {
                    CGSize chateLabelSize = CGSizeMake(247.f, 85.f);
                    obj.contentF = CGRectMake(0, 0.f, chateLabelSize.width, chateLabelSize.height);
                    contentHeight += chateLabelSize.height;
                    
                    if (contentWidth < chateLabelSize.width) {
                        contentWidth = chateLabelSize.width;
                    }
                } else if ([obj.type isEqualToString:@"6"] ||
                           [obj.type isEqualToString:@"15"] ||
                           [obj.type isEqualToString:@"16"] ||
                           [obj.type isEqualToString:@"17"] ||
                           [obj.type isEqualToString:@"18"] ||
                           [obj.type isEqualToString:@"19"] ||
                           [obj.type isEqualToString:@"20"] ||
                           [obj.type isEqualToString:@"31"]) {
                    
                    __block CGSize chateLabelSize = CGSizeMake(chatLabelMax, 0.f);
                    __block CGFloat tableViewHeight = 0.f;
                    chateLabelSize.height += 24.f;//热点问题基础高度 48.f - 24.f
                    
                    BOOL hiddenRefresh = NO;
                    
                    CGFloat titleMaxWidth = chateLabelSize.width;
                    
                    //                    CGFloat titleTotalWidth = [obj.text?:@"" contentWidthWithFont:[UIFont fontWithName:@"PingFangSC-Medium" size:16.f] height:24.f];
                    CGFloat titleTotalWidth = [obj.text?:@"" tim_sizeWithMaxWidth:chatLabelMax andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_titleFont].width;
                    CGFloat titleWidth = titleTotalWidth >= titleMaxWidth ? titleMaxWidth : titleTotalWidth;
                    
                    //                    CGFloat titleTotalHeight = [obj.text?:@"" contentHeightWithFont:[UIFont fontWithName:@"PingFangSC-Medium" size:16.f] width:titleWidth];
                    CGFloat titleTotalHeight = [obj.text?:@"" tim_sizeWithMaxWidth:chatLabelMax andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_titleFont].height;
                    
                    obj.refreshBtnSize = [TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitle tim_sizeWithMaxWidth:chatLabelMax andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitleFont];;
                    
                    CGFloat titleHeight = titleTotalHeight >= 24.f ? titleTotalHeight : 24.f;
                    
                    _hotIssueTitleF = CGRectMake(0, 0.f, titleWidth, titleHeight);
                    
                    chateLabelSize.height += _hotIssueTitleF.size.height;
                    
                    if ([obj.type isEqualToString:@"31"]) {
                        
                        NSArray <NSString *>* item = [NSMutableArray array];
                        if (obj.data &&
                            [obj.data isKindOfClass:[NSArray class]] &&
                            obj.data.count > 0) {
                            
                            NSMutableArray <NSNumber *>*hotListTypeTitleH = [NSMutableArray array];
                            
                            [obj.data enumerateObjectsUsingBlock:^(CombinationDataModel * _Nonnull dataModel, NSUInteger idx, BOOL * _Nonnull stop) {
                                
                                NSString *name = [NSString stringWithFormat:@"%@",dataModel.name?:@""];
                                //                                CGSize labelSize = [name sizeWithMaxWidth:chateLabelSize.width andFont:[UIFont fontWithName:@"PingFangSC-Regular" size:14.f]];
                                CGSize labelSize = [name tim_sizeWithMaxWidth:chateLabelSize.width andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssueTitleFont];
                                //                                if (labelSize.height <= 22.f) {
                                //                                    chateLabelSize.height += 38.f;
                                //                                    tableViewHeight += 38.f;
                                //                                    [hotListTypeTitleH addObject:[NSNumber numberWithFloat:38.f]];
                                //                                } else {
                                //                                    chateLabelSize.height += 60.f;
                                //                                    tableViewHeight += 60.f;
                                //                                    [hotListTypeTitleH addObject:[NSNumber numberWithFloat:60.f]];
                                //                                }
                                chateLabelSize.height += labelSize.height+TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssusSpacing;
                                tableViewHeight += labelSize.height+TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssusSpacing;
                                [hotListTypeTitleH addObject:[NSNumber numberWithFloat:labelSize.height+TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssusSpacing]];
                            }];
                            
                            obj.hotListTypeTitleH = [NSArray arrayWithArray:hotListTypeTitleH];
                            
                            if (obj.selectData < 0) {
                                obj.hotListTypeSubIssueH = @[@(0),@(0),@(0),@(0),@(0)];
                                hiddenRefresh = YES;
                            } else {
                                CombinationDataModel *dataObj = obj.data[obj.selectData?:0];
                                
                                item = [self getSubIssueItem:dataObj.topics
                                                  selectPage:dataObj.selectPageData];//obj.selectPageData];
                                
                                if (dataObj.topics.count <= TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber) {
                                    hiddenRefresh = YES;
                                } else {
                                    hiddenRefresh = NO;
                                }
                                
                                NSMutableArray <NSNumber *>*itemH = [NSMutableArray array];
                                [item enumerateObjectsUsingBlock:^(NSString * _Nonnull text, NSUInteger idx, BOOL * _Nonnull stop) {
                                    //                                    CGSize labelSize = [[NSString stringWithFormat:@"%ld.%@",idx+1,text] sizeWithMaxWidth:chateLabelSize.width andFont:[UIFont fontWithName:@"PingFangSC-Regular" size:14.f]];
                                    CGSize labelSize = [[NSString stringWithFormat:@"%ld.%@",idx+1,text] tim_sizeWithMaxWidth:chateLabelSize.width andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssueTitleFont];
                                    //                                    if (labelSize.height <= 22.f) {
                                    //                                        chateLabelSize.height += 30.f;
                                    //                                        tableViewHeight += 30.f;
                                    //                                        [itemH addObject:[NSNumber numberWithFloat:30.f]];
                                    //                                    } else {
                                    //                                        chateLabelSize.height += 52.f;
                                    //                                        tableViewHeight += 52.f;
                                    //                                        [itemH addObject:[NSNumber numberWithFloat:52.f]];
                                    //                                    }
                                    chateLabelSize.height += labelSize.height+TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssusSpacing;
                                    tableViewHeight += labelSize.height+TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssusSpacing;
                                    [itemH addObject:[NSNumber numberWithFloat:labelSize.height+TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssusSpacing]];
                                }];
                                obj.hotListTypeSubIssueH = [NSArray arrayWithArray:itemH];
                            }
                            
                            if (!hiddenRefresh) {
                                /// 通过文案及字体大小计算文本所占大小
                                CGSize refreshBtnSize = [TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitle tim_sizeWithMaxWidth:chatLabelMax andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitleFont];
                                //                                tableViewHeight += 18.f;
                                //                                chateLabelSize.height += 18.f;//换一换按钮高度
                                tableViewHeight += refreshBtnSize.height;
                                chateLabelSize.height += refreshBtnSize.height;//换一换按钮高度
                            }
                        }
                    } else {
                        NSArray <NSString *>* item = [NSMutableArray array];
                        if (obj.cards &&
                            [obj.cards isKindOfClass:[NSArray class]] &&
                            obj.cards.count > 0) {
                            
                            item = [self getSubIssueItem:obj.cards
                                              selectPage:obj.selectPageData];
                            
                            if (obj.cards.count <= TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber) {
                                hiddenRefresh = YES;
                            } else {
                                hiddenRefresh = NO;
                            }
                        } else if (obj.data &&
                                   [obj.data isKindOfClass:[NSArray class]] &&
                                   obj.data.count > 0) {
                            __block CGFloat segmentHeight = 29.0f;
                            __block CGFloat newSegmentHeight = 29.0f;
                            [obj.data enumerateObjectsUsingBlock:^(CombinationDataModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                                if (idx == 0) {
                                    segmentHeight = [obj.name tim_sizeWithMaxWidth:chatLabelMax andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentFont].height;
                                    
                                    newSegmentHeight = [obj.name tim_sizeWithMaxWidth:chateLabelSize.width andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentFont].height;
                                    
                                    * stop = YES;
                                }
                            }];
                            chateLabelSize.height += MAX(segmentHeight, 29.0);//segmentC 固定高度 29.f;
                            obj.segmentHeight = MAX(newSegmentHeight, 29.0);
                            //                            chateLabelSize.height += 29.f;//segmentC 固定高度 29.f;
                            CombinationDataModel *dataObj = obj.data[obj.selectData?:0];
                            
                            item = [self getSubIssueItem:dataObj.topics
                                              selectPage:dataObj.selectPageData];//obj.selectPageData];
                            
                            if (dataObj.topics.count <= TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber) {
                                hiddenRefresh = YES;
                            } else {
                                hiddenRefresh = NO;
                            }
                        }
                        
                        NSMutableArray <NSNumber *>*itemH = [NSMutableArray array];
                        [item enumerateObjectsUsingBlock:^(NSString * _Nonnull text, NSUInteger idx, BOOL * _Nonnull stop) {
                            //                            CGSize labelSize = [[NSString stringWithFormat:@"%ld.%@",idx+1,text] sizeWithMaxWidth:chateLabelSize.width andFont:[UIFont fontWithName:@"PingFangSC-Regular" size:14.0]];
                            CGSize labelSize = [[NSString stringWithFormat:@"%ld.%@",idx+1,text] tim_sizeWithMaxWidth:chateLabelSize.width andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssueTitleFont];
                            //                            if (labelSize.height <= 22.f) {
                            //                                chateLabelSize.height += 38.f;
                            //                                tableViewHeight += 38.f;
                            //                                [itemH addObject:[NSNumber numberWithFloat:38.f]];
                            //                            } else {
                            //                                chateLabelSize.height += 60.f;
                            //                                tableViewHeight += 60.f;
                            //                                [itemH addObject:[NSNumber numberWithFloat:60.f]];
                            //                            }
                            chateLabelSize.height += labelSize.height+TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssusSpacing;
                            tableViewHeight += labelSize.height+TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssusSpacing;
                            [itemH addObject:[NSNumber numberWithFloat:labelSize.height+TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssusSpacing]];
                            if (idx == TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber-1) {
                                *stop = YES;
                            }
                            
                        }];
                        obj.hotSubIssueH = [NSArray arrayWithArray:itemH];
                        
                        if (!hiddenRefresh) {
                            /// 通过文案及字体大小计算文本所占大小
                            CGSize refreshBtnSize = [TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitle tim_sizeWithMaxWidth:chatLabelMax andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitleFont];
                            _refreshBtnY = chateLabelSize.height;
                            _refreshIconY = chateLabelSize.height + 2.f;
                            //                            chateLabelSize.height += 18.f;//换一换按钮高度
                            chateLabelSize.height += refreshBtnSize.height;
                        }
                    }
                    
                    contentHeight += chateLabelSize.height;
                    if (contentWidth < chatLabelMax) {
                        contentWidth = chatLabelMax;
                    }
                    obj.tableViewH = tableViewHeight;
                    
                    obj.contentF = CGRectMake(0, 0, chateLabelSize.width, chateLabelSize.height);
                }
            }];
            
            __block CGFloat helpfulAndUnHelpfulContentHeight = contentHeight;
            
            [array enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                
                if (obj.clickLikeFeature) {
                    
                    helpfulAndUnHelpfulContentHeight = contentHeight;
                    
                    if (![kitUtils isBlankString:obj.answerSource]) {
                        
                        CAShapeLayer *dashedLineLayer = [CAShapeLayer layer];
                        dashedLineLayer.strokeColor = TOSHexColor(0xF0F0F0).CGColor;  // 虚线的颜色
                        dashedLineLayer.fillColor = [UIColor clearColor].CGColor;    // 填充色，一般设为透明

                        // 设置线宽
                        dashedLineLayer.lineWidth = 1.0;

                        // 设置虚线的间隔与长度
                        dashedLineLayer.lineDashPattern = @[@4, @2];  // 2 为实线长度，2 为间隔长度

                        // 使用 UIBezierPath 创建路径
                        UIBezierPath *path = [UIBezierPath bezierPath];
                        [path moveToPoint:CGPointMake(0, 0)]; // 虚线的起始点
                        [path addLineToPoint:CGPointMake(chatLabelMax, 0)]; // 虚线的结束点

                        // 关联路径到 CAShapeLayer
                        dashedLineLayer.path = path.CGPath;
                        
                        obj.answerSourceDashedLine = dashedLineLayer;
                        
                        
                        // 创建一个 NSMutableAttributedString 来处理不同字体
                        NSMutableAttributedString *answerSourceAtt = [[NSMutableAttributedString alloc] initWithString:[NSString stringWithFormat:@"参考来源: %@",obj.answerSource]];
                        
                        // 设置 "参考来源: " 的字体和颜色
                        [answerSourceAtt addAttribute:NSFontAttributeName
                                               value:[UIFont fontWithName:@"PingFangSC-Medium" size:14.f]
                                               range:NSMakeRange(0, 5)]; // "参考来源: " 的长度为 5
                        [answerSourceAtt addAttribute:NSForegroundColorAttributeName
                                               value:TOSHexColor(0x8C8C8C)
                                               range:NSMakeRange(0, 5)];

                        // 设置 "这是其他文字" 的字体和颜色
                        [answerSourceAtt addAttribute:NSFontAttributeName
                                               value:[UIFont fontWithName:@"PingFangSC-Regular" size:14.f]
                                               range:NSMakeRange(5, answerSourceAtt.length - 5)]; // 剩余部分的长度
                        [answerSourceAtt addAttribute:NSForegroundColorAttributeName
                                               value:TOSHexColor(0xBFBFBF)
                                               range:NSMakeRange(5, answerSourceAtt.length - 5)];
                        
                        CGSize chateLabelSize = [@"" tim_sizeWithMaxWidth:chatLabelMax attributeStr:answerSourceAtt];
                        CGFloat answerSourceH = chateLabelSize.height <= 22.f ? 22.f : 44.f;
                        obj.answerSourceDashedLineF = CGRectMake(0.f, helpfulAndUnHelpfulContentHeight + 8.f, chatLabelMax, 1.f);
                        helpfulAndUnHelpfulContentHeight += 1.f;
                        helpfulAndUnHelpfulContentHeight += 8.f;
                        obj.answerSourceF = CGRectMake(0.f, helpfulAndUnHelpfulContentHeight + 8.f, chateLabelSize.width, answerSourceH);
                        obj.answerSourceAtt = answerSourceAtt;
                        helpfulAndUnHelpfulContentHeight += answerSourceH;
                        helpfulAndUnHelpfulContentHeight += 16.f;
                        obj.showAnswerSource = YES;
                    }
                    
                    obj.helpfulAndUnHelpfulContentF = CGRectMake(0, helpfulAndUnHelpfulContentHeight, chatLabelMax, 31.f);
                    CGFloat helpfulBtnW = obj.helpfulAndUnHelpfulContentF.size.width/2;
                    obj.helpfulBtnF = CGRectMake(0.f, 9.f, helpfulBtnW, 22.f);
                    obj.helpfulAndUnHelpfulLineF = CGRectMake(helpfulBtnW, 11.f, 1.f, 18.f);
                    obj.unHelpfulBtnF = CGRectMake(helpfulBtnW, 9.f, helpfulBtnW, 22.f);
                    
                    helpfulAndUnHelpfulContentHeight += obj.helpfulAndUnHelpfulContentF.size.height;
                    
                    CGFloat UnHelpfulTagMaxY = 0.f;
                    
                    if (obj.helpfulSelectType == HelpfulAndUnHelpfulSelectType_UnHelpful &&
                        !obj.closeClick &&
                        [TOSKitCustomInfo shareCustomInfo].setUnHelpfulTagList &&
                        ([TOSKitCustomInfo shareCustomInfo].setUnHelpfulTagList.count > 0 ||
                        [TOSKitCustomInfo shareCustomInfo].isShowUnHelpfulContent)) {
                        
                        UnHelpfulTagMaxY += 9.f;
                        
                        if ([TOSKitCustomInfo shareCustomInfo].setUnHelpfulTagList &&
                            [TOSKitCustomInfo shareCustomInfo].setUnHelpfulTagList.count > 0) {
                            NSInteger unHelpfulTagLineNum = [TOSKitCustomInfo shareCustomInfo].setUnHelpfulTagList.count / 2 + [TOSKitCustomInfo shareCustomInfo].setUnHelpfulTagList.count % 2;
                            UnHelpfulTagMaxY += 12.f;
                            UnHelpfulTagMaxY += (unHelpfulTagLineNum * 24.f + ((unHelpfulTagLineNum - 1) * 8.f));
                            
                            obj.unHelpfulTagsF = [NSMutableArray array];
                            
                            CGFloat tagW = (chatLabelMax - 8.f) / 2;
                            [[TOSKitCustomInfo shareCustomInfo].setUnHelpfulTagList enumerateObjectsUsingBlock:^(NSString * _Nonnull content, NSUInteger idx, BOOL * _Nonnull stop) {
                                CGFloat tagX = idx % 2 * (tagW + 8.f);
                                CGFloat tagY = 9.f + 12.f + (idx/2) * (24.f + 8.f);
                                [obj.unHelpfulTagsF addObject:[NSValue valueWithCGRect:CGRectMake(tagX, tagY, tagW, 24.f)]];
                            }];
                        }
                        
                        CGFloat unHelpfulTextViewMaxY = UnHelpfulTagMaxY;
                        if ([TOSKitCustomInfo shareCustomInfo].isShowUnHelpfulContent) {
                            obj.unHelpfulTextViewF = CGRectMake(0, unHelpfulTextViewMaxY + 12.f, chatLabelMax, 100.f);
                            
                            unHelpfulTextViewMaxY += 12.f;
                            unHelpfulTextViewMaxY += 100.f;
                        }
                        
                        if (obj.showRequiredWarning) {
                            obj.requiredWarningF = CGRectMake(0.f, unHelpfulTextViewMaxY + 2.f, chatLabelMax, 22.f);
                            
                            unHelpfulTextViewMaxY += 2.f;
                            unHelpfulTextViewMaxY += 22.f;
                        }
                        
                        CGFloat unHelpfulSubmitMaxY = unHelpfulTextViewMaxY;
                        if (unHelpfulSubmitMaxY > 0.f) {
                            obj.unHelpfulSubmitF = CGRectMake(0.f, unHelpfulSubmitMaxY + 12.f, chatLabelMax, 32.f);
                            unHelpfulSubmitMaxY += 12.f;
                            unHelpfulSubmitMaxY += 32.f;
                        }
                        
                        obj.unHelpfulTagAndTextViewContentF = CGRectMake(0, helpfulAndUnHelpfulContentHeight, chatLabelMax, unHelpfulSubmitMaxY);
                        helpfulAndUnHelpfulContentHeight += obj.unHelpfulTagAndTextViewContentF.size.height;
                    } else {
                        obj.unHelpfulTagAndTextViewContentF = CGRectZero;
                    }
                    
                    if (contentWidth < chatLabelMax) {
                        contentWidth = chatLabelMax;
                    }
                    
                    if (![kitUtils isBlankString:obj.answerSource]) {   //存在参考来源时提前停止
                        *stop = YES;
                    } else {
                        obj.showAnswerSource = NO;
                    }
                }
            }];
            
            contentHeight = helpfulAndUnHelpfulContentHeight;
            
            CGSize chateLabelSize = CGSizeMake(contentWidth, contentHeight);
            
            CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            CGSize bubbleSize = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + bubblePadding * 2);
            
            _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            
            CGFloat x     = CGRectGetMinX(_bubbleViewF) + bubblePadding + arrowWidth;
            _topViewF     = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
            _robotCombinationF = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + (bubbleSize.height -chateLabelSize.height) / 2, chateLabelSize.width, chateLabelSize.height);
        } else if ([model.message.type isEqualToString:GXRichText]) {
            
            NSMutableArray <RichTextMessage *>*richModels = (NSMutableArray <RichTextMessage *>*)model.message.content;
            
            __block CGFloat richContentWidth = 0;
            __block CGFloat repliedMessageY = 0;
            
            RepliedMessageModel *repliedMessage = (RepliedMessageModel *)model.message.extra;
            
            if (repliedMessage &&
                [repliedMessage isKindOfClass:[RepliedMessageModel class]] &&
                (![kitUtils isBlankString:repliedMessage.content] ||
                 ![kitUtils isBlankString:repliedMessage.fileKey])) {
                richContentWidth = chatLabelMax;
                
                repliedMessageY += 47.f;
                NSInteger messageType = repliedMessage.messageType.integerValue;
                CGFloat contentF = 0;
                
                NSString *content = @"";
                switch (messageType) {
                    case 1:
                        content = [NSString stringWithFormat:@"%@",repliedMessage.content];
                        break;
                    case 2: {
                        contentF = 82.f;
                        repliedMessageY += 82.f;
                    }
                        break;
                    case 3:
                        content = [NSString stringWithFormat:@"[文件] %@",repliedMessage.fileName];
                        break;
                    case 4:
                        content = [NSString stringWithFormat:@"[视频] %@",repliedMessage.fileName];
                        break;
                    case 7:
                        content = @"[音频文件]";
                        break;
                    default:
                        content = @"[未知类型]";
                        break;
                }
                if (messageType != 2) {
                    CGSize chateLabelSize = [content tim_sizeWithMaxWidth:chatLabelMax - 10.f
                                                                  andFont:[UIFont fontWithName:@"PingFangSC-Regular" size:13.0]];
                    if (chateLabelSize.height <= 24.f) {
                        repliedMessageY += 18.f;
                        contentF = 18.f;
                    } else {
                        repliedMessageY += 40.f;
                        contentF = 40.f;
                    }
                }
                
                _repliedMessageContentF = CGRectMake(10.f, 34.f, chatLabelMax - 10.f, contentF);
                _repliedMessageContent = [content mutableCopy];
            }
            _repliedMessageViewF = CGRectMake(0, 0, chatLabelMax, repliedMessageY);
            
            __block CGFloat richContentHeight = 0;
            
            [richModels enumerateObjectsUsingBlock:^(RichTextMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                
                if ([kitUtils isTextTypeLabel:obj.type]) {
                    
                    if ([obj.type isEqualToString:@"a"] ||
                        [obj.type isEqualToString:@"knowledge"]) {
                        
                    } else {
                        obj.attributedString = [self transferMessageString:obj.content?:@"" font:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font foreColor:[TOSKitCustomInfo shareCustomInfo].receiveText_Color lineHeight:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font.lineHeight];
                    }
                    
                    NSMutableArray *fontDic = [NSMutableArray array];
                    [obj.subElements enumerateObjectsUsingBlock:^(RichTextMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                        if ([obj.type isEqualToString:@"span"] &&
                            [obj.style hasPrefix:@"font-size"]) {
                            
                            NSString *styleStr = [obj.style stringByReplacingOccurrencesOfString:@"font-size: " withString:@""];
                            NSString *sizeStr = [styleStr stringByReplacingOccurrencesOfString:@"px;" withString:@""];
                            [fontDic addObject:@{@"content": obj.content?:@"", @"font": [UIFont fontWithName:@"PingFangSC-Regular" size:sizeStr.doubleValue]}];
                        }
                    }];
                    
                    //                    CGSize chateLabelSize = [obj.content sizeWithMaxWidth:chatLabelMax andFont:MessageFont fontDic:fontDic];
                    //                    CGSize chateLabelSize = [obj.content sizeWithMaxWidth:chatLabelMax andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotText_font fontDic:fontDic];
                    
                    UIFont *font = [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font;
                    NSMutableAttributedString *attributedString = [obj.content transferMessageFont:font
                                                                                         foreColor:[self setTextLabelColor:model.isSender]
                                                                                           fontDic:fontDic
                                                                                        lineHeight:font.lineHeight
                                                                                  filteredLineFeed:YES];
                    
                    CGSize chateLabelSize = [obj.content tim_sizeWithMaxWidth:chatLabelMax attributeStr:attributedString];
                    
                    obj.contentF = CGRectMake(0, 0, chateLabelSize.width, chateLabelSize.height);
                    richContentHeight += chateLabelSize.height;
                    
                    if (richContentWidth < chateLabelSize.width) {
                        richContentWidth = chateLabelSize.width;
                    }
                } else {
                    
                    if ([obj.type isEqualToString:@"tr"] ||
                        [obj.type isEqualToString:@"h1"] ||
                        [obj.type isEqualToString:@"h2"] ||
                        [obj.type isEqualToString:@"h3"] ||
                        [obj.type isEqualToString:@"h4"] ||
                        [obj.type isEqualToString:@"h5"] ||
                        [obj.type isEqualToString:@"h6"]) {
                        
                        //                        CGSize chateLabelSize = [obj.content sizeWithMaxWidth:chatLabelMax andFont:MessageFont];
                        obj.attributedString = [self transferMessageString:obj.content?:@"" font:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font foreColor:[TOSKitCustomInfo shareCustomInfo].receiveText_Color lineHeight:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font.lineHeight];
                        
                        //                        CGSize chateLabelSize = [obj.content sizeWithMaxWidth:chatLabelMax andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotText_font];
                        CGSize chateLabelSize = [obj.content tim_sizeWithMaxWidth:chatLabelMax attributeStr:obj.attributedString];
                        
                        
                        obj.contentF = CGRectMake(0, 0, chateLabelSize.width, chateLabelSize.height);
                        richContentHeight += chateLabelSize.height;
                        
                        if (richContentWidth < chateLabelSize.width) {
                            richContentWidth = chateLabelSize.width;
                        }
                    } else if ([obj.type isEqualToString:@"ul"] ||
                               [obj.type isEqualToString:@"ol"]) {
                        
                        obj.attributedString = [self transferMessageString:obj.content?:@"" font:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font foreColor:[TOSKitCustomInfo shareCustomInfo].receiveText_Color lineHeight:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font.lineHeight];
                        
                        CGFloat olChatLabelMax = chatLabelMax;
                        if ([obj.type isEqualToString:@"ul"]) {
                            olChatLabelMax -= 20.f;
                        }
                        
                        //                        CGSize chateLabelSize = [obj.content sizeWithMaxWidth:olChatLabelMax andFont:MessageFont];
                        //                        CGSize chateLabelSize = [obj.content sizeWithMaxWidth:olChatLabelMax andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotText_font];
                        CGSize chateLabelSize = [obj.content tim_sizeWithMaxWidth:olChatLabelMax attributeStr:obj.attributedString];
                        
                        obj.contentF = CGRectMake(0, 0, chateLabelSize.width, chateLabelSize.height);
                        richContentHeight += chateLabelSize.height;
                        
                        if (richContentWidth < chateLabelSize.width) {
                            richContentWidth = chateLabelSize.width;
                        }
                    } else if ([obj.type isEqualToString:@"img"] ||
                               [obj.type isEqualToString:@"video"]) {
                        
                        obj.contentF = CGRectMake(0, 0, chatLabelMax, chatLabelMax);
                        richContentHeight += chatLabelMax;
                        if (richContentWidth < chatLabelMax) {
                            richContentWidth = chatLabelMax;
                        }
                    }
                }
            }];
            
            CGSize chateLabelSize = CGSizeMake(richContentWidth, richContentHeight);
            
            CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            CGSize bubbleSize = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + bubblePadding * 2 + _repliedMessageViewF.size.height);
            
            _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            
            _repliedMessageViewF.size.width = chateLabelSize.width;
            
            CGFloat x     = CGRectGetMinX(_bubbleViewF) + bubblePadding + arrowWidth;
            _topViewF     = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
            _richTextContentF   = CGRectMake(x, _repliedMessageViewF.size.height + CGRectGetMinY(_bubbleViewF) + (bubbleSize.height - _repliedMessageViewF.size.height - chateLabelSize.height) / 2, chateLabelSize.width, chateLabelSize.height);
            
            
            _repliedMessageViewF.origin.y = CGRectGetMinY(_bubbleViewF);
            _repliedMessageViewF.origin.x = x;
            
        } else if ([model.message.type isEqualToString:TypeCommodityCardDetails]) {
            
            TIMCommodityCardMessage *cardMessgae = (TIMCommodityCardMessage *)model.message.extra;
            
            CGFloat bottomViewH = 0.f;
            
            CGFloat bottomViewW = APP_WIDTH - CGRectGetMaxX(_headImageViewF) - 14.f * 2;
            
            __block NSString *orderNumber = @"";
            if ([cardMessgae.extraInfo isKindOfClass:[NSArray class]]) {
                [cardMessgae.extraInfo enumerateObjectsUsingBlock:^(NSDictionary<NSString *,NSString *> * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    
                    if ([obj[@"name"] isEqualToString:@"订单号"]) {
                        orderNumber = obj[@"value"];
                        *stop = YES;
                    }
                }];
            }
            
            CGFloat orderNumberY = 0;
            CGFloat orderNumberH = 0;
            if (![kitUtils isBlankString:orderNumber]) {
                orderNumberY = 7.f;
                orderNumberH = 18.f;
            }
            
            CGFloat timeY = 0;
            CGFloat timeH = 0;
            if (![kitUtils isBlankString:cardMessgae.time]) {
                timeY = 7.f;
                timeH = 18.f;
            }
            
            NSString *orderNum;
            if (orderNumber &&
                orderNumber.length > 0) {
                orderNum = [NSString stringWithFormat:@"订单号：#%@",orderNumber];
            } else {
                orderNum = @" ";
            }
            
            CGFloat orderNumberW = [orderNum contentWidthWithFont:MessageFont12 height:100];
            orderNumberW = orderNumberW <= 190.f ? orderNumberW : 190.f;
            CGFloat timeW = bottomViewW-14.f*2-orderNumberW-10.f;
            
            _orderNumberF = CGRectMake(14.f, orderNumberY, orderNumberW, orderNumberH);
            _timeF = CGRectMake(bottomViewW - timeW - 14.f, timeY, timeW, timeH);
            
            CGFloat topCuttingLineY = 0.f;
            CGFloat topCuttingLineH = 0.f;
            CGFloat topCuttingLineTop = MAX(CGRectGetMaxY(_orderNumberF), CGRectGetMaxY(_timeF));
            if (topCuttingLineTop > 18.f) {
                topCuttingLineY = topCuttingLineTop + 7.f;
                topCuttingLineH = 0.5f;
            }
            _topCuttingLineF = CGRectMake(14.f, topCuttingLineY, bottomViewW - 14.f * 2, topCuttingLineH);
            
            CGFloat commodityPicX = 0.f;
            CGFloat commodityPicW = 0.f;
            if (![kitUtils isBlankString:cardMessgae.img]) {
                commodityPicX = 14.f;
                commodityPicW = 80.f;
            }
            _commodityPicF = CGRectMake(commodityPicX, CGRectGetMaxY(_topCuttingLineF) + 9.f, commodityPicW, 80.f);
            
            
            CGFloat titleH = 35.f;
            CGFloat subTitleTopSpace = 0.f;
            if (![kitUtils isBlankString:cardMessgae.subTitle]) {
                
                CGSize subTitleSize = [cardMessgae.subTitle tim_sizeWithMaxWidth:chatLabelMax andFont:[UIFont fontWithName:@"PingFangSC-Medium" size:12.0]];
                if (subTitleSize.height > 20.f) {
                    titleH = 35.f;
                } else {
                    titleH = 18.f;
                    subTitleTopSpace = 17.f;
                }
            }
            _commodityTitleF = CGRectMake(CGRectGetMaxX(_commodityPicF) + 10.f, CGRectGetMaxY(_topCuttingLineF) + 9.f, bottomViewW - CGRectGetMaxX(_commodityPicF) - 10.f - 14.f, titleH);
            _commoditySubTitleF = CGRectMake(CGRectGetMinX(_commodityTitleF), CGRectGetMaxY(_commodityTitleF) + 4.f + subTitleTopSpace, CGRectGetWidth(_commodityTitleF), 18.f);
            _priceF = CGRectMake(CGRectGetMinX(_commodityTitleF), CGRectGetMaxY(_commoditySubTitleF) + 8.f, CGRectGetWidth(_commodityTitleF), 18.f);
            
            CGFloat bottomCuttingLineH = 0.f;
            if (![kitUtils isBlankString:cardMessgae.status] ||
                (cardMessgae.extraInfo &&
                 [cardMessgae.extraInfo isKindOfClass:[NSArray class]] &&
                 cardMessgae.extraInfo.count > 0)) {
                bottomCuttingLineH = 0.5f;
            }
            _bottomCuttingLineF = CGRectMake(14.f, CGRectGetMaxY(_priceF) + 9.f, CGRectGetWidth(_topCuttingLineF), bottomCuttingLineH);
            
            CGFloat transportTitleH = 0.f;
            CGFloat transportTitleY = CGRectGetMaxY(_bottomCuttingLineF);
            if (![kitUtils isBlankString:cardMessgae.status]) {
                transportTitleH = 18.f;
                transportTitleY = CGRectGetMaxY(_bottomCuttingLineF) + 5.f;
            }
            _transportTitleF = CGRectMake(14.f, transportTitleY, 61.f, transportTitleH);
            _transportStatusF = CGRectMake(CGRectGetMaxX(_transportTitleF), CGRectGetMinY(_transportTitleF), 140.f, CGRectGetHeight(_transportTitleF));
            
            CGFloat foldAndUnfoldBtnY = CGRectGetMaxY(_bottomCuttingLineF);
            CGFloat foldAndUnfoldBtnH = 0.f;
            CGFloat foldAndUnfoldIconH = 0.f;
            if (cardMessgae.extraInfo &&
                [cardMessgae.extraInfo isKindOfClass:[NSArray class]] &&
                cardMessgae.extraInfo.count > 3) {
                foldAndUnfoldBtnH = 30.f;
                foldAndUnfoldIconH = 16.f;
            }
            _foldAndUnfoldBtnF = CGRectMake(bottomViewW - 16.f - 14.f - 32.f, foldAndUnfoldBtnY, 32.f, foldAndUnfoldBtnH);
            _foldAndUnfoldIconF = CGRectMake(bottomViewW - 16.f - 14.f, CGRectGetCenter(_foldAndUnfoldBtnF).y - foldAndUnfoldIconH/2.f, 16.f, foldAndUnfoldIconH);
            
            CGFloat transportDetailsH = 0;
            if (cardMessgae.extraInfo &&
                [cardMessgae.extraInfo isKindOfClass:[NSArray class]]) {
                if (cardMessgae.extraInfo.count > 3 &&
                    _foldAndUnfold) {
                    transportDetailsH = 3 * 21.f;
                } else {
                    transportDetailsH = cardMessgae.extraInfo.count * 21.f;
                }
            } else {
                transportDetailsH = 0.f;
            }
            
            CGFloat transportDetailsY = MAX(CGRectGetMaxY(_transportTitleF), CGRectGetMaxY(_foldAndUnfoldBtnF));
            if (transportDetailsY > 10.f) {
                transportDetailsY = transportDetailsY + 5.f;
            }
            
            _transportDetailsF = CGRectMake(14.f, transportDetailsY, bottomViewW - 14.f * 2, transportDetailsH);
            
            
            bottomViewH += CGRectGetMaxY(_transportDetailsF);
            
            CGSize chateLabelSize = CGSizeMake(_headNameF.origin.x - 14.f - bubblePadding * 2.f + arrowWidth, bottomViewH);
            
            CGSize bubbleSize     = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height);
            CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            
            
            _topViewF     = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
            
            _bottomViewF = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, _bubbleViewF.origin.y, bottomViewW, _bubbleViewF.size.height);
            _cellClickBtnF = CGRectMake(0, 0, CGRectGetWidth(_bottomViewF), CGRectGetHeight(_bottomViewF));
        } else if ([model.message.type isEqualToString:TypeLogisticsCard]) {
            
            CGSize contentSize = CGSizeMake(216.f, 0.f);
            
            TIMLogisticsCardMessage *cardMsg = (TIMLogisticsCardMessage *)model.message.extra;
            if (![kitUtils isBlankString:cardMsg.createTime]) {
                cardMsg.createTime = [NSString stringWithFormat:@"承运日期：%@",cardMsg.createTime];
                _logisticsCard_startDateF = CGRectMake(12.f, 8.f, contentSize.width, 20.f);
            } else {
                _logisticsCard_startDateF = CGRectMake(12.f, 8.f, contentSize.width, 0.f);;
            }
            
            if (![kitUtils isBlankString:cardMsg.goodsName]) {
                cardMsg.goodsName = [NSString stringWithFormat:@"商品名称：%@",cardMsg.goodsName];
                CGSize goodsNameSize = [cardMsg.goodsName defaultLabelSizeWithMaxWidth:contentSize.width andFont:[UIFont fontWithName:@"PingFangSC-Regular" size:14.f]];
                
                _logisticsCard_commodityNameF = CGRectMake(12.f, CGRectGetMaxY(_logisticsCard_startDateF) + 4.f, goodsNameSize.width, goodsNameSize.height);
            } else {
                _logisticsCard_commodityNameF = CGRectMake(12.f, CGRectGetMaxY(_logisticsCard_startDateF) + 4.f, contentSize.width, 0.f);
            }
            
            cardMsg.senderName = [NSString stringWithFormat:@"%@",cardMsg.senderName];
            
            cardMsg.recipientName = [NSString stringWithFormat:@"%@",cardMsg.recipientName];
            
            CGSize senderNameSize = [cardMsg.senderName defaultLabelSizeWithMaxWidth:contentSize.width - 14.f
                                                                             andFont:[UIFont fontWithName:@"PingFangSC-Medium" size:16.f]];
            CGSize recipientNameSize = [cardMsg.recipientName defaultLabelSizeWithMaxWidth:contentSize.width - 14.f
                                                                                   andFont:[UIFont fontWithName:@"PingFangSC-Medium" size:16.f]];
            _logisticsCard_startingPointF = CGRectMake(26.f, CGRectGetMaxY(_logisticsCard_commodityNameF) + 8.f, senderNameSize.width, senderNameSize.height);
            _logisticsCard_endPointF = CGRectMake(26.f, CGRectGetMaxY(_logisticsCard_startingPointF) + 8.f, recipientNameSize.width, recipientNameSize.height);
            
            _logisticsCard_startIconF = CGRectMake(12.f, CGRectGetMinY(_logisticsCard_startingPointF) + 7.5f, 8.f, 8.f);
            _logisticsCard_endIconF = CGRectMake(12.f, CGRectGetMinY(_logisticsCard_endPointF) + 7.5f, 8.f, 8.f);
            _logisticsCard_verticalLineF = CGRectMake(15.5f, CGRectGetMaxY(_logisticsCard_startIconF) + 3.f, 1.f, CGRectGetMinY(_logisticsCard_endIconF) - CGRectGetMaxY(_logisticsCard_startIconF) - 6.f);
            
            if (![kitUtils isBlankString:cardMsg.goodsAmount]) {
                cardMsg.goodsAmount = [NSString stringWithFormat:@"%@",cardMsg.goodsAmount];
                _logisticsCard_commodityAmountF = CGRectMake(12.f, CGRectGetMaxY(_logisticsCard_endPointF) + 8.f, contentSize.width, 18.f);
            } else {
                _logisticsCard_commodityAmountF = CGRectMake(12.f, CGRectGetMaxY(_logisticsCard_endPointF) + 8.f, contentSize.width, 0.f);
            }
            
            if (![kitUtils isBlankString:cardMsg.goodsQuantity]) {
                cardMsg.goodsQuantity = [NSString stringWithFormat:@"%@",cardMsg.goodsQuantity];
                CGSize goodsQuantitySize = [cardMsg.goodsQuantity defaultLabelSizeWithMaxWidth:contentSize.width - 20.f*2 andFont:MessageFont12];
                _logisticsCard_commodityQuantityBtnF = CGRectMake(12.f, CGRectGetMaxY(_logisticsCard_commodityAmountF) + 12.f, goodsQuantitySize.width + 20.f*2, 24.f);
            } else {
                _logisticsCard_commodityQuantityBtnF = CGRectMake(12.f, CGRectGetMaxY(_logisticsCard_commodityAmountF) + 12.f, 0.f, 0.f);
            }
            
            if (![kitUtils isBlankString:cardMsg.orderNumber]) {
                _logisticsCard_horizontalLineF = CGRectMake(12.f, CGRectGetMaxY(_logisticsCard_commodityQuantityBtnF) + 12.f, contentSize.width + 12.f, 1.f);
                
                if (![cardMsg.orderNumber hasPrefix:@"订单编号"]) {
                    cardMsg.orderNumber = [NSString stringWithFormat:@"订单编号：%@",cardMsg.orderNumber];
                }
                CGFloat maxWidth = contentSize.width - 16.f - 4.f;
                CGSize orderNumberSize = [cardMsg.orderNumber defaultLabelSizeWithMaxHeight:maxWidth andFont:[UIFont fontWithName:@"PingFangSC-Regular" size:13.f]];
                NSLog(@"orderNumberSize ==== %@，cardMsg.orderNumber === %@，contentSize ==== %@",NSStringFromCGSize(orderNumberSize),cardMsg.orderNumber,NSStringFromCGSize(contentSize));
                _logisticsCard_orderNumberF = CGRectMake(12.f, CGRectGetMaxY(_logisticsCard_horizontalLineF) + 8.f, orderNumberSize.width >= maxWidth ? maxWidth : orderNumberSize.width, 22.f);
                _logisticsCard_copyBtnF = CGRectMake(CGRectGetMaxX(_logisticsCard_orderNumberF) + 4.f, CGRectGetMaxY(_logisticsCard_horizontalLineF) + 11.f, 16.f, 16.f);
            } else {
                _logisticsCard_horizontalLineF = CGRectMake(12.f, CGRectGetMaxY(_logisticsCard_commodityQuantityBtnF) + 12.f, contentSize.width + 12.f, 0.f);
                _logisticsCard_orderNumberF = CGRectMake(12.f, CGRectGetMaxY(_logisticsCard_horizontalLineF) + 8.f, 0.f, 0.f);
                _logisticsCard_copyBtnF = CGRectMake(CGRectGetMaxX(_logisticsCard_orderNumberF) + 4.f, CGRectGetMaxY(_logisticsCard_horizontalLineF) + 11.f, 16.f, 0.f);
            }
            
            bubblePadding = 12.f;
            contentSize.height = CGRectGetMaxY(_logisticsCard_orderNumberF);
            CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            CGSize bubbleSize = CGSizeMake(contentSize.width + bubblePadding * 2 + arrowWidth, contentSize.height + 8.f);
            
            _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            
            _topViewF     = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
        } else if ([model.message.type isEqualToString:TypeSmallProgramCard]) { //小程序卡片
            
            TOSMessageSmallProgramModel *smallProgramMsg = (TOSMessageSmallProgramModel *)model.message.extra;
            
            bubblePadding = 12.f;
            CGFloat maxWidth = 216.f;
            
            CGSize chateLabelSize = [smallProgramMsg.title?:@"" defaultLabelSizeWithMaxWidth:maxWidth andFont:MessageFont];
            if (chateLabelSize.height <= 24.f) {
                chateLabelSize.height = 24.f;
            } else {
                chateLabelSize.height = 48.f;
            }
            chateLabelSize.width = maxWidth;
            
            _smallProgram_appLogoF = CGRectMake(bubblePadding, 8.f, 18.f, 18.f);
            CGFloat appNameX = CGRectGetMaxX(_smallProgram_appLogoF) + 4.f;
            _smallProgram_appNameF = CGRectMake(appNameX, 8.f, chateLabelSize.width - appNameX, 18.f);
            
            _smallProgram_titleF = CGRectMake(bubblePadding, CGRectGetMaxY(_smallProgram_appLogoF) + 4.f, chateLabelSize.width, chateLabelSize.height);
            
            _smallProgram_picurlF = CGRectMake(bubblePadding, CGRectGetMaxY(_smallProgram_titleF) + 4.f, maxWidth, 174.f);
            
            _smallProgram_lineF = CGRectMake(bubblePadding, CGRectGetMaxY(_smallProgram_picurlF) + 8.f, maxWidth, 1.f);
            
            _smallProgram_iconF = CGRectMake(bubblePadding, CGRectGetMaxY(_smallProgram_lineF) + 6.f, 14.f, 14.f);
            _smallProgram_iconTextF = CGRectMake(CGRectGetMaxX(_smallProgram_iconF) + 4.f, CGRectGetMaxY(_smallProgram_lineF) + 4.f, 80.f, 18.f);
            
            _smallProgram_copyBtnF = CGRectMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth - 65.f, CGRectGetMaxY(_smallProgram_lineF) + 4.f, 55.f, 18.f);
            
            
            chateLabelSize.height = CGRectGetMaxY(_smallProgram_copyBtnF);            
            CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            CGSize bubbleSize = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + 4.f);
            
            _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            
            _topViewF     = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
            
        } else if ([model.message.type isEqualToString:TypeRevoke]) { // 撤回消息
            
//            topViewH = 0;
//            [self revokeMessageFrameWithModel:model bubblePadding:bubblePadding cellMinW:cellMinW topViewH:topViewH headToBubble:headToBubble topTimeViewH:topTimeViewH arrowWidth:arrowWidth topViewToBubbleH:2 cellMargin:cellMargin timeShowLength:timeShowLength];
            if (![kitUtils isBlankString:model.message.content]) {
                
                _chatLabelF = CGRectMake(0, 0, 0, 0);
                
                /// 距离屏幕左右的间距+label内部文字的间距
                UIEdgeInsets edgeInsets = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets;
                UIEdgeInsets textEdgeInsets = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets;
                
                CGFloat edgeInset = edgeInsets.left+edgeInsets.right+textEdgeInsets.left+textEdgeInsets.right;
                /// ⚠️这里计算后，cellheight在下面还会计算一次，之前的逻辑，不知道是哪个业务涉及到的，所以这里改动计算方法，在下方也需要同步改动⚠️
                CGSize size = [model.message.content tim_sizeForWithMaxWidth:[UIScreen mainScreen].bounds.size.width - edgeInset lineSpacing:2.0 withFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont];
                if ([TOSKitCustomInfo shareCustomInfo].chatMessage_system_center) {
                    _chatLabelF.size.height = (int)size.height+textEdgeInsets.top+textEdgeInsets.bottom;
                    _chatLabelF.size.width  = size.width+textEdgeInsets.left+textEdgeInsets.right;
                    _chatLabelF.origin.x = (kScreenWidth-_chatLabelF.size.width)/2;
                    _chatLabelF.origin.y = (edgeInsets.top + edgeInsets.bottom) * 0.5;
                    
                }
                else {
                    _chatLabelF = CGRectMake(edgeInsets.left, edgeInsets.top, (int)size.width+textEdgeInsets.left+textEdgeInsets.right, (int)size.height+textEdgeInsets.top+textEdgeInsets.bottom);
                }
                
                _bubbleViewF = CGRectMake(0, 0, 0, size.height+edgeInsets.top + edgeInsets.bottom);// 只需要高度就行
            }
            
        }else if ([model.message.type isEqualToString:TypeEventQueue]) { // 排队消息
            if (![kitUtils isBlankString:model.message.content]) {
                
                _chatLabelF = CGRectMake(0, 0, 0, 0);
                /// 时间信息的高度
                CGFloat timeHieght = 0.0;
                /// 判断是否显示时间信息
                if (model.message.showTime) {
                    timeHieght = topTimeViewH;
                }
                /// 默认排队中
                NSString * leaveQueueStr = @" 放弃排队";
                /// 如果结束了会话，需要把放弃排队置空
                if ([TIMClient.sharedTIMClient getLibOnlineStatus] == TinetChatStatusTypeCloseChat) {
                    leaveQueueStr = @"";
                }
                
                /// 距离屏幕左右的间距+label内部文字的间距
                UIEdgeInsets edgeInsets = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets;
                UIEdgeInsets textEdgeInsets = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets;
                CGFloat edgeInset = edgeInsets.left+edgeInsets.right+textEdgeInsets.left+textEdgeInsets.right;
                /// ⚠️这里计算后，cellheight在下面还会计算一次，之前的逻辑，不知道是哪个业务涉及到的，所以这里改动计算方法，在下方也需要同步改动⚠️
                CGSize size = [[NSString stringWithFormat:@"%@%@", model.message.content, leaveQueueStr] tim_sizeForWithMaxWidth:[UIScreen mainScreen].bounds.size.width - edgeInset lineSpacing:2.0 withFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont];
                _chatLabelF.size.height = (int)size.height+textEdgeInsets.top+textEdgeInsets.bottom;
                _chatLabelF.size.width  = size.width+textEdgeInsets.left+textEdgeInsets.right;
                _chatLabelF.origin.x = (kScreenWidth-_chatLabelF.size.width)/2;
                _chatLabelF.origin.y = (edgeInsets.top + edgeInsets.bottom) * 0.5+timeHieght;
                _bubbleViewF = CGRectMake(0, 0, 0, size.height+edgeInsets.top + edgeInsets.bottom+timeHieght);// 只需要高度就行
            }
//            topViewH = 0;
//            [self queueMessageFrameWithModel:model bubblePadding:bubblePadding cellMinW:cellMinW topViewH:topViewH headToBubble:headToBubble topTimeViewH:topTimeViewH arrowWidth:arrowWidth topViewToBubbleH:2+20 cellMargin:cellMargin timeShowLength:timeShowLength];
        }else if ([model.message.type isEqualToString:TypeCustomFile]) { // 自定义文件
            if (model.message.content) {
                
                CGSize chateLabelSize = CGSizeMake(247.f, 85.f);
                CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
                CGSize bubbleSize = CGSizeMake(chateLabelSize.width, chateLabelSize.height);
                
                _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+bubbleToNameH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
                CGFloat x     = CGRectGetMinX(_bubbleViewF) + bubblePadding+8.f;
                _topViewF     = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
                _chatLabelF   = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + (bubbleSize.height -chateLabelSize.height) / 2, chateLabelSize.width, chateLabelSize.height);
                
                
                //                _custFilePictureF         = CGRectMake(CGRectGetMinX(_bubbleViewF) + 160.f, CGRectGetMinY(_bubbleViewF) + 8.f, 32.f, 32.f);
                _custFilePictureF         = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + 8.f, 32.f, 32.f);
                
                CGFloat custFileTypeDescY = CGRectGetMinY(_bubbleViewF) + 12.0f;
                CGFloat custFileTypeDescW = _bubbleViewF.size.width - bubblePadding - 8.f - CGRectGetWidth(_custFilePictureF) - 13.f;
                _custFileTypeDescF = CGRectMake(CGRectGetMaxX(_custFilePictureF) + 4.0f, custFileTypeDescY, custFileTypeDescW, 24.f);
                
                _custFileNameF = CGRectMake(x, CGRectGetMaxY(_custFileTypeDescF) + 3.f, custFileTypeDescW, 17.f);
                
                //                _custFileTimeF = CGRectMake(x, CGRectGetMaxY(_custFileNameF) + 11.f, custFileTypeDescW, 16.f);
                _custFileTimeF = CGRectMake(CGRectGetMinX(_custFileTypeDescF), CGRectGetMaxY(_custFileTypeDescF) + 5.f, custFileTypeDescW, 16.f);
                
                _aduitRejectedViewF  = CGRectMake(CGRectGetMinX(_bubbleViewF), CGRectGetMaxY(_custFileTypeDescF) + 12.0f, bubbleSize.width, 36.f);
                
            }
        } else if ([model.message.type isEqualToString:TypeCustom]) { // 自定义消息
            if (model.message.content) {
                NSDictionary * contentDic = [kitUtils dictionaryWithJsonString:model.message.content];
                // 如果不是默认模板
                if (contentDic && [contentDic objectForKey:@"template"] &&
                    [contentDic[@"template"] isEqualToString:@"tim-rtcMedia"]) {
                    // 获取stopAction
                    NSString * strStopAction = @"hangup";
                    if ([contentDic valueForKey:@"body"] && [contentDic[@"body"] objectForKey:@"stopAction"]) {
                        strStopAction = [kitUtils transferStopAction:contentDic[@"body"][@"stopAction"] isSender:model.isSender duration:[contentDic[@"body"][@"duration"] intValue]];
                    }
                    CGSize chateLabelSize = [strStopAction tim_sizeWithMaxWidth:chatLabelMax andFont:MessageFont];
                    chateLabelSize.width += 22;
                    CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
                    CGSize bubbleSize = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + bubblePadding * 2);
                    
                    _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+bubbleToNameH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
                    CGFloat x     = CGRectGetMinX(_bubbleViewF) + bubblePadding + arrowWidth;
                    _topViewF     = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
                    _chatLabelF   = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + (bubbleSize.height -chateLabelSize.height) / 2, chateLabelSize.width, chateLabelSize.height);
                } else  {
                    CGSize chateLabelSize = CGSizeMake(240.f, 113.f);
                    CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
                    CGSize bubbleSize = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + bubblePadding * 2);
                    
                    _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+bubbleToNameH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
                    CGFloat x     = CGRectGetMinX(_bubbleViewF) + bubblePadding + arrowWidth;
                    _topViewF     = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
                    _chatLabelF   = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + (bubbleSize.height -chateLabelSize.height) / 2, chateLabelSize.width, chateLabelSize.height);
                    CGFloat customPadding = (bubbleSize.height -chateLabelSize.height) / 2;
                    _custShareTitleF      = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + customPadding, chateLabelSize.width, 27.f);
                    CGFloat custPicHeight = CGRectGetMaxY(_bubbleViewF) - CGRectGetMaxY(_custShareTitleF) - 2 * customPadding;
                    _custPictureF         = CGRectMake(x, CGRectGetMaxY(_custShareTitleF) + 1 + customPadding, custPicHeight, custPicHeight);
                    _custTitleF           = CGRectMake(CGRectGetMaxX(_custPictureF) + customPadding, CGRectGetMinY(_custPictureF), CGRectGetMaxX(_bubbleViewF)-CGRectGetMaxX(_custPictureF)- 2 *customPadding, _custPictureF.size.height / 3.f);
                    _custContentF         = _custTitleF;
                    _custContentF.origin.y  = CGRectGetMaxY(_custPictureF) - _custPictureF.size.height * 2.f / 3.f;
                    _custContentF.size.height  = _custPictureF.size.height * 2.f / 3.f;
                }
            }
        } else if ([model.message.type isEqualToString:TypePic]) {
            CGSize imageSize = CGSizeMake(model.picWidth?:150, model.picHeight?:150);
            float rateWH = (float)imageSize.width / imageSize.height;
            if(imageSize.width < imageSize.height){
                imageSize.width = picMinWidth;
                imageSize.height = picMinWidth / rateWH;
                if (imageSize.height > picMaxWidth) {
                    // 如果太高 则限高
                    imageSize.height = picMaxWidth;
                    imageSize.width = picMaxWidth * rateWH;
                }
            }else {
                imageSize.height = picMinWidth;
                imageSize.width = picMinWidth * rateWH;
                if (imageSize.width > picMaxWidth) {
                    // 如果太宽 则限宽
                    imageSize.width = picMaxWidth;
                    imageSize.height = picMaxWidth / rateWH;
                }
            }
            if ([model.picType isEqualToString:kGIFTimageType]) {
                imageSize = CGSizeMake(80, 80);
            }
            CGSize topViewSize     = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            CGSize bubbleSize      = CGSizeMake(imageSize.width, imageSize.height);
            CGFloat bubbleX        = CGRectGetMaxX(_headImageViewF)+headToBubble;
            _bubbleViewF           = CGRectMake(bubbleX, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+bubbleToNameH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGFloat x              = CGRectGetMinX(_bubbleViewF);
            _topViewF              = CGRectMake(x, cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
            _picViewF              = CGRectMake(x, cellMargin+topViewH+topTimeViewH+bubbleToNameH + topViewToBubbleH+topTimeViewH+bubbleToNameH+receiveNickNameToBubbleSpacing, imageSize.width, imageSize.height);
            
            CGSize chateLabelSize = [@"待审核" tim_sizeWithMaxWidth:chatLabelMax andFont:MessageFont12];
            _aduitStatusViewF      = CGRectMake(x + bubbleSize.width, topViewH+bubbleSize.height-chateLabelSize.height+topTimeViewH, chateLabelSize.width+bubblePadding, chateLabelSize.height+bubblePadding);
            
            // 审核操作
            CGSize chateLabelSize2 = [@"   不合格    通过   " tim_sizeWithMaxWidth:chatLabelMax andFont:MessageFont12];
            CGRect auditOpVF     = CGRectMake(x + bubbleSize.width/2 - chateLabelSize2.width/2-bubblePadding, topViewH+bubbleSize.height+bubblePadding*2+topTimeViewH, chateLabelSize2.width+bubblePadding*2, chateLabelSize2.height+bubblePadding);
            _aduitRejectedViewF = auditOpVF;
            _aduitRejectedViewF.size.width = auditOpVF.size.width/2;
            _aduitApprovedViewF = CGRectMake(auditOpVF.origin.x+auditOpVF.size.width/2, auditOpVF.origin.y, auditOpVF.size.width/2, auditOpVF.size.height);
            _bubbleViewF.size.height = _bubbleViewF.size.height +chateLabelSize2.height+bubblePadding;
        } else if ([model.message.type isEqualToString:TypeVoice]) {   // 语音
            CGFloat bubbleViewW = voiceMinW + 20; // 加上一个红点的宽度
            //            CGFloat bubbleViewW = 100 + 20; // 加上一个红点的宽度
            CGFloat voiceToBull = 4;
            long addbubbleViewLength = APP_WIDTH - CGRectGetMaxX(_headImageViewF) * 2 - voiceMinW; // 追增长度最大值
            long needAddWidth = 5 * [model.message.voiceDuration longValue] / 1000;
            if (needAddWidth > addbubbleViewLength) {
                needAddWidth = addbubbleViewLength;
            }
            TIMKitLog(@"needAddWidth = %ld",needAddWidth);
            _bubbleViewF = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+bubbleToNameH+receiveNickNameToBubbleSpacing, bubbleViewW + needAddWidth, 40);
            _topViewF    = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin+topTimeViewH, bubbleViewW-arrowWidth, topViewH);
            _voiceIconF = CGRectMake(CGRectGetMinX(_bubbleViewF)+arrowWidth+bubblePadding, cellMargin + 10 + topViewH+topTimeViewH+bubbleToNameH +topViewToBubbleH, 13, 20);
            // 假设
            NSString *duraStr = @"100";
            CGSize durSize = [duraStr tim_sizeWithMaxWidth:chatLabelMax andFont:[UIFont systemFontOfSize:14]];
            _durationLabelF = CGRectMake(CGRectGetMaxX(_bubbleViewF) - voiceToBull - durSize.width, cellMargin + 10 + topViewH+topTimeViewH+bubbleToNameH+topViewToBubbleH, durSize.width, durSize.height);
            _redViewF = CGRectMake(CGRectGetMaxX(_bubbleViewF) + 6, CGRectGetMinY(_bubbleViewF) + _bubbleViewF.size.height*0.5-4, 8, 8);
        } else if ([model.message.type isEqualToString:TypeVideo]) {   // 视频
            CGSize imageSize = CGSizeMake(model.picWidth?:150, model.picHeight?:150);
            float rateWH = (float)imageSize.width / imageSize.height;
            if(imageSize.width < imageSize.height){
                imageSize.width = picMinWidth;
                imageSize.height = picMinWidth / rateWH;
            }else {
                imageSize.height = picMinWidth;
                imageSize.width = picMinWidth * rateWH;
            }
            UIImage *videoImage = [[ICMediaManager sharedManager] videoImageWithFileName:[NSString stringWithFormat:@"%@.png",model.message.fileKey]];
            if (!videoImage) {
                NSString *path          = [[ICVideoManager shareManager] receiveVideoPathWithFileKey:model.message.fileKey];
                videoImage    = [UIImage videoFramerateWithPath:path];
            }
            if (videoImage) {
                float scale        = videoImage.size.height/videoImage.size.width;
                imageSize = CGSizeMake(150, 140*scale);
            }
            CGSize bubbleSize = CGSizeMake(imageSize.width, imageSize.height+topViewH);
            _bubbleViewF = CGRectMake(CGRectGetMaxX(_headImageViewF)+headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+bubbleToNameH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGSize topViewSize     = CGSizeMake(imageSize.width-arrowWidth, topViewH);
            CGFloat x              = CGRectGetMinX(_bubbleViewF);
            _topViewF              = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
            _picViewF              = CGRectMake(x, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+bubbleToNameH+receiveNickNameToBubbleSpacing, imageSize.width, imageSize.height);
            
            //            CGSize chateLabelSize = [@"待审核" sizeWithMaxWidth:chatLabelMax andFont:MessageFont12];
            //            _aduitStatusViewF      = CGRectMake(x + bubbleSize.width, topViewH+bubbleSize.height-chateLabelSize.height+topTimeViewH, chateLabelSize.width+bubblePadding, chateLabelSize.height+bubblePadding);
            
            // 审核操作
            //            CGSize chateLabelSize2 = [@"不合格  通过" sizeWithMaxWidth:chatLabelMax andFont:MessageFont12];
            //            CGRect auditOpVF     = CGRectMake(x + bubbleSize.width/2 - chateLabelSize2.width/2-bubblePadding, topViewH+bubbleSize.height+bubblePadding*2+topTimeViewH, chateLabelSize2.width+bubblePadding*2, chateLabelSize2.height+bubblePadding);
            //            _aduitRejectedViewF = auditOpVF;
            //            _aduitRejectedViewF.size.width = auditOpVF.size.width/2;
            //            _aduitApprovedViewF = CGRectMake(auditOpVF.origin.x+auditOpVF.size.width/2, auditOpVF.origin.y, auditOpVF.size.width/2, auditOpVF.size.height);
            _bubbleViewF.size.height = _bubbleViewF.size.height;//+chateLabelSize2.height+ bubblePadding
            
        } else if ([model.message.type isEqualToString:TypeSystem]) {
            if (![kitUtils isBlankString:model.message.content]) {
                
                _chatLabelF = CGRectMake(0, 0, 0, 0);
                
                /// 距离屏幕左右的间距+label内部文字的间距
                UIEdgeInsets edgeInsets = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets;
                UIEdgeInsets textEdgeInsets = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets;
                
                CGFloat edgeInset = edgeInsets.left+edgeInsets.right+textEdgeInsets.left+textEdgeInsets.right;
                
//                CGSize size           = [model.message.content tim_sizeWithMaxWidth:[UIScreen mainScreen].bounds.size.width - 30.0 - edgeInset andFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont];
                /// ⚠️这里计算后，cellheight在下面还会计算一次，之前的逻辑，不知道是哪个业务涉及到的，所以这里改动计算方法，在下方也需要同步改动⚠️
                CGSize size = [model.message.content tim_sizeForWithMaxWidth:[UIScreen mainScreen].bounds.size.width - edgeInset lineSpacing:2.0 withFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont];
                if ([TOSKitCustomInfo shareCustomInfo].chatMessage_system_center) {
                    _chatLabelF.size.height = (int)size.height+textEdgeInsets.top+textEdgeInsets.bottom;
                    _chatLabelF.size.width  = size.width+textEdgeInsets.left+textEdgeInsets.right;
                    _chatLabelF.origin.x = (kScreenWidth-_chatLabelF.size.width)/2;
                    _chatLabelF.origin.y = (edgeInsets.top + edgeInsets.bottom) * 0.5;
                    
                }
                else {
                    _chatLabelF = CGRectMake(edgeInsets.left, edgeInsets.top, (int)size.width+textEdgeInsets.left+textEdgeInsets.right, (int)size.height+textEdgeInsets.top+textEdgeInsets.bottom);
                }
                
                _bubbleViewF = CGRectMake(0, 0, 0, size.height+edgeInsets.top + edgeInsets.bottom);// 只需要高度就行
            }
        } else if ([model.message.type isEqualToString:TypeFile]) {
            CGSize bubbleSize = CGSizeMake(253, 95.0);
            _bubbleViewF = CGRectMake(CGRectGetMaxX(_headImageViewF)+headToBubble, cellMargin+topViewH+topViewToBubbleH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGSize topViewSize     = CGSizeMake(bubbleSize.width-arrowWidth, topViewH);
            CGFloat x              = CGRectGetMinX(_bubbleViewF);
            _topViewF              = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin, topViewSize.width, topViewSize.height);
            _picViewF              = CGRectMake(x, cellMargin+topViewH+topViewToBubbleH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
        } else if ([model.message.type isEqualToString:TypePicText]) {
            CGSize bubbleSize = CGSizeMake(253, 120.0);
            _bubbleViewF = CGRectMake(CGRectGetMaxX(_headImageViewF)+headToBubble, cellMargin+topViewH+topViewToBubbleH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGSize topViewSize     = CGSizeMake(bubbleSize.width-arrowWidth, topViewH);
            CGFloat x              = CGRectGetMinX(_bubbleViewF);
            _topViewF              = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin, topViewSize.width, topViewSize.height);
            _picViewF              = CGRectMake(x, cellMargin+topViewH+topViewToBubbleH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
        }else if ([model.message.type isEqualToString:TypeRobotCombination]) {//机器人组合消息
            
            //            CGSize chateLabelSize = [model.combinationTitle sizeWithMaxWidth:chatLabelMax andFont:MessageFont];
            //            CGSize chateSubLabelSize = [model.combinationSubTitle sizeWithMaxWidth:chatLabelMax andFont:MessageFont];
            CGSize chateLabelSize = [model.combinationTitle tim_sizeWithMaxWidth:chatLabelMax andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_titleFont];
            CGSize chateSubLabelSize = [model.combinationSubTitle tim_sizeWithMaxWidth:chatLabelMax andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_subTitleFont];
            //            CGFloat moreHeight = 10;
            //            CGFloat textWidth = chatLabelMax;
            //            if (model.combinationNum == 0) {
            //                moreHeight = 0;
            //                textWidth = chateLabelSize.width;
            //            }
            CGSize bubbleSize = CGSizeMake(chatLabelMax + bubblePadding * 2 + arrowWidth, chateLabelSize.height+chateSubLabelSize.height+30.f*model.combinationNum + bubblePadding * 2 + 10);
            _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+bubbleToNameH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGFloat x = CGRectGetMinX(_bubbleViewF) + bubblePadding + arrowWidth;
            _custShareTitleF = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + 10, chatLabelMax, chateLabelSize.height);
            _custTitleF = CGRectMake(x, CGRectGetMinY(_bubbleViewF)+chateLabelSize.height +20, chatLabelMax, chateSubLabelSize.height);
            _custContentF = CGRectMake(x, CGRectGetMinY(_bubbleViewF)  + chateLabelSize.height +chateSubLabelSize.height +20, chatLabelMax, 30.f*model.combinationNum);
            
        }else if ([model.message.type isEqualToString:TypeRobotCombinationList]) {//机器人组合消息
            
            
            //            CGSize chateLabelSize = [model.combinationTitle sizeWithMaxWidth:chatLabelMax andFont:MessageFont];
            //            CGSize chateSubLabelSize = [model.combinationSubTitle sizeWithMaxWidth:chatLabelMax andFont:MessageFont];
            CGSize chateLabelSize = [model.combinationTitle tim_sizeWithMaxWidth:chatLabelMax andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_titleFont];
            CGSize chateSubLabelSize = [model.combinationSubTitle tim_sizeWithMaxWidth:chatLabelMax andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_subTitleFont];
            //            CGFloat moreHeight = 10;
            //            CGFloat textWidth = chatLabelMax;
            //            if (model.combinationNum == 0) {
            //                moreHeight = 0;
            //                textWidth = chateLabelSize.width;
            //            }
            CGSize bubbleSize = CGSizeMake(chatLabelMax + bubblePadding * 2 + arrowWidth, chateLabelSize.height+chateSubLabelSize.height+30.f*model.combinationNum + bubblePadding * 2 + 10);
            _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+bubbleToNameH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGFloat x = CGRectGetMinX(_bubbleViewF) + bubblePadding + arrowWidth;
            _custShareTitleF = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + 10, chatLabelMax, chateLabelSize.height);
            _custTitleF = CGRectMake(x, CGRectGetMinY(_bubbleViewF)+chateLabelSize.height +20, chatLabelMax, chateSubLabelSize.height);
            _custContentF = CGRectMake(x, CGRectGetMinY(_bubbleViewF)  + chateLabelSize.height +chateSubLabelSize.height +20, chatLabelMax, 30.f*model.combinationNum);
            
        }else if ([model.message.type isEqualToString:TypeRobotSelectCombination]) {//机器人组合消息20
            
            CGSize titleHeightSize = [model.combinationTitle tim_sizeWithMaxWidth:chatLabelMax andFont:MessageFont];
            CGFloat titleHeight = titleHeightSize.height;
            
            CGSize chateLabelSize = CGSizeMake(240.f, titleHeight + 30.f*(model.combinationNum+2)+15.f);
            CGSize bubbleSize = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + bubblePadding * 2);
            _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+bubbleToNameH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGFloat x = CGRectGetMinX(_bubbleViewF) + bubblePadding + arrowWidth;
            CGFloat customPadding = (bubbleSize.height -chateLabelSize.height) / 2;
            _custShareTitleF = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + customPadding, chateLabelSize.width, titleHeight);
            _custContentF = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + customPadding + titleHeight+ 10.f , chateLabelSize.width, 30.f*(model.combinationNum+2));
        }else if ([model.message.type isEqualToString:TypeRobotWelcome]) {//机器人欢迎消息
            
            CGSize htmlWebSize = CGSizeMake(240.f, 440.f);
            
            CGSize bubbleSize = CGSizeMake(htmlWebSize.width + bubblePadding * 2 + arrowWidth, htmlWebSize.height + bubblePadding * 2);
            _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+bubbleToNameH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGFloat x = CGRectGetMinX(_bubbleViewF) + bubblePadding + arrowWidth;
            CGFloat customPadding = (bubbleSize.height -htmlWebSize.height) / 2;
            _custContentF = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + customPadding, htmlWebSize.width,200);
            _custFileNameF = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + customPadding+20+200, htmlWebSize.width,210);
        }else if ([model.message.type isEqualToString:TySystomCombination]) {//系统化欢迎消息
            CGSize htmlWebSize = CGSizeMake(240.f, 210.f);
            CGSize bubbleSize = CGSizeMake(htmlWebSize.width + bubblePadding * 2 + arrowWidth, htmlWebSize.height + bubblePadding * 2);
            _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+bubbleToNameH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            CGFloat x = CGRectGetMinX(_bubbleViewF) + bubblePadding + arrowWidth;
            CGFloat customPadding = (bubbleSize.height -htmlWebSize.height) / 2;
            _custContentF = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + customPadding, htmlWebSize.width,200);
        } else if ([model.message.type isEqualToString:TypeSatisfactionPopup]) { // 满意度评价底部弹框版本
            //            CGSize chateLabelSize = [model.message.content sizeWithMaxWidth:chatLabelMax andFont:MessageFont];
            CGSize chateLabelSize = [@"请对我本次服务进行满意度评价" tim_sizeWithMaxWidth:chatLabelMax andFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font];
            chateLabelSize.width += 2.f;
            CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
            CGSize bubbleSize = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + bubblePadding * 2 + 44.f);
            
            _bubbleViewF  = CGRectMake(CGRectGetMaxX(_headImageViewF) + headToBubble, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+receiveNickNameToBubbleSpacing, bubbleSize.width, bubbleSize.height);
            
            CGFloat x     = CGRectGetMinX(_bubbleViewF) + bubblePadding + arrowWidth;
            _topViewF     = CGRectMake(CGRectGetMinX(_bubbleViewF), cellMargin+topTimeViewH, topViewSize.width, topViewSize.height);
            _chatLabelF   = CGRectMake(x, CGRectGetMinY(_bubbleViewF) + bubblePadding, chateLabelSize.width, chateLabelSize.height);
        } else if ([model.message.type isEqualToString:TypeInvestigation]){ // 满意度评价
            
            CGSize contentSize = CGSizeMake(APP_WIDTH - 16.f*2.f, 110.f);   //基础宽高，包含评价等级高度
            
            NSString *content = model.message.content;
            
            NSString *extra = model.message.extra;
            TOSSatisfactionStatusModel *extraModel = [TOSSatisfactionStatusModel yy_modelWithJSON:extra];
            
            if (extraModel.alreadyInvestigation &&
                [extraModel.alreadyInvestigation isEqualToString:@"1"]) { //已评价
                
                TOSGetInvestigationInfoModel *infoModel = [TOSGetInvestigationInfoModel shareInvestigationInfoModel];
                _satisfactionSelectStar = infoModel.options[0].star.integerValue;
            } else if (extraModel.alreadyInvestigation &&
                       [extraModel.alreadyInvestigation isEqualToString:@"-1"]) {
                contentSize.height += 0.f;//24.f+38.f
            } else {//未评价
                contentSize.height += 62.f;//24.f+38.f
                if (self.satisfactionSelectStar < 1) {
                    TOSSatisfactionModel *contentModel = [TOSSatisfactionModel yy_modelWithJSON:model.message.content];
                    _satisfactionSelectStar = contentModel.investigation.star.count;
                    NSLog(@"satisfactionSelectStar : %ld", _satisfactionSelectStar);
                }
            }
            
            
            TOSSatisfactionModel *contentModel = [TOSSatisfactionModel yy_modelWithJSON:content];
            
            CGFloat titleHeight = [contentModel.investigation.welcome?:@"" tim_sizeWithMaxWidth:contentSize.width - 20.f andFont:[UIFont fontWithName:@"PingFangSC-Regular" size:13.f]].height;
            contentSize.height += titleHeight;
            _satisfactionTitleHeight = titleHeight;
            
            
            CGFloat starCount = contentModel.investigation.star.count;
            _satisfactionStarWidth = contentSize.width/starCount;
            
            if (contentModel.investigation.content.options &&
                contentModel.investigation.content.options.count > 0) {
                
                TOSSatisfactionStarInfoModel *starInfoModel = [contentModel.investigation.content.options[0].star objectOrNilAtIndex:_satisfactionSelectStar-1];
                NSInteger tabBarCount = starInfoModel.tabBar.count;
                NSInteger lineNum = tabBarCount/2+tabBarCount%2;
                
                _satisfactionSelectStarTagHeight = lineNum * 42.f;//12 + 30;
                contentSize.height += _satisfactionSelectStarTagHeight; //标签高度
                
                NSMutableArray *tabBarFrame = [NSMutableArray array];
                
                CGFloat width = (contentSize.width - 69.f) / 2;//25 + 25 + 19
                [starInfoModel.tabBar enumerateObjectsUsingBlock:^(NSString * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    
                    TOSSatisfactionTabBarFrameModel *tabBarFrameModel = [[TOSSatisfactionTabBarFrameModel alloc] init];
                    CGFloat left = idx%2 * width;
                    tabBarFrameModel.x = [NSNumber numberWithFloat:left + 25.f + (left > 0 ? 19.f : 0.f)];
                    
                    NSInteger lineNum = idx/2;
                    tabBarFrameModel.y = [NSNumber numberWithFloat:lineNum * 42.f + 12.f];//12 + 30
                    tabBarFrameModel.w = [NSNumber numberWithFloat:width];
                    tabBarFrameModel.h = [NSNumber numberWithFloat:30.f];
                    
                    [tabBarFrame addObject:tabBarFrameModel];
                }];
                
                self.tabBarFrame = [NSArray arrayWithArray:tabBarFrame];
            } else {
                _satisfactionSelectStarTagHeight = 0.f;
            }
            
            contentSize.height += 24.f;//底部高度
            
            _bubbleViewF = CGRectMake(0, 0, 0, contentSize.height + 16.f*2);// 只需要高度就行
        }
    }
    _cellHight = MAX(CGRectGetMaxY(_bubbleViewF), CGRectGetMaxY(_headImageViewF)) + cellMargin;
    if ([model.message.type isEqualToString:TypeSystem]) {
        //        CGSize size           = [model.message.content sizeWithMaxWidth:[UIScreen mainScreen].bounds.size.width-40 andFont:[UIFont systemFontOfSize:11.0]];
        //        _cellHight = size.height+10;
        CGFloat edgeinset = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.left+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.right + [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.left + [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.right;
//        CGSize size = [model.message.content tim_sizeWithMaxWidth:[UIScreen mainScreen].bounds.size.width - edgeinset andFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont];
        /// 计算富文本的宽高
        CGSize size = [model.message.content tim_sizeForWithMaxWidth:[UIScreen mainScreen].bounds.size.width - edgeinset lineSpacing:2.0 withFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont];
        
        _cellHight = size.height + [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.top+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.bottom + [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.top + [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.bottom;
    }
    
    if ([model.message.type isEqualToString:TypeUnknown]) {
        CGFloat edgeinset = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.left+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.right + [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.left + [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.right;
//        CGSize size = [model.message.content tim_sizeWithMaxWidth:[UIScreen mainScreen].bounds.size.width - edgeinset andFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont];
        /// 计算富文本的宽高
        CGSize size = [model.message.content tim_sizeForWithMaxWidth:[UIScreen mainScreen].bounds.size.width - edgeinset lineSpacing:2.0 withFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont];
        
        _cellHight = size.height + [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.top+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.bottom + [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.top + [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.bottom;
        
        _chatLabelF = CGRectMake(0, 0, 0, 0);
        
        /// 距离屏幕左右的间距+label内部文字的间距
        UIEdgeInsets edgeInsets = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets;
        UIEdgeInsets textEdgeInsets = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets;
        
        CGFloat edgeInset = edgeInsets.left+edgeInsets.right+textEdgeInsets.left+textEdgeInsets.right;
        
        if ([TOSKitCustomInfo shareCustomInfo].chatMessage_system_center) {
            _chatLabelF.size.height = (int)size.height+textEdgeInsets.top+textEdgeInsets.bottom;
//            _chatLabelF.size.width  = [UIScreen mainScreen].bounds.size.width - edgeInset;
//            _chatLabelF.origin.x = (edgeInsets.left + edgeInsets.right)*0.5f;
//            _chatLabelF.origin.y = (edgeInsets.top + edgeInsets.bottom) * 0.5;
            _chatLabelF.size.width  = size.width+textEdgeInsets.left+textEdgeInsets.right;
            _chatLabelF.origin.x = (kScreenWidth-_chatLabelF.size.width)/2;
            _chatLabelF.origin.y = (edgeInsets.top + edgeInsets.bottom) * 0.5;
        }
        else {
            _chatLabelF = CGRectMake(edgeInsets.left, edgeInsets.top, (int)size.width+textEdgeInsets.left+textEdgeInsets.right, (int)size.height+textEdgeInsets.top+textEdgeInsets.bottom);
        }
        
    }
    
    
    if (model.message.deliveryState == ICMessageDeliveryState_Failure_SensitiveWords) {
        _cellHight += 20.f;
    }
    
    if (model.isSender) {
        //        if (model.message.from containsString:@"customer-") {
        
        //        if (1) {
        if ([model.message.from containsString:@"customer"] == NO) {
            
            //如果要展示已读未读状态
            TIMKitLog(@"如果要展示已读未读状态,计算frame");
            //仿钉钉布局
            //            _cellHight = _cellHight + unreadLabelH;
            //            if (_bubbleViewF.size.width >= unreadLabelWidth) {
            //
            //                _unReadLabelF = CGRectMake(_bubbleViewF.origin.x, _cellHight - unreadLabelH - 5, _bubbleViewF.size.width, unreadLabelH);
            //            }else{
            //
            //                CGFloat minusWidth = unreadLabelWidth - _bubbleViewF.size.width;
            //                _unReadLabelF = CGRectMake(_bubbleViewF.origin.x - minusWidth, _cellHight - unreadLabelH - 5, _bubbleViewF.size.width + minusWidth, unreadLabelH);
            //            }
            
            //仿拼多多布局
            _unReadLabelF = CGRectMake(_bubbleViewF.origin.x - unreadLabelWidth, _bubbleViewF.origin.y + _bubbleViewF.size.height - unreadLabelH, unreadLabelWidth, unreadLabelH);
            
        }
    }
    
}

/// 获取当前页的子问题
/// @param dataSource 源数据
/// @param selectPage 当前下标
- (NSArray <NSString *>*)getSubIssueItem:(NSArray <NSString *>*)dataSource
                              selectPage:(NSInteger)selectPage {
    
    if (dataSource.count <= 5) {
        return dataSource;
    } else {
        
        NSInteger surplus = dataSource.count - selectPage*5;
        NSInteger count = surplus >= 5 ? 5 : surplus;
        NSArray <NSString *>*items = [dataSource subarrayWithRange:NSMakeRange(selectPage*5, count)];
        return items;
    }
}

// 缩放，临时的方法
- (CGSize)handleImage:(CGSize)retSize
{
    CGFloat scaleH = 0.22;
    CGFloat scaleW = 0.38;
    CGFloat height = 0;
    CGFloat width = 0;
    if (retSize.height / APP_HEIGHT + 0.16 > retSize.width / APP_WIDTH) {
        height = APP_HEIGHT * scaleH;
        width = retSize.width / retSize.height * height;
    } else {
        width = APP_WIDTH * scaleW;
        height = retSize.height / retSize.width * width;
    }
    return CGSizeMake(width, height);
}

/// 撤回消息类型的界面布局
/// @param model model
/// @param bubblePadding bubblePadding
/// @param cellMinW cellMinW
/// @param topViewH topViewH
/// @param headToBubble headToBubble
/// @param topTimeViewH topTimeViewH
/// @param arrowWidth arrowWidth
/// @param topViewToBubbleH topViewToBubbleH
/// @param cellMargin cellMargin
/// @param timeShowLength timeShowLength
- (void)revokeMessageFrameWithModel:(TIMMessageModel *)model
                      bubblePadding:(CGFloat)bubblePadding
                           cellMinW:(CGFloat)cellMinW
                           topViewH:(CGFloat)topViewH
                       headToBubble:(CGFloat)headToBubble
                       topTimeViewH:(CGFloat)topTimeViewH
                         arrowWidth:(CGFloat)arrowWidth
                   topViewToBubbleH:(CGFloat)topViewToBubbleH
                         cellMargin:(CGFloat)cellMargin
                     timeShowLength:(CGFloat)timeShowLength {
    /// 昵称和气泡的间距
    CGFloat sendNickNameToBubbleSpacing = 0.0f;
    
    CGSize chateLabelSize = [model.message.content tim_sizeWithMaxWidth:APP_WIDTH - 100 andFont:MessageFont12];
    CGSize bubbleSize     = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + bubblePadding * 2);
    CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
    _bubbleViewF          = CGRectMake(CGRectGetMinX(_headImageViewF) - headToBubble - bubbleSize.width, cellMargin+topViewH+topViewToBubbleH+topTimeViewH+sendNickNameToBubbleSpacing, bubbleSize.width, 0);
    CGFloat x             = CGRectGetMinX(_bubbleViewF)+bubblePadding;
    _topViewF             = CGRectMake(CGRectGetMinX(_headImageViewF) - timeShowLength , cellMargin+topTimeViewH,topViewSize.width,topViewSize.height);
    
    CGFloat chatWidth = MIN(chateLabelSize.width, APP_WIDTH - 100) + 10;
    _chatLabelF           = CGRectMake(0, CGRectGetMinY(_bubbleViewF), chatWidth, 22.f);
    _chatLabelF.origin.x = (APP_WIDTH - chatWidth)/2;
    _headImageViewF.size.height = _chatLabelF.size.height;
}

- (void)queueMessageFrameWithModel:(TIMMessageModel *)model
                     bubblePadding:(CGFloat)bubblePadding
                          cellMinW:(CGFloat)cellMinW
                          topViewH:(CGFloat)topViewH
                      headToBubble:(CGFloat)headToBubble
                      topTimeViewH:(CGFloat)topTimeViewH
                        arrowWidth:(CGFloat)arrowWidth
                  topViewToBubbleH:(CGFloat)topViewToBubbleH
                        cellMargin:(CGFloat)cellMargin
                    timeShowLength:(CGFloat)timeShowLength {
    /// 昵称和气泡的间距
    CGFloat sendNickNameToBubbleSpacing = [TOSKitCustomInfo shareCustomInfo].Chat_visitorName_enable ? [TOSKitCustomInfo shareCustomInfo].nickNameToBubbleSpacing : 0.0f;
    
    CGSize chateLabelSize = [model.message.content tim_sizeWithMaxWidth:APP_WIDTH - 100 andFont:MessageFont12];
    CGSize bubbleSize     = CGSizeMake(chateLabelSize.width + bubblePadding * 2 + arrowWidth, chateLabelSize.height + bubblePadding * 2);
    CGSize topViewSize    = CGSizeMake(cellMinW+bubblePadding*2, topViewH);
    _bubbleViewF          = CGRectMake(CGRectGetMinX(_headImageViewF) - headToBubble - bubbleSize.width, cellMargin+topViewH+topViewToBubbleH+topTimeViewH + sendNickNameToBubbleSpacing - 20, bubbleSize.width, 0);
    CGFloat x             = CGRectGetMinX(_bubbleViewF)+bubblePadding;
    _topViewF             = CGRectMake(CGRectGetMinX(_headImageViewF) - timeShowLength , cellMargin+topTimeViewH,topViewSize.width,topViewSize.height);
    
    CGFloat chatWidth = MIN(chateLabelSize.width, APP_WIDTH - 100) + 10;
    _chatLabelF           = CGRectMake(0, CGRectGetMinY(_bubbleViewF), chatWidth, 22.f);
    _chatLabelF.origin.x = (APP_WIDTH - chatWidth)/2 - 20;
    _headImageViewF.size.height = _chatLabelF.size.height;
    
    
    _custTitleF = CGRectMake(0, CGRectGetMinY(_bubbleViewF), 50, 22.f);
    _custTitleF.origin.x = (APP_WIDTH - chatWidth)/2 + chatWidth-15;
    
}

/// 客户搜索模块，设置标签样式
/// @param tagView TTGTextTagCollectionView
- (void)setupTTGTextTagView:(TTGTextTagCollectionView *)tagView {
    
    // Alignment
    tagView.alignment = TTGTagCollectionAlignmentLeft;
    
    tagView.horizontalSpacing = 8.f;
    tagView.verticalSpacing = 8.f;
    tagView.contentInset = UIEdgeInsetsMake(8.f, 0.f, 8.f, 0.f);
    // Use manual calculate height
    tagView.manualCalculateHeight = YES;
    
    tagView.enableTagSelection = YES;
    
    tagView.showsVerticalScrollIndicator = NO;
}

/// 标签样式配置
- (TTGTextTagStyle *)setupTTGTextTagStyle {
    TTGTextTagStyle *tagStyle = [[TTGTextTagStyle alloc] init];
    tagStyle.extraSpace = CGSizeMake(16.f, 8.f);
    tagStyle.cornerRadius = 4.f;
    tagStyle.backgroundColor = TOSHexColor(0xFFFFFF);
    return tagStyle;
}


- (TTGTextTagStringContent *)setupTTGTextTagContent {
    TTGTextTagStringContent *content = [[TTGTextTagStringContent alloc] init];
    content.textFont = [UIFont fontWithName:@"PingFangSC-Regular" size:12.f];
    content.textColor = TOSHexColor(0xFFFFFF);
    return content;
}

- (NSMutableAttributedString *)transferMessageString:(NSString *)message
                                                font:(UIFont *)font
                                           foreColor:(UIColor *)fColor
                                          lineHeight:(CGFloat)lineHeight {
    
    __weak typeof(self) weadSelf = self;
//    NSMutableAttributedString *attributeStr = [[NSMutableAttributedString alloc] initWithData:[[message  stringByReplacingOccurrencesOfString:@"\n" withString:@" "] dataUsingEncoding:NSUTF8StringEncoding] options:@{} documentAttributes:nil error:nil];
    NSMutableAttributedString *attributeStr = [[NSMutableAttributedString alloc] initWithData:[message dataUsingEncoding:NSUTF8StringEncoding] options:@{} documentAttributes:nil error:nil];
    attributeStr.lineBreakMode = NSLineBreakByWordWrapping;
    NSMutableDictionary <NSString *, NSString *>*linkDic = [NSMutableDictionary dictionary];
    [attributeStr enumerateAttribute:NSLinkAttributeName inRange:NSMakeRange(0, attributeStr.length) options:(NSAttributedStringEnumerationReverse) usingBlock:^(id  _Nullable value, NSRange range, BOOL * _Nonnull stop) {
        
        if (value) {
            [linkDic setObject:[NSString stringWithFormat:@"%lu,%lu",(unsigned long)range.location,(unsigned long)range.length] forKey:value];
        }
    }];
    
    // 表情的规则
    NSString *emotionPattern = @"(\\[[a-zA-Z0-9\\/\\u4e00-\\u9fa5]+\\])";
    // @的规则
    //    NSString *atPattern = @"@[0-9a-zA-Z\\u4e00-\\u9fa5-_]+";
    // #话题#的规则
    //    NSString *topicPattern = @"#[0-9a-zA-Z\\u4e00-\\u9fa5]+#";
//    // 订单号的规则
//    NSString *orderNumberPattern = @"([a-zA-Z0-9_]{5,})";
//    
//    // url链接的规则
//    NSString *urlPattern = @"(((http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(www.[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(((http[s]{0,1}|ftp)://|)((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?))";
//    
    //    NSString *urlPattern = @"(<a[a-zA-Z0-9\\s:/%=><&\\?\\-~_\\$#+\\.,;\"']*(</a>))|([a-zA-Z0-9_]{5,})";
    
//    NSString *phoneNumber = @"(\\d{3}-\\d{8}|\\d{4}-\\d{7}|\\d{11})";
    
    //    NSString *pattern = [NSString stringWithFormat:@"%@",emotionPattern];
//    NSString *pattern = [NSString stringWithFormat:@"%@|%@|%@|%@", urlPattern, emotionPattern, phoneNumber, orderNumberPattern];
    NSString *pattern = [NSString stringWithFormat:@"%@", emotionPattern];
    
    /// 判断超链接正则是否为null
    if (TOSKitCustomInfo.shareCustomInfo.urlRegular != nil) {
        if (TOSKitCustomInfo.shareCustomInfo.urlRegular.regular.length > 0) {
            pattern = [NSString stringWithFormat:@"%@|%@", pattern, TOSKitCustomInfo.shareCustomInfo.urlRegular.regular];
        }
    }
    
    /// 判断手机号正则是否为null
    if (TOSKitCustomInfo.shareCustomInfo.telRegular != nil) {
        if (TOSKitCustomInfo.shareCustomInfo.telRegular.regular.length > 0) {
            pattern = [NSString stringWithFormat:@"%@|%@", pattern, TOSKitCustomInfo.shareCustomInfo.telRegular.regular];
        }
    }
    
    /// 判断订单号正则是否为null
    if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular != nil) {
        if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.regular.length > 0) {
            pattern = [NSString stringWithFormat:@"%@|%@", pattern, TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.regular];
        }
    }
    
    
    NSError *error    = nil;
    //pattern
    NSRegularExpression *expression = [NSRegularExpression regularExpressionWithPattern:pattern options:NSRegularExpressionCaseInsensitive error:&error];
    if (!expression) {
        return attributeStr;
    }
    [attributeStr addAttribute:NSForegroundColorAttributeName value:fColor range:NSMakeRange(0,attributeStr.length)]; //设置字体颜色
    [attributeStr addAttribute:NSFontAttributeName value:font range:NSMakeRange(0, attributeStr.length)];
    
    [linkDic enumerateKeysAndObjectsUsingBlock:^(NSString * _Nonnull key, NSString * _Nonnull obj, BOOL * _Nonnull stop) {
        
        NSArray <NSString *>*rangeArray = [obj componentsSeparatedByString:@","];
        NSRange range = NSMakeRange((rangeArray[0]).integerValue,(rangeArray[1]).integerValue);
        [attributeStr setTextHighlightRange:range
                                      color:TOSHexColor(0x4385FF)
                            backgroundColor:[UIColor colorWithWhite:0.000 alpha:0.220]
                                  tapAction:^(UIView *containerView, NSAttributedString *text, NSRange range, CGRect rect){
            __strong typeof(weadSelf) strongSelf = weadSelf;
            [strongSelf routerEventWithName:TinetRouterClickEventUrl
                                   userInfo:@{@"content"   : key}];
        }];
    }];
    
    NSArray *resultArray = [expression matchesInString:attributeStr.string options:0 range:NSMakeRange(0, attributeStr.string.length)];
    NSMutableArray *mutableArray = [NSMutableArray arrayWithCapacity:resultArray.count];
    for (NSTextCheckingResult *match in resultArray) {
        NSRange range    = match.range;
        NSString *subStr = [attributeStr.string substringWithRange:range];
        if ([subStr hasPrefix:@"http"] || [subStr hasPrefix:@"www"] || [subStr hasPrefix:@"ftp"]) {   //链接
            UIColor * highColor = fColor;
            if (TOSKitCustomInfo.shareCustomInfo.urlRegular != nil && TOSKitCustomInfo.shareCustomInfo.urlRegular.highlightColor != nil) {
                highColor = TOSKitCustomInfo.shareCustomInfo.urlRegular.highlightColor;
            }
            [attributeStr setTextHighlightRange:range
                                          color:highColor
                                backgroundColor:[UIColor colorWithWhite:0.000 alpha:0.220]
                                      tapAction:^(UIView *containerView, NSAttributedString *text, NSRange range, CGRect rect){
                __strong typeof(weadSelf) strongSelf = weadSelf;
                
                [strongSelf routerEventWithName:TinetRouterClickEventUrl
                                       userInfo:@{@"content"   : [text.string substringWithRange:range]}];
                
            }];
        } else if ([subStr hasPrefix:@"["] && [subStr hasSuffix:@"]"]) {    //表情
            
            NSArray *faceArr = [ICFaceManager customEmotion];
            for (XZEmotion *face in faceArr) {
                if ([face.face_name isEqualToString:subStr]) {
                    
                    UIImage *image = [[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,face.face_name]] imageWithRenderingMode:(UIImageRenderingModeAlwaysOriginal)];
                    image = [UIImage imageWithCGImage:image.CGImage scale:[UIScreen mainScreen].scale orientation:UIImageOrientationUp];
                    
                    
                    NSMutableAttributedString *attachText = [NSMutableAttributedString attachmentStringWithContent:image contentMode:UIViewContentModeScaleAspectFit attachmentSize:CGSizeMake(1.f*lineHeight, 1.f*lineHeight) alignToFont:font alignment:YYTextVerticalAlignmentCenter];
                    
                    [attachText addAttribute:NSKernAttributeName value:@(100) range:NSMakeRange(0, attachText.string.length)];
                    
                    NSMutableDictionary *imagDic   = [NSMutableDictionary dictionaryWithCapacity:2];
                    [imagDic setObject:attachText forKey:@"image"];
                    [imagDic setObject:[NSValue valueWithRange:range] forKey:@"range"];
                    
                    [mutableArray addObject:imagDic];
                }
            }
        } else {
            
            
            /// 判断电话正则是否为null。(tips：电话的优先级在订单号之上)
            if (TOSKitCustomInfo.shareCustomInfo.telRegular != nil) {
                if (TOSKitCustomInfo.shareCustomInfo.telRegular.regular.length > 0) {
                    NSRegularExpression *regex = [NSRegularExpression regularExpressionWithPattern:TOSKitCustomInfo.shareCustomInfo.telRegular.regular options:NSRegularExpressionCaseInsensitive error:nil];
                    NSArray *matches = [regex matchesInString:subStr options:NSMatchingReportProgress range:NSMakeRange(0, [subStr length])];
                    if (matches.count > 0) {
                        UIColor * highColor = fColor;
                        if (TOSKitCustomInfo.shareCustomInfo.telRegular != nil && TOSKitCustomInfo.shareCustomInfo.telRegular.highlightColor != nil) {
                            highColor = TOSKitCustomInfo.shareCustomInfo.telRegular.highlightColor;
                        }
                        [attributeStr setTextHighlightRange:range
                                                      color:highColor
                                            backgroundColor:[UIColor colorWithWhite:0.000 alpha:0.220]
                                                  tapAction:^(UIView *containerView, NSAttributedString *text, NSRange range, CGRect rect){

                            __strong typeof(weadSelf) strongSelf = weadSelf;
                            [strongSelf routerEventWithName:TinetRouterClickEventPhone
                                                   userInfo:@{@"content"   : [text.string substringWithRange:range]
                                                            }];

                        }];
                    }
                    else {
                        if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular != nil) {
                            if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.regular.length > 0) {
                                //订单号
                                NSLog(@"订单号匹配：%@", subStr);
                                UIColor * highColor = fColor;
                                if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.highlightColor != nil) {
                                    highColor = TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.highlightColor;
                                }
                                [attributeStr setTextHighlightRange:range
                                                              color:highColor
                                                    backgroundColor:[UIColor colorWithWhite:0.000 alpha:0.220]
                                                          tapAction:^(UIView *containerView, NSAttributedString *text, NSRange range, CGRect rect){
                                    __strong typeof(weadSelf) strongSelf = weadSelf;
                                    
                                    [strongSelf routerEventWithName:TinetRouterClickEventOrderNumber
                                                           userInfo:@{@"content"   : [text.string substringWithRange:range]
                                                                    }];
                                }];
                            }
                        }
                    }
                }
            }
            else {
                if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular != nil) {
                    if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.regular.length > 0) {
                        //订单号
                        NSLog(@"订单号匹配：%@", subStr);
                        UIColor * highColor = fColor;
                        if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.highlightColor != nil) {
                            highColor = TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.highlightColor;
                        }
                        [attributeStr setTextHighlightRange:range
                                                      color:highColor
                                            backgroundColor:[UIColor colorWithWhite:0.000 alpha:0.220]
                                                  tapAction:^(UIView *containerView, NSAttributedString *text, NSRange range, CGRect rect){
                            __strong typeof(weadSelf) strongSelf = weadSelf;
                            
                            [strongSelf routerEventWithName:TinetRouterClickEventOrderNumber
                                                   userInfo:@{@"content"   : [text.string substringWithRange:range]
                                                            }];
                        }];
                    }
                }
            }
            
        }
    }
    for (int i =(int) mutableArray.count - 1; i >= 0; i --) {
        NSRange range;
        [mutableArray[i][@"range"] getValue:&range];
        [attributeStr replaceCharactersInRange:range withAttributedString:mutableArray[i][@"image"]];
    }
    attributeStr.font = font;
    return attributeStr;
}

- (UIColor *)setTextLabelColor:(BOOL)isSender {
    if (isSender) {
        return [TOSKitCustomInfo shareCustomInfo].senderText_Color;
    } else {
        return [TOSKitCustomInfo shareCustomInfo].receiveText_Color;
    }
}

- (void)routerEventWithName:(NSString *)eventName userInfo:(NSDictionary *)userInfo {
    [[NSNotificationCenter defaultCenter] postNotificationName:kTIMRouterEventNotification object:@{
        @"eventName": eventName,
        @"userInfo": userInfo,
    }];
}

@end
