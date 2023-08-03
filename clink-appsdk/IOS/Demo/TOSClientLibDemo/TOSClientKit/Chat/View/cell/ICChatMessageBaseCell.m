//
//  ICChatMessageBaseCell.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/12.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICChatMessageBaseCell.h"
#import "TIMMessageModel.h"
#import "TIMICMessage.h"
#import "TIMConstants.h"
#import "ICMediaManager.h"
#import <TOSClientLib/TOSClientLib.h>
#import <TOSClientKit/TOSClientKit.h>
#import "UIImageView+WebCache.h"
#import "kitUtils.h"
#import "UIImage+PureColorImage.h"
#import "UIImage+Extension.h"
#import "UIImage+GIF.h"
#import <TOSClientLib/TOSClientLib.h>
#import "YYKit.h"
#import "UIImageView+YYWebImage.h"

@interface ICChatMessageBaseCell ()


@end

@implementation ICChatMessageBaseCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self setupUI];
        self.backgroundColor = [UIColor clearColor];
        self.selectionStyle = UITableViewCellSelectionStyleNone;
        
        UILongPressGestureRecognizer *longRecognizer = [[UILongPressGestureRecognizer alloc] initWithTarget:self action:@selector(longPressRecognizer:)];
        longRecognizer.minimumPressDuration = 0.5;
        [self addGestureRecognizer:longRecognizer];
    }
    return self;
}

#pragma mark - UI

- (void)setupUI {
    [self.contentView addSubview:self.messageTopTimeView];
    [self.contentView addSubview:self.messageTopView];
    [self.contentView addSubview:self.bubbleView];
    [self.contentView addSubview:self.nameLabel];
    [self.contentView addSubview:self.headImageView];
    [self.contentView addSubview:self.activityView];
    [self.contentView addSubview:self.retryButton];
    [self.contentView addSubview:self.readLabel];
}

#pragma mark - Getter and Setter

-(UILabel *)nameLabel{
    if (!_nameLabel) {
        _nameLabel = [[UILabel alloc]init];
        _nameLabel.backgroundColor = [UIColor clearColor];
        _nameLabel.textColor = [TOSKitCustomInfo shareCustomInfo].chatMessage_visitorName_textColor;
        _nameLabel.font = [TOSKitCustomInfo shareCustomInfo].chatMessage_visitorName_font;
    }
    return _nameLabel;
}

- (ICHeadImageView *)headImageView {
    if (_headImageView == nil) {
        _headImageView = [[ICHeadImageView alloc] init];
        _headImageView.userInteractionEnabled = YES;
//        [_headImageView setColor:TOSColor(219, 220, 220) bording:0.0];
        UILongPressGestureRecognizer *longGes = [[UILongPressGestureRecognizer alloc] initWithTarget:self action:@selector(headClicked:)];
        longGes.minimumPressDuration = .3f;
        [_headImageView addGestureRecognizer:longGes];
    }
    return _headImageView;
}

- (UIImageView *)bubbleView {
    if (_bubbleView == nil) {
        _bubbleView = [[UIImageView alloc] init];
    }
    return _bubbleView;
}

- (UIActivityIndicatorView *)activityView {
    if (_activityView == nil) {
        _activityView = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
    }
    return _activityView;
}

