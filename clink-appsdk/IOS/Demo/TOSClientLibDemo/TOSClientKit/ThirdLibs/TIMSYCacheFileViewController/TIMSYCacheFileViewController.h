//
//  TIMSYCacheFileViewController.h
//  zhangshaoyu
//
//  Created by 侯力 on 2024/3/19.
//  Copyright © 2017年 侯力. All rights reserved.
//  https://github.com/potato512/SYCacheFileViewController

#import <UIKit/UIKit.h>
#import "TOSBaseViewController.h"

@interface TIMSYCacheFileViewController : TOSBaseViewController

/// 导航栏标题（默认缓存目录）
@property (nonatomic, strong) NSString *cacheTitle;
/// 数据源（默认home目录）
@property (nonatomic, strong) NSMutableArray *cacheArray;

/// 显示样式（默认0列表，1九宫格）
@property (nonatomic, assign) NSInteger showType;

@end
