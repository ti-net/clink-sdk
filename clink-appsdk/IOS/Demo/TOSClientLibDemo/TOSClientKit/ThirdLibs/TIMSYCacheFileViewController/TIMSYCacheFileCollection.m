//
//  TIMSYCacheFileCollection.m
//  zhangshaoyu
//
//  Created by 侯力 on 2024/3/19.
//  Copyright © 2019年 侯力. All rights reserved.
//

#import "TIMSYCacheFileCollection.h"
#import "TIMSYCacheFileCollectionCell.h"
#import "TIMSYCacheFileModel.h"
#import "TIMSYCacheFileManager.h"

@interface TIMSYCacheFileCollection () <UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout>

@property (nonatomic, strong) NSIndexPath *previousIndex;

@end

@implementation TIMSYCacheFileCollection

- (instancetype)initWithFrame:(CGRect)frame collectionViewLayout:(UICollectionViewLayout *)layout
{
    self = [super initWithFrame:frame collectionViewLayout:layout];
    if (self) {
        self.backgroundColor = [UIColor clearColor];
        
        self.delegate = self;
        self.dataSource = self;
        self.autoresizingMask = UIViewAutoresizingFlexibleHeight;
        [self registerClass:[TIMSYCacheFileCollectionCell class] forCellWithReuseIdentifier:timidentifierCollectionViewCell];
        self.allowsSelection = YES;
        self.allowsMultipleSelection = NO;
        self.alwaysBounceVertical = YES;
    }
    return self;
}

+ (UICollectionViewLayout *)collectionlayout
{
    // 确定是水平滚动，还是垂直滚动
    UICollectionViewFlowLayout *flowLayout = [[UICollectionViewFlowLayout alloc] init];
    [flowLayout setScrollDirection:UICollectionViewScrollDirectionVertical];
    return flowLayout;
}

#pragma mark - UICollectionViewDataSource

// 定义展示的UICollectionViewCell的个数
- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section
{
    return self.cacheDatas.count;
}

// 每个UICollectionView展示的内容
- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath
{
    TIMSYCacheFileCollectionCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:timidentifierCollectionViewCell forIndexPath:indexPath];
    
    // 数据
    TIMSYCacheFileModel *model = self.cacheDatas[indexPath.row];
    cell.model = model;
    // 长按
    TIMSYCacheFileCollection __weak *weakSelf = self;
    cell.longPress = ^{
        if (weakSelf.longPress) {
            weakSelf.longPress(weakSelf, indexPath);
        }
    };
    
    return cell;
}

#pragma mark - UICollectionViewDelegateFlowLayout

// 定义每个UICollectionView的大小
- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath
{
    return CGSizeMake(widthCollectionViewCell, timheightCollectionViewCell);
}

// 定义每个UICollectionView的 margin
- (UIEdgeInsets)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout insetForSectionAtIndex:(NSInteger)section
{
    return UIEdgeInsetsMake(10.0, 10.0, 10.0, 10.0);
}

// 最小行间距
- (CGFloat)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout minimumLineSpacingForSectionAtIndex:(NSInteger)section
{
    return 10.0;
}

// 最小列间距
- (CGFloat)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout minimumInteritemSpacingForSectionAtIndex:(NSInteger)section
{
    return 0.0;
}

// 设定页眉的尺寸
- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout referenceSizeForHeaderInSection:(NSInteger)section
{
    return CGSizeZero;
}

// 设定页脚的尺寸
- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout referenceSizeForFooterInSection:(NSInteger)section
{
    return CGSizeZero;
}

#pragma mark - UICollectionViewDelegate

// UICollectionView被选中时调用的方法
- (void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath
{
    [collectionView deselectItemAtIndexPath:indexPath animated:YES];
    
    TIMSYCacheFileModel *model = self.cacheDatas[indexPath.row];
    
    // 音频播放
    TIMSYCacheFileType type = [[TIMSYCacheFileManager shareManager] fileTypeReadWithFilePath:model.filePath];
    if (TIMSYCacheFileTypeAudio == type) {
        model.fileProgressShow = YES;
        NSString *currentPath = model.filePath;
        
        if (self.previousIndex) {
            TIMSYCacheFileModel *previousModel = self.cacheDatas[self.previousIndex.row];
            NSString *previousPath = previousModel.filePath;
            if (![currentPath isEqualToString:previousPath]) {
                previousModel.fileProgress = 0.0;
                previousModel.fileProgressShow = NO;
                [collectionView reloadItemsAtIndexPaths:@[self.previousIndex]];
            }
        }
        
        self.previousIndex = indexPath;
    }
    
    // 回调响应
    if (self.itemClick) {
        self.itemClick(indexPath);
    }
}

// 返回这个UICollectionView是否可以被选择
- (BOOL)collectionView:(UICollectionView *)collectionView shouldSelectItemAtIndexPath:(NSIndexPath *)indexPath
{
    return YES;
}

#pragma mark - 删除操作

- (void)deleItemAtIndex:(NSIndexPath *)indexPath
{
    if (indexPath.row > self.cacheDatas.count - 1) {
        return;
    }
    TIMSYCacheFileModel *model = self.cacheDatas[indexPath.row];
    // 系统数据不可删除
    if ([[TIMSYCacheFileManager shareManager] isFileSystemWithFilePath:model.filePath]) {
        [[[UIAlertView alloc] initWithTitle:nil message:@"系统文件不能删除" delegate:nil cancelButtonTitle:@"确定" otherButtonTitles:nil, nil] show];
        return;
    }
    // 当前删除的是音频时，先停止播放
    if (model.fileType == TIMSYCacheFileTypeAudio) {
        [[NSNotificationCenter defaultCenter] postNotificationName:TIMSYCacheFileAudioDeleteNotificationName object:nil];
    }
    if (self.previousIndex) {
        self.previousIndex = nil;
    }
    // 删除数据：删除数组、删除本地文件/文件夹、刷新页面、发通知刷新文件大小统计
    // 删除数组
    [self.cacheDatas removeObjectAtIndex:indexPath.row];
    // 删除本地文件/文件夹
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        BOOL isDelete = [TIMSYCacheFileManager deleteFileWithDirectory:model.filePath];
        NSLog(@"删除：%@", (isDelete ? @"成功" : @"失败"));
    });
    // 刷新页面
//    [self deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationNone];
    [self reloadData];
}

@end
