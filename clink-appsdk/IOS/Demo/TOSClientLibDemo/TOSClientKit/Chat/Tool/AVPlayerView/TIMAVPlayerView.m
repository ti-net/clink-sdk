//
//  TIMAVPlayerView.m
//  TIMClientKit
//
//  Created by 李成 on 2022/5/19.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "TIMAVPlayerView.h"
#import "TIMConstants.h"


static void * TIMVideoPlayerStatusContext = &TIMVideoPlayerStatusContext;

static void * TIMVideoPlayer_PlaybackLikelyToKeepUp = &TIMVideoPlayer_PlaybackLikelyToKeepUp;
static void * TIMVideoPlayer_PlaybackBufferEmpty = &TIMVideoPlayer_PlaybackBufferEmpty;
static void * TIMVideoPlayerLoadedTimeRangesContext = &TIMVideoPlayerLoadedTimeRangesContext;

NSString * const TIMVideoPlayerErrorDomain = @"TIMVideoPlayerErrorDomain";


@interface TIMAVPlayerView ()

/// 播放时为YES 暂停时为NO
@property (nonatomic, assign) BOOL isPlaying;

/// 播放属性
@property (nonatomic, strong) AVPlayer      *player;
@property (nonatomic, strong) AVPlayerItem  *item;
@property (nonatomic, strong) AVURLAsset    *urlAsset;
@property (nonatomic, strong) AVPlayerLayer  *playerLayer;

@property (nonatomic, strong) id timeObserverToken;

/// 播放暂停按钮
@property (nonatomic, strong) UIButton                * playButton;
/// 返回按钮
@property (nonatomic, strong) UIButton                * backButton;
/// 进度条
@property (nonatomic, strong) UISlider                * progressView;

@end


@implementation TIMAVPlayerView

- (instancetype)initWithCoder:(NSCoder *)aDecoder{
    self =[super initWithCoder:aDecoder];
    if (self) {
        
        [self setupAudioSession];
    }
    return self;
}

- (instancetype)initWithFrame:(CGRect)frame{
    self =[super initWithFrame:frame];
    if (self) {
        
        [self setupAudioSession];
        [self addSubview:self.playButton];
        [self addSubview:self.backButton];
        [self addSubview:self.progressView];
        
    }
    return self;
}

- (void)setupAudioSession {
    
    NSError *categoryError = nil;
    BOOL success = [[AVAudioSession sharedInstance] setCategory:AVAudioSessionCategoryPlayback error:&categoryError];
    if (!success) {
        NSLog(@"Error setting audio session category: %@", categoryError);
    }
    
    NSError *activeError = nil;
    success = [[AVAudioSession sharedInstance] setActive:YES error:&activeError];
    if (!success) {
        NSLog(@"Error setting audio session active: %@", activeError);
    }
}


/// 视频适配模式
- (NSString *)videoGravity {
    if(!_videoGravity) {
        _videoGravity =AVLayerVideoGravityResizeAspect;
    }
    return _videoGravity;
}

- (void)layoutSubviews{
    [super layoutSubviews];
    
    if (self.playerLayer) {
    
        self.playerLayer.frame =self.bounds;
        
    }
    
}

/// 跳到xx秒播放视频
- (void)seekToTime:(CGFloat )time {
    
    if (self.player) {
        [self.player.currentItem cancelPendingSeeks];
        CMTime cmTime =CMTimeMakeWithSeconds(time, 1);
        if (CMTIME_IS_INVALID(cmTime) || self.player.currentItem.status != AVPlayerStatusReadyToPlay) {
            return;
        }
        
        [self.player seekToTime:cmTime];
        
//        [self.player seekToTime:cmTime toleranceBefore:CMTimeMake(1,1)  toleranceAfter:CMTimeMake(1,1) completionHandler:^(BOOL finished) {
//            
//            
//        }];
        
   }
    
}



/// 设置播放URL
- (void)setURL:(NSURL *)URL {
    //如果有正在播放的视频 先释放掉
    [self resetPlayerItemIfNecessary];
    self.urlAsset =[AVURLAsset assetWithURL:URL];
    [self creatPlayerWithAsset:self.urlAsset];
    
}

/// 直接赋值AVURLAsset
- (void)setAsset:(AVURLAsset *)asset {
    
    //如果有正在播放的视频 先释放掉
    [self resetPlayerItemIfNecessary];
    
    [self creatPlayerWithAsset:asset];
    
    
}

