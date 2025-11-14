//
//  TIMSYCacheFileRead.m
//  zhangshaoyu
//
//  Created by 侯力 on 2024/3/19.
//  Copyright © 2017年 侯力. All rights reserved.
//

#import "TIMSYCacheFileRead.h"
// 音频
#import "TIMSYCacheFileAudio.h"
// 视频
#import "TIMSYCacheFileVideo.h"
// 文档
#import <QuickLook/QuickLook.h>

#import "TIMSYCacheFileManager.h"
#import "TIMSYCacheFileImage.h"

@interface TIMSYCacheFileRead () <UIDocumentInteractionControllerDelegate, UIScrollViewDelegate>

@property (nonatomic, weak) UIViewController *controller;

// 图片显示
@property (nonatomic, strong) UIImageView *imageView;
@property (nonatomic, strong) TIMSYCacheFileImage *fileImage;

// 文档查看（文档、图片、视频）
@property (nonatomic, strong) UIDocumentInteractionController *documentController;

@end

@implementation TIMSYCacheFileRead

// 初始化
- (id)init
{
    self = [super init];
    if (self) {
        
    }
    return self;
}

// 内存管理
- (void)dealloc
{
    NSLog(@"%@ 被释放了!", [self class]);
}

- (void)releaseSYCacheFileRead
{
    [NSObject cancelPreviousPerformRequestsWithTarget:self];
}

/**
 *  文件阅读：图片浏览、文档查看、音视频播放
 *
 *  @param filePath 文件路径
 *  @param target   UIViewController
 */
- (void)fileReadWithFilePath:(NSString *)filePath target:(id)target
{
    if (filePath == nil || ![[NSFileManager defaultManager] fileExistsAtPath:filePath]) {
        [[[UIAlertView alloc] initWithTitle:@"温馨提示" message:@"filePath指向文件不存在" delegate:@"确定" cancelButtonTitle:nil otherButtonTitles:nil, nil] show];
        return;
    }
    if (target == nil || ![target isKindOfClass:[UIViewController class]]) {
        [[[UIAlertView alloc] initWithTitle:@"温馨提示" message:@"target类型不是UIViewController" delegate:@"确定" cancelButtonTitle:nil otherButtonTitles:nil, nil] show];
        return;
    }

    if ([TIMSYCacheFileManager shareManager].showDoucumentUI) {
        if ([filePath hasSuffix:@"apk"]) {
            UIAlertController *alertController = [UIAlertController alertControllerWithTitle:nil message:@"无法打开apk文件" preferredStyle:UIAlertControllerStyleAlert];
            UIAlertAction *cancelAction = [UIAlertAction actionWithTitle:@"确定" style:UIAlertActionStyleCancel handler:^(UIAlertAction * _Nonnull action) {
                
            }];
            [alertController addAction:cancelAction];
            [target presentViewController:alertController animated:YES completion:NULL];
        } else {
            [self fileDocumentReadWithFilePath:filePath target:target];
        }
    } else {
        TIMSYCacheFileType type = [[TIMSYCacheFileManager shareManager] fileTypeReadWithFilePath:filePath];
        if (TIMSYCacheFileTypeAudio == type) {
            [self fileAudioReadWithFilePath:filePath target:target];
        } else if (TIMSYCacheFileTypeVideo == type) {
            [self fileVidioReadWithFilePath:filePath target:target];
        } else if (TIMSYCacheFileTypeImage == type) {
            if ([filePath hasSuffix:@"gif"]) {
                [self fileDocumentReadWithFilePath:filePath target:target];
            } else {
                [self fileImageReadWithFilePath:filePath target:target];
            }
        } else {
            if ([filePath hasSuffix:@"apk"]) {
                UIAlertController *alertController = [UIAlertController alertControllerWithTitle:nil message:@"无法打开apk文件" preferredStyle:UIAlertControllerStyleAlert];
                UIAlertAction *cancelAction = [UIAlertAction actionWithTitle:@"确定" style:UIAlertActionStyleCancel handler:^(UIAlertAction * _Nonnull action) {
                    
                }];
                [alertController addAction:cancelAction];
                [target presentViewController:alertController animated:YES completion:NULL];
            } else {
                [self fileDocumentReadWithFilePath:filePath target:target];
            }
        }
    }
}

#pragma mark - 音频播放

- (void)fileAudioReadWithFilePath:(NSString *)filePath target:(id)target
{
    if (filePath && target) {
        [[TIMSYCacheFileAudio shareAudio] playAudioWithFilePath:filePath];
    }
}

#pragma mark - 视频播放

/// 播放视频（网络地址，或本地路径，或本地文件名称）
- (void)fileVidioReadWithFilePath:(NSString *)filePath target:(id)target
{
    if (filePath && target) {
        [[TIMSYCacheFileAudio shareAudio] stopAudio];
        
        TIMSYCacheFileVideo *videoPlayer = [[TIMSYCacheFileVideo alloc] init];
        [videoPlayer playVideoWithFilePath:filePath target:target];
    }
}

#pragma mark - 图片播放

CGFloat scaleMini = 1.0;
CGFloat scaleMax = 3.0;

- (void)fileImageReadWithFilePath:(NSString *)filePath target:(id)target
{
    if (filePath && target) {
        [[TIMSYCacheFileAudio shareAudio] stopAudio];
        
        self.controller = target;
        
        NSArray *images = @[filePath];
        if ([TIMSYCacheFileManager shareManager].showImageShuffling) {
            NSString *file = filePath.stringByDeletingLastPathComponent;
            images = [TIMSYCacheFileManager imagefilesWithFilePath:file];
        }
        if (self.fileImage == nil) {
            self.fileImage = [[TIMSYCacheFileImage alloc] init];
        }
        NSInteger index = 0;
        if ([TIMSYCacheFileManager shareManager].nameImage) {
            index = [images indexOfObject:[TIMSYCacheFileManager shareManager].nameImage];
        }
        self.fileImage.index = index;
        self.fileImage.images = images;
        [self.fileImage reloadImages];
    }
}

#pragma mark - 文件阅读

- (void)fileDocumentReadWithFilePath:(NSString *)filePath target:(id)target
{
    if (filePath && target) {
        [[TIMSYCacheFileAudio shareAudio] stopAudio];
        
        NSURL *url = [NSURL fileURLWithPath:filePath];
        self.controller = target;
        if (self.documentController == nil) {
            self.documentController = [UIDocumentInteractionController interactionControllerWithURL:url];
            self.documentController.delegate = self;
            [self.documentController presentPreviewAnimated:YES];
        }
    }
}

#pragma mark UIDocumentInteractionController

- (UIViewController *)documentInteractionControllerViewControllerForPreview:(UIDocumentInteractionController *)controller
{
    return self.controller;
}

- (UIView *)documentInteractionControllerViewForPreview:(UIDocumentInteractionController *)controller
{
    return self.controller.view;
}

- (CGRect)documentInteractionControllerRectForPreview:(UIDocumentInteractionController *)controller
{
    return self.controller.view.bounds;
}

// 点击预览窗口的“Done”(完成)按钮时调用
- (void)documentInteractionControllerDidEndPreview:(UIDocumentInteractionController *)_controller
{
    self.documentController = nil;
}

@end
