//
//  UIImage+Extension.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/9/27.
//  Copyright © 2016年 gxz. All rights reserved.
//

#import <AVFoundation/AVFoundation.h>
#import "UIImage+Extension.h"
#import "TIMConstants.h"
#import "kitUtils.h"

@implementation UIImage (Extension)


+ (UIImage *)gxz_imageWithColor:(UIColor *)color
{
    CGRect rect = CGRectMake(0.0f, 0.0f, 1.0f, 1.0f);
    UIGraphicsBeginImageContext(rect.size);
    CGContextRef context = UIGraphicsGetCurrentContext();
    CGContextSetFillColorWithColor(context, [color CGColor]);
    CGContextFillRect(context, rect);
    UIImage *theImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return theImage;
}

// 视频第一帧
+ (UIImage *)videoFramerateWithPath:(NSString *)videoPath
{
    if ([kitUtils isBlankString:videoPath]) {
        return [UIImage imageNamed:@""];
    }
    NSString *mp4Path = [[videoPath stringByDeletingPathExtension] stringByAppendingPathExtension:@"mp4"];
    AVURLAsset *asset = [[AVURLAsset alloc] initWithURL:[NSURL fileURLWithPath:mp4Path] options:nil];
    NSParameterAssert(asset);
    AVAssetImageGenerator *assetImageGenerator = [[AVAssetImageGenerator alloc] initWithAsset:asset];
    assetImageGenerator.appliesPreferredTrackTransform = YES;
    assetImageGenerator.apertureMode = AVAssetImageGeneratorApertureModeEncodedPixels;
    CGImageRef thumbnailImageRef = NULL;
    CFTimeInterval thumbnailImageTime = 0;
    NSError *thumbnailImageGenerationError = nil;
    thumbnailImageRef = [assetImageGenerator copyCGImageAtTime:CMTimeMake(thumbnailImageTime, 60) actualTime:NULL error:&thumbnailImageGenerationError];
    if (!thumbnailImageRef) {
        return nil;
    }
    UIImage *thumbnailImage = thumbnailImageRef ? [[UIImage alloc] initWithCGImage:thumbnailImageRef] : nil;
    CGImageRelease(thumbnailImageRef);
    return thumbnailImage;
}

// 压缩图片
+ (UIImage *)simpleImage:(UIImage *)originImg
{
    CGSize imageSize = [self handleImage:originImg.size];
    if (imageSize.width <= 0 || imageSize.height <= 0 || isnan(imageSize.width) || isnan(imageSize.height)) return nil;
    UIGraphicsBeginImageContextWithOptions(imageSize, YES, 0.0);
    CGContextRef contextRef = UIGraphicsGetCurrentContext();
    UIBezierPath *bezierPath = [UIBezierPath bezierPathWithRect:CGRectMake(0, 0, imageSize.width, imageSize.height)];
    CGContextAddPath(contextRef, bezierPath.CGPath);
    CGContextClip(contextRef);
    [originImg drawInRect:CGRectMake(0, 0, imageSize.width, imageSize.height)];
    UIImage *clipedImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return clipedImage;
}

+ (CGSize)handleImage:(CGSize)retSize {
    CGFloat width = 0;
    CGFloat height = 0;
    if (retSize.width > retSize.height) {
        width = App_Frame_Width;
        height = retSize.height / retSize.width * width;
    } else {
        height = APP_Frame_Height;
        width = retSize.width / retSize.height * height;
    }
    return CGSizeMake(width, height);
}


+ (UIImage *)makeArrowImageWithSize:(CGSize)imageSize
                              image:(UIImage *)image
                           isSender:(BOOL)isSender
{
    if (imageSize.width <= 0 || imageSize.height <= 0 || isnan(imageSize.width) || isnan(imageSize.height)) return nil;
    UIGraphicsBeginImageContextWithOptions(imageSize, NO, 0.0);
    CGContextRef contextRef = UIGraphicsGetCurrentContext();
    UIBezierPath *path = [self getBezierPath:isSender imageSize:imageSize];
    CGContextAddPath(contextRef, path.CGPath);
    CGContextEOClip(contextRef);
    [image drawInRect:CGRectMake(0, 0, imageSize.width, imageSize.height)];
    UIImage *arrowImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return arrowImage;
}

+ (UIBezierPath *)getBezierPath:(BOOL)isSender
                      imageSize:(CGSize)imageSize
{
    CGFloat arrowWidth = 6;
    CGFloat marginTop = 13;
    CGFloat arrowHeight = 10;
    CGFloat imageW = imageSize.width;
    UIBezierPath *path;
    if (isSender) {
        path = [UIBezierPath bezierPathWithRoundedRect:CGRectMake(0, 0, imageSize.width - arrowWidth, imageSize.height) cornerRadius:6];
        [path moveToPoint:CGPointMake(imageW - arrowWidth, 0)];
        [path addLineToPoint:CGPointMake(imageW - arrowWidth, marginTop)];
        [path addLineToPoint:CGPointMake(imageW, marginTop + 0.5 * arrowHeight)];
        [path addLineToPoint:CGPointMake(imageW - arrowWidth, marginTop + arrowHeight)];
        [path closePath];
        
    } else {
        path = [UIBezierPath bezierPathWithRoundedRect:CGRectMake(arrowWidth, 0, imageSize.width - arrowWidth, imageSize.height) cornerRadius:6];
        [path moveToPoint:CGPointMake(arrowWidth, 0)];
        [path addLineToPoint:CGPointMake(arrowWidth, marginTop)];
        [path addLineToPoint:CGPointMake(0, marginTop + 0.5 * arrowHeight)];
        [path addLineToPoint:CGPointMake(arrowWidth, marginTop + arrowHeight)];
        [path closePath];
    }
    return path;
}

