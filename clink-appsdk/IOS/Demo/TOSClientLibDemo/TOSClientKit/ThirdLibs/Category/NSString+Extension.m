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
#import "YYKit.h"

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

- (CGSize)sizeWithMaxWidth:(CGFloat)width andFont:(UIFont *)font
{
    return [self sizeWithMaxWidth:width andFont:font fontDic:nil];
}

- (CGSize)sizeWithMaxWidth:(CGFloat)width
                   andFont:(UIFont *)font
                   fontDic:(NSMutableArray <NSDictionary *>*)fontDic {
    
    NSMutableAttributedString *attributeStr
                      = [[NSMutableAttributedString alloc] initWithData:[self dataUsingEncoding:NSUnicodeStringEncoding] options:@{NSDocumentTypeDocumentAttribute: NSHTMLTextDocumentType, NSCharacterEncodingDocumentAttribute:[NSNumber numberWithInt:NSUTF8StringEncoding]} documentAttributes:nil error:nil];

    NSString * updateString = [attributeStr string];
    attributeStr = [[NSMutableAttributedString alloc] initWithString:updateString attributes:nil];

    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    paragraphStyle.lineBreakMode = NSLineBreakByWordWrapping;
    [attributeStr addAttribute:NSParagraphStyleAttributeName value:paragraphStyle range:NSMakeRange(0, attributeStr.string.length)];
    
    NSString *regEmj  = @"\\[[a-zA-Z0-9\\/\\u4e00-\\u9fa5]+\\]";// [微笑]、［哭］等自定义表情处理
    NSError *error    = nil;
    NSRegularExpression *expression = [NSRegularExpression regularExpressionWithPattern:regEmj options:NSRegularExpressionCaseInsensitive error:&error];
    if (!expression) {
        //ICLog(@"%@",error);
        return CGSizeMake(0,0);
    }

    [attributeStr addAttribute:NSFontAttributeName value:font range:NSMakeRange(0, attributeStr.string.length)];
    
    [fontDic enumerateObjectsUsingBlock:^(NSDictionary * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        [attributeStr addAttribute:NSFontAttributeName value:obj[@"font"]?:@"" range:[attributeStr.string rangeOfString:obj[@"content"]?:@""]];
    }];
    
    NSArray *resultArray = [expression matchesInString:attributeStr.string options:0 range:NSMakeRange(0, attributeStr.string.length)];
    NSMutableArray *mutableArray = [NSMutableArray arrayWithCapacity:resultArray.count];
    NSInteger mutableArrayLength = 0;
    for (NSTextCheckingResult *match in resultArray) {
        NSRange range    = match.range;
        NSString *subStr = [attributeStr.string substringWithRange:range];
        mutableArrayLength += subStr.length;
        NSArray *faceArr = [ICFaceManager customEmotion];
        for (XZEmotion *face in faceArr) {
            if ([face.face_name isEqualToString:subStr]) {
                UIImage *image = [[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,face.face_name]] imageWithRenderingMode:(UIImageRenderingModeAlwaysOriginal)];
                image = [UIImage imageWithCGImage:image.CGImage scale:[UIScreen mainScreen].scale orientation:UIImageOrientationUp];
                
                NSMutableAttributedString *attachText = [NSMutableAttributedString attachmentStringWithContent:image contentMode:UIViewContentModeScaleAspectFit attachmentSize:CGSizeMake(1.f*font.lineHeight, 1.f*font.lineHeight) alignToFont:font alignment:YYTextVerticalAlignmentCenter];
                
                [attachText addAttribute:NSKernAttributeName value:@(100) range:NSMakeRange(0, attachText.string.length)];
                
                NSMutableDictionary *imagDic   = [NSMutableDictionary dictionaryWithCapacity:2];
                [imagDic setObject:attachText forKey:@"image"];
                [imagDic setObject:[NSValue valueWithRange:range] forKey:@"range"];
                [mutableArray addObject:imagDic];
            }
        }
    }
    
    for (int i =(int) mutableArray.count - 1; i >= 0; i --) {
        NSRange range;
        [mutableArray[i][@"range"] getValue:&range];
        [attributeStr replaceCharactersInRange:range withAttributedString:mutableArray[i][@"image"]];
    }
    YYLabel *label = [[YYLabel alloc] init];
    label.lineBreakMode = NSLineBreakByWordWrapping;
    [label setNumberOfLines:0];
    [label setAttributedText:attributeStr];
    return [label sizeThatFits:CGSizeMake(width, MAXFLOAT)];
}

- (CGSize)defaultLabelSizeWithMaxWidth:(CGFloat)width andFont:(UIFont *)font {
    CGRect rect = [self boundingRectWithSize:CGSizeMake(width, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName:font} context:nil];
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



@end
