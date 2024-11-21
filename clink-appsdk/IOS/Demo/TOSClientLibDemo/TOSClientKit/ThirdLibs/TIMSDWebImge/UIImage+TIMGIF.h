//
//  UIImage+TIMGIF.h
//  LBGIFImage
//
//  Created by 侯力 on 2024/03/19.
//  Copyright (c) 2012 侯力. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UIImage (TIMGIF)

+ (UIImage *)sd_animatedGIFNamed:(NSString *)name;

+ (UIImage *)sd_animatedGIFWithData:(NSData *)data;

- (UIImage *)sd_animatedImageByScalingAndCroppingToSize:(CGSize)size;

@end
