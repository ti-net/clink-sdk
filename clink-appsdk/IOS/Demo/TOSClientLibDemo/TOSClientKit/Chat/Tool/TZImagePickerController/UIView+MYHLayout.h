//
//  UIView+MYHLayout.h
//
//  Created by 侯力 on 2024/03/15.
//  Copyright © 2015年 侯力. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef enum : NSUInteger {
    TZOscillatoryAnimationToBigger,
    TZOscillatoryAnimationToSmaller,
} TZOscillatoryAnimationType;

@interface UIView (MYHLayout)

@property (nonatomic) CGFloat tos_tz_left;        ///< Shortcut for frame.origin.x.
@property (nonatomic) CGFloat tos_tz_top;         ///< Shortcut for frame.origin.y
@property (nonatomic) CGFloat tos_tz_right;       ///< Shortcut for frame.origin.x + frame.size.width
@property (nonatomic) CGFloat tos_tz_bottom;      ///< Shortcut for frame.origin.y + frame.size.height
@property (nonatomic) CGFloat tos_tz_width;       ///< Shortcut for frame.size.width.
@property (nonatomic) CGFloat tos_tz_height;      ///< Shortcut for frame.size.height.
@property (nonatomic) CGFloat tos_tz_centerX;     ///< Shortcut for center.x
@property (nonatomic) CGFloat tos_tz_centerY;     ///< Shortcut for center.y
@property (nonatomic) CGPoint tos_tz_origin;      ///< Shortcut for frame.origin.
@property (nonatomic) CGSize  tos_tz_size;        ///< Shortcut for frame.size.

+ (void)tos_showOscillatoryAnimationWithLayer:(CALayer *)layer type:(TZOscillatoryAnimationType)type;

@end
