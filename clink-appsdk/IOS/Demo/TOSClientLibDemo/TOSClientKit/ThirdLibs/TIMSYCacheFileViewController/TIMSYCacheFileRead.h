//
//  TIMSYCacheFileRead.h
//  zhangshaoyu
//
//  Created by 侯力 on 2024/3/19.
//  Copyright © 2017年 侯力. All rights reserved.
//  文件浏览

#import <Foundation/Foundation.h>

@interface TIMSYCacheFileRead : NSObject

/**
 *  文件阅读：图片浏览、文档查看、音视频播放
 *
 *  @param filePath 文件路径
 *  @param target   UIViewController
 */
- (void)fileReadWithFilePath:(NSString *)filePath target:(id)target;

/**
 *  内存释放
 */
- (void)releaseSYCacheFileRead;

@end