- (void)creatPlayerWithAsset:(AVURLAsset *)urlAsset {
    
    /// 初始化playerItem
//    self.item =[AVPlayerItem playerItemWithAsset:urlAsset];
    self.item = [AVPlayerItem playerItemWithURL:urlAsset.URL];
    
    if (@available(iOS 10.0, *)) {
        self.item.preferredForwardBufferDuration =10.f;
    } else {
        // Fallback on earlier versions
    }
    /// 判断item是否存在
    if(!self.item){
        [self reportUnableToCreatePlayerItem];
        return;
    }
    
    /// 每次都重新创建Player，替换replaceCurrentItemWithPlayerItem:，该方法阻塞线程
    self.player =[AVPlayer playerWithPlayerItem:self.item];
    self.player.actionAtItemEnd = AVPlayerActionAtItemEndNone;
    self.playerLayer =[AVPlayerLayer playerLayerWithPlayer:self.player];
    
    /// 此处为默认视频填充模式
    self.playerLayer.videoGravity = self.videoGravity;
    
    [self setNeedsLayout];
    [self layoutIfNeeded];
    
    /// 添加playerLayer到self.layer
    [self.layer insertSublayer:self.playerLayer atIndex:0];
    
    /// 添加播放时间观察
    [self enableTimeUpdates];
    /// 添加观察
    [self preparePlayerItem:self.item];
    
}
/// 添加kvo监听
- (void)preparePlayerItem:(AVPlayerItem *)playerItem {
    [self addPlayerItemObservers:playerItem];
}

- (void)addPlayerItemObservers:(AVPlayerItem *)playerItem {
    [playerItem addObserver:self
                 forKeyPath:@"status"
                    options:NSKeyValueObservingOptionInitial | NSKeyValueObservingOptionOld | NSKeyValueObservingOptionNew
                    context:TIMVideoPlayerStatusContext];
    
    [playerItem addObserver:self
                 forKeyPath:@"playbackLikelyToKeepUp"
                    options:NSKeyValueObservingOptionNew
                    context:TIMVideoPlayer_PlaybackLikelyToKeepUp];
    
    [playerItem addObserver:self
                 forKeyPath:@"playbackBufferEmpty"
                    options:NSKeyValueObservingOptionNew
                    context:TIMVideoPlayer_PlaybackBufferEmpty];
    
    [playerItem addObserver:self
                 forKeyPath:@"loadedTimeRanges"
                    options:NSKeyValueObservingOptionNew
                    context:TIMVideoPlayerLoadedTimeRangesContext];
    
    /// 播放完毕的通知
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(playerItemDidPlayToEndTime:)
                                                 name:AVPlayerItemDidPlayToEndTimeNotification
                                               object:nil];
    
    /// 耳机插入和拔掉通知
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(audioRouteChangeListenerCallback:) name:AVAudioSessionRouteChangeNotification object:nil];
    
    
}

/// 播放结束
- (void)playerItemDidPlayToEndTime:(NSNotification *)notification {
    
    self.progressView.value = 1.0;
    [self.player seekToTime:kCMTimeZero];
    self.playButton.selected = NO;
    [self pause];
    
    if ([self.delegate respondsToSelector:@selector(videoPlayerDidReachEnd:)]) {
        [self.delegate videoPlayerDidReachEnd:self];
    }
    
}

/// 耳机插入、拔出事件
 - (void)audioRouteChangeListenerCallback:(NSNotification*)notification {
    NSDictionary *interuptionDict = notification.userInfo;
    
    NSInteger routeChangeReason = [[interuptionDict valueForKey:AVAudioSessionRouteChangeReasonKey] integerValue];
    
    switch (routeChangeReason) {
            
        case AVAudioSessionRouteChangeReasonNewDeviceAvailable:
            // 耳机插入
            break;
            
        case AVAudioSessionRouteChangeReasonOldDeviceUnavailable: {
            // 拔掉耳机继续播放
            if (self.isPlaying) {
                [self.player play];
            }
            break;
        }
        case AVAudioSessionRouteChangeReasonCategoryChange:
            // called at start - also when other audio wants to play
            
            break;
    }
}


/// 移除player属性观察
- (void)removePlayerItemObservers:(AVPlayerItem *)playerItem {
    [playerItem cancelPendingSeeks];
    
    [playerItem removeObserver:self forKeyPath:@"status" context:TIMVideoPlayerStatusContext];
    [playerItem removeObserver:self forKeyPath:@"loadedTimeRanges" context:TIMVideoPlayerLoadedTimeRangesContext];
    [playerItem removeObserver:self forKeyPath:@"playbackBufferEmpty" context:TIMVideoPlayer_PlaybackBufferEmpty];
    [playerItem removeObserver:self forKeyPath:@"playbackLikelyToKeepUp" context:TIMVideoPlayer_PlaybackLikelyToKeepUp];
    
    [[NSNotificationCenter defaultCenter] removeObserver:self name:AVPlayerItemDidPlayToEndTimeNotification object:nil];
    
    [[NSNotificationCenter defaultCenter] removeObserver:self name:AVAudioSessionRouteChangeNotification object:nil];
}


