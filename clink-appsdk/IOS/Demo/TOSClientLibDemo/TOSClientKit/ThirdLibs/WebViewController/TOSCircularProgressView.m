//
//  TOSCircularProgressView.m
//  TOSClientKit
//
//  Created by 言 on 2024/9/4.
//  Copyright © 2024 YanBo. All rights reserved.
//

#import "TOSCircularProgressView.h"
#import "TIMConstants.h"

@interface TOSCircularProgressView ()

// 圆形进度条的层
@property (nonatomic, strong) CAShapeLayer *progressLayer;
// 用于显示进度百分比的标签
@property (nonatomic, strong) UILabel *progressLabel;

@end

@implementation TOSCircularProgressView

- (void)setupSubviews {
    [super setupSubviews];
    
    // 初始化圆形进度条
    [self setupCircularProgress];
}

- (void)defineLayout {
    [super defineLayout];
    
}

// 更新进度条
- (void)updateProgress:(CGFloat)progress {
    self.progressLayer.strokeEnd = progress;
    self.progressLabel.text = [NSString stringWithFormat:@"%.0f%%", progress * 100];
}

- (void)setupCircularProgress {
    // 设置圆形路径的中心点和半径
    CGPoint center = CGPointMake(self.bounds.size.width / 2, self.bounds.size.height / 2);
    CGFloat radius = 30.0;
    
    // 创建圆形路径
    UIBezierPath *circlePath = [UIBezierPath bezierPathWithArcCenter:center
                                                              radius:radius
                                                          startAngle:-M_PI_2
                                                            endAngle:(M_PI * 2) - M_PI_2
                                                           clockwise:YES];
    
    // 背景圆环
    CAShapeLayer *backgroundLayer = [CAShapeLayer layer];
    backgroundLayer.path = circlePath.CGPath;
    backgroundLayer.strokeColor = TOSHexAColor(0xFFFFFF, .4f).CGColor;
    backgroundLayer.fillColor = [UIColor clearColor].CGColor;
    backgroundLayer.lineWidth = 3.0;
    [self.layer addSublayer:backgroundLayer];
    
    // 进度条
    self.progressLayer = [CAShapeLayer layer];
    self.progressLayer.path = circlePath.CGPath;
    self.progressLayer.strokeColor = [UIColor whiteColor].CGColor;
    self.progressLayer.fillColor = [UIColor clearColor].CGColor;
    self.progressLayer.lineWidth = 3.0;
    self.progressLayer.strokeEnd = 0.0; // 初始进度为 0
    [self.layer addSublayer:self.progressLayer];
    
    // 进度百分比标签
    self.progressLabel = [[UILabel alloc] initWithFrame:CGRectMake(center.x - 30, center.y - 15, 60, 30)];
    self.progressLabel.textAlignment = NSTextAlignmentCenter;
    self.progressLabel.textColor = [UIColor whiteColor];
    self.progressLabel.text = @"0%";
    self.progressLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:16.f];
    [self addSubview:self.progressLabel];
}

@end
