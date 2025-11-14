//
//  CABasicAnimation+TRQuickCreate.m
//  mobileCMS
//
//  Created by 赵言 on 2020/4/8.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "CABasicAnimation+TRQuickCreate.h"

@implementation CABasicAnimation (TRQuickCreate)

+ (CABasicAnimation *)tr_quickCreateAnimationWithKeyPath:(NSString *)keyPath
                                                duration:(CGFloat)duration
                                               fromValue:(id)fromValue
                                                 toValue:(id)toValue {
    CABasicAnimation *pulse = [CABasicAnimation animationWithKeyPath:keyPath];
    pulse.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
    pulse.duration = duration;
    pulse.repeatCount = 1;
    pulse.autoreverses = NO;
    pulse.fromValue = fromValue;
    pulse.toValue = toValue;
    return pulse;
}

@end
