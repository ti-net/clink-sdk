//
//  OSEventSystemMessageCell.m
//  TIMClientKit
//
//  Created by apple on 2021/11/29.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "OSEventSystemMessageCell.h"
#import "TIMMessageModel.h"
#import "TIMConstants.h"

@interface OSEventSystemMessageCell()

@property (nonatomic, strong) UILabel *titleLbl;

@end

@implementation OSEventSystemMessageCell


- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {

        [self.contentView addSubview:self.titleLbl];
        self.bubbleView.hidden = YES;
        self.nameLabel.hidden = YES;
        self.headImageView.hidden = YES;
        self.activityView.hidden = YES;
        self.retryButton.hidden = YES;
    }
    return self;
}
#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];

    self.titleLbl.frame = modelFrame.chatLabelF;
    self.titleLbl.text = modelFrame.model.message.content;
}

#pragma mark lazy
- (UILabel *)titleLbl
{
    if (nil == _titleLbl) {
        _titleLbl = [[UILabel alloc] init];
        _titleLbl.numberOfLines = 1;
        _titleLbl.font = MessageFont12;
        _titleLbl.textColor = [UIColor whiteColor];
        _titleLbl.backgroundColor = TOSHexAColor(0xC8CBCE,1.0);
        _titleLbl.textAlignment = NSTextAlignmentCenter;
        _titleLbl.layer.cornerRadius = 2.f;
        _titleLbl.layer.masksToBounds = YES;
    }
    return _titleLbl;
}

@end
