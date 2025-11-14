//
//  UIImage+ImageTool.h
//  SmartHome
//
//  Created by 赵言 on 2019/7/8.
//  Copyright © 2019 赵言. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface UIImage (ImageTool)

/**
 无缓存加载本地图片，适用于大图

 @param imageName 图片名
 @param type 图片类型
 @return image
 */
+ (UIImage *)noCacheImage:(NSString *)imageName imageType:(NSString *)type;

/**
 有缓存的加载本地原始图片，适用于小图。不会经过渲染

 @param imageName 图片名
 @return image
 */
+ (UIImage *)cacheImageOriginalType:(NSString *)imageName;

/**
 图片压缩
 
 @param scaleSize 比例
 @return 缩略图
 */
- (UIImage *)scaleImage:(float)scaleSize;

@end

NS_ASSUME_NONNULL_END
