//
//  TIMRefreshView.m
//  TIMRefreshView
//
//  Created by aier on 15-2-22.
//  Copyright (c) 2015年 GSD. All rights reserved.
//

/**
 
 *******************************************************
 *                                                      *
 * 感谢您的支持， 如果下载的代码在使用过程中出现BUG或者其他问题    *
 * 您可以发邮件到 或者 到                       *
 * https://github.com/gsdios?tab=repositories 提交问题     *
 *                                                      *
 *******************************************************
 
 */
#import "TIMRefreshView.h"
#import "UIView+SDExtension.h"
#import "TIMConstants.h"

CGFloat const TIMRefreshViewDefaultHeight = 70.0f;
CGFloat const TIMActivityIndicatorViewMargin = 50.0f;
CGFloat const TIMTextIndicatorMargin = 20.0f;
CGFloat const TIMTimeIndicatorMargin = 10.0f;

@implementation TIMRefreshView
{
    UIImageView *_stateIndicatorView;
    UILabel *_textIndicator;
    UILabel *_timeIndicator;
    NSString *_lastRefreshingTimeString;
    BOOL _hasSetOriginalInsets;
}

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        
        UIActivityIndicatorView *activity = [[UIActivityIndicatorView alloc] init];
        activity.activityIndicatorViewStyle = UIActivityIndicatorViewStyleGray;
        [activity startAnimating];
        [self addSubview:activity];
        _activityIndicatorView = activity;
        
        // 状态提示图片
        UIImageView *stateIndicator = [[UIImageView alloc] init];
        stateIndicator.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"sdRefeshView_arrow"]];
        [self addSubview:stateIndicator];
        _stateIndicatorView = stateIndicator;
        _stateIndicatorView.bounds = CGRectMake(0, 0, 15, 40);
        
        // 状态提示label
        UILabel *textIndicator = [[UILabel alloc] init];
        textIndicator.bounds = CGRectMake(0, 0, 300, 30);
        textIndicator.textAlignment = NSTextAlignmentCenter;
        textIndicator.backgroundColor = [UIColor clearColor];
        textIndicator.font = [UIFont systemFontOfSize:14];
        textIndicator.textColor = [UIColor lightGrayColor];
        [self addSubview:textIndicator];
        _textIndicator = textIndicator;
        
        // 更新时间提示label
        UILabel *timeIndicator = [[UILabel alloc] init];
        timeIndicator.bounds = CGRectMake(0, 0, 160, 16);
        timeIndicator.textAlignment = NSTextAlignmentCenter;
        timeIndicator.textColor = [UIColor lightGrayColor];
        timeIndicator.font = [UIFont systemFontOfSize:14];
        [self addSubview:timeIndicator];
        _timeIndicator = timeIndicator;
    }
    return self;
}

+ (instancetype)refreshView
{
    return [[self alloc] init];
}

- (void)willMoveToSuperview:(UIView *)newSuperview
{
    [super willMoveToSuperview:newSuperview];
    
    if (!newSuperview) {
        [self.superview removeObserver:self forKeyPath:TIMRefreshViewObservingkeyPath];
    }
}

- (void)didMoveToSuperview
{
    [super didMoveToSuperview];
    self.bounds = CGRectMake(0, 0, self.scrollView.frame.size.width, TIMRefreshViewDefaultHeight);
}

- (void)layoutSubviews
{
    [super layoutSubviews];
    
    _activityIndicatorView.center = CGPointMake(TIMActivityIndicatorViewMargin, self.height * 0.5);
    _stateIndicatorView.center = _activityIndicatorView.center;
    
    _textIndicator.center = CGPointMake(self.width * 0.5, _activityIndicatorView.height * 0.5 + TIMTextIndicatorMargin);
    _timeIndicator.center = CGPointMake(self.width * 0.5, self.height - _timeIndicator.height * 0.5 - TIMTimeIndicatorMargin);
}

- (NSString *)lastRefreshingTimeString
{
    if (_lastRefreshingTimeString == nil) {
        return [self refreshingTimeString];
    }
    return _lastRefreshingTimeString;
}

