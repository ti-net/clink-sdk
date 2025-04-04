//
//  ICChatMessageUnsupportCell.m
//  TIMClientKit
//
//  Created by 赵言 on 2021/1/11.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "ICChatMessageUnsupportCell.h"
#import "TIMMessageModel.h"
#import "ICFaceManager.h"
#import "TIMConstants.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import <CoreText/CoreText.h>

@interface ICChatMessageUnsupportCell ()

@property (nonatomic, strong) NSMutableAttributedString *attributedStr;

@end

@implementation ICChatMessageUnsupportCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.chatLabel];
    }
    return self;
}

#pragma mark - Private Method


- (void)setModelFrame:(TIMMessageFrame *)modelFrame {
    [super setModelFrame:modelFrame];
    self.chatLabel.frame = modelFrame.chatLabelF;
    self.readLabel.frame = modelFrame.unReadLabelF;

    if (modelFrame.model.isSender) {
        [self.chatLabel setAttributedText:modelFrame.model.attributedString];
    }else{
        [self.chatLabel setAttributedText:modelFrame.model.attributedString];
    }
//    [self BMWCustomerUnreadStatusDisplayWithModel:modelFrame.model];

}

#pragma mark - Getter and Setter
- (TIMLabel *)chatLabel
{
    if (nil == _chatLabel) {
        _chatLabel = [[TIMLabel alloc] init];
        _chatLabel.numberOfLines = 0;
        _chatLabel.font = MessageFont;
        _chatLabel.textColor = TOSHexAColor(0x282724,1.0);
        _chatLabel.linkDetectionTypes = KILinkTypeOptionNone;
    }
    return _chatLabel;
}

@end
