//
//  UIImage+TIMWebP.h
//  SDWebImage
//
//  Created by 侯力 on 2024/03/19.
//  Copyright (c) 2013 侯力. All rights reserved.
//

#ifdef SD_WEBP

#import <UIKit/UIKit.h>

// Fix for issue #416 Undefined symbols for architecture armv7 since WebP introduction when deploying to device
void TIMWebPInitPremultiplyNEON(void);

void TIMWebPInitUpsamplersNEON(void);

void TIMVP8DspInitNEON(void);

@interface UIImage (TIMWebP)

+ (UIImage *)sd_imageWithWebPData:(NSData *)data;

@end

#endif
