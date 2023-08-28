//
//  OSRobotHotSubIssueView.m
//  TOSClientKit
//
//  Created by 言 on 2022/8/8.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "OSRobotHotSubIssueView.h"
#import "TIMConstants.h"
#import "YYKit.h"
#import "ICMessageConst.h"
#import "NSString+Extension.h"
#import "UIResponder+GXRouter.h"
#import <TOSClientLib/CombinationMessage.h>
#import "kitUtils.h"
#import <TOSClientKit/TOSKitCustomInfo.h>

@interface OSRobotHotSubIssueView ()

@property (nonatomic, strong) NSMutableArray <YYLabel *>*dataSource;

@property (nonatomic, strong) NSArray <KnowledgeDataModel *>*knowledge;

@end

@implementation OSRobotHotSubIssueView

/// 添加子视图
- (void)setupSubviews {
    [super setupSubviews];
    self.backgroundColor = [UIColor clearColor];
    
    for (NSInteger i = 0; i < TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber; i++) {
        
        YYLabel *content = [[YYLabel alloc] init];
        content.tag = 60000+i;
//        content.numberOfLines = 2;
//        content.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
//        content.textColor = TOSHexAColor(0x262626,1.f);
        content.numberOfLines = 0;
        content.font = TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssueTitleFont;
        content.textColor = TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_hotSubIssueTitleColor;
        content.userInteractionEnabled = YES;
        [self addSubview:content];
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapLabel:)];
        [content addGestureRecognizer:tap];
        [self.dataSource addObject:content];
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

- (void)reloadView:(NSArray <NSNumber *>*)itemH {
    __block CGFloat y = 0;
    @WeakObj(self);
    [self.dataSource enumerateObjectsUsingBlock:^(YYLabel * obj, NSUInteger idx, BOOL * _Nonnull stop) {
        @StrongObj(self);
        CGFloat height = ((NSNumber *)[itemH objectOrNilAtIndex:idx]).floatValue;
        obj.frame = CGRectMake(0.f, y, self.tos_width, height);
        y += height;
    }];
}

/// 赋值
/// @param item 赋值
- (void)setupItem:(NSArray <NSString *>*)item knowledges:(NSArray <KnowledgeDataModel *>*)knowledges {
    self.knowledge = [NSArray arrayWithArray:knowledges];
    [item enumerateObjectsUsingBlock:^(NSString * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        self.dataSource[idx].text = [NSString stringWithFormat:@"%ld.%@",idx+1,obj?:@""];
        if (idx == TOSKitCustomInfo.shareCustomInfo.chatMessage_tosRobotCombination_showRefreshNumber-1) {
            *stop = YES;
        }
    }];
}

- (NSMutableArray<YYLabel *> *)dataSource {
    if (!_dataSource) {
        _dataSource = [NSMutableArray array];
    }
    return _dataSource;
}

@end
