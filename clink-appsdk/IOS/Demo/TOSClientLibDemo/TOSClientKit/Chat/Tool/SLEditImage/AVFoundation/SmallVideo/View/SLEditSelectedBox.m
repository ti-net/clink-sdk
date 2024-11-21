//
//  SLEditSelectedBox.m
//  DarkMode
//
//  Created by wsl on 2019/10/23.
//  Copyright © 2019 wsl. All rights reserved.
//

#import "SLEditSelectedBox.h"
#import "TIMConstants.h"

@interface SLEditSelectedBox ()
//@property (nonatomic, strong) CALayer *topLeft;
//@property (nonatomic, strong) CALayer *topRight;
//@property (nonatomic, strong) CALayer *bottomLeft;
//@property (nonatomic, strong) CALayer *bottomRight;

@end

@implementation SLEditSelectedBox

#pragma mark - Override
- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        [self initView];
    }
    return self;
}
- (instancetype)init{
    if (self = [super init]) {
        [self initView];
    }
    return self;
}
#pragma mark - help Methods
- (void)initView {
    // 初始化遮罩
    self.userInteractionEnabled = YES;
    self.layer.borderColor = TOSHexColor(0x979797).CGColor;
    self.layer.borderWidth = 1.f;
    self.backgroundColor = [UIColor clearColor];
    self.clipsToBounds = NO;
}

- (void)layoutSubviews {
    [super layoutSubviews];
}

- (void)didMoveToSuperview{
    if (self.superview) {
        [self.layer.sublayers makeObjectsPerformSelector:@selector(removeFromSuperlayer)];
        // 缩放系数
        CGAffineTransform transform = self.superview.transform;
        CGFloat scale = sqrt(transform.a*transform.a + transform.c*transform.c);
        scale = scale <= 1 ? 1 : scale ;
        
        self.layer.borderWidth = 1/scale;
    }
}

@end
