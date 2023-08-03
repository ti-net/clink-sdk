//
//  OSRobotHotSubIssueView.h
//  TOSClientKit
//
//  Created by 言 on 2022/8/8.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "TOSBaseView.h"

NS_ASSUME_NONNULL_BEGIN
@class KnowledgeDataModel;
@interface OSRobotHotSubIssueView : TOSBaseView

/// 赋值
/// @param item 赋值
- (void)setupItem:(NSArray <NSString *>*)item knowledges:(NSArray <KnowledgeDataModel *>*)knowledges;

- (void)reloadView:(NSArray <NSNumber *>*)itemH;

@end

NS_ASSUME_NONNULL_END
