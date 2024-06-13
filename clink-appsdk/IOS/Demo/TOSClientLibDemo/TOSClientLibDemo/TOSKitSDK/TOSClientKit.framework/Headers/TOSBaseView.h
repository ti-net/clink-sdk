//
//  BaseView.h
//  SmartHome
//
//  Created by 赵言 on 2019/7/4.
//  Copyright © 2019 赵言. All rights reserved.
//  所有view的基类

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN
@class TOSBaseViewController;
@interface TOSBaseView : UIView

@property (nonatomic, weak) TOSBaseViewController *currentVC;

/// 添加子视图
- (void)setupSubviews;

/// 重写子视图布局
- (void)defineLayout;

@end

NS_ASSUME_NONNULL_END
