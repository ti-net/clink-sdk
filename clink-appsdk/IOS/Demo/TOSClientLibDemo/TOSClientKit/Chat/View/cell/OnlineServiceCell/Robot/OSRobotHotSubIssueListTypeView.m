//
//  OSRobotHotSubIssueListTypeView.m
//  TOSClientKit
//
//  Created by 言 on 2022/12/26.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "OSRobotHotSubIssueListTypeView.h"
#import "TIMConstants.h"
#import "YYKit.h"
#import "ICMessageConst.h"
#import "NSString+Extension.h"
#import "UIResponder+GXRouter.h"
#import "OSRobotHotSubIssueListTypeTitleView.h"

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
#import "OSRobotHotSubIssueView.h"
#import "YYKit.h"
#import "NSString+Frame.h"
#import "NSString+Extension.h"
#import "TIMMessageFrame.h"
#import <TOSClientLib/CombinationMessage.h>

@interface OSRobotHotSubIssueListTypeView ()

@property (nonatomic, strong) NSMutableArray <OSRobotHotSubIssueListTypeTitleView *>*titleDataSource;

@property (nonatomic, strong) NSMutableArray <UILabel *>*issueDataSource;

@property (nonatomic, assign) NSInteger selectedIndex;

@property (nonatomic, strong) NSArray <KnowledgeDataModel *>*knowledge;

@end

@implementation OSRobotHotSubIssueListTypeView

/// 添加子视图
- (void)setupSubviews {
    [super setupSubviews];
    self.backgroundColor = [UIColor clearColor];
    
    self.selectedIndex = 0;
    
    for (NSInteger i = 0; i < 5; i++) {
        
        UILabel *content = [[UILabel alloc] init];
        content.tag = 60000+i;
        content.numberOfLines = 2;
        content.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        content.textColor = TOSHexAColor(0x4385FF,1.f);
        content.userInteractionEnabled = YES;
        [self addSubview:content];
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapLabel:)];
        [content addGestureRecognizer:tap];
        [self.issueDataSource addObject:content];
    }
}

- (void)tapLabel:(UITapGestureRecognizer *)sender {
    NSInteger tag = sender.view.tag - 60000;
    NSString *knowledge = @"";
    if (tag >= 0 &&
        self.knowledge &&
        [self.knowledge isKindOfClass:[NSArray class]] &&
        self.knowledge.count > 0) {
        knowledge = [kitUtils DataTOjsonString:[self.knowledge[tag] yy_modelToJSONObject]];
    }
    
    [self routerEventWithName:GXHotIssueSendMessageEventName
                     userInfo:@{
        RouterEventGetSendTextMessage:[((UILabel *)sender.view).text substringFromIndex:2],
        RouterEventGetSendTextMessageKnowledge:knowledge,
    }];
}

/// 重写子视图布局
- (void)defineLayout {
    [super defineLayout];
    
}

- (void)reloadViewWithTitleItem:(NSArray <NSNumber *>*)titleH
                      issueItem:(NSArray <NSNumber *>*)issueH {
    __block CGFloat y = 0;
    @WeakObj(self);
    [self.titleDataSource enumerateObjectsUsingBlock:^(OSRobotHotSubIssueListTypeTitleView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        @StrongObj(self);
        CGFloat height = ((NSNumber *)[titleH objectOrNilAtIndex:idx]).floatValue;
        obj.frame = CGRectMake(0.f, y, self.tos_width, height);
        [obj reloadView];
        y += height;
        
        if (self.selectedIndex == idx) {
            [self.issueDataSource enumerateObjectsUsingBlock:^(UILabel * obj, NSUInteger idx, BOOL * _Nonnull stop) {
                @StrongObj(self);
                CGFloat height = ((NSNumber *)[issueH objectOrNilAtIndex:idx]).floatValue;
                obj.frame = CGRectMake(0.f, y, self.tos_width, height);
                y += height;
                if (!self.hiddenRefresh &&
                    idx == self.issueDataSource.count - 1) {
                    self.refreshIcon.tosSD_y = y + self.tosSD_y;
                    self.refreshBtn.tosSD_y = y + self.tosSD_y;
                    y += 18.f;
                }
            }];
        }
    }];
}

/// 赋值
/// @param item 赋值
- (void)setupIssueItem:(NSArray <NSString *>*)item knowledges:(NSArray <KnowledgeDataModel *>*)knowledges {
    @WeakObj(self);
    self.knowledge = [NSArray arrayWithArray:knowledges];
    [item enumerateObjectsUsingBlock:^(NSString * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        @StrongObj(self);
        self.issueDataSource[idx].text = [NSString stringWithFormat:@"%ld.%@",idx+1,obj?:@""];
        if (idx == 4) {
            *stop = YES;
        }
    }];
}

- (void)setupTitleItem:(NSArray <NSString *>*)item
         selectedIndex:(NSInteger)selectedIndex {
    self.selectedIndex = selectedIndex;
    
    @WeakObj(self);
    [self.titleDataSource enumerateObjectsUsingBlock:^(OSRobotHotSubIssueListTypeTitleView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        
        [obj removeFromSuperview];
    }];
    
    self.titleDataSource = [NSMutableArray array];
    [item enumerateObjectsUsingBlock:^(NSString * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        @StrongObj(self);
        BOOL selected = NO;
        if (selectedIndex == idx) {
            selected = YES;
        }
        OSRobotHotSubIssueListTypeTitleView *titleView = [[OSRobotHotSubIssueListTypeTitleView alloc] initWithFrame:CGRectZero];
        titleView.tempModelFrame = self.tempModelFrame;
        titleView.indexPath = self.indexPath;
        titleView.itemIndex = idx;
        [titleView setupTitle:obj selected:selected];
        [self addSubview:titleView];
        [self.titleDataSource addObject:titleView];
    }];
}

- (NSMutableArray<OSRobotHotSubIssueListTypeTitleView *> *)titleDataSource {
    if (!_titleDataSource) {
        _titleDataSource = [NSMutableArray array];
    }
    return _titleDataSource;
}

- (NSMutableArray<UILabel *> *)issueDataSource {
    if (!_issueDataSource) {
        _issueDataSource = [NSMutableArray array];
    }
    return _issueDataSource;
}

@end
