//
//  OSRobotHotSubIssueListTypeTitleView.m
//  TOSClientKit
//
//  Created by 言 on 2022/12/26.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "OSRobotHotSubIssueListTypeTitleView.h"
#import "TIMConstants.h"
#import "YYKit.h"
#import "ICMessageConst.h"
#import "NSString+Extension.h"
#import "UIResponder+GXRouter.h"
#import "TIMMessageModel.h"
#import "ICFileTool.h"
#import "NSDictionary+Extension.h"
#import "TIMLabel.h"
#import "TIMConstants.h"
#import "UIView+SDExtension.h"
#import "kitUtils.h"
#import "UIImageView+TIMWebCache.h"
#import "ICFaceManager.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import "ZYSegmentControl.h"
#import <TOSClientLib/CombinationMessage.h>
#import "OSRobotHotSubIssueView.h"
#import "YYKit.h"
#import "NSString+Frame.h"
#import "NSString+Extension.h"
#import "TIMMessageFrame.h"


@interface OSRobotHotSubIssueListTypeTitleView ()

@property (nonatomic, strong) UILabel *title;
@property (nonatomic, strong) UIImageView *icon;

@end

@implementation OSRobotHotSubIssueListTypeTitleView

/// 添加子视图
- (void)setupSubviews {
    [super setupSubviews];
    self.backgroundColor = [UIColor clearColor];
    
    [self addSubview:self.title];
    [self addSubview:self.icon];
}

- (void)tapLabel:(UITapGestureRecognizer *)sender {
    NSInteger selectData = ((NSMutableArray <CombinationMessage *>*)self.tempModelFrame.model.message.content)[self.indexPath.row].selectData;
    if (selectData == self.itemIndex) {
        ((NSMutableArray <CombinationMessage *>*)self.tempModelFrame.model.message.content)[self.indexPath.row].selectData = -1;
    } else {
        ((NSMutableArray <CombinationMessage *>*)self.tempModelFrame.model.message.content)[self.indexPath.row].selectData = self.itemIndex;
    }
    //此段代码为触发model的setModel
    self.tempModelFrame.model = self.tempModelFrame.model;
    
    [self routerEventWithName:GXRobotCombinationHotIssueCellRefreshEventName
                     userInfo:@{MessageKey:self.tempModelFrame}];
}

/// 重写子视图布局
- (void)defineLayout {
    [super defineLayout];
    
}

- (void)reloadView {
    self.title.frame = CGRectMake(0, 0, self.tos_width - 14.f - 8.f, self.tos_height);
    self.icon.frame = CGRectMake(self.tos_width - 14.f, 12.f, 14.f, 14.f);
    self.icon.tosSD_y = self.tos_height/2 - 14.f/2.f;
}

- (void)setupTitle:(NSString *)title selected:(BOOL)selected {
    self.title.text = [NSString stringWithFormat:@"%@",title?:@""];
    NSString *icon;
    if (selected) {
        icon = @"robotCell_downArrow";
    } else {
        icon = @"robotCell_rightArrow";
    }
    
    self.icon.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,icon]];
}

- (UILabel *)title {
    if (!_title) {
        _title = [[UILabel alloc] init];
        _title.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        _title.textColor = TOSHexColor(0x262626);
        _title.userInteractionEnabled = YES;
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapLabel:)];
        [_title addGestureRecognizer:tap];
    }
    return _title;
}

- (UIImageView *)icon {
    if (!_icon) {
        _icon = [[UIImageView alloc] initWithImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"robotCell_rightArrow"]]];
    }
    return _icon;
}

@end
