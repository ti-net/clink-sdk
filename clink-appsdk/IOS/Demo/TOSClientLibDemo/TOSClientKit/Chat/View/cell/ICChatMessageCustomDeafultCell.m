//
//  ICChatMessageCustomDeafultCell.m
//  TIMClientKit
//
//  Created by YanBo on 2020/8/27.
//  Copyright © 2020 YanBo. All rights reserved.
//
#import "TIMLabel.h"
#import "ICChatMessageCustomDeafultCell.h"
#import "TIMConstants.h"
#import "UIView+SDExtension.h"
#import "TIMMessageModel.h"
#import "kitUtils.h"
#import "UIImageView+TIMWebCache.h"
#import "ICFaceManager.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"

static CGFloat PaddingLength = 3.0f;

@interface ICChatMessageCustomDeafultCell()
@property (nonatomic, strong) UIImageView *pictureView;
@property (nonatomic, strong) TIMLabel *shareTitleLabel;
@property (nonatomic, strong) TIMLabel *titleLabel;
@property (nonatomic, strong) TIMLabel *content;
@property (nonatomic, strong) UIView *midLine;
@property (nonatomic, strong) TIMLabel *chatLabel;
@end

@implementation ICChatMessageCustomDeafultCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.shareTitleLabel];
        [self.contentView addSubview:self.midLine];
        [self.contentView addSubview:self.pictureView];
        [self.contentView addSubview:self.titleLabel];
        [self.contentView addSubview:self.content];
        [self.contentView addSubview:self.chatLabel];
    }
    return self;
}
#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    if (!modelFrame.model.message.content) {
        return;
    }
    self.readLabel.frame = modelFrame.unReadLabelF;

    NSDictionary * contentDic = [kitUtils dictionaryWithJsonString:modelFrame.model.message.content];
    if (!contentDic) {
        return;
    }
    if ([contentDic objectForKey:@"template"] && [contentDic[@"template"] isEqualToString:@"tim-rtcMedia"]) {
        self.chatLabel.frame = modelFrame.chatLabelF;
        if ([contentDic objectForKey:@"body"] && [contentDic[@"body"] objectForKey:@"stopAction"]) {
            NSDictionary * bodyContentDic = contentDic[@"body"];
            NSString * contentStr = [kitUtils transferStopAction:bodyContentDic[@"stopAction"] isSender:modelFrame.model.isSender duration:[bodyContentDic[@"duration"] intValue]];
            if (!contentStr) {
                contentStr = @"";
            }
            NSMutableAttributedString *strAtt = [[NSMutableAttributedString alloc] initWithString:contentStr attributes:@{
                NSFontAttributeName : self.chatLabel.font
            }
            ];
            NSTextAttachment *attatch = [[NSTextAttachment alloc] initWithData:nil ofType:nil];
            attatch.bounds = CGRectMake(0, -4, self.chatLabel.font.lineHeight, self.chatLabel.font.lineHeight);
            
            if ([bodyContentDic objectForKey:@"onlyAudio"] && [bodyContentDic[@"onlyAudio"] boolValue]) {
                attatch.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"stopActionAudio"]];
            } else {
                attatch.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"stopActionVideo"]];
            }
            NSAttributedString *string8 = [NSAttributedString attributedStringWithAttachment:attatch];
            if (modelFrame.model.isSender) {
                [strAtt appendAttributedString:string8];
                [strAtt addAttribute:NSForegroundColorAttributeName value:TOSHexAColor(0xffffff,1.0) range:NSMakeRange(0,strAtt.length)]; //设置字体颜色
            } else {
                [strAtt insertAttributedString:string8 atIndex:0];
                [strAtt addAttribute:NSForegroundColorAttributeName value:TOSHexAColor(0x000000,1.0) range:NSMakeRange(0,strAtt.length)]; //设置字体颜色
            }
            
            self.chatLabel.attributedText = strAtt;
        }
    } else {
        [self.shareTitleLabel setFrame:modelFrame.custShareTitleF];
        [self.pictureView setFrame:modelFrame.custPictureF];
        CGRect midLineRect = modelFrame.custShareTitleF;
        midLineRect.origin.y = CGRectGetMaxY(modelFrame.custShareTitleF) + 1;
        midLineRect.size.height = 1.0f;
        [self.midLine setFrame:midLineRect];
        [self.titleLabel setFrame:modelFrame.custTitleF];
        [self.content setFrame:modelFrame.custContentF];

        NSDictionary * contentDic = [kitUtils dictionaryWithJsonString:modelFrame.model.message.content];
        if ([kitUtils isBlankString:contentDic[@"body"][@"shareTitle"]]) {
            self.shareTitleLabel.text = @"";
        }else{
            self.shareTitleLabel.text = contentDic[@"body"][@"shareTitle"];
        }
        if ([kitUtils isBlankString:contentDic[@"body"][@"title"]]) {
            self.titleLabel.text = @"";
        }else{
            self.titleLabel.text = contentDic[@"body"][@"title"];
        }
        if ([kitUtils isBlankString:contentDic[@"body"][@"content"]]) {
            [self.content setAttributedText:[[NSAttributedString alloc] initWithString:@""]];
        }else{
            [self.content setAttributedText:[self showAttributedToHtml:contentDic[@"body"][@"content"]]];
        }

        [self.pictureView sd_setImageWithURL:contentDic[@"body"][@"imgUrl"]?:@"" placeholderImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"loadImgErr"]]];
    }
