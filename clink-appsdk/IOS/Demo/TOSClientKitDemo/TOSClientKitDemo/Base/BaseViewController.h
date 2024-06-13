//
//  BaseViewController.h
//  SmartHome
//
//  Created by 赵言 on 2019/7/4.
//  Copyright © 2019 赵言. All rights reserved.
//  所有ViewController的基类

#import <UIKit/UIKit.h>

typedef enum{
    Navi_BackColor_Default = 0,
    Navi_BackColor_Clear
}NaviBackGroundColor;  //NavigationBar的颜色

NS_ASSUME_NONNULL_BEGIN

@interface BaseViewController : UIViewController

/// 页面第一次创建显示
@property (nonatomic, assign) BOOL isCreate;

- (void)setupSubviews;

@end

NS_ASSUME_NONNULL_END
