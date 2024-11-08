//
//  ICChatMessageCustomFileCell.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/7/20.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICChatMessageCustomFileCell.h"
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
#import "YYLabel.h"

@interface ICChatMessageCustomFileCell ()<UIDocumentInteractionControllerDelegate, NSURLConnectionDataDelegate>

@property (nonatomic, weak) UIViewController *controller;

@property (nonatomic, strong) TIMLabel *custFileNameLabel;
@property (nonatomic, strong) YYLabel *custFileTypeDescLabel;
@property (nonatomic, strong) UIImageView *custFilePictureView;
@property (nonatomic, strong) TIMLabel *custFileTimeLabel;
@property (nonatomic, strong) UIView                * lineView;
@property (nonatomic, strong) UIButton *downLoadBtn;
@property (nonatomic, copy) NSString *fileUrl;

/** 文件数据 */
@property (nonatomic, strong) NSMutableData *fileData;
/** 文件的总长度 */
@property (nonatomic, assign) NSInteger contentLength;

/// 文档查看（文档、图片、视频）
@property (nonatomic, strong) UIDocumentInteractionController *documentController;

@end

@implementation ICChatMessageCustomFileCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
//        [self.contentView addSubview:self.custFileNameLabel];
        [self.contentView addSubview:self.custFileTypeDescLabel];
        [self.contentView addSubview:self.custFilePictureView];
//        [self.contentView addSubview:self.custFileTimeLabel];
        [self.contentView addSubview:self.downLoadBtn];
        [self.contentView addSubview:self.lineView];
        
//        UITapGestureRecognizer * tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(fileTap)];
//        self.bubbleView.userInteractionEnabled = YES;
//        [self.bubbleView addGestureRecognizer:tap];
        
    }
    return self;
}

- (void)fileTap {
    NSLog(@"fileTap");
    if ([self fileWhetherItExists:self.fileUrl withFileName:self.custFileTypeDescLabel.text]) {
        NSLog(@"查看");
        /// 查看
        [self fileDocumentReadWithFilePath:self.modelFrame.model.mediaPath target:[self getCurrentViewController]];
    }
    else {
        NSLog(@"还没下载下来：");
    }
    
    
}

#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    if (!modelFrame.model.message.content) {
        return;
    }
    self.fileUrl = [modelFrame.model.urlPath stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    self.readLabel.frame = modelFrame.unReadLabelF;

    NSDictionary * contentDic = [kitUtils dictionaryWithJsonString:modelFrame.model.message.content];
    if (!contentDic) {
        return;
    }
//    [self.custFileNameLabel setFrame:modelFrame.custFileNameF];
    [self.custFileTypeDescLabel setFrame:modelFrame.custFileTypeDescF];
    [self.custFilePictureView setFrame:modelFrame.custFilePictureF];
//    [self.custFileTimeLabel setFrame:modelFrame.custFileTimeF];
    [self.lineView setFrame:(CGRectMake(CGRectGetMinX(modelFrame.aduitRejectedViewF), CGRectGetMinY(modelFrame.aduitRejectedViewF)-1, modelFrame.aduitRejectedViewF.size.width, 1))];
    [self.downLoadBtn setFrame:modelFrame.aduitRejectedViewF];

//    _aduitRejectedViewF  = CGRectMake(x, CGRectGetMaxY(_custFileTypeDescF) + 22.f, custFileTypeDescW, 17.f);

    
    
//    if (![kitUtils isBlankString:contentDic[@"body"][@"fileTypeDesc"]]) {
//        self.custFileTypeDescLabel.text = contentDic[@"body"][@"fileTypeDesc"];
//    } else {
//        self.custFileTypeDescLabel.text = @"文件";
//    }
    
    if (![kitUtils isBlankString:contentDic[@"fileName"]]) {
//        self.custFileNameLabel.text = contentDic[@"fileName"];
        self.custFileTypeDescLabel.text = [NSString stringWithFormat:@"%@", contentDic[@"fileName"]];
    } else {
//        self.custFileNameLabel.text = @"";
        self.custFileTypeDescLabel.text = @"文件";
    }
    
    NSString *imageString;
    if (![kitUtils isBlankString:contentDic[@"fileName"]]) {
        
        if ([contentDic[@"fileName"] rangeOfString:@"pdf"].location !=NSNotFound) {
            imageString = @"Fileppt";
        } else if ([contentDic[@"fileName"] rangeOfString:@"doc"].location !=NSNotFound ) {
            imageString = @"Filedoc";
        } else if ([contentDic[@"fileName"] rangeOfString:@"ppt"].location !=NSNotFound ) {
            imageString = @"Fileunknown";
        } else if ([contentDic[@"fileName"] rangeOfString:@"xls"].location !=NSNotFound ) {
            imageString = @"FileExcel";
        } else if ([contentDic[@"fileName"] rangeOfString:@"mp3"].location !=NSNotFound ) {
            imageString = @"Filemp3";
        } else if ([contentDic[@"fileName"] rangeOfString:@"text"].location !=NSNotFound || [contentDic[@"fileName"] rangeOfString:@"txt"].location !=NSNotFound) {
            imageString = @"Filetxt";
        } else {
            imageString = @"file_unknown";
        }
    } else {
        imageString = @"file_unknown";
    }
    
    self.custFilePictureView.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,imageString]];
    
