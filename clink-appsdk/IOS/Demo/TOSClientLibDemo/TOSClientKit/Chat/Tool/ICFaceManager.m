//
//  ICFaceManager.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/4/1.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICFaceManager.h"
#import "TIMExtension.h"
#import "XZEmotion.h"
#import "ICFaceManager.h"
#import <UIKit/UIKit.h>
#import "TIMConstants.h"
#import "kitUtils.h"
#import "NSAttributedString+YYText.h"

#define ICBundle [NSBundle mainBundle]

@implementation ICFaceManager

static NSArray * _emojiEmotions,*_custumEmotions,*_gifEmotions;

+ (NSArray *)emojiEmotion
{
    if (_emojiEmotions) {
        return _emojiEmotions;
    }
    NSString *path  = [ICBundle pathForResource:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"emoji.plist"] ofType:nil];
    TIMKitLog(@"emojiEmotion path = %@",path);
    _emojiEmotions  = [XZEmotion mjtim_objectArrayWithKeyValuesArray:[NSArray arrayWithContentsOfFile:path]];
    return _emojiEmotions;
}

+ (NSArray *)customEmotion
{
    if (_custumEmotions) {
        return _custumEmotions;
    }
    NSString *path  = [ICBundle pathForResource:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"osNormal_face.plist"] ofType:nil];
    NSArray *arrry = [NSArray arrayWithContentsOfFile:path];
    if (arrry && arrry.count > 0) {
        _custumEmotions = [XZEmotion mjtim_objectArrayWithKeyValuesArray:arrry];
    } else {
        _custumEmotions = [NSArray array];
    }
    return _custumEmotions;
}

+ (NSArray *)gifEmotion
{
    if (_gifEmotions) {
        return _gifEmotions;
    }
    NSString *path  = [ICBundle pathForResource:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"gif_face.plist"] ofType:nil];
    _gifEmotions = [XZEmotion mjtim_objectArrayWithKeyValuesArray:[NSArray arrayWithContentsOfFile:path]];
    return _gifEmotions;
}

+ (NSMutableAttributedString *)transferMessageString:(NSString *)message
                                                font:(UIFont *)font
                                           foreColor:(UIColor *)fColor
                                          lineHeight:(CGFloat)lineHeight
{
    
    NSMutableAttributedString *attributeStr
                      = [[NSMutableAttributedString alloc] initWithString:message];
    NSString *regEmj  = @"\\[[a-zA-Z0-9\\/\\u4e00-\\u9fa5]+\\]";// [微笑]、［哭］等自定义表情处理
    NSError *error    = nil;
    NSRegularExpression *expression = [NSRegularExpression regularExpressionWithPattern:regEmj options:NSRegularExpressionCaseInsensitive error:&error];
    if (!expression) {
        //ICLog(@"%@",error);
        return attributeStr;
    }
    [attributeStr addAttribute:NSForegroundColorAttributeName value:fColor range:NSMakeRange(0,attributeStr.length)]; //设置字体颜色
    [attributeStr addAttribute:NSFontAttributeName value:font range:NSMakeRange(0, attributeStr.length)];
    NSArray *resultArray = [expression matchesInString:message options:0 range:NSMakeRange(0, message.length)];
    NSMutableArray *mutableArray = [NSMutableArray arrayWithCapacity:resultArray.count];
    for (NSTextCheckingResult *match in resultArray) {
        NSRange range    = match.range;
        NSString *subStr = [message substringWithRange:range];
        NSArray *faceArr = [ICFaceManager customEmotion];
        for (XZEmotion *face in faceArr) {
            if ([face.face_name isEqualToString:subStr]) {
//                NSTextAttachment *attach   = [[NSTextAttachment alloc] init];
//
//                attach.image               = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,face.face_name]];
//                // 位置调整Y值就行
//                attach.bounds              = CGRectMake(0, -4, lineHeight, lineHeight);
//
//
                
                
                UIImage *image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,face.face_name]];
                image = [UIImage imageWithCGImage:image.CGImage scale:[UIScreen mainScreen].scale orientation:UIImageOrientationUp];
                
                NSMutableAttributedString *attachText = [NSMutableAttributedString attachmentStringWithContent:image contentMode:UIViewContentModeScaleAspectFit attachmentSize:CGSizeMake(lineHeight, lineHeight) alignToFont:font alignment:YYTextVerticalAlignmentCenter];
                
                
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
    return attributeStr;
}





@end
