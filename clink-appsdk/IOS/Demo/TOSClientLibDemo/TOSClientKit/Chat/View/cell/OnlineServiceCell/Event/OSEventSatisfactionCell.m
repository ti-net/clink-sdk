//
//  OSEventSatisfactionCell.m
//  TOSClientKit
//
//  Created by 言 on 2022/8/11.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "OSEventSatisfactionCell.h"

#import "NSObject+TIMShowError.h"

#import "TOSSatisfactionModel.h"
#import "TOSSatisfactionStatusModel.h"
#import "YYKit.h"
#import "kitUtils.h"
#import "TIMConstants.h"

#import <TOSClientLib/TOSGetInvestigationInfoModel.h>

#import "TIMMessageModel.h"
#import "OSEventSatisfactionTagView.h"
#import "OSEventSatisfactionExpressionView.h"

@interface OSEventSatisfactionCell ()

@property (weak, nonatomic) IBOutlet UIView *contentBottomView;

@property (weak, nonatomic) IBOutlet UILabel *titleLabel;
@property (strong, nonatomic) UIView *expressionBottomView;
@property (strong, nonatomic) UIView *tagBottomView;
@property (weak, nonatomic) IBOutlet UIButton *commitBtn;

@property (nonatomic, strong) UIView *maskView;

@property (nonatomic, strong) NSMutableArray <OSEventSatisfactionExpressionView *>*dataSource;

@property (nonatomic, strong) TIMMessageFrame *tempModelFrame;

@end

@implementation OSEventSatisfactionCell

