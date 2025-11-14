//
//  NSString+Extension.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/9/27.
//  Copyright © 2016年 gxz. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "NSString+Extension.h"
#import "ICFaceManager.h"
#import "XZEmotion.h"
#import "TIMConstants.h"
#import "ICMessageConst.h"
#import "YYKit.h"
#import "TOSKitCustomInfo.h"

#define EmojiCodeToSymbol(c) ((((0x808080F0 | (c & 0x3F000) >> 4) | (c & 0xFC0) << 10) | (c & 0x1C0000) << 18) | (c & 0x3F) << 24)

@implementation NSString (Extension)

- (NSString *)emoji
{
    return [NSString emojiWithStringCode:self];
}

+ (NSString *)emojiWithStringCode:(NSString *)stringCode
{
    char *charCode = (char *)stringCode.UTF8String;
    long intCode = strtol(charCode, NULL, 16);
    return [self emojiWithIntCode:(int)intCode];
}

+ (NSString *)emojiWithIntCode:(int)intCode {
    int symbol = EmojiCodeToSymbol(intCode);
    NSString *string = [[NSString alloc] initWithBytes:&symbol length:sizeof(symbol) encoding:NSUTF8StringEncoding];
    if (string == nil) { // 新版Emoji
        string = [NSString stringWithFormat:@"%C", (unichar)intCode];
    }
    return string;
}

