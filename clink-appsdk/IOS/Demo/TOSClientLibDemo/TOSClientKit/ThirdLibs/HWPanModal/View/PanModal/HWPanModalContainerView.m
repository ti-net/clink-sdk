//
//  HWPanModalContainerView.m
//  Pods
//
//  Created by heath wang on 2019/10/17.
//

#import "HWPanModalContainerView.h"
#import "HWPanModalContentView.h"
#import "HWPanModalPresentableHandler.h"
#import "HWDimmedView.h"
#import "HWPanContainerView.h"
#import "UIView+HW_Frame.h"
#import "HWPanIndicatorView.h"
#import "HWPanModalAnimator.h"

@interface HWPanModalContainerView () <HWPanModalPresentableHandlerDelegate, HWPanModalPresentableHandlerDataSource>

@property (nonatomic, strong) HWPanModalContentView<HWPanModalPresentable> *contentView;
@property (nonatomic, weak) UIView *presentingView;

@property (nonatomic, strong) HWPanModalPresentableHandler *handler;

// 判断弹出的view是否在做动画
@property (nonatomic, assign) BOOL isPresentedViewAnimating;
@property (nonatomic, assign) PresentationState currentPresentationState;
@property (nonatomic, assign) BOOL isPresenting;
@property (nonatomic, assign) BOOL isDismissing;

// view
@property (nonatomic, strong) HWDimmedView *backgroundView;
@property (nonatomic, strong) HWPanContainerView *panContainerView;
@property (nonatomic, strong) UIView<HWPanModalIndicatorProtocol> *dragIndicatorView;

@property (nonatomic, copy) void(^animationBlock)(void);

@property (nullable, nonatomic, strong) UISelectionFeedbackGenerator *feedbackGenerator API_AVAILABLE(ios(10.0));

@end

@implementation HWPanModalContainerView

- (instancetype)initWithPresentingView:(UIView *)presentingView contentView:(HWPanModalContentView<HWPanModalPresentable> *)contentView {
    self = [super init];
    if (self) {
        _presentingView = presentingView;
        _contentView = contentView;
    }

    return self;
}

- (void)show {
    [self prepare];
    [self presentAnimationWillBegin];
    [self beginPresentAnimation];
}

- (void)dismissAnimated:(BOOL)flag completion:(void (^)(void))completion {
    if (flag) {
        self.animationBlock = completion;
        [self dismiss:NO mode:PanModalInteractiveModeNone];
    } else {

        [[self presentable] panModalWillDismiss];
        [self removeFromSuperview];
        [[self presentable] panModalDidDismissed];

        completion ? completion() : nil;
    }
}

- (void)prepare {
    [self.presentingView addSubview:self];
    self.frame = self.presentingView.bounds;

    _handler = [[HWPanModalPresentableHandler alloc] initWithPresentable:self.contentView];
    _handler.delegate = self;
    _handler.dataSource = self;

    if (@available(iOS 10.0, *)) {
        _feedbackGenerator = [UISelectionFeedbackGenerator new];
        [_feedbackGenerator prepare];
    } else {
        // Fallback on earlier versions
    }
}

- (void)presentAnimationWillBegin {
    [[self presentable] panModalTransitionWillBegin];
    [self layoutBackgroundView];

    if ([[self presentable] originPresentationState] == PresentationStateLong) {
        self.currentPresentationState = PresentationStateLong;
    } else if ([[self presentable] originPresentationState] == PresentationStateMedium) {
        self.currentPresentationState = PresentationStateMedium;
    }
    
    [self addSubview:self.panContainerView];
    [self layoutPresentedView];
    
    [self.handler configureScrollViewInsets];
    [[self presentable] presentedViewDidMoveToSuperView];
}

- (void)beginPresentAnimation {
    self.isPresenting = YES;
    CGFloat yPos = self.contentView.shortFormYPos;
    if ([[self presentable] originPresentationState] == PresentationStateLong) {
        yPos = self.contentView.longFormYPos;
    } else if ([[self presentable] originPresentationState] == PresentationStateMedium) {
        yPos = self.contentView.mediumFormYPos;
    }
    
    // refresh layout
    [self configureViewLayout];
    [self adjustPresentedViewFrame];

    self.panContainerView.hw_top = self.hw_height;
    
    if ([[self presentable] isHapticFeedbackEnabled]) {
        if (@available(iOS 10.0, *)) {
            [self.feedbackGenerator selectionChanged];
        }
    }
    
    [HWPanModalAnimator animate:^{
        self.panContainerView.hw_top = yPos;
        self.backgroundView.dimState = DimStateMax;
    } config:[self presentable] completion:^(BOOL completion) {
        self.isPresenting = NO;
        [[self presentable] panModalTransitionDidFinish];
        
        if (@available(iOS 10.0, *)) {
            self.feedbackGenerator = nil;
        }
    }];
    
}

