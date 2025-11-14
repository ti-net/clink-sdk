//
//  ICChatMessageCommodityCardDetailsCell.m
//  TIMClientKit
//
//  Created by 赵言 on 2022/5/24.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "ICChatMessageCommodityCardDetailsCell.h"
#import "TIMConstants.h"
#import "ICTransportDetailsTableCell.h"

#import "YYKit.h"
#import "UIImageView+TIMWebCache.h"
#import "kitUtils.h"
#import "UIImage+PureColorImage.h"
#import "UIImage+Extension.h"
#import "UIImage+TIMGIF.h"
#import <TOSClientLib/TIMCommodityCardMessage.h>
#import "TIMMessageModel.h"
#import "TIMICMessage.h"
#import <TOSClientKit/TOSKitCustomInfo.h>

@interface ICChatMessageCommodityCardDetailsCell () <UITableViewDelegate, UITableViewDataSource>

@property (strong, nonatomic) UIView *bottomView;

@property (strong, nonatomic) UILabel *orderNumber;
@property (strong, nonatomic) UILabel *time;

@property (strong, nonatomic) UIView *topCuttingLine;

@property (strong, nonatomic) UIImageView *commodityPic;
@property (strong, nonatomic) UILabel *commodityTitle;
@property (strong, nonatomic) UILabel *commoditySubTitle;
@property (strong, nonatomic) UILabel *price;

@property (strong, nonatomic) UIView *bottomCuttingLine;

@property (strong, nonatomic) UILabel *transportTitle;
@property (strong, nonatomic) UILabel *transportStatus;
@property (strong, nonatomic) UIButton *foldAndUnfoldBtn;
@property (strong, nonatomic) UIImageView *foldAndUnfoldIcon;
@property (strong, nonatomic) UITableView *transportDetails;

@property (strong, nonatomic) UIButton *cellClickBtn;


@property (strong, nonatomic) NSArray <NSDictionary <NSString *, NSString *>*>*extraInfo;

@property (strong, nonatomic) TIMMessageFrame *cardModelFrame;

@end

@implementation ICChatMessageCommodityCardDetailsCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        self.bubbleView.hidden = YES;
        [self.contentView addSubview:self.bottomView];
        
        [self.bottomView addSubview:self.orderNumber];
        [self.bottomView addSubview:self.time];
        [self.bottomView addSubview:self.topCuttingLine];
        [self.bottomView addSubview:self.commodityPic];
        [self.bottomView addSubview:self.commodityTitle];
        [self.bottomView addSubview:self.commoditySubTitle];
        [self.bottomView addSubview:self.transportDetails];
        [self.bottomView addSubview:self.price];
        [self.bottomView addSubview:self.bottomCuttingLine];
        [self.bottomView addSubview:self.transportTitle];
        [self.bottomView addSubview:self.foldAndUnfoldIcon];
        [self.bottomView addSubview:self.transportStatus];
        
        [self.bottomView addSubview:self.cellClickBtn];
        
        [self.bottomView addSubview:self.foldAndUnfoldBtn];
        
        
        self.extraInfo = [NSArray array];
        [self.transportDetails registerNib:[UINib nibWithNibName:@"TOSClient.bundle/ICTransportDetailsTableCell" bundle:nil] forCellReuseIdentifier:[ICTransportDetailsTableCell className]];
        if (@available(iOS 15.0, *)) {
            self.transportDetails.sectionHeaderTopPadding = 0.f;
            [UITableView appearance].sectionHeaderTopPadding = 0.f;
        }
    }
    return self;
}

