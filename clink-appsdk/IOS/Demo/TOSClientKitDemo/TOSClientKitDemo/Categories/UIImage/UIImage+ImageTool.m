//
//  UIImage+ImageTool.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/8.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "UIImage+ImageTool.h"
#import "NSString+UrlChineseCoding.h"

@implementation UIImage (ImageTool)

+ (UIImage *)noCacheImage:(NSString *)imageName imageType:(NSString *)type {
    NSString *filePath = [[NSBundle mainBundle] pathForResource:imageName ofType:type];
    UIImage *image = [UIImage imageWithContentsOfFile:filePath];
    return image;
}

+ (UIImage *)cacheImageOriginalType:(NSString *)imageName {
    UIImage *image = [[UIImage imageNamed:imageName] imageWithRenderingMode:(UIImageRenderingModeAlwaysOriginal)];
    return image;
}

- (UIImage *)scaleImage:(float)scaleSize {
    UIGraphicsBeginImageContext(CGSizeMake(self.size.width * scaleSize, self.size.height * scaleSize));
    [self drawInRect:CGRectMake(0, 0, self.size.width * scaleSize, self.size.height * scaleSize)];
    UIImage *scaledImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return scaledImage;
}

@end
