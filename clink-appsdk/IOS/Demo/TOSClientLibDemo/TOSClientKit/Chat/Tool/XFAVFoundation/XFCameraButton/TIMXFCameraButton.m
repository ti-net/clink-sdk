//
//  TIMXFCameraButton.m
//  
//
//  Created by xf-ling on 2017/6/1.
//  Copyright © 2017年 LXF. All rights reserved.
//

#import "TIMXFCameraButton.h"

// 默认按钮大小
#define CAMERABUTTONWIDTH 75
#define TOUCHVIEWWIDTH 55

// 录制时按钮的缩放比
#define SHOOTCAMERABUTTONSCALE 1.6f
#define SHOOTTOUCHVIEWSCALE .8f

// 录制按钮动画轨道宽度
#define PROGRESSLINEWIDTH 3

#define RGB(r, g, b, a) [UIColor colorWithRed:r/255.0 green:g/255.0 blue:b/255.0 alpha:a]

@interface TIMXFCameraButton ()

@property (weak, nonatomic) UIView *contentView;

@property (weak, nonatomic) UIView *touchView;
@property (strong, nonatomic) CAShapeLayer *trackLayer;
@property (strong, nonatomic) CAShapeLayer *progressLayer;

@property (copy, nonatomic) TapEventBlock tapEventBlock;
@property (copy, nonatomic) LongPressEventBlock longPressEventBlock;

@end

@implementation TIMXFCameraButton

#pragma mark - 工厂方法

+ (instancetype)defaultCameraButton
{
    // 设置camera view
    TIMXFCameraButton *cameraButton = [[TIMXFCameraButton alloc] initWithFrame:CGRectMake(0, 0, 120.f, 120.f)];
    cameraButton.backgroundColor = [UIColor clearColor];
    
    
    UIView *contentView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, CAMERABUTTONWIDTH, CAMERABUTTONWIDTH)];
    [contentView.layer setCornerRadius:(CAMERABUTTONWIDTH / 2)];
    contentView.backgroundColor = [UIColor colorWithRed:225/255.0f green:225/255.0f blue:230/255.0f alpha:1.0f];
    [cameraButton addSubview:contentView];
    cameraButton.contentView = contentView;
    contentView.center = CGPointMake(cameraButton.frame.size.width/2, cameraButton.frame.size.height/2);
    
    
    // 设置camera view的点击按钮
    CGFloat touchViewX = (CAMERABUTTONWIDTH - TOUCHVIEWWIDTH) / 2;
    CGFloat touchViewY = (CAMERABUTTONWIDTH - TOUCHVIEWWIDTH) / 2;
    UIView *touchView = [[UIView alloc] initWithFrame:CGRectMake(touchViewX, touchViewY, TOUCHVIEWWIDTH, TOUCHVIEWWIDTH)];
    touchView.center = CGPointMake(cameraButton.frame.size.width/2, cameraButton.frame.size.height/2);
    cameraButton.touchView = touchView;
    [cameraButton addSubview:touchView];
    [cameraButton.touchView.layer setCornerRadius:(cameraButton.touchView.bounds.size.width / 2)];
    touchView.backgroundColor = [UIColor whiteColor];
    
    [cameraButton initCircleAnimationLayer];
    
    return cameraButton;
}


#pragma mark - 点击事件与长按事件

/**
 *  配置点击事件
 */
- (void)configureTapCameraButtonEventWithBlock:(TapEventBlock)tapEventBlock
{
    self.tapEventBlock = tapEventBlock;
    
    UITapGestureRecognizer *tapGestureRecognizer = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapCameraButtonEvent:)];
    
    [self addGestureRecognizer:tapGestureRecognizer];
}

- (void)tapCameraButtonEvent:(UITapGestureRecognizer *)tapGestureRecognizer
{
    if (self.tapEventBlock)
    {
        self.tapEventBlock(tapGestureRecognizer);
    }
}

/**
 *  配置按压事件
 */
- (void)configureLongPressCameraButtonEventWithBlock:(LongPressEventBlock)longPressEventBlock
{
    self.longPressEventBlock = longPressEventBlock;
    
    UILongPressGestureRecognizer *longPressGestureRecognizer = [[UILongPressGestureRecognizer alloc] initWithTarget:self action:@selector(longPressCameraButtonEvent:)];
    
    [self addGestureRecognizer:longPressGestureRecognizer];
}