- (void)layoutSubviews {
    [super layoutSubviews];
    [self configureViewLayout];
}

#pragma mark - public method

- (void)setNeedsLayoutUpdate {

    [self configureViewLayout];
    [self updateBackgroundColor];
    [self.handler observeScrollable];
    [self adjustPresentedViewFrame];
    [self.handler configureScrollViewInsets];

    [self updateContainerViewShadow];
    [self updateDragIndicatorView];
    [self updateRoundedCorners];
}

- (void)updateUserHitBehavior {
    [self checkBackgroundViewEventPass];
    [self checkPanGestureRecognizer];
}

- (void)transitionToState:(PresentationState)state animated:(BOOL)animated {

    if (![self.presentable shouldTransitionToState:state]) return;

    [self.dragIndicatorView didChangeToState:HWIndicatorStateNormal];
    [self.presentable willTransitionToState:state];

    switch (state) {
        case PresentationStateLong: {
            [self snapToYPos:self.handler.longFormYPosition animated:animated];
        }
            break;
        case PresentationStateMedium: {
            [self snapToYPos:self.handler.mediumFormYPosition animated:animated];
        }
            break;
        case PresentationStateShort: {
            [self snapToYPos:self.handler.shortFormYPosition animated:animated];
        }
            break;
        default:
            break;
    }
    self.currentPresentationState = state;
    [[self presentable] didChangeTransitionToState:state];
}

- (void)setScrollableContentOffset:(CGPoint)offset animated:(BOOL)animated {
    [self.handler setScrollableContentOffset:offset animated:animated];
}

#pragma mark - layout

- (void)adjustPresentedViewFrame {
    CGRect frame = self.frame;
    CGSize size = CGSizeMake(CGRectGetWidth(frame), CGRectGetHeight(frame) - self.handler.anchoredYPosition);
    
    self.panContainerView.hw_size = frame.size;
    self.panContainerView.contentView.frame = CGRectMake(0, 0, size.width, size.height);
    self.contentView.frame = self.panContainerView.contentView.bounds;
    [self.contentView setNeedsLayout];
    [self.contentView layoutIfNeeded];
}

- (void)configureViewLayout {

    [self.handler configureViewLayout];
    self.userInteractionEnabled = [[self presentable] isUserInteractionEnabled];
}

- (void)layoutBackgroundView {
    [self addSubview:self.backgroundView];
    [self updateBackgroundColor];
    self.backgroundView.translatesAutoresizingMaskIntoConstraints = NO;

    NSArray *hCons = [NSLayoutConstraint constraintsWithVisualFormat:@"H:|[backgroundView]|" options:0 metrics:nil views:@{@"backgroundView": self.backgroundView}];
    NSArray *vCons = [NSLayoutConstraint constraintsWithVisualFormat:@"V:|[backgroundView]|" options:0 metrics:nil views:@{@"backgroundView": self.backgroundView}];
    [NSLayoutConstraint activateConstraints:hCons];
    [NSLayoutConstraint activateConstraints:vCons];
}

- (void)updateBackgroundColor {
    self.backgroundView.blurTintColor = [self.presentable backgroundConfig].blurTintColor;
}

- (void)layoutPresentedView {
    if (!self.presentable)
        return;

    self.handler.presentedView = self.panContainerView;
    
    if ([[self presentable] allowsTouchEventsPassingThroughTransitionView]) {
        [self.panContainerView addGestureRecognizer:self.handler.panGestureRecognizer];
    } else {
        [self addGestureRecognizer:self.handler.panGestureRecognizer];
    }

    [self setNeedsLayoutUpdate];
    [self adjustPanContainerBackgroundColor];
}

- (void)adjustPanContainerBackgroundColor {
    self.panContainerView.contentView.backgroundColor = self.contentView.backgroundColor ? : [self.presentable panScrollable].backgroundColor;
}

- (void)updateDragIndicatorView {
    if ([self.presentable showDragIndicator]) {
        [self addDragIndicatorViewToView:self.panContainerView];
    } else {
        self.dragIndicatorView.hidden = YES;
    }
}

