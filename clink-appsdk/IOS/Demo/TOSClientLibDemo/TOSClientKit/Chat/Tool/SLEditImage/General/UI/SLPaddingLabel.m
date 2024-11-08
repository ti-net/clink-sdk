//
//  SLPaddingLabel.m
//  DarkMode
//
//  Created by wsl on 2019/10/19.
//  Copyright © 2019 wsl. All rights reserved.
//

#import "SLPaddingLabel.h"

@implementation SLPaddingLabel
- (void)drawTextInRect:(CGRect)rect {
  [super drawTextInRect:UIEdgeInsetsInsetRect(rect, _textPadding)];
}
- (void)setTextPadding:(UIEdgeInsets)textPadding {
    _textPadding = textPadding;
    [self setNeedsLayout];
}

- (UIView *)hitTest:(CGPoint)point withEvent:(UIEvent *)event {
    UIView *result = [super hitTest:point withEvent:event];
    
    for (UIButton *button in self.subviews) {
        if ([button isKindOfClass:[UIButton class]]) {
            CGPoint butttonClickPoint = [button convertPoint:point fromView:self];
            if ([button pointInside:butttonClickPoint withEvent:event]) {
                return button;
            }
        }
    }
    return result;
}

@end
