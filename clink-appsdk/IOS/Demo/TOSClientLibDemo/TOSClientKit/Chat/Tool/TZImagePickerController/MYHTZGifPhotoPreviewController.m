//
//  MYHTZGifPhotoPreviewController.m
//  MYHTZImagePickerController
//
//  Created by 侯力 on 2024/03/15.
//  Copyright © 2016年 侯力. All rights reserved.
//

#import "MYHTZGifPhotoPreviewController.h"
#import "MYHTZImagePickerController.h"
#import "MYHTZAssetModel.h"
#import "UIView+MYHLayout.h"
#import "MYHTZPhotoPreviewCell.h"
#import "MYHTZImageManager.h"

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wdeprecated-declarations"

@interface MYHTZGifPhotoPreviewController () {
    UIView *_toolBar;
    UIButton *_doneButton;
    UIProgressView *_progress;
    
    MYHTZPhotoPreviewView *_previewView;
    
    UIStatusBarStyle _originStatusBarStyle;
}
@property (assign, nonatomic) BOOL needShowStatusBar;
@end

@implementation MYHTZGifPhotoPreviewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.needShowStatusBar = ![UIApplication sharedApplication].statusBarHidden;
    self.view.backgroundColor = [UIColor blackColor];
    MYHTZImagePickerController *tzImagePickerVc = (MYHTZImagePickerController *)self.navigationController;
    if (tzImagePickerVc) {
        self.navigationItem.title = [NSString stringWithFormat:@"GIF %@",tzImagePickerVc.previewBtnTitleStr];
    }
    [self configPreviewView];
    [self configBottomToolBar];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    _originStatusBarStyle = [UIApplication sharedApplication].statusBarStyle;
    [UIApplication sharedApplication].statusBarStyle = UIStatusBarStyleLightContent;
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    if (self.needShowStatusBar) {
        [UIApplication sharedApplication].statusBarHidden = NO;
    }
    [UIApplication sharedApplication].statusBarStyle = _originStatusBarStyle;
}

- (void)configPreviewView {
    _previewView = [[MYHTZPhotoPreviewView alloc] initWithFrame:CGRectZero];
    _previewView.model = self.model;
    __weak typeof(self) weakSelf = self;
    [_previewView setSingleTapGestureBlock:^{
        __strong typeof(weakSelf) strongSelf = weakSelf;
        [strongSelf signleTapAction];
    }];
    [self.view addSubview:_previewView];
}

- (void)configBottomToolBar {
    _toolBar = [[UIView alloc] initWithFrame:CGRectZero];
    CGFloat rgb = 34 / 255.0;
    _toolBar.backgroundColor = [UIColor colorWithRed:rgb green:rgb blue:rgb alpha:0.7];
    
    _doneButton = [UIButton buttonWithType:UIButtonTypeCustom];
    _doneButton.titleLabel.font = [UIFont systemFontOfSize:16];
    [_doneButton addTarget:self action:@selector(doneButtonClick) forControlEvents:UIControlEventTouchUpInside];
    MYHTZImagePickerController *tzImagePickerVc = (MYHTZImagePickerController *)self.navigationController;
    if (tzImagePickerVc) {
        [_doneButton setTitle:tzImagePickerVc.doneBtnTitleStr forState:UIControlStateNormal];
        [_doneButton setTitleColor:tzImagePickerVc.oKButtonTitleColorNormal forState:UIControlStateNormal];
    } else {
        [_doneButton setTitle:[NSBundle tos_tz_localizedStringForKey:@"Done"] forState:UIControlStateNormal];
        [_doneButton setTitleColor:[UIColor colorWithRed:(83/255.0) green:(179/255.0) blue:(17/255.0) alpha:1.0] forState:UIControlStateNormal];
    }
    [_toolBar addSubview:_doneButton];
    
    UILabel *byteLabel = [[UILabel alloc] init];
    byteLabel.textColor = [UIColor whiteColor];
    byteLabel.font = [UIFont systemFontOfSize:13];
    byteLabel.frame = CGRectMake(10, 0, 100, 44);
    [[MYHTZImageManager manager] getPhotosBytesWithArray:@[_model] completion:^(NSString *totalBytes) {
        byteLabel.text = totalBytes;
    }];
    [_toolBar addSubview:byteLabel];
    
    [self.view addSubview:_toolBar];
    
    if (tzImagePickerVc.gifPreviewPageUIConfigBlock) {
        tzImagePickerVc.gifPreviewPageUIConfigBlock(_toolBar, _doneButton);
    }
}

- (UIStatusBarStyle)preferredStatusBarStyle {
    MYHTZImagePickerController *tzImagePicker = (MYHTZImagePickerController *)self.navigationController;
    if (tzImagePicker && [tzImagePicker isKindOfClass:[MYHTZImagePickerController class]]) {
        return tzImagePicker.statusBarStyle;
    }
    return [super preferredStatusBarStyle];
}

#pragma mark - Layout

- (void)viewDidLayoutSubviews {
    [super viewDidLayoutSubviews];
    
    _previewView.frame = self.view.bounds;
    _previewView.scrollView.frame = self.view.bounds;
    CGFloat toolBarHeight = 44 + [TZCommonTools tz_safeAreaInsets].bottom;
    _toolBar.frame = CGRectMake(0, self.view.tos_tz_height - toolBarHeight, self.view.tos_tz_width, toolBarHeight);
    _doneButton.frame = CGRectMake(self.view.tos_tz_width - 44 - 12, 0, 44, 44);
    
    MYHTZImagePickerController *tzImagePickerVc = (MYHTZImagePickerController *)self.navigationController;
    if (tzImagePickerVc.gifPreviewPageDidLayoutSubviewsBlock) {
        tzImagePickerVc.gifPreviewPageDidLayoutSubviewsBlock(_toolBar, _doneButton);
    }
}

#pragma mark - Click Event

- (void)signleTapAction {
    _toolBar.hidden = !_toolBar.isHidden;
    [self.navigationController setNavigationBarHidden:_toolBar.isHidden];
    MYHTZImagePickerController *tzImagePickerVc = (MYHTZImagePickerController *)self.navigationController;
    if (_toolBar.isHidden) {
        [UIApplication sharedApplication].statusBarHidden = YES;
    } else if (tzImagePickerVc.needShowStatusBar) {
        [UIApplication sharedApplication].statusBarHidden = NO;
    }
}

- (void)doneButtonClick {
    if (self.navigationController) {
        MYHTZImagePickerController *imagePickerVc = (MYHTZImagePickerController *)self.navigationController;
        if (imagePickerVc.autoDismiss) {
            [self.navigationController dismissViewControllerAnimated:YES completion:^{
                [self callDelegateMethod];
            }];
        } else {
            [self callDelegateMethod];
        }
    } else {
        [self dismissViewControllerAnimated:YES completion:^{
            [self callDelegateMethod];
        }];
    }
}

- (void)callDelegateMethod {
    MYHTZImagePickerController *imagePickerVc = (MYHTZImagePickerController *)self.navigationController;
    UIImage *animatedImage = _previewView.imageView.image;
    if ([imagePickerVc.pickerDelegate respondsToSelector:@selector(imagePickerController:didFinishPickingGifImage:sourceAssets:)]) {
        [imagePickerVc.pickerDelegate imagePickerController:imagePickerVc didFinishPickingGifImage:animatedImage sourceAssets:_model.asset];
    }
    if (imagePickerVc.didFinishPickingGifImageHandle) {
        imagePickerVc.didFinishPickingGifImageHandle(animatedImage,_model.asset);
    }
}

#pragma clang diagnostic pop

@end
