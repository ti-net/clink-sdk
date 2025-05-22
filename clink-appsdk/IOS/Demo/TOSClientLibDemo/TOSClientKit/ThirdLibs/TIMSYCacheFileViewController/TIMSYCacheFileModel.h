//
//  TIMSYCacheFileModel.h
//  zhangshaoyu
//
//  Created by 侯力 on 2024/3/19.
//  Copyright © 2017年 侯力. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMSYCacheFileDefine.h"

@interface TIMSYCacheFileModel : NSObject

/// 文件路径
@property (nonatomic, strong) NSString *filePath;
/// 文件名称
@property (nonatomic, strong) NSString *fileName;
/// 文件大小
@property (nonatomic, strong) NSString *fileSize;

/// 原始文件大小
@property (nonatomic, assign) double originalFileSize;

/// 文件类型（1视频、2音频、3图片、4文档）
@property (nonatomic, assign) TIMSYCacheFileType fileType;
/// 音频文件进度
@property (nonatomic, assign) float fileProgress;
/// 音频文件进度显示
@property (nonatomic, assign) BOOL fileProgressShow;

@end