- (UIButton *)retryButton {
    if (_retryButton == nil) {
        _retryButton = [[UIButton alloc] init];
        /// 是否显示标题
        if ([TOSKitCustomInfo shareCustomInfo].resendButton.currentTitle) {
            [_retryButton setTitle:[TOSKitCustomInfo shareCustomInfo].resendButton.titleLabel.text forState:(UIControlStateNormal)];
            _retryButton.titleLabel.font = [TOSKitCustomInfo shareCustomInfo].resendButton.titleLabel.font;
        }
        /// 是否设置字体颜色
        if ([TOSKitCustomInfo shareCustomInfo].resendButton.currentTitleColor) {
            UIColor * textColor = [TOSKitCustomInfo shareCustomInfo].resendButton.currentTitleColor;
            [_retryButton setTitleColor:textColor forState:(UIControlStateNormal)];
        }
        
        /// 是否显示图片
        if ([TOSKitCustomInfo shareCustomInfo].resendButton.currentImage) {
            [_retryButton setImage:[TOSKitCustomInfo shareCustomInfo].resendButton.currentImage forState:UIControlStateNormal];
        }
        /// 是否显示背景图片
        if ([TOSKitCustomInfo shareCustomInfo].resendButton.currentBackgroundImage) {
            [_retryButton setBackgroundImage:[TOSKitCustomInfo shareCustomInfo].resendButton.currentBackgroundImage forState:(UIControlStateNormal)];
        }
        if ([TOSKitCustomInfo shareCustomInfo].resendButton.currentImage && [TOSKitCustomInfo shareCustomInfo].resendButton.currentTitle) {
            _retryButton.imageEdgeInsets = [TOSKitCustomInfo shareCustomInfo].resendButton.imageEdgeInsets;
            _retryButton.titleEdgeInsets = [TOSKitCustomInfo shareCustomInfo].resendButton.titleEdgeInsets;
        }
        
        _retryButton.layer.cornerRadius = [TOSKitCustomInfo shareCustomInfo].resendButton.layer.cornerRadius;
        _retryButton.layer.borderColor = [TOSKitCustomInfo shareCustomInfo].resendButton.layer.borderColor;
        _retryButton.layer.borderWidth = [TOSKitCustomInfo shareCustomInfo].resendButton.layer.borderWidth;
        
//        _retryButton.backgroundColor = UIColor.orangeColor;
        [_retryButton addTarget:self action:@selector(retryButtonClick:) forControlEvents:UIControlEventTouchUpInside];
        
    }
    return _retryButton;
}

-(ICMessageTopView *)messageTopView{
    if (_messageTopView == nil) {
        _messageTopView = [[ICMessageTopView alloc] init];
    }
    return _messageTopView;
}

-(ICMessageTopTimeView *)messageTopTimeView{
    if (_messageTopTimeView == nil) {
        _messageTopTimeView = [[ICMessageTopTimeView alloc] init];
    }
    return _messageTopTimeView;
}


- (UILabel *)readLabel
{
    if (nil == _readLabel) {
        _readLabel = [[UILabel alloc] init];
        _readLabel.font = MessageFont12;
        _readLabel.textColor = TOSHexAColor(0x999999,1.0);
//        _readLabel.textAlignment = NSTextAlignmentRight;
//        _readLabel.backgroundColor = [UIColor cyanColor];
    }
    return _readLabel;
}


#pragma mark - Respond Method

- (void)retryButtonClick:(UIButton *)btn {
    @WeakObj(self)
    if ([selfWeak.longPressDelegate respondsToSelector:@selector(reSendMessage:)]) {
        [selfWeak.longPressDelegate reSendMessage:selfWeak];
    }
}


- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    _modelFrame = modelFrame;
    
    TIMMessageModel *messageModel = modelFrame.model;
    self.headImageView.frame     = modelFrame.headImageViewF;
    self.nameLabel.frame = modelFrame.headNameF;
    @WeakObj(self)
    dispatch_async(dispatch_get_main_queue(), ^{
        @StrongObj(self)
        self.bubbleView.frame        = modelFrame.bubbleViewF;
    });
    self.messageTopView.frame    = modelFrame.topViewF;
    self.messageTopTimeView.frame= modelFrame.topTimeViewF;
    
    
    if ([modelFrame.model.message.from isEqualToString:[[OnlineDataSave shareOnlineDataSave] getVisitorId]]) {//访客端
        [self.headImageView.imageView setImageWithURL:[NSURL URLWithString:[[OnlineDataSave shareOnlineDataSave] getVisitorHeaderUrl]] placeholder:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"default_avatar"]]];
