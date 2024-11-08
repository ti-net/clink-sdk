//
//  QXCameraController.m
//
//
//  Created by xf-ling on 2017/6/1.
//  Copyright © 2017年 LXF. All rights reserved.
//

#import "TIMXFCameraController.h"
#import <AVFoundation/AVFoundation.h>
#import <AssetsLibrary/AssetsLibrary.h>
#import "TIMXFCameraButton.h"
#import "TIMXFPhotoLibraryManager.h"
#import <Photos/Photos.h>
#import <CoreMotion/CoreMotion.h>
#import "TIMConstants.h"
#import "kitUtils.h"
#import "NSDate+Common.h"
#import "SLEditImageController.h"

#define kScreenWidth [UIScreen mainScreen].bounds.size.width
#define kScreenHeight [UIScreen mainScreen].bounds.size.height
#define SafeViewBottomHeight (kScreenHeight == 812.0 ? 34.0 : 0.0)
#define iSiPhoneX ([UIScreen instancesRespondToSelector:@selector(currentMode)] ? CGSizeEqualToSize(CGSizeMake(1125, 2436), [[UIScreen mainScreen] currentMode].size) : NO)
#define VIDEO_FILEPATH                                              @"Video"

typedef void(^PropertyChangeBlock)(AVCaptureDevice *captureDevice);

@interface TIMXFCameraController() <AVCaptureVideoDataOutputSampleBufferDelegate, AVCaptureAudioDataOutputSampleBufferDelegate, UIGestureRecognizerDelegate>

@property (nonatomic, strong) dispatch_queue_t videoQueue;

@property (strong, nonatomic) AVCaptureSession *captureSession;                          //负责输入和输出设备之间的数据传递

@property (nonatomic, strong) AVCaptureDeviceInput *videoInput;                          //视频输入
@property (nonatomic, strong) AVCaptureDeviceInput *audioInput;                          //声音输入
@property (nonatomic, strong) AVCaptureVideoDataOutput *videoOutput;
@property (nonatomic, strong) AVCaptureAudioDataOutput *audioOutput;

@property (strong, nonatomic) AVCaptureStillImageOutput *captureStillImageOutput;        //照片输出流

@property (nonatomic, strong) AVAssetWriter *assetWriter;
@property (nonatomic, strong) AVAssetWriterInput *assetWriterVideoInput;
@property (nonatomic, strong) AVAssetWriterInput *assetWriterAudioInput;
@property (nonatomic, strong) NSDictionary *videoCompressionSettings;
@property (nonatomic, strong) NSDictionary *audioCompressionSettings;
@property (nonatomic, assign) BOOL canWrite;

@property (strong, nonatomic) AVCaptureVideoPreviewLayer *captureVideoPreviewLayer;      //预览图层

@property (nonatomic, strong) NSTimer *timer;                                            //记录录制时间

/// 闪光灯按钮
@property (strong, nonatomic) UIButton *onFlashLamp;

@property (weak, nonatomic) IBOutlet UIView *viewContainer;
@property (weak, nonatomic) IBOutlet UIButton *rotateCameraButton;
@property (weak, nonatomic) IBOutlet UIButton *takeButton;                               //拍摄按钮
@property (weak, nonatomic) IBOutlet UIButton *closeButton;
@property (weak, nonatomic) IBOutlet UILabel *tipLabel;
@property (strong, nonatomic) TIMXFCameraButton *cameraButton;                              //拍摄按钮

@property (weak, nonatomic) IBOutlet UIImageView *focusImageView;                        //聚焦视图
@property (assign, nonatomic) Boolean isFocusing;                                        //镜头正在聚焦
@property (assign, nonatomic) Boolean isShooting;                                        //正在拍摄
@property (assign, nonatomic) Boolean isRotatingCamera;                                  //正在旋转摄像头

//捏合缩放摄像头
@property (nonatomic,assign) CGFloat beginGestureScale;                                  //记录开始的缩放比例
@property (nonatomic,assign) CGFloat effectiveScale;                                     //最后的缩放比例

// 拍照摄像后的预览模块
@property (weak, nonatomic) IBOutlet UIButton *cancelButton;
@property (weak, nonatomic) IBOutlet UIButton *confirmButton;
@property (strong, nonatomic) UIView *photoPreviewContainerView;                         //相片预览ContainerView
@property (strong, nonatomic) UIImageView *photoPreviewImageView;                        //相片预览ImageView
@property (strong, nonatomic) UIView *videoPreviewContainerView;                         //视频预览View
@property (strong, nonatomic) NSURL *videoURL;                                           //视频文件地址
@property (strong, nonatomic) AVPlayerLayer *playerLayer;
@property (strong, nonatomic) AVPlayer *player;
@property (strong, nonatomic) AVPlayerItem *playerItem;
@property (assign, nonatomic) CGFloat currentVideoTimeLength;                             //当前小视频总时长

@property (assign, nonatomic) UIDeviceOrientation shootingOrientation;                 //拍摄中的手机方向
@property (strong, nonatomic) CMMotionManager *motionManager;

@end

@implementation TIMXFCameraController{
    
    CGFloat timeLength;             //时间长度
    
}

#pragma mark - 工厂方法

+ (instancetype)defaultCameraController
{
    TIMXFCameraController *cameraController = [[TIMXFCameraController alloc] initWithNibName:@"TOSClient.bundle/TIMXFCameraController" bundle:nil];
    
    return cameraController;
}

#pragma mark - 控制器方法

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    // 隐藏状态栏
    //    [[UIApplication sharedApplication] setStatusBarHidden:YES withAnimation:UIStatusBarAnimationNone];
    [UIApplication sharedApplication].statusBarHidden = YES;
    
    _isFocusing = NO;
    _isShooting = NO;
    _isRotatingCamera = NO;
    _canWrite = NO;
    _beginGestureScale = 1.0f;
    _effectiveScale = 1.0f;
    
    // 加载图标
    [_rotateCameraButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"icon_change"]] forState:UIControlStateNormal];
    _focusImageView.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"sight_video_focus"]];
    [_cancelButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"icon_return_p"]] forState:UIControlStateHighlighted];
    [_cancelButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"icon_return_n"]] forState:UIControlStateNormal];
    [_confirmButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"icon_finish_p"]] forState:UIControlStateNormal];
    
    UIImage *onFlashLampImage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"onFlashLamp"]];
    UIImage *offFlashLampImage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"offFlashLamp"]];
    
    UIButton *onFlashLamp = [UIButton buttonWithType:(UIButtonTypeCustom)];
    onFlashLamp.frame = CGRectMake(kWindowWidth - offFlashLampImage.size.width - 20.f, kStatusBarHeight + 20.f, offFlashLampImage.size.width, offFlashLampImage.size.height);
    [onFlashLamp setImage:offFlashLampImage forState:(UIControlStateNormal)];
    [onFlashLamp setImage:onFlashLampImage forState:(UIControlStateSelected)];
    [onFlashLamp addTarget:self action:@selector(didClickOnFlashLampAction:) forControlEvents:(UIControlEventTouchUpInside)];
    [self.view addSubview:onFlashLamp];
    self.onFlashLamp = onFlashLamp;
    
    AVAuthorizationStatus authStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeVideo];
    if (authStatus == AVAuthorizationStatusRestricted || authStatus == AVAuthorizationStatusDenied) {
        [self requestAuthorizationForVideo];
    }
    
    //判断用户是否允许访问麦克风权限
    authStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeAudio];
    if (authStatus == AVAuthorizationStatusRestricted || authStatus == AVAuthorizationStatusDenied) {
        [self requestAuthorizationForVideo];
    }
    [self requestAuthorizationForPhotoLibrary];
    
    [self initAVCaptureSession];
    
    [self addTapGenstureRecognizerForCamera];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self configDefaultUIDisplay];
}

- (void)didClickOnFlashLampAction:(UIButton *)sender {
    sender.selected = !sender.selected;
}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    
    [self startSession];
    
    [self setFocusCursorWithPoint:self.viewContainer.center];
    
    [self tipLabelAnimation];
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    
    // 显示状态栏
    //    [[UIApplication sharedApplication] setStatusBarHidden:NO withAnimation:UIStatusBarAnimationNone];
    [UIApplication sharedApplication].statusBarHidden = NO;
}

- (void)viewDidDisappear:(BOOL)animated {
    [super viewDidDisappear:animated];
    
    [self stopSession];
    
    [self stopUpdateAccelerometer];
}