//    if (![kitUtils isBlankString:contentDic[@"body"][@"createTime"]]) {
//        self.custFileTimeLabel.text = contentDic[@"body"][@"createTime"];
//    } else {
//        self.custFileTimeLabel.text =progress @"";
//    }
    if ([self fileWhetherItExists:self.fileUrl withFileName:self.custFileTypeDescLabel.text] ||
        [[NSFileManager defaultManager] fileExistsAtPath:self.fileUrl]) {
        [self.downLoadBtn setTitle:@"已下载" forState:(UIControlStateNormal)];
    }
    else {
        [self.downLoadBtn setTitle:@"下载" forState:(UIControlStateNormal)];
    }
//    [self BMWCustomerUnreadStatusDisplayWithModel:modelFrame.model];
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

- (YYLabel *)custFileTypeDescLabel {
    if (!_custFileTypeDescLabel) {
        _custFileTypeDescLabel = [[YYLabel alloc] init];
//        _custFileTypeDescLabel.numberOfLines = 2;
//        _custFileTypeDescLabel.textVerticalAlignment = YYTextVerticalAlignmentTop;
        _custFileTypeDescLabel.numberOfLines = 1;
        _custFileTypeDescLabel.textVerticalAlignment = YYTextVerticalAlignmentCenter;
        _custFileTypeDescLabel.font = [UIFont fontWithName:@"PingFangSC-Medium" size:16.0f];
        _custFileTypeDescLabel.textColor = TOSHexAColor(0x262626,1.0);
//        _custFileTypeDescLabel.linkDetectionTypes = KILinkTypeOptionNone;
        _custFileTypeDescLabel.lineBreakMode = NSLineBreakByTruncatingMiddle;
//        __weak typeof(self) weakself = self;
//        _custFileTypeDescLabel.textTapAction = ^(UIView * _Nonnull containerView, NSAttributedString * _Nonnull text, NSRange range, CGRect rect) {
//            __strong typeof(self) strongself = weakself;
//            NSLog(@"textTapAction");
//            [strongself fileTap];
//        };
//        _custFileTypeDescLabel.backgroundColor = UIColor.orangeColor;
        
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
        _downLoadBtn.backgroundColor = [UIColor clearColor];
        [_downLoadBtn addTarget:self action:@selector(downLoadAction) forControlEvents:UIControlEventTouchUpInside];
    }
    return _downLoadBtn;
}

-(void)downLoadAction{
    
    if ([self.downLoadBtn.titleLabel.text isEqualToString:@"已下载"]) {
        return;
    }
    
    NSLog(@"self.fileUrl 开始下载 %@",self.fileUrl);
    
    NSURL *url = [NSURL URLWithString:self.fileUrl];
    [NSURLConnection connectionWithRequest:[NSURLRequest requestWithURL:url] delegate:self];
    NSLog(@"开始下载");
    
    if ([self.longPressDelegate respondsToSelector:@selector(downFileMessage:)]) {
        [self.longPressDelegate downFileMessage:self.fileUrl];
    }
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
    NSLog(@"已下载：%.2f%%", (progress) * 100);
//    self.progressView.progress = progress;
    
}

- (void)connectionDidFinishLoading:(NSURLConnection *)connection {
    NSLog(@"下载完毕 ：+++++ %@", self.modelFrame.model.mediaPath);
    
    NSLog(@"self.fileUrl 下载完毕 %@",self.fileUrl);
    
    // 将文件写入沙盒中
    NSError * error = nil;
    BOOL write = [self.fileData writeToFile:self.modelFrame.model.mediaPath options:(NSAtomicWrite) error:&error];
    if (write) {
        NSLog(@"写入成功");
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
            NSLog(@"处理后的url：%@", url.absoluteString);
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

- (UIViewController *)getCurrentViewController {
    UIWindow *window = [[UIApplication sharedApplication].delegate window];
    UIViewController *topViewController = [window rootViewController];
    while (true) {
        if (topViewController.presentedViewController) {
            topViewController = topViewController.presentedViewController;
        }
        else if ([topViewController isKindOfClass:[UINavigationController class]] && [(UINavigationController*)topViewController topViewController]) {
            
            topViewController = [(UINavigationController *)topViewController topViewController];
        }
        else if ([topViewController isKindOfClass:[UITabBarController class]]) {
            
            UITabBarController *tab = (UITabBarController *)topViewController;
            topViewController = tab.selectedViewController;
        } else {
            break;
        }
    }
    return topViewController;
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
    self.modelFrame.model.mediaPath = downloadPath;
    if ([fileManager fileExistsAtPath:downloadPath]) {
        return YES;
    }
    return NO;
}

@end
