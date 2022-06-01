//
//  NSObject+TIMImage.h
//  TIMClientLib
//
//  Created by lianpeng on 2021/5/24.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSObject (TIMImage)

+ (UIImage *)TIMCompressImageSize:(UIImage *)image toByte:(NSUInteger)maxLength;

@end

NS_ASSUME_NONNULL_END
