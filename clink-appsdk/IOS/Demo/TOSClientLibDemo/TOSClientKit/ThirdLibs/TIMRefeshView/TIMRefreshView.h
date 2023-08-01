//
//  TIMRefreshView.h
//  TIMRefreshView
//
//  Created by aier on 15-2-22.
//  Copyright (c) 2015年 GSD. All rights reserved.
//


#import <UIKit/UIKit.h>
typedef enum {
    TIMRefreshViewStateWillRefresh,
    TIMRefreshViewStateRefreshing,
    TIMRefreshViewStateNormal
} TIMRefreshViewState;

#define TIMRefreshViewMethodIOS7 ([[UIDevice currentDevice].systemVersion floatValue] >= 7.0)
#define TIMRefreshViewObservingkeyPath @"contentOffset"
#define SDKNavigationBarHeight 64

// ---------------------------配置----------------------------------

// 进入刷新状态时的提示文字
#define TIMRefreshViewRefreshingStateText @"正在加载最新数据,请稍候"
// 进入即将刷新状态时的提示文字
#define TIMRefreshViewWillRefreshStateText @"松开即可加载最新数据"

// ---------------------------配置----------------------------------

@interface TIMRefreshView : UIView

@property (nonatomic, copy) void(^beginRefreshingOperation)(void);
@property (nonatomic, weak) id beginRefreshingTarget;
@property (nonatomic, assign) SEL beginRefreshingAction;
@property (nonatomic, assign) BOOL isEffectedByNavigationController;

+ (instancetype)refreshView;

- (void)addToScrollView:(UIScrollView *)scrollView;
- (void)addToScrollView:(UIScrollView *)scrollView isEffectedByNavigationController:(BOOL)effectedByNavigationController;

- (void)addTarget:(id)target refreshAction:(SEL)action;
- (void)endRefreshing;


@property (nonatomic, weak) UIScrollView *scrollView;
@property (nonatomic, assign) TIMRefreshViewState refreshState;
@property (nonatomic, copy) NSString *textForNormalState;

// 子类自定义位置使用
@property (nonatomic, assign) UIEdgeInsets scrollViewEdgeInsets;

@property (nonatomic, assign) CGFloat stateIndicatorViewNormalTransformAngle;
@property (nonatomic, assign) CGFloat stateIndicatorViewWillRefreshStateTransformAngle;

// 记录原始contentEdgeInsets
@property (nonatomic, assign) UIEdgeInsets originalEdgeInsets;
// 加载指示器
@property (nonatomic, weak) UIActivityIndicatorView *activityIndicatorView;
@property (nonatomic, assign) BOOL isManuallyRefreshing;

- (UIEdgeInsets)syntheticalEdgeInsetsWithEdgeInsets:(UIEdgeInsets)edgeInsets;

@end
