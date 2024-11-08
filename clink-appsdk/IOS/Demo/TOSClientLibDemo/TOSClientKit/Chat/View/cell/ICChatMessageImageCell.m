//
//  ICChatMessageImageCell.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/13.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICChatMessageImageCell.h"
#import "ICMediaManager.h"
#import "TIMMessageModel.h"
#import "TIMICMessage.h"
#import "ICFileTool.h"
#import "ICMessageHelper.h"
#import "UIImage+TIMGIF.h"
//#import "UIButton+WebCache.h"
#import "TIMConstants.h"
#import "NSString+Extension.h"
#import "UIImage+Extension.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
//#import <SDWebImage/SDWebImage.h>
#import <TOSClientKit/TOSClientKit.h>
#import "NSObject+TIMImage.h"
#import "kitUtils.h"

//#import "TIMSDWebImageDownloader.h"

@interface ICChatMessageImageCell ()

@property (nonatomic, strong) UIButton *auditRejectedBtn;
@property (nonatomic, strong) UIButton *auditApprovedBtn;
@property (nonatomic, copy) NSString *     strEnableLookUp;           // 是否有查看权限
@property (nonatomic, copy) NSString *     strEnableAudit;             // 是否有审核权限
@property (nonatomic, strong) NSNumber* msgAuditStatus;          // 消息的审核状态
@end

@implementation ICChatMessageImageCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.imageBtn];
    }
    return self;
}



#pragma mark - Private Method

- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    self.readLabel.frame = modelFrame.unReadLabelF;

    __block UIImage *image;
    ICMediaManager *manager = [ICMediaManager sharedManager];
    
    NSString * strPicType = [modelFrame.model.mediaPath pathExtension];
    NSLog(@"modelFrame.model.mediaPath === %@",modelFrame.model.mediaPath);
    if ([strPicType isEqualToString:kGIFTimageType]) {
        NSData *imageData = [NSData dataWithContentsOfFile:modelFrame.model.mediaPath];
        image = [UIImage sd_animatedGIFWithData:imageData];
    }else{
        image = [manager imageWithLocalPath:[manager imagePathWithName:modelFrame.model.mediaPath.lastPathComponent] msgId:modelFrame.model.message.messageId?:@""];
    }
    self.imageBtn.frame = modelFrame.picViewF;
    self.strEnableLookUp = modelFrame.model.strEnableLookUp;
    self.strEnableAudit = modelFrame.model.strEnableAudit;
    self.msgAuditStatus = modelFrame.model.auditStatus;
    self.bubbleView.userInteractionEnabled = _imageBtn.imageView.image != nil;
    self.bubbleView.image = nil;
    self.auditStatusBtn.frame = modelFrame.aduitStatusViewF;
    
    if ([TOSKitCustomInfo shareCustomInfo].chatBubble_CornerType != BubbleCornerTypeAll && [TOSKitCustomInfo shareCustomInfo].chatBubble_CornerType != BubbleCornerTypeNormal) {
        self.imageBtn.layer.maskedCorners = self.bubbleView.layer.maskedCorners;
    }
    
  
    if (modelFrame.model.isSender) {    // 发送者
        self.imageBtn.layer.cornerRadius = [TOSKitCustomInfo shareCustomInfo].senderBubble_cornerRadius;
        if (image == nil) {
            TIMKitLog(@"image == nil");
        }else{
            // 如果本地路径的图片存在 那么直接显示
            if ([strPicType isEqualToString:kGIFTimageType]) {
                [self performSelectorOnMainThread:@selector(mySetImageWithImage:) withObject:image waitUntilDone:YES modes:@[NSDefaultRunLoopMode]];
            }else{
                [self performSelectorOnMainThread:@selector(mySetImageWithImage:) withObject:image waitUntilDone:YES modes:@[NSDefaultRunLoopMode]];
            }
        }
    } else {
        self.imageBtn.layer.cornerRadius = [TOSKitCustomInfo shareCustomInfo].receiveBubble_cornerRadius;
        NSString *orgImgPath= @"";
        if ([strPicType isEqualToString:kGIFTimageType]) {
            orgImgPath = [manager smallGifImgPath:modelFrame];
            if ([ICFileTool fileExistsAtPath:orgImgPath]) {
                [self performSelectorOnMainThread:@selector(mySetImageWithImage:) withObject:image waitUntilDone:YES modes:@[NSDefaultRunLoopMode]];
            }
        }else{
            orgImgPath = [manager originImgPath:modelFrame];
            if ([ICFileTool fileExistsAtPath:orgImgPath]) {
                
//                dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
////                    NSLog(@"current thread0 == %@",[NSThread currentThread]);
//                    UIImage *orgImg = [manager imageWithLocalPath:orgImgPath msgId:modelFrame.model.message.messageId?:@""];
//                    UIImage *arrowImage = [manager arrowMeImage:orgImg size:modelFrame.picViewF.size mediaPath:orgImgPath isSender:modelFrame.model.isSender];
//
//                    dispatch_async(dispatch_get_main_queue(), ^{
//                        [self performSelectorOnMainThread:@selector(mySetImageWithImage:) withObject:arrowImage waitUntilDone:YES modes:@[NSDefaultRunLoopMode]];
//                    });
//
//                });
                UIImage *orgImg = [manager imageWithLocalPath:orgImgPath msgId:modelFrame.model.message.messageId?:@""];
                [self performSelectorOnMainThread:@selector(mySetImageWithImage:) withObject:orgImg waitUntilDone:YES modes:@[NSDefaultRunLoopMode]];
                
               
//                [self.imageBtn setBackgroundImage:arrowImage forState:UIControlStateNormal];
            }else{
                
//                dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
////                    NSLog(@"current thread0 == %@",[NSThread currentThread]);
//                    UIImage *arrowImage = [manager arrowMeImage:image size:modelFrame.picViewF.size mediaPath:modelFrame.model.mediaPath isSender:modelFrame.model.isSender];
//
//                    dispatch_async(dispatch_get_main_queue(), ^{
//                        [self performSelectorOnMainThread:@selector(mySetImageWithImage:) withObject:arrowImage waitUntilDone:YES modes:@[NSDefaultRunLoopMode]];
//                    });
//
//                });
                [self performSelectorOnMainThread:@selector(mySetImageWithImage:) withObject:image waitUntilDone:YES modes:@[NSDefaultRunLoopMode]];
                
            }
        }
    }

}

