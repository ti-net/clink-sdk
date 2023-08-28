
//  TIMRefreshComponent.m
//  TIMRefreshExample
//
//  Created by MJ Lee on 15/3/4.
//  Copyright (c) 2015年 小码哥. All rights reserved.
//

#import "TIMRefreshComponent.h"
#import "TIMRefreshConst.h"

@interface TIMRefreshComponent()
@property (strong, nonatomic) UIPanGestureRecognizer *pan;
@end

@implementation TIMRefreshComponent
#pragma mark - 初始化
- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        // 准备工作
        [self prepare];
        
        // 默认是普通状态
        self.state = TIMRefreshStateIdle;
    }
    return self;
}

- (void)prepare
{
    // 基本属性
    self.autoresizingMask = UIViewAutoresizingFlexibleWidth;
    self.backgroundColor = [UIColor clearColor];
}

- (void)layoutSubviews
{
    [self placeSubviews];
    
    [super layoutSubviews];
}

- (void)placeSubviews{}

- (void)willMoveToSuperview:(UIView *)newSuperview
{
    [super willMoveToSuperview:newSuperview];
    
    // 如果不是UIScrollView，不做任何事情
    if (newSuperview && ![newSuperview isKindOfClass:[UIScrollView class]]) return;
    
    // 旧的父控件移除监听
    [self removeObservers];
    
    if (newSuperview) { // 新的父控件
        // 记录UIScrollView
        _scrollView = (UIScrollView *)newSuperview;
        
        // 设置宽度
        self.mj_w = _scrollView.mj_w;
        // 设置位置
        self.mj_x = -_scrollView.tosmj_insetL;
    
        // 设置永远支持垂直弹簧效果
        _scrollView.alwaysBounceVertical = YES;
        // 记录UIScrollView最开始的contentInset
        _scrollViewOriginalInset = _scrollView.tosmj_inset;
        
        // 添加监听
        [self addObservers];
    }
}

- (void)drawRect:(CGRect)rect
{
    [super drawRect:rect];
    
    if (self.state == TIMRefreshStateWillRefresh) {
        // 预防view还没显示出来就调用了beginRefreshing
        self.state = TIMRefreshStateRefreshing;
    }
}

#pragma mark - KVO监听
- (void)addObservers
{
    NSKeyValueObservingOptions options = NSKeyValueObservingOptionNew | NSKeyValueObservingOptionOld;
    [self.scrollView addObserver:self forKeyPath:TIMRefreshKeyPathContentOffset options:options context:nil];
    [self.scrollView addObserver:self forKeyPath:TIMRefreshKeyPathContentSize options:options context:nil];
    self.pan = self.scrollView.panGestureRecognizer;
    [self.pan addObserver:self forKeyPath:TIMRefreshKeyPathPanState options:options context:nil];
}

- (void)removeObservers
{
    [self.superview removeObserver:self forKeyPath:TIMRefreshKeyPathContentOffset];
    [self.superview removeObserver:self forKeyPath:TIMRefreshKeyPathContentSize];
    [self.pan removeObserver:self forKeyPath:TIMRefreshKeyPathPanState];
    self.pan = nil;
}

- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary *)change context:(void *)context
{
    // 遇到这些情况就直接返回
    if (!self.userInteractionEnabled) return;
    
    // 这个就算看不见也需要处理
    if ([keyPath isEqualToString:TIMRefreshKeyPathContentSize]) {
        [self scrollViewContentSizeDidChange:change];
    }
    
    // 看不见
    if (self.hidden) return;
    if ([keyPath isEqualToString:TIMRefreshKeyPathContentOffset]) {
        [self scrollViewContentOffsetDidChange:change];
    } else if ([keyPath isEqualToString:TIMRefreshKeyPathPanState]) {
        [self scrollViewPanStateDidChange:change];
    }
}

- (void)scrollViewContentOffsetDidChange:(NSDictionary *)change{}
- (void)scrollViewContentSizeDidChange:(NSDictionary *)change{}
- (void)scrollViewPanStateDidChange:(NSDictionary *)change{}

#pragma mark - 公共方法
#pragma mark 设置回调对象和回调方法
- (void)setRefreshingTarget:(id)target refreshingAction:(SEL)action
{
    self.refreshingTarget = target;
    self.refreshingAction = action;
}

- (void)setState:(TIMRefreshState)state
{
    _state = state;
    
    // 加入主队列的目的是等setState:方法调用完毕、设置完文字后再去布局子控件
    TIMRefreshDispatchAsyncOnMainQueue([self setNeedsLayout];)
}

