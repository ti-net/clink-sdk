//
//  TIMSYCacheFileTable.h
//  zhangshaoyu
//
//  Created by 侯力 on 2024/3/19.
//  Copyright © 2017年 侯力. All rights reserved.
//  文件显示列表视图

#import <UIKit/UIKit.h>

@interface TIMSYCacheFileTable : UITableView

/// 数据源
@property (nonatomic, strong) NSMutableArray *cacheDatas;

/// 响应回调
@property (nonatomic, copy) void (^itemClick)(NSIndexPath *indexPath);
/// 长按回调
@property (nonatomic, copy) void (^longPress)(TIMSYCacheFileTable *table, NSIndexPath *indexPath);

- (void)deleItemAtIndex:(NSIndexPath *)indexPath;

@end
