//
//  MYHTZVideoPlayerController.m
//  MYHTZImagePickerController
//
//  Created by 侯力 on 2024/03/15.
//  Copyright © 2016年 侯力. All rights reserved.
//

#import "MYHTZVideoPlayerController.h"
#import <MediaPlayer/MediaPlayer.h>
#import "UIView+MYHLayout.h"
#import "MYHTZImageManager.h"
#import "MYHTZAssetModel.h"
#import "MYHTZImagePickerController.h"
#import "MYHTZPhotoPreviewController.h"

@interface MYHTZVideoPlayerController () {
    AVPlayer *_player;
    AVPlayerLayer *_playerLayer;
    UIButton *_playButton;
    UIImage *_cover;
    
    UIView *_toolBar;
    UIButton *_doneButton;
    UIProgressView *_progress;
    
    UIStatusBarStyle _originStatusBarStyle;
}
@property (assign, nonatomic) BOOL needShowStatusBar;

// iCloud无法同步提示UI
@property (nonatomic, strong) UIView *iCloudErrorView;

@end

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wdeprecated-declarations"

@implementation MYHTZVideoPlayerController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.needShowStatusBar = ![UIApplication sharedApplication].statusBarHidden;
    self.view.backgroundColor = [UIColor blackColor];
    MYHTZImagePickerController *tzImagePickerVc = (MYHTZImagePickerController *)self.navigationController;
    if (tzImagePickerVc) {
        self.navigationItem.title = tzImagePickerVc.previewBtnTitleStr;
    }
    [self configMoviePlayer];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(pausePlayerAndShowNaviBar) name:UIApplicationWillResignActiveNotification object:nil];
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

- (void)configMoviePlayer {
    [[MYHTZImageManager manager] getPhotoWithAsset:_model.asset completion:^(UIImage *photo, NSDictionary *info, BOOL isDegraded) {
        BOOL iCloudSyncFailed = !photo && [TZCommonTools isICloudSyncError:info[PHImageErrorKey]];
        self.iCloudErrorView.hidden = !iCloudSyncFailed;
        if (!isDegraded && photo) {
            self->_cover = photo;
            self->_doneButton.enabled = YES;
        }
    }];
    [[MYHTZImageManager manager] getVideoWithAsset:_model.asset completion:^(AVPlayerItem *playerItem, NSDictionary *info) {
        dispatch_async(dispatch_get_main_queue(), ^{
            self->_player = [AVPlayer playerWithPlayerItem:playerItem];
            self->_playerLayer = [AVPlayerLayer playerLayerWithPlayer:self->_player];
            self->_playerLayer.frame = self.view.bounds;
            [self.view.layer addSublayer:self->_playerLayer];
            [self addProgressObserver];
            [self configPlayButton];
            [self configBottomToolBar];
            [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(pausePlayerAndShowNaviBar) name:AVPlayerItemDidPlayToEndTimeNotification object:self->_player.currentItem];
        });
    }];
}

/// Show progress，do it next time / 给播放器添加进度更新,下次加上
- (void)addProgressObserver{
    AVPlayerItem *playerItem = _player.currentItem;
    UIProgressView *progress = _progress;
    [_player addPeriodicTimeObserverForInterval:CMTimeMake(1.0, 1.0) queue:dispatch_get_main_queue() usingBlock:^(CMTime time) {
        float current = CMTimeGetSeconds(time);
        float total = CMTimeGetSeconds([playerItem duration]);
        if (current) {
            [progress setProgress:(current/total) animated:YES];
        }
    }];
}

- (void)configPlayButton {
    _playButton = [UIButton buttonWithType:UIButtonTypeCustom];
    [_playButton setImage:[UIImage tz_imageNamedFromMyBundle:@"MMVideoPreviewPlay"] forState:UIControlStateNormal];
    [_playButton setImage:[UIImage tz_imageNamedFromMyBundle:@"MMVideoPreviewPlayHL"] forState:UIControlStateHighlighted];
    [_playButton addTarget:self action:@selector(playButtonClick) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:_playButton];
}

