//
//  UIImageView+LoadImage.h
//  SmartHome
//
//  Created by 赵言 on 2019/8/5.
//  Copyright © 2019 赵言. All rights reserved.
//  加载url图片

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface UIImageView (LoadImage)

/**
 YYWebImage加载图片

 @param imageUrl 图片URL
 @param placeholderPic 占位图
 */
- (void)loadImageWithUrl:(NSString *)imageUrl placeholderPic:(NSString *)placeholderPic;

@end

NS_ASSUME_NONNULL_END
