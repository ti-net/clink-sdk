//
//  ICChatMessageTextCell.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/13.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICChatMessageTextCell.h"
#import "TIMMessageModel.h"
#import "ICFaceManager.h"
#import "TIMConstants.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import <TOSClientLib/TIMLibUtils.h>
#import "NSDictionary+TIMTool.h"
#import "YYKit.h"
#import "XZEmotion.h"
#import "TOSCustomerChatVC.h"
#import "STBaseWebViewController.h"
#import <TOSClientLib/TOSClientLib.h>
#import <TOSClientKit/TOSClientKit.h>

@interface ICChatMessageTextCell ()

@property (nonatomic, strong) UILabel *sensitiveWordsLabel;

@end

@implementation ICChatMessageTextCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.chatLabel];
        [self.contentView addSubview:self.sensitiveWordsLabel];
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
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    self.chatLabel.frame = modelFrame.chatLabelF;
    self.readLabel.frame = modelFrame.unReadLabelF;
    NSMutableAttributedString *attStr;
//    self.chatLabel.font = MessageFont;
    self.chatLabel.numberOfLines = 0;
    self.sensitiveWordsLabel.frame = modelFrame.sensitiveWordsLabelF;
    UIColor *textColor = [self setTextLabelColor:modelFrame.model.isSender];
    if (modelFrame.model.isSender) {
        self.chatLabel.font = [TOSKitCustomInfo shareCustomInfo].chatMessage_visitorText_font;
        attStr =  [self transferMessageString:modelFrame.model.message.content?:@"" font:self.chatLabel.font  foreColor:textColor lineHeight:self.chatLabel.font.lineHeight];
    }else{
        self.chatLabel.font = [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font;
        attStr =  [self transferMessageString:modelFrame.model.message.content?:@"" font:self.chatLabel.font  foreColor:textColor lineHeight:self.chatLabel.font.lineHeight];
    }
    
    NSDictionary *extraDic = [TIMLibUtils dictionaryWithJsonString:modelFrame.model.message.extra];
    if ([extraDic by_ObjectForKey:@"atNameList"]) {
        NSString *atNameListStr = extraDic[@"atNameList"];
        NSArray *atNameListArr = [atNameListStr componentsSeparatedByString:@","];
        [atNameListArr enumerateObjectsUsingBlock:^(NSString *obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if ([modelFrame.model.message.content containsString:obj]) {
                NSRange range = [attStr.string rangeOfString:obj];
                [attStr addAttribute:NSForegroundColorAttributeName value:[UIColor redColor] range:range];
            }
        }];
    }
    

    // Create text container
//    YYTextContainer *container = [[YYTextContainer alloc] init];
//    container.size = CGSizeMake(modelFrame.chatLabelF.size.width, modelFrame.chatLabelF.size.height);
//    container.maximumNumberOfRows = 0;
//    container.truncationType = YYTextTruncationTypeEnd;
    
    // Generate a text layout.
//    YYTextLayout *layout = [YYTextLayout layoutWithContainer:container text:attStr];
//
//    self.chatLabel.text = @"";
//
//    self.chatLabel.size = layout.textBoundingSize;
//    self.chatLabel.textLayout = layout;
    
    
    [self.chatLabel setNumberOfLines:0];
    [self.chatLabel setAttributedText:attStr];
    
//    self.chatLabel.font = MessageFont;
    self.chatLabel.numberOfLines = 0;
    self.chatLabel.lineBreakMode = NSLineBreakByWordWrapping;
}