- (void)setModelFrame:(TIMMessageFrame *)modelFrame {
    [super setModelFrame:modelFrame];
    self.cardModelFrame = modelFrame;
    
    self.bottomView.frame = modelFrame.bottomViewF;
    self.cellClickBtn.frame = modelFrame.cellClickBtnF;
    
    self.orderNumber.frame = modelFrame.orderNumberF;
    self.time.frame = modelFrame.timeF;
    self.topCuttingLine.frame = modelFrame.topCuttingLineF;
    self.commodityPic.frame = modelFrame.commodityPicF;
    self.commodityTitle.frame = modelFrame.commodityTitleF;
    self.commoditySubTitle.frame = modelFrame.commoditySubTitleF;
    self.price.frame = modelFrame.priceF;
    self.bottomCuttingLine.frame = modelFrame.bottomCuttingLineF;
    self.transportTitle.frame = modelFrame.transportTitleF;
    self.transportStatus.frame = modelFrame.transportStatusF;
    self.foldAndUnfoldBtn.frame = modelFrame.foldAndUnfoldBtnF;
    self.foldAndUnfoldIcon.frame = modelFrame.foldAndUnfoldIconF;
    self.transportDetails.frame = modelFrame.transportDetailsF;
    
    
    if (modelFrame.model.isSender) {
        [self setBubbleColor:YES];
        [self.orderNumber setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_orderId_sender_textColor];
        [self.time setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_time_sender_textColor];
        [self.commodityTitle setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_title_sender_textColor];
        [self.commoditySubTitle setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_description_sender_textColor];
        [self.price setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_price_sender_textColor];
        [self.transportTitle setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_transportStatus_sender_textColor];
        [self.transportStatus setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_transportStatus_sender_textColor];
        [self.foldAndUnfoldBtn setTitleColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_transportStatus_sender_textColor forState:(UIControlStateNormal)];
        [self.foldAndUnfoldIcon setTintColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_transportStatus_sender_textColor];
    } else {
        [self setBubbleColor:NO];
        [self.orderNumber setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_orderId_receive_textColor];
        [self.time setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_time_receive_textColor];
        [self.commodityTitle setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_title_receive_textColor];
        [self.commoditySubTitle setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_description_receive_textColor];
        [self.price setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_price_receive_textColor];
        [self.transportTitle setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_transportStatus_receive_textColor];
        [self.transportStatus setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_transportStatus_receive_textColor];
        [self.foldAndUnfoldBtn setTitleColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_transportStatus_receive_textColor forState:(UIControlStateNormal)];
        [self.foldAndUnfoldIcon setTintColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_transportStatus_receive_textColor];
    }
    TIMCommodityCardMessage *option = (TIMCommodityCardMessage *)modelFrame.model.message.extra;
    
    if (![kitUtils isBlankString:option.img]) {
        self.commodityPic.hidden = NO;
        [self.commodityPic sd_setImageWithURL:[NSURL URLWithString:option.img]];
    } else {
        self.commodityPic.hidden = YES;
    }
    
    NSInteger extraInfoCount = 0;
    __block NSString *orderNumber = @"";
    if ([option.extraInfo isKindOfClass:[NSArray class]]) {
        extraInfoCount = option.extraInfo.count;
        [option.extraInfo enumerateObjectsUsingBlock:^(NSDictionary<NSString *,NSString *> * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            
            if ([obj[@"name"] isEqualToString:@"订单号"]) {
                orderNumber = obj[@"value"];
                *stop = YES;
            }
        }];
    }
    
    if (orderNumber &&
        orderNumber.length > 0) {
        self.orderNumber.text = [NSString stringWithFormat:@"订单号：%@",orderNumber];
    } else {
        self.orderNumber.text = @" ";
    }
    
    if (orderNumber.length) {
        extraInfoCount -= 1;
    }
    
    if (extraInfoCount > 0) {
        NSMutableArray * updateArray = [NSMutableArray array];
        for (NSDictionary * item in option.extraInfo) {
            if (![item[@"name"] isEqualToString:@"订单号"]) {
                [updateArray addObject:item];
            }
        }
        self.extraInfo = updateArray;
    }
    
    [self.transportDetails reloadData];
        
    if (option.extraInfo &&
        [option.extraInfo isKindOfClass:[NSArray class]] &&
        extraInfoCount > 3) {
        self.foldAndUnfoldBtn.hidden = NO;
        self.foldAndUnfoldIcon.hidden = NO;
    } else {
        self.foldAndUnfoldBtn.hidden = YES;
        self.foldAndUnfoldIcon.hidden = YES;
    }
    
    self.time.text = option.time?:@" ";
    self.commodityTitle.text = option.subTitle?:@" ";
    self.commoditySubTitle.text = option.descriptions?:@" ";
    if (![kitUtils isBlankString:option.price]) {
        self.price.text = [NSString stringWithFormat:@"%@",option.price?:@" "];
    } else {
        self.price.text = @" ";
    }
    
    if ([kitUtils isBlankString:option.status]) {
        self.transportStatus.text = @" ";
        self.transportTitle.text = @" ";
    } else {
        self.transportStatus.text = option.status;
        self.transportTitle.text = @"到货状态：";
    }
    
    
    self.foldAndUnfoldBtn.selected = modelFrame.foldAndUnfold;
    if (self.foldAndUnfoldBtn.isSelected) {
        self.foldAndUnfoldIcon.image = [[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"topArrow"]] imageWithRenderingMode:(UIImageRenderingModeAlwaysTemplate)];
    } else {
        self.foldAndUnfoldIcon.image = [[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"bottomArrow"]] imageWithRenderingMode:(UIImageRenderingModeAlwaysTemplate)];
    }
}