- (void)dealloc {
    TIMKitLog(@"dealloc");
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

#pragma mark - 控件方法

/**
 *  关闭当前界面
 */
- (IBAction)closeBtnFunc:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}

/**
 *  切换前后摄像头
 */
- (IBAction)rotateCameraBtnFunc:(id)sender {
    _isRotatingCamera = YES;
    
    AVCaptureDevice *currentDevice = [self.videoInput device];
    AVCaptureDevicePosition currentPosition = [currentDevice position];
    
    AVCaptureDevice *toChangeDevice;
    AVCaptureDevicePosition toChangePosition = AVCaptureDevicePositionFront;
    if (currentPosition == AVCaptureDevicePositionUnspecified || currentPosition == AVCaptureDevicePositionFront) {
        toChangePosition = AVCaptureDevicePositionBack;
    }
    toChangeDevice = [self getCameraDeviceWithPosition:toChangePosition];
    
    //获得要调整的设备输入对象
    AVCaptureDeviceInput *toChangeDeviceInput = [[AVCaptureDeviceInput alloc] initWithDevice:toChangeDevice error:nil];
    
    //改变会话的配置前一定要先开启配置，配置完成后提交配置改变
    [self.captureSession beginConfiguration];
    //移除原有输入对象
    [self.captureSession removeInput:self.videoInput];
    //添加新的输入对象
    if ([self.captureSession canAddInput:toChangeDeviceInput]) {
        [self.captureSession addInput:toChangeDeviceInput];
        self.videoInput = toChangeDeviceInput;
    }
    
    //提交会话配置
    [self.captureSession commitConfiguration];
    
    _isRotatingCamera = NO;
}

- (IBAction)cancelBtnfunc:(id)sender {
    [self removePlayerItemNotification];
    [self startAnimationGroup];
}

/**
 *  确认按钮并返回代理
 */
- (IBAction)confirmBtnFunc:(id)sender {
    TIMKitLog(@"点击确认拍摄按钮");
    __weak typeof(self) weakSelf = self;
    if (self.photoPreviewImageView) {
        UIImage *finalImage = [self cutImageWithView:self.photoPreviewImageView];
        
        [TIMXFPhotoLibraryManager savePhotoWithImage:finalImage andAssetCollectionName:self.assetCollectionName withCompletion:^(UIImage *image, NSError *error) {
            
            if (self.takePhotosCompletionBlock) {
                if (error) {
                    TIMKitLog(@"保存照片失败!");
                    weakSelf.takePhotosCompletionBlock(nil, error);
                } else {
                    TIMKitLog(@"保存照片成功!");
                    weakSelf.takePhotosCompletionBlock(image, nil);
                }
            }
        }];
        self.confirmButton.userInteractionEnabled = NO;
    } else {
        [weakSelf cropWithVideoUrlStr:weakSelf.videoURL start:0 end:weakSelf.currentVideoTimeLength completion:^(NSURL *outputURL, Float64 videoDuration, BOOL isSuccess) {
            
            if (isSuccess) {
                [TIMXFPhotoLibraryManager saveVideoWithVideoUrl:outputURL andAssetCollectionName:nil withCompletion:^(NSURL *videoUrl, NSError *error) {
                    
                    if (self.shootCompletionBlock) {
                        if (error) {
                            TIMKitLog(@"保存视频失败!");
                            weakSelf.shootCompletionBlock(nil, 0, nil, error);
                        } else {
                            TIMKitLog(@"保存视频成功!");
                            
                            // 获取视频的第一帧图片
                            UIImage *image = [weakSelf thumbnailImageRequestWithVideoUrl:videoUrl andTime:0.01f];
                            weakSelf.shootCompletionBlock(videoUrl, videoDuration, image, nil);
                            [[NSFileManager defaultManager] removeItemAtURL:weakSelf.videoURL error:nil];
                            weakSelf.videoURL = nil;
                        }
                    }
                    weakSelf.confirmButton.userInteractionEnabled = NO;
                }];
            } else {
                TIMKitLog(@"保存视频失败!");
                [[NSFileManager defaultManager] removeItemAtURL:weakSelf.videoURL error:nil];
                weakSelf.videoURL = nil;
                [[NSFileManager defaultManager] removeItemAtURL:outputURL error:nil];
            }
        }];
    }
}

#pragma mark - 懒加载
- (AVCaptureSession *)captureSession
{
    if (_captureSession == nil)
    {
        _captureSession = [[AVCaptureSession alloc] init];
        
        if ([_captureSession canSetSessionPreset:AVCaptureSessionPresetHigh])
        {
            _captureSession.sessionPreset = AVCaptureSessionPresetHigh;
        }
    }
    
    return _captureSession;
}

- (dispatch_queue_t)videoQueue
{
    if (!_videoQueue)
    {
        _videoQueue = dispatch_queue_create("XFCameraController", DISPATCH_QUEUE_SERIAL); // dispatch_get_main_queue();
    }
    
    return _videoQueue;
}

- (CMMotionManager *)motionManager
{
    if (!_motionManager)
    {
        _motionManager = [[CMMotionManager alloc] init];
    }
    return _motionManager;
}

#pragma mark - 私有方法

/**
 *  初始化AVCapture会话
 */
- (void)initAVCaptureSession
{
    //1、添加 "视频" 与 "音频" 输入流到session
    [self setupVideo];
    
    [self setupAudio];
    
    //2、添加图片，movie输出流到session
    [self setupCaptureStillImageOutput];
    
    //3、创建视频预览层，用于实时展示摄像头状态
    [self setupCaptureVideoPreviewLayer];
    
    //设置静音状态也可播放声音
    AVAudioSession *audioSession = [AVAudioSession sharedInstance];
    [audioSession setCategory:AVAudioSessionCategoryPlayback error:nil];
}

/**
 *  设置视频输入
 */
- (void)setupVideo
{
    AVCaptureDevice *captureDevice = [self getCameraDeviceWithPosition:AVCaptureDevicePositionBack];
    if (!captureDevice)
    {
        TIMKitLog(@"取得后置摄像头时出现问题.");
        
        return;
    }
    
    NSError *error = nil;
    self.videoInput = [[AVCaptureDeviceInput alloc] initWithDevice:captureDevice error:&error];
    if (error)
    {
        TIMKitLog(@"取得设备输入videoInput对象时出错，错误原因：%@", error);
        
        return;
    }
    
    //3、将设备输出添加到会话中
    if ([self.captureSession canAddInput:self.videoInput])
    {
        [self.captureSession addInput:self.videoInput];
    }
    
    self.videoOutput = [[AVCaptureVideoDataOutput alloc] init];
    self.videoOutput.alwaysDiscardsLateVideoFrames = YES; //立即丢弃旧帧，节省内存，默认YES
    [self.videoOutput setSampleBufferDelegate:self queue:self.videoQueue];
    if ([self.captureSession canAddOutput:self.videoOutput])
    {
        [self.captureSession addOutput:self.videoOutput];
    }
}

/**
 *  设置音频录入
 */
- (void)setupAudio
{
    NSError *error = nil;
    self.audioInput = [[AVCaptureDeviceInput alloc] initWithDevice:[AVCaptureDevice defaultDeviceWithMediaType:AVMediaTypeAudio] error:&error];
    if (error)
    {
        TIMKitLog(@"取得设备输入audioInput对象时出错，错误原因：%@", error);
        
        return;
    }
    if ([self.captureSession canAddInput:self.audioInput])
    {
        [self.captureSession addInput:self.audioInput];
    }
    
    self.audioOutput = [[AVCaptureAudioDataOutput alloc] init];
    [self.audioOutput setSampleBufferDelegate:self queue:self.videoQueue];
    if([self.captureSession canAddOutput:self.audioOutput])
    {
        [self.captureSession addOutput:self.audioOutput];
    }
}

/**
 *  设置图片输出
 */
- (void)setupCaptureStillImageOutput
{
    self.captureStillImageOutput = [[AVCaptureStillImageOutput alloc] init];
    NSDictionary *outputSettings = @{
                                     //                                     AVVideoScalingModeKey:AVVideoScalingModeResizeAspect,
                                     AVVideoCodecKey:AVVideoCodecJPEG
                                     };
    [_captureStillImageOutput setOutputSettings:outputSettings];
    
    if ([self.captureSession canAddOutput:_captureStillImageOutput])
    {
        [self.captureSession addOutput:_captureStillImageOutput];
    }
}

