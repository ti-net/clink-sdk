//
//  MYHTZAssetModel.m
//  MYHTZImagePickerController
//
//  Created by 谭真 on 15/12/24.
//  Copyright © 2015年 谭真. All rights reserved.
//

#import "MYHTZAssetModel.h"
#import "MYHTZImageManager.h"

@implementation MYHTZAssetModel

+ (instancetype)modelWithAsset:(PHAsset *)asset type:(MYHTZAssetModelMediaType)type{
    MYHTZAssetModel *model = [[MYHTZAssetModel alloc] init];
    model.asset = asset;
    model.isSelected = NO;
    model.type = type;
    return model;
}

+ (instancetype)modelWithAsset:(PHAsset *)asset type:(MYHTZAssetModelMediaType)type timeLength:(NSString *)timeLength {
    MYHTZAssetModel *model = [self modelWithAsset:asset type:type];
    model.timeLength = timeLength;
    return model;
}

@end



@implementation MYHTZAlbumModel

- (void)setResult:(PHFetchResult *)result needFetchAssets:(BOOL)needFetchAssets {
    _result = result;
    if (needFetchAssets) {
        [[MYHTZImageManager manager] getAssetsFromFetchResult:result completion:^(NSArray<MYHTZAssetModel *> *models) {
            self->_models = models;
            if (self->_selectedModels) {
                [self checkSelectedModels];
            }
        }];
    }
}

- (void)refreshFetchResult {
    PHFetchResult *fetchResult = [PHAsset fetchAssetsInAssetCollection:self.collection options:self.options];
    self.count = fetchResult.count;
    [self setResult:fetchResult];
}

- (void)setSelectedModels:(NSArray *)selectedModels {
    _selectedModels = selectedModels;
    if (_models) {
        [self checkSelectedModels];
    }
}

- (void)checkSelectedModels {
    self.selectedCount = 0;
    NSMutableSet *selectedAssets = [NSMutableSet setWithCapacity:_selectedModels.count];
    for (MYHTZAssetModel *model in _selectedModels) {
        [selectedAssets addObject:model.asset];
    }
    for (MYHTZAssetModel *model in _models) {
        if ([selectedAssets containsObject:model.asset]) {
            self.selectedCount ++;
        }
    }
}

- (NSString *)name {
    if (_name) {
        return _name;
    }
    return @"";
}

@end