//        self.nameLabel.textAlignment = NSTextAlignmentRight;
        self.nameLabel.text = [[OnlineDataSave shareOnlineDataSave] getVisitorName];
        // 配置是否显示访客端昵称
        if (![TOSKitCustomInfo shareCustomInfo].Chat_visitorName_show) {
            self.nameLabel.text = @"";
        }
    }else{//坐席端
       NSString*name = [[ICMediaManager sharedManager] getHeadNameWithUserId:modelFrame.model.message.from];
       NSString*headUrl = [[ICMediaManager sharedManager] getHeadImgUrlWithUserId:modelFrame.model.message.from];
        
//        self.nameLabel.textAlignment = NSTextAlignmentLeft;

        if (name.length == 0 || headUrl.length == 0) {
            //获取客服头像信息
            [[OnlineRequestManager sharedCustomerManager] getClientInfoWithSender:modelFrame.model.message.from
                                                                       senderType:@"1"
                                                                          success:^(OnlineClientInfoModel * _Nonnull model) {
                NSLog(@"headUrl ===== %@",[model yy_modelToJSONObject]);
                if (model.avatar != nil && model.avatar.length > 0) {
                    //保存头像
                    [[ICMediaManager sharedManager] savaHeadImgUrl:model.avatar userId:modelFrame.model.message.from];
                    [self.headImageView.imageView setImageWithURL:[NSURL URLWithString:model.avatar] placeholder:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"default_avatar"]]];
                }else{
                    if (headUrl.length>0) {
                        [self.headImageView.imageView setImageWithURL:[NSURL URLWithString:headUrl] placeholder:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"default_avatar"]]];
                    }else{
                        self.headImageView.imageView.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"default_avatar"]];
                    }
                }
                if (model.nickName != nil && model.nickName.length > 0) {
                    //保存名称
                    [[ICMediaManager sharedManager] savaHeadName:model.nickName userId:modelFrame.model.message.from];
                    self.nameLabel.text = model.nickName;
                }else{
                    if (name.length>0) {
                        self.nameLabel.text = name;
                    }else{
                        self.nameLabel.text = @"客服";
                    }
                }
                
            } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
                
                if (name.length>0) {
                    self.nameLabel.text = name;
                }else{
                    self.nameLabel.text = @"客服";
                }
                
                if (headUrl.length>0) {
                    [self.headImageView.imageView setImageWithURL:[NSURL URLWithString:headUrl] placeholder:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"default_avatar"]]];
                }else{
                    self.headImageView.imageView.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"default_avatar"]];
                }
            }];

        }else{
            self.nameLabel.text = name;
            if ([headUrl isEqualToString:@"customer_service_default_avatar"]) {
                self.headImageView.imageView.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"customer_service_default_avatar"]];
            } else if ([headUrl isEqualToString:@"default_avatar"]) {
                self.headImageView.imageView.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"default_avatar"]];
            } else {
                [self.headImageView.imageView setImageWithURL:[NSURL URLWithString:headUrl] placeholder:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"default_avatar"]]];
            }
        }
        // 配置是否显示坐席或机器人的昵称或工号
        if (![TOSKitCustomInfo shareCustomInfo].Chat_tosRobotName_show) {
            self.nameLabel.text = @"";
        }
    }

    
    [self.messageTopTimeView messageShowTimeLine:modelFrame.model.message.msgDate show:modelFrame.model.message.showTime];
    
    if (messageModel.isSender) {    // 发送者
        self.nameLabel.textAlignment = NSTextAlignmentRight;
        self.nameLabel.textColor = [TOSKitCustomInfo shareCustomInfo].chatMessage_visitorName_textColor;
        self.nameLabel.font = [TOSKitCustomInfo shareCustomInfo].chatMessage_visitorName_font;
        @WeakObj(self)
        dispatch_async(dispatch_get_main_queue(), ^{
            @StrongObj(self)
            self.activityView.frame  = modelFrame.activityF;
            self.retryButton.frame   = modelFrame.retryButtonF;
            NSLog(@"modelFrame.retryButtonF : %@", NSStringFromCGRect(modelFrame.retryButtonF));
        });
        
        [self.messageTopView messageSendName:@"" isSender:YES date:modelFrame.model.message.msgDate.timeIntervalSince1970];
        switch (modelFrame.model.message.deliveryState) { // 发送状态
            case ICMessageDeliveryState_Delivering:
            {
                @WeakObj(self)
                dispatch_async(dispatch_get_main_queue(), ^{
                    @StrongObj(self)
                    [self.activityView startAnimating];
                });
                [self.activityView setHidden:NO];
                [self.retryButton setHidden:YES];
                
            }
                break;
            case ICMessageDeliveryState_Delivered:
            {
                @WeakObj(self)
                dispatch_async(dispatch_get_main_queue(), ^{
                    @StrongObj(self)
                    [self.activityView stopAnimating];
                });
                [self.activityView setHidden:YES];
                [self.retryButton setHidden:YES];
                
            }
                break;
            case ICMessageDeliveryState_Failure:
            case ICMessageDeliveryState_Failure_SensitiveWords:
            {
                [self.activityView stopAnimating];
                [self.activityView setHidden:YES];
                [self.retryButton setHidden:NO];
                self.readLabel.text = @"";
            }
                break;
            default:
                break;
        }
        if ([modelFrame.model.message.type isEqualToString:TypeFile] ||[modelFrame.model.message.type isEqualToString:TypePicText]) {
            UIImage * srcImage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"liaotianfile"]];
            self.bubbleView.image = [[srcImage stretchableImageWithLeftCapWidth:10 topCapHeight:30] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
        } else if([modelFrame.model.message.type isEqualToString:TypeCustom] ||
                  [modelFrame.model.message.type isEqualToString:TypeCustomFile]){
            NSDictionary * contentDic = [kitUtils dictionaryWithJsonString:modelFrame.model.message.content];
            if (contentDic && [contentDic objectForKey:@"template"] &&
                [contentDic[@"template"] isEqualToString:@"tim-rtcMedia"]) {
                UIImage * srcImage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"liaotianbeijing2"]];
                self.bubbleView.image = [[srcImage stretchableImageWithLeftCapWidth:10 topCapHeight:30] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
            }else{
                UIImage * srcImage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"custombeijing1"]];
                self.bubbleView.image = [[srcImage stretchableImageWithLeftCapWidth:10 topCapHeight:30] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
            }
        } else{
            UIImage * srcImage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"liaotianbeijing2"]];
            self.bubbleView.image = [[srcImage stretchableImageWithLeftCapWidth:10 topCapHeight:30] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
        }
        [self setBubbleColor:YES];
    } else {    // 接收者
        self.nameLabel.textAlignment = NSTextAlignmentLeft;
        self.nameLabel.textColor = [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotName_textColor;
        self.nameLabel.font = [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotName_font;
        NSString * strName = [kitUtils isBlankString:modelFrame.model.message.senderName]?@"":modelFrame.model.message.senderName;
        if ([kitUtils isBlankString:strName]) {
            
        }else{
            [self.messageTopView messageSendName:strName isSender:NO date:modelFrame.model.message.msgDate.timeIntervalSince1970];
        }
        self.retryButton.hidden  = YES;
        if([modelFrame.model.message.type isEqualToString:TypeCustom] ||
           [modelFrame.model.message.type isEqualToString:TypeCustomFile]){
            NSDictionary * contentDic = [kitUtils dictionaryWithJsonString:modelFrame.model.message.content];
            if (contentDic && [contentDic objectForKey:@"template"] &&
                [contentDic[@"template"] isEqualToString:@"tim-rtcMedia"]) {
                UIImage * srcImage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"liaotianbeijing1"]];
                self.bubbleView.image = [[srcImage stretchableImageWithLeftCapWidth:10 topCapHeight:30] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
            }else{
                UIImage * srcImage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"custombeijing1"]];
                self.bubbleView.image = [[srcImage stretchableImageWithLeftCapWidth:10 topCapHeight:30] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
            }
        }else{
            UIImage * srcImage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"liaotianbeijing1"]];
            self.bubbleView.image = [[srcImage stretchableImageWithLeftCapWidth:10 topCapHeight:30] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
        }
        [self setBubbleColor:NO];
    }
}

