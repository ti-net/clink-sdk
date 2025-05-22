//
//  SYCacheFileDefine.h
//  zhangshaoyu
//
//  Created by 侯力 on 2024/3/19.
//  Copyright © 2017年 侯力. All rights reserved.
//  https://github.com/potato512/SYCacheFileViewController

#ifndef TIMSYCacheFileDefine_h
#define TIMSYCacheFileDefine_h

static NSString *const TIMSYCacheFileTitle = @"缓存文件";

/**
 *  默认显示文件
 *  视频：.avi、.dat、.mkv、.flv、.vob、.mp4、.m4v、.mpg、.mpeg、.mpe、.3pg、.mov、.swf、.wmv、.asf、.asx、.rm、.rmvb
 *  音频：.wav、.aif、.au、.mp3、.ram、.wma、.mmf、.amr、.aac、.flac、.midi、.mp3、.oog、.cd、.asf、.rm、.real、.ape、.vqf
 *  图片：.jpg、.png、.jpeg、.gif、.bmp
 *  文档：.txt、.sh、.doc、.docx、.xls、.xlsx、.pdf、.hlp、.wps、.rtf、.html、@".htm", .iso、.rar、.zip、.exe、.mdf、.ppt、.pptx、.apk
 */

/// 文件类型
typedef NS_ENUM(NSInteger, TIMSYCacheFileType)
{
    /// 文件类型 0未知
    TIMSYCacheFileTypeUnknow = 0,
    
    /// 文件类型 1视频
    TIMSYCacheFileTypeVideo = 1,
    
    /// 文件类型 2音频
    TIMSYCacheFileTypeAudio = 2,
    
    /// 文件类型 3图片
    TIMSYCacheFileTypeImage = 3,
    
    /// 文件类型 4文档
    TIMSYCacheFileTypeDocument = 4,
};

#endif /* SYCacheFileDefine_h */
