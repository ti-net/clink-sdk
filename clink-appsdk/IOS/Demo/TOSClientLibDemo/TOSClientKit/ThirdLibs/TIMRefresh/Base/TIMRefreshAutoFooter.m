//
//  TIMRefreshAutoFooter.m
//  TIMRefreshExample
//
//  Created by MJ Lee on 15/4/24.
//  Copyright (c) 2015年 小码哥. All rights reserved.
//

#import "TIMRefreshAutoFooter.h"

@interface TIMRefreshAutoFooter()
/** 一个新的拖拽 */
@property (nonatomic) BOOL triggerByDrag;
@property (nonatomic) NSInteger leftTriggerTimes;
@end

@implementation TIMRefreshAutoFooter

#pragma mark - 初始化
- (void)willMoveToSuperview:(UIView *)newSuperview
{
    [super willMoveToSuperview:newSuperview];
    
    if (newSuperview) { // 新的父控件
        if (self.hidden == NO) {
            self.scrollView.tosmj_insetB += self.mj_h;
        }
        
        // 设置位置
        self.mj_y = _scrollView.tosmj_contentH;
    } else { // 被移除了
        if (self.hidden == NO) {
            self.scrollView.tosmj_insetB -= self.mj_h;
        }
    }
}

#pragma mark - 过期方法
- (void)setAppearencePercentTriggerAutoRefresh:(CGFloat)appearencePercentTriggerAutoRefresh
{
    self.triggerAutomaticallyRefreshPercent = appearencePercentTriggerAutoRefresh;
}

- (CGFloat)appearencePercentTriggerAutoRefresh
{
    return self.triggerAutomaticallyRefreshPercent;
}

#pragma mark - 实现父类的方法
- (void)prepare
{
    [super prepare];
    
    // 默认底部控件100%出现时才会自动刷新
    self.triggerAutomaticallyRefreshPercent = 1.0;
    
    // 设置为默认状态
    self.automaticallyRefresh = YES;
    
    self.autoTriggerTimes = 1;
}

- (void)scrollViewContentSizeDidChange:(NSDictionary *)change
{
    [super scrollViewContentSizeDidChange:change];
    
    // 设置位置
    self.mj_y = self.scrollView.tosmj_contentH + self.ignoredScrollViewContentInsetBottom;
}

- (void)scrollViewContentOffsetDidChange:(NSDictionary *)change
{
    [super scrollViewContentOffsetDidChange:change];
    
    if (self.state != TIMRefreshStateIdle || !self.automaticallyRefresh || self.mj_y == 0) return;
    
    if (_scrollView.tosmj_insetT + _scrollView.tosmj_contentH > _scrollView.mj_h) { // 内容超过一个屏幕
        // 这里的_scrollView.mj_contentH替换掉self.mj_y更为合理
        if (_scrollView.tosmj_offsetY >= _scrollView.tosmj_contentH - _scrollView.mj_h + self.mj_h * self.triggerAutomaticallyRefreshPercent + _scrollView.tosmj_insetB - self.mj_h) {
            // 防止手松开时连续调用
            CGPoint old = [change[@"old"] CGPointValue];
            CGPoint new = [change[@"new"] CGPointValue];
            if (new.y <= old.y) return;
            
            if (_scrollView.isDragging) {
                self.triggerByDrag = YES;
            }
            // 当底部刷新控件完全出现时，才刷新
            [self beginRefreshing];
        }
    }
}

- (void)scrollViewPanStateDidChange:(NSDictionary *)change
{
    [super scrollViewPanStateDidChange:change];
    
    if (self.state != TIMRefreshStateIdle) return;
    
    UIGestureRecognizerState panState = _scrollView.panGestureRecognizer.state;
    
    switch (panState) {
        // 手松开
        case UIGestureRecognizerStateEnded: {
            if (_scrollView.tosmj_insetT + _scrollView.tosmj_contentH <= _scrollView.mj_h) {  // 不够一个屏幕
                if (_scrollView.tosmj_offsetY >= - _scrollView.tosmj_insetT) { // 向上拽
                    self.triggerByDrag = YES;
                    [self beginRefreshing];
                }
            } else { // 超出一个屏幕
                if (_scrollView.tosmj_offsetY >= _scrollView.tosmj_contentH + _scrollView.tosmj_insetB - _scrollView.mj_h) {
                    self.triggerByDrag = YES;
                    [self beginRefreshing];
                }
            }
        }
            break;
            
        case UIGestureRecognizerStateBegan: {
            [self resetTriggerTimes];
        }
            break;
            
        default:
            break;
    }
}

- (BOOL)unlimitedTrigger {
    return self.leftTriggerTimes == -1;
}

- (void)beginRefreshing
{
    if (self.triggerByDrag && self.leftTriggerTimes <= 0 && !self.unlimitedTrigger) {
        return;
    }
    
    [super beginRefreshing];
}

- (void)setState:(TIMRefreshState)state
{
    TIMRefreshCheckState
    
    if (state == TIMRefreshStateRefreshing) {
        [self executeRefreshingCallback];
    } else if (state == TIMRefreshStateNoMoreData || state == TIMRefreshStateIdle) {
        if (self.triggerByDrag) {
            if (!self.unlimitedTrigger) {
                self.leftTriggerTimes -= 1;
            }
            self.triggerByDrag = NO;
        }
        
        if (TIMRefreshStateRefreshing == oldState) {
            if (self.scrollView.pagingEnabled) {
                CGPoint offset = self.scrollView.contentOffset;
                offset.y -= self.scrollView.tosmj_insetB;
                [UIView animateWithDuration:TIMRefreshSlowAnimationDuration animations:^{
                    self.scrollView.contentOffset = offset;
                    
                    if (self.endRefreshingAnimationBeginAction) {
                        self.endRefreshingAnimationBeginAction();
                    }
                } completion:^(BOOL finished) {
                    if (self.endRefreshingCompletionBlock) {
                        self.endRefreshingCompletionBlock();
                    }
                }];
                return;
            }
            
            if (self.endRefreshingCompletionBlock) {
                self.endRefreshingCompletionBlock();
            }
        }
    }
}

- (void)resetTriggerTimes {
    self.leftTriggerTimes = self.autoTriggerTimes;
}

- (void)setHidden:(BOOL)hidden
{
    BOOL lastHidden = self.isHidden;
    
    [super setHidden:hidden];
    
    if (!lastHidden && hidden) {
        self.state = TIMRefreshStateIdle;
        
        self.scrollView.tosmj_insetB -= self.mj_h;
    } else if (lastHidden && !hidden) {
        self.scrollView.tosmj_insetB += self.mj_h;
        
        // 设置位置
        self.mj_y = _scrollView.tosmj_contentH;
    }
}

- (void)setAutoTriggerTimes:(NSInteger)autoTriggerTimes {
    _autoTriggerTimes = autoTriggerTimes;
    self.leftTriggerTimes = autoTriggerTimes;
}
@end
