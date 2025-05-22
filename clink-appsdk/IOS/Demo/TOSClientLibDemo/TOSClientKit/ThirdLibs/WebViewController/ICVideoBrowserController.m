//
//  ICVideoBrowserController.m
//  TIMClientKit
//
//  Created by YanBo on 2020/7/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "ICVideoBrowserController.h"
#import "ICAVPlayer.h"
#import "TIMConstants.h"
#import "kitUtils.h"
#import "UIImage+TIMGIF.h"
#import "ICVideoManager.h"
#import <TOSClientKit/TOSClientKit.h>
#import "WHToast.h"
#import "TIMAVPlayerView.h"
#import "NSObject+TIMShowError.h"

@interface ICVideoBrowserController ()<ICAVPlayerDelegate, TIMAVPlayerViewDelegate>

@property (nonatomic, strong) NSString *curVideoPath;
@property (nonatomic, strong) ICAVPlayer *curPlayer;
@property (nonatomic, strong) UIButton *startPlayerBtn;
@property (nonatomic, strong) UIButton *downloadBtn;

/// 返回按钮
@property (nonatomic, strong) UIButton                * backButton;

@property (nonatomic, strong) TIMAVPlayerView                * playerView;
@end

@implementation ICVideoBrowserController

- (UIButton *)backButton {
    if (!_backButton) {
        _backButton = [[UIButton alloc] initWithFrame:(CGRectMake(10, APP_Frame_Height-60 - kBottomBarHeight, 40, 40))];
//        [_backButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"videoClose"]] forState:UIControlStateNormal];
        [_backButton setBackgroundImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"videoClose"]] forState:(UIControlStateNormal)];
        [_backButton addTarget:self action:@selector(closePlayerViewAction) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _backButton;
}

- (void)tapAction:(UITapGestureRecognizer *)sender {
    [self.curPlayer dismissAnimated:YES completion:nil];
}

- (instancetype)initWithPath:(CGRect)fromRect messageId:(NSString *)msgId videoPath:(NSString *)videoPath{
    if (self = [super init]) {
        [self.view addSubview:self.imageView];
        [self.view addSubview:self.downloadBtn];
        [self.view addSubview:self.backButton];
        
        // 加载loading
        NSString *imagePath = [[NSBundle mainBundle] pathForResource:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"loading"] ofType:kGIFTimageType];
        UITapGestureRecognizer *viewTap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapAction:)];
        self.view.userInteractionEnabled = YES;
        [self.view addGestureRecognizer:viewTap];
        
        UITapGestureRecognizer *imageTap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapAction:)];
        [self.imageView addGestureRecognizer:imageTap];
        
        NSData *imageData = [NSData dataWithContentsOfFile:imagePath];
        self.imageView.image = [UIImage sd_animatedGIFWithData:imageData];
        // 设置图片位置
        self.imageView.frame = CGRectMake(App_Frame_Width / 2 - 32, APP_Frame_Height / 2 - 32, 64, 64);
        
        NSString *getVideoPath = [[ICVideoManager shareManager] videoPathWithFileName:msgId];
        NSData * videoData = [NSData dataWithContentsOfFile:getVideoPath];
        if (videoData && videoData.length > 3){
            self.curVideoPath = getVideoPath?:@"";
            [self playEvent];
        } else if(![videoPath hasPrefix:@"http"]) {
            self.curVideoPath = videoPath?:@"";
            [self playEvent];
        }else {
            @WeakObj(self)
            dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
                @StrongObj(self)
                // 创建并配置 NSMutableURLRequest
                NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:videoPath]
                                                                       cachePolicy:NSURLRequestUseProtocolCachePolicy
                                                                   timeoutInterval:30];
                // 添加自定义 header (如果有)
                // [request setValue:@"value" forHTTPHeaderField:@"headerField"];
                
                // 使用 NSURLSession 发起请求
                NSURLSession *session = [NSURLSession sharedSession];
                NSURLSessionDataTask *dataTask = [session dataTaskWithRequest:request completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
                    if (error) {
                        NSLog(@"Error downloading video: %@", error.localizedDescription);
                        return;
                    }
                    NSLog(@"视频下载地址：%@", videoPath);
                    @StrongObj(self)
                    dispatch_async(dispatch_get_main_queue(), ^{
                        // 通知主线程刷新
                        if (data) {
                            [self.imageView removeFromSuperview];
                            [self.backButton removeFromSuperview];
                            
                            // 写入文件并设置播放路径
                            self.curVideoPath = [[ICVideoManager shareManager] WriteDataToFile:msgId data:data];
                            self.playerView = [[TIMAVPlayerView alloc] initWithFrame:self.view.bounds];
                            [self.view addSubview:self.playerView];
                            NSLog(@"self.curVideoPath : %@", self.curVideoPath);
                            // 根据路径前缀设置 URL
                            NSURL *videoURL = ([self.curVideoPath hasPrefix:@"/var"]) ? [NSURL fileURLWithPath:self.curVideoPath ?: @""] : [NSURL URLWithString:self.curVideoPath ?: @""];
                            [self.playerView setURL:videoURL];
                            
                            self.playerView.delegate = self;
                            [self.playerView play];
                            [self.view bringSubviewToFront:self.downloadBtn];
                        }
                    });
                }];
                
                [dataTask resume];
            });
        }
        self.view.backgroundColor = [UIColor blackColor];
    }
    return self;
}

