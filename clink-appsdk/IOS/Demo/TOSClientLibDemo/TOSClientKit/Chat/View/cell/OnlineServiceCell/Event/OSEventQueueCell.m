//
//  OSEventQueueCell.m
//  TIMClientKit
//
//  Created by apple on 2021/10/21.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "OSEventQueueCell.h"
#import "TIMMessageModel.h"
#import "TIMConstants.h"

@interface OSEventQueueCell()
//{
//    TIMMessageFrame *_modelFrame;
//}

@property (nonatomic, strong) UILabel *titleLbl;
@property (nonatomic, strong) UIButton *leaveQueueBtn;

@end

@implementation OSEventQueueCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {

        [self.contentView addSubview:self.titleLbl];
        [self.contentView addSubview:self.leaveQueueBtn];
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
    self.leaveQueueBtn.frame = modelFrame.custTitleF;
    self.leaveQueueBtn.hidden = NO;
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

-(UIButton *)leaveQueueBtn{
    if (!_leaveQueueBtn) {
        _leaveQueueBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [_leaveQueueBtn setTitle:@"放弃排队" forState:UIControlStateNormal];
        [_leaveQueueBtn setTitleColor:TOSHexAColor(0x1890ff,1.0) forState:UIControlStateNormal];
        _leaveQueueBtn.titleLabel.font = MessageFont12;
        [_leaveQueueBtn addTarget:self action:@selector(leavaAction) forControlEvents:UIControlEventTouchUpInside];
        
    }
    return _leaveQueueBtn;
}

-(void)leavaAction{
    self.leaveQueueBtn.hidden = YES;
    int app_width = [UIScreen mainScreen].bounds.size.width;
    float diffWidth = (app_width - self.modelFrame.chatLabelF.size.width) / 2.0 - self.modelFrame.chatLabelF.origin.x;

    self.titleLbl.frame = CGRectMake(self.modelFrame.chatLabelF.origin.x +                                     diffWidth,
                                     self.modelFrame.chatLabelF.origin.y,
                                     self.modelFrame.chatLabelF.size.width,
                                     self.modelFrame.chatLabelF.size.height);
    [[NSNotificationCenter defaultCenter] postNotificationName:OSLeaveQueueNotification object:self.modelFrame.model?:@""];
}

@end
