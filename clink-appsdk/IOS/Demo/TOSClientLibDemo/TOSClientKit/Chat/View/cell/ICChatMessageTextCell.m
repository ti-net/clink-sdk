//
//  ICChatMessageTextCell.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/13.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICChatMessageTextCell.h"
#import "TIMMessageModel.h"
#import "ICFaceManager.h"
#import "TIMConstants.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import "NSDictionary+TIMTool.h"
#import "YYKit.h"
#import "XZEmotion.h"
#import "TOSCustomerChatVC.h"
#import "STBaseWebViewController.h"
#import <TOSClientKit/TOSClientKit.h>
#import "kitUtils.h"

@interface ICChatMessageTextCell ()

@property (nonatomic, strong) UILabel *sensitiveWordsLabel;

@end

@implementation ICChatMessageTextCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.chatLabel];
        [self.contentView addSubview:self.sensitiveWordsLabel];
    }
    return self;
}

#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    self.chatLabel.frame = modelFrame.chatLabelF;
    self.readLabel.frame = modelFrame.unReadLabelF;
    NSMutableAttributedString *attStr = modelFrame.model.attributedString;
    self.chatLabel.numberOfLines = 0;
    self.sensitiveWordsLabel.frame = modelFrame.sensitiveWordsLabelF;
    if (modelFrame.model.isSender) {
        self.chatLabel.font = [TOSKitCustomInfo shareCustomInfo].chatMessage_visitorText_font;
    }else{
        self.chatLabel.font = [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font;
    }
    
    NSDictionary *extraDic = [kitUtils dictionaryWithJsonString:modelFrame.model.message.extra];
    if ([extraDic tim_ObjectForKey:@"atNameList"]) {
        NSString *atNameListStr = extraDic[@"atNameList"];
        NSArray *atNameListArr = [atNameListStr componentsSeparatedByString:@","];
        [atNameListArr enumerateObjectsUsingBlock:^(NSString *obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if ([modelFrame.model.message.content containsString:obj]) {
                NSRange range = [attStr.string rangeOfString:obj];
                [attStr addAttribute:NSForegroundColorAttributeName value:[UIColor redColor] range:range];
            }
        }];
    }
    
    [self.chatLabel setNumberOfLines:0];
    [self.chatLabel setAttributedText:attStr];
    
    self.chatLabel.numberOfLines = 0;
    self.chatLabel.lineBreakMode = NSLineBreakByWordWrapping;
}

- (void)urlSkip:(NSURL *)url
{
    [self routerEventWithName:GXRouterEventURLSkip
                     userInfo:@{@"url"   : url
                                }];
}

#pragma mark - Getter and Setter
- (YYLabel *)chatLabel
{
    if (nil == _chatLabel) {
        _chatLabel = [[YYLabel alloc] init];
        _chatLabel.numberOfLines = 0;
        _chatLabel.font = MessageFont;
        _chatLabel.textColor = TOSHexAColor(0x282724,1.0);
        _chatLabel.userInteractionEnabled = YES;
    }
    return _chatLabel;
}

- (UILabel *)sensitiveWordsLabel {
    if (!_sensitiveWordsLabel) {
        _sensitiveWordsLabel = [[UILabel alloc] init];
        _sensitiveWordsLabel.textAlignment = NSTextAlignmentRight;
        _sensitiveWordsLabel.numberOfLines = 0;
        _sensitiveWordsLabel.font = MessageFont12;
        _sensitiveWordsLabel.textColor = TOSHexAColor(0x8C8C8C,1.0f);
        _sensitiveWordsLabel.userInteractionEnabled = YES;
        _sensitiveWordsLabel.text = @"消息中包含敏感词无法发送";
    }
    return _sensitiveWordsLabel;
}

@end