- (void)addToScrollView:(UIScrollView *)scrollView
{
    _scrollView = scrollView;
    
    [_scrollView addSubview:self];
    [_scrollView addObserver:self forKeyPath:TIMRefreshViewObservingkeyPath options:NSKeyValueObservingOptionNew context:nil];
    
    // 默认是在NavigationController控制下，否则可以调用addToScrollView:isEffectedByNavigationController:(设值为NO) 即可
    _isEffectedByNavigationController = YES;
}

- (void)addToScrollView:(UIScrollView *)scrollView isEffectedByNavigationController:(BOOL)effectedByNavigationController
{
    [self addToScrollView:scrollView];
    _isEffectedByNavigationController = effectedByNavigationController;
    _originalEdgeInsets = scrollView.contentInset;
}

- (void)addTarget:(id)target refreshAction:(SEL)action
{
    _beginRefreshingTarget = target;
    _beginRefreshingAction = action;
}


// 获得在scrollView的contentInset原来基础上增加一定值之后的新contentInset
- (UIEdgeInsets)syntheticalEdgeInsetsWithEdgeInsets:(UIEdgeInsets)edgeInsets
{
    return UIEdgeInsetsMake(_originalEdgeInsets.top + edgeInsets.top, _originalEdgeInsets.left + edgeInsets.left, _originalEdgeInsets.bottom + edgeInsets.bottom, _originalEdgeInsets.right + edgeInsets.right);
}

- (void)setRefreshState:(TIMRefreshViewState)refreshState
{
    _refreshState = refreshState;
    
    switch (refreshState) {
            // 进入刷新状态
        case TIMRefreshViewStateRefreshing:
        {
            if (!_hasSetOriginalInsets) {
                _originalEdgeInsets = self.scrollView.contentInset;
                _hasSetOriginalInsets = YES;
            }
            
            _scrollView.contentInset = [self syntheticalEdgeInsetsWithEdgeInsets:self.scrollViewEdgeInsets];
            
            [_activityIndicatorView startAnimating];
            _stateIndicatorView.hidden = YES;
            _activityIndicatorView.hidden = NO;
            _lastRefreshingTimeString = [self refreshingTimeString];
            _textIndicator.text = TIMRefreshViewRefreshingStateText;
            
            if (self.beginRefreshingOperation) {
                self.beginRefreshingOperation();
            } else if (self.beginRefreshingTarget) {
                if ([self.beginRefreshingTarget respondsToSelector:self.beginRefreshingAction]) {
                    
                    // 屏蔽performSelector-leak警告
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Warc-performSelector-leaks"
                    [self.beginRefreshingTarget performSelector:self.beginRefreshingAction];
                }
            }
        }
            break;
            
        case TIMRefreshViewStateWillRefresh:
        {
            _textIndicator.text = TIMRefreshViewWillRefreshStateText;
            [UIView animateWithDuration:0.5 animations:^{
                self->_stateIndicatorView.transform = CGAffineTransformMakeRotation(self.stateIndicatorViewWillRefreshStateTransformAngle);
            }];
        }
            break;
            
        case TIMRefreshViewStateNormal:
        {
            [UIView animateWithDuration:0.5 animations:^{
                self->_stateIndicatorView.transform = CGAffineTransformMakeRotation(self.stateIndicatorViewNormalTransformAngle);
            }];
            _textIndicator.text = self.textForNormalState;
            
            _timeIndicator.text = [NSString stringWithFormat:@"最后更新：%@", [self lastRefreshingTimeString]];
            _stateIndicatorView.hidden = NO;
            [_activityIndicatorView stopAnimating];
            _activityIndicatorView.hidden = YES;
        }
            break;
            
        default:
            break;
    }
}


- (void)endRefreshing
{
    [UIView animateWithDuration:0.6 animations:^{
        self->_scrollView.contentInset = self->_originalEdgeInsets;
    } completion:^(BOOL finished) {
        [self setRefreshState:TIMRefreshViewStateNormal];
        if (self.isManuallyRefreshing) {
            self.isManuallyRefreshing = NO;
        }
    }];
}

// 更新时间
- (NSString *)refreshingTimeString
{
    NSDate *date = [NSDate date];
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    formatter.dateFormat = @"HH:mm";
    return [formatter stringFromDate:date];
}

// 保留！
- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary *)change context:(void *)context
{
    ;
}

@end