+ (UIImage *)addImage:(UIImage *)firstImg
              toImage:(UIImage *)secondImg
{
    // iOS 17.0以上针对某些接口UIGraphicsBeginImageContextWithOptions的废弃兼容
    CGFloat scale = 1.0f;  // 默认值 1.0f
    UIImage *resultImg = nil;
    if (secondImg.size.width <= 0 || secondImg.size.height <= 0 || isnan(secondImg.size.width) || isnan(secondImg.size.height)) return nil;
    if (@available(iOS 17.0, *)) {
        // 实际应该是在10.0作为分界点 但是为了谨慎起见只针对17做处理
        UIGraphicsImageRendererFormat *format = [[UIGraphicsImageRendererFormat alloc] init];
        format.scale = scale;
        format.opaque = NO;
        UIGraphicsImageRenderer *renderer = [[UIGraphicsImageRenderer alloc] initWithSize:secondImg.size
                                                                                   format:format];
        resultImg = [renderer imageWithActions:^(UIGraphicsImageRendererContext * _Nonnull rendererContext) {
            [secondImg drawInRect:CGRectMake(0, 0, secondImg.size.width, secondImg.size.height)];
            [firstImg drawInRect:CGRectMake((secondImg.size.width-firstImg.size.width)*0.5,(secondImg.size.height-firstImg.size.height)*0.5, firstImg.size.width, firstImg.size.height)];
        }];
    } else {
        UIGraphicsBeginImageContext(secondImg.size);
        [secondImg drawInRect:CGRectMake(0, 0, secondImg.size.width, secondImg.size.height)];
        [firstImg drawInRect:CGRectMake((secondImg.size.width-firstImg.size.width)*0.5,(secondImg.size.height-firstImg.size.height)*0.5, firstImg.size.width, firstImg.size.height)];
        resultImg = UIGraphicsGetImageFromCurrentImageContext();
        UIGraphicsEndImageContext();
    }
    return resultImg;
}

+ (UIImage *)addImage2:(UIImage *)firstImg
               toImage:(UIImage *)secondImg
{
    // iOS 17.0以上针对某些接口UIGraphicsBeginImageContextWithOptions的废弃兼容
    CGFloat scale = 1.0f;  // 默认值 1.0f
    UIImage *image = nil;
    if (secondImg.size.width <= 0 || secondImg.size.height <= 0 || isnan(secondImg.size.width) || isnan(secondImg.size.height)) return nil;
    if (@available(iOS 17.0, *)) {
        // 实际应该是在10.0作为分界点 但是为了谨慎起见只针对17做处理
        UIGraphicsImageRendererFormat *format = [[UIGraphicsImageRendererFormat alloc] init];
        format.scale = scale;
        format.opaque = NO;
        UIGraphicsImageRenderer *renderer = [[UIGraphicsImageRenderer alloc] initWithSize:secondImg.size
                                                                                   format:format];
        image = [renderer imageWithActions:^(UIGraphicsImageRendererContext * _Nonnull rendererContext) {
            [secondImg drawInRect:CGRectMake(0, 0,secondImg.size.width,secondImg.size.height)];
            CGFloat firstImgW = secondImg.size.width/4;
            CGFloat firstImgH = secondImg.size.width/4;
            [firstImg drawInRect:CGRectMake((secondImg.size.width-firstImgW)*0.5,(secondImg.size.height-firstImgH)*0.5, firstImgW,firstImgH)];
        }];
    } else {
        UIGraphicsBeginImageContextWithOptions(secondImg.size, NO, scale);
        [secondImg drawInRect:CGRectMake(0, 0,secondImg.size.width,secondImg.size.height)];
        CGFloat firstImgW = secondImg.size.width/4;
        CGFloat firstImgH = secondImg.size.width/4;
        [firstImg drawInRect:CGRectMake((secondImg.size.width-firstImgW)*0.5,(secondImg.size.height-firstImgH)*0.5, firstImgW,firstImgH)];
        image = UIGraphicsGetImageFromCurrentImageContext();
        UIGraphicsEndImageContext();
    }

    return image;
}

+ (NSString *)typeForImageData:(NSData *)data{
    uint8_t c;
    [data getBytes:&c length:1];
    switch (c)
    {
        case 0xFF:
            return @"jpeg";
        case 0x89:
            return @"png";
        case 0x47:
            return @"gif";
        case 0x49:
        case 0x4D:
            return @"tiff";
    }
    return @"";
}

@end
