//
//  TRLoadingView.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "TRLoadingView.h"
#import "MBProgressHUD+GeneralConfiguration.h"

@interface TRLoadingView ()

@property (nonatomic, weak) UIView *view;

@property (nonatomic, strong) MBProgressHUD *mbProgress;

@end

@implementation TRLoadingView

#pragma mark - public method -
+ (instancetype)showLoadingAddTo:(UIView *)view animated:(BOOL)animated {
    TRLoadingView *loadingView = [[self alloc] initWithView:view];
    [view addSubview:loadingView];
    [view bringSubviewToFront:loadingView];
    [loadingView showWithAnimate:animated];
    return loadingView;
}

+ (BOOL)hideLoadingForView:(UIView *)view animated:(BOOL)animated {
    TRLoadingView *loadingView = [self _loadingForView:view];
    if (loadingView) {
        [loadingView hideWithAnimate:animated];
        return YES;
    }
    return NO;
}

- (instancetype)initWithFrame:(CGRect)frame {
    NSAssert(NO, @"please use initWithView method.");
    return nil;
}

- (instancetype)init {
    NSAssert(NO, @"please use initWithView method.");
    return nil;
}

- (instancetype)initWithCoder:(NSCoder *)aDecoder {
    NSAssert(NO, @"please use initWithView method.");
    return nil;
}

- (instancetype)initWithView:(UIView *)view {
    NSAssert(view, @"super view can not be nil.");
    if (self = [super initWithFrame:CGRectZero]) {
        _view = view;
        [self setUpMBProgressWithSuperview:view];
    }
    return self;
}

- (void)layoutSubviews {
    [super layoutSubviews];
    self.frame = _view.bounds;
    _mbProgress.center = self.center;
}

- (void)showWithAnimate:(BOOL)animate {
    if (animate) {
        [UIView animateWithDuration:.3f animations:^{
            self.alpha = 1;
        }];
    } else {
        self.alpha = 1;
    }
}

- (void)showWithAnimate:(BOOL)animate addView:(UIView * )view {
    if (animate) {
        [UIView animateWithDuration:.3f animations:^{
            self.alpha = 1;
        }];
    } else {
        self.alpha = 1;
    }
}

- (void)hideWithAnimate:(BOOL)animate {
    if (animate) {
        [UIView animateWithDuration:.3f animations:^{
            self.alpha = 0;
        } completion:^(BOOL finished) {
            [self->_mbProgress hideAnimated:YES];
            if (self) {
                [self removeFromSuperview];
            }
        }];
    } else {
        self.alpha = 0;
        [_mbProgress hideAnimated:YES];
        if (self) {
            [self removeFromSuperview];
        }
    }
}

#pragma mark - MBProgress的相关设置
- (void)setUpMBProgressWithSuperview:(UIView *)superview {
    
    self.backgroundColor = [UIColor clearColor];
    self.frame = _view.bounds;
    self.alpha = 0;
    
    _mbProgress = [MBProgressHUD showHUDAddedTo:self animated:YES];
    _mbProgress.mode = MBProgressHUDModeIndeterminate;
    _mbProgress.margin = 20.f;
    _mbProgress.offset = CGPointMake(0, 0);
    _mbProgress.detailsLabel.text = @"正在加载...";
    [_mbProgress setupMBProgress];
    [_mbProgress hideAnimated:YES afterDelay:30.f];
    __weak typeof(self) weakSelf = self;
    _mbProgress.completionBlock = ^{
        __strong typeof(self) strongSelf = weakSelf;
        if (strongSelf) {
            [strongSelf removeFromSuperview];
        }
    };
}

- (void)setMaxShowTime:(CGFloat)maxShowTime {
    [_mbProgress hideAnimated:YES afterDelay:maxShowTime];
    _maxShowTime = maxShowTime;
}

- (void)setMinShowTime:(CGFloat)minShowTime {
    _mbProgress.minShowTime = minShowTime;
    _minShowTime = minShowTime;
}

+ (instancetype)_loadingForView:(UIView *)view {
    NSEnumerator *subviewEnum = [view.subviews reverseObjectEnumerator];
    for (UIView *subview in subviewEnum) {
        if ([subview isKindOfClass:self]) {
            return (TRLoadingView *)subview;
        }
    }
    return nil;
}

@end
