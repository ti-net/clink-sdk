//
//  TIMRefreshFooterView.m
//  TIMRefreshView
//
//  Created by aier on 15-2-23.
//  Copyright (c) 2015年 GSD. All rights reserved.
//


#import "TIMRefreshFooterView.h"
#import "UIView+SDExtension.h"

@implementation TIMRefreshFooterView
{
    CGFloat _originalScrollViewContentHeight;
}

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        self.textForNormalState = @"上拉可以加载最新数据";
        self.stateIndicatorViewNormalTransformAngle = M_PI;
        self.stateIndicatorViewWillRefreshStateTransformAngle = 0;
        [self setRefreshState:TIMRefreshViewStateNormal];
    }
    return self;
}

- (void)layoutSubviews
{
    [super layoutSubviews];
    
    self.activityIndicatorView.hidden = YES;
    _originalScrollViewContentHeight = self.scrollView.contentSize.height;
    self.center = CGPointMake(self.scrollView.tosSD_width * 0.5, self.scrollView.contentSize.height + self.tosSD_height * 0.5); // + self.scrollView.contentInset.bottom
    
    self.hidden = [self shouldHide];
}

- (void)didMoveToSuperview
{
    [super didMoveToSuperview];
    self.scrollViewEdgeInsets = UIEdgeInsetsMake(0, 0, self.tosSD_height, 0);
}

- (BOOL)shouldHide
{
    if (self.isEffectedByNavigationController) {
        return (self.scrollView.bounds.size.height - SDKNavigationBarHeight > self.tosSD_y); //  + self.scrollView.contentInset.bottom
    }
    return (self.scrollView.bounds.size.height> self.tosSD_y); // + self.scrollView.contentInset.bottom
}


- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary *)change context:(void *)context
{
    if (![keyPath isEqualToString:TIMRefreshViewObservingkeyPath] || self.refreshState == TIMRefreshViewStateRefreshing) return;
    
    CGFloat y = [change[@"new"] CGPointValue].y;
    CGFloat criticalY = self.scrollView.contentSize.height - self.scrollView.tosSD_height + self.tosSD_height + self.scrollView.contentInset.bottom;
    
    // 如果scrollView内容有增减，重新调整refreshFooter位置
    if (self.scrollView.contentSize.height != _originalScrollViewContentHeight) {
        [self layoutSubviews];
    }
    
    // 只有在 y>0 以及 scrollview的高度不为0 时才判断
    if ((y <= 0) || (self.scrollView.bounds.size.height == 0)) return;
    
    // 触发TIMRefreshViewStateRefreshing状态
    if (y <= criticalY && (self.refreshState == TIMRefreshViewStateWillRefresh) && !self.scrollView.isDragging) {
        [self setRefreshState:TIMRefreshViewStateRefreshing];
        return;
    }
    
    // 触发TIMRefreshViewStateWillRefresh状态
    if (y > criticalY && (TIMRefreshViewStateNormal == self.refreshState)) {
        if (self.hidden) return;
        [self setRefreshState:TIMRefreshViewStateWillRefresh];
        return;
    }
    
    if (y <= criticalY && self.scrollView.isDragging && (TIMRefreshViewStateNormal != self.refreshState)) {
        [self setRefreshState:TIMRefreshViewStateNormal];
    }
    

}

@end
