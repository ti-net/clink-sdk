//
//  ICChatMessageRichTextCell.m
//  TOSClientKit
//
//  Created by 言 on 2022/6/14.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "ICChatMessageRichTextCell.h"
#import <TOSClientLib/RichTextMessage.h>
#import "ICMessageConst.h"

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

#import <TOSClientLib/CombinationMessage.h>

@interface ICChatMessageRichTextCell ()

@property (nonatomic, strong) RichTextMessage *model;

@property (nonatomic, strong) CombinationMessage *combinationModel;

@property (nonatomic, strong) UIView *icon;

@end

@implementation ICChatMessageRichTextCell

- (void)setupSubviews {
    [super setupSubviews];
    
    self.backgroundColor = [UIColor clearColor];
    [self addSubview:self.chatLabel];
    [self addSubview:self.icon];
}

- (void)defineLayout {
    [super defineLayout];
    
}

- (void)setWithModel:(RichTextMessage *)model {
    
    self.chatLabel.frame = model.contentF;
    
    if ([model.type isEqualToString:@"ul"]) {
        self.chatLabel.sl_x += 20;
        self.icon.hidden = NO;
    } else {
        self.icon.hidden = YES;
    }
    self.model = model;
    
//    self.chatLabel.font = MessageFont;
    self.chatLabel.font = [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font;
    self.chatLabel.numberOfLines = 0;
    __block NSMutableAttributedString *attStr;
    __block UIColor *textColor = [TOSKitCustomInfo shareCustomInfo].receiveText_Color;
    if ([model.type isEqualToString:@"a"] ||
        [model.type isEqualToString:@"knowledge"]) {
        
        @WeakObj(self)
        attStr = [[NSMutableAttributedString alloc] initWithData:[model.content dataUsingEncoding:NSUnicodeStringEncoding] options:@{NSDocumentTypeDocumentAttribute: NSHTMLTextDocumentType} documentAttributes:nil error:nil];
        
        [attStr addAttribute:NSForegroundColorAttributeName value:textColor range:NSMakeRange(0,attStr.length)]; //设置字体颜色
        [attStr addAttribute:NSFontAttributeName value:self.chatLabel.font range:NSMakeRange(0, attStr.length)];
        
        attStr.font = self.chatLabel.font;
        [attStr setTextHighlightRange:NSMakeRange(0, attStr.length)
                                      color:TOSHexColor(0x4385FF)
                            backgroundColor:[UIColor colorWithWhite:0.000 alpha:0.220]
                                  tapAction:^(UIView *containerView, NSAttributedString *text, NSRange range, CGRect rect){
            @StrongObj(self)
            
            NSString *event = @"";
            if ([model.type isEqualToString:@"a"]) {
                event = TinetRouterClickEventUrl;
            } else if ([model.type isEqualToString:@"knowledge"]) {
                event = TinetRouterClickEventKnowledge;
            }
            [self routerEventWithName:event
                             userInfo:@{@"content"   : self.model.urlPath?:@"",
                                        RouterEventGetSendTextMessageKnowledge : model.knowledge?:@""
                                      }];
            
        }];
    } else {
        
        attStr = [self transferMessageString:model.content?:@"" font:self.chatLabel.font foreColor:textColor lineHeight:self.chatLabel.font.lineHeight];
    }
    
    [model.subElements enumerateObjectsUsingBlock:^(RichTextMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        
        NSRange range = [attStr.string rangeOfString:obj.content];
        if (range.length >= 0 &&
            range.location >= 0) {
            
        } else {
            range = NSMakeRange(0, 0);
        }
        
        
//        NSLog(@"[model yy_modelToJSONObject] ===== %@",[model yy_modelToJSONObject]);
//        NSLog(@"NSStringFromRange(range) ======== %@,  model.content == %lu,   attStr.length ==== %lu,    content ====== %@, attStr.string ===== %@",NSStringFromRange(range),(unsigned long)model.content.length, (unsigned long)attStr.length, model.content, attStr.string);
        
        if (attStr.length >= range.length) {
            
            if ([obj.type isEqualToString:@"a"] ||
                [obj.type isEqualToString:@"knowledge"]) {
                
                @WeakObj(self)
                [attStr addAttribute:NSForegroundColorAttributeName value:textColor range:range]; //设置字体颜色
                [attStr addAttribute:NSFontAttributeName value:self.chatLabel.font range:range];
                
                [attStr setTextHighlightRange:range
                                              color:TOSHexColor(0x4385FF)
                                    backgroundColor:[UIColor colorWithWhite:0.000 alpha:0.220]
                                          tapAction:^(UIView *containerView, NSAttributedString *text, NSRange range, CGRect rect){
                    @StrongObj(self)
                    
                    NSString *event = @"";
                    if ([obj.type isEqualToString:@"a"]) {
                        event = TinetRouterClickEventUrl;
                    } else if ([obj.type isEqualToString:@"knowledge"]) {
                        event = TinetRouterClickEventKnowledge;
                    }
                    [self routerEventWithName:event
                                     userInfo:@{@"content"   : obj.urlPath?:@"",
                                                RouterEventGetSendTextMessageKnowledge : obj.knowledge?:@""
                                              }];
                    
                }];
            } else if ([obj.type isEqualToString:@"strong"]) {
                
                [attStr setStrokeWidth:[NSNumber numberWithInteger:-4] range:range];
            } else if ([obj.type isEqualToString:@"em"]) {
                
                [attStr setTextGlyphTransform:CGAffineTransformMake(1, 0, tanf(10 * (CGFloat)M_PI / 180), 1, 0, 0) range:range];
            } else if ([obj.type isEqualToString:@"span"]) {
                
                if ([obj.style isEqualToString:@"text-decoration: underline;"]) {
                    [attStr setTextUnderline:[YYTextDecoration decorationWithStyle:(YYTextLineStyleSingle)] range:range];
                } else if ([obj.style containsString:@"background-color"]) {
                    
//                    NSString *style = [obj.style stringByReplacingOccurrencesOfString:@"background-color: #" withString:@""];
//                    NSString *colorStr = [style stringByReplacingOccurrencesOfString:@";" withString:@""];
                    NSArray * styleArr = [obj.style componentsSeparatedByString:@";"];
                    NSLog(@"styleArr : %@", styleArr);
                    NSString * colorStr = @"";
                    NSString * substring1 = @" background-color: #";
                    NSString * substring2 = @"background-color: #";
                    for (NSString * itemStr in styleArr) {
                        if ([itemStr hasPrefix:substring1]) {
                            colorStr = [itemStr substringFromIndex:substring1.length];
                        }
                        else if ([itemStr hasPrefix:substring2]) {
                            colorStr = [itemStr substringFromIndex:substring2.length];
                        }
                    }
                    /// 背景色的16进制值为6位数才进行背景颜色的处理
                    if (colorStr.length == 6) {
                        [attStr setBackgroundColor:[self colorWithHexString:colorStr alpha:1.f] range:range];
                    }
                    
                } else if ([obj.style containsString:@"color"]) {
                    
                    NSString *style = [obj.style stringByReplacingOccurrencesOfString:@"color: #" withString:@""];
                    NSString *colorStr = [style stringByReplacingOccurrencesOfString:@";" withString:@""];
                    [attStr setColor:[self colorWithHexString:colorStr alpha:1.f] range:range];
                } else if ([obj.style hasPrefix:@"font-size"]) {
                    NSString *styleStr = [obj.style stringByReplacingOccurrencesOfString:@"font-size: " withString:@""];
                    NSString *sizeStr = [styleStr stringByReplacingOccurrencesOfString:@"px;" withString:@""];
                    [attStr setFont:[UIFont fontWithName:@"PingFangSC-Regular" size:sizeStr.doubleValue]
                              range:range];
                }
            }
        }
    }];
    
    
    // Create text container
    YYTextContainer *container = [[YYTextContainer alloc] init];
    container.size = CGSizeMake(model.contentF.size.width, model.contentF.size.height);
    container.maximumNumberOfRows = 0;
    container.truncationType = YYTextTruncationTypeEnd;
    
    // Generate a text layout.
    YYTextLayout *layout = [YYTextLayout layoutWithContainer:container text:attStr];
    
    self.chatLabel.text = @"";
    
    self.chatLabel.size = layout.textBoundingSize;
    self.chatLabel.textLayout = layout;
    
//    self.chatLabel.font = MessageFont;
    self.chatLabel.numberOfLines = 0;
//    self.chatLabel.lineBreakMode = NSLineBreakByCharWrapping;
    self.chatLabel.lineBreakMode = NSLineBreakByWordWrapping;
}

- (NSMutableAttributedString *)transferMessageString:(NSString *)message
                                                font:(UIFont *)font
                                           foreColor:(UIColor *)fColor
                                          lineHeight:(CGFloat)lineHeight {
    
    __weak typeof(self) weadSelf = self;
    NSMutableAttributedString *attributeStr = [[NSMutableAttributedString alloc] initWithData:[message dataUsingEncoding:NSUnicodeStringEncoding] options:@{NSDocumentTypeDocumentAttribute: NSHTMLTextDocumentType} documentAttributes:nil error:nil];
//    attributeStr.lineBreakMode = NSLineBreakByCharWrapping;
    attributeStr.lineBreakMode = NSLineBreakByWordWrapping;
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
    
//    NSString *pattern = [NSString stringWithFormat:@"%@",emotionPattern];
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

- (YYLabel *)chatLabel
{
    if (nil == _chatLabel) {
        _chatLabel = [[YYLabel alloc] init];
        _chatLabel.numberOfLines = 0;
        _chatLabel.font = MessageFont;
        _chatLabel.textColor = TOSHexAColor(0x282724,1.0);
        _chatLabel.userInteractionEnabled = YES;
//        _chatLabel.lineBreakMode = NSLineBreakByCharWrapping;
        _chatLabel.lineBreakMode = NSLineBreakByWordWrapping;
    }
    return _chatLabel;
}

- (UIView *)icon {
    if (!_icon) {
        _icon = [[UIView alloc] initWithFrame:CGRectMake(4, 5.5f, 12, 12)];
        _icon.backgroundColor = [UIColor blackColor];
        _icon.layer.masksToBounds = YES;
        _icon.layer.cornerRadius = 6.f;
        _icon.hidden = YES;
    }
    return _icon;
}

- (UIColor *)colorWithHexString:(NSString *)colorStr alpha:(CGFloat)alpha {
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
