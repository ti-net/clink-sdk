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
#import "UIImage+GIF.h"
#import "ICVideoManager.h"
#import <TOSClientKit/TOSClientKit.h>
#import "WHToast.h"
#import "TIMAVPlayerView.h"

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
        _backButton = [[UIButton alloc] initWithFrame:(CGRectMake(10, kNavTop-40, 40, 40))];
        [_backButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"navi_back"]] forState:UIControlStateNormal];
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
        if (videoData){
            self.curVideoPath = getVideoPath?:@"";
        } else if(![videoPath hasPrefix:@"http"]) {
            self.curVideoPath = videoPath?:@"";
        }else {
           @WeakObj(self)
           dispatch_async(dispatch_queue_create(0, 0), ^{
               // 子线程执行任务（比如获取较大数据）
               //1.通过URL创建NSURLRequest
               NSURLRequest *request = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:videoPath] cachePolicy:NSURLRequestUseProtocolCachePolicy timeoutInterval:30];
               //2.创建一个 NSMutableURLRequest 添加 header
               NSMutableURLRequest *mutableRequest = [request mutableCopy];
               //3.把值覆给request
               request = [mutableRequest copy];
               //3.建立网络连接NSURLConnection，同步请求数据
               NSHTTPURLResponse *response = nil;
               NSError *error = nil;
               __block NSData *videoImageData = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
               dispatch_async(dispatch_get_main_queue(), ^{
                   @StrongObj(self)
                // 通知主线程刷新
                   if (videoImageData) {
                       [self.imageView removeFromSuperview];
                       self.curVideoPath = [[ICVideoManager shareManager] WriteDataToFile:msgId data:videoImageData];
                       self.playerView =[[TIMAVPlayerView alloc]initWithFrame:(self.view.bounds)];
                       [self.view addSubview:self.playerView];
                       NSLog(@"videoImageData:%@", self.curVideoPath);
//                       [self.playerView setURL:[NSURL URLWithString:self.curVideoPath]];
                       if ([self.curVideoPath hasPrefix:@"/var"]) {
                           [self.playerView setURL:[NSURL fileURLWithPath:self.curVideoPath?:@""]];
                       }
                       else {
                           [self.playerView setURL:[NSURL URLWithString:self.curVideoPath?:@""]];
                       }
                       self.playerView.delegate = self;
                       [self.playerView play];
//                       self.curPlayer = [[ICAVPlayer alloc] initWithPlayerURL:[NSURL fileURLWithPath:self.curVideoPath]];
//                       self.curPlayer.delegate = self;
//
//                       UIView * fromView = [[UIView alloc] initWithFrame:fromRect];
//                       [self.curPlayer presentFromVideoView:fromView toContainer:self.view animated:YES completion:^{
//                           [self.curPlayer addSubview:self.startPlayerBtn];
//
//                       }];
                   }
               });
           });
       }
    
        self.view.backgroundColor = [UIColor blackColor];
        
        if (![kitUtils isBlankString:self.curVideoPath]) {
            
            //本地视频地址前缀过滤
            if ( self.curVideoPath.length < 7) {
                self.curVideoPath = @"";
            }else{
                if ([[self.curVideoPath substringToIndex:7] isEqualToString:@"file://"]) {
                    self.curVideoPath = [self.curVideoPath substringFromIndex:7];
                }else{
                }
            }
            
            [self.imageView removeFromSuperview];
//            self.curPlayer = [[ICAVPlayer alloc] initWithPlayerURL:[NSURL fileURLWithPath:self.curVideoPath]];
//            self.curPlayer.delegate = self;
//
//            UIView * fromView = [[UIView alloc] initWithFrame:fromRect];
//            [self.curPlayer presentFromVideoView:fromView toContainer:self.view animated:YES completion:^{
//                [self.curPlayer addSubview:self.startPlayerBtn];
//            }];
            NSLog(@"isBlankString:%@ \n [NSURL fileURLWithPath:self.curVideoPath]: %@", self.curVideoPath, [NSURL fileURLWithPath:self.curVideoPath]);
            self.playerView =[[TIMAVPlayerView alloc]initWithFrame:(self.view.bounds)];
            [self.view addSubview:self.playerView];
            self.playerView.delegate = self;
            if ([self.curVideoPath hasPrefix:@"/var"]) {
                [self.playerView setURL:[NSURL fileURLWithPath:self.curVideoPath?:@""]];
            }
            else {
                [self.playerView setURL:[NSURL URLWithString:self.curVideoPath?:@""]];
            }
            [self.playerView play];
            
        }
        
        [self.view bringSubviewToFront:self.downloadBtn];
        
    }
    return self;
}

-(void)closePlayerViewAction{
    [self.curPlayer releasePlayer];
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (UIButton *)startPlayerBtn
{
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

- (UIButton *)downloadBtn
{
    if (!_downloadBtn) {
        _downloadBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [_downloadBtn setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"arrow"]] forState:UIControlStateNormal];
        _downloadBtn.frame = CGRectMake(App_Frame_Width - 60, APP_Frame_Height - 60, 40, 40);
        [_downloadBtn addTarget:self action:@selector(downloadAction) forControlEvents:UIControlEventTouchUpInside];
        _downloadBtn.layer.masksToBounds = YES;
        _downloadBtn.layer.cornerRadius = 5;
    }
    return _downloadBtn;
}

-(void)downloadAction{
    UIActionSheet *actionSheet = [[UIActionSheet alloc] initWithTitle:nil delegate:self cancelButtonTitle:@"取消" destructiveButtonTitle:nil otherButtonTitles:@"保存", nil];
    [actionSheet showInView:self.view];
}

//保存视频完成之后的回调
- (void) savedPhotoImage:(UIImage*)image didFinishSavingWithError: (NSError *)error contextInfo: (void *)contextInfo {
    if (error) {
        NSLog(@"保存视频失败%@", error.localizedDescription);
        [WHToast showMessage:@"保存视频失败" duration:2 finishHandler:^{
            
        }];
    }else {
        [WHToast showMessage:@"保存视频成功" duration:2 finishHandler:^{
            
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



#pragma mark - Getter And Setter

- (void)setImage:(UIImage *)image
{
    _image = image;
    self.imageView.image = image;
    
    // 设置图片位置
    self.imageView.frame = CGRectMake(0, 0, image.size.width, image.size.height);
}

- (UIImageView *)imageView
{
    if (!_imageView) {
        _imageView = [[UIImageView alloc] init];
        _imageView.userInteractionEnabled = YES;
    }
    return _imageView;
}

#pragma mark - UIActionSheetDelegate

- (void)actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex
{
    if (buttonIndex == 0) {
        if (self.curVideoPath) {
            NSURL *url = [NSURL URLWithString:self.curVideoPath];
            BOOL compatible = UIVideoAtPathIsCompatibleWithSavedPhotosAlbum([url path]);
            if (compatible)
            {
                //保存相册核心代码
                UISaveVideoAtPathToSavedPhotosAlbum([url path], self, @selector(savedPhotoImage:didFinishSavingWithError:contextInfo:), nil);
            }
        }else{
            [WHToast showMessage:@"视频下载中" duration:2 finishHandler:^{
                
            }];
        }
        
    }
}


@end
