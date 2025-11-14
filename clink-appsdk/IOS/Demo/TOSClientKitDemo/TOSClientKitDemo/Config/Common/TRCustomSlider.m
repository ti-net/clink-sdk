//
//  TRCustomSlider.m
//  mobileCMS
//
//  Created by 赵言 on 2020/4/9.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "TRCustomSlider.h"

@implementation TRCustomSlider

- (CGRect)trackRectForBounds:(CGRect)bounds {
    CGFloat H = 5.f;
    CGFloat Y = self.height/2 - H/2;
    CGFloat X = bounds.origin.x;
    CGFloat W = bounds.size.width;
    return CGRectMake(X, Y, W, H);
}

@end