/**
 *  设置预览layer
 */
- (void)setupCaptureVideoPreviewLayer
{
    _captureVideoPreviewLayer = [[AVCaptureVideoPreviewLayer alloc] initWithSession:self.captureSession];
    
    CALayer *layer = self.viewContainer.layer;
    
    _captureVideoPreviewLayer.frame = CGRectMake(0, 0, kScreenWidth, kScreenHeight);
    _captureVideoPreviewLayer.videoGravity = AVLayerVideoGravityResizeAspect;           //填充模式
    
    [layer addSublayer:_captureVideoPreviewLayer];
}

/**
 *  开启会话
 */
- (void)startSession
{
    if (![self.captureSession isRunning])
    {
        [self.captureSession startRunning];
    }
}

/**
 *  停止会话
 */
- (void)stopSession
{
    if ([self.captureSession isRunning])
    {
        [self.captureSession stopRunning];
    }
}

/**
 *  开始拍照录像动画组合
 */
- (void)startAnimationGroup {
    [self configDefaultUIDisplay];
    [self setFocusCursorWithPoint:self.viewContainer.center];
    [self tipLabelAnimation];
}

/**
 *  配置默认UI信息
 */
- (void)configDefaultUIDisplay {
    if (self.photoPreviewImageView) {
        [self.photoPreviewImageView removeFromSuperview];
        [self.photoPreviewContainerView removeFromSuperview];
        self.photoPreviewImageView = nil;
        self.photoPreviewContainerView = nil;
    }
    if (self.videoPreviewContainerView) {
        [self.player pause];
        self.player = nil;
        self.playerItem = nil;
        [self.playerLayer removeFromSuperlayer];
        self.playerLayer = nil;
        self.cameraButton.progressPercentage = 0.0f;
        [self.videoPreviewContainerView removeFromSuperview];
        self.videoPreviewContainerView = nil;
        [[NSFileManager defaultManager] removeItemAtURL:self.videoURL error:nil];
        self.videoURL = nil;
    }
    
    [self.view bringSubviewToFront:self.rotateCameraButton];
    [self.view bringSubviewToFront:self.closeButton];
    [self.view bringSubviewToFront:self.onFlashLamp];
    [self.rotateCameraButton setHidden:NO];
    [self.closeButton setHidden:NO];
    
    [self.view bringSubviewToFront:self.tipLabel];
    [self.tipLabel setAlpha:0];
    [self.tipLabel setText:@"点击拍照，长按摄像"];
    
    [self.cancelButton setHidden:YES];
    [self.confirmButton setHidden:YES];
    
    // 设置拍照按钮
    if (_cameraButton == nil) {
        TIMXFCameraButton *cameraButton = [TIMXFCameraButton defaultCameraButton];
        _cameraButton = cameraButton;
        
        [self.view addSubview:cameraButton];
        CGFloat cameraBtnX = (kScreenWidth - cameraButton.bounds.size.width) / 2;
        CGFloat cameraBtnY = kScreenHeight - cameraButton.bounds.size.height - 35 - SafeViewBottomHeight;    //距离底部35
        cameraButton.frame = CGRectMake(cameraBtnX, cameraBtnY, cameraButton.bounds.size.width, cameraButton.bounds.size.height);
        [self.view bringSubviewToFront:cameraButton];
        
        // 设置拍照按钮点击事件
        __weak typeof(self) weakSelf = self;
        // 配置拍照方法
        [cameraButton configureTapCameraButtonEventWithBlock:^(UITapGestureRecognizer *tapGestureRecognizer) {
            [weakSelf takePhotos:tapGestureRecognizer];
        }];
        // 配置拍摄方法
        [cameraButton configureLongPressCameraButtonEventWithBlock:^(UILongPressGestureRecognizer *longPressGestureRecognizer) {
            [weakSelf longPressCameraButtonFunc:longPressGestureRecognizer];
        }];
    }
    [self.cameraButton setHidden:NO];
    [self.view bringSubviewToFront:self.cameraButton];
    
    // 对焦imageView
    [self.view bringSubviewToFront:self.focusImageView];
    [self.focusImageView setAlpha:0];
    
    // 监听屏幕方向
    [self startUpdateAccelerometer];
}

/**
 *  提示语动画
 */
- (void)tipLabelAnimation {
    [self.view bringSubviewToFront:self.tipLabel];
    
    __weak typeof(self) weakSelf = self;
    [UIView animateWithDuration:1.0f delay:0.5f options:UIViewAnimationOptionCurveEaseInOut animations:^{
        
        [weakSelf.tipLabel setAlpha:1];
    } completion:^(BOOL finished) {
        
        [UIView animateWithDuration:1.0f delay:3.0f options:UIViewAnimationOptionCurveEaseInOut animations:^{
            [weakSelf.tipLabel setAlpha:0];
        } completion:nil];
    }];
}

/**
 *  取得指定位置的摄像头
 *
 *  @param position 摄像头位置
 *
 *  @return 摄像头设备
 */
- (AVCaptureDevice *)getCameraDeviceWithPosition:(AVCaptureDevicePosition )position {
    NSArray *cameras = [AVCaptureDevice devicesWithMediaType:AVMediaTypeVideo];
    for (AVCaptureDevice *camera in cameras) {
        if ([camera position] == position) {
            return camera;
        }
    }
    return nil;
}

/**
 *  改变设备属性的统一操作方法
 *
 *  @param propertyChange 属性改变操作
 */
- (void)changeDeviceProperty:(PropertyChangeBlock)propertyChange {
    AVCaptureDevice *captureDevice = [self.videoInput device];
    NSError *error;
    
    //注意改变设备属性前一定要首先调用lockForConfiguration:调用完之后使用unlockForConfiguration方法解锁
    if ([captureDevice lockForConfiguration:&error]) {
        propertyChange(captureDevice);
        [captureDevice unlockForConfiguration];
    } else {
        TIMKitLog(@"设置设备属性过程发生错误，错误信息：%@",error.localizedDescription);
    }
}

#pragma mark - 拍照功能
- (AVCaptureDevicePosition)devicePosition {
    if([[self.videoInput device] position] == AVCaptureDevicePositionUnspecified) {
        return AVCaptureDevicePositionBack;
    }
    return [[self.videoInput device] position];
}

/**
 *  拍照方法
 */
- (void)takePhotos:(UITapGestureRecognizer *)tapGestureRecognizer
{
    //根据设备输出获得连接
    AVCaptureConnection *captureConnection = [self.captureStillImageOutput connectionWithMediaType:AVMediaTypeVideo];
    
    if ([self devicePosition] == AVCaptureDevicePositionFront && captureConnection.supportsVideoMirroring) {
        captureConnection.videoMirrored = YES;
    } else {
        captureConnection.videoMirrored = NO;
    }
    
    if (captureConnection == nil) {
        // 设备故障
        return;
    }
    if (self.onFlashLamp.selected) {
        [self turnTorchOn:YES];
    }
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(.5f * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        //根据连接取得设备输出的数据
        [self.captureStillImageOutput captureStillImageAsynchronouslyFromConnection:captureConnection completionHandler:^(CMSampleBufferRef imageDataSampleBuffer, NSError *error) {
            dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
                if (!error) {
                    if (imageDataSampleBuffer){
                        NSData *imageData = [AVCaptureStillImageOutput jpegStillImageNSDataRepresentation:imageDataSampleBuffer];
                        UIImage *image = [UIImage imageWithData:imageData];
                        [self previewPhotoWithImage:image];
                        [self turnTorchOn:NO];
                    }
                }else{
                    
                }
            });
        }];
    });
}

/**
 *  预览图片
 */