- (void)mySetImageWithImage:(UIImage *)arrowImage{
    NSLog(@"current thread == %@",[NSThread currentThread]);
    [self.imageBtn setBackgroundImage:arrowImage forState:UIControlStateNormal];

}


- (void)imageBtnClick:(UIButton *)btn
{
    if (btn.currentBackgroundImage == nil) {
        return;
    }
    CGRect smallRect = [ICMessageHelper photoFramInWindow:btn];
    CGRect bigRect   = [ICMessageHelper photoLargerInWindow:btn];
    NSValue *smallValue = [NSValue valueWithCGRect:smallRect];
    NSValue *bigValue   = [NSValue valueWithCGRect:bigRect];
    [self routerEventWithName:GXRouterEventImageTapEventName
                     userInfo:@{MessageKey   : self.modelFrame,
                                @"smallRect" : smallValue,
                                @"bigRect"   : bigValue
                                }];
}

- (void)imageRejectedBtnClick:(UIButton *)btn
{
    [self routerEventWithName:GXRouterEventImageRejectedTapEventName
                     userInfo:@{MessageKey   : self.modelFrame
                                }];
}

- (void)imageApprovedBtnClick:(UIButton *)btn
{
    [self routerEventWithName:GXRouterEventImageApprovedTapEventName
                     userInfo:@{MessageKey   : self.modelFrame
                                }];
}



#pragma mark - Getter

- (UIButton *)imageBtn
{
    if (nil == _imageBtn) {
        _imageBtn = [[UIButton alloc] init];
        [_imageBtn addTarget:self action:@selector(imageBtnClick:) forControlEvents:UIControlEventTouchUpInside];
        _imageBtn.imageEdgeInsets = UIEdgeInsetsMake(0, 0, 0, 0);
        _imageBtn.layer.masksToBounds = YES;
//        _imageBtn.layer.cornerRadius = 5;
        _imageBtn.clipsToBounds = YES;
        _imageBtn.imageView.contentMode = UIViewContentModeScaleAspectFit;
        UIImage * image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"loadImage.png"]];
        [_imageBtn setBackgroundImage:image forState:(UIControlStateNormal)];
        
    }
    return _imageBtn;
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

@end