- (void)longPressCameraButtonEvent:(UILongPressGestureRecognizer *)longPressGestureRecognizer
{
    if (self.longPressEventBlock)
    {
        self.longPressEventBlock(longPressGestureRecognizer);
    }
}

#pragma mark - 录制视频按钮动画

// 初始化按钮路径
- (void)initCircleAnimationLayer
{
    float centerX = self.contentView.bounds.size.width / 2.0;
    float centerY = self.contentView.bounds.size.height / 2.0;
    //半径
    float radius = (self.contentView.bounds.size.width - PROGRESSLINEWIDTH) / 2.0;
    
    //创建贝塞尔路径
    UIBezierPath *path = [UIBezierPath bezierPathWithArcCenter:CGPointMake(centerX, centerY) radius:radius startAngle:(-0.5f * M_PI) endAngle:(1.5f * M_PI) clockwise:YES];
    
    //添加背景圆环
    CAShapeLayer *backLayer = [CAShapeLayer layer];
    backLayer.frame = self.contentView.bounds;
    backLayer.fillColor =  [[UIColor clearColor] CGColor];
    backLayer.strokeColor  = [RGB(225, 225, 230, 1.0f) CGColor];
    backLayer.lineWidth = PROGRESSLINEWIDTH;
    backLayer.path = [path CGPath];
    backLayer.strokeEnd = 1;
    [self.contentView.layer addSublayer:backLayer];
    
    //创建进度layer
    _progressLayer = [CAShapeLayer layer];
    _progressLayer.frame = self.contentView.bounds;
    _progressLayer.fillColor =  [[UIColor clearColor] CGColor];
    //指定path的渲染颜色
    _progressLayer.strokeColor  = [[UIColor blackColor] CGColor];
    _progressLayer.lineCap = kCALineCapSquare;//kCALineCapRound;
    _progressLayer.lineWidth = PROGRESSLINEWIDTH;
    _progressLayer.path = [path CGPath];
    _progressLayer.strokeEnd = 0;
    
    //设置渐变颜色
    CAGradientLayer *gradientLayer =  [CAGradientLayer layer];
    gradientLayer.frame = self.contentView.bounds;
    
    // 渐变颜色
    [gradientLayer setColors:[NSArray arrayWithObjects:(id)[RGB(76, 192, 29, 1.0f) CGColor], (id)[RGB(76, 192, 29, 1.0f) CGColor],  nil]];
//    [gradientLayer setColors:[NSArray arrayWithObjects:(id)[RGB(28, 178, 29, 1.0f) CGColor], (id)[RGB(255, 203, 0, 1.0f) CGColor],  nil]];
    
    gradientLayer.startPoint = CGPointMake(0, 0);
    gradientLayer.endPoint = CGPointMake(0, 1);
    [gradientLayer setMask:_progressLayer];     //用progressLayer来截取渐变层
    [self.contentView.layer addSublayer:gradientLayer];
    
}

// 设置按钮百分比
- (void)setProgressPercentage:(CGFloat)progressPercentage
{
    _progressPercentage = progressPercentage;
    _progressLayer.strokeEnd = progressPercentage;
    [_progressLayer removeAllAnimations];
}

/**
 *  开始录制前的准备动画
 */
- (void)startShootAnimationWithDuration:(NSTimeInterval)duration
{
    __weak typeof(self) weakSelf = self;
    
    [UIView animateWithDuration:duration animations:^{
        
        weakSelf.contentView.transform = CGAffineTransformMakeScale(SHOOTCAMERABUTTONSCALE, SHOOTCAMERABUTTONSCALE);
        weakSelf.touchView.transform = CGAffineTransformMakeScale(SHOOTTOUCHVIEWSCALE, SHOOTTOUCHVIEWSCALE);
        
    } completion:^(BOOL finished) {
        // nothing
    }];
}

/**
 *  结束摄影动画
 */
- (void)stopShootAnimation
{
    self.contentView.transform = CGAffineTransformIdentity;
    self.touchView.transform = CGAffineTransformIdentity;
}


@end
