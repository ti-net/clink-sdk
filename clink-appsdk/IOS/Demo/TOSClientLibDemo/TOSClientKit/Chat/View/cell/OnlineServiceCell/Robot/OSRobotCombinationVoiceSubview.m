//
//  OSRobotCombinationVoiceSubview.m
//  TOSClientKit
//
//  Created by 言 on 2022/8/1.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "OSRobotCombinationVoiceSubview.h"
#import "TIMMessageModel.h"
#import "ICRecordManager.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import <TOSClientLib/TOSClientLib.h>
#import <TOSClientLib/CombinationMessage.h>

#import "kitUtils.h"
#import "TIMConstants.h"
#import "YYKit.h"
#import "ICMessageConst.h"
#import "NSString+Extension.h"
#import "UIResponder+GXRouter.h"

@interface OSRobotCombinationVoiceSubview ()

@property (nonatomic, strong) UIButton    *voiceButton;
@property (nonatomic, strong) UIImageView *voiceIcon;
@property (nonatomic, strong) CombinationMessage *model;

@property (nonatomic, strong) NSString *fileUrl;

@property (nonatomic, strong) UIView    *bottomView;

@end

@implementation OSRobotCombinationVoiceSubview

- (void)setupSubviews {
    [super setupSubviews];
    
    self.backgroundColor = [UIColor clearColor];
    [self addSubview:self.bottomView];
    [self addSubview:self.voiceIcon];
    [self addSubview:self.durationLabel];
    [self addSubview:self.voiceButton];
}

- (void)defineLayout {
    [super defineLayout];
    
}

- (void)setWithModel:(CombinationMessage *)model {
    
    self.bottomView.frame = CGRectMake(5, 5, self.tos_width - 10, self.tos_height - 10);
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
    
    self.model = model;
        
    self.voiceIcon.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"left-3"]];
    UIImage *image1 = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"left-1"]];
    UIImage *image2 = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"left-2"]];
    UIImage *image3 = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"left-3"]];
    self.voiceIcon.animationImages = @[image1, image2, image3];
    
    self.voiceIcon.animationDuration = 0.8;

    self.voiceIcon.frame     = CGRectMake(20.f, self.tos_height/2 - 20.f/2, 13.f, 20.f);
    
    self.durationLabel.frame = CGRectMake(CGRectGetMaxX(self.voiceIcon.frame) + 5.f, self.tos_height/2 - 24.f/2, 10.f, 24.f);
    
    self.voiceButton.frame   = self.bounds;

    NSData *data = [NSData dataWithContentsOfURL:[NSURL URLWithString:urlPath]];
    
    NSError *error = nil;
    AVAudioPlayer *player = [[AVAudioPlayer alloc] initWithData:data error:&error];
    
    // 获取时长
    int voiceDuration = (int)player.duration;
    if (voiceDuration < 1) {
        // 小于一秒的显示1秒
        voiceDuration = 1;
    }
    
    [self.durationLabel setText:[NSString stringWithFormat:@"%d''",voiceDuration]];
    [self.durationLabel sizeToFit];
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
    voiceBtn.selected = !voiceBtn.selected;
    NSLog(@"fileUrl ==== %@",self.fileUrl);
    [self routerEventWithName:GXRouterEventCombinationVoiceTapEventName
                     userInfo:@{MessageKey : self.fileUrl,
                                VoiceIcon  : self.voiceIcon
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
    if (nil == _durationLabel) {
        _durationLabel = [[UILabel alloc] init];
        _durationLabel.font = MessageFont;
        [_durationLabel setTextColor:[UIColor blackColor]];
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

- (UIView *)bottomView {
    if (!_bottomView) {
        _bottomView = [[UIView alloc] init];
        _bottomView.backgroundColor = [UIColor clearColor];
        _bottomView.layer.borderColor = [UIColor grayColor].CGColor;
        _bottomView.layer.borderWidth = 0.5f;
        _bottomView.layer.cornerRadius = 5.f;
        _bottomView.layer.masksToBounds = YES;
    }
    return _bottomView;
}

@end
