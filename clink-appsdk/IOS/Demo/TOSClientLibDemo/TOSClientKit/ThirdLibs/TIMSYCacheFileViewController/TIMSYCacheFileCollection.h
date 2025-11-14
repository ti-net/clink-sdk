//
//  TIMSYCacheFileCollection.h
//  zhangshaoyu
//
//  Created by 侯力 on 2024/3/19.
//  Copyright © 2019年 侯力. All rights reserved.
//  文件列表九宫格显示

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMSYCacheFileCollection : UICollectionView

+ (UICollectionViewLayout *)collectionlayout;

/// 数据源
@property (nonatomic, strong) NSMutableArray *cacheDatas;

/// 响应回调
@property (nonatomic, copy) void (^itemClick)(NSIndexPath *indexPath);
/// 长按回调
@property (nonatomic, copy) void (^longPress)(TIMSYCacheFileCollection *collection, NSIndexPath *indexPath);

- (void)deleItemAtIndex:(NSIndexPath *)indexPath;

@end

NS_ASSUME_NONNULL_END
