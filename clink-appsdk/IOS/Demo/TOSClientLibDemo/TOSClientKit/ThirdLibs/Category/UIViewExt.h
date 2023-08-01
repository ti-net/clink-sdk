/*
 Erica Sadun, http://ericasadun.com
 iPhone Developer's Cookbook, 3.0 Edition
 BSD License, Use at your own risk
 */
//  控件frame布局获取和设置

#import <UIKit/UIKit.h>

#define scaleX  kWindowWidth/320
#define scaleY  kWindowHeight/568


CGPoint CGRectGetCenter(CGRect rect);
CGRect  CGRectMoveToCenter(CGRect rect, CGPoint center);

@interface UIView (ViewFrameGeometry)


@property CGPoint origin;
@property CGSize size;

@property (readonly) CGPoint bottomLeft;
@property (readonly) CGPoint bottomRight;
@property (readonly) CGPoint topRight;

@property CGFloat height;
@property CGFloat width;

@property CGFloat top;
@property CGFloat left;

@property CGFloat bottom;
@property CGFloat right;

- (void) moveBy: (CGPoint) delta;
- (void) scaleBy: (CGFloat) scaleFactor;
- (void) fitInSize: (CGSize) aSize;



@property CGFloat centerX;
@property CGFloat centerY;


@property (nonatomic, assign)CGFloat x;
@property (nonatomic, assign)CGFloat y;

- (void)verticalCenter;

- (void)horizontalCenter;

/*
 * self的left距离view的right的距离
 */
- (void)toLeftView:(UIView *)view ofPix:(float)left;

/**
 *  self的right距离view的left的距离
 */
- (void)toRightView:(UIView *)view ofPix:(float)right;

/**
 *  self的top距离view的bottom的距离
 */
- (void)toTopView:(UIView *)view ofPix:(float)top;

/**
 *  self的bottom距离view的top的距离
 */
- (void)toBottomView:(UIView *)view ofPix:(float)bottom;

/**
 *  距离父视图底部距离
 */
- (void)marginBottom:(CGFloat)bottom;

/**
 *  距离父视图右侧距离
 */
- (void)marginRight:(CGFloat)right;

/**
 *  距离父视图上部距离
 */
- (void)marginTop:(CGFloat)top;

/**
 *  距离父视图左侧距离
 */
- (void)marginLeft:(CGFloat)left;

@end
