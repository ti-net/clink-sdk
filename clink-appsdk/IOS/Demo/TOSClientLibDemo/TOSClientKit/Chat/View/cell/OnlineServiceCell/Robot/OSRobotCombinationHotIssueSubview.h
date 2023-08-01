//
//  OSRobotCombinationHotIssueSubview.h
//  TOSClientKit
//
//  Created by 言 on 2022/8/1.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "TOSBaseView.h"

NS_ASSUME_NONNULL_BEGIN
@class TIMMessageFrame;
@class CombinationMessage;
@interface OSRobotCombinationHotIssueSubview : TOSBaseView

- (void)setWithModel:(CombinationMessage *)model;
- (void)setModelFrame:(TIMMessageFrame *)modelFrame;

/// 热点问题的标题高度
@property (nonatomic, assign) CGRect hotIssueTitleF;

/// 换一换高度
@property (nonatomic, assign) CGFloat refreshBtnY;

/// 换一换icon高度
@property (nonatomic, assign) CGFloat refreshIconY;

@property (nonatomic, strong) TIMMessageFrame *tempModelFrame;
@property (nonatomic, strong) NSIndexPath *indexPath;

@end

NS_ASSUME_NONNULL_END
