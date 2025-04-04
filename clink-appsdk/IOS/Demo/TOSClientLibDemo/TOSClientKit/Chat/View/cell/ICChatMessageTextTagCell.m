//
//  ICChatMessageTextTagCell.m
//  TOSClientKit
//
//  Created by 言 on 2022/9/8.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "ICChatMessageTextTagCell.h"
#import "TTGTextTagCollectionView.h"
#import "YYLabel.h"
#import "TIMMessageModel.h"
#import "ICFaceManager.h"
#import "TIMConstants.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import "NSDictionary+TIMTool.h"
#import "YYKit.h"
#import "XZEmotion.h"
#import "TOSCustomerChatVC.h"
#import "STBaseWebViewController.h"
#import <TOSClientLib/TOSClientLib.h>
#import <TOSClientKit/TOSClientKit.h>
#import <TOSClientLib/TOSMessageTextTagModel.h>
#import "kitUtils.h"

@interface ICChatMessageTextTagCell () <TTGTextTagCollectionViewDelegate>

@property (nonatomic, strong) YYLabel *chatLabel;
@property (nonatomic, strong) TTGTextTagCollectionView *textTagView;

@end

@implementation ICChatMessageTextTagCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.chatLabel];
        [self.contentView addSubview:self.textTagView];
    }
    return self;
}

- (UIColor *)setTextLabelColor:(BOOL)isSender {
    if (isSender) {
        return [TOSKitCustomInfo shareCustomInfo].senderText_Color;
    } else {
        return [TOSKitCustomInfo shareCustomInfo].receiveText_Color;
    }
}

#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame {
    [super setModelFrame:modelFrame];
    
    self.chatLabel.frame = modelFrame.chatLabelF;
    self.readLabel.frame = modelFrame.unReadLabelF;
    NSMutableAttributedString *attStr = modelFrame.model.attributedString;
    TOSMessageTextTagModel *textTagModel = (TOSMessageTextTagModel *)modelFrame.model.message.content;
    
    self.chatLabel.font = [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font;
    
    self.chatLabel.numberOfLines = 0;
    [self.chatLabel setAttributedText:attStr];
    self.chatLabel.lineBreakMode = NSLineBreakByWordWrapping;
    
    self.textTagView.frame = modelFrame.textTagViewF;
    
    [self.textTagView removeAllTags];
    NSMutableArray *textTags = [NSMutableArray new];
    [textTagModel.tags enumerateObjectsUsingBlock:^(TOSMessageTextSubTagModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        
        TTGTextTagStringContent *tagContent = [self setupTTGTextTagContent];
        if (obj.text &&
            ![kitUtils isBlankString:obj.text] &&
            obj.text.length > 6) {
            NSRange range = {0, 5};
            tagContent.text = [NSString stringWithFormat:@"%@...",[obj.text substringWithRange:range]];
        } else {
            tagContent.text = obj.text;
        }
        
        TTGTextTagStyle *tagStyle = [self setupTTGTextTagStyle];
        if ([kitUtils isBlankString:obj.bgColor]) {
            tagStyle.backgroundColor = TOSHexColor(0x10A2F3);
        } else {
            tagStyle.backgroundColor = [self colorWithHexString:obj.bgColor alpha:1.f];
        }
        TTGTextTag *textTag = [TTGTextTag tagWithContent:tagContent style:tagStyle];
        textTag.attachment = obj.text;
        [textTags addObject:textTag];
        
        if (idx >= 7) {
            *stop = YES;
        }
    }];
    [self.textTagView addTags:textTags];
    
    
    @WeakObj(self)
    dispatch_async(dispatch_get_main_queue(), ^{
        @StrongObj(self)
        self.bubbleView.frame        = modelFrame.textTagBubbleViewF;
    });
}

- (void)urlSkip:(NSURL *)url {
    [self routerEventWithName:GXRouterEventURLSkip
                     userInfo:@{@"url"   : url
                                }];
}

- (void)textTagCollectionView:(TTGTextTagCollectionView *)textTagCollectionView
                    didTapTag:(TTGTextTag *)tag
                      atIndex:(NSUInteger)index {
    [self routerEventWithName:GXHotIssueSendMessageEventName
                     userInfo:@{RouterEventGetSendTextMessage:tag.attachment}];
}

/// 客户搜索模块，设置标签样式
/// @param tagView TTGTextTagCollectionView
- (void)setupTTGTextTagView:(TTGTextTagCollectionView *)tagView {
    
    // Alignment
    tagView.alignment = TTGTagCollectionAlignmentLeft;
    
    tagView.horizontalSpacing = 8.f;
    tagView.verticalSpacing = 8.f;
    tagView.contentInset = UIEdgeInsetsMake(8.f, 0.f, 8.f, 0.f);
    // Use manual calculate height
    tagView.manualCalculateHeight = YES;
    
    tagView.enableTagSelection = YES;
    
    tagView.showsVerticalScrollIndicator = NO;
}

/// 全局默认标签样式配置
- (TTGTextTagStyle *)setupTTGTextTagStyle {
    TTGTextTagStyle *tagStyle = [[TTGTextTagStyle alloc] init];
    tagStyle.extraSpace = CGSizeMake(16.f, 8.f);
    tagStyle.cornerRadius = 4.f;
    tagStyle.backgroundColor = TOSHexColor(0x10A2F3);
    tagStyle.borderWidth = 0.f;
    tagStyle.shadowRadius = 0.f;
    tagStyle.shadowOffset = CGSizeMake(0.f, 0.f);
    return tagStyle;
}


- (TTGTextTagStringContent *)setupTTGTextTagContent {
    TTGTextTagStringContent *content = [[TTGTextTagStringContent alloc] init];
    content.textFont = [UIFont fontWithName:@"PingFangSC-Regular" size:12.f];
    content.textColor = TOSHexColor(0xFFFFFF);
    return content;
}

#pragma mark - Getter and Setter
- (TTGTextTagCollectionView *)textTagView {
    if (!_textTagView) {
        _textTagView = [[TTGTextTagCollectionView alloc] init];
        _textTagView.delegate = self;
        _textTagView.backgroundColor = [UIColor clearColor];
        [self setupTTGTextTagView:_textTagView];
    }
    return _textTagView;
}

- (YYLabel *)chatLabel {
    if (nil == _chatLabel) {
        _chatLabel = [[YYLabel alloc] init];
        _chatLabel.numberOfLines = 0;
        _chatLabel.font = MessageFont;
        _chatLabel.textColor = TOSHexAColor(0x282724,1.0);
        _chatLabel.userInteractionEnabled = YES;
    }
    return _chatLabel;
}

- (UIColor *)colorWithHexString:(NSString *)colorStr alpha:(CGFloat)alpha {
    colorStr = [colorStr stringByReplacingOccurrencesOfString:@"#" withString:@""];
    NSString *cString = [[colorStr stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]] uppercaseString];
        
    NSRange range;
    range.location = 0;
    range.length = 2;
    NSString *rString = [cString substringWithRange:range];
    
    range.location = 2;
    NSString *gString = [cString substringWithRange:range];
    
    range.location = 4;
    NSString *bString = [cString substringWithRange:range];
    
    
    unsigned int r, g, b;
    [[NSScanner scannerWithString:rString] scanHexInt:&r];
    [[NSScanner scannerWithString:gString] scanHexInt:&g];
    [[NSScanner scannerWithString:bString] scanHexInt:&b];
    
    return TOSAColor(r, g, b, alpha);
}

@end
