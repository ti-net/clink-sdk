//
//  ICChatMessageSmallProgramCardCell.m
//  TOSClientKit
//
//  Created by 言 on 2022/12/30.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "ICChatMessageSmallProgramCardCell.h"
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
#import <TOSClientLib/TOSMessageSmallProgramModel.h>
#import "NSObject+TIMShowError.h"

#import "YYKit.h"
#import "UIImageView+TIMWebCache.h"
#import "kitUtils.h"
#import "UIImage+PureColorImage.h"
#import "UIImage+Extension.h"
#import "UIImage+TIMGIF.h"
#import <TOSClientLib/TIMCommodityCardMessage.h>
#import "TIMMessageModel.h"
#import "TIMICMessage.h"

@interface ICChatMessageSmallProgramCardCell ()

@property (strong, nonatomic) UIImageView *appLogo;
@property (nonatomic, strong) UILabel *appName;
@property (nonatomic, strong) UILabel *titleLabel;


@property (strong, nonatomic) UIImageView *picurl;

@property (nonatomic, strong) UIView *line;

@property (strong, nonatomic) UIImageView *icon;
@property (nonatomic, strong) UILabel *iconText;

@property (nonatomic, strong) UIButton *cellBtn;

@property (nonatomic, strong) UIButton *copyBtn;

@property (nonatomic, strong) TOSMessageSmallProgramModel *smallProgramMsg;

@end

@implementation ICChatMessageSmallProgramCardCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        self.bubbleView.userInteractionEnabled = YES;
        [self.bubbleView addSubview:self.appLogo];
        [self.bubbleView addSubview:self.appName];
        [self.bubbleView addSubview:self.titleLabel];
        [self.bubbleView addSubview:self.picurl];
        [self.bubbleView addSubview:self.line];
        [self.bubbleView addSubview:self.icon];
        [self.bubbleView addSubview:self.iconText];
        [self.bubbleView addSubview:self.cellBtn];
        [self.bubbleView addSubview:self.copyBtn];
    }
    return self;
}

- (UIColor *)setTextLabelColor:(BOOL)isSender {
    if (isSender) {
        return [TOSKitCustomInfo shareCustomInfo].senderText_Color;
    } else {
        return [TOSKitCustomInfo shareCustomInfo].receiveText_Color;
    }
}

#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    
    self.cellBtn.frame = modelFrame.bubbleViewF;
    
    TOSMessageSmallProgramModel *smallProgramMsg = (TOSMessageSmallProgramModel *)modelFrame.model.message.extra;
    self.smallProgramMsg = smallProgramMsg;
    
    self.appLogo.frame = modelFrame.smallProgram_appLogoF;
    [self.appLogo sd_setImageWithURL:[NSURL URLWithString:smallProgramMsg.appLogo]];
    
    self.appName.frame = modelFrame.smallProgram_appNameF;
    self.appName.text = smallProgramMsg.appName?:@"";
    
    self.titleLabel.frame = modelFrame.smallProgram_titleF;

    self.picurl.frame = modelFrame.smallProgram_picurlF;
    [self.picurl sd_setImageWithURL:[NSURL URLWithString:smallProgramMsg.picurl]];

    self.line.frame = modelFrame.smallProgram_lineF;

    self.icon.frame = modelFrame.smallProgram_iconF;
    self.iconText.frame = modelFrame.smallProgram_iconTextF;

    self.copyBtn.frame = modelFrame.smallProgram_copyBtnF;
    
    self.titleLabel.text = smallProgramMsg.title?:@"";
}

#pragma mark - Getter and Setter
- (UIImageView *)appLogo {
    if (!_appLogo) {
        _appLogo = [[UIImageView alloc] initWithFrame:CGRectZero];
        _appLogo.userInteractionEnabled = YES;
    }
    return _appLogo;
}

- (UILabel *)appName {
    if (!_appName) {
        _appName = [[UILabel alloc] init];
        _appName.textColor = TOSHexColor(0xBFBFBF);
        _appName.font = MessageFont12;
        _appName.userInteractionEnabled = YES;
    }
    return _appName;
}

- (UILabel *)titleLabel
{
    if (!_titleLabel) {
        _titleLabel = [[UILabel alloc] init];
        _titleLabel.numberOfLines = 2;
        _titleLabel.font = MessageFont;
        _titleLabel.textColor = TOSHexColor(0x262626);
        _titleLabel.userInteractionEnabled = YES;
    }
    return _titleLabel;
}


- (UIImageView *)picurl {
    if (!_picurl) {
        _picurl = [[UIImageView alloc] initWithFrame:CGRectZero];
        _picurl.layer.cornerRadius = 8.f;
        _picurl.layer.masksToBounds = YES;
        _picurl.userInteractionEnabled = YES;
    }
    return _picurl;
}

- (UIView *)line {
    if (!_line) {
        _line = [[UIView alloc] initWithFrame:CGRectZero];
        _line.backgroundColor = TOSHexAColor(0x000000, 0.06f);
        _line.userInteractionEnabled = YES;
    }
    return _line;
}

- (UIImageView *)icon {
    if (!_icon) {
        _icon = [[UIImageView alloc] initWithFrame:CGRectZero];
        _icon.image = [UIImage imageNamed:kTOSClientResourcesPath(@"smallProgram_link")];
        _icon.userInteractionEnabled = YES;
    }
    return _icon;
}

- (UILabel *)iconText {
    if (!_iconText) {
        _iconText = [[UILabel alloc] init];
        _iconText.text = @"小程序";
        _iconText.textColor = TOSHexColor(0xBFBFBF);
        _iconText.font = MessageFont12;
        _iconText.userInteractionEnabled = YES;
    }
    return _iconText;
}

- (UIButton *)cellBtn {
    if (!_cellBtn) {
        _cellBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [_cellBtn addTarget:self action:@selector(didClickCellBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _cellBtn;
}

- (UIButton *)copyBtn {
    if (!_copyBtn) {
        _copyBtn = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _copyBtn.titleLabel.font = MessageFont12;
        [_copyBtn setTitleColor:TOSHexColor(0x4385FF) forState:(UIControlStateNormal)];
        [_copyBtn setTitle:@"复制链接" forState:(UIControlStateNormal)];
        [_copyBtn addTarget:self action:@selector(didClickCopyBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _copyBtn;
}

- (void)didClickCopyBtnAction:(UIButton *)sender {
    [self tim_showMBErrorView:@"复制成功"];
    UIPasteboard *pasteboard  = [UIPasteboard generalPasteboard];
    pasteboard.string         = self.smallProgramMsg.pagepath?:@"";
}

- (void)didClickCellBtnAction:(UIButton *)sender {
    [self routerEventWithName:TinetRouterClickEventMiniProgramCard
                     userInfo:@{@"content"   : [self.smallProgramMsg yy_modelToJSONObject]}];
}

@end
