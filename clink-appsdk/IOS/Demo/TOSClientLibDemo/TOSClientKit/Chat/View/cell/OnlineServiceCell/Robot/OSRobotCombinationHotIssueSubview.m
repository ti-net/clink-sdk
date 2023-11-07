//
//  OSRobotCombinationHotIssueSubview.m
//  TOSClientKit
//
//  Created by 言 on 2022/8/1.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "OSRobotCombinationHotIssueSubview.h"
#import "TIMMessageModel.h"
#import "ICFileTool.h"
#import "NSDictionary+Extension.h"
#import "TIMLabel.h"
#import "TIMConstants.h"
#import "UIView+SDExtension.h"
#import "kitUtils.h"
#import "UIImageView+WebCache.h"
#import "ICFaceManager.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import "ZYSegmentControl.h"
#import <TOSClientLib/CombinationMessage.h>
#import "OSRobotHotSubIssueView.h"
#import "YYKit.h"
#import "NSString+Frame.h"
#import "NSString+Extension.h"
#import "TIMMessageFrame.h"
#import "OSRobotHotSubIssueListTypeView.h"
#import <TOSClientKit/TOSKitCustomInfo.h>

@interface OSRobotCombinationHotIssueSubview () <ZYSegmentControlDelegate>

@property (nonatomic, strong) YYLabel *title;
@property (nonatomic, strong) UIImageView *refreshIcon;
@property (nonatomic, strong) UIButton *refreshBtn;

@property (nonatomic, strong) ZYSegmentControl *segmentC;
@property (nonatomic, strong) UIView *segmentCLine;

@property (nonatomic, strong) OSRobotHotSubIssueView *subIssueView;
@property (nonatomic, strong) OSRobotHotSubIssueListTypeView *subIssueVerticalListView;

@property (nonatomic, strong) CombinationMessage *model;

@end

@implementation OSRobotCombinationHotIssueSubview

- (void)setupSubviews {
    [super setupSubviews];
    
    self.backgroundColor = [UIColor clearColor];
    [self addSubview:self.title];
    [self addSubview:self.segmentC];
    [self addSubview:self.subIssueView];
    [self addSubview:self.subIssueVerticalListView];
    [self addSubview:self.refreshIcon];
    [self addSubview:self.refreshBtn];
}

- (void)defineLayout {
    [super defineLayout];
    
}

