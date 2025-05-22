//
//  TIMAVPlayerView.h
//  TIMClientKit
//
//  Created by 李成 on 2022/5/19.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>

NS_ASSUME_NONNULL_BEGIN
@class TIMAVPlayerView;
@protocol TIMAVPlayerViewDelegate <NSObject>

@optional

/// 可播放／播放中
- (void)videoPlayerIsReadyToPlayVideo:(TIMAVPlayerView *)playerView;
/// 播放完毕
- (void)videoPlayerDidReachEnd:(TIMAVPlayerView *)playerView;
/// 当前播放时间
- (void)videoPlayer:(TIMAVPlayerView *)playerView timeDidChange:(CGFloat )time;
/// duration 当前缓冲的长度
- (void)videoPlayer:(TIMAVPlayerView *)playerView loadedTimeRangeDidChange:(CGFloat )duration;
/// 进行跳转后没数据 即播放卡顿
- (void)videoPlayerPlaybackBufferEmpty:(TIMAVPlayerView *)playerView;
/// 进行跳转后有数据 能够继续播放
- (void)videoPlayerPlaybackLikelyToKeepUp:(TIMAVPlayerView *)playerView;
/// 加载失败
- (void)videoPlayer:(TIMAVPlayerView *)playerView didFailWithError:(NSError *)error;
/// 点击显示操作按钮
- (void)videoPlayerWithBackTouch:(TIMAVPlayerView *)playerView;

@end


@interface TIMAVPlayerView : UIView

/// 视频显示的模式
@property (nonatomic, copy) NSString                * videoGravity;

/// 播放时为YES 暂停时为NO
@property (nonatomic, assign,readonly) BOOL isPlaying;

/// 播放属性
@property (nonatomic, strong,readonly) AVPlayer      *player;
@property (nonatomic, strong,readonly) AVPlayerItem  *item;
@property (nonatomic, strong,readonly) AVURLAsset    *urlAsset;
@property (nonatomic, strong,readonly) AVPlayerLayer  *playerLayer;
/// 代理
@property (nonatomic,weak) id<TIMAVPlayerViewDelegate> delegate;

/// 设置播放URL
- (void)setURL:(NSURL *)URL;

- (void)setAsset:(AVURLAsset *)asset;

/// 跳到xx秒播放视频
- (void)seekToTime:(CGFloat )time;

/// 播放
- (void)play;

/// 暂停
- (void)pause;

/// 停止播放
- (void)stop;

/// 获取当前播放的时间
- (CGFloat )getCurrentPlayTime;

/// 获取视频的总时间长
- (CGFloat)getTotalPlayTime;

/// 获取视频宽高比
- (CGFloat )getVideoScale:(NSURL *)URL;

@end

NS_ASSUME_NONNULL_END
