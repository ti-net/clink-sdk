//
//  OSRobotCombinationFileSubview.m
//  TOSClientKit
//
//  Created by 言 on 2022/8/1.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "OSRobotCombinationFileSubview.h"
#import "TIMMessageModel.h"
#import "ICFileTool.h"
#import "NSDictionary+Extension.h"
#import "TIMLabel.h"
#import "TIMConstants.h"
#import "UIView+SDExtension.h"
#import "kitUtils.h"
#import "UIImageView+TIMWebCache.h"
#import "ICFaceManager.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import <TOSClientLib/CombinationMessage.h>

#import <TOSClientLib/TOSClientLib.h>

#import "YYKit.h"
#import "ICMessageConst.h"
#import "NSString+Extension.h"
#import "UIResponder+GXRouter.h"

@interface OSRobotCombinationFileSubview () <UIDocumentInteractionControllerDelegate, NSURLConnectionDataDelegate>

@property (nonatomic, weak) UIViewController *controller;

@property (nonatomic, strong) TIMLabel *custFileNameLabel;
@property (nonatomic, strong) UILabel *custFileTypeDescLabel;
@property (nonatomic, strong) UIImageView *custFilePictureView;
@property (nonatomic, strong) TIMLabel *custFileTimeLabel;
@property (nonatomic, strong) UIView                * lineView;
@property (nonatomic, strong) UIButton *downLoadBtn;
@property (nonatomic, copy) NSString *fileUrl;

@property (nonatomic, strong) CombinationMessage *model;


/** 文件数据 */
@property (nonatomic, strong) NSMutableData *fileData;
/** 文件的总长度 */
@property (nonatomic, assign) NSInteger contentLength;

/// 文档查看（文档、图片、视频）
@property (nonatomic, strong) UIDocumentInteractionController *documentController;

@end

@implementation OSRobotCombinationFileSubview
- (void)setupSubviews {
    [super setupSubviews];
    
    self.backgroundColor = [UIColor clearColor];
    
    [self addSubview:self.custFileTypeDescLabel];
    [self addSubview:self.custFilePictureView];
    [self addSubview:self.downLoadBtn];
    [self addSubview:self.lineView];
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapAction:)];
    [self addGestureRecognizer:tap];
}

- (void)tapAction:(UITapGestureRecognizer *)sender {
    [self routerEventWithName:GXRouterEventCombinationFileTapEventName 
                     userInfo:@{@"urlPath"   : self.fileUrl,
                                }];
}

- (void)defineLayout {
    [super defineLayout];
    
}

