//
//  CABasicAnimation+TRQuickCreate.h
//  mobileCMS
//
//  Created by 赵言 on 2020/4/8.
//  Copyright © 2020 赵言. All rights reserved.
//

#import <QuartzCore/QuartzCore.h>

NS_ASSUME_NONNULL_BEGIN

@interface CABasicAnimation (TRQuickCreate)

+ (CABasicAnimation *)tr_quickCreateAnimationWithKeyPath:(NSString *)keyPath
                                                duration:(CGFloat)duration
                                               fromValue:(id)fromValue
                                                 toValue:(id)toValue;

@end

NS_ASSUME_NONNULL_END