- (void)previewPhotoWithImage:(UIImage *)image {
    UIImageOrientation imageOrientation = UIImageOrientationUp;
    if (self.shootingOrientation == UIDeviceOrientationLandscapeRight) {
        imageOrientation = UIImageOrientationDown;
    } else if (self.shootingOrientation == UIDeviceOrientationLandscapeLeft) {
        imageOrientation = UIImageOrientationUp;
    } else if (self.shootingOrientation == UIDeviceOrientationPortraitUpsideDown) {
        imageOrientation = UIImageOrientationLeft;
    } else {
        imageOrientation = UIImageOrientationRight;
    }
    
    //根据设备输出获得连接
    AVCaptureConnection *captureConnection = [self.captureStillImageOutput connectionWithMediaType:AVMediaTypeVideo];
    
    if ([self devicePosition] == AVCaptureDevicePositionFront && captureConnection.supportsVideoMirroring) {
        if (self.shootingOrientation == UIDeviceOrientationLandscapeRight) {
            imageOrientation = UIImageOrientationUpMirrored;
        } else if (self.shootingOrientation == UIDeviceOrientationLandscapeLeft) {
            imageOrientation = UIImageOrientationDownMirrored;
        } else if (self.shootingOrientation == UIDeviceOrientationPortraitUpsideDown) {
            imageOrientation = UIImageOrientationRightMirrored;
        } else {
            imageOrientation = UIImageOrientationLeftMirrored;
        }
    }
    
    SLEditImageController *sleEditImageController = [[SLEditImageController alloc] init];
    sleEditImageController.assetCollectionName = self.assetCollectionName;
    __weak typeof(self) weakSelf = self;
    [sleEditImageController setReturnImage:^(UIImage * _Nonnull image) {
        __strong typeof(weakSelf) strongSelf = weakSelf;
        [TIMXFPhotoLibraryManager savePhotoWithImage:image andAssetCollectionName:self.assetCollectionName withCompletion:^(UIImage *image, NSError *error) {
            __strong typeof(weakSelf) strongSelf = weakSelf;
            if (strongSelf.takePhotosCompletionBlock) {
                if (error) {
                    TIMKitLog(@"保存照片失败!");
                    strongSelf.takePhotosCompletionBlock(nil, error);
                } else {
                    TIMKitLog(@"保存照片成功!");
                    strongSelf.takePhotosCompletionBlock(image, nil);
                }
            }
        }];
        
        strongSelf.confirmButton.userInteractionEnabled = NO;
    }];
    
    UIImage *originalImage = [UIImage imageWithCGImage:image.CGImage scale:[UIScreen mainScreen].scale orientation:imageOrientation];
//    NSString *timeStr = [[NSDate date] stringWithDateFormat:@"yyyy.MM.dd HH:mm"];
    sleEditImageController.image = originalImage;//[self jx_WaterImageWithImage:originalImage text:timeStr textPoint:CGPointMake(0, originalImage.size.height - 50)];
    sleEditImageController.modalPresentationStyle = UIModalPresentationCustom;
    [self presentViewController:sleEditImageController animated:NO completion:nil];
}

// 给图片添加文字水印：
- (UIImage *)jx_WaterImageWithImage:(UIImage *)image
                               text:(NSString *)text
                          textPoint:(CGPoint)point {
    if (!image || (image.size.width <= 0 || image.size.height <=0 || isnan(image.size.width) || isnan(image.size.height)) ) {
        // 是兼容iOS17的 后续可以考虑是否替换实现 TODO
        return image;
    }
    //1.开启上下文
    UIGraphicsBeginImageContextWithOptions(image.size, NO, 0);
    //2.绘制图片
    [image drawInRect:CGRectMake(0, 0, image.size.width, image.size.height)];
    
    CGFloat rectangularWidth = image.size.width/3.f;
    CGFloat rectangularHeight = rectangularWidth/4.f;
    
    //空心矩形的frame
    CGRect rect = CGRectMake(0, image.size.height - rectangularHeight, rectangularWidth, rectangularHeight);
    
    //边框宽度
    CGFloat borderWidth = MAX(rectangularWidth/100.f, 1.f);
    CGContextRef contextRef = UIGraphicsGetCurrentContext();
    CGContextSetLineWidth(contextRef, borderWidth);
    
    UIBezierPath *path = [UIBezierPath bezierPathWithRect:rect];
    //3.添加路径
    CGContextAddPath(contextRef, path.CGPath);
    
    //描边路径
    [TOSHexColor(0xA7A8AC) set];
    CGContextStrokePath(contextRef);
    
    [TOSHexAColor(0x6B6F7E, 0.7f) set];
    CGContextAddPath(contextRef, path.CGPath);
    //填充路径
    CGContextFillPath(contextRef);
    
    CGFloat fontSize = 11.f * rectangularWidth / 120.f;
    NSDictionary *attributed = @{NSFontAttributeName: [UIFont fontWithName:@"PingFangSC-Regular" size:fontSize], NSForegroundColorAttributeName: [UIColor whiteColor]};
    
    CGSize textSize = [self sizeWithText:text attributedString:attributed];
    //水印文字
    [text drawAtPoint:CGPointMake((rect.size.width - textSize.width)/2, (rect.size.height - textSize.height)/2 + rect.origin.y) withAttributes:attributed];
    
    //3.从上下文中获取新图片
    UIImage *newImage = UIGraphicsGetImageFromCurrentImageContext();
    //4.关闭图形上下文
    UIGraphicsEndImageContext();
    //返回图片
    return newImage;
}

- (CGSize)sizeWithText:(NSString *)text attributedString:(NSDictionary * )attributed {
    CGSize size = [text sizeWithAttributes:attributed];
    return size;
}

- (UIImage *)cutImageWithView:(UIView *)view {
    if (!view || (view.frame.size.width <= 0 || view.frame.size.height <= 0 || isnan(view.frame.size.width) || isnan(view.frame.size.height)) ) {
        // 是兼容iOS17的 后续可以考虑是否替换实现 TODO
        return nil;
    }
    UIGraphicsBeginImageContextWithOptions(view.frame.size, NO, 0);
    [view.layer renderInContext:UIGraphicsGetCurrentContext()];
    UIImage *image = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    
    return image;
}

#pragma mark - 视频录制

/**
 *  录制视频方法
 */
- (void)longPressCameraButtonFunc:(UILongPressGestureRecognizer *)sender {
    AVAuthorizationStatus authStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeVideo];
    if (authStatus == AVAuthorizationStatusRestricted || authStatus ==AVAuthorizationStatusDenied) {
        NSLog(@"authStatus ==== %ld",authStatus);
        return;
    }
    
    //判断用户是否允许访问麦克风权限
    authStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeAudio];
    if (authStatus == AVAuthorizationStatusRestricted || authStatus ==AVAuthorizationStatusDenied) {
        NSLog(@"authStatus ----- %ld",authStatus);
        return;
    }
    
    switch (sender.state) {
        case UIGestureRecognizerStateBegan:
            [self startVideoRecorder];
            break;
        case UIGestureRecognizerStateCancelled:
            [self stopVideoRecorder];
            break;
        case UIGestureRecognizerStateEnded:
            [self stopVideoRecorder];
            break;
        case UIGestureRecognizerStateFailed:
            [self stopVideoRecorder];
            break;
        default:
            break;
    }
}

/**
 *  开始录制视频
 */
- (void)startVideoRecorder {
    _isShooting = YES;
    [self stopUpdateAccelerometer];
    [self.cameraButton startShootAnimationWithDuration:START_VIDEO_ANIMATION_DURATION];
    
    __weak typeof(self) weakSelf = self;
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(START_VIDEO_ANIMATION_DURATION * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        
        NSURL *url = [NSURL fileURLWithPath:[weakSelf createVideoFilePath]];
        self.videoURL = url;
        [self setUpWriter];
        [weakSelf timerFired];
    });
}

/**
 *  结束录制视频
 */
- (void)stopVideoRecorder {
    if (_isShooting) {
        _isShooting = NO;
        self.cameraButton.progressPercentage = 0.0f;
        [self.cameraButton stopShootAnimation];
        [self timerStop];
        
        __weak __typeof(self)weakSelf = self;
        if(_assetWriter && _assetWriter.status == AVAssetWriterStatusWriting) {
//            dispatch_async(self.videoQueue, ^{
                [_assetWriter finishWritingWithCompletionHandler:^{
                    weakSelf.canWrite = NO;
                    weakSelf.assetWriter = nil;
                    weakSelf.assetWriterAudioInput = nil;
                    weakSelf.assetWriterVideoInput = nil;
                }];
//            });
        }
        
        if (timeLength < VIDEO_RECORDER_MIN_TIME)
        {
            [_tipLabel setText:@"录制时间过短"];
            [self tipLabelAnimation];
            return;
        }
        
        [self.cameraButton setHidden:YES];
        
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.3f * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            
            [weakSelf previewVideoAfterShoot];
        });
    } else {
        // nothing
    }
}

/**
 *  设置写入视频属性
 */
