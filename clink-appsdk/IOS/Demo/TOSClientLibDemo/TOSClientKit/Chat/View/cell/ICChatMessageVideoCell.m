//
//  ICChatMessageVideoCell.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/13.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICChatMessageVideoCell.h"
#import "TIMMessageModel.h"
#import "UIImage+Extension.h"
#import "TIMICMessage.h"
#import "ICVideoManager.h"
#import "ICMediaManager.h"
#import "ZacharyPlayManager.h"
#import "ICAVPlayer.h"
#import "ICFileTool.h"
#import "UIView+SDExtension.h"
#import "TIMConstants.h"
#import "ICMessageHelper.h"
#import "kitUtils.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import <TOSClientKit/TOSClientKit.h>

@interface ICChatMessageVideoCell ()

@property (nonatomic, strong) UIButton *imageBtn;

@property (nonatomic, strong) UIButton *topBtn;

@property (nonatomic, strong) UIButton *auditRejectedBtn;
@property (nonatomic, strong) UIButton *auditApprovedBtn;
@property (nonatomic, copy) NSString *     strEnableLookUp;           // 是否有查看权限
@property (nonatomic, copy) NSString *     strEnableAudit;             // 是否有审核权限
@property (nonatomic, strong) NSNumber* msgAuditStatus;          // 消息的审核状态

@end

@implementation ICChatMessageVideoCell


- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.imageBtn];
        [self.imageBtn addSubview:self.topBtn];
    }
    return self;
}

- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
//    @WeakObj(self)
    [super setModelFrame:modelFrame];
    ICMediaManager *manager = [ICMediaManager sharedManager];
    self.readLabel.frame = modelFrame.unReadLabelF;
    
    modelFrame.model.urlPath = [modelFrame.model.urlPath stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    
    UIImage *videoArrowImage;
    TIMKitLog(@"modelFrame.model.mediaPath ====== %@",modelFrame.model.mediaPath);
    if (modelFrame.model.mediaPath.length >= 4 && [[modelFrame.model.mediaPath substringToIndex:4] isEqualToString:@"http"]) {
//        videoArrowImage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"loadImage.png"]];
//        self.bubbleView.image = [videoArrowImage stretchableImageWithLeftCapWidth:5 topCapHeight:5];
        
        NSString * localPath;
        localPath = [[ICMediaManager sharedManager] smallImgPath:modelFrame.model.message.messageId];
        videoArrowImage = [UIImage imageWithContentsOfFile:localPath];
        if (!videoArrowImage) {
            UIImage *resultImg = [self getVideoPreViewImage:[NSURL URLWithString:modelFrame.model.urlPath]];
            videoArrowImage = [UIImage addImage2:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"video_play_btn_bg"]] toImage:resultImg];
            if (videoArrowImage) {
                [[ICMediaManager sharedManager] saveImage:videoArrowImage msgId:modelFrame.model.message.messageId picType:@"jpg"];
            }
        }
        

        
        
    }else{
        NSString * strRealVideoPath;
        if (modelFrame.model.mediaPath!=nil && modelFrame.model.mediaPath.length>=0) {
            strRealVideoPath = modelFrame.model.mediaPath;

            self.bubbleView.image = nil;

            videoArrowImage = [manager videoConverPhotoWithVideoPath:strRealVideoPath size:modelFrame.picViewF.size isSender:modelFrame.model.isSender];
        }
//        if ([[modelFrame.model.mediaPath substringToIndex:7] isEqualToString:@"file://"]) {
//            strRealVideoPath = [modelFrame.model.mediaPath substringFromIndex:7];
//        }else{
//            strRealVideoPath = modelFrame.model.mediaPath;
//        }
        
//        strRealVideoPath = modelFrame.model.mediaPath;
//
//        self.bubbleView.image = nil;
//
//        videoArrowImage = [manager videoConverPhotoWithVideoPath:strRealVideoPath size:modelFrame.picViewF.size isSender:modelFrame.model.isSender];
        
        
    }
    // 判断封面图
    if (modelFrame.model.urlPath.length > 0 && ![modelFrame.model.urlPath hasPrefix:@"http"]) {
        self.bubbleView.image = nil;
        videoArrowImage = [manager imageConverPhotoWithVideoPath:modelFrame.model.urlPath size:modelFrame.picViewF.size isSender:modelFrame.model.isSender];
    }
    
    self.imageBtn.frame = modelFrame.picViewF;
    
    self.strEnableLookUp = modelFrame.model.strEnableLookUp;
    self.strEnableAudit = modelFrame.model.strEnableAudit;
    self.msgAuditStatus = modelFrame.model.auditStatus;
    
    self.bubbleView.userInteractionEnabled = videoArrowImage != nil;
    [self.imageBtn setImage:videoArrowImage  forState:UIControlStateNormal];
    self.topBtn.frame = CGRectMake(0, 0, _imageBtn.tosSD_width, _imageBtn.tosSD_height);
    
    if ([TOSKitCustomInfo shareCustomInfo].chatBubble_CornerType != BubbleCornerTypeAll && [TOSKitCustomInfo shareCustomInfo].chatBubble_CornerType != BubbleCornerTypeNormal) {
        self.imageBtn.layer.maskedCorners = self.bubbleView.layer.maskedCorners;
    }
    
    if (modelFrame.model.isSender) {
        self.imageBtn.layer.cornerRadius = [TOSKitCustomInfo shareCustomInfo].senderBubble_cornerRadius;
    }
    else {
        self.imageBtn.layer.cornerRadius = [TOSKitCustomInfo shareCustomInfo].receiveBubble_cornerRadius;
    }
    
}



