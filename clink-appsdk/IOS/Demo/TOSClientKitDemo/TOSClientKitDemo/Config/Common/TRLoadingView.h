//
//  TRLoadingView.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface TRLoadingView : BaseView

/**
 Loading的最小显示时间，默认为0
 */
@property (nonatomic, assign) CGFloat minShowTime;

/**
 Loading的最大显示时间，默认为
 */
@property (nonatomic, assign) CGFloat maxShowTime;


+ (instancetype)showLoadingAddTo:(UIView *)view animated:(BOOL)animated;

+ (BOOL)hideLoadingForView:(UIView *)view animated:(BOOL)animated;

@end

NS_ASSUME_NONNULL_END
