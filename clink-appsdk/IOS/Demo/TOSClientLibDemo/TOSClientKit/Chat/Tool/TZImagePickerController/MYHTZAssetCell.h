//
//  MYHTZAssetCell.h
//  MYHTZImagePickerController
//
//  Created by 谭真 on 15/12/24.
//  Copyright © 2015年 谭真. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Photos/Photos.h>

typedef enum : NSUInteger {
    MYHTZAssetCellTypePhoto = 0,
    MYHTZAssetCellTypeLivePhoto,
    MYHTZAssetCellTypePhotoGif,
    MYHTZAssetCellTypeVideo,
    MYHTZAssetCellTypeAudio,
} MYHTZAssetCellType;

@class MYHTZAssetModel;
@interface MYHTZAssetCell : UICollectionViewCell
@property (weak, nonatomic) UIButton *selectPhotoButton;
@property (weak, nonatomic) UIButton *cannotSelectLayerButton;
@property (nonatomic, strong) MYHTZAssetModel *model;
@property (assign, nonatomic) NSInteger index;
@property (nonatomic, copy) void (^didSelectPhotoBlock)(BOOL);
@property (nonatomic, assign) MYHTZAssetCellType type;
@property (nonatomic, assign) BOOL allowPickingGif;
@property (nonatomic, assign) BOOL allowPickingMultipleVideo;
@property (nonatomic, copy) NSString *representedAssetIdentifier;
@property (nonatomic, assign) int32_t imageRequestID;

@property (nonatomic, strong) UIImage *photoSelImage;
@property (nonatomic, strong) UIImage *photoDefImage;

@property (nonatomic, assign) BOOL showSelectBtn;
@property (assign, nonatomic) BOOL allowPreview;

@property (nonatomic, copy) void (^assetCellDidSetModelBlock)(MYHTZAssetCell *cell, UIImageView *imageView, UIImageView *selectImageView, UILabel *indexLabel, UIView *bottomView, UILabel *timeLength, UIImageView *videoImgView);
@property (nonatomic, copy) void (^assetCellDidLayoutSubviewsBlock)(MYHTZAssetCell *cell, UIImageView *imageView, UIImageView *selectImageView, UILabel *indexLabel, UIView *bottomView, UILabel *timeLength, UIImageView *videoImgView);
@end


@class MYHTZAlbumModel;
@interface MYHTZAlbumCell : UITableViewCell
@property (nonatomic, strong) MYHTZAlbumModel *model;
@property (weak, nonatomic) UIButton *selectedCountButton;

@property (nonatomic, copy) void (^albumCellDidSetModelBlock)(MYHTZAlbumCell *cell, UIImageView *posterImageView, UILabel *titleLabel);
@property (nonatomic, copy) void (^albumCellDidLayoutSubviewsBlock)(MYHTZAlbumCell *cell, UIImageView *posterImageView, UILabel *titleLabel);
@end


@interface MYHTZAssetCameraCell : UICollectionViewCell
@property (nonatomic, strong) UIImageView *imageView;
@end
