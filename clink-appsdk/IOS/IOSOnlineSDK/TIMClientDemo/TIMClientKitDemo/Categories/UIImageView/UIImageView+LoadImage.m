//
//  UIImageView+LoadImage.m
//  SmartHome
//
//  Created by 赵言 on 2019/8/5.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "UIImageView+LoadImage.h"

@implementation UIImageView (LoadImage)

- (void)loadImageWithUrl:(NSString *)imageUrl placeholderPic:(NSString *)placeholderPic {
    UIImage *placeholderImage = placeholderPic && placeholderPic.length > 0 ? [UIImage imageNamed:placeholderPic] : [UIImage imageWithColor:kHexColor(kBackgroundColor)];
    if (imageUrl && imageUrl.length > 0) {
        [self yy_setImageWithURL:[NSURL URLWithString:imageUrl?:@""]
                     placeholder:placeholderImage
                         options:YYWebImageOptionSetImageWithFadeAnimation | YYWebImageOptionAllowBackgroundTask | YYWebImageOptionHandleCookies
                      completion:nil];
    } else {
        self.image = placeholderImage;
    }
}

@end
