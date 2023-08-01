//
//  UIView+Create.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//
#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface UIView (Create)

- (UILabel *)labelWithFontSize:(CGFloat)fontSize color:(UIColor *)color;

- (UIView *)cg_view;

- (UIView *)viewWithBgColor:(UIColor *)color;

- (UIImageView *)imageViewWithImage:(UIImage *)image;

- (UIButton *)buttonWithType:(UIButtonType)type;

- (UIButton *)buttonWithType:(UIButtonType)type title:(NSString *)title titleColor:(UIColor *)titleColor BgColor:(UIColor *)bgColor;

- (UIButton *)buttonWithType:(UIButtonType)type title:(NSString *)title titleColor:(UIColor *)titleColor BgColor:(UIColor *)bgColor image:(UIImage *)image;

- (UIButton *)buttonWithType:(UIButtonType)type bgImage:(UIImage *)bgImage;

- (UIButton *)buttonWithType:(UIButtonType)type bgImage:(UIImage *)normalBgImage bgImageSelected:(UIImage *)selectedBgImage;

- (UIScrollView *)scrollView;

@end

NS_ASSUME_NONNULL_END