- (void)addDragIndicatorViewToView:(UIView *)view {
    // if has been add, won't update it.
    self.dragIndicatorView.hidden = NO;

    if (self.dragIndicatorView.superview == view) {
        [self.dragIndicatorView didChangeToState:HWIndicatorStateNormal];
        return;
    }

    self.handler.dragIndicatorView = self.dragIndicatorView;
    [view addSubview:self.dragIndicatorView];
    CGSize indicatorSize = [self.dragIndicatorView indicatorSize];

    self.dragIndicatorView.frame = CGRectMake((view.hw_width - indicatorSize.width) / 2, -kIndicatorYOffset - indicatorSize.height, indicatorSize.width, indicatorSize.height);

    [self.dragIndicatorView setupSubviews];
    [self.dragIndicatorView didChangeToState:HWIndicatorStateNormal];
}

- (void)updateContainerViewShadow {
    HWPanModalShadow shadow = [[self presentable] contentShadow];
    if (shadow.shadowColor) {
        [self.panContainerView updateShadow:shadow.shadowColor shadowRadius:shadow.shadowRadius shadowOffset:shadow.shadowOffset shadowOpacity:shadow.shadowOpacity];
    } else {
        [self.panContainerView clearShadow];
    }
}

- (void)updateRoundedCorners {
    if ([self.presentable shouldRoundTopCorners]) {
        [self addRoundedCornersToView:self.panContainerView.contentView];
    } else {
        [self resetRoundedCornersToView:self.panContainerView.contentView];
    }
}

- (void)addRoundedCornersToView:(UIView *)view {
    CGFloat radius = [self.presentable cornerRadius];

    UIBezierPath *bezierPath = [UIBezierPath bezierPathWithRoundedRect:view.bounds byRoundingCorners:UIRectCornerTopRight | UIRectCornerTopLeft cornerRadii:CGSizeMake(radius, radius)];

    CAShapeLayer *mask = [CAShapeLayer new];
    mask.path = bezierPath.CGPath;
    view.layer.mask = mask;

    // 提高性能
    view.layer.shouldRasterize = YES;
    view.layer.rasterizationScale = [UIScreen mainScreen].scale;
}

- (void)resetRoundedCornersToView:(UIView *)view {
    view.layer.mask = nil;
    view.layer.shouldRasterize = NO;
}

- (void)snapToYPos:(CGFloat)yPos animated:(BOOL)animated {

    if (animated) {
        [HWPanModalAnimator animate:^{
            self.isPresentedViewAnimating = YES;
            [self adjustToYPos:yPos];
        } config:self.presentable completion:^(BOOL completion) {
            self.isPresentedViewAnimating = NO;
        }];
    } else {
        [self adjustToYPos:yPos];
    }

}

- (void)adjustToYPos:(CGFloat)yPos {
    self.panContainerView.hw_top = MAX(yPos, self.handler.anchoredYPosition);

    // change dim background starting from shortFormYPosition.
    if (self.panContainerView.frame.origin.y >= self.handler.shortFormYPosition) {

        CGFloat yDistanceFromShortForm = self.panContainerView.frame.origin.y - self.handler.shortFormYPosition;
        CGFloat bottomHeight = self.hw_height - self.handler.shortFormYPosition;
        CGFloat percent = yDistanceFromShortForm / bottomHeight;
        self.backgroundView.dimState = DimStatePercent;
        self.backgroundView.percent = 1 - percent;

        [self.presentable panModalGestureRecognizer:self.handler.panGestureRecognizer dismissPercent:MIN(percent, 1)];

    } else {
        self.backgroundView.dimState = DimStateMax;
    }
}

#pragma mark - HWPanModalPresentableHandlerDelegate

- (void)adjustPresentableYPos:(CGFloat)yPos {
    [self adjustToYPos:yPos];
}

- (void)presentableTransitionToState:(PresentationState)state {
    [self transitionToState:state animated:YES];
}

- (PresentationState)getCurrentPresentationState {
    return self.currentPresentationState;
}

- (void)dismiss:(BOOL)isInteractive mode:(PanModalInteractiveMode)mode {
    self.handler.panGestureRecognizer.enabled = NO;

    [[self presentable] panModalWillDismiss];

    [HWPanModalAnimator animate:^{
        self.panContainerView.hw_top = CGRectGetHeight(self.bounds);
        self.backgroundView.dimState = DimStateOff;
        self.dragIndicatorView.alpha = 0;
    } config:[self presentable] completion:^(BOOL completion) {
        [self removeFromSuperview];
        [[self presentable] panModalDidDismissed];
        self.animationBlock ? self.animationBlock() : nil;
    }];

}

#pragma mark - HWPanModalPresentableHandlerDataSource

- (CGSize)containerSize {
    return self.presentingView.bounds.size;
}

- (BOOL)isBeingDismissed {
    return self.isDismissing;
}

- (BOOL)isBeingPresented {
    return self.isPresenting;
}
 
- (BOOL)isFormPositionAnimating {
    return self.isPresentedViewAnimating;
}