- (void)setUpWriter {
    if (self.videoURL == nil) {
        return;
    }
    
    self.assetWriter = [AVAssetWriter assetWriterWithURL:self.videoURL fileType:AVFileTypeMPEG4 error:nil];
    //写入视频大小
    NSInteger numPixels = kScreenWidth * kScreenHeight;
    
    //每像素比特
    CGFloat bitsPerPixel = 20.f;
    NSInteger bitsPerSecond = numPixels * bitsPerPixel;
    
    // 码率和帧率设置
    NSDictionary *compressionProperties = @{ AVVideoAverageBitRateKey : @(bitsPerSecond),
                                             AVVideoExpectedSourceFrameRateKey : @(24),
                                             AVVideoMaxKeyFrameIntervalKey : @(15),
                                             AVVideoProfileLevelKey : AVVideoProfileLevelH264MainAutoLevel };
    CGFloat width = kScreenHeight;
    CGFloat height = kScreenWidth;
    if (iSiPhoneX) {
        width = kScreenHeight - 146;
        height = kScreenWidth;
    }
    //视频属性
    self.videoCompressionSettings = @{ AVVideoCodecKey : AVVideoCodecH264,
                                       AVVideoWidthKey : @(width * 2),
                                       AVVideoHeightKey : @(height * 2),
                                       AVVideoScalingModeKey : AVVideoScalingModeResizeAspectFill,
                                       AVVideoCompressionPropertiesKey : compressionProperties };
    
    _assetWriterVideoInput = [AVAssetWriterInput assetWriterInputWithMediaType:AVMediaTypeVideo outputSettings:self.videoCompressionSettings];
    //expectsMediaDataInRealTime 必须设为yes，需要从capture session 实时获取数据
    _assetWriterVideoInput.expectsMediaDataInRealTime = YES;
    
    AVCaptureConnection *captureConnection = [self.videoOutput connectionWithMediaType:AVMediaTypeVideo];
    
    if ([self devicePosition] == AVCaptureDevicePositionBack) {
        if (self.shootingOrientation == UIDeviceOrientationLandscapeRight) {
            _assetWriterVideoInput.transform = CGAffineTransformMakeRotation(M_PI);
        } else if (self.shootingOrientation == UIDeviceOrientationLandscapeLeft) {
            _assetWriterVideoInput.transform = CGAffineTransformMakeRotation(0);
        } else if (self.shootingOrientation == UIDeviceOrientationPortraitUpsideDown) {
            _assetWriterVideoInput.transform = CGAffineTransformMakeRotation(M_PI + (M_PI / 2.0));
        } else {
            _assetWriterVideoInput.transform = CGAffineTransformMakeRotation(M_PI / 2.0);
        }
        captureConnection.videoMirrored = NO;
    } else {
        if (self.shootingOrientation == UIDeviceOrientationLandscapeRight) {
            _assetWriterVideoInput.transform = CGAffineTransformMakeRotation(0);
        } else if (self.shootingOrientation == UIDeviceOrientationLandscapeLeft) {
            _assetWriterVideoInput.transform = CGAffineTransformMakeRotation(M_PI);
        } else if (self.shootingOrientation == UIDeviceOrientationPortraitUpsideDown) {
            _assetWriterVideoInput.transform = CGAffineTransformMakeRotation(M_PI / 2.0);
        } else {
            _assetWriterVideoInput.transform = CGAffineTransformMakeRotation(M_PI + (M_PI / 2.0));
        }
        captureConnection.videoMirrored = YES;
    }
    
    // 音频设置
    self.audioCompressionSettings = @{ AVEncoderBitRatePerChannelKey : @(28000),
                                       AVFormatIDKey : @(kAudioFormatMPEG4AAC),
                                       AVNumberOfChannelsKey : @(1),
                                       AVSampleRateKey : @(22050) };
    
    _assetWriterAudioInput = [AVAssetWriterInput assetWriterInputWithMediaType:AVMediaTypeAudio outputSettings:self.audioCompressionSettings];
    _assetWriterAudioInput.expectsMediaDataInRealTime = YES;
    
    if ([_assetWriter canAddInput:_assetWriterVideoInput]) {
        [_assetWriter addInput:_assetWriterVideoInput];
    } else {
        TIMKitLog(@"AssetWriter videoInput append Failed");
    }
    
    if ([_assetWriter canAddInput:_assetWriterAudioInput]) {
        [_assetWriter addInput:_assetWriterAudioInput];
    } else {
        TIMKitLog(@"AssetWriter audioInput Append Failed");
    }
    
    _canWrite = NO;
}

- (NSString *)createVideoFilePath {
    // 创建视频文件的存储路径
    NSString *filePath = [self createVideoFolderPath];
    if (filePath == nil) {
        return nil;
    }
    
    NSString *videoType = @".mp4";
    NSString *videoDestDateString = [kitUtils getMsgUUID];//[self createFileNamePrefix];
    NSString *videoFileName = [videoDestDateString stringByAppendingString:videoType];
    
    NSUInteger idx = 1;
    /*We only allow 10000 same file name*/
    NSString *finalPath = [NSString stringWithFormat:@"%@/%@", filePath, videoFileName];
    
    while (idx % 10000 && [[NSFileManager defaultManager] fileExistsAtPath:finalPath]) {
        finalPath = [NSString stringWithFormat:@"%@/%@_(%lu)%@", filePath, videoDestDateString, (unsigned long)idx++, videoType];
    }
    return finalPath;
}

- (NSString *)createVideoFolderPath
{
    NSFileManager *fileManager = [NSFileManager defaultManager];
//    NSString *homePath = NSHomeDirectory();
    NSString *documentPath = [[NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingPathComponent:@"Chat"];
    
    NSString *tmpFilePath;
    
    if (documentPath.length > 0)
    {
//        NSString *documentPath = [homePath stringByAppendingString:@"/Documents"];
        /// 判断这个目录是否存在，如果不存在需要手动创建
        if ([fileManager fileExistsAtPath:documentPath] == NO) {
            [fileManager createDirectoryAtPath:documentPath withIntermediateDirectories:YES attributes:nil error:nil];
            NSLog(@"创建目录");
        }
        
        if ([fileManager fileExistsAtPath:documentPath isDirectory:NULL] == YES)
        {
            BOOL success = NO;
            
            NSArray *paths = [fileManager contentsOfDirectoryAtPath:documentPath error:nil];
            
            //offline file folder
            tmpFilePath = [documentPath stringByAppendingString:[NSString stringWithFormat:@"/%@", VIDEO_FILEPATH]];
            if ([paths containsObject:VIDEO_FILEPATH] == NO)
            {
                success = [fileManager createDirectoryAtPath:tmpFilePath withIntermediateDirectories:YES attributes:nil error:nil];
                if (!success)
                {
                    tmpFilePath = nil;
                }
            }
            return tmpFilePath;
        }
    }
    
    return false;
}

/**
 *  创建文件名
 */
- (NSString *)createFileNamePrefix
{
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"yyyy-MM-dd HH:mm:ss zzz"];
    
    NSString *destDateString = [dateFormatter stringFromDate:[NSDate date]];
    destDateString = [destDateString stringByReplacingOccurrencesOfString:@" " withString:@"-"];
    destDateString = [destDateString stringByReplacingOccurrencesOfString:@"+" withString:@"-"];
    destDateString = [destDateString stringByReplacingOccurrencesOfString:@":" withString:@"-"];
    
    return destDateString;
}

/**
 *  开启定时器
 */
- (void)timerFired
{
    timeLength = 0;
    self.timer = [NSTimer scheduledTimerWithTimeInterval:TIMER_INTERVAL target:self selector:@selector(timerRecord) userInfo:nil repeats:YES];
}

/**
 *  绿色转圈百分比计算
 */
- (void)timerRecord
{
    if (!_isShooting)
    {
        [self timerStop];
        return ;
    }
    
    // 时间大于VIDEO_RECORDER_MAX_TIME则停止录制
    if (timeLength > VIDEO_RECORDER_MAX_TIME)
    {
        [self stopVideoRecorder];
    }
    
    timeLength += TIMER_INTERVAL;
    
    //    NSLog(@"%lf", timeLength / VIDEO_RECORDER_MAX_TIME);
    
    self.cameraButton.progressPercentage = timeLength / VIDEO_RECORDER_MAX_TIME;
    
}

/**
 *  停止定时器
 */
- (void)timerStop
{
    if ([self.timer isValid])
    {
        [self.timer invalidate];
        self.timer = nil;
    }
}

/**
 *  预览录制的视频
 */
