//
//  ICChatMessageRichImageCell.m
//  TOSClientKit
//
//  Created by 言 on 2022/6/14.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "ICChatMessageRichImageCell.h"
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
#import "TIMConstants.h"
#import "ICMessageHelper.h"
#import "kitUtils.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"

#import <TOSClientLib/CombinationMessage.h>

@interface ICChatMessageRichImageCell ()

@property (nonatomic, strong) UIImageView *picView;
@property (nonatomic, copy) NSString *urlPath;
@property (nonatomic, strong) RichTextMessage *model;

@property (nonatomic, strong) CombinationMessage *combinationModel;

@end

@implementation ICChatMessageRichImageCell

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

- (NSString *)getParamByName:(NSString *)name URLString:(NSString *)url{
    NSError *error;
    NSString *regTags=[[NSString alloc] initWithFormat:@"(^|&|\\?)+%@=+([^&]*)(&|$)", name];
    NSRegularExpression *regex = [NSRegularExpression regularExpressionWithPattern:regTags
                                                                           options:NSRegularExpressionCaseInsensitive
                                                                             error:&error];
   
    // 执行匹配的过程
    NSArray *matches = [regex matchesInString:url
                                      options:0
                                        range:NSMakeRange(0, [url length])];
    for (NSTextCheckingResult *match in matches) {
        NSString *tagValue = [url substringWithRange:[match rangeAtIndex:2]];  // 分组2所对应的串
        return tagValue;
    }
    return @"";
}

- (void)setWithModel:(RichTextMessage *)model {
    self.picView.frame = model.contentF;
    self.picView.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"im_photos"]];
    
    ICMediaManager *manager = [ICMediaManager sharedManager];
    if (model.urlPath &&
        [model.urlPath isKindOfClass:[NSString class]] &&
        ![model.urlPath hasPrefix:@"http"]) {
        
        model.fileKey = model.urlPath;
        if ([model.urlPath hasPrefix:@"article/images"] ||
            [model.urlPath hasPrefix:@"file/attachment"]) { //知识库类型
            
            model.urlPath = [[NSString stringWithFormat:@"%@/api/kb/articles/images?filePath=%@",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl],model.urlPath] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        } else if ([model.urlPath hasPrefix:@"/basic-api/oss/"]) {    //机器人类型
            NSString *path = @"";
            if (![model.urlPath hasPrefix:@"/"]) {
                path = @"/";
            }
            NSString * hostURL = @"";
            /// 上海
            if ([[[OnlineDataSave shareOnlineDataSave] getOnlineUrl] isEqualToString:@"https://chat-app-sh.clink.cn"]) {
                hostURL = @"https://webchat-sh.clink.cn";
            }
            /// 北京
            else if ([[[OnlineDataSave shareOnlineDataSave] getOnlineUrl] isEqualToString:@"https://chat-app-bj.clink.cn"]) {
                hostURL = @"https://webchat-bj.clink.cn";
            }/// 北京Test0
            else if ([[[OnlineDataSave shareOnlineDataSave] getOnlineUrl] isEqualToString:@"https://chat-app-bj-test0.clink.cn"]) {
                hostURL = @"https://webchat-bj-test0.clink.cn";
            }
            model.urlPath = [[NSString stringWithFormat:@"%@%@%@%@%@%@%@", hostURL, @"/api/bot_media?fileKey=",path,model.urlPath, @"&provider=", self.robotProvider, @"&isDownload=false&isThumbnail=true"] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        } else {
            NSString *path = @"";
            if (![model.urlPath hasPrefix:@"/"]) {
                path = @"/";
            }
            model.urlPath = [[NSString stringWithFormat:@"%@%@%@",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl],path,model.urlPath] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        }
    } else {
        model.fileKey = model.urlPath;
        model.urlPath = [model.urlPath stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    }
    
    self.urlPath = [model.urlPath mutableCopy];
    self.model = model;
    
    if ([model.type isEqualToString:@"video"]) {
        
        UIImage *videoArrowImage;
        
        if (model.urlPath &&
            [model.urlPath isKindOfClass:[NSString class]]) {
            
            NSString *fileKey = [NSString stringWithFormat:@"%@",model.fileKey];
            
            NSString *charaters = @"?!@#$^&%*+,:;=.'\"`<>()[]{}/\\| 《》";
            NSCharacterSet *set = [[NSCharacterSet characterSetWithCharactersInString:charaters] invertedSet];
            fileKey = [fileKey stringByAddingPercentEncodingWithAllowedCharacters:set];
            
            NSString * localPath;
            localPath = [[ICMediaManager sharedManager] smallImgPath:fileKey];
            videoArrowImage = [UIImage imageWithContentsOfFile:localPath];
            
            if (!videoArrowImage) {
                UIImage *resultImg = [self getVideoPreViewImage:[NSURL URLWithString:model.urlPath]];
                videoArrowImage = [UIImage addImage2:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"video_play_btn_bg"]] toImage:resultImg];
                if (videoArrowImage) {
                    [[ICMediaManager sharedManager] saveImage:videoArrowImage msgId:fileKey picType:@"jpg"];
                }
            }
            
        }
        // 判断封面图
        if (model.urlPath.length > 0 && ![model.urlPath hasPrefix:@"http"]) {
            
            videoArrowImage = [manager imageConverPhotoWithVideoPath:model.urlPath size:model.contentF.size isSender:NO];
        }
        
        if (videoArrowImage) {
            self.picView.image = videoArrowImage;
        }
    } else {
        TIMKitLog(@"urlPath ==== %@",model.urlPath);
        [self.picView setImageWithURL:[NSURL URLWithString:model.urlPath?:@""] placeholder:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"im_photos"]]];
    }
}

- (void)imageBtnClick
{
    TIMMessageFrame *modelFrame = [[TIMMessageFrame alloc] init];
    
    modelFrame.model.mediaPath = self.urlPath;
    modelFrame.model.message.messageId = self.urlPath;
    NSLog(@"urlPath ==== %@",self.urlPath);
    if ([self.model.type isEqualToString:@"video"]) {
        
        CGRect btnRect = [ICMessageHelper photoFramInWindow:self.picView];
        NSValue *btnValue   = [NSValue valueWithCGRect:btnRect];
        [self routerEventWithName:GXRouterEventVideoTapEventName
                         userInfo:@{MessageKey   : modelFrame,
                                    @"BtnView"   : btnValue,
                                    @"urlPath"   : self.urlPath?:@"",
                                    }];
        
    } else {
        CGRect smallRect = [ICMessageHelper photoFramInWindow:self.picView];
        CGRect bigRect   = [ICMessageHelper photoFramInWindow:self.picView];
        NSValue *smallValue = [NSValue valueWithCGRect:smallRect];
        NSValue *bigValue   = [NSValue valueWithCGRect:bigRect];
        [self routerEventWithName:GXRouterEventImageTapEventName
                         userInfo:@{MessageKey   : modelFrame,
                                    @"smallRect" : smallValue,
                                    @"bigRect"   : bigValue,
                                    @"urlPath"   : self.urlPath,
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
