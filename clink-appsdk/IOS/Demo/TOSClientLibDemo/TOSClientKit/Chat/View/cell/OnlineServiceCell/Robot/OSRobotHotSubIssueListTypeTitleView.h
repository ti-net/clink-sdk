//
//  OSRobotHotSubIssueListTypeTitleView.h
//  TOSClientKit
//
//  Created by 言 on 2022/12/26.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <TOSClientKit/TOSClientKit.h>

NS_ASSUME_NONNULL_BEGIN

@class TIMMessageFrame;
@interface OSRobotHotSubIssueListTypeTitleView : TOSBaseView

@property (nonatomic, strong) TIMMessageFrame *tempModelFrame;
@property (nonatomic, strong) NSIndexPath *indexPath;

/// 当前Title下标
@property (nonatomic, assign) NSInteger itemIndex;

/// 赋值
/// @param title 标题
/// @param selected 选中状态
- (void)setupTitle:(NSString *)title selected:(BOOL)selected;

- (void)reloadView;

@end

NS_ASSUME_NONNULL_END