//    [self BMWCustomerUnreadStatusDisplayWithModel:modelFrame.model];

}

#pragma mark - Getter and Setter
- (TIMLabel *)shareTitleLabel
{
    if (nil == _shareTitleLabel) {
        _shareTitleLabel = [[TIMLabel alloc] init];
        _shareTitleLabel.numberOfLines = 1;
        _shareTitleLabel.font = MessageFont;
        _shareTitleLabel.textColor = TOSHexAColor(0x666666,1.0);
//        _shareTitleLabel.frame = CGRectMake(self.contentView.origin.x + PaddingLength, self.contentView.origin.y + PaddingLength, self.contentView.size.width - 2 * PaddingLength, 30.f);
        _shareTitleLabel.linkDetectionTypes = KILinkTypeOptionNone;
    }
    return _shareTitleLabel;
}

- (UIImageView *)pictureView
{
    if (nil == _pictureView) {
        _pictureView = [[UIImageView alloc] init];
//        _pictureView.frame = CGRectMake(_shareTitleLabel.origin.x, _shareTitleLabel.origin.y +1 + 2* PaddingLength , self.contentView.size.width - 2 * PaddingLength, self.contentView.size.height - _shareTitleLabel.size.height - 4* PaddingLength - 1);
    }
    return _pictureView;
}



- (TIMLabel *)titleLabel
{
    if (nil == _titleLabel) {
        _titleLabel = [[TIMLabel alloc] init];
        _titleLabel.numberOfLines = 1;
        _titleLabel.font = MessageCustTitleFont;
        _titleLabel.textColor = TOSHexAColor(0x262626,1.0);
//        _titleLabel.frame = CGRectMake(_pictureView.origin.x + 2 *PaddingLength, _pictureView.origin.y, self.contentView.size.width - 4 * PaddingLength - _pictureView.size.width, _pictureView.size.height / 3);
        _titleLabel.linkDetectionTypes = KILinkTypeOptionNone;
    }
    return _titleLabel;
}

- (TIMLabel *)content
{
    if (nil == _content) {
        _content = [[TIMLabel alloc] init];
        _content.numberOfLines = 0;
        _content.font = MessageFont;
//        _content.frame = CGRectMake(_titleLabel.origin.x,_pictureView.origin.y - _pictureView.size.height / 3,_titleLabel.size.width,_titleLabel.size.height);
//        _content.textColor = TOSHexAColor(0x262626,1.0);
        _content.linkDetectionTypes = KILinkTypeOptionNone;
    }
    return _content;
}

- (UIView *)midLine
{
    if (nil == _midLine) {
        _midLine = [[UIView alloc] init];
        _midLine.tosSD_height = 1.0f;
        _midLine.backgroundColor = [UIColor grayColor];
        _midLine.alpha = 1.0;
//        _midLine.frame = CGRectMake(_shareTitleLabel.origin.x, _shareTitleLabel.origin.y + PaddingLength, _shareTitleLabel.size.width, 1.0f);
    }
    return _midLine;
}

///显示富文本
-(NSAttributedString *)showAttributedToHtml:(NSString *)html{
//替换图片的高度为屏幕的高度
//    NSString *newString = [html stringByReplacingOccurrencesOfString:@"<img" withString:[NSString stringWithFormat:@"<img width=\"%f\"",width]];
    
    NSAttributedString *attributeString=[[NSAttributedString alloc] initWithData:[html dataUsingEncoding:NSUTF8StringEncoding] options:@{} documentAttributes:nil error:nil];
     
     
    return attributeString;
 
}

- (TIMLabel *)chatLabel
{
    if (nil == _chatLabel) {
        _chatLabel = [[TIMLabel alloc] init];
        _chatLabel.numberOfLines = 0;
        _chatLabel.font = MessageFont;
        _chatLabel.textColor = TOSHexAColor(0x282724,1.0);
        _chatLabel.linkDetectionTypes = KILinkTypeOptionNone;
    }
    return _chatLabel;
}

@end