- (void)setModelFrame:(TIMMessageFrame *)modelFrame {
    
    [self.expressionBottomView removeFromSuperview];
    
    self.expressionBottomView = [[UIView alloc] init];
    [self.contentBottomView addSubview:self.expressionBottomView];
    
    [self.tagBottomView removeFromSuperview];
    self.tagBottomView = [[UIView alloc] init];
    [self.contentBottomView addSubview:self.tagBottomView];
    
    self.dataSource = [NSMutableArray array];
    for (NSInteger i = 0; i < 5; i++) {
        
        OSEventSatisfactionExpressionView *expressionView = kInitXibName(@"TOSClient.bundle/OSEventSatisfactionExpressionView");
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(didClickIconBtnAction:)];
        [expressionView addGestureRecognizer:tap];
        expressionView.hidden = YES;
        [self.expressionBottomView addSubview:expressionView];
        [self.dataSource addObject:expressionView];
    }
    
    self.expressionBottomView.frame = CGRectMake(0, CGRectGetMaxY(self.titleLabel.frame), self.contentView.tos_width - 32.f, 86.f);
    
    self.tagBottomView.frame = CGRectMake(0, CGRectGetMaxY(self.expressionBottomView.frame), self.contentView.tos_width - 32.f, modelFrame.satisfactionSelectStarTagHeight);
    
    self.commitBtn.frame = CGRectMake(25.f, CGRectGetMaxY(self.tagBottomView.frame) + 24.f, self.contentView.tos_width - 32.f - 25.f - 25.f, 38.f);
    TOSSatisfactionModel *contentModel = [TOSSatisfactionModel yy_modelWithJSON:modelFrame.model.message.content];
    self.tempModelFrame = modelFrame;
    TOSSatisfactionStatusModel *extraModel = [TOSSatisfactionStatusModel yy_modelWithJSON:modelFrame.model.message.extra];
    [self.maskView removeFromSuperview];
    
    if (extraModel.alreadyInvestigation &&
        [extraModel.alreadyInvestigation isEqualToString:@"1"]) { //已评价
        self.commitBtn.hidden = YES;
        
        TOSGetInvestigationInfoModel *infoModel = [TOSGetInvestigationInfoModel shareInvestigationInfoModel];
        self.tempModelFrame.satisfactionSelectStar = infoModel.options[0].star.integerValue;
        self.tempModelFrame.tabBarSelect = [NSMutableArray arrayWithArray:infoModel.options[0].label];
        
        self.maskView = [[UIView alloc] initWithFrame:self.contentView.bounds];
        [self.contentView addSubview:self.maskView];
    } else if (extraModel.alreadyInvestigation &&
               [extraModel.alreadyInvestigation isEqualToString:@"-1"]) {
        
        self.commitBtn.hidden = YES;
        self.maskView = [[UIView alloc] initWithFrame:self.contentView.bounds];
        [self.contentView addSubview:self.maskView];
    } else {//未评价
//        self.commitBtn.hidden = NO;
        /// 当前没有选中的星级，不显示提交按钮
        if (self.tempModelFrame.satisfactionSelectStar < 1) {
            self.commitBtn.hidden = YES;
        }
        else {
            self.commitBtn.hidden = NO;
        }
    }
    
    NSInteger idx = contentModel.investigation.star.count - 1;
    NSInteger i = 0;
    while (idx >= 0) {
        
        TOSSatisfactionStarModel *obj = [contentModel.investigation.star objectOrNilAtIndex:idx];
        OSEventSatisfactionExpressionView *view = [self.dataSource objectOrNilAtIndex:idx];
        view.hidden = NO;
        view.obj = obj;
        CGFloat width = modelFrame.satisfactionStarWidth;
        view.frame = CGRectMake(width*i, 26.f, width, 60.f);
        [view setupContent:obj.star.integerValue title:obj.desc gray:obj.isSelect];
        if (self.tempModelFrame.satisfactionSelectStar == obj.star.integerValue) {
            [view didClickIconBtnAction:YES];
        } else {
            [view didClickIconBtnAction:NO];
        }
        i++;
        idx--;
    }
    
    TOSSatisfactionStarInfoModel *starInfoModel = [contentModel.investigation.content.options[0].star objectOrNilAtIndex:self.tempModelFrame.satisfactionSelectStar-1];
    
    [self.tagBottomView removeAllSubviews];
    
    [starInfoModel.tabBar enumerateObjectsUsingBlock:^(NSString * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        
        TOSSatisfactionTabBarFrameModel *frameModel = [self.tempModelFrame.tabBarFrame objectOrNilAtIndex:idx];
        OSEventSatisfactionTagView *tagView = kInitXibName(@"TOSClient.bundle/OSEventSatisfactionTagView");
        tagView.frame = CGRectMake(frameModel.x.floatValue, frameModel.y.floatValue, frameModel.w.floatValue, frameModel.h.floatValue);
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(didClickTagBtnAction:)];
        [tagView addGestureRecognizer:tap];
        
        if ([self.tempModelFrame.tabBarSelect containsObject:obj]) {
            [tagView didClickTagBtnAction:YES];
        } else {
            [tagView didClickTagBtnAction:NO];
        }
        
        [tagView setupContent:obj];
        [self.tagBottomView addSubview:tagView];
    }];
    
    if ([kitUtils isBlankString:contentModel.investigation.welcome]) {
        self.titleLabel.text = @"";
    } else {
        self.titleLabel.text = contentModel.investigation.welcome;
    }
}

- (void)didClickTagBtnAction:(UITapGestureRecognizer *)sender {
    OSEventSatisfactionTagView *tagView = (OSEventSatisfactionTagView *)sender.view;
    NSString *content = tagView.content.titleLabel.text;
    if (![self.tempModelFrame.tabBarSelect containsObject:content]) {
        [tagView didClickTagBtnAction:YES];
        [self.tempModelFrame.tabBarSelect addObject:content];
    } else {
        [tagView didClickTagBtnAction:NO];
        [self.tempModelFrame.tabBarSelect removeObject:content];
    }
}

