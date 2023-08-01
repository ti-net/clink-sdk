//
//  UIView+Create.m
//  mobileCMS
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "UIView+Creat.h"


@implementation UIView (Create)

- (UILabel *)labelWithFontSize:(CGFloat)fontSize color:(UIColor *)color {
    UILabel *label = [[UILabel alloc] init];
    label.textColor = color;
    label.font = [UIFont fontWithName:@"PingFangSC-Regular" size:fontSize];
    label.numberOfLines = 1;
    [self addSubview:label];
    return label;
}

- (UIView *)viewWithBgColor:(UIColor *)color {
    UIView *view = [self cg_view];
    view.backgroundColor = color;
    return view;
}

- (UIView *)cg_view {
    UIView *view = [[UIView alloc] init];
    [self addSubview:view];
    return view;
}


- (UIImageView *)imageViewWithImage:(UIImage *)image {
    UIImageView *imageView = [[UIImageView alloc] init];
    [self addSubview:imageView];
    if (image) {
        imageView.image = image;
    }
    return imageView;
}

- (UIButton *)buttonWithType:(UIButtonType)type {
    UIButton *button = [UIButton buttonWithType:type];
    button.backgroundColor = [UIColor clearColor];
    [self addSubview:button];
    return button;
}

- (UIButton *)buttonWithType:(UIButtonType)type title:(NSString *)title titleColor:(UIColor *)titleColor BgColor:(UIColor *)bgColor {
    UIButton *button = [UIButton buttonWithType:type];
    [button setTitle:title forState:UIControlStateNormal];
    [button setTitleColor:titleColor forState:UIControlStateNormal];
    button.backgroundColor = bgColor;
    [self addSubview:button];
    return button;
}

- (UIButton *)buttonWithType:(UIButtonType)type title:(NSString *)title titleColor:(UIColor *)titleColor BgColor:(UIColor *)bgColor image:(UIImage *)image {
    UIButton *button = [self buttonWithType:type title:title titleColor:titleColor BgColor:bgColor];
    [button setImage:image forState:UIControlStateNormal];
    return button;
}

- (UIButton *)buttonWithType:(UIButtonType)type bgImage:(UIImage *)bgImage {
    UIButton *button = [UIButton buttonWithType:type];
    [button setBackgroundImage:bgImage forState:UIControlStateNormal];
    [self addSubview:button];
    return button;
}

- (UIButton *)buttonWithType:(UIButtonType)type bgImage:(UIImage *)normalBgImage bgImageSelected:(UIImage *)selectedBgImage {
    UIButton *button = [self buttonWithType:type bgImage:normalBgImage];
    [button setBackgroundImage:selectedBgImage forState:UIControlStateSelected];
    return button;
}

- (UIScrollView *)scrollView {
    UIScrollView *scrollView = [[UIScrollView alloc] init];
    scrollView.showsVerticalScrollIndicator = NO;
    scrollView.showsHorizontalScrollIndicator = NO;
    scrollView.userInteractionEnabled = YES;
    scrollView.contentInset = UIEdgeInsetsZero;
    scrollView.scrollEnabled = YES;
    scrollView.bounces = YES;
    [self addSubview:scrollView];
    return scrollView;
}

@end
