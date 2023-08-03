//
//  ICChatMessageVoiceCell.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/13.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICChatMessageVoiceCell.h"
#import "TIMMessageModel.h"
#import "ICRecordManager.h"
#import "TIMConstants.h"
#import "kitUtils.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import <TOSClientLib/TOSClientLib.h>

@interface ICChatMessageVoiceCell ()

@property (nonatomic, strong) UIButton    *voiceButton;
@property (nonatomic, strong) UIImageView *voiceIcon;
@property (nonatomic, strong) UIView      *redView;

@end

@implementation ICChatMessageVoiceCell


- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.voiceIcon];
        [self.contentView addSubview:self.durationLabel];
        [self.contentView addSubview:self.voiceButton];
        [self.contentView addSubview:self.redView];

    }
    return self;
}


- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    self.readLabel.frame = modelFrame.unReadLabelF;

    if (modelFrame.model.isSender) {  // sender
        [self.durationLabel setTextColor:[UIColor blackColor]];
        self.voiceIcon.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"right-3"]];
        UIImage *image1 = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"right-1"]];
        UIImage *image2 = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"right-2"]];
        UIImage *image3 = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"right-3"]];
        self.voiceIcon.animationImages = @[image1, image2, image3];
    } else {                          // receive
        [self.durationLabel setTextColor:[UIColor blackColor]];
        self.voiceIcon.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"left-3"]];
        UIImage *image1 = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"left-1"]];
        UIImage *image2 = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"left-2"]];
        UIImage *image3 = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"left-3"]];
        self.voiceIcon.animationImages = @[image1, image2, image3];
    }
    self.voiceIcon.animationDuration = 0.8;
    if (modelFrame.model.message.status == ICMessageStatus_read) {
        self.redView.hidden  = YES;
    } else if (modelFrame.model.message.status == ICMessageStatus_unRead) {
        self.redView.hidden  = NO;
    }
    if ([[kitUtils readNSUserDefaultsVoiceReadStatus:modelFrame.model.message.messageId from:modelFrame.model.message.from to:modelFrame.model.message.to] isEqualToString:@"true"]) {
        self.redView.hidden  = YES;
    }
    self.durationLabel.frame = modelFrame.durationLabelF;
    
    self.voiceIcon.frame     = modelFrame.voiceIconF;
    self.voiceButton.frame   = modelFrame.bubbleViewF;
    self.redView.frame       = modelFrame.redViewF;
    
//    NSString *voicePath = [selfWeak mediaPath:modelFrame.model.mediaPath];
//    NSLog(@"mediaPath = %@ voicePath = %@",modelFrame.model.mediaPath,voicePath);
//    long voicelengthWithMs = (long)[[ICRecordManager shareManager] durationWithVideo:[NSURL fileURLWithPath:voicePath]];
//    NSLog(@"modelFrame.model.message.voiceDuration = %@",modelFrame.model.message.voiceDuration);
    long voicelengthWithMs = (long)[modelFrame.model.message.voiceDuration longLongValue];
    int showDuration = (int)(voicelengthWithMs/ 1000);
    if (voicelengthWithMs > 0 && voicelengthWithMs < 1000) {
        showDuration = 1;
    }

//    NSLog(@"set duraion = %d type = %@",showDuration,modelFrame.model.message.type);
    [self.durationLabel setText:[NSString stringWithFormat:@"%d''",showDuration]];
    [self.durationLabel sizeToFit];
//    [self BMWCustomerUnreadStatusDisplayWithModel:modelFrame.model];

}

// 文件路径
- (NSString *)mediaPath:(NSString *)originPath
{
    // 这里文件路径重新给，根据文件名字来拼接
    NSString *name = [[originPath lastPathComponent] stringByDeletingPathExtension];
    return [[ICRecordManager shareManager] receiveVoicePathWithFileKey:name];
}


#pragma mark - respond Method

- (void)voiceButtonClicked:(UIButton *)voiceBtn
{
    @WeakObj(self)
    voiceBtn.selected = !voiceBtn.selected;
    [selfWeak routerEventWithName:GXRouterEventVoiceTapEventName
                     userInfo:@{MessageKey : selfWeak.modelFrame,
                                VoiceIcon  : selfWeak.voiceIcon,
                                RedView    : selfWeak.redView
                                }];
}


#pragma mark - Getter

- (UIButton *)voiceButton
{
    if (nil == _voiceButton) {
        _voiceButton = [UIButton buttonWithType:UIButtonTypeCustom];
        [_voiceButton addTarget:self action:@selector(voiceButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
    }
    return _voiceButton;
}

- (UILabel *)durationLabel
{
    if (nil == _durationLabel ) {
        _durationLabel = [[UILabel alloc] init];
        _durationLabel.font = MessageFont;
    }
    return _durationLabel;
}

- (UIImageView *)voiceIcon
{
    if (nil == _voiceIcon) {
        _voiceIcon = [[UIImageView alloc] init];
    }
    return _voiceIcon;
}

- (UIView *)redView
{
    if (nil == _redView) {
        _redView = [[UIView alloc] init];
        _redView.layer.masksToBounds = YES;
        _redView.layer.cornerRadius = 4;
        _redView.backgroundColor = TOSHexAColor(0xf05e4b,1.0);
    }
    return _redView;
}

@end