- (void)didClickIconBtnAction:(UITapGestureRecognizer *)sender {
    OSEventSatisfactionExpressionView *expressionView = (OSEventSatisfactionExpressionView *)sender.view;
    [expressionView didClickIconBtnAction:YES];
    [self.dataSource enumerateObjectsUsingBlock:^(OSEventSatisfactionExpressionView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        
        if (![obj isEqual:expressionView]) {
            [obj didClickIconBtnAction:NO];
        }
    }];
    //此段代码为触发model的setModel
    self.tempModelFrame.satisfactionSelectStar = expressionView.obj.star.integerValue;
    self.tempModelFrame.tabBarSelect = [NSMutableArray array];
    self.tempModelFrame.model = self.tempModelFrame.model;
//    /// 提交按钮的间距加高度 24.f+38.f    TIMMessageFrame类中，未评价是直接加了62的，评价按钮是未隐藏的，所以需要在这里加上评价按钮的相关高度
//    self.tempModelFrame.cellHight += 62.0f;
    
    [self routerEventWithName:GXRobotCombinationHotIssueCellRefreshEventName
                     userInfo:@{MessageKey:self.tempModelFrame}];
}

- (IBAction)didClickCommitBtnAction:(UIButton *)sender {
    TOSSatisfactionStatusModel *extraModel = [TOSSatisfactionStatusModel yy_modelWithJSON:self.tempModelFrame.model.message.extra?:@""];
    
    TOSSatisfactionModel *contentModel = [TOSSatisfactionModel yy_modelWithJSON:self.tempModelFrame.model.message.content];
    
    NSMutableDictionary *dic = [NSMutableDictionary dictionary];
    
    NSInteger idx = contentModel.investigation.star.count - 1;
    NSInteger i = 0;
    while (idx >= 0) {
        
        TOSSatisfactionStarModel *obj = [contentModel.investigation.star objectOrNilAtIndex:idx];
        if (self.tempModelFrame.satisfactionSelectStar == obj.star.integerValue) {
            [dic setObject:obj.desc?:@"" forKey:@"name"];
            [dic setObject:obj.star forKey:@"star"];
        }
        i++;
        idx--;
    }
    
    [dic setObject:self.tempModelFrame.tabBarSelect?:@[] forKey:@"label"];
    

    @WeakObj(self);
    [[OnlineRequestManager sharedCustomerManager] submitInvestigationUniqueId:extraModel.uniqueId?:@""
                                             mainUniqueId:extraModel.mainUniqueId?:@""
                                                  options:@[dic]
                                                    solve:@""
                                                   remark:@""
                                                  Success:^{
        @StrongObj(self)
        TOSSatisfactionStatusModel *extraModel = [TOSSatisfactionStatusModel yy_modelWithJSON:self.tempModelFrame.model.message.extra];
        
        extraModel.alreadyInvestigation = @"-1";
        
//        TOSGetInvestigationInfoModel *infoModel = [TOSGetInvestigationInfoModel shareInvestigationInfoModel];
//        TOSGetInvestigationOptionsModel *model = [[TOSGetInvestigationOptionsModel alloc] init];
//        infoModel.options = [NSArray arrayWithObject:model];
//        infoModel.options[0].star = [NSNumber numberWithInteger:self.tempModelFrame.satisfactionSelectStar];
//        infoModel.options[0].label = [NSArray arrayWithArray:self.tempModelFrame.tabBarSelect];
        
        self.tempModelFrame.model.message.extra = [extraModel yy_modelToJSONObject];
        
        self.tempModelFrame.model = self.tempModelFrame.model;
        
        [self routerEventWithName:GXRobotCombinationHotIssueCellRefreshEventName
                         userInfo:@{MessageKey:self.tempModelFrame}];
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        @StrongObj(self)
        [self tim_showMBErrorView:errorDes];
        self.tempModelFrame.tabBarSelect = [NSMutableArray array];
        self.tempModelFrame.satisfactionSelectStar = 0;
        self.tempModelFrame.model = self.tempModelFrame.model;
        [self routerEventWithName:GXRobotCombinationHotIssueCellRefreshEventName
                         userInfo:@{MessageKey:self.tempModelFrame}];
    }];
}

- (void)awakeFromNib {
    [super awakeFromNib];
    
    self.commitBtn.layer.cornerRadius = 4.f;
    self.commitBtn.layer.masksToBounds = YES;
    self.commitBtn.layer.borderWidth = 1.f;
    self.commitBtn.layer.borderColor = TOSHexColor(0x4385FF).CGColor;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