- (void)headClicked:(UILongPressGestureRecognizer *)sender {
    
    if (sender.state == UIGestureRecognizerStateEnded &&
        [self.longPressDelegate respondsToSelector:@selector(headImageClicked:)] &&
        _modelFrame.model &&
        !_modelFrame.model.isSender) { //校验数据是否存在，是否是对方
        [self.longPressDelegate headImageClicked:_modelFrame.model];
    }
}

- (void)setBubbleColor:(BOOL)isSender{
  
    /// 设置圆角弧度
    if (isSender) {
        _bubbleView.layer.cornerRadius = [TOSKitCustomInfo shareCustomInfo].senderBubble_cornerRadius;
        _bubbleView.image = [UIImage imageWithColor:[TOSKitCustomInfo shareCustomInfo].senderBubble_backGround];
        switch ([TOSKitCustomInfo shareCustomInfo].chatBubble_CornerType) {
            case BubbleCornerTypeLeft: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMinYCorner | kCALayerMinXMaxYCorner;
                break;
            }
            case BubbleCornerTypeLeftTop: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMinYCorner;
                break;
            }
            case BubbleCornerTypeLeftBottom: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMaxYCorner;
                break;
            }
            case BubbleCornerTypeRight: {
                self.bubbleView.layer.maskedCorners = kCALayerMaxXMinYCorner | kCALayerMaxXMaxYCorner;
                break;
            }
            case BubbleCornerTypeRightTop: {
                self.bubbleView.layer.maskedCorners = kCALayerMaxXMinYCorner;
                break;
            }
            case BubbleCornerTypeRightBottom: {
                self.bubbleView.layer.maskedCorners = kCALayerMaxXMaxYCorner;
                break;
            }
            case BubbleCornerTypeTop: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMinYCorner | kCALayerMaxXMinYCorner;
                break;
            }
            case BubbleCornerTypeBottom: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMaxYCorner | kCALayerMaxXMaxYCorner;
                break;
            }
            case BubbleCornerTypeNoLeftTop: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMaxYCorner | kCALayerMaxXMaxYCorner | kCALayerMaxXMinYCorner;
                break;
            }
            case BubbleCornerTypeNoRightTop: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMaxYCorner | kCALayerMaxXMaxYCorner | kCALayerMinXMinYCorner;
                break;
            }
            case BubbleCornerTypeNoLeftBottom: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMinYCorner | kCALayerMaxXMinYCorner | kCALayerMaxXMaxYCorner;
                break;
            }
            case BubbleCornerTypeNoRightBottom: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMinYCorner | kCALayerMaxXMinYCorner | kCALayerMinXMaxYCorner;
                break;
            }
            case BubbleCornerTypeNormal: {
                self.bubbleView.layer.cornerRadius = 0.0f;
                break;
            }
            
            default:
                break;
        }
    } else {
        _bubbleView.layer.cornerRadius = [TOSKitCustomInfo shareCustomInfo].receiveBubble_cornerRadius;
        _bubbleView.image = [UIImage imageWithColor:[TOSKitCustomInfo shareCustomInfo].receiveBubble_backGround];
        switch ([TOSKitCustomInfo shareCustomInfo].chatBubble_CornerType) {
            case BubbleCornerTypeLeft: {
                self.bubbleView.layer.maskedCorners = kCALayerMaxXMinYCorner | kCALayerMaxXMaxYCorner;
                break;
            }
            case BubbleCornerTypeLeftTop: {
                self.bubbleView.layer.maskedCorners = kCALayerMaxXMinYCorner;
                break;
            }
            case BubbleCornerTypeLeftBottom: {
                self.bubbleView.layer.maskedCorners = kCALayerMaxXMaxYCorner;
                break;
            }
            case BubbleCornerTypeRight: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMinYCorner | kCALayerMinXMaxYCorner;
                break;
            }
            case BubbleCornerTypeRightTop: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMinYCorner;
                break;
            }
            case BubbleCornerTypeRightBottom: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMaxYCorner;
                break;
            }
            case BubbleCornerTypeTop: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMinYCorner | kCALayerMaxXMinYCorner;
                break;
            }
            case BubbleCornerTypeBottom: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMaxYCorner | kCALayerMaxXMaxYCorner;
                break;
            }
            case BubbleCornerTypeNoLeftTop: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMaxYCorner | kCALayerMaxXMaxYCorner | kCALayerMinXMinYCorner;
                break;
            }
            case BubbleCornerTypeNoRightTop: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMaxYCorner | kCALayerMaxXMaxYCorner | kCALayerMaxXMinYCorner;
                break;
            }
            case BubbleCornerTypeNoLeftBottom: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMinYCorner | kCALayerMaxXMinYCorner | kCALayerMinXMaxYCorner;
                break;
            }
            case BubbleCornerTypeNoRightBottom: {
                self.bubbleView.layer.maskedCorners = kCALayerMinXMinYCorner | kCALayerMaxXMinYCorner | kCALayerMaxXMaxYCorner;
                break;
            }
            case BubbleCornerTypeNormal: {
                self.bubbleView.layer.cornerRadius = 0.0f;
                break;
            }
            default:
                break;
        }
    }
    _bubbleView.layer.masksToBounds = YES;
}

#pragma mark - longPress delegate

- (void)longPressRecognizer:(UILongPressGestureRecognizer *)recognizer
{
    if ([self.longPressDelegate respondsToSelector:@selector(longPress:)]) {
        [self.longPressDelegate longPress:recognizer];
    }
}

@end
