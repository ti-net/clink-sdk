//
//  ICChatMessageCommodityCardCell.m
//  TIMClientKit
//
//  Created by 赵言 on 2022/5/23.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "ICChatMessageCommodityCardCell.h"
#import "YYKit.h"
#import "UIImageView+TIMWebCache.h"
#import "kitUtils.h"
#import "UIImage+PureColorImage.h"
#import "UIImage+Extension.h"
#import "UIImage+TIMGIF.h"
#import <TOSClientLib/TOSClientKitCommodityCardOption.h>
#import "TIMMessageModel.h"
#import "TIMConstants.h"
#import <TOSClientKit/TOSKitCustomInfo.h>

@interface ICChatMessageCommodityCardCell ()

@property (weak, nonatomic) IBOutlet UIImageView *commodityPic;
@property (weak, nonatomic) IBOutlet UILabel *commodityTitle;
@property (weak, nonatomic) IBOutlet UILabel *price;
@property (weak, nonatomic) IBOutlet UIButton *sendBtn;
@property (weak, nonatomic) IBOutlet UILabel *descriptions;

@end

@implementation ICChatMessageCommodityCardCell

- (IBAction)didClickSenderCommodityCardBtnAction:(UIButton *)sender {
    [self routerEventWithName:TinetRouterSenderCommodityCardEventUrl
                     userInfo:@{@"content"   : @""
                              }];
}

- (void)setModelFrame:(TIMMessageFrame *)modelFrame {
    
    TOSClientKitCommodityCardOption *option = (TOSClientKitCommodityCardOption *)modelFrame.model.message.extra;
    
    if (![kitUtils isBlankString:option.img]) {
        self.commodityPic.hidden = NO;
        [self.commodityPic sd_setImageWithURL:[NSURL URLWithString:option.img]];
    } else {
        self.commodityPic.hidden = YES;
    }
    self.commodityTitle.text = option.subTitle?:@" ";
    if (![kitUtils isBlankString:option.price]) {
        self.price.text = [NSString stringWithFormat:@"%@",option.price?:@""];
    } else {
        self.price.text = @"";
    }
    
    if (![kitUtils isBlankString:option.descriptions]) {
        self.descriptions.text = [NSString stringWithFormat:@"%@",option.descriptions?:@" "];
    } else {
        self.descriptions.text = @" ";
    }
    
    [self.sendBtn setTitle:[NSString stringWithFormat:@"   %@   ",option.buttonText?:@"发送商品"] forState:(UIControlStateNormal)];
    
    [self.sendBtn setBackgroundColor:[TOSKitCustomInfo shareCustomInfo].CommodityCard_sendBtn_backgroundColor];
    [self.sendBtn setTitleColor:[TOSKitCustomInfo shareCustomInfo].CommodityCard_sendBtn_textColor forState:(UIControlStateNormal)];
    [self.descriptions setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCard_descriptions_textColor];
    [self.commodityTitle setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCard_title_textColor];
    [self.price setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCard_price_textColor];
}


- (void)awakeFromNib {
    [super awakeFromNib];
    
    self.backgroundColor = [UIColor clearColor];
    self.selectionStyle = UITableViewCellSelectionStyleNone;
    
    UILongPressGestureRecognizer *longRecognizer = [[UILongPressGestureRecognizer alloc] initWithTarget:self action:@selector(longPressRecognizer:)];
    longRecognizer.minimumPressDuration = 0.5;
    [self addGestureRecognizer:longRecognizer];
}

#pragma mark - longPress delegate

- (void)longPressRecognizer:(UILongPressGestureRecognizer *)recognizer
{
    if ([self.longPressDelegate respondsToSelector:@selector(longPress:)]) {
        [self.longPressDelegate longPress:recognizer];
    }
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