- (NSMutableAttributedString *)transferMessageString:(NSString *)message
                                                font:(UIFont *)font
                                           foreColor:(UIColor *)fColor
                                          lineHeight:(CGFloat)lineHeight {
    
    __weak typeof(self) weadSelf = self;
    NSMutableAttributedString *attributeStr = [[NSMutableAttributedString alloc] initWithData:[message dataUsingEncoding:NSUnicodeStringEncoding] options:@{NSDocumentTypeDocumentAttribute: NSHTMLTextDocumentType} documentAttributes:nil error:nil];
    attributeStr.lineBreakMode = NSLineBreakByCharWrapping;
    
    NSMutableDictionary <NSString *, NSString *>*linkDic = [NSMutableDictionary dictionary];
    [attributeStr enumerateAttribute:NSLinkAttributeName inRange:NSMakeRange(0, attributeStr.length) options:(NSAttributedStringEnumerationReverse) usingBlock:^(id  _Nullable value, NSRange range, BOOL * _Nonnull stop) {
        
        if (value) {
            [linkDic setObject:[NSString stringWithFormat:@"%lu,%lu",(unsigned long)range.location,(unsigned long)range.length] forKey:value];
        }
    }];
    
    // 表情的规则
    NSString *emotionPattern = @"(\\[[a-zA-Z0-9\\/\\u4e00-\\u9fa5]+\\])";
    // @的规则
//    NSString *atPattern = @"@[0-9a-zA-Z\\u4e00-\\u9fa5-_]+";
    // #话题#的规则
//    NSString *topicPattern = @"#[0-9a-zA-Z\\u4e00-\\u9fa5]+#";
    // 订单号的规则
    NSString *orderNumberPattern = @"([a-zA-Z0-9_]{5,})";
    
    // url链接的规则
    NSString *urlPattern = @"(((http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(www.[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(((http[s]{0,1}|ftp)://|)((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?))";
     
//    NSString *urlPattern = @"(<a[a-zA-Z0-9\\s:/%=><&\\?\\-~_\\$#+\\.,;\"']*(</a>))|([a-zA-Z0-9_]{5,})";
    
    NSString *phoneNumber = @"(\\d{3}-\\d{8}|\\d{4}-\\d{7}|\\d{11})";
    
    NSString *pattern = [NSString stringWithFormat:@"%@|%@|%@|%@", urlPattern, emotionPattern, phoneNumber, orderNumberPattern];
    
    NSError *error    = nil;
    //pattern
    NSRegularExpression *expression = [NSRegularExpression regularExpressionWithPattern:pattern options:NSRegularExpressionCaseInsensitive error:&error];
    if (!expression) {
        return attributeStr;
    }
    [attributeStr addAttribute:NSForegroundColorAttributeName value:fColor range:NSMakeRange(0,attributeStr.length)]; //设置字体颜色
    [attributeStr addAttribute:NSFontAttributeName value:font range:NSMakeRange(0, attributeStr.length)];
    
    [linkDic enumerateKeysAndObjectsUsingBlock:^(NSString * _Nonnull key, NSString * _Nonnull obj, BOOL * _Nonnull stop) {
        
        NSArray <NSString *>*rangeArray = [obj componentsSeparatedByString:@","];
        NSRange range = NSMakeRange((rangeArray[0]).integerValue,(rangeArray[1]).integerValue);
        [attributeStr setTextHighlightRange:range
                                color:TOSHexColor(0x4385FF)
                      backgroundColor:[UIColor colorWithWhite:0.000 alpha:0.220]
                            tapAction:^(UIView *containerView, NSAttributedString *text, NSRange range, CGRect rect){
            
            __strong typeof(weadSelf) strongSelf = weadSelf;
            [strongSelf routerEventWithName:TinetRouterClickEventUrl
                             userInfo:@{@"content"   : key
                                        }];
            
        }];
    }];
    
    NSArray *resultArray = [expression matchesInString:attributeStr.string options:0 range:NSMakeRange(0, attributeStr.string.length)];
    NSMutableArray *mutableArray = [NSMutableArray arrayWithCapacity:resultArray.count];
    for (NSTextCheckingResult *match in resultArray) {
        NSRange range    = match.range;
        NSString *subStr = [attributeStr.string substringWithRange:range];
        if ([subStr hasPrefix:@"http"] || [subStr hasPrefix:@"www"] || [subStr hasPrefix:@"ftp"]) {   //链接
            
            [attributeStr setTextHighlightRange:range
                                    color:TOSHexColor(0x4385FF)
                          backgroundColor:[UIColor colorWithWhite:0.000 alpha:0.220]
                                tapAction:^(UIView *containerView, NSAttributedString *text, NSRange range, CGRect rect){
                
                __strong typeof(weadSelf) strongSelf = weadSelf;
                
                NSLog(@"需要跳转超链接");
                
                [strongSelf routerEventWithName:TinetRouterClickEventUrl
                                 userInfo:@{@"content"   : [text.string substringWithRange:range]
                                            }];
                
            }];
        } else if ([subStr hasPrefix:@"["] && [subStr hasSuffix:@"]"]) {    //表情
            
            NSArray *faceArr = [ICFaceManager customEmotion];
            for (XZEmotion *face in faceArr) {
                if ([face.face_name isEqualToString:subStr]) {
                    
                    UIImage *image = [[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,face.face_name]] imageWithRenderingMode:(UIImageRenderingModeAlwaysOriginal)];
                    image = [UIImage imageWithCGImage:image.CGImage scale:[UIScreen mainScreen].scale orientation:UIImageOrientationUp];
                    
                    NSMutableAttributedString *attachText = [NSMutableAttributedString attachmentStringWithContent:image contentMode:UIViewContentModeScaleAspectFit attachmentSize:CGSizeMake(1.f*lineHeight, 1.f*lineHeight) alignToFont:font alignment:YYTextVerticalAlignmentCenter];
                    
                    [attachText addAttribute:NSKernAttributeName value:@(100) range:NSMakeRange(0, attachText.string.length)];
                    
                    NSMutableDictionary *imagDic   = [NSMutableDictionary dictionaryWithCapacity:2];
                    [imagDic setObject:attachText forKey:@"image"];
                    [imagDic setObject:[NSValue valueWithRange:range] forKey:@"range"];
                    
                    [mutableArray addObject:imagDic];
                }
            }
        } else {
            
            NSRegularExpression *regex = [NSRegularExpression regularExpressionWithPattern:@"[a-zA-Z_]+" options:NSRegularExpressionCaseInsensitive error:nil];
            NSArray *matches = [regex matchesInString:subStr options:NSMatchingReportProgress range:NSMakeRange(0, [subStr length])];
            
            if (!matches || matches.count == 0) { //手机号
                
                [attributeStr setTextHighlightRange:range
                                        color:TOSHexColor(0x4385FF)
                              backgroundColor:[UIColor colorWithWhite:0.000 alpha:0.220]
                                    tapAction:^(UIView *containerView, NSAttributedString *text, NSRange range, CGRect rect){
                    
                    __strong typeof(weadSelf) strongSelf = weadSelf;
                    [strongSelf routerEventWithName:TinetRouterClickEventPhone
                                     userInfo:@{@"content"   : [text.string substringWithRange:range]
                                                }];
                    
                }];
            } else {   //订单号
                
                [attributeStr setTextHighlightRange:range
                                        color:TOSHexColor(0x4385FF)
                              backgroundColor:[UIColor colorWithWhite:0.000 alpha:0.220]
                                    tapAction:^(UIView *containerView, NSAttributedString *text, NSRange range, CGRect rect){
                    __strong typeof(weadSelf) strongSelf = weadSelf;
                    
                    [strongSelf routerEventWithName:TinetRouterClickEventOrderNumber
                                     userInfo:@{@"content"   : [text.string substringWithRange:range]
                                                }];
                }];
            }
        }
    }
    for (int i =(int) mutableArray.count - 1; i >= 0; i --) {
        NSRange range;
        [mutableArray[i][@"range"] getValue:&range];
        [attributeStr replaceCharactersInRange:range withAttributedString:mutableArray[i][@"image"]];
    }
    attributeStr.font = font;
    return attributeStr;
}

