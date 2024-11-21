//
//  TIMSYCacheFileVideo.h
//  zhangshaoyu
//
//  Created by 侯力 on 2024/3/19.
//  Copyright © 2019年 侯力. All rights reserved.
//  视频播放

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMSYCacheFileVideo : NSObject

/// 视频播放（根据路径）
- (void)playVideoWithFilePath:(NSString *)filePath target:(id)target;

@end

NS_ASSUME_NONNULL_END
