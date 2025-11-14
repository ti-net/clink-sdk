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
        attStr = [[NSMutableAttributedString alloc] initWithData:[model.content dataUsingEncoding:NSUTF8StringEncoding] options:@{} documentAttributes:nil error:nil];
        
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
        
        attStr = [model.attributedString mutableCopy];
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