/// 播放时间 观察
- (void)addTimeObserver {
    
    if (self.timeObserverToken || self.player == nil) {
        return;
    }
    
    __weak typeof (self) weakSelf = self;
    /// 设置监听播放进度
    self.timeObserverToken = [self.player addPeriodicTimeObserverForInterval:CMTimeMakeWithSeconds(1.0, 1.0) queue:dispatch_get_main_queue() usingBlock:^(CMTime time) {
        __strong typeof (weakSelf) strongSelf = weakSelf;
        if (!strongSelf) {
            return;
        }
        NSLog(@"当前的播放时间：%lf", (CGFloat)CMTimeGetSeconds(time));
        /// 添加监听后，当前总时长可能是nan，拿不到值，所以需要做一下判断总时长是否大于0
        if ([strongSelf getTotalPlayTime] > 0) {
            /// 更新进度条
            strongSelf.progressView.value = (CGFloat)CMTimeGetSeconds(time)/[strongSelf getTotalPlayTime];
        }
        
        NSLog(@"总时长：%lf,  当前：%lf", [strongSelf getTotalPlayTime], [strongSelf getCurrentPlayTime]);
        
        if ([strongSelf.delegate respondsToSelector:@selector(videoPlayer:timeDidChange:)]) {
            [strongSelf.delegate videoPlayer:strongSelf timeDidChange:CMTimeGetSeconds(time)];
        }
        
    }];
    
    
    
}

/// 移除时间观察
- (void)removeTimeObserver {
    if (!self.timeObserverToken) {
        return;
    }
    if (self.player) {
        [self.player removeTimeObserver:self.timeObserverToken];
    }
    
    self.timeObserverToken = nil;
}
/// 开始监听播放时间
- (void)enableTimeUpdates {
    
    [self addTimeObserver];
    
    
}

/// 关闭监听播放时间
- (void)disableTimeUpdates {
    
    [self removeTimeObserver];
    
}

/// 加载播放失败报错
- (void)reportUnableToCreatePlayerItem{
    
    if ([self.delegate respondsToSelector:@selector(videoPlayer:didFailWithError:)]) {
        /// 报错信息
        NSError *error = [NSError errorWithDomain:TIMVideoPlayerErrorDomain
                                             code:100
                                         userInfo:@{NSLocalizedDescriptionKey : @"Unable to create AVPlayerItem."}];
        [self.delegate videoPlayer:self didFailWithError:error];
    }
    
}


/// 释放
- (void)resetPlayerItemIfNecessary {
    
    if (self.item) {
        [self removePlayerItemObservers:self.item];
    }
    
    /// 移除时间观察
    [self disableTimeUpdates];
    
    if (self.playerLayer) {
         [self.playerLayer removeFromSuperlayer];
    }
    if (self.item){
        self.item = nil;
    }
    
    if (self.player) {
        [self.player replaceCurrentItemWithPlayerItem:nil];
        self.player = nil;
    }
    
    if (self.urlAsset) {
        self.urlAsset = nil;
    }
    
    if (self.playerLayer) {
        self.playerLayer = nil;
    }
    
}

/// 播放
- (void)play {
    
    self.isPlaying = YES;
    self.playButton.selected = YES;
    self.player.rate = 1.0;
    [self.player play];
    [self addTimeObserver];
    
//    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.3 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
//        if ([self getTotalPlayTime] &&
//            [self getTotalPlayTime] >= 0) {
//            NSLog(@"%f",[self getTotalPlayTime]);
//            self.progressView.maximumValue = [self getTotalPlayTime];
//        }
//    });
    
}

/// 暂停
- (void)pause {
    
    if (self.isPlaying) {
        self.isPlaying =NO;
        [self.player pause];
        [self removeTimeObserver];
    }
    
}

/// 停止播放
- (void)stop {
    
    [self.player pause];
    /// item置为nil相关
    [self resetPlayerItemIfNecessary];
    
}

/// 获取当前播放的时间
- (CGFloat )getCurrentPlayTime {
    if(self.player) {
        return CMTimeGetSeconds([self.player currentTime]);
    }
    return 0.0f;
}