- (BOOL)isPresentedViewAnchored {
    
    if (![[self presentable] shouldRespondToPanModalGestureRecognizer:self.handler.panGestureRecognizer]) {
        return YES;
    }
    
    if (!self.isPresentedViewAnimating && self.handler.extendsPanScrolling && (CGRectGetMinY(self.panContainerView.frame) <= self.handler.anchoredYPosition || HW_TWO_FLOAT_IS_EQUAL(CGRectGetMinY(self.panContainerView.frame), self.handler.anchoredYPosition))) {
        return YES;
    }
    return NO;
}

#pragma mark - event handle

- (UIView *)hitTest:(CGPoint)point withEvent:(UIEvent *)event {
    
    if (!self.userInteractionEnabled || self.hidden || self.alpha < 0.01) {
        return nil;
    }

    if (![self pointInside:point withEvent:event]) {
        return nil;
    }
    
    BOOL eventThrough = [[self presentable] allowsTouchEventsPassingThroughTransitionView];
    if (eventThrough) {
        CGPoint convertedPoint = [self.panContainerView convertPoint:point fromView:self];
        if (CGRectGetWidth(self.panContainerView.frame) >= convertedPoint.x &&
            convertedPoint.x > 0 &&
            CGRectGetHeight(self.panContainerView.frame) >= convertedPoint.y &&
            convertedPoint.y > 0) {
            return [super hitTest:point withEvent:event];
        } else {
            return nil;
        }
    } else {
        return [super hitTest:point withEvent:event];
    }
}

- (void)checkBackgroundViewEventPass {
    if ([[self presentable] allowsTouchEventsPassingThroughTransitionView]) {
        self.backgroundView.userInteractionEnabled = NO;
        self.backgroundView.tapBlock = nil;
    } else {
        self.backgroundView.userInteractionEnabled = YES;
        __weak typeof(self) wkSelf = self;
        self.backgroundView.tapBlock = ^(UITapGestureRecognizer *recognizer) {
            if ([[wkSelf presentable] allowsTapBackgroundToDismiss]) {
                [wkSelf dismiss:NO mode:PanModalInteractiveModeNone];
            }
        };
    }
}

- (void)checkPanGestureRecognizer {
    if ([[self presentable] allowsTouchEventsPassingThroughTransitionView]) {
        [self removeGestureRecognizer:self.handler.panGestureRecognizer];
        [self.panContainerView addGestureRecognizer:self.handler.panGestureRecognizer];
    } else {
        [self.panContainerView removeGestureRecognizer:self.handler.panGestureRecognizer];
        [self addGestureRecognizer:self.handler.panGestureRecognizer];
    }
}

#pragma mark - getter

- (id<HWPanModalPresentable>)presentable {
    if ([self.contentView conformsToProtocol:@protocol(HWPanModalPresentable)]) {
        return self.contentView;
    }
    return nil;
}

- (HWDimmedView *)backgroundView {
    if (!_backgroundView) {
        if (self.presentable) {
            _backgroundView = [[HWDimmedView alloc] initWithBackgroundConfig:[self.presentable backgroundConfig]];
        } else {
            _backgroundView = [[HWDimmedView alloc] init];
        }
        
        if ([[self presentable] allowsTouchEventsPassingThroughTransitionView]) {
            _backgroundView.userInteractionEnabled = NO;
        } else {
            __weak typeof(self) wkSelf = self;
            _backgroundView.tapBlock = ^(UITapGestureRecognizer *recognizer) {
                if ([[wkSelf presentable] allowsTapBackgroundToDismiss]) {
                    [wkSelf dismiss:NO mode:PanModalInteractiveModeNone];
                }
            };
        }

    }

    return _backgroundView;
}

- (HWPanContainerView *)panContainerView {
    if (!_panContainerView) {
        _panContainerView = [[HWPanContainerView alloc] initWithPresentedView:self.contentView frame:self.bounds];
    }

    return _panContainerView;
}

- (UIView<HWPanModalIndicatorProtocol> *)dragIndicatorView {

    if (!_dragIndicatorView) {
        if ([self presentable] &&
                [[self presentable] respondsToSelector:@selector(customIndicatorView)] &&
                [[self presentable] customIndicatorView] != nil) {
            _dragIndicatorView = [[self presentable] customIndicatorView];
            // set the indicator size first in case `setupSubviews` can Not get the right size.
            _dragIndicatorView.hw_size = [[[self presentable] customIndicatorView] indicatorSize];
        } else {
            _dragIndicatorView = [HWPanIndicatorView new];
        }
    }

    return _dragIndicatorView;
}

@end
