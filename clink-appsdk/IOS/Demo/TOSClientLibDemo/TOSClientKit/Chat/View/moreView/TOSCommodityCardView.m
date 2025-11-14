//
//  TOSCommodityCardView.m
//  TOSClientKit
//
//  Created by 李成 on 2024/9/25.
//  Copyright © 2024 YanBo. All rights reserved.
//  聊天页面底部的商品卡片UI

#import "TOSCommodityCardView.h"
#import "YYKit.h"
#import "TIMConstants.h"
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

@interface TOSCommodityCardView ()

@property (nonatomic, strong) UIView                * bgView;

@property (nonatomic, strong) UIImageView                * commodityPic;

@property (nonatomic, strong) UILabel                * commodityTitle;

@property (nonatomic, strong) UILabel                * price;

@property (nonatomic, strong) UIButton                * sendBtn;

@property (nonatomic, strong) UILabel                * descriptionsLabel;

@property (nonatomic, strong) UIButton                * closeBtn;

@end



@implementation TOSCommodityCardView

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        
        self.backgroundColor = [UIColor colorWithHexString:@"#EFF0F3"];
        self.userInteractionEnabled = YES;
//        self.backgroundColor = UIColor.orangeColor;
        
        [self addSubview:self.bgView];
        [self.bgView addSubview:self.commodityPic];
        [self.bgView addSubview:self.commodityTitle];
        [self.bgView addSubview:self.closeBtn];
        [self.bgView addSubview:self.price];
        [self.bgView addSubview:self.descriptionsLabel];
        [self.bgView addSubview:self.sendBtn];
        TOSClientKitCommodityCardOption * option = [TOSKitCustomInfo shareCustomInfo].commodityCardOption;
        if (![kitUtils isBlankString:option.img]) {
            self.commodityPic.hidden = NO;
            [self.commodityPic sd_setImageWithURL:[NSURL URLWithString:[TOSKitCustomInfo shareCustomInfo].commodityCardOption.img]];
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
            self.descriptionsLabel.text = [NSString stringWithFormat:@"%@",option.descriptions?:@" "];
        } else {
            self.descriptionsLabel.text = @" ";
        }
        
        [self.sendBtn setTitle:[NSString stringWithFormat:@"   %@   ",option.buttonText?:@"发送商品"] forState:(UIControlStateNormal)];
        
        
        [self.sendBtn setBackgroundColor:[TOSKitCustomInfo shareCustomInfo].CommodityCard_sendBtn_backgroundColor];
        [self.sendBtn setTitleColor:[TOSKitCustomInfo shareCustomInfo].CommodityCard_sendBtn_textColor forState:(UIControlStateNormal)];
        [self.descriptionsLabel setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCard_descriptions_textColor];
        [self.commodityTitle setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCard_title_textColor];
        [self.price setTextColor:[TOSKitCustomInfo shareCustomInfo].CommodityCard_price_textColor];
        
        UITapGestureRecognizer * tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapTouch)];
        [self addGestureRecognizer:tap];
        
    }
    return self;
}


#pragma mark - action

- (void)closeTouch {
    
    if (self.delegate && [self.delegate respondsToSelector:@selector(TOSCommodityCardViewCloseTouch)]) {
        [self.delegate TOSCommodityCardViewCloseTouch];
    }
}

- (void)sendTouch {
    
    if (self.delegate && [self.delegate respondsToSelector:@selector(TOSCommodityCardViewSendTouch)]) {
        [self.delegate TOSCommodityCardViewSendTouch];
    }
    
}

- (void)tapTouch {
    
    if (self.delegate && [self.delegate respondsToSelector:@selector(TOSCommodityCardViewTapTouch)]) {
        [self.delegate TOSCommodityCardViewTapTouch];
    }
    
}


#pragma mark - lazy

- (UIView *)bgView {
    if (!_bgView) {
        _bgView = [[UIView alloc] initWithFrame:(CGRectMake(8.0, 8.0, self.frame.size.width-16.0, self.frame.size.height-16.0))];
        _bgView.backgroundColor = [UIColor colorWithHexString:@"#FFFFFF"];
        _bgView.layer.cornerRadius = 8.0f;
        _bgView.layer.masksToBounds = YES;
        
    }
    return _bgView;
}

- (UIImageView *)commodityPic {
    if (!_commodityPic) {
        _commodityPic = [[UIImageView alloc] initWithFrame:(CGRectMake(12.0, 12.0, 80.0, 80.0))];
        _commodityPic.layer.cornerRadius = 6.0f;
        _commodityPic.layer.masksToBounds = YES;
        _commodityPic.backgroundColor = UIColor.orangeColor;
        
    }
    return _commodityPic;
}

- (UILabel *)commodityTitle {
    if (!_commodityTitle) {
        _commodityTitle = [[UILabel alloc] initWithFrame:(CGRectMake(104.0, 12.0, self.bgView.bounds.size.width-104.0-12.0-16.0-12.0, 44.0))];
        _commodityTitle.numberOfLines = 2;
        _commodityTitle.font = [UIFont boldSystemFontOfSize:14.0f];
        
    }
    return _commodityTitle;
}

- (UIButton *)closeBtn {
    if (!_closeBtn) {
        _closeBtn = [[UIButton alloc] initWithFrame:(CGRectMake(self.bgView.bounds.size.width - 12.0-16.0, 15.0, 16.0, 16.0))];
        [_closeBtn setBackgroundImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"commodityCard-close"]] forState:(UIControlStateNormal)];
        [_closeBtn addTarget:self action:@selector(closeTouch) forControlEvents:(UIControlEventTouchUpInside)];
        
    }
    return _closeBtn;
}

- (UILabel *)price {
    if (!_price) {
        _price = [[UILabel alloc] initWithFrame:(CGRectMake(self.commodityTitle.left_sd, self.descriptionsLabel.bottom_sd+2.0, 100.0, 22.0))];
        _price.font = [UIFont systemFontOfSize:14.0f];
        
    }
    return _price;
}

- (UIButton *)sendBtn {
    if (!_sendBtn) {
        _sendBtn = [[UIButton alloc] initWithFrame:(CGRectMake(self.bgView.width_sd-80.0-12.0, self.descriptionsLabel.bottom_sd, 80.0, 22.0))];
        _sendBtn.titleLabel.font = [UIFont systemFontOfSize:14.0f];
        _sendBtn.layer.cornerRadius = 11.0f;
        _sendBtn.layer.masksToBounds = YES;
        [_sendBtn addTarget:self action:@selector(sendTouch) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _sendBtn;
}

- (UILabel *)descriptionsLabel {
    if (!_descriptionsLabel) {
        _descriptionsLabel = [[UILabel alloc] initWithFrame:(CGRectMake(self.commodityTitle.left_sd, self.commodityTitle.bottom_sd, self.commodityTitle.width_sd, 18.0f))];
        _descriptionsLabel.font = [UIFont systemFontOfSize:12.0f];
        
    }
    return _descriptionsLabel;
}


/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
