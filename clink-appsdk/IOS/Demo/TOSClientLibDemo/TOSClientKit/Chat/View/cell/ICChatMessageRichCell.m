//
//  ICChatMessageRichCell.m
//  TOSClientKit
//
//  Created by 言 on 2022/6/14.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "ICChatMessageRichCell.h"
#import "TIMMessageModel.h"
#import <TOSClientLib/RichTextMessage.h>
#import "TIMConstants.h"
#import <TOSClientLib/OnlineChatRecordModel.h>

#import "YYKit.h"

#import "ICChatMessageRichTextCell.h"
#import "ICChatMessageRichImageCell.h"


#import <TOSClientLib/CombinationMessage.h>

#import "TIMICMessage.h"
#import "ICMediaManager.h"
#import "ICFileTool.h"
#import "ICMessageHelper.h"
#import "kitUtils.h"

@interface ICChatMessageRichCell ()

/// 引用部分

@property (nonatomic, strong) UIView *repliedMessageView;

@property (nonatomic, strong) UIView *repliedVerticalLine;
@property (nonatomic, strong) UILabel *repliedSenderName;

@property (nonatomic, strong) UIImageView *repliedImageView;
@property (nonatomic, strong) UILabel *repliedContent;

@property (nonatomic, strong) UIView *repliedHorizontalLine;


/// 主消息体
@property (nonatomic, strong) UIView *subContentView;

@property (nonatomic, strong) NSMutableArray <RichTextMessage *>*elements;

@end

@implementation ICChatMessageRichCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.subContentView];
        [self.contentView addSubview:self.repliedMessageView];
    }
    return self;
}

/// 主消息体
- (UIView *)subContentView {
    if (!_subContentView) {
        _subContentView = [[UIView alloc] initWithFrame:CGRectZero];
        _subContentView.backgroundColor = [UIColor clearColor];
    }
    return _subContentView;
}

/// 引用部分
- (UIView *)repliedMessageView {
    if (!_repliedMessageView) {
        _repliedMessageView = [[UIView alloc] initWithFrame:CGRectZero];
        _repliedMessageView.backgroundColor = [UIColor clearColor];
        
        [_repliedMessageView addSubview:self.repliedVerticalLine];
        [_repliedMessageView addSubview:self.repliedSenderName];
        [_repliedMessageView addSubview:self.repliedHorizontalLine];
        [_repliedMessageView addSubview:self.repliedContent];
        [_repliedMessageView addSubview:self.repliedImageView];
    }
    return _repliedMessageView;
}

- (UIView *)repliedVerticalLine {
    if (!_repliedVerticalLine) {
        _repliedVerticalLine = [[UIView alloc] initWithFrame:CGRectZero];
        _repliedVerticalLine.backgroundColor = TOSHexAColor(0xD9D9D9, 1.f);
    }
    return _repliedVerticalLine;
}

- (UILabel *)repliedSenderName {
    if (!_repliedSenderName) {
        _repliedSenderName = [[UILabel alloc] initWithFrame:CGRectZero];
        _repliedSenderName.numberOfLines = 1;
        _repliedSenderName.font = [UIFont fontWithName:@"PingFangSC-Regular" size:13.f];
        _repliedSenderName.textColor = TOSHexAColor(0x8C8C8C, 1.f);
    }
    return _repliedSenderName;
}

- (UIView *)repliedHorizontalLine {
    if (!_repliedHorizontalLine) {
        _repliedHorizontalLine = [[UIView alloc] initWithFrame:CGRectZero];
        _repliedHorizontalLine.backgroundColor = TOSHexAColor(0x000000, .06f);
    }
    return _repliedHorizontalLine;
}

- (UILabel *)repliedContent {
    if (!_repliedContent) {
        _repliedContent = [[UILabel alloc] initWithFrame:CGRectZero];
        _repliedContent.numberOfLines = 2;
        _repliedContent.font = [UIFont fontWithName:@"PingFangSC-Regular" size:13.f];
        _repliedContent.textColor = TOSHexAColor(0x8C8C8C, 1.f);
        _repliedContent.hidden = YES;
    }
    return _repliedContent;
}

- (UIImageView *)repliedImageView {
    if (!_repliedImageView) {
        _repliedImageView  = [[UIImageView alloc] initWithFrame:CGRectZero];
        _repliedImageView.contentMode = UIViewContentModeScaleAspectFit;
        _repliedImageView.hidden = YES;
    }
    return _repliedImageView;
}