- (void)didClickFoldAndUnfoldBtnAction:(UIButton *)sender {
    sender.selected = !sender.selected;
    self.cardModelFrame.foldAndUnfold = sender.isSelected;
    if (sender.isSelected) {
        self.foldAndUnfoldIcon.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"topArrow"]];
    } else {
        self.foldAndUnfoldIcon.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"bottomArrow"]];
    }
    
    //此段代码为触发model的setModel
    self.cardModelFrame.model = self.cardModelFrame.model;
    [self routerEventWithName:GXRobotCombinationHotIssueCellRefreshEventName
                     userInfo:@{MessageKey:self.cardModelFrame}];
}

- (void)setBubbleColor:(BOOL)isSender{
    if (isSender) {
        self.bottomView.backgroundColor = [TOSKitCustomInfo shareCustomInfo].senderBubble_backGround;
    } else {
        self.bottomView.backgroundColor = [TOSKitCustomInfo shareCustomInfo].receiveBubble_backGround;
    }
}

- (void)didClickCellAction:(UIButton *)sender {
    [self routerEventWithName:TinetRouterClickCommodityCardEvent
                     userInfo:@{@"content"   : [self.cardModelFrame.model yy_modelToJSONObject]
                              }];
}

#pragma mark - UITableViewDataSource
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    if (![self.extraInfo isKindOfClass:[NSArray class]] ||
        self.extraInfo.count <= 0) {
        return 0;
    }
    if (self.cardModelFrame.foldAndUnfold) {
        return self.extraInfo.count >= 3 ? 3 : self.extraInfo.count;
    } else {
        return self.extraInfo.count;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    NSString *reuseIdentifie = [ICTransportDetailsTableCell className];
    ICTransportDetailsTableCell *cell = [tableView dequeueReusableCellWithIdentifier:reuseIdentifie];
    NSDictionary *dic = self.extraInfo[indexPath.row];
    cell.name.text = [NSString stringWithFormat:@"%@：",dic[@"name"]];
    cell.value.text = dic[@"value"];
    
    if (self.cardModelFrame.model.isSender) {
        [cell.name setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_transportStatus_sender_textColor];
        [cell.value setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_transportStatus_sender_textColor];
    } else {
        [cell.name setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_transportStatus_receive_textColor];
        [cell.value setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCardDetails_transportStatus_receive_textColor];
    }
    return cell;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

#pragma mark - 初始化
- (UIButton *)cellClickBtn {
    if (!_cellClickBtn) {
        _cellClickBtn = [UIButton buttonWithType:(UIButtonTypeSystem)];
        [_cellClickBtn addTarget:self action:@selector(didClickCellAction:) forControlEvents:(UIControlEventTouchUpInside)];
        _cellClickBtn.userInteractionEnabled = YES;
    }
    return _cellClickBtn;
}

- (UILabel *)orderNumber {
    if (!_orderNumber) {
        _orderNumber = [[UILabel alloc] initWithFrame:CGRectZero];
        _orderNumber.textColor = TOSHexColor(0x1366DC);
        _orderNumber.font = MessageFont12;
        _orderNumber.userInteractionEnabled = YES;
    }
    return _orderNumber;
}

- (UILabel *)time {
    if (!_time) {
        _time = [[UILabel alloc] initWithFrame:CGRectZero];
        _time.textColor = TOSHexColor(0xAFB2BC);
        _time.font = MessageFont12;
        _time.userInteractionEnabled = YES;
        _time.textAlignment = NSTextAlignmentRight;
    }
    return _time;
}

- (UIView *)topCuttingLine {
    if (!_topCuttingLine) {
        _topCuttingLine = [[UIView alloc] initWithFrame:CGRectZero];
        _topCuttingLine.backgroundColor = TOSHexColor(0xAFB2BC);
        _topCuttingLine.userInteractionEnabled = YES;
    }
    return _topCuttingLine;
}

- (UIImageView *)commodityPic {
    if (!_commodityPic) {
        _commodityPic = [[UIImageView alloc] initWithFrame:CGRectZero];
        _commodityPic.contentMode = UIViewContentModeScaleAspectFit;
        _commodityPic.userInteractionEnabled = YES;
    }
    return _commodityPic;
}

- (UILabel *)commodityTitle {
    if (!_commodityTitle) {
        _commodityTitle = [[UILabel alloc] initWithFrame:CGRectZero];
        _commodityTitle.textColor = TOSHexColor(0x8A8D9A);
        _commodityTitle.font = [UIFont fontWithName:@"PingFangSC-Medium" size:12.0];
        _commodityTitle.userInteractionEnabled = YES;
        _commodityTitle.numberOfLines = 2;
    }
    return _commodityTitle;
}

- (UILabel *)commoditySubTitle {
    if (!_commoditySubTitle) {
        _commoditySubTitle = [[UILabel alloc] initWithFrame:CGRectZero];
        _commoditySubTitle.textColor = TOSHexColor(0xAFB2BC);
        _commoditySubTitle.font = MessageFont12;
        _commoditySubTitle.userInteractionEnabled = YES;
    }
    return _commoditySubTitle;
}

- (UILabel *)price {
    if (!_price) {
        _price = [[UILabel alloc] initWithFrame:CGRectZero];
        _price.textColor = TOSHexColor(0x8A8D9A);
        _price.font = [UIFont fontWithName:@"PingFangSC-Medium" size:12.0];
        _price.userInteractionEnabled = YES;
    }
    return _price;
}

- (UIView *)bottomCuttingLine {
    if (!_bottomCuttingLine) {
        _bottomCuttingLine = [[UIView alloc] initWithFrame:CGRectZero];
        _bottomCuttingLine.backgroundColor = TOSHexColor(0xAFB2BC);
        _bottomCuttingLine.userInteractionEnabled = YES;
    }
    return _bottomCuttingLine;
}


- (UILabel *)transportTitle {
    if (!_transportTitle) {
        _transportTitle = [[UILabel alloc] initWithFrame:CGRectZero];
        _transportTitle.textColor = TOSHexColor(0xAFB2BC);
        _transportTitle.font = MessageFont12;
        _transportTitle.userInteractionEnabled = YES;
        _transportTitle.text = @"到货状态：";
    }
    return _transportTitle;
}

- (UILabel *)transportStatus {
    if (!_transportStatus) {
        _transportStatus = [[UILabel alloc] initWithFrame:CGRectZero];
        _transportStatus.textColor = TOSHexColor(0x8A8D9A);
        _transportStatus.font = MessageFont12;
        _transportStatus.userInteractionEnabled = YES;
    }
    return _transportStatus;
}

- (UIButton *)foldAndUnfoldBtn {
    if (!_foldAndUnfoldBtn) {
        _foldAndUnfoldBtn = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _foldAndUnfoldBtn.titleLabel.font = MessageFont12;
        [_foldAndUnfoldBtn setTitleColor:TOSHexColor(0xAFB2BC) forState:(UIControlStateNormal)];
        [_foldAndUnfoldBtn setTitle:@"收起" forState:(UIControlStateNormal)];
        [_foldAndUnfoldBtn setTitle:@"展开" forState:(UIControlStateSelected)];
        _foldAndUnfoldBtn.selected = YES;
        _foldAndUnfoldBtn.userInteractionEnabled = YES;
        [_foldAndUnfoldBtn addTarget:self action:@selector(didClickFoldAndUnfoldBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _foldAndUnfoldBtn;
}

- (UIImageView *)foldAndUnfoldIcon {
    if (!_foldAndUnfoldIcon) {
        _foldAndUnfoldIcon = [[UIImageView alloc] initWithFrame:CGRectZero];
        _foldAndUnfoldIcon.contentMode = UIViewContentModeCenter;
        _foldAndUnfoldIcon.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"bottomArrow"]];
        _foldAndUnfoldIcon.userInteractionEnabled = YES;
    }
    return _foldAndUnfoldIcon;
}

- (UITableView *)transportDetails {
    if (!_transportDetails) {
        _transportDetails = [[UITableView alloc] initWithFrame:CGRectZero style:(UITableViewStylePlain)];
        _transportDetails.separatorStyle = UITableViewCellSeparatorStyleNone;
        _transportDetails.delegate = self;
        _transportDetails.dataSource = self;
        _transportDetails.rowHeight = 21.f;
        _transportDetails.backgroundColor = [UIColor clearColor];
        _transportDetails.userInteractionEnabled = YES;
    }
    return _transportDetails;
}

- (UIView *)bottomView {
    if (!_bottomView) {
        _bottomView = [[UIView alloc] initWithFrame:CGRectZero];
        _bottomView.layer.cornerRadius = 8.f;
        _bottomView.layer.masksToBounds = YES;
        _bottomView.userInteractionEnabled = YES;
        _bottomView.clipsToBounds = YES;
    }
    return _bottomView;
}

@end