#pragma mark - Private Method
- (void)setWithModel:(CombinationMessage *)model {
    self.model = model;
    
    NSString *fileKey = @"null";
    NSString *fileName = @"null";
    if (![kitUtils isBlankString:model.text]) {
        fileKey = model.text;
    }
    if (![kitUtils isBlankString:model.name]) {
        fileName = model.name;
    }
    
    NSString *provider = model.robotProvider;
    NSString *timestamp = [kitUtils getNowTimestampWithSec];
    NSString *accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
    NSString *sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
    NSString *urlPath = [[NSString stringWithFormat:@"%@?fileKey=%@&fileName=%@&provider=%@&isDownload=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[NSString stringWithFormat:@"%@/bot_media",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl]],fileKey,fileName,model.robotProvider,@"false",[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    
    self.fileUrl = urlPath;
    
    [self.custFilePictureView setFrame:CGRectMake(16.f, 8.f, 32.f, 32.f)];
    
    //(CGRectGetMaxX(self.custFilePictureView.frame) + 4.f, 12.f, self.width - CGRectGetMaxX(self.custFilePictureView.frame) - 4.f - 16.f, 24.f)
    [self.custFileTypeDescLabel setFrame:CGRectMake(CGRectGetMaxX(self.custFilePictureView.frame) + 4.f, 12.f, self.tos_width - CGRectGetMaxX(self.custFilePictureView.frame) - 20.f, 24.f)];
    
    [self.lineView setFrame:(CGRectMake(0.f, 48.f, self.tos_width, 1.f))];
    
    //(self.width/2-50.f/2, 57.f, 50.f, 20.f)
    [self.downLoadBtn setFrame:CGRectMake(self.tos_width/2-25.f, 57.f, 50.f, 20.f)];

    if (![kitUtils isBlankString:model.name]) {
        self.custFileTypeDescLabel.text = [NSString stringWithFormat:@"%@",model.name];
    } else {
        self.custFileTypeDescLabel.text = @"文件";
    }
    
    NSString *imageString;
    if (![kitUtils isBlankString:model.name]) {
        
        if ([model.name rangeOfString:@"pdf"].location !=NSNotFound) {
            imageString = @"Fileppt";
        } else if ([model.name rangeOfString:@"doc"].location !=NSNotFound) {
            imageString = @"Filedoc";
        } else if ([model.name rangeOfString:@"ppt"].location !=NSNotFound) {
            imageString = @"Fileunknown";
        } else if ([model.name rangeOfString:@"xls"].location !=NSNotFound ||
                   [model.name rangeOfString:@"xlsx"].location !=NSNotFound) {
            imageString = @"FileExcel";
        } else if ([model.name rangeOfString:@"mp3"].location !=NSNotFound) {
            imageString = @"Filemp3";
        } else if ([model.name rangeOfString:@"text"].location !=NSNotFound ||
                   [model.name rangeOfString:@"txt"].location !=NSNotFound) {
            imageString = @"Filetxt";
        } else {
            imageString = @"file_unknown";
        }
    } else {
        imageString = @"file_unknown";
    }
    
    self.custFilePictureView.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,imageString]];
    
    if ([self fileWhetherItExists:self.fileUrl withFileName:self.custFileTypeDescLabel.text]) {
        [self.downLoadBtn setTitle:@"已下载" forState:(UIControlStateNormal)];
    }
    else {
        [self.downLoadBtn setTitle:@"下载" forState:(UIControlStateNormal)];
    }
}

#pragma mark - Getter and Setter
- (TIMLabel *)custFileNameLabel {
    if (!_custFileNameLabel) {
        _custFileNameLabel = [[TIMLabel alloc] init];
        _custFileNameLabel.numberOfLines = 2;
        _custFileNameLabel.font = [UIFont systemFontOfSize:13.0];
        _custFileNameLabel.textColor = TOSHexAColor(0x999999,1.0);
        _custFileNameLabel.linkDetectionTypes = KILinkTypeOptionNone;
        _custFileNameLabel.backgroundColor = UIColor.blueColor;
    }
    return _custFileNameLabel;
}

- (UIImageView *)custFilePictureView {
    if (!_custFilePictureView) {
        _custFilePictureView = [[UIImageView alloc] init];
    }
    return _custFilePictureView;
}

- (UILabel *)custFileTypeDescLabel {
    if (!_custFileTypeDescLabel) {
        _custFileTypeDescLabel = [[UILabel alloc] init];
        _custFileTypeDescLabel.numberOfLines = 1;
        _custFileTypeDescLabel.font = [UIFont fontWithName:@"PingFangSC-Medium" size:16.0f];
        _custFileTypeDescLabel.textColor = TOSHexAColor(0x262626,1.0);
        _custFileTypeDescLabel.lineBreakMode = NSLineBreakByTruncatingMiddle;
        
    }
    return _custFileTypeDescLabel;
}

- (TIMLabel *)custFileTimeLabel {
    if (!_custFileTimeLabel) {
        _custFileTimeLabel = [[TIMLabel alloc] init];
        _custFileTimeLabel.numberOfLines = 1;
        _custFileTimeLabel.font = [UIFont systemFontOfSize:12.0];
        _custFileTimeLabel.textColor = TOSHexAColor(0x999999,1.0);
        _custFileTimeLabel.linkDetectionTypes = KILinkTypeOptionNone;
        _custFileTimeLabel.backgroundColor = UIColor.yellowColor;
    }
    return _custFileTimeLabel;
}

- (UIView *)lineView {
    if (!_lineView) {
        _lineView = [[UIView alloc] init];
        _lineView.backgroundColor = TOSHexAColor(0x000000, 0.06);
        
    }
    return _lineView;
}

