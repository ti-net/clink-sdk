//
//  ICChatMessageRevokeCell.m
//  TIMClientKit
//
//  Created by 赵言 on 2020/10/19.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "ICChatMessageRevokeCell.h"
#import "TIMMessageModel.h"
#import "ICFaceManager.h"
#import "TIMConstants.h"
#import <TOSClientLib/TOSClientLib.h>
#import <CoreText/CoreText.h>
#import "NSString+Extension.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"

#define APP_WIDTH ([UIScreen mainScreen].bounds.size.width)
#define APP_HEIGHT ([UIScreen mainScreen].bounds.size.height)

@interface ICChatMessageRevokeCell ()

@property (nonatomic, strong) NSMutableAttributedString *attributedStr;

@end

@implementation ICChatMessageRevokeCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.chatLabel];
        self.bubbleView.hidden = YES;
        self.headImageView.hidden = YES;
        self.nameLabel.hidden = YES;
        self.activityView.hidden = YES;
        self.retryButton.hidden = YES;
    }
    return self;
}

#pragma mark - Private Method


- (void)setModelFrame:(TIMMessageFrame *)modelFrame {
    [super setModelFrame:modelFrame];
    self.readLabel.frame = modelFrame.unReadLabelF;

    if ([modelFrame.model.message.content containsString:@"(null)"]) {
    } else {
        [self setupLayoutWithModelFrame:modelFrame];
    }
//    [self BMWCustomerUnreadStatusDisplayWithModel:modelFrame.model];

}

- (void)setupLayoutWithModelFrame:(TIMMessageFrame *)modelFrame {
    self.chatLabel.frame = modelFrame.chatLabelF;
    
    NSMutableAttributedString *attributedStr = [ICFaceManager transferMessageString:modelFrame.model.message.content font:self.chatLabel.font  foreColor:TOSHexAColor(0xC8CBCE,1.0) lineHeight:self.chatLabel.font.lineHeight];
    
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    paragraphStyle.alignment = NSTextAlignmentCenter;
    
    [attributedStr setAttributes:@{
        NSForegroundColorAttributeName: [UIColor whiteColor],
        NSParagraphStyleAttributeName: paragraphStyle,
        NSBackgroundColorAttributeName: TOSHexAColor(0xC8CBCE,1.0),
    }
                           range:NSMakeRange(0, modelFrame.model.message.content.length)];
    
    [attributedStr setAttributes:@{
        NSForegroundColorAttributeName: TOSHexAColor(0x1C69D4,1.0),
        NSParagraphStyleAttributeName: paragraphStyle,
        NSBackgroundColorAttributeName: TOSHexAColor(0xC8CBCE,1.0),
    }
                           range:[modelFrame.model.message.content rangeOfString:kTIMRecalledMessageAgainEdit]];
    
    [self.chatLabel setAttributedText:attributedStr];
    self.attributedStr = attributedStr;
}

#pragma mark - Getter and Setter
- (TIMLabel *)chatLabel
{
    if (nil == _chatLabel) {
        _chatLabel = [[TIMLabel alloc] init];
        _chatLabel.numberOfLines = 1;
        _chatLabel.font = MessageFont12;
        _chatLabel.textColor = [UIColor whiteColor];
        _chatLabel.backgroundColor = TOSHexAColor(0xC8CBCE,1.0);
        _chatLabel.linkDetectionTypes = KILinkTypeOptionAll;
        _chatLabel.textAlignment = NSTextAlignmentCenter;
        _chatLabel.layer.cornerRadius = 2.f;
        _chatLabel.layer.masksToBounds = YES;
        _chatLabel.userInteractionEnabled = YES;
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapAction:)];
        [_chatLabel addGestureRecognizer:tap];
    }
    return _chatLabel;
}

- (void)tapAction:(UITapGestureRecognizer *)sender {
    CGPoint touchPoint = [sender locationInView:self.chatLabel];
    
    CGRect validFrame = [self boundingRectForCharacterRange:[self.modelFrame.model.message.content rangeOfString:kTIMRecalledMessageAgainEdit]];
    if(CGRectContainsPoint(validFrame, touchPoint)) {
        [[NSNotificationCenter defaultCenter] postNotificationName:kTIMRecalledMessageAgainEditNotification object:self.modelFrame.model.message.revokeContent?:@""];
    }
}

- (CGRect)boundingRectForCharacterRange:(NSRange)range {
    NSTextStorage *textStorage = [[NSTextStorage alloc] initWithAttributedString:self.attributedStr];
    NSLayoutManager *layoutManager = [[NSLayoutManager alloc] init];
    [textStorage addLayoutManager:layoutManager];
    NSTextContainer *textContainer = [[NSTextContainer alloc] initWithSize:[self.chatLabel bounds].size];
    textContainer.lineFragmentPadding = 0;
    [layoutManager addTextContainer:textContainer];
    NSRange glyphRange;
    [layoutManager characterRangeForGlyphRange:range actualGlyphRange:&glyphRange];
    return [layoutManager boundingRectForGlyphRange:glyphRange inTextContainer:textContainer];
}

@end