- (void)urlSkip:(NSURL *)url
{
    [self routerEventWithName:GXRouterEventURLSkip
                     userInfo:@{@"url"   : url
                                }];
}

#pragma mark - Getter and Setter
- (YYLabel *)chatLabel
{
    if (nil == _chatLabel) {
        _chatLabel = [[YYLabel alloc] init];
        _chatLabel.numberOfLines = 0;
        _chatLabel.font = MessageFont;
        _chatLabel.textColor = TOSHexAColor(0x282724,1.0);
        _chatLabel.userInteractionEnabled = YES;
    }
    return _chatLabel;
}

- (UILabel *)sensitiveWordsLabel {
    if (!_sensitiveWordsLabel) {
        _sensitiveWordsLabel = [[UILabel alloc] init];
        _sensitiveWordsLabel.textAlignment = NSTextAlignmentRight;
        _sensitiveWordsLabel.numberOfLines = 0;
        _sensitiveWordsLabel.font = MessageFont12;
        _sensitiveWordsLabel.textColor = TOSHexAColor(0x8C8C8C,1.0f);
        _sensitiveWordsLabel.userInteractionEnabled = YES;
        _sensitiveWordsLabel.text = @"消息中包含敏感词无法发送";
    }
    return _sensitiveWordsLabel;
}

@end
