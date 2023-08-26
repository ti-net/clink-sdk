//
//  TIMRefreshHeaderView.m
//  TIMRefreshView
//
//  Created by aier on 15-2-22.
//  Copyright (c) 2015年 GSD. All rights reserved.
//



#import "TIMRefreshHeaderView.h"
#import "UIView+SDExtension.h"

@implementation TIMRefreshHeaderView
{
    BOOL _hasLayoutedForManuallyRefreshing;
}

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        self.textForNormalState = @"下拉可以加载最新数据";
        self.stateIndicatorViewNormalTransformAngle = 0;
        self.stateIndicatorViewWillRefreshStateTransformAngle = M_PI;
        [self setRefreshState:TIMRefreshViewStateNormal];
    }
    return self;
}

- (CGFloat)yOfCenterPoint
{
    //    if (self.isManuallyRefreshing && self.isEffectedByNavigationController && TIMRefreshViewMethodIOS7) {
    //        return - (self.height * 0.5 + self.originalEdgeInsets.top - SDKNavigationBarHeight);
    //    }
    return - (self.tosSD_height * 0.5);
}

- (void)didMoveToSuperview
{
    [super didMoveToSuperview];
    self.scrollViewEdgeInsets = UIEdgeInsetsMake(self.frame.size.height, 0, 0, 0);
}

- (void)layoutSubviews
{
    [super layoutSubviews];
    
    self.center = CGPointMake(self.scrollView.tosSD_width * 0.5, [self yOfCenterPoint]);
    
    // 手动刷新
    if (self.isManuallyRefreshing && !_hasLayoutedForManuallyRefreshing && self.scrollView.contentInset.top > 0) {
        self.activityIndicatorView.hidden = NO;
        
        // 模拟下拉操作
        CGPoint temp = self.scrollView.contentOffset;
        temp.y -= self.tosSD_height * 2;
        self.scrollView.contentOffset = temp; // 触发准备刷新
        temp.y += self.tosSD_height;
        self.scrollView.contentOffset = temp; // 触发刷新
        
        _hasLayoutedForManuallyRefreshing = YES;
    } else {
        self.activityIndicatorView.hidden = !self.isManuallyRefreshing;
    }
}

- (void)autoRefreshWhenViewDidAppear
{
    self.isManuallyRefreshing = YES;
}

- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary *)change context:(void *)context
{
    if (![keyPath isEqualToString:TIMRefreshViewObservingkeyPath] || self.refreshState == TIMRefreshViewStateRefreshing) return;
    
    CGFloat y = [change[@"new"] CGPointValue].y;
    CGFloat criticalY = -self.tosSD_height - self.scrollView.contentInset.top;
    
    // 只有在 y<=0 以及 scrollview的高度不为0 时才判断
    if ((y > 0) || (self.scrollView.bounds.size.height == 0)) return;
    
    // 触发TIMRefreshViewStateRefreshing状态
    if (y <= criticalY && (self.refreshState == TIMRefreshViewStateWillRefresh) && !self.scrollView.isDragging) {
        [self setRefreshState:TIMRefreshViewStateRefreshing];
        return;
    }
    
    // 触发TIMRefreshViewStateWillRefresh状态
    if (y < criticalY && (TIMRefreshViewStateNormal == self.refreshState)) {
        [self setRefreshState:TIMRefreshViewStateWillRefresh];
        return;
    }
    
    if (y > criticalY && self.scrollView.isDragging && (TIMRefreshViewStateNormal != self.refreshState)) {
        [self setRefreshState:TIMRefreshViewStateNormal];
    }
}

@end
