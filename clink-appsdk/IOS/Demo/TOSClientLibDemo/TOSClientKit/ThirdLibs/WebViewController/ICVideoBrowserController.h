//
//  ICVideoBrowserController.h
//  TIMClientKit
//
//  Created by YanBo on 2020/7/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface ICVideoBrowserController : UIViewController

@property (nonatomic, strong) UIImage *image;
@property (nonatomic, strong) UIImageView *imageView;

- (instancetype)initWithPath:(CGRect)fromRect messageId:(NSString*)msgId videoPath:(NSString *)videoPath;
@end

NS_ASSUME_NONNULL_END
