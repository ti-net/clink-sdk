//
//  TIMSYCacheFileCell.h
//  zhangshaoyu
//
//  Created by 侯力 on 2024/3/19.
//  Copyright © 2017年 侯力. All rights reserved.
//  文件显示单元格

#import <UIKit/UIKit.h>
#import "TIMSYCacheFileModel.h"

static NSString *const timreuseSYCacheDirectoryCell = @"SYCacheDirectoryCell";
static CGFloat const timheightSYCacheDirectoryCell = 60.0;

@interface TIMSYCacheFileCell : UITableViewCell

/// 数据源
@property (nonatomic, strong) TIMSYCacheFileModel *model;
/// 长按回调
@property (nonatomic, copy) void (^longPress)(void);

@end