- (void)previewVideoAfterShoot
{
    if (self.videoURL == nil || self.videoPreviewContainerView != nil)
    {
        return;
    }
    
    AVURLAsset *asset = [AVURLAsset assetWithURL:self.videoURL];
    
    //获取视频总时长
    Float64 duration = CMTimeGetSeconds(asset.duration);
    
    self.currentVideoTimeLength = duration;
    
    // 初始化AVPlayer
    self.videoPreviewContainerView = [[UIView alloc] init];
    self.videoPreviewContainerView.frame = CGRectMake(0, 0, kScreenWidth, kScreenHeight);
    self.videoPreviewContainerView.backgroundColor = [UIColor blackColor];
    
    self.playerItem = [AVPlayerItem playerItemWithAsset:asset];
    self.player = [[AVPlayer alloc] initWithPlayerItem:_playerItem];
    
    self.playerLayer = [AVPlayerLayer playerLayerWithPlayer:self.player];
    self.playerLayer.frame = CGRectMake(0, 0, kScreenWidth, kScreenHeight);
    
    self.playerLayer.videoGravity = AVLayerVideoGravityResizeAspect;
    
    [self.videoPreviewContainerView.layer addSublayer:self.playerLayer];
    
    // 其余UI布局设置
    [self.view addSubview:self.videoPreviewContainerView];
    [self.view bringSubviewToFront:self.videoPreviewContainerView];
    [self.view bringSubviewToFront:self.cancelButton];
    [self.view bringSubviewToFront:self.confirmButton];
    [self.cameraButton setHidden:YES];
    [self.closeButton setHidden:YES];
    [self.rotateCameraButton setHidden:YES];
    [self.cancelButton setHidden:NO];
    [self.confirmButton setHidden:NO];
    
    // 重复播放预览视频
    [self addNotificationWithPlayerItem];
    
    // 开始播放
    [self.player play];
}

/**
 *  截取指定时间的视频缩略图
 *
 *  @param timeBySecond 时间点，单位：s
 */
- (UIImage *)thumbnailImageRequestWithVideoUrl:(NSURL *)videoUrl andTime:(CGFloat)timeBySecond
{
    if (self.videoURL == nil)
    {
        return nil;
    }
    
    AVURLAsset *urlAsset = [AVURLAsset assetWithURL:videoUrl];
    
    //根据AVURLAsset创建AVAssetImageGenerator
    AVAssetImageGenerator *imageGenerator = [AVAssetImageGenerator assetImageGeneratorWithAsset:urlAsset];
    /*截图
     * requestTime:缩略图创建时间
     * actualTime:缩略图实际生成的时间
     */
    NSError *error = nil;
    CMTime requestTime = CMTimeMakeWithSeconds(timeBySecond, 10); //CMTime是表示电影时间信息的结构体，第一个参数表示是视频第几秒，第二个参数表示每秒帧数.(如果要活的某一秒的第几帧可以使用CMTimeMake方法)
    CMTime actualTime;
    CGImageRef cgImage = [imageGenerator copyCGImageAtTime:requestTime actualTime:&actualTime error:&error];
    if(error)
    {
        TIMKitLog(@"截取视频缩略图时发生错误，错误信息：%@", error.localizedDescription);
        return nil;
    }
    
    CMTimeShow(actualTime);
    UIImage *image = [UIImage imageWithCGImage:cgImage];
    CGImageRelease(cgImage);
    
    UIImage *finalImage = nil;
    if (self.shootingOrientation == UIDeviceOrientationLandscapeRight)
    {
        finalImage = [self rotateImage:image withOrientation:UIImageOrientationDown];
    }
    else if (self.shootingOrientation == UIDeviceOrientationLandscapeLeft)
    {
        finalImage = [self rotateImage:image withOrientation:UIImageOrientationUp];
    }
    else if (self.shootingOrientation == UIDeviceOrientationPortraitUpsideDown)
    {
        finalImage = [self rotateImage:image withOrientation:UIImageOrientationLeft];
    }
    else
    {
        finalImage = [self rotateImage:image withOrientation:UIImageOrientationRight];
    }
    
    return finalImage;
}

/**
 *  图片旋转
 */
- (UIImage *)rotateImage:(UIImage *)image withOrientation:(UIImageOrientation)orientation
{
    long double rotate = 0.0;
    CGRect rect;
    float translateX = 0;
    float translateY = 0;
    float scaleX = 1.0;
    float scaleY = 1.0;
    
    switch (orientation)
    {
        case UIImageOrientationLeft:
            rotate = M_PI_2;
            rect = CGRectMake(0, 0, image.size.height, image.size.width);
            translateX = 0;
            translateY = -rect.size.width;
            scaleY = rect.size.width/rect.size.height;
            scaleX = rect.size.height/rect.size.width;
            break;
        case UIImageOrientationRight:
            rotate = 3 * M_PI_2;
            rect = CGRectMake(0, 0, image.size.height, image.size.width);
            translateX = -rect.size.height;
            translateY = 0;
            scaleY = rect.size.width/rect.size.height;
            scaleX = rect.size.height/rect.size.width;
            break;
        case UIImageOrientationDown:
            rotate = M_PI;
            rect = CGRectMake(0, 0, image.size.width, image.size.height);
            translateX = -rect.size.width;
            translateY = -rect.size.height;
            break;
        default:
            rotate = 0.0;
            rect = CGRectMake(0, 0, image.size.width, image.size.height);
            translateX = 0;
            translateY = 0;
            break;
    }
    
    // iOS 17.0以上针对某些接口UIGraphicsBeginImageContextWithOptions的废弃兼容
    CGFloat scale = 1.0f;  // 默认值 1.0f
    UIImage *newPic = nil;
    if (@available(iOS 17.0, *)) {
        // 实际应该是在10.0作为分界点 但是为了谨慎起见只针对17做处理
        UIGraphicsImageRendererFormat *format = [[UIGraphicsImageRendererFormat alloc] init];
        format.scale = scale;
        format.opaque = NO;
        UIGraphicsImageRenderer *renderer = [[UIGraphicsImageRenderer alloc] initWithSize:rect.size
                                                                                   format:format];
        newPic = [renderer imageWithActions:^(UIGraphicsImageRendererContext * _Nonnull rendererContext) {
            CGContextRef context = UIGraphicsGetCurrentContext();
            //做CTM变换
            CGContextTranslateCTM(context, 0.0, rect.size.height);
            CGContextScaleCTM(context, 1.0, -1.0);
            CGContextRotateCTM(context, rotate);
            CGContextTranslateCTM(context, translateX, translateY);
            
            CGContextScaleCTM(context, scaleX, scaleY);
            //绘制图片
            CGContextDrawImage(context, CGRectMake(0, 0, rect.size.width, rect.size.height), image.CGImage);
        }];
    } else {
        
        UIGraphicsBeginImageContext(rect.size);
        CGContextRef context = UIGraphicsGetCurrentContext();
        //做CTM变换
        CGContextTranslateCTM(context, 0.0, rect.size.height);
        CGContextScaleCTM(context, 1.0, -1.0);
        CGContextRotateCTM(context, rotate);
        CGContextTranslateCTM(context, translateX, translateY);
        
        CGContextScaleCTM(context, scaleX, scaleY);
        //绘制图片
        CGContextDrawImage(context, CGRectMake(0, 0, rect.size.width, rect.size.height), image.CGImage);
        
        newPic = UIGraphicsGetImageFromCurrentImageContext();
    }
    
    return newPic;
}

#pragma mark - 截取视频方法