/// 获取视频的总时间长
- (CGFloat)getTotalPlayTime {
    if (self.player) {
        return CMTimeGetSeconds(self.player.currentItem.duration);
    }
    return 0.0f;
}

/// 获取视频宽高比
- (CGFloat )getVideoScale:(NSURL *)URL {
    if (!URL) return 0.0f;
    /// 获取视频尺寸
    AVURLAsset *asset = [AVURLAsset assetWithURL:URL];
    NSArray *array = asset.tracks;
    CGSize videoSize = CGSizeZero;
    for (AVAssetTrack *track in array) {
        if ([track.mediaType isEqualToString:AVMediaTypeVideo]) {
            videoSize = track.naturalSize;
        }
    }
    return videoSize.height/videoSize.width;
}

#pragma mark - Observer Response
- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary *)change context:(void *)context {
    /// player状态
    if (context == TIMVideoPlayerStatusContext) {
        AVPlayerStatus newStatus = [[change objectForKey:NSKeyValueChangeNewKey] integerValue];
        AVPlayerStatus oldStatus = [[change objectForKey:NSKeyValueChangeOldKey] integerValue];
        
        if (newStatus != oldStatus) {
            /*
                 * AVPlayerItem 的三种状态
                 * AVPlayerItemStatusUnknown
                 * AVPlayerItemStatusReadyToPlay
                 * AVPlayerItemStatusFailed
                */
            switch (newStatus) {
                case AVPlayerItemStatusUnknown: {
                    NSLog(@"播放器状态未知");
                    break;
                }
                case AVPlayerItemStatusReadyToPlay: {
                    /// 视频可播放／播放中
                    if ([self.delegate respondsToSelector:@selector(videoPlayerIsReadyToPlayVideo:)]) {
                        dispatch_async(dispatch_get_main_queue(), ^{
                            [self.delegate videoPlayerIsReadyToPlayVideo:self];
                      });
                    }
                    break;
                }
                case AVPlayerStatusFailed: {
                    NSError *error = [NSError errorWithDomain:TIMVideoPlayerErrorDomain code:100 userInfo:@{NSLocalizedDescriptionKey : @"未知播放错误, status == AVPlayerItemStatusFailed"}];
                        if ([self.delegate respondsToSelector:@selector(videoPlayer:didFailWithError:)]) {
                            dispatch_async(dispatch_get_main_queue(), ^{
                                [self.delegate videoPlayer:self didFailWithError:error];
                            });
                        }
                    }
                    break;
                }
        }
        
    }
    else if (context == TIMVideoPlayer_PlaybackBufferEmpty) {
        /// 缓冲为空
        if ([keyPath isEqualToString:@"playbackBufferEmpty"]) {
            if (self.player.currentItem.playbackBufferEmpty) {
                if ([self.delegate respondsToSelector:@selector(videoPlayerPlaybackBufferEmpty:)]) {
                    dispatch_async(dispatch_get_main_queue(), ^{
                        [self.delegate videoPlayerPlaybackBufferEmpty:self];
                    });
                }
            }
        }
        
    }
    else if (context == TIMVideoPlayer_PlaybackLikelyToKeepUp) {
        /// 是否可能在不暂停的情况下继续播放
        if([keyPath isEqualToString:@"playbackLikelyToKeepUp"]) {
            
            if (self.player.currentItem.playbackLikelyToKeepUp){
                if ([self.delegate respondsToSelector:@selector(videoPlayerPlaybackLikelyToKeepUp:)]) {
                    dispatch_async(dispatch_get_main_queue(), ^{
                        [self.delegate videoPlayerPlaybackLikelyToKeepUp:self];
                    });
                }
            }
        }
        
    }
    else if (context == TIMVideoPlayerLoadedTimeRangesContext) {
        /// 缓冲的时间范围
        if ([keyPath isEqualToString:@"loadedTimeRanges"]) {
            if ([self.delegate respondsToSelector:@selector(videoPlayer:loadedTimeRangeDidChange:)]) {
                CGFloat loadedDuration =[self calcLoadedDuration];
                dispatch_async(dispatch_get_main_queue(), ^{
                    [self.delegate videoPlayer:self loadedTimeRangeDidChange:loadedDuration];
                });
            }
        }
    }
    else {
        /// 其他的情况不做处理
        [super observeValueForKeyPath:keyPath ofObject:object change:change context:context];
        
    }
    
}
/// 当前缓冲的长度
- (CGFloat )calcLoadedDuration {
    CGFloat loadedDuration = 0.0f;
    
    if (self.player && self.player.currentItem) {
        NSArray *loadedTimeRanges = self.player.currentItem.loadedTimeRanges;
        
        if (loadedTimeRanges && [loadedTimeRanges count]) {
            CMTimeRange timeRange = [[loadedTimeRanges firstObject] CMTimeRangeValue];
            CGFloat startSeconds = CMTimeGetSeconds(timeRange.start);
            CGFloat durationSeconds = CMTimeGetSeconds(timeRange.duration);
            
            loadedDuration = startSeconds + durationSeconds;
        }
    }
    
    return loadedDuration;
}