- (NSMutableAttributedString *)transferMessageFont:(UIFont *)font
                                         foreColor:(UIColor *)fColor
                                           fontDic:(NSMutableArray <NSDictionary *>*)fontDic
                                        lineHeight:(CGFloat)lineHeight
                                  filteredLineFeed:(BOOL)filteredLineFeed {
    
    NSString *content = [self mutableCopy];
    if (filteredLineFeed) {
        content = [self stringByReplacingOccurrencesOfString:@"\n" withString:@" "];
    }
    
    __weak typeof(self) weadSelf = self;
    NSMutableAttributedString *attributeStr = [[NSMutableAttributedString alloc] initWithData:[content dataUsingEncoding:NSUTF8StringEncoding] options:@{} documentAttributes:nil error:nil];
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
//    NSString *orderNumberPattern = @"([a-zA-Z0-9_]{5,})";
    
    // url链接的规则
//    NSString *urlPattern = @"(((http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(www.[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(((http[s]{0,1}|ftp)://|)((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?))";
    
    //    NSString *urlPattern = @"(<a[a-zA-Z0-9\\s:/%=><&\\?\\-~_\\$#+\\.,;\"']*(</a>))|([a-zA-Z0-9_]{5,})";
    
//    NSString *phoneNumber = @"(\\d{3}-\\d{8}|\\d{4}-\\d{7}|\\d{11})";
//    
//    NSString *pattern = [NSString stringWithFormat:@"%@|%@|%@|%@", urlPattern, emotionPattern, phoneNumber, orderNumberPattern];
    NSString *pattern = [NSString stringWithFormat:@"%@", emotionPattern];
    
    /// 判断超链接正则是否为null
    if (TOSKitCustomInfo.shareCustomInfo.urlRegular != nil) {
        if (TOSKitCustomInfo.shareCustomInfo.urlRegular.regular.length > 0) {
            pattern = [NSString stringWithFormat:@"%@|%@", pattern, TOSKitCustomInfo.shareCustomInfo.urlRegular.regular];
        }
    }
    
    /// 判断手机号正则是否为null
    if (TOSKitCustomInfo.shareCustomInfo.telRegular != nil) {
        if (TOSKitCustomInfo.shareCustomInfo.telRegular.regular.length > 0) {
            pattern = [NSString stringWithFormat:@"%@|%@", pattern, TOSKitCustomInfo.shareCustomInfo.telRegular.regular];
        }
    }
    
    /// 判断订单号正则是否为null
    if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular != nil) {
        if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.regular.length > 0) {
            pattern = [NSString stringWithFormat:@"%@|%@", pattern, TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.regular];
        }
    }
    
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
            UIColor * highColor = fColor;
            if (TOSKitCustomInfo.shareCustomInfo.urlRegular != nil && TOSKitCustomInfo.shareCustomInfo.urlRegular.highlightColor != nil) {
                highColor = TOSKitCustomInfo.shareCustomInfo.urlRegular.highlightColor;
            }
            [attributeStr setTextHighlightRange:range
                                          color:highColor
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
            
            /// 判断电话正则是否为null。(tips：电话的优先级在订单号之上)
            if (TOSKitCustomInfo.shareCustomInfo.telRegular != nil) {
                if (TOSKitCustomInfo.shareCustomInfo.telRegular.regular.length > 0) {
                    NSRegularExpression *regex = [NSRegularExpression regularExpressionWithPattern:TOSKitCustomInfo.shareCustomInfo.telRegular.regular options:NSRegularExpressionCaseInsensitive error:nil];
                    NSArray *matches = [regex matchesInString:subStr options:NSMatchingReportProgress range:NSMakeRange(0, [subStr length])];
                    if (matches.count > 0) {
                        UIColor * highColor = fColor;
                        if (TOSKitCustomInfo.shareCustomInfo.telRegular != nil && TOSKitCustomInfo.shareCustomInfo.telRegular.highlightColor != nil) {
                            highColor = TOSKitCustomInfo.shareCustomInfo.telRegular.highlightColor;
                        }
                        [attributeStr setTextHighlightRange:range
                                                      color:highColor
                                            backgroundColor:[UIColor colorWithWhite:0.000 alpha:0.220]
                                                  tapAction:^(UIView *containerView, NSAttributedString *text, NSRange range, CGRect rect){

                            __strong typeof(weadSelf) strongSelf = weadSelf;
                            [strongSelf routerEventWithName:TinetRouterClickEventPhone
                                                   userInfo:@{@"content"   : [text.string substringWithRange:range]
                                                            }];

                        }];
                    }
                    else {
                        if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular != nil) {
                            if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.regular.length > 0) {
                                //订单号
                                NSLog(@"订单号匹配：%@", subStr);
                                UIColor * highColor = fColor;
                                if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.highlightColor != nil) {
                                    highColor = TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.highlightColor;
                                }
                                [attributeStr setTextHighlightRange:range
                                                              color:highColor
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
                }
            }
            else {
                if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular != nil) {
                    if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.regular.length > 0) {
                        //订单号
                        NSLog(@"订单号匹配：%@", subStr);
                        UIColor * highColor = fColor;
                        if (TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.highlightColor != nil) {
                            highColor = TOSKitCustomInfo.shareCustomInfo.orderNumberRegular.highlightColor;
                        }
                        [attributeStr setTextHighlightRange:range
                                                      color:highColor
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
        }
    }
    for (int i =(int) mutableArray.count - 1; i >= 0; i --) {
        NSRange range;
        [mutableArray[i][@"range"] getValue:&range];
        [attributeStr replaceCharactersInRange:range withAttributedString:mutableArray[i][@"image"]];
    }
    
    attributeStr.font = font;
    
    [fontDic enumerateObjectsUsingBlock:^(NSDictionary * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        [attributeStr addAttribute:NSFontAttributeName value:obj[@"font"]?:@"" range:[attributeStr.string rangeOfString:obj[@"content"]?:@""]];
    }];
    
    return attributeStr;
}

- (void)routerEventWithName:(NSString *)eventName userInfo:(NSDictionary *)userInfo {
    [[NSNotificationCenter defaultCenter] postNotificationName:kTIMRouterEventNotification object:@{
        @"eventName": eventName,
        @"userInfo": userInfo,
    }];
}

- (CGSize)tim_sizeWithMaxWidth:(CGFloat)width
                  attributeStr:(NSMutableAttributedString *)attributeStr {
    
    YYLabel *label = [[YYLabel alloc] init];
    label.lineBreakMode = NSLineBreakByWordWrapping;
    [label setNumberOfLines:0];
    [label setAttributedText:attributeStr];
    return [label sizeThatFits:CGSizeMake(width, MAXFLOAT)];
}

- (CGSize)tim_sizeWithMaxWidth:(CGFloat)width
                       andFont:(UIFont *)font {
    
    CGSize textSize = [self boundingRectWithSize:CGSizeMake(width, MAXFLOAT)
                                         options:(NSStringDrawingUsesLineFragmentOrigin | NSStringDrawingUsesFontLeading)
                                      attributes:@{NSFontAttributeName: font}
                                         context:nil].size;
    return textSize;
}

- (CGSize)defaultLabelSizeWithMaxWidth:(CGFloat)width andFont:(UIFont *)font {
    CGRect rect = [self boundingRectWithSize:CGSizeMake(width, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName:font} context:nil];
    return rect.size;
}

- (CGSize)defaultLabelSizeWithMaxHeight:(CGFloat)height andFont:(UIFont *)font {
    CGRect rect = [self boundingRectWithSize:CGSizeMake(MAXFLOAT, height) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName:font} context:nil];
    return rect.size;
}