- (void)cropWithVideoUrlStr:(NSURL *)videoUrl start:(CGFloat)startTime end:(CGFloat)endTime completion:(void (^)(NSURL *outputURL, Float64 videoDuration, BOOL isSuccess))completionHandle {
    AVURLAsset *asset = [[AVURLAsset alloc] initWithURL:videoUrl options:nil];
    
    //获取视频总时长
    Float64 duration = CMTimeGetSeconds(asset.duration);
    
    if (duration > VIDEO_RECORDER_MAX_TIME) {
        duration = VIDEO_RECORDER_MAX_TIME;
    }
    
    startTime = 0;
    endTime = duration;
    
    NSString *outputFilePath = [self createVideoFilePath];
    NSURL *outputFileUrl = [NSURL fileURLWithPath:outputFilePath];
    
    NSArray *compatiblePresets = [AVAssetExportSession exportPresetsCompatibleWithAsset:asset];
    if ([compatiblePresets containsObject:AVAssetExportPresetHighestQuality]) {
        
        AVAssetExportSession *exportSession = [[AVAssetExportSession alloc]
                                               initWithAsset:asset presetName:AVAssetExportPresetHighestQuality];
        
        NSURL *outputURL = outputFileUrl;
        
        exportSession.outputURL = outputURL;
        exportSession.outputFileType = AVFileTypeMPEG4;
        exportSession.shouldOptimizeForNetworkUse = YES;
        
        CMTime start = CMTimeMakeWithSeconds(startTime, asset.duration.timescale);
        CMTime duration = CMTimeMakeWithSeconds(endTime - startTime,asset.duration.timescale);
        CMTimeRange range = CMTimeRangeMake(start, duration);
        exportSession.timeRange = range;
        
        [exportSession exportAsynchronouslyWithCompletionHandler:^{
            switch ([exportSession status]) {
                case AVAssetExportSessionStatusFailed: {
                    TIMKitLog(@"合成失败：%@", [[exportSession error] description]);
                    completionHandle(outputURL, endTime, NO);
                }
                    break;
                case AVAssetExportSessionStatusCancelled: {
                    completionHandle(outputURL, endTime, NO);
                }
                    break;
                case AVAssetExportSessionStatusCompleted: {
                    completionHandle(outputURL, endTime, YES);
                }
                    break;
                default: {
                    completionHandle(outputURL, endTime, NO);
                }
                    break;
            }
        }];
    }
}

#pragma mark - 预览视频通知
/**
 *  添加播放器通知
 */
-(void)addNotificationWithPlayerItem
{
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(playVideoFinished:) name:AVPlayerItemDidPlayToEndTimeNotification object:self.playerItem];
}

-(void)removePlayerItemNotification
{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

/**
 *  播放完成通知
 *
 *  @param notification 通知对象
 */
-(void)playVideoFinished:(NSNotification *)notification
{
    //    NSLog(@"视频播放完成.");
    
    // 播放完成后重复播放
    // 跳到最新的时间点开始播放
    [self.player seekToTime:CMTimeMake(0, 1)];
    [self.player play];
}

#pragma mark - AVCaptureVideoDataOutputSampleBufferDelegate AVCaptureAudioDataOutputSampleBufferDelegate

- (void)captureOutput:(AVCaptureOutput *)captureOutput didOutputSampleBuffer:(CMSampleBufferRef)sampleBuffer fromConnection:(AVCaptureConnection *)connection
{
    if (_isRotatingCamera)
    {
        return;
    }
    
    @autoreleasepool
    {
        //视频
        if (connection == [self.videoOutput connectionWithMediaType:AVMediaTypeVideo])
        {
            @synchronized(self)
            {
                if (_isShooting)
                {
                    [self appendSampleBuffer:sampleBuffer ofMediaType:AVMediaTypeVideo];
                }
            }
        }
        
        //音频
        if (connection == [self.audioOutput connectionWithMediaType:AVMediaTypeAudio])
        {
            @synchronized(self)
            {
                if (_isShooting)
                {
                    [self appendSampleBuffer:sampleBuffer ofMediaType:AVMediaTypeAudio];
                }
            }
        }
    }
}


/**
 *  开始写入数据
 */
- (void)appendSampleBuffer:(CMSampleBufferRef)sampleBuffer ofMediaType:(NSString *)mediaType {
    if (sampleBuffer == NULL || sampleBuffer == nil) {
        TIMKitLog(@"empty sampleBuffer");
        return;
    }
    
    if (sampleBuffer) {
        //    CFRetain(sampleBuffer);
        //    dispatch_async(self.videoQueue, ^{
                @autoreleasepool {
                    if (!self.canWrite && mediaType == AVMediaTypeVideo) {
                        [self.assetWriter startWriting];
                        [self.assetWriter startSessionAtSourceTime:CMSampleBufferGetPresentationTimeStamp(sampleBuffer)];
                        self.canWrite = YES;
                    }
                    
                    //写入视频数据
                    if (mediaType == AVMediaTypeVideo) {
                        if (self.assetWriterVideoInput.readyForMoreMediaData) {
                            BOOL success = [self.assetWriterVideoInput appendSampleBuffer:sampleBuffer];
                            if (!success) {
                                @synchronized (self) {
                                    [self stopVideoRecorder];
                                }
                            }
                        }
                    }
                    
                    //写入音频数据
                    if (mediaType == AVMediaTypeAudio) {
                        if (self.assetWriterAudioInput.readyForMoreMediaData) {
                            BOOL success = [self.assetWriterAudioInput appendSampleBuffer:sampleBuffer];
                            if (!success) {
                                @synchronized (self) {
                                    [self stopVideoRecorder];
                                }
                            }
                        }
                    }
        //            CFRelease(sampleBuffer);
                }
        //    });
    }else{
        TIMKitLog(@"sampleBuffer 不存在");

    }

}

#pragma mark - 摄像头聚焦，与缩放

/**
 *  添加点按手势
 */
- (void)addTapGenstureRecognizerForCamera {
    UIPinchGestureRecognizer *pinchGesture = [[UIPinchGestureRecognizer alloc] initWithTarget:self action:@selector(handlePinchGesture:)];
    pinchGesture.delegate = self;
    [self.viewContainer addGestureRecognizer:pinchGesture];
}

/**
 *  点击屏幕，聚焦事件
 */
- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    // 不聚焦的情况：聚焦中，旋转摄像头中，查看录制的视频中，查看照片中
    if (_isFocusing || touches.count == 0 || _isRotatingCamera || _videoPreviewContainerView || _photoPreviewImageView) {
        return;
    }
    
    UITouch *touch = nil;
    
    for (UITouch *t in touches) {
        touch = t;
        break;
    }
    
    CGPoint point = [touch locationInView:self.viewContainer];
    
    if (point.y > CGRectGetMaxY(self.tipLabel.frame)) {
        return;
    }
    
    [self setFocusCursorWithPoint:point];
}

/**
 *  设置聚焦光标位置
 *
 *  @param point 光标位置
 */
- (void)setFocusCursorWithPoint:(CGPoint)point {
    self.isFocusing = YES;
    
    self.focusImageView.center = point;
    self.focusImageView.transform = CGAffineTransformMakeScale(1.5, 1.5);
    self.focusImageView.alpha = 1;
    
    //将UI坐标转化为摄像头坐标
    CGPoint cameraPoint = [self.captureVideoPreviewLayer captureDevicePointOfInterestForPoint:point];
    [self focusWithPoint:cameraPoint];
    
    __weak typeof(self) weakSelf = self;
    [UIView animateWithDuration:1.0 animations:^{
        weakSelf.focusImageView.transform = CGAffineTransformIdentity;
    } completion:^(BOOL finished) {
        weakSelf.focusImageView.alpha = 0;
        weakSelf.isFocusing = NO;
    }];
}

/**
 *  设置聚焦点
 *
 *  @param point 聚焦点
 */
- (void)focusWithPoint:(CGPoint)point {
    [self changeDeviceProperty:^(AVCaptureDevice *captureDevice) {
         // 聚焦
         if ([captureDevice isFocusModeSupported:AVCaptureFocusModeContinuousAutoFocus]) {
             [captureDevice setFocusMode:AVCaptureFocusModeContinuousAutoFocus];
         }
         if ([captureDevice isFocusPointOfInterestSupported]) {
             [captureDevice setFocusPointOfInterest:point];
         }
         // 曝光
         if ([captureDevice isExposureModeSupported:AVCaptureExposureModeContinuousAutoExposure]) {
             [captureDevice setExposureMode:AVCaptureExposureModeContinuousAutoExposure];
         }
         if ([captureDevice isExposurePointOfInterestSupported]) {
             [captureDevice setExposurePointOfInterest:point];
         }
     }];
}

