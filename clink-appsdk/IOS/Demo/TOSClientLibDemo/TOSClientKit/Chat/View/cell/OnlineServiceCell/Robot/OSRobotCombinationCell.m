//
//  OSRobotCombinationCell.m
//  TIMClientKit
//
//  Created by apple on 2021/8/31.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "OSRobotCombinationCell.h"
#import "YYKit.h"
#import "kitUtils.h"
#import "TIMMessageModel.h"
#import <TOSClientLib/CombinationMessage.h>
#import "OSRobotCombinationTextSubview.h"
#import "OCRobotCombinationImageVideoSubview.h"
#import "OSRobotCombinationFileSubview.h"
#import "OSRobotCombinationVoiceSubview.h"
#import "OSRobotCombinationHotIssueSubview.h"

@interface OSRobotCombinationCell() ///<UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, strong) TIMMessageFrame *tempModelFrame;
@property (nonatomic, strong) UIView *subContentView;

@end

@implementation OSRobotCombinationCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.subContentView];
    }
    return self;
}

- (UIView *)subContentView {
    if (!_subContentView) {
        _subContentView = [[UIView alloc] initWithFrame:CGRectZero];
        _subContentView.backgroundColor = [UIColor clearColor];
    }
    return _subContentView;
}

#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    self.tempModelFrame = modelFrame;
    
    NSArray <CombinationMessage *>*richModels = (NSMutableArray <CombinationMessage *>*)modelFrame.model.message.content;
    
    self.subContentView.frame = modelFrame.robotCombinationF;
    [self.subContentView removeAllSubviews];
    
    NSMutableArray <CombinationMessage *>*elements = [NSMutableArray arrayWithArray:richModels];
    __block CGFloat y = 0;
    CGFloat width = modelFrame.robotCombinationF.size.width;
    CGFloat x = 0;
    
    [elements enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {

        CGFloat height = obj.contentF.size.height;

        if ([obj.type isEqualToString:@"5"]) {
            OSRobotCombinationTextSubview *textSubview = [[OSRobotCombinationTextSubview alloc] initWithFrame:CGRectMake(x, y, width, height)];
            [textSubview setWithCombinationModel:obj];
            [self.subContentView addSubview:textSubview];
        } else if ([obj.type isEqualToString:@"2"] ||
                   [obj.type isEqualToString:@"4"]) {

            OCRobotCombinationImageVideoSubview *mediaSubview = [[OCRobotCombinationImageVideoSubview alloc] initWithFrame:CGRectMake(x, y, width, height)];
            [mediaSubview setWithCombinationModel:obj];
            [self.subContentView addSubview:mediaSubview];
        } else if ([obj.type isEqualToString:@"3"]) {

            OSRobotCombinationFileSubview *fileSubview = [[OSRobotCombinationFileSubview alloc] initWithFrame:CGRectMake(x, y, width, height)];
            [fileSubview setWithModel:obj];
            [self.subContentView addSubview:fileSubview];
        } else if ([obj.type isEqualToString:@"7"]) {

            OSRobotCombinationVoiceSubview *voiceSubview = [[OSRobotCombinationVoiceSubview alloc] initWithFrame:CGRectMake(x, y, width, height)];
            [voiceSubview setWithModel:obj];
            [self.subContentView addSubview:voiceSubview];
        } else if ([obj.type isEqualToString:@"6"] ||
                   [obj.type isEqualToString:@"15"] ||
                   [obj.type isEqualToString:@"16"] ||
                   [obj.type isEqualToString:@"17"] ||
                   [obj.type isEqualToString:@"18"] ||
                   [obj.type isEqualToString:@"19"] ||
                   [obj.type isEqualToString:@"20"] ||
                   [obj.type isEqualToString:@"31"]) {

            OSRobotCombinationHotIssueSubview *hotIssueSubview = [[OSRobotCombinationHotIssueSubview alloc] initWithFrame:CGRectMake(x, y, width, height)];
            hotIssueSubview.hotIssueTitleF = self.tempModelFrame.hotIssueTitleF;
            hotIssueSubview.refreshBtnY = self.tempModelFrame.refreshBtnY;
            hotIssueSubview.refreshIconY = self.tempModelFrame.refreshIconY;
            hotIssueSubview.tempModelFrame = self.tempModelFrame;
            hotIssueSubview.indexPath = [NSIndexPath indexPathForRow:idx inSection:0];
            [hotIssueSubview setWithModel:obj];
            [self.subContentView addSubview:hotIssueSubview];
        } else {
        }
        y += height;
    }];
}

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
