//
//  OCRobotCombinationImageVideoSubview.m
//  TOSClientKit
//
//  Created by 言 on 2022/8/9.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "OCRobotCombinationImageVideoSubview.h"
#import <TOSClientLib/CombinationMessage.h>
#import "TIMConstants.h"

#import <TOSClientLib/RichTextMessage.h>
#import "ICMediaManager.h"
#import "YYKit.h"
#import "UIImageView+YYWebImage.h"
#import "TIMMessageModel.h"
#import "UIImage+Extension.h"
#import "TIMICMessage.h"
#import "ICVideoManager.h"
#import "ICMediaManager.h"
#import "ZacharyPlayManager.h"
#import "ICAVPlayer.h"
#import "ICFileTool.h"
#import "UIView+SDExtension.h"
#import "ICMessageHelper.h"
#import "kitUtils.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"

@interface OCRobotCombinationImageVideoSubview ()

@property (nonatomic, strong) UIImageView *picView;
@property (nonatomic, copy) NSString *urlPath;
@property (nonatomic, strong) CombinationMessage *model;

@property (nonatomic, strong) CombinationMessage *combinationModel;


@end

@implementation OCRobotCombinationImageVideoSubview
- (void)setupSubviews {
    [super setupSubviews];
    
    self.backgroundColor = [UIColor clearColor];
    [self addSubview:self.picView];
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(imageBtnClick)];
    [self.picView addGestureRecognizer:tap];
}

- (void)defineLayout {
    [super defineLayout];
    
}

- (void)setWithCombinationModel:(CombinationMessage *)model {
    NSString *fileKey = @"null";
    NSString *fileName = @"null";
    NSString *saveFileName = @"";
    if (![kitUtils isBlankString:model.text]) {
        fileKey = [NSString stringWithFormat:@"%@",model.text];
        saveFileName = [[NSString stringWithFormat:@"%@",fileKey] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    }
    if (![kitUtils isBlankString:model.name]) {
        fileName = [NSString stringWithFormat:@"%@",model.name];
    }
    
//    NSString *provider = model.robotProvider;
    NSString *timestamp = [kitUtils getNowTimestampWithSec];
    NSString *accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
    NSString *sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
    NSString *urlPath = [[NSString stringWithFormat:@"%@?fileKey=%@&fileName=%@&provider=%@&isDownload=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[NSString stringWithFormat:@"%@/bot_media",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl]],fileKey,fileName,model.robotProvider,@"false",[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    
    if ([model.type isEqualToString:@"2"]) {
        urlPath = [NSString stringWithFormat:@"%@&isThumbnail=true",urlPath];
    }
    if (saveFileName.length <= 0) {
        saveFileName = [NSString stringWithFormat:@"%@",urlPath];
    }
    
    self.model = model;
    
    
    self.picView.frame = model.contentF;
    self.picView.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"im_photos"]];
    
    ICMediaManager *manager = [ICMediaManager sharedManager];
    
    self.urlPath = [urlPath mutableCopy];
    self.combinationModel = model;
    
    if ([model.type isEqualToString:@"4"]) {
        
        UIImage *videoArrowImage;
        
        if (urlPath &&
            [urlPath isKindOfClass:[NSString class]]) {
            
            
            NSString *charaters = @"?!@#$^&%*+,:;=.'\"`<>()[]{}/\\| 《》";
            NSCharacterSet *set = [[NSCharacterSet characterSetWithCharactersInString:charaters] invertedSet];
            saveFileName = [saveFileName stringByAddingPercentEncodingWithAllowedCharacters:set];
            
            NSString * localPath;
            localPath = [[ICMediaManager sharedManager] smallImgPath:saveFileName];
            videoArrowImage = [UIImage imageWithContentsOfFile:localPath];
            if (!videoArrowImage) {
                UIImage *resultImg = [self getVideoPreViewImage:[NSURL URLWithString:urlPath]];
                videoArrowImage = [UIImage addImage2:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"video_play_btn_bg"]] toImage:resultImg];
                if (videoArrowImage) {
                    [[ICMediaManager sharedManager] saveImage:videoArrowImage msgId:saveFileName picType:@"jpg"];
                }
            }
            
        }
//        else {
//            NSString * strRealVideoPath;
//            if (urlPath!=nil && urlPath.length>=0) {
//                strRealVideoPath = urlPath;
//
//                videoArrowImage = [manager videoConverPhotoWithVideoPath:strRealVideoPath size:model.contentF.size isSender:NO];
//            }
//
//
//        }
        // 判断封面图
        if (urlPath.length > 0 && ![urlPath hasPrefix:@"http"]) {
            
            videoArrowImage = [manager imageConverPhotoWithVideoPath:urlPath size:model.contentF.size isSender:NO];
        }
        
        if (videoArrowImage) {
            self.picView.image = videoArrowImage;
        }
    } else {
        [self.picView setImageWithURL:[NSURL URLWithString:urlPath?:@""] placeholder:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"im_photos"]]];
    }
}

- (void)imageBtnClick
{
    TIMMessageFrame *modelFrame = [[TIMMessageFrame alloc] init];
    
    if ([self.model.type isEqualToString:@"4"]) {
        
        modelFrame.model.mediaPath = self.urlPath;
        modelFrame.model.message.messageId = self.urlPath;
        
        CGRect btnRect = [ICMessageHelper photoFramInWindow:self.picView];
        NSValue *btnValue   = [NSValue valueWithCGRect:btnRect];
        [self routerEventWithName:GXRouterEventVideoTapEventName
                         userInfo:@{MessageKey   : modelFrame,
                                    @"BtnView"   : btnValue,
                                    @"urlPath"   : self.urlPath,
                                    }];
        
    } else {
        
        modelFrame.model.mediaPath = [self.urlPath stringByReplacingOccurrencesOfString:@"isThumbnail=true" withString:@"isThumbnail=false"];
        modelFrame.model.message.messageId = [self.urlPath stringByReplacingOccurrencesOfString:@"isThumbnail=true" withString:@"isThumbnail=false"];
        
        
        CGRect smallRect = [ICMessageHelper photoFramInWindow:self.picView];
        CGRect bigRect   = [ICMessageHelper photoFramInWindow:self.picView];
        NSValue *smallValue = [NSValue valueWithCGRect:smallRect];
        NSValue *bigValue   = [NSValue valueWithCGRect:bigRect];
        [self routerEventWithName:GXRouterEventImageTapEventName
                         userInfo:@{MessageKey   : modelFrame,
                                    @"smallRect" : smallValue,
                                    @"bigRect"   : bigValue,
                                    @"urlPath"   : [self.urlPath stringByReplacingOccurrencesOfString:@"isThumbnail=true" withString:@"isThumbnail=false"],
                                    }];
    }
}

- (UIImageView *)picView {
    if (!_picView) {
        _picView = [[UIImageView alloc] init];
        _picView.contentMode = UIViewContentModeScaleAspectFit;
        _picView.userInteractionEnabled = YES;
    }
    return _picView;
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