- (void)handlePinchGesture:(UIPinchGestureRecognizer *)recognizer {
    if (_isShooting) {
        return;
    }
    
    BOOL allTouchesAreOnTheCaptureVideoPreviewLayer = YES;
    
    NSUInteger numTouches = [recognizer numberOfTouches], i;
    for ( i = 0; i < numTouches; ++i) {
        CGPoint location = [recognizer locationOfTouch:i inView:self.viewContainer];
        CGPoint convertedLocation = [self.captureVideoPreviewLayer convertPoint:location fromLayer:self.captureVideoPreviewLayer.superlayer];
        if (![self.captureVideoPreviewLayer containsPoint:convertedLocation]) {
            allTouchesAreOnTheCaptureVideoPreviewLayer = NO;
            break;
        }
    }
    
    if (allTouchesAreOnTheCaptureVideoPreviewLayer) {
        CGFloat videoMaxZoomFactor = self.videoInput.device.activeFormat.videoMaxZoomFactor;
        CGFloat maxScaleAndCropFactor = videoMaxZoomFactor<DEFAULT_VIDEO_ZOOM_FACTOR?videoMaxZoomFactor:DEFAULT_VIDEO_ZOOM_FACTOR;
        CGFloat currentScale = self.beginGestureScale * recognizer.scale;
        if ((currentScale > 1.0f) && (currentScale < maxScaleAndCropFactor)) {
            self.effectiveScale = self.beginGestureScale * recognizer.scale;
            if ((self.effectiveScale < videoMaxZoomFactor) && (self.effectiveScale > 1.0f)) {
                [self changeDeviceProperty:^(AVCaptureDevice *captureDevice) {
                    [captureDevice rampToVideoZoomFactor:self.effectiveScale withRate:10.0f];
                }];
            }
        }
    }
}

#pragma mark - UIGestureRecognizerDelegate

- (BOOL)gestureRecognizerShouldBegin:(UIGestureRecognizer *)gestureRecognizer {
    if ([gestureRecognizer isKindOfClass:[UIPinchGestureRecognizer class]]) {
        self.beginGestureScale = self.effectiveScale;
    }
    return YES;
}

#pragma mark - 重力感应相关

/**
 *  开始监听屏幕方向
 */
- (void)startUpdateAccelerometer {
    if ([self.motionManager isAccelerometerAvailable] == YES) {
        //回调会一直调用,建议获取到就调用下面的停止方法，需要再重新开始，当然如果需求是实时不间断的话可以等离开页面之后再stop
        [self.motionManager setAccelerometerUpdateInterval:1.0];
        [self.motionManager startAccelerometerUpdatesToQueue:[NSOperationQueue currentQueue] withHandler:^(CMAccelerometerData *accelerometerData, NSError *error) {
             double x = accelerometerData.acceleration.x;
             double y = accelerometerData.acceleration.y;
             if ((fabs(y) + 0.1f) >= fabs(x)) {
                 //                 NSLog(@"y:%lf", y);
                 if (y >= 0.1f) {
                     // Down
                     TIMKitLog(@"Down");
                     _shootingOrientation = UIDeviceOrientationPortraitUpsideDown;
                 } else {
                     // Portrait
                     TIMKitLog(@"Portrait");
                     self->_shootingOrientation = UIDeviceOrientationPortrait;
                 }
             } else {
                 //                 NSLog(@"x:%lf", x);
                 if (x >= 0.1f) {
                     // Right
                     TIMKitLog(@"Right");
                     self->_shootingOrientation = UIDeviceOrientationLandscapeRight;
                 } else if (x <= 0.1f) {
                     // Left
                     TIMKitLog(@"Left");
                     self->_shootingOrientation = UIDeviceOrientationLandscapeLeft;
                 } else {
                     // Portrait
                     TIMKitLog(@"Portrait");
                     self->_shootingOrientation = UIDeviceOrientationPortrait;
                 }
             }
         }];
    }
}

/**
 *  停止监听屏幕方向
 */
- (void)stopUpdateAccelerometer {
    if ([self.motionManager isAccelerometerActive] == YES) {
        [self.motionManager stopAccelerometerUpdates];
        _motionManager = nil;
    }
}

#pragma mark - 判断是否有权限

/**
 *  请求权限
 */
- (void)requestAuthorizationForVideo {
    __weak typeof(self) weakSelf = self;
    
    // 请求相机权限
    AVAuthorizationStatus videoAuthStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeVideo];
    if (videoAuthStatus != AVAuthorizationStatusAuthorized) {
        NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
        
        NSString *appName = [infoDictionary objectForKey:@"CFBundleDisplayName"];
        if (appName == nil) {
            appName = @"APP";
        }
        NSString *message = [NSString stringWithFormat:@"允许%@访问你的相机？", appName];
        
        UIAlertController *alertController = [UIAlertController alertControllerWithTitle:@"警告" message:message preferredStyle:UIAlertControllerStyleAlert];
        
        UIAlertAction *okAction = [UIAlertAction actionWithTitle:@"好的" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
            [weakSelf dismissViewControllerAnimated:YES completion:nil];
        }];
        
        UIAlertAction *setAction = [UIAlertAction actionWithTitle:@"设置" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
            NSURL *url = [NSURL URLWithString:UIApplicationOpenSettingsURLString];
            if ([[UIApplication sharedApplication] canOpenURL:url]) {
                [[UIApplication sharedApplication] openURL:url];
            }
            [weakSelf dismissViewControllerAnimated:YES completion:nil];
        }];
        
        [alertController addAction:okAction];
        [alertController addAction:setAction];
        
        [self presentViewController:alertController animated:YES completion:nil];
    }
    
    // 请求麦克风权限
    AVAuthorizationStatus audioAuthStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeAudio];
    if (audioAuthStatus != AVAuthorizationStatusAuthorized) {
        NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
        
        NSString *appName = [infoDictionary objectForKey:@"CFBundleDisplayName"];
        if (appName == nil) {
            appName = @"APP";
        }
        NSString *message = [NSString stringWithFormat:@"允许%@访问你的麦克风？", appName];
        
        UIAlertController *alertController = [UIAlertController alertControllerWithTitle:@"警告" message:message preferredStyle:UIAlertControllerStyleAlert];
        
        UIAlertAction *okAction = [UIAlertAction actionWithTitle:@"好的" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
            [weakSelf dismissViewControllerAnimated:YES completion:nil];
        }];
        
        UIAlertAction *setAction = [UIAlertAction actionWithTitle:@"设置" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
            NSURL *url = [NSURL URLWithString:UIApplicationOpenSettingsURLString];
            if ([[UIApplication sharedApplication] canOpenURL:url]) {
                [[UIApplication sharedApplication] openURL:url];
            }
            [weakSelf dismissViewControllerAnimated:YES completion:nil];
        }];
        
        [alertController addAction:okAction];
        [alertController addAction:setAction];
        
        [self presentViewController:alertController animated:YES completion:nil];
    }
}

- (void)requestAuthorizationForPhotoLibrary {
    __weak typeof(self) weakSelf = self;
    
    // 请求照片权限
    [TIMXFPhotoLibraryManager requestALAssetsLibraryAuthorizationWithCompletion:^(Boolean isAuth) {
        
        if (!isAuth) {
            __strong typeof(weakSelf) strongSelf = weakSelf;
            dispatch_async(dispatch_get_main_queue(), ^{
                NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
                
                NSString *appName = [infoDictionary objectForKey:@"CFBundleDisplayName"];
                if (appName == nil) {
                    appName = @"APP";
                }
                NSString *message = [NSString stringWithFormat:@"允许%@访问你的相册？", appName];
                
                UIAlertController *alertController = [UIAlertController alertControllerWithTitle:@"警告" message:message preferredStyle:UIAlertControllerStyleAlert];
                
                UIAlertAction *okAction = [UIAlertAction actionWithTitle:@"好的" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
                    [strongSelf dismissViewControllerAnimated:YES completion:nil];
                }];
                
                UIAlertAction *setAction = [UIAlertAction actionWithTitle:@"设置" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
                    NSURL *url = [NSURL URLWithString:UIApplicationOpenSettingsURLString];
                    if ([[UIApplication sharedApplication] canOpenURL:url]) {
                        [[UIApplication sharedApplication] openURL:url];
                    }
                    [strongSelf dismissViewControllerAnimated:YES completion:nil];
                }];
                
                [alertController addAction:okAction];
                [alertController addAction:setAction];
                
                [strongSelf presentViewController:alertController animated:YES completion:nil];
            });
        }
    }];
}

// 打开/关闭手电筒
- (void)turnTorchOn:(BOOL)on {
    AVCaptureDevice *device = [self.videoInput device];
    if ([device hasTorch] && [device hasFlash]){

        [device lockForConfiguration:nil];
        if (on) {
            [device setTorchMode:AVCaptureTorchModeOn];
        } else {
            [device setTorchMode:AVCaptureTorchModeOff];
        }
        [device unlockForConfiguration];
    } else {
        NSLog(@"当前设备没有闪光灯，不能提供手电筒功能");
    }
}

@end
