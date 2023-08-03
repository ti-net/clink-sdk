//
//  ICPhotoBrowserController.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/4/12.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ICPhotoBrowserController : UIViewController

@property (nonatomic, strong) UIImage *image;

@property (nonatomic, strong) UIImageView *imageView;

- (instancetype)initWithImage:(UIImage *)image msgId:(NSString *)msgId originImageUrl:(NSString *)originImageUrl;

@end
