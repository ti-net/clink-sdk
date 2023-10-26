//
//  BaseCollectionViewCell.h
//  SmartHome
//
//  Created by 赵言 on 2019/7/9.
//  Copyright © 2019 赵言. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface BaseCollectionViewCell : UICollectionViewCell

/// 普通的cell创建
/// @param collectionView collectionView
/// @param reuseIdentifie cell的标识符
/// @param indexPath indexPath
+ (instancetype)cellWithCollectionView:(UICollectionView *)collectionView reuseIdentifie:(NSString *)reuseIdentifie indexPath:(NSIndexPath *)indexPath;

/// xib类型的cell创建
/// @param collectionView collectionView
/// @param reuseIdentifie cell的标识符
/// @param indexPath indexPath
+ (instancetype)cellXibWithCollectionView:(UICollectionView *)collectionView reuseIdentifie:(NSString *)reuseIdentifie indexPath:(NSIndexPath *)indexPath;

- (void)setupSubviews;
- (void)defineLayout;

/// 设置Model数据
/// @param model 数据
- (void)setWithModel:(id)model;

@end

NS_ASSUME_NONNULL_END