-(UIButton *)downLoadBtn{
    if (!_downLoadBtn) {
        _downLoadBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [_downLoadBtn setTitleColor:TOSHexAColor(0x1890ff,1.0) forState:UIControlStateNormal];
        [_downLoadBtn.titleLabel setFont:[UIFont systemFontOfSize:14]];
        [_downLoadBtn setTitle:@"下载" forState:UIControlStateNormal];
        _downLoadBtn.titleLabel.textAlignment = NSTextAlignmentCenter;
        _downLoadBtn.backgroundColor = [UIColor clearColor];
        [_downLoadBtn addTarget:self action:@selector(downLoadAction) forControlEvents:UIControlEventTouchUpInside];
    }
    return _downLoadBtn;
}

-(void)downLoadAction{
    
    if ([self.downLoadBtn.titleLabel.text isEqualToString:@"已下载"]) {
        return;
    }
    
    NSURL *url = [NSURL URLWithString:self.fileUrl];
    [NSURLConnection connectionWithRequest:[NSURLRequest requestWithURL:url] delegate:self];
}

#pragma mark - <NSURLConnectionDataDelegate>
- (void)connection:(NSURLConnection *)connection didReceiveResponse:(NSHTTPURLResponse *)response
{
    self.contentLength = [response.allHeaderFields[@"Content-Length"] integerValue];
    self.fileData = [NSMutableData data];
}

- (void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data
{
    [self.fileData appendData:data];
    CGFloat progress = 1.0 * self.fileData.length / self.contentLength;
}

- (void)connectionDidFinishLoading:(NSURLConnection *)connection {
    
    // 将文件写入沙盒中
    NSError * error = nil;
    BOOL write = [self.fileData writeToFile:self.model.mediaPath options:(NSAtomicWrite) error:&error];
    if (write) {
        [self.downLoadBtn setTitle:@"已下载" forState:(UIControlStateNormal)];
        self.fileData = nil;
    }
    else {
        NSLog(@"写入失败 ： %@", error);
    }
    
}

- (void)fileDocumentReadWithFilePath:(NSString *)filePath target:(id)target {
    if (filePath && target) {
        NSURL *url;
        if ([filePath isKindOfClass:[NSURL class]]) {
            url = (NSURL *)filePath;
        }
        else {
            url = [NSURL fileURLWithPath:filePath];
        }
        self.controller = target;
        if (self.documentController == nil) {
            self.documentController = [UIDocumentInteractionController interactionControllerWithURL:url];
            self.documentController.delegate = self;
            [self.documentController presentPreviewAnimated:YES];
        }
    }
}

#pragma mark - UIDocumentInteractionControllerDelegate
- (UIViewController *)documentInteractionControllerViewControllerForPreview:(UIDocumentInteractionController *)controller {
    return self.controller;
}

- (UIView *)documentInteractionControllerViewForPreview:(UIDocumentInteractionController *)controller {
    return self.controller.view;
}

- (CGRect)documentInteractionControllerRectForPreview:(UIDocumentInteractionController *)controller {
    return self.controller.view.bounds;
}

// 点击预览窗口的“Done”(完成)按钮时调用
- (void)documentInteractionControllerDidEndPreview:(UIDocumentInteractionController *)_controller {
    self.documentController = nil;
}

/// 判断文件是否存在
- (BOOL)fileWhetherItExists:(NSString *)file withFileName:(NSString *)fileName {
    /// 获取Documents目录路径
    NSString *path = [[NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingPathComponent:@"File"];
    NSFileManager *fileManager = [NSFileManager defaultManager];
    /// 判断是否存在该路径
    BOOL isDirExist = [fileManager fileExistsAtPath:path];
    /// 没有需要创建文件路径
    if (!isDirExist) {
        BOOL isCreatDir = [fileManager createDirectoryAtPath:path withIntermediateDirectories:YES attributes:nil error:nil];
        if (!isCreatDir) {
            //ICLog(@"create folder failed");
            NSLog(@"创建文件夹失败！");
        }
    }
    NSString * downloadPath = [path stringByAppendingPathComponent:[NSString stringWithFormat:@"%@", fileName]];
    self.model.mediaPath = downloadPath;
    if ([fileManager fileExistsAtPath:downloadPath]) {
        return YES;
    }
    return NO;
}

@end