-(void)dealloc{
    NSLog(@"TIMPlayerView销毁了");
    [self resetPlayerItemIfNecessary];
}

#pragma mark - action
/// 返回按钮点击事件
- (void)backTouch {

    if (self.delegate && [self.delegate respondsToSelector:@selector(videoPlayerWithBackTouch:)]) {
        [self stop];
        dispatch_async(dispatch_get_main_queue(), ^{
            [self.delegate videoPlayerWithBackTouch:self];
        });

    }

}

/// 播放/暂停按钮点击事件
- (void)playButtonTouch:(UIButton *)sender {
    
    self.playButton.selected = !self.playButton.selected;
    if (self.playButton.selected) {
        
        NSLog(@"进度条的值：%lf", self.progressView.value);
        if (self.progressView.value == self.progressView.maximumValue) {
//            self.player.rate = 1.0f;
//            [self seekToTime:0.0f];
//            [self.player play];
//            self.isPlaying = YES;
//            self.player.rate = 1.0;
//            [self.player seekToTime:kCMTimeZero];
//            [self.player play];
            NSLog(@"重新播放");
        }
        else {
            NSLog(@"播放");
            
        }
        [self play];
        
    }
    else {
        NSLog(@"暂停");
        [self pause];
        
        
    }
    
}


//处理
- (void)sliderValueChanged:(UISlider*)slider forEvent:(UIEvent*)event {
    UITouch*touchEvent = event.allTouches.allObjects[0];

    switch (touchEvent.phase) {
        case UITouchPhaseBegan:
            NSLog(@"开始拖动");
            break;
        case UITouchPhaseMoved:
            NSLog(@"正在拖动");
            NSLog(@"拖动值：%lf", slider.value);
            break;
        case UITouchPhaseEnded:
            NSLog(@"结束拖动");
            NSLog(@"拖动值：%lf", slider.value);
            
            self.isPlaying = YES;
            self.progressView.value = slider.value;
            [self removeTimeObserver];
            self.player.rate = 1.0f;
            [self seekToTime:slider.value*[self getTotalPlayTime]];
            [self addTimeObserver];
            self.playButton.selected = YES;
            
            break;
        default:
            break;
    }
}


#pragma mark - 懒加载

- (UIButton *)playButton {
    if (!_playButton) {
        _playButton = [[UIButton alloc] initWithFrame:(CGRectMake(10, self.bounds.size.height - 120 - kBottomBarHeight, 40, 40))];
        [_playButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@", FRAMEWORKS_BUNDLE_PATH, @"videoPause"]] forState:(UIControlStateSelected)];
        [_playButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@", FRAMEWORKS_BUNDLE_PATH, @"videoPlay"]] forState:(UIControlStateNormal)];
        [_playButton addTarget:self action:@selector(playButtonTouch:) forControlEvents:(UIControlEventTouchUpInside)];
        
    }
    return _playButton;
}

- (UIButton *)backButton {
    if (!_backButton) {
        _backButton = [[UIButton alloc] initWithFrame:(CGRectMake(10, APP_Frame_Height-60 - kBottomBarHeight, 40, 40))];
//        [_backButton setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"navi_back"]] forState:UIControlStateNormal];
        [_backButton setBackgroundImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"videoClose"]] forState:(UIControlStateNormal)];
        [_backButton addTarget:self action:@selector(backTouch) forControlEvents:(UIControlEventTouchUpInside)];

    }
    return _backButton;
}

- (UISlider *)progressView {
    if (!_progressView) {
        _progressView = [[UISlider alloc] initWithFrame:(CGRectMake(60, self.bounds.size.height - 115 - kBottomBarHeight, self.bounds.size.width-110, 30))];
        _progressView.tintColor = UIColor.blueColor;
        _progressView.value = 0.0f;
        [_progressView addTarget:self action:@selector(sliderValueChanged:forEvent:) forControlEvents:(UIControlEventValueChanged)];
        _progressView.minimumValue = 0.0f;
        _progressView.maximumValue = 1.0f;
        
    }
    return _progressView;
}


/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