- (NSString *)originName
{
    NSArray *list = [self componentsSeparatedByString:@"_"];
    NSMutableString *orgName = [NSMutableString string];
    NSUInteger count = list.count;
    if (list.count > 1) {
        for (int i = 1; i < count; i ++) {
            [orgName appendString:list[i]];
            if (i < count-1) {
                [orgName appendString:@"_"];
            }
        }
    } else {  // 防越狱的情况下，本地改名字
        orgName = list[0];
    }
    return orgName;
}

+ (NSString *)currentName
{
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"YYYYMMddHHMMss"];
    NSString *currentDate = [dateFormatter stringFromDate:[NSDate date]];
    return currentDate;
}

- (NSString *)firstStringSeparatedByString:(NSString *)separeted
{
    NSArray *list = [self componentsSeparatedByString:separeted];
    return [list firstObject];
}


/// 计算富文本所占的大小
- (CGSize)tim_sizeForWithMaxWidth:(CGFloat)width lineSpacing:(CGFloat)lineSpacing withFont:(UIFont *)font {
    NSAttributedString * attributedString = [[NSAttributedString alloc] initWithString:self];
    /// 创建一个包含段落样式的 NSMutableParagraphStyle 对象
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    paragraphStyle.lineSpacing = lineSpacing;
    paragraphStyle.lineBreakMode = NSLineBreakByCharWrapping;
    paragraphStyle.maximumLineHeight = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont.lineHeight;
    paragraphStyle.minimumLineHeight = [TOSKitCustomInfo shareCustomInfo].chatMessage_system_textFont.lineHeight;
    
    /// 创建一个包含段落样式的新的富文本属性字典
    NSDictionary *attributes = @{
        NSParagraphStyleAttributeName: paragraphStyle,
        NSFontAttributeName: font,
        NSForegroundColorAttributeName: TOSKitCustomInfo.shareCustomInfo.chatMessage_system_textColor,
    };
    
    /// 创建一个包含段落样式的新 NSAttributedString
    NSMutableAttributedString *attributedStringWithLineHeight = [[NSMutableAttributedString alloc] initWithAttributedString:attributedString];
    [attributedStringWithLineHeight addAttributes:attributes range:NSMakeRange(0, attributedString.length)];
    
    /// 计算文本所需的尺寸
    CGRect rect = [attributedStringWithLineHeight boundingRectWithSize:CGSizeMake(width, MAXFLOAT)
                                                               options:(NSStringDrawingUsesLineFragmentOrigin | NSStringDrawingUsesFontLeading)
                                                               context:nil];
    /// 向上取整
    return CGSizeMake(ceil(rect.size.width), ceil(rect.size.height));
}

@end
