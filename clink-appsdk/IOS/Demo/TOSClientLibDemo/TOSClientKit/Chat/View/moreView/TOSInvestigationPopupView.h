//
//  TOSInvestigationPopupView.h
//  TOSClientKit
//
//  Created by 李成 on 2023/8/9.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@protocol TOSInvestigationPopupViewDelegate <NSObject>

/// 提交评价成功
- (void)TOSInvestigationPopupViewCommitEvaluateSuccess;

/// 关闭弹窗
- (void)TOSInvestigationPopupViewCloseView;

@end

@interface TOSInvestigationPopupView : UIView

@property (nonatomic, weak) id <TOSInvestigationPopupViewDelegate>                delegate;

/// 提交用到的 uniqueId
@property (nonatomic, copy) NSString                * uniqueId;

/// 提交用到的 mainUniqueId
@property (nonatomic, copy) NSString                * mainUniqueId;

/// 标题
@property (nonatomic, copy) NSString                * clientName;

/// 展示弹窗
- (void)showPopupView;


@end

NS_ASSUME_NONNULL_END