/// 播放事件
- (void)playEvent {
    if (![kitUtils isBlankString:self.curVideoPath]) {
        
        //本地视频地址前缀过滤
        if (self.curVideoPath.length < 7) {
            self.curVideoPath = @"";
        } else {
            if ([[self.curVideoPath substringToIndex:7] isEqualToString:@"file://"]) {
                self.curVideoPath = [self.curVideoPath substringFromIndex:7];
            } else {
            }
        }
        
        [self.imageView removeFromSuperview];
        [self.backButton removeFromSuperview];
        NSLog(@"isBlankString:%@ \n [NSURL fileURLWithPath:self.curVideoPath]: %@", self.curVideoPath, [NSURL fileURLWithPath:self.curVideoPath]);
        self.playerView =[[TIMAVPlayerView alloc]initWithFrame:(self.view.bounds)];
        [self.view addSubview:self.playerView];
        self.playerView.delegate = self;
        if ([self.curVideoPath hasPrefix:@"/var"]) {
            [self.playerView setURL:[NSURL fileURLWithPath:self.curVideoPath?:@""]];
        } else if ([self.curVideoPath hasPrefix:@"file:///var"]) {
            [self.playerView setURL:[NSURL URLWithString:self.curVideoPath?:@""]];
        } else {
            [self.playerView setURL:[NSURL URLWithString:self.curVideoPath?:@""]];
        }
        [self.playerView play];
        [self.view bringSubviewToFront:self.downloadBtn];
    }
}

-(void)closePlayerViewAction{
    [self.curPlayer releasePlayer];
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (UIButton *)startPlayerBtn {
    if (!_startPlayerBtn) {
        _startPlayerBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [_startPlayerBtn setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"MMVideoPreviewPlay"]] forState:UIControlStateNormal];
        _startPlayerBtn.frame = CGRectMake(App_Frame_Width / 2 - 40, APP_Frame_Height / 2 - 40, 80, 80);
        [_startPlayerBtn addTarget:self action:@selector(playerClickPlay) forControlEvents:UIControlEventTouchUpInside];
        _startPlayerBtn.layer.masksToBounds = YES;
        _startPlayerBtn.layer.cornerRadius = 5;
    }
    return _startPlayerBtn;
}

- (UIButton *)downloadBtn {
    if (!_downloadBtn) {
        _downloadBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [_downloadBtn setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"arrow"]] forState:UIControlStateNormal];
        _downloadBtn.frame = CGRectMake(App_Frame_Width - 60, APP_Frame_Height - 60 - kBottomBarHeight, 40, 40);
        [_downloadBtn addTarget:self action:@selector(downloadAction) forControlEvents:UIControlEventTouchUpInside];
        _downloadBtn.layer.masksToBounds = YES;
        _downloadBtn.layer.cornerRadius = 5;
    }
    return _downloadBtn;
}

-(void)downloadAction {
    if (self.curVideoPath) {
        NSURL *url = [NSURL URLWithString:self.curVideoPath];
        BOOL compatible = UIVideoAtPathIsCompatibleWithSavedPhotosAlbum([url path]);
        if (compatible) {
            //保存相册核心代码
            UISaveVideoAtPathToSavedPhotosAlbum([url path], self, @selector(savedPhotoImage:didFinishSavingWithError:contextInfo:), nil);
        }
    } else {
        [WHToast showMessage:@"正在下载中..." duration:2 finishHandler:^{
            
        }];
    }
}

//保存视频完成之后的回调
- (void)savedPhotoImage:(UIImage*)image didFinishSavingWithError:(NSError *)error contextInfo:(void *)contextInfo {
    if (error) {
        NSLog(@"保存视频失败%@", error.localizedDescription);
        [WHToast showImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"videoDownload_fail"]] message:@"网络异常" duration:2 finishHandler:^{
            
        }];
    }else {
        [WHToast showImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"videoDownload_successful"]] message:@"下载成功" duration:2 finishHandler:^{
            
        }];
    }
}


-(void)playerClickPlay{
    [self.startPlayerBtn removeFromSuperview];
    [self.curPlayer playerItemDidPlayToEndTime];
}

#pragma mark - TIMAVPlayerViewDelegate

/// 返回按钮的点击
- (void)videoPlayerWithBackTouch:(TIMAVPlayerView *)playerView {
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (void)videoPlayerPlaybackBufferEmpty:(TIMAVPlayerView *)playerView {
    NSLog(@"进行跳转后没数据 即播放卡顿");
}

- (void)videoPlayer:(TIMAVPlayerView *)playerView loadedTimeRangeDidChange:(CGFloat )duration {
    NSLog(@"duration 当前缓冲的长度 : %lf", duration);
}

- (void)videoPlayer:(TIMAVPlayerView *)playerView didFailWithError:(NSError *)error {
    NSLog(@"播放信息出错：%@", error.debugDescription);
    [self tim_showMBErrorView:error.debugDescription];
}

#pragma mark - Getter And Setter

- (void)setImage:(UIImage *)image {
    _image = image;
    self.imageView.image = image;
    // 设置图片位置
    self.imageView.frame = CGRectMake(0, 0, image.size.width, image.size.height);
}

- (UIImageView *)imageView {
    if (!_imageView) {
        _imageView = [[UIImageView alloc] init];
        _imageView.userInteractionEnabled = YES;
    }
    return _imageView;
}

@end
