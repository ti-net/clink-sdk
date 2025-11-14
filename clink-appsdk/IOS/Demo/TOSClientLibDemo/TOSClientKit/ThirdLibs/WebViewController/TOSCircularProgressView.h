//
//  TOSCircularProgressView.h
//  TOSClientKit
//
//  Created by 言 on 2024/9/4.
//  Copyright © 2024 YanBo. All rights reserved.
//

#import <TOSClientKit/TOSClientKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface TOSCircularProgressView : TOSBaseView

// 更新进度条
- (void)updateProgress:(CGFloat)progress;

@end

NS_ASSUME_NONNULL_END