#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    
    
    // 引用
    RepliedMessageModel *repliedMessage = (RepliedMessageModel *)modelFrame.model.message.extra;
    
    self.repliedMessageView.frame = modelFrame.repliedMessageViewF;
    
    if (repliedMessage &&
        [repliedMessage isKindOfClass:[RepliedMessageModel class]] &&
        (![kitUtils isBlankString:repliedMessage.content] ||
        ![kitUtils isBlankString:repliedMessage.fileKey])) {
        
        self.repliedMessageView.hidden = NO;
        
        self.repliedVerticalLine.frame = CGRectMake(0.f, 12.f, 2.f, modelFrame.repliedMessageViewF.size.height - 24.f);
        
        self.repliedSenderName.frame = CGRectMake(10.f, 12.f, modelFrame.repliedMessageContentF.size.width, 18.f);
        self.repliedSenderName.text = [NSString stringWithFormat:@"%@",repliedMessage.senderName];
        
        if (repliedMessage.messageType.integerValue == 2) {
            
            NSString *imageStr = @"";
            
            NSString *fileKey = @"null";
            NSString *fileName = @"null";
            if (![kitUtils isBlankString:repliedMessage.fileKey]) {
                fileKey = repliedMessage.fileKey;
            }
            if (![kitUtils isBlankString:repliedMessage.fileName]) {
                fileName = repliedMessage.fileName;
            }
            
            
            NSString *timestamp = [kitUtils getNowTimestampWithSec];
            NSString *accessSecret = [[OnlineDataSave shareOnlineDataSave] getAccessSecret];
            NSString *sign = [kitUtils md5StringFromString:[NSString stringWithFormat:@"%@%@%@%@", [[OnlineDataSave shareOnlineDataSave] getEnterpriseId],[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,accessSecret]];
            
            imageStr = [[NSString stringWithFormat:@"%@/api/app/message/file?fileKey=%@&fileName=%@&accessId=%@&timestamp=%@&enterpriseId=%@&sign=%@",[[OnlineDataSave shareOnlineDataSave] getOnlineUrl],fileKey,fileName,[[OnlineDataSave shareOnlineDataSave] getAccessId],timestamp,[[OnlineDataSave shareOnlineDataSave] getEnterpriseId],sign] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
            
            self.repliedImageView.hidden = NO;
            self.repliedContent.hidden = YES;
            self.repliedImageView.frame = modelFrame.repliedMessageContentF;
            [self.repliedImageView setImageWithURL:[NSURL URLWithString:imageStr] placeholder:[UIImage imageNamed:@""] options:(YYWebImageOptionSetImageWithFadeAnimation) completion:nil];
        } else {
            self.repliedContent.hidden = NO;
            self.repliedImageView.hidden = YES;
            self.repliedContent.frame = modelFrame.repliedMessageContentF;
            self.repliedContent.text = modelFrame.repliedMessageContent;
        }
        
        self.repliedHorizontalLine.frame = CGRectMake(0.f, modelFrame.repliedMessageViewF.size.height - 1.f, modelFrame.repliedMessageViewF.size.width, 1.f);
    } else {
        self.repliedMessageView.hidden = YES;
    }
    
    
    
    // 主消息体
    NSMutableArray <RichTextMessage *>*richModels = (NSMutableArray <RichTextMessage *>*)modelFrame.model.message.content;
    
    self.elements = [NSMutableArray arrayWithArray:richModels];
    
    self.subContentView.frame = modelFrame.richTextContentF;
    [self.subContentView removeAllSubviews];
    
    __block CGFloat y = 0;
    CGFloat width = modelFrame.richTextContentF.size.width;
    CGFloat x = 0;
    
    [self.elements enumerateObjectsUsingBlock:^(RichTextMessage * _Nonnull model, NSUInteger idx, BOOL * _Nonnull stop) {
        
        CGFloat height = model.contentF.size.height;
        
        if ([model.type isEqualToString:@"text"] ||
            [model.type isEqualToString:@"a"] ||
            [model.type isEqualToString:@"tr"] ||
            [model.type isEqualToString:@"knowledge"] ||
            [model.type isEqualToString:@"p"] ||
            [model.type isEqualToString:@"div"] ||
            [model.type isEqualToString:@"ul"] ||
            [model.type isEqualToString:@"ol"] ||
            [model.type isEqualToString:@"span"] ||
            [model.type isEqualToString:@"strong"] ||
            [model.type isEqualToString:@"em"] ||
            [model.type isEqualToString:@"sup"] ||
            [model.type isEqualToString:@"sub"] ||
            [model.type isEqualToString:@"h1"] ||
            [model.type isEqualToString:@"h2"] ||
            [model.type isEqualToString:@"h3"] ||
            [model.type isEqualToString:@"h4"] ||
            [model.type isEqualToString:@"h5"] ||
            [model.type isEqualToString:@"h6"] ||
            [model.type isEqualToString:@"code"]) {
            
            ICChatMessageRichTextCell *textSubview = [[ICChatMessageRichTextCell alloc] initWithFrame:CGRectMake(x, y, width, height)];
            [textSubview setWithModel:model];
            [self.subContentView addSubview:textSubview];
        } else if ([model.type isEqualToString:@"img"] ||
                   [model.type isEqualToString:@"video"]) {
            
            ICChatMessageRichImageCell *imageSubview = [[ICChatMessageRichImageCell alloc] initWithFrame:CGRectMake(x, y, width, height)];
            [imageSubview setWithModel:model];
            [self.subContentView addSubview:imageSubview];
        } else {
            
            ICChatMessageRichTextCell *textSubview = [[ICChatMessageRichTextCell alloc] initWithFrame:CGRectMake(x, y, width, height)];
            [self.subContentView addSubview:textSubview];
        }
        y += height;
    }];
}

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
