//
//  TIMSYCacheFileCollectionCell.h
//  zhangshaoyu
//
//  Created by 侯力 on 2024/3/19.
//  Copyright © 2019年 侯力. All rights reserved.
//  文件列表单元格

#import <UIKit/UIKit.h>
#import "TIMSYCacheFileModel.h"

NS_ASSUME_NONNULL_BEGIN

static NSInteger const columnNumber = 2;

#define widthScreen ([[UIScreen mainScreen] bounds].size.width)

#define widthCollectionViewCell ((widthScreen - 10.0 * (columnNumber + 1)) / columnNumber)
static CGFloat const timheightCollectionViewCell = 120.0;
static NSString *const timidentifierCollectionViewCell = @"CollectionViewCell";

@interface TIMSYCacheFileCollectionCell : UICollectionViewCell

/// 数据源
@property (nonatomic, strong) TIMSYCacheFileModel *model;
/// 长按回调
@property (nonatomic, copy) void (^longPress)(void);

@end

NS_ASSUME_NONNULL_END
