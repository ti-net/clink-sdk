//
//  SLEditImageController.h
//  DarkMode
//
//  Created by wsl on 2019/10/31.
//  Copyright © 2019 wsl. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

typedef void(^ReturnImage)(UIImage *image);

@interface SLEditImageController : UIViewController

/**
 *  自定义APP相册名字，如果为空则默认为APP的名字
 */
@property (strong, nonatomic) NSString *assetCollectionName;
@property (nonatomic, strong) UIImage *image; //当前拍摄的照片
- (void)setReturnImage:(ReturnImage)returnImage;

@end

NS_ASSUME_NONNULL_END