#pragma mark - Private Method
- (void)setWithModel:(CombinationMessage *)model {
    
    self.model = model;
    
//    CGSize refreshBtnSize = [TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitle sizeWithMaxWidth:self.tos_width andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitleFont];
    CGSize refreshBtnSize = model.refreshBtnSize;
    
    self.refreshBtn.frame = CGRectMake(194.f, self.refreshBtnY, refreshBtnSize.width+10, 18.f);
//    self.refreshBtn.x = self.width - 53.f;//37.f + 16.f;
    self.refreshBtn.tosSD_x = self.tos_width - 16.f - self.refreshBtn.tos_width;
    
    self.refreshIcon.frame = CGRectMake(174.f, self.refreshIconY, 16.f, 16.f);
//    self.refreshIcon.x = self.width - 73.f;//16.f + 57.f;
    self.refreshIcon.tosSD_x = self.tos_width - 16.f - self.refreshBtn.tos_width - self.refreshIcon.tos_width;
    
    self.title.frame = self.hotIssueTitleF;
    
    if ([kitUtils isBlankString:model.text]) {
//        self.title.text = @"猜你想问";
        self.title.text = @"热门问题";
    } else {
        self.title.text = model.text;
    }

    BOOL hiddenRefresh = NO;
    
    if ([model.type isEqualToString:@"31"]) {
        self.subIssueView.hidden = YES;
        self.subIssueVerticalListView.hidden = NO;
        self.segmentC.hidden = YES;
        
        self.subIssueVerticalListView.tempModelFrame = self.tempModelFrame;
        self.subIssueVerticalListView.indexPath = self.indexPath;
        
        
        NSMutableArray *array = [NSMutableArray array];
        [model.data enumerateObjectsUsingBlock:^(CombinationDataModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            [array addObject:[NSString stringWithFormat:@"%@",obj.name?:@""]];
        }];
        
        [self.subIssueVerticalListView setupTitleItem:array
                                        selectedIndex:model.selectData?:0];
        
        if (model.selectData < 0) {
            hiddenRefresh = YES;
        } else {
            CombinationDataModel *data = model.data[model.selectData?:0];
            
            NSArray <NSString *>*items = [self getSubIssueItem:data.topics
                                                    selectPage:data.selectPageData];
            NSArray <KnowledgeDataModel *>*knowledges = [self getSubIssueItem:data.knowledge
                                                                   selectPage:data.selectPageData];
            
            [self.subIssueVerticalListView setupIssueItem:items
                                               knowledges:knowledges];
            
            
            if (data.topics.count <= 5) {
                hiddenRefresh = YES;
            } else {
                hiddenRefresh = NO;
            }
        }
        
        self.subIssueVerticalListView.frame = CGRectMake(0.f, self.title.tos_bottom + 12.f, model.contentF.size.width, model.tableViewH);
        
        self.subIssueVerticalListView.hiddenRefresh = hiddenRefresh;
        self.subIssueVerticalListView.refreshIcon = self.refreshIcon;
        self.subIssueVerticalListView.refreshBtn = self.refreshBtn;
        
        [self.subIssueVerticalListView reloadViewWithTitleItem:model.hotListTypeTitleH
                                                     issueItem:model.hotListTypeSubIssueH];
    } else {
        
        self.refreshBtn.tosSD_y = self.refreshBtnY;
        self.refreshIcon.tosSD_y = self.refreshIconY;
        
        self.subIssueView.hidden = NO;
        self.subIssueVerticalListView.hidden = YES;
        
        if (model.cards &&
            [model.cards isKindOfClass:[NSArray class]] &&
            model.cards.count > 0) {
            self.segmentC.hidden = YES;
            self.segmentC.selectedSegmentIndex = 0;
            
            NSArray <NSString *>*items = [self getSubIssueItem:model.cards
                                                    selectPage:model.selectPageData];
            NSArray <KnowledgeDataModel *>*knowledges = [self getSubIssueItem:model.knowledge
                                                                   selectPage:model.selectPageData];
            [self.subIssueView setupItem:items
                              knowledges:knowledges];
            
            if (model.cards.count <= TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber) {
                hiddenRefresh = YES;
            } else {
                hiddenRefresh = NO;
            }
            
            self.subIssueView.frame = CGRectMake(0.f, self.title.tos_bottom + 12.f, model.contentF.size.width, model.tableViewH);
            
        } else {
            self.segmentC.frame = CGRectMake(0, self.title.tos_bottom + 12.f, model.contentF.size.width, 29.f);
            self.segmentCLine.frame = CGRectMake(0, 29.f, model.contentF.size.width, 1.f);
            
            self.segmentC.hidden = NO;
            
            NSMutableArray *array = [NSMutableArray array];
            CGFloat segmentHeight = model.segmentHeight;
            [model.data enumerateObjectsUsingBlock:^(CombinationDataModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                [array addObject:[NSString stringWithFormat:@"%@",obj.name?:@""]];
//                if (idx == 0) {
//                    segmentHeight = [obj.name sizeWithMaxWidth:self.tos_width andFont:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentFont].height;
//                }
            }];
            
            self.segmentC.tos_height = MAX(segmentHeight, 29.0);
            self.segmentCLine.tos_top = self.segmentC.tos_height;
            
            [self.segmentC reloadDataWithDatas:array];
            self.segmentC.selectedSegmentIndex = model.selectData?:0;
            
            CombinationDataModel *data = model.data[model.selectData?:0];
            
            NSArray <NSString *>*items = [self getSubIssueItem:data.topics
                                                    selectPage:data.selectPageData];
            NSArray <KnowledgeDataModel *>*knowledges = [self getSubIssueItem:data.knowledge
                                                                   selectPage:data.selectPageData];
            
            [self.subIssueView setupItem:items
                              knowledges:knowledges];
            
            if (data.topics.count <= TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber) {
                hiddenRefresh = YES;
            } else {
                hiddenRefresh = NO;
                
            }
            
            self.subIssueView.frame = CGRectMake(0.f, self.segmentC.tos_bottom, model.contentF.size.width, model.tableViewH);
        }
        [self.subIssueView reloadView:model.hotSubIssueH];
    }
    
    self.refreshIcon.hidden = hiddenRefresh;
    self.refreshBtn.hidden = hiddenRefresh;
}

/// 获取当前页的子问题
/// @param dataSource 源数据
/// @param selectPage 当前页
- (NSArray <NSString *>*)getSubIssueItem:(NSArray <NSString *>*)dataSource
                              selectPage:(NSInteger)selectPage {
    
    if (dataSource.count <= TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber) {
        return dataSource;
    } else {
        
        NSInteger surplus = dataSource.count - selectPage*TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber;
        NSInteger count = surplus >= TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber ? TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber : surplus;
        NSArray <NSString *>*items = [dataSource subarrayWithRange:NSMakeRange(selectPage*TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber, count)];
        return items;
    }
}

/// 刷新到下一页
/// @param dataSource 源数据
/// @param currentPage 当前页
- (NSInteger)reloadSubIssuePage:(NSArray <NSString *>*)dataSource
                    currentPage:(NSInteger)currentPage {
    
    if (dataSource.count <= TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber) {
        return 0;
    } else {
        
        NSInteger surplus = dataSource.count - currentPage*TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber;
        NSInteger count = surplus <= TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber ? 0 : (currentPage+1);
        return count;
    }
}


