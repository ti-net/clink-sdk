//
//  ICHeadImageView.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/8.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICHeadImageView.h"
#import "TIMConstants.h"
#import "UIView+SDExtension.h"
#import "kitUtils.h"
#import <TOSClientKit/TOSKitCustomInfo.h>

//static const CGFloat bordering = 4; // 边框

@interface ICHeadImageView ()

@property (nonatomic, assign) CGFloat bordering;

@end

@implementation ICHeadImageView

- (instancetype)init
{
    if (self = [super init]) {
        [self imageView];
        self.layer.masksToBounds  = YES;
        self.backgroundColor      = [UIColor whiteColor];
//        self.bordering            = 4;
        self.bordering            = 0;
    }
    return self;
}

- (void)setFrame:(CGRect)frame
{
    [super setFrame:frame];
}


- (void)layoutSubviews
{
    self.imageView.width = self.frame.size.width - _bordering;
    self.imageView.height = self.frame.size.height - _bordering;
    
    self.layer.cornerRadius = [TOSKitCustomInfo shareCustomInfo].portrait_cornerRadius;
    self.imageView.layer.cornerRadius = [TOSKitCustomInfo shareCustomInfo].portrait_cornerRadius;
    self.imageView.centerX = self.width*0.5;
    self.imageView.centerY = self.height*0.5;
}

- (void)setColor:(UIColor *)color bording:(CGFloat)bord
{
//    self.backgroundColor = color;
    self.bordering       = bord;
}

- (UIImageView *)imageView
{
    if (nil == _imageView) {
        UIImageView *imageV = [[UIImageView alloc] init];
        imageV.layer.masksToBounds = YES;
        [self addSubview:imageV];
        _imageView = imageV;
    }
    return _imageView;
}


@end