- (void)configBottomToolBar {
    _toolBar = [[UIView alloc] initWithFrame:CGRectZero];
    CGFloat rgb = 34 / 255.0;
    _toolBar.backgroundColor = [UIColor colorWithRed:rgb green:rgb blue:rgb alpha:0.7];
    
    _doneButton = [UIButton buttonWithType:UIButtonTypeCustom];
    _doneButton.titleLabel.font = [UIFont systemFontOfSize:16];
    if (!_cover) {
        _doneButton.enabled = NO;
    }
    [_doneButton addTarget:self action:@selector(doneButtonClick) forControlEvents:UIControlEventTouchUpInside];
    MYHTZImagePickerController *tzImagePickerVc = (MYHTZImagePickerController *)self.navigationController;
    if (tzImagePickerVc) {
        [_doneButton setTitle:tzImagePickerVc.doneBtnTitleStr forState:UIControlStateNormal];
        [_doneButton setTitleColor:tzImagePickerVc.oKButtonTitleColorNormal forState:UIControlStateNormal];
    } else {
        [_doneButton setTitle:[NSBundle tos_tz_localizedStringForKey:@"Done"] forState:UIControlStateNormal];
        [_doneButton setTitleColor:[UIColor colorWithRed:(83/255.0) green:(179/255.0) blue:(17/255.0) alpha:1.0] forState:UIControlStateNormal];
    }
    [_doneButton setTitleColor:tzImagePickerVc.oKButtonTitleColorDisabled forState:UIControlStateDisabled];
    [_toolBar addSubview:_doneButton];
    [self.view addSubview:_toolBar];
    
    if (tzImagePickerVc.videoPreviewPageUIConfigBlock) {
        tzImagePickerVc.videoPreviewPageUIConfigBlock(_playButton, _toolBar, _doneButton);
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
    
    BOOL isFullScreen = self.view.tos_tz_height == [UIScreen mainScreen].bounds.size.height;
    CGFloat statusBarHeight = isFullScreen ? [TZCommonTools tz_statusBarHeight] : 0;
    CGFloat statusBarAndNaviBarHeight = statusBarHeight + self.navigationController.navigationBar.tos_tz_height;
    _playerLayer.frame = self.view.bounds;
    CGFloat toolBarHeight = 44 + [TZCommonTools tz_safeAreaInsets].bottom;
    _toolBar.frame = CGRectMake(0, self.view.tos_tz_height - toolBarHeight, self.view.tos_tz_width, toolBarHeight);
    _doneButton.frame = CGRectMake(self.view.tos_tz_width - 44 - 12, 0, 44, 44);
    _playButton.frame = CGRectMake(0, statusBarAndNaviBarHeight, self.view.tos_tz_width, self.view.tos_tz_height - statusBarAndNaviBarHeight - toolBarHeight);
    
    MYHTZImagePickerController *tzImagePickerVc = (MYHTZImagePickerController *)self.navigationController;
    if (tzImagePickerVc.videoPreviewPageDidLayoutSubviewsBlock) {
        tzImagePickerVc.videoPreviewPageDidLayoutSubviewsBlock(_playButton, _toolBar, _doneButton);
    }
}

#pragma mark - Click Event

- (void)playButtonClick {
    CMTime currentTime = _player.currentItem.currentTime;
    CMTime durationTime = _player.currentItem.duration;
    if (_player.rate == 0.0f) {
        [[NSNotificationCenter defaultCenter] postNotificationName:@"TZ_VIDEO_PLAY_NOTIFICATION" object:_player];
        if (currentTime.value == durationTime.value) [_player.currentItem seekToTime:CMTimeMake(0, 1)];
        [_player play];
        [self.navigationController setNavigationBarHidden:YES];
        _toolBar.hidden = YES;
        [_playButton setImage:nil forState:UIControlStateNormal];
        [UIApplication sharedApplication].statusBarHidden = YES;
    } else {
        [self pausePlayerAndShowNaviBar];
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
    if ([imagePickerVc.pickerDelegate respondsToSelector:@selector(imagePickerController:didFinishPickingVideo:sourceAssets:)]) {
        [imagePickerVc.pickerDelegate imagePickerController:imagePickerVc didFinishPickingVideo:_cover sourceAssets:_model.asset];
    }
    if (imagePickerVc.didFinishPickingVideoHandle) {
        imagePickerVc.didFinishPickingVideoHandle(_cover,_model.asset);
    }
}

#pragma mark - Notification Method

- (void)pausePlayerAndShowNaviBar {
    [_player pause];
    _toolBar.hidden = NO;
    [self.navigationController setNavigationBarHidden:NO];
    [_playButton setImage:[UIImage tz_imageNamedFromMyBundle:@"MMVideoPreviewPlay"] forState:UIControlStateNormal];
    
    if (self.needShowStatusBar) {
        [UIApplication sharedApplication].statusBarHidden = NO;
    }
}

#pragma mark - lazy
- (UIView *)iCloudErrorView{
    if (!_iCloudErrorView) {
        _iCloudErrorView = [[UIView alloc] initWithFrame:CGRectMake(0, [TZCommonTools tz_statusBarHeight] + 44 + 10, self.view.tos_tz_width, 28)];
        UIImageView *icloud = [[UIImageView alloc] init];
        icloud.image = [UIImage tz_imageNamedFromMyBundle:@"iCloudError"];
        icloud.frame = CGRectMake(20, 0, 28, 28);
        [_iCloudErrorView addSubview:icloud];
        UILabel *label = [[UILabel alloc] init];
        label.frame = CGRectMake(53, 0, self.view.tos_tz_width - 63, 28);
        label.font = [UIFont systemFontOfSize:10];
        label.textColor = [UIColor whiteColor];
        label.text = [NSBundle tos_tz_localizedStringForKey:@"iCloud sync failed"];
        [_iCloudErrorView addSubview:label];
        [self.view addSubview:_iCloudErrorView];
        _iCloudErrorView.hidden = YES;
    }
    return _iCloudErrorView;
}

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

#pragma clang diagnostic pop

@end
