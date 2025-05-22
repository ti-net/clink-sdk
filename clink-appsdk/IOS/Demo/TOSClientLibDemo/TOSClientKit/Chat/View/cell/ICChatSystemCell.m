//
//  ICChatSystemCell.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/6/7.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICChatSystemCell.h"
#import "TIMMessageFrame.h"
#import "TIMMessageModel.h"
#import "TIMICMessage.h"
#import "UIView+SDExtension.h"
#import "TIMConstants.h"
#import "NSString+Extension.h"
#import <TOSClientKit/TOSKitCustomInfo.h>
#import <YYKit.h>

#define labelFont [UIFont systemFontOfSize:11.0]

@interface ICChatSystemCell ()

@property (nonatomic, weak) YYLabel *contentLabel;

@end

@implementation ICChatSystemCell

+ (instancetype)cellWithTableView:(UITableView *)tableView reusableId:(NSString *)ID
{
    ICChatSystemCell *cell = [[ICChatSystemCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:ID];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    return cell;
}


- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        
        YYLabel *contentLabel = [[YYLabel alloc] init];
        [self addSubview:contentLabel];
        self.contentLabel = contentLabel;
        self.backgroundColor           = [UIColor clearColor];
        self.selectionStyle            = UITableViewCellSelectionStyleNone;
//        self.contentLabel.backgroundColor = TOSHexAColor(0xFFFFFF,0.65);
//        self.contentLabel.textColor       = TOSHexAColor(0x595959, 1.0);
//        self.contentLabel.textAlignment   = NSTextAlignmentCenter;
//        self.contentLabel.font            = [UIFont systemFontOfSize:12.0];
//        self.contentLabel.layer.masksToBounds  = YES;
//        self.contentLabel.layer.cornerRadius   = 4.0;
//        self.contentLabel.width = [UIScreen mainScreen].bounds.size.width - 40;
        self.contentLabel.numberOfLines        = 0;
        
        self.contentLabel.backgroundColor = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_backgroundColor;
        self.contentLabel.textColor       = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textColor;
        self.contentLabel.textAlignment   = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textAlignment;
        self.contentLabel.font            = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont;
        self.contentLabel.layer.masksToBounds  = YES;
        self.contentLabel.layer.cornerRadius   = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_cornerRadius;
    }
    return self;
}

- (void)setMessageF:(TIMMessageFrame *)messageF
{
//    CGFloat width = [UIScreen mainScreen].bounds.size.width;
//    CGSize size           = [messageF.model.message.content sizeWithMaxWidth:[UIScreen mainScreen].bounds.size.width - 40 andFont:labelFont];
//    if (size.width > width-40) {
//        size.width = width - 40;
//    }
//    self.contentLabel.height = (int)size.height+3;
//    self.contentLabel.width  = (int)size.width+20;// 这个地方不强制转换会有问题
//    self.contentLabel.center = CGPointMake(width*0.5, (size.height+10)*0.5);
    
    /// 距离屏幕左右的间距+label内部文字的间距
//    CGFloat edgeInset = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.left+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.right+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.left+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.right;
//
//    CGSize size           = [messageF.model.message.content tim_sizeWithMaxWidth:[UIScreen mainScreen].bounds.size.width - edgeInset andFont:[TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont];
//
//    if ([TOSKitCustomInfo shareCustomInfo].chatMessage_system_center) {
//        self.contentLabel.tos_height = (int)size.height+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.top+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.bottom;
//        self.contentLabel.tos_width  = (int)size.width+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.left+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.right;// 这个地方不强制转换会有问题
////        self.contentLabel.center = CGPointMake(width*0.5, (size.height+10)*0.5);
//        self.contentLabel.centerX = width*0.5;
//        self.contentLabel.centerY = (self.contentLabel.tos_height + [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.top + [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.bottom) * 0.5;
//    }
//    else {
//        self.contentLabel.frame = CGRectMake([TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.left, [TOSKitCustomInfo shareCustomInfo].chatMessage_system_edgeInsets.top, (int)size.width+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.left+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.right, (int)size.height+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.top+[TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets.bottom);
//    }
    self.contentLabel.frame = messageF.chatLabelF;
    
    _messageF            = messageF;
    NSAttributedString * attributedString = [[NSAttributedString alloc] initWithString:messageF.model.message.content];
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
    self.contentLabel.attributedText = attributedStringWithLineHeight;
    self.contentLabel.textContainerInset = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_labelTextEdgeInsets;
    self.contentLabel.textAlignment   = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textAlignment;
    self.contentLabel.lineBreakMode = NSLineBreakByCharWrapping;
    
//    self.contentLabel.text = messageF.model.message.content;
    
}



- (void)layoutSubviews
{
    [super layoutSubviews];
}


@end
