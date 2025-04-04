//
//  TIMQKeyboardBaseManager.h
//  QKeyBoardDemo
//
//  Created by 侯力 on 2024/3/19.
//  Copyright (c) 2021年 侯力. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMQInputBarViewConfiguration.h"

@interface TIMQKeyboardBaseManager : NSObject

- (instancetype)initWithViewController:(UIViewController *)viewController;

@property (nonatomic, weak) UIViewController *viewController;//当前vc

//  输入栏TextView的高度发送变化的动画时长（秒）
@property (nonatomic, assign) NSTimeInterval inputBarHeightChangeAnimationDuration; // default is 0.2

@end
