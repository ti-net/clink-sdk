//
//  TIMSYCacheFileModel.m
//  zhangshaoyu
//
//  Created by zhangshaoyu on 17/6/28.
//  Copyright © 2017年 zhangshaoyu. All rights reserved.
//

#import "TIMSYCacheFileModel.h"
#import "TIMSYCacheFileManager.h"

@implementation TIMSYCacheFileModel

- (NSString *)fileSize
{
    if (self.fileType == TIMSYCacheFileTypeUnknow) {
        return [TIMSYCacheFileManager fileSizeTotalStringWithDirectory:self.filePath];
    }
    return [TIMSYCacheFileManager fileSizeStringWithFilePath:self.filePath];
}

- (double)originalFileSize
{
    return [TIMSYCacheFileManager  fileSizeNumberWithFilePath:self.filePath];
}

- (TIMSYCacheFileType)fileType
{
    return [[TIMSYCacheFileManager shareManager] fileTypeReadWithFilePath:self.filePath];
}

@end
