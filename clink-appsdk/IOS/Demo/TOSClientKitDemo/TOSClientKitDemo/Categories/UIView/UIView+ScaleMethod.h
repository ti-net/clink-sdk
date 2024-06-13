//
//  UIView+ScaleMethod.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//
#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface UIView (ScaleMethod)

@property (nonatomic, assign) CGFloat scale;
@property (nonatomic, assign) CGFloat heightScale;

@end


@interface UIViewController (ScaleMethod)

@property (nonatomic, assign) CGFloat scale;
@property (nonatomic, assign) CGFloat heightScale;

@end

NS_ASSUME_NONNULL_END
