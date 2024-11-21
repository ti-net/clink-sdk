//
//  ICChatMessageLogisticsCardCell.m
//  TOSClientKit
//
//  Created by 言 on 2023/1/4.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import "ICChatMessageLogisticsCardCell.h"
#import "TIMMessageModel.h"
#import "ICFaceManager.h"
#import "TIMConstants.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import "NSDictionary+TIMTool.h"
#import "XZEmotion.h"
#import "YYLabel.h"
#import "TOSCustomerChatVC.h"
#import "STBaseWebViewController.h"
#import <TOSClientLib/TOSClientLib.h>
#import <TOSClientKit/TOSClientKit.h>
#import "NSObject+TIMShowError.h"

#import "YYKit.h"
#import "UIImageView+TIMWebCache.h"
#import "kitUtils.h"
#import "UIImage+PureColorImage.h"
#import "UIImage+Extension.h"
#import "UIImage+TIMGIF.h"
#import "TIMMessageModel.h"
#import "TIMICMessage.h"
#import <TOSClientLib/TIMLogisticsCardMessage.h>

@interface ICChatMessageLogisticsCardCell ()

@property (strong, nonatomic) UILabel *startDate;
@property (strong, nonatomic) UILabel *commodityName;

@property (strong, nonatomic) UIView *startIcon;
@property (strong, nonatomic) UIView *verticalLine;
@property (strong, nonatomic) UIView *endIcon;

@property (strong, nonatomic) UILabel *startingPoint;
@property (strong, nonatomic) UILabel *endPoint;

@property (strong, nonatomic) UILabel *commodityAmount;
@property (strong, nonatomic) UIButton *commodityQuantityBtn;

@property (strong, nonatomic) UIView *horizontalLine;

@property (strong, nonatomic) UILabel *orderNumber;

@property (strong, nonatomic) UIButton *copyBtn;
@property (strong, nonatomic) TIMLogisticsCardMessage *cardMsg;

@end

@implementation ICChatMessageLogisticsCardCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        self.bubbleView.userInteractionEnabled = YES;
        [self.bubbleView addSubview:self.startDate];
        [self.bubbleView addSubview:self.commodityName];
        
        [self.bubbleView addSubview:self.startIcon];
        [self.bubbleView addSubview:self.verticalLine];
        [self.bubbleView addSubview:self.endIcon];
        
        [self.bubbleView addSubview:self.startingPoint];
        [self.bubbleView addSubview:self.endPoint];
        
        [self.bubbleView addSubview:self.commodityAmount];
        [self.bubbleView addSubview:self.commodityQuantityBtn];
        
        [self.bubbleView addSubview:self.horizontalLine];
        
        [self.bubbleView addSubview:self.orderNumber];
        [self.bubbleView addSubview:self.copyBtn];
    }
    return self;
}

#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    
    TIMLogisticsCardMessage *cardMsg = (TIMLogisticsCardMessage *)modelFrame.model.message.extra;
    self.cardMsg = cardMsg;
    
    self.startDate.frame = modelFrame.logisticsCard_startDateF;
    self.startDate.text = cardMsg.createTime?:@"";
    self.commodityName.frame = modelFrame.logisticsCard_commodityNameF;
    self.commodityName.text = cardMsg.goodsName?:@"";
 
    self.startIcon.frame = modelFrame.logisticsCard_startIconF;
    self.verticalLine.frame = modelFrame.logisticsCard_verticalLineF;
    self.endIcon.frame = modelFrame.logisticsCard_endIconF;

    self.startingPoint.frame = modelFrame.logisticsCard_startingPointF;
    self.startingPoint.text = cardMsg.senderName?:@"";
    self.endPoint.frame = modelFrame.logisticsCard_endPointF;
    self.endPoint.text = cardMsg.recipientName?:@"";

    self.commodityAmount.frame = modelFrame.logisticsCard_commodityAmountF;
    self.commodityAmount.text = cardMsg.goodsAmount?:@"";
    self.commodityQuantityBtn.frame = modelFrame.logisticsCard_commodityQuantityBtnF;
    [self.commodityQuantityBtn setTitle:cardMsg.goodsQuantity?:@"" forState:(UIControlStateNormal)];

    self.horizontalLine.frame = modelFrame.logisticsCard_horizontalLineF;

    self.orderNumber.frame = modelFrame.logisticsCard_orderNumberF;
    self.orderNumber.text = cardMsg.orderNumber?:@"";
    self.copyBtn.frame = modelFrame.logisticsCard_copyBtnF;
}

#pragma mark - Getter and Setter
- (UILabel *)startDate {
    if (!_startDate) {
        _startDate = [[UILabel alloc] initWithFrame:CGRectZero];
        _startDate.textColor = TOSHexColor(0x595959);
        _startDate.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        _startDate.userInteractionEnabled = YES;
        _startDate.numberOfLines = 1;
    }
    return _startDate;
}

- (UILabel *)commodityName {
    if (!_commodityName) {
        _commodityName = [[UILabel alloc] initWithFrame:CGRectZero];
        _commodityName.textColor = TOSHexColor(0x595959);
        _commodityName.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        _commodityName.userInteractionEnabled = YES;
        _commodityName.numberOfLines = 0;
    }
    return _commodityName;
}