//- (void)imageBtnClick:(UIButton *)btn
//{
//    __block NSString *path = [[ICVideoManager shareManager] videoPathForMP4:self.modelFrame.model.mediaPath];
//    [self videoPlay:path];
//
//}

- (void)imageBtnClick:(UIButton *)btn
{
//    __block NSString *path = [[ICVideoManager shareManager] videoPathForMP4:selfWeak.modelFrame.model.mediaPath];
//    [selfWeak videoPlay:path];
    @WeakObj(self)
    CGRect btnRect = [ICMessageHelper photoFramInWindow:selfWeak.imageBtn];
    NSValue *btnValue   = [NSValue valueWithCGRect:btnRect];
    [selfWeak routerEventWithName:GXRouterEventVideoTapEventName
                     userInfo:@{MessageKey   : selfWeak.modelFrame,
                                @"BtnView"   : btnValue
                                }];
}


- (void)videoPlay:(NSString *)path
{
//    ICAVPlayer *player = [[ICAVPlayer alloc] initWithPlayerURL:[NSURL fileURLWithPath:path]];
//    UIView *fullSreen = [[UIView alloc] initWithFrame:CGRectMake(0,0,App_Frame_Width,APP_Frame_Height)];
//    [self addSubview:fullSreen];
//    [player presentFromVideoView:self.imageBtn toContainer:fullSreen animated:YES completion:nil];
}

#pragma mark - videoPlay

- (void)firstPlay
{
    @WeakObj(self)
    CGRect btnRect = [ICMessageHelper photoFramInWindow:self.imageBtn];
    NSValue *btnValue   = [NSValue valueWithCGRect:btnRect];
    [selfWeak routerEventWithName:GXRouterEventVideoTapEventName
                     userInfo:@{MessageKey   : selfWeak.modelFrame,
                                @"BtnView"   : btnValue
                                }];
//    @WeakObj(self)
//    __block NSString *path = [[ICVideoManager shareManager] videoPathForMP4:selfWeak.modelFrame.model.mediaPath];
//    if ([ICFileTool fileExistsAtPath:path]) {
//        [selfWeak reloadStart];
//        _topBtn.hidden = YES;
//    }
}

-(void)reloadStart {
    @WeakObj(self)
    __block NSString *path = [[ICVideoManager shareManager] videoPathForMP4:selfWeak.modelFrame.model.mediaPath];
    [[ZacharyPlayManager sharedInstance] startWithLocalPath:path WithVideoBlock:^(UIImage *imageData, NSString *filePath,CGImageRef tpImage) {
        @StrongObj(self)
        if ([filePath isEqualToString:path]) {
            [self.imageBtn setImage:imageData forState:UIControlStateNormal];
        }
    }];
    
//    [[ZacharyPlayManager sharedInstance] reloadVideo:^(NSString *filePath) {
//        MAIN(^{
//            @StrongObj(self)
//            if ([filePath isEqualToString:path]) {
//                [self reloadStart];
//            }
//        });
//    } withFile:path];
}

-(void)stopVideo {
    _topBtn.hidden = NO;
    [[ZacharyPlayManager sharedInstance] cancelVideo:[[ICVideoManager shareManager] videoPathForMP4:self.modelFrame.model.mediaPath]];
}

