//
//  OSRobotHotSubIssueListTypeView.h
//  TOSClientKit
//
//  Created by 言 on 2022/12/26.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "TOSBaseView.h"

NS_ASSUME_NONNULL_BEGIN
@class TIMMessageFrame;
@class KnowledgeDataModel;
@interface OSRobotHotSubIssueListTypeView : TOSBaseView

@property (nonatomic, assign) BOOL hiddenRefresh;
@property (nonatomic, weak) UIImageView *refreshIcon;
@property (nonatomic, weak) UIButton *refreshBtn;

@property (nonatomic, strong) TIMMessageFrame *tempModelFrame;
@property (nonatomic, strong) NSIndexPath *indexPath;

- (void)setupTitleItem:(NSArray <NSString *>*)item selectedIndex:(NSInteger)selectedIndex;

- (void)setupIssueItem:(NSArray <NSString *>*)item knowledges:(NSArray <KnowledgeDataModel *>*)knowledges;


- (void)reloadViewWithTitleItem:(NSArray <NSNumber *>*)titleH issueItem:(NSArray <NSNumber *>*)issueH;

@end

NS_ASSUME_NONNULL_END
