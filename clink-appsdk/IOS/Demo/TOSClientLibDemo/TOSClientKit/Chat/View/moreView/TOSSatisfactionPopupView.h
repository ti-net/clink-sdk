//
//  TOSSatisfactionPopupView.h
//  TOSClientKit
//
//  Created by 言 on 2023/9/27.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import <TOSClientKit/TOSClientKit.h>

NS_ASSUME_NONNULL_BEGIN
@class TIMMessageFrame;
@interface TOSSatisfactionPopupView : TOSBaseView

/// 提交用到的 uniqueId
@property (nonatomic, copy) NSString                * uniqueId;

/// 提交用到的 mainUniqueId
@property (nonatomic, copy) NSString                * mainUniqueId;


@property (nonatomic, strong) TIMMessageFrame *tempModelFrame;


/// 是否在提交后退出ViewController，NO：不退出，刷新消息界面。YES：退出
@property (nonatomic, assign) BOOL popViewController;

/// 是否显示暂不评价按钮
@property (nonatomic, assign) BOOL showNotEvaluated;

/// 展示弹窗
- (void)showPopupView;

@end

NS_ASSUME_NONNULL_END