-(void)dealloc {
    TIMKitLog(@"chatVideoCell dealloc");
    [[ZacharyPlayManager sharedInstance] cancelAllVideo];
}

// 点击按钮
- (void)imageRejectedBtnClick:(UIButton *)btn
{
     @WeakObj(self)
    // 与图片逻辑相同
    [selfWeak routerEventWithName:GXRouterEventImageRejectedTapEventName
                     userInfo:@{MessageKey   : selfWeak.modelFrame
                                }];
}

- (void)imageApprovedBtnClick:(UIButton *)btn
{
    @WeakObj(self)
    // 与图片逻辑相同
    [selfWeak routerEventWithName:GXRouterEventImageApprovedTapEventName
                     userInfo:@{MessageKey   : selfWeak.modelFrame
                                }];
}

-(void)longPress{
    
}

#pragma mark - Getter

- (UIButton *)imageBtn
{
    if (nil == _imageBtn) {
        _imageBtn = [[UIButton alloc] init];
        [_imageBtn addTarget:self action:@selector(imageBtnClick:) forControlEvents:UIControlEventTouchUpInside];
        _imageBtn.layer.masksToBounds = YES;
//        _imageBtn.layer.cornerRadius = 5;
        _imageBtn.clipsToBounds = YES;
    }
    return _imageBtn;
}

- (UIButton *)topBtn
{
    if (!_topBtn) {
        _topBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [_topBtn addTarget:self action:@selector(firstPlay) forControlEvents:UIControlEventTouchUpInside];
        _topBtn.layer.masksToBounds = YES;
        _topBtn.layer.cornerRadius = 5;
        
        //按钮长按
        UILongPressGestureRecognizer *longPress = [[UILongPressGestureRecognizer alloc] initWithTarget:self action:@selector(longPress)];
       //长按时间
        longPress.minimumPressDuration = 0.8;
        [_topBtn addGestureRecognizer:longPress];
        
    }
    return _topBtn;
}


- (UIButton *)auditStatusBtn
{
    if (nil == _auditStatusBtn) {
        _auditStatusBtn = [[UIButton alloc] init];
        _auditStatusBtn.imageEdgeInsets = UIEdgeInsetsMake(0, 0, 0, 0);
        _auditStatusBtn.layer.masksToBounds = YES;
        _auditStatusBtn.layer.cornerRadius = 5;
        _auditStatusBtn.clipsToBounds = YES;
    }
    return _auditStatusBtn;
}

- (UIButton *)auditRejectedBtn
{
    if (nil == _auditRejectedBtn) {
        _auditRejectedBtn = [[UIButton alloc] init];
        [_auditRejectedBtn addTarget:self action:@selector(imageRejectedBtnClick:) forControlEvents:UIControlEventTouchUpInside];
        _auditRejectedBtn.imageEdgeInsets = UIEdgeInsetsMake(0, 0, 0, 0);
        _auditRejectedBtn.layer.masksToBounds = YES;
//        _auditRejectedBtn.layer.cornerRadius = 5;
        _auditRejectedBtn.clipsToBounds = YES;
    }
    return _auditRejectedBtn;
}

- (UIButton *)auditApprovedBtn
{
    if (nil == _auditApprovedBtn) {
        _auditApprovedBtn = [[UIButton alloc] init];
        [_auditApprovedBtn addTarget:self action:@selector(imageApprovedBtnClick:) forControlEvents:UIControlEventTouchUpInside];
        _auditApprovedBtn.imageEdgeInsets = UIEdgeInsetsMake(0, 0, 0, 0);
        _auditApprovedBtn.layer.masksToBounds = YES;
//        _auditApprovedBtn.layer.cornerRadius = 5;
        _auditApprovedBtn.clipsToBounds = YES;
    }
    return _auditApprovedBtn;
}


// 获取网络视频第一帧
- (UIImage*) getVideoPreViewImage:(NSURL *)path
{
    AVURLAsset *asset = [[AVURLAsset alloc] initWithURL:path options:nil];
    AVAssetImageGenerator *assetGen = [[AVAssetImageGenerator alloc] initWithAsset:asset];
    
    assetGen.appliesPreferredTrackTransform = YES;
    CMTime time = CMTimeMakeWithSeconds(0.0, 600);
    NSError *error = nil;
    CMTime actualTime;
    CGImageRef image = [assetGen copyCGImageAtTime:time actualTime:&actualTime error:&error];
    UIImage *videoImage = [[UIImage alloc] initWithCGImage:image];
    CGImageRelease(image);
    return videoImage;
}

@end
