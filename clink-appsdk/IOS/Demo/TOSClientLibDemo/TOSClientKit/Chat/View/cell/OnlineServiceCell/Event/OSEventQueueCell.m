//
//  OSEventQueueCell.m
//  TIMClientKit
//
//  Created by apple on 2021/10/21.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "OSEventQueueCell.h"
#import "TIMMessageModel.h"
#import "TIMConstants.h"
#import <YYKit.h>
#import "TOSKitCustomInfo.h"

@interface OSEventQueueCell()
//{
//    TIMMessageFrame *_modelFrame;
//}

@property (nonatomic, strong) YYLabel *titleLbl;
@property (nonatomic, strong) UIButton *leaveQueueBtn;

@end

@implementation OSEventQueueCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {

        [self.contentView addSubview:self.titleLbl];
        [self.contentView addSubview:self.leaveQueueBtn];
        self.bubbleView.hidden = YES;
        self.nameLabel.hidden = YES;
        self.headImageView.hidden = YES;
        self.activityView.hidden = YES;
        self.retryButton.hidden = YES;
    }
    return self;
}
#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    
    NSLog(@"排队数据赋值");
    
    // 创建一个包含段落样式的 NSMutableParagraphStyle 对象
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    paragraphStyle.lineSpacing = 2.0;
    paragraphStyle.lineBreakMode = NSLineBreakByCharWrapping;
    paragraphStyle.maximumLineHeight = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont.lineHeight;
    paragraphStyle.minimumLineHeight = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont.lineHeight;
    
    // 创建一个包含段落样式的新的富文本属性字典
    NSDictionary *attributes = @{
        NSParagraphStyleAttributeName: paragraphStyle,
        NSFontAttributeName: [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont,
        NSForegroundColorAttributeName: TOSKitCustomInfo.shareCustomInfo.chatMessage_system_textColor,
    };
    // 创建一个包含段落样式的新 NSAttributedString
    NSMutableAttributedString *attributedStringWithLineHeight = [self content:modelFrame.model.message.content];
    [attributedStringWithLineHeight addAttributes:attributes range:NSMakeRange(0, modelFrame.model.message.content.length)];
    /// 当前不是座席在线状态需要添加放弃排队功能
    if ([TIMClient.sharedTIMClient getLibOnlineStatus] != TinetChatStatusTypeCloseChat) {
        // 创建一个包含段落样式的新的富文本属性字典
        NSDictionary *leaveQueueAttributes = @{
            NSParagraphStyleAttributeName: paragraphStyle,
            NSFontAttributeName: [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont,
            NSForegroundColorAttributeName: TOSHexAColor(0x1890ff,1.0),
        };
        NSMutableAttributedString * leaveQueueAttributedStr = [[NSMutableAttributedString alloc] initWithString:@" 放弃排队"];
        [leaveQueueAttributedStr addAttributes:leaveQueueAttributes range:NSMakeRange(0, leaveQueueAttributedStr.length)];
        [attributedStringWithLineHeight appendAttributedString:leaveQueueAttributedStr];
        
        __weak typeof(self) weakself = self;
        [attributedStringWithLineHeight setTextHighlightRange:NSMakeRange(modelFrame.model.message.content.length, leaveQueueAttributedStr.length)
                                      color:TOSHexColor(0x4385FF)
                            backgroundColor:[UIColor colorWithWhite:0.000 alpha:0.220]
                                  tapAction:^(UIView *containerView, NSAttributedString *text, NSRange range, CGRect rect){
            __strong typeof(weakself) strongSelf = weakself;
            [strongSelf leavaAction];
            strongSelf.titleLbl.attributedText = [strongSelf content:modelFrame.model.message.content];
            strongSelf.titleLbl.textAlignment = NSTextAlignmentCenter;
        }];
    }
    
    self.titleLbl.attributedText = attributedStringWithLineHeight;
    self.titleLbl.textAlignment = NSTextAlignmentCenter;
    self.titleLbl.textContainerInset = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets;
    self.titleLbl.frame = modelFrame.chatLabelF;
//    self.titleLbl.text = modelFrame.model.message.content;
//    self.leaveQueueBtn.frame = modelFrame.custTitleF;
//    self.leaveQueueBtn.hidden = NO;
    
}

- (NSMutableAttributedString *)content:(NSString *)content {
    NSAttributedString * attributedString = [[NSAttributedString alloc] initWithString:content];
    // 创建一个包含段落样式的 NSMutableParagraphStyle 对象
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    paragraphStyle.lineSpacing = 2.0;
    paragraphStyle.lineBreakMode = NSLineBreakByCharWrapping;
    paragraphStyle.maximumLineHeight = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont.lineHeight;
    paragraphStyle.minimumLineHeight = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont.lineHeight;
    
    // 创建一个包含段落样式的新的富文本属性字典
    NSDictionary *attributes = @{
        NSParagraphStyleAttributeName: paragraphStyle,
        NSFontAttributeName: [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont,
        NSForegroundColorAttributeName: TOSKitCustomInfo.shareCustomInfo.chatMessage_system_textColor,
    };
    
    // 创建一个包含段落样式的新 NSAttributedString
    NSMutableAttributedString *attributedStringWithLineHeight = [[NSMutableAttributedString alloc] initWithAttributedString:attributedString];
    [attributedStringWithLineHeight addAttributes:attributes range:NSMakeRange(0, attributedString.length)];
    return attributedStringWithLineHeight;
}


#pragma mark lazy
- (YYLabel *)titleLbl
{
    if (nil == _titleLbl) {
        _titleLbl = [[YYLabel alloc] init];
        _titleLbl.numberOfLines = 0;
        _titleLbl.font = MessageFont12;
        _titleLbl.textColor = [UIColor whiteColor];
        _titleLbl.backgroundColor = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_backgroundColor;
        _titleLbl.textAlignment = NSTextAlignmentCenter;
        _titleLbl.layer.cornerRadius = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_cornerRadius;
        _titleLbl.layer.masksToBounds = YES;
    }
    return _titleLbl;
}

-(UIButton *)leaveQueueBtn{
    if (!_leaveQueueBtn) {
        _leaveQueueBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [_leaveQueueBtn setTitle:@"放弃排队" forState:UIControlStateNormal];
        [_leaveQueueBtn setTitleColor:TOSHexAColor(0x1890ff,1.0) forState:UIControlStateNormal];
        _leaveQueueBtn.titleLabel.font = MessageFont12;
        [_leaveQueueBtn addTarget:self action:@selector(leavaAction) forControlEvents:UIControlEventTouchUpInside];
        
    }
    return _leaveQueueBtn;
}

-(void)leavaAction{
//    self.leaveQueueBtn.hidden = YES;
//    int app_width = [UIScreen mainScreen].bounds.size.width;
//    float diffWidth = (app_width - self.modelFrame.chatLabelF.size.width) / 2.0 - self.modelFrame.chatLabelF.origin.x;
//
//    self.titleLbl.frame = CGRectMake(self.modelFrame.chatLabelF.origin.x +                                     diffWidth,
//                                     self.modelFrame.chatLabelF.origin.y,
//                                     self.modelFrame.chatLabelF.size.width,
//                                     self.modelFrame.chatLabelF.size.height);
    [[NSNotificationCenter defaultCenter] postNotificationName:OSLeaveQueueNotification object:self.modelFrame.model?:@""];
}

@end
