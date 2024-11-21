//
//  TIMSYCacheFileAudio.h
//  zhangshaoyu
//
//  Created by 侯力 on 2024/3/19.
//  Copyright © 2019年 侯力. All rights reserved.
//  音频播放

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

static NSString *const TIMSYCacheFileAudioDurationValueChangeNotificationName = @"AudioDurationValueChangeNotificationName";
static NSString *const TIMSYCacheFileAudioStopNotificationName = @"AudioStopNotificationName";
static NSString *const TIMSYCacheFileAudioDeleteNotificationName = @"AudioDeleteNotificationName";

@interface TIMSYCacheFileAudio : NSObject

+ (instancetype)shareAudio;

- (void)playAudioWithFilePath:(NSString *)filePath;

- (void)stopAudio;

@end

NS_ASSUME_NONNULL_END