- (UIView *)startIcon {
    if (!_startIcon) {
        _startIcon = [[UIView alloc] initWithFrame:CGRectZero];
        _startIcon.backgroundColor = TOSHexColor(0x4385FF);
        _startIcon.layer.masksToBounds = YES;
        _startIcon.layer.cornerRadius = 4.f;
    }
    return _startIcon;
}

- (UIView *)verticalLine {
    if (!_verticalLine) {
        _verticalLine = [[UIView alloc] initWithFrame:CGRectZero];
        _verticalLine.backgroundColor = TOSHexAColor(0x000000, 0.1f);
    }
    return _verticalLine;
}

- (UIView *)endIcon {
    if (!_endIcon) {
        _endIcon = [[UIView alloc] initWithFrame:CGRectZero];
        _endIcon.backgroundColor = TOSHexColor(0xFF4D4F);
        _endIcon.layer.masksToBounds = YES;
        _endIcon.layer.cornerRadius = 4.f;
    }
    return _endIcon;
}

- (UILabel *)startingPoint {
    if (!_startingPoint) {
        _startingPoint = [[UILabel alloc] initWithFrame:CGRectZero];
        _startingPoint.textColor = TOSHexColor(0x262626);
        _startingPoint.font = [UIFont fontWithName:@"PingFangSC-Medium" size:16.f];
        _startingPoint.userInteractionEnabled = YES;
        _startingPoint.numberOfLines = 0;
    }
    return _startingPoint;
}

- (UILabel *)endPoint {
    if (!_endPoint) {
        _endPoint = [[UILabel alloc] initWithFrame:CGRectZero];
        _endPoint.textColor = TOSHexColor(0x262626);
        _endPoint.font = [UIFont fontWithName:@"PingFangSC-Medium" size:16.f];
        _endPoint.userInteractionEnabled = YES;
        _endPoint.numberOfLines = 0;
    }
    return _endPoint;
}

- (UILabel *)commodityAmount {
    if (!_commodityAmount) {
        _commodityAmount = [[UILabel alloc] initWithFrame:CGRectZero];
        _commodityAmount.textColor = TOSHexColor(0x4385FF);
        _commodityAmount.font = [UIFont fontWithName:@"PingFangSC-Semibold" size:16.f];
        _commodityAmount.userInteractionEnabled = YES;
        _commodityAmount.numberOfLines = 1;
    }
    return _commodityAmount;
}

- (UIButton *)commodityQuantityBtn {
    if (!_commodityQuantityBtn) {
        _commodityQuantityBtn = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _commodityQuantityBtn.titleLabel.font = MessageFont12;
        [_commodityQuantityBtn setTitleColor:TOSHexColor(0xFFFFFF)
                                    forState:(UIControlStateNormal)];
        [_commodityQuantityBtn setBackgroundColor:TOSHexColor(0x4385FF)];
        _commodityQuantityBtn.layer.cornerRadius = 2.f;
        _commodityQuantityBtn.layer.masksToBounds = YES;
        _commodityQuantityBtn.userInteractionEnabled = YES;
        [_commodityQuantityBtn addTarget:self
                                  action:@selector(didClickCommodityQuantityBtnAction:)
                        forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _commodityQuantityBtn;
}

- (UIView *)horizontalLine {
    if (!_horizontalLine) {
        _horizontalLine = [[UIView alloc] initWithFrame:CGRectZero];
        _horizontalLine.backgroundColor = TOSHexColor(0xF0F1F7);
    }
    return _horizontalLine;
}

- (UILabel *)orderNumber {
    if (!_orderNumber) {
        _orderNumber = [[UILabel alloc] initWithFrame:CGRectZero];
        _orderNumber.textColor = TOSHexColor(0x595959);
        _orderNumber.font = [UIFont fontWithName:@"PingFangSC-Regular" size:13.f];
        _orderNumber.userInteractionEnabled = YES;
        _orderNumber.numberOfLines = 1;
    }
    return _orderNumber;
}

- (UIButton *)copyBtn {
    if (!_copyBtn) {
        _copyBtn = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_copyBtn setBackgroundImage:[UIImage imageNamed:kTOSClientResourcesPath(@"logisticsCard_copy")] forState:(UIControlStateNormal)];
        [_copyBtn addTarget:self
                                  action:@selector(didClickCopyBtnAction:)
                        forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _copyBtn;
}

- (void)didClickCopyBtnAction:(UIButton *)sender {
    [self tim_showMBErrorView:@"复制成功"];
    UIPasteboard *pasteboard  = [UIPasteboard generalPasteboard];
    pasteboard.string         = [self.cardMsg.orderNumber?:@"00000" substringFromIndex:5];
}

- (void)didClickCommodityQuantityBtnAction:(UIButton *)sender {
    [self routerEventWithName:TinetRouterClickEventLogisticsCard
                     userInfo:@{@"content"   : [self.cardMsg yy_modelToJSONObject]}];
}

@end