#pragma mark 进入刷新状态
- (void)beginRefreshing
{
    [UIView animateWithDuration:TIMRefreshFastAnimationDuration animations:^{
        self.alpha = 1.0;
    }];
    self.pullingPercent = 1.0;
    // 只要正在刷新，就完全显示
    if (self.window) {
        self.state = TIMRefreshStateRefreshing;
    } else {
        // 预防正在刷新中时，调用本方法使得header inset回置失败
        if (self.state != TIMRefreshStateRefreshing) {
            self.state = TIMRefreshStateWillRefresh;
            // 刷新(预防从另一个控制器回到这个控制器的情况，回来要重新刷新一下)
            [self setNeedsDisplay];
        }
    }
}

- (void)beginRefreshingWithCompletionBlock:(void (^)(void))completionBlock
{
    self.beginRefreshingCompletionBlock = completionBlock;
    
    [self beginRefreshing];
}

#pragma mark 结束刷新状态
- (void)endRefreshing
{
    TIMRefreshDispatchAsyncOnMainQueue(self.state = TIMRefreshStateIdle;)
}

- (void)endRefreshingWithCompletionBlock:(void (^)(void))completionBlock
{
    self.endRefreshingCompletionBlock = completionBlock;
    
    [self endRefreshing];
}

#pragma mark 是否正在刷新
- (BOOL)isRefreshing
{
    return self.state == TIMRefreshStateRefreshing || self.state == TIMRefreshStateWillRefresh;
}

#pragma mark 自动切换透明度
- (void)setAutoChangeAlpha:(BOOL)autoChangeAlpha
{
    self.automaticallyChangeAlpha = autoChangeAlpha;
}

- (BOOL)isAutoChangeAlpha
{
    return self.isAutomaticallyChangeAlpha;
}

- (void)setAutomaticallyChangeAlpha:(BOOL)automaticallyChangeAlpha
{
    _automaticallyChangeAlpha = automaticallyChangeAlpha;
    
    if (self.isRefreshing) return;
    
    if (automaticallyChangeAlpha) {
        self.alpha = self.pullingPercent;
    } else {
        self.alpha = 1.0;
    }
}

#pragma mark 根据拖拽进度设置透明度
- (void)setPullingPercent:(CGFloat)pullingPercent
{
    _pullingPercent = pullingPercent;
    
    if (self.isRefreshing) return;
    
    if (self.isAutomaticallyChangeAlpha) {
        self.alpha = pullingPercent;
    }
}

#pragma mark - 内部方法
- (void)executeRefreshingCallback
{
    TIMRefreshDispatchAsyncOnMainQueue({
        if (self.refreshingBlock) {
            self.refreshingBlock();
        }
        if ([self.refreshingTarget respondsToSelector:self.refreshingAction]) {
            TIMRefreshMsgSend(TIMRefreshMsgTarget(self.refreshingTarget), self.refreshingAction, self);
        }
        if (self.beginRefreshingCompletionBlock) {
            self.beginRefreshingCompletionBlock();
        }
    })
}

#pragma mark - <<< Deprecation compatible function >>> -
- (void)setEndRefreshingAnimateCompletionBlock:(TIMRefreshComponentEndRefreshingCompletionBlock)endRefreshingAnimateCompletionBlock {
    _endRefreshingAnimationBeginAction = endRefreshingAnimateCompletionBlock;
}
@end

@implementation UILabel(TIMRefresh)
+ (instancetype)mj_label
{
    UILabel *label = [[self alloc] init];
    label.font = TIMRefreshLabelFont;
    label.textColor = TIMRefreshLabelTextColor;
    label.autoresizingMask = UIViewAutoresizingFlexibleWidth;
    label.textAlignment = NSTextAlignmentCenter;
    label.backgroundColor = [UIColor clearColor];
    return label;
}

- (CGFloat)mj_textWidth {
    CGFloat stringWidth = 0;
    CGSize size = CGSizeMake(MAXFLOAT, MAXFLOAT);
    
    if (self.attributedText) {
        if (self.attributedText.length == 0) { return 0; }
        stringWidth = [self.attributedText boundingRectWithSize:size
                                                        options:NSStringDrawingUsesLineFragmentOrigin
                                                        context:nil].size.width;
    } else {
        if (self.text.length == 0) { return 0; }
        NSAssert(self.font != nil, @"请检查 mj_label's `font` 是否设置正确");
        stringWidth = [self.text boundingRectWithSize:size
                                              options:NSStringDrawingUsesLineFragmentOrigin
                                           attributes:@{NSFontAttributeName:self.font}
                                              context:nil].size.width;
    }
    return stringWidth;
}
@end