- (void)setModelFrame:(TIMMessageFrame *)modelFrame {
    
//    CombinationMessage *richModels = (CombinationMessage *)modelFrame.model.message.content;
    
}

#pragma mark - ZYSegmentControlDelegate
- (void)didClickSegmentedControl:(NSUInteger)selectedIndex {
    
    ((NSMutableArray <CombinationMessage *>*)self.tempModelFrame.model.message.content)[self.indexPath.row].selectData = selectedIndex;
    //此段代码为触发model的setModel
    self.tempModelFrame.model = self.tempModelFrame.model;
    
    [self routerEventWithName:GXRobotCombinationHotIssueCellRefreshEventName
                     userInfo:@{MessageKey:self.tempModelFrame}];
}

- (void)refreshBtnAction:(UIButton *)sender {
    CombinationMessage *model = ((NSMutableArray <CombinationMessage *>*)self.tempModelFrame.model.message.content)[self.indexPath.row];
    
    NSInteger index = 0;
    if (model.cards &&
        [model.cards isKindOfClass:[NSArray class]] &&
        model.cards.count > 0) {
        
        index = [self reloadSubIssuePage:model.cards currentPage:model.selectPageData];
        ((NSMutableArray <CombinationMessage *>*)self.tempModelFrame.model.message.content)[self.indexPath.row].selectPageData = index;
    } else {
        CombinationDataModel *data = model.data[model.selectData?:0];
        
        index = [self reloadSubIssuePage:data.topics currentPage:data.selectPageData];//model.selectPageData];
        ((CombinationDataModel *)(((NSMutableArray <CombinationMessage *>*)self.tempModelFrame.model.message.content)[self.indexPath.row]).data[model.selectData?:0]).selectPageData = index;
    }
    
    //此段代码为触发model的setModel
    self.tempModelFrame.model = self.tempModelFrame.model;
    
    [self routerEventWithName:GXRobotCombinationHotIssueCellRefreshEventName
                     userInfo:@{MessageKey:self.tempModelFrame}];
}

#pragma mark - 初始化
- (OSRobotHotSubIssueView *)subIssueView {
    if (!_subIssueView) {
        _subIssueView = [[OSRobotHotSubIssueView alloc] initWithFrame:CGRectZero];
    }
    return _subIssueView;
}

- (OSRobotHotSubIssueListTypeView *)subIssueVerticalListView {
    if (!_subIssueVerticalListView) {
        _subIssueVerticalListView = [[OSRobotHotSubIssueListTypeView alloc] initWithFrame:CGRectZero];
    }
    return _subIssueVerticalListView;
}

- (YYLabel *)title {
    if (!_title) {
        _title = [[YYLabel alloc] init];
//        _title.text = @"猜你想问";
        _title.text = @"热门问题";
//        _title.font = [UIFont fontWithName:@"PingFangSC-Medium" size:16.f];
        _title.font = TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_titleFont;
//        _title.textColor = TOSHexColor(0xFAAD14);
        _title.textColor = TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_titleColor;
        _title.numberOfLines = 0;
    }
    return _title;
}

- (UIImageView *)refreshIcon {
    if (!_refreshIcon) {
        _refreshIcon = [[UIImageView alloc] initWithImage:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshImage];
    }
    return _refreshIcon;
}

- (UIButton *)refreshBtn {
    if (!_refreshBtn) {
        _refreshBtn = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_refreshBtn setTitle:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitle forState:UIControlStateNormal];
        _refreshBtn.titleLabel.font = TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitleFont;
        [_refreshBtn setTitleColor:TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshTitleColor forState:UIControlStateNormal];
        [_refreshBtn addTarget:self action:@selector(refreshBtnAction:) forControlEvents:UIControlEventTouchUpInside];
    }
    return _refreshBtn;
}

- (ZYSegmentControl *)segmentC {
    if (!_segmentC) {
        _segmentC = [[ZYSegmentControl alloc]
                                      initWithFrame:CGRectMake(0, 0, kWindowWidth, 29.f)
                                      items:@[]
                                      styleType:(StyleTypeLine)
                                      arrayColor:@[TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentUnselectedTextColor, TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentTextColor, TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentTextColor]];
        _segmentC.backgroundColor = [UIColor clearColor];
        _segmentC.delegate = self;
        [self addSubview:_segmentC];
        [_segmentC addSubview:self.segmentCLine];
    }
    return _segmentC;
}

- (UIView *)segmentCLine {
    if (!_segmentCLine) {
        _segmentCLine = [[UIView alloc] initWithFrame:CGRectMake(0, 29.f, kWindowWidth, 1.f)];
        _segmentCLine.backgroundColor = TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_segmentLineColor;
    }
    return _segmentCLine;
}

@end
