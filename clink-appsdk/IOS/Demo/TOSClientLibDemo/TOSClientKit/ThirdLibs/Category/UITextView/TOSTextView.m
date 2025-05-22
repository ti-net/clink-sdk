//
//  TOSTextView.m
//  TOSClientKit
//
//  Created by 李成 on 2023/7/19.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import "TOSTextView.h"
#import "XZEmotion.h"
#import "SWTextAttachment.h"
#import "TIMConstants.h"
#import "ICFaceManager.h"
#import "kitUtils.h"


@interface TOSTextView ()

@property (nonatomic,strong) UITapGestureRecognizer *sw_emoticonTapGesture;

@end

@implementation TOSTextView

- (instancetype)initWithFrame:(CGRect)frame textContainer:(NSTextContainer *)textContainer {
    self = [super initWithFrame:frame textContainer:textContainer];
    if (self) {
        [self addGesture];
    }
    return self;
}

- (instancetype)initWithCoder:(NSCoder *)coder {
    self = [super initWithCoder:coder];
    if (self) {
        [self addGesture];
    }
    return self;
}


- (void)addGesture {
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(hanlde_sw_emtion_tapGesture:)];
    [self addGestureRecognizer:tap];
    self.sw_emoticonTapGesture = tap;
    self.sw_emoticonTapGesture.enabled = NO;
}

- (void)copy:(id)sender {
    NSAttributedString *selectedAttributedText = [self.attributedText attributedSubstringFromRange:self.selectedRange];
    NSString *str = [self transalteEmoticonAttributedString:selectedAttributedText];
    [UIPasteboard generalPasteboard].string = str;
}

- (void)paste:(id)sender {
    [self pasteTransalteStringEmoticonAttributedWithString:[UIPasteboard generalPasteboard].string];
}

- (void)cut:(nullable id)sender {
    
    NSAttributedString *selectedAttributedText = [self.attributedText attributedSubstringFromRange:self.selectedRange];
    NSString *str = [self transalteEmoticonAttributedString:selectedAttributedText];
    [UIPasteboard generalPasteboard].string = str;
    self.text = @"";
    [self sendSystemMessage];
}

- (void)pasteTransalteStringEmoticonAttributedWithString:(NSString *)string {
    if (!string || [string isEqualToString:@""]) {
        return;
    }
    [self disableDragInteraction];
    NSMutableAttributedString *mutableAttributedStr = [[NSMutableAttributedString alloc] initWithString:string];
    
    NSArray <XZEmotion *>*emoji = [ICFaceManager customEmotion];
    [emoji enumerateObjectsUsingBlock:^(XZEmotion * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        if ([string containsString:obj.face_name]) {
            
            //正则处理
            NSString *leftSquareBrackets = [obj.face_name stringByReplacingOccurrencesOfString:@"[" withString:@"\\["];
            NSString *rightSquareBrackets = [leftSquareBrackets stringByReplacingOccurrencesOfString:@"]" withString:@"\\]"];
            NSRegularExpression *regex = [NSRegularExpression regularExpressionWithPattern:[NSString stringWithFormat:@"%@",rightSquareBrackets] options:NSRegularExpressionDotMatchesLineSeparators error:nil];
            NSArray<NSTextCheckingResult *> *array = [regex matchesInString:mutableAttributedStr.mutableString options:NSMatchingWithTransparentBounds range:NSMakeRange(0, mutableAttributedStr.mutableString.length)];
            
            for (NSTextCheckingResult *text in array) {
                
                //创建附件对象
                SWTextAttachment *attachment = [[SWTextAttachment alloc] init];
                //设置附件图片为表情图片
                attachment.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,obj.face_name]];
                attachment.chs = obj.face_name;
                CGFloat height = self.font.lineHeight;
                
                attachment.bounds = CGRectMake(0, self.font.descender, height, height);
                NSAttributedString *imageAttributedStr = [NSAttributedString attributedStringWithAttachment:attachment];
                
                NSRange range = [mutableAttributedStr.mutableString rangeOfString:obj.face_name];
                [mutableAttributedStr replaceCharactersInRange:range withAttributedString:imageAttributedStr];
            }
        }
    }];
    /// 需要重新设置富文本的字体大小,否则表情会出现大小不一样的bug
    [mutableAttributedStr addAttribute:NSFontAttributeName value:self.font range:NSMakeRange(0, mutableAttributedStr.string.length)];
    NSMutableAttributedString * testMutableAtt = [[NSMutableAttributedString alloc] initWithAttributedString:self.attributedText];
    NSLog(@"当前的输入框的内容：%@", testMutableAtt);
    [testMutableAtt insertAttributedString:mutableAttributedStr atIndex:self.selectedRange.location];
    NSLog(@"粘贴后的输入框内容：%@", testMutableAtt);
    self.attributedText = testMutableAtt;
    /// 直接给attributedText赋值,是不会触发textView的代理方法和通知的
    [self sendSystemMessage];
}

- (NSString *)transalteEmoticonAttributedString:(NSAttributedString *)emoticonAttributedString {
    NSRange range = self.selectedRange;
    NSMutableString *result = [[NSMutableString alloc] initWithString:@""];
    //遍历NSAttributedString,SWTextAttachment对应的字符串
    [self.attributedText enumerateAttributesInRange:range options:0 usingBlock:^(NSDictionary<NSAttributedStringKey,id> * _Nonnull attrs, NSRange range, BOOL * _Nonnull stop) {
        SWTextAttachment *attachment = attrs[NSAttachmentAttributeName];
        if(attachment &&
           [attachment isKindOfClass:[SWTextAttachment class]]){
            
            if (![kitUtils isBlankString:attachment.chs]) {
                [result appendString:attachment.chs];

            }else{
                TIMKitLog(@"attachment.chs 不存在");
            }
        }else{
            NSString *str = [self.attributedText.string substringWithRange:range];
            [result appendString:str];
        }
    }];
    return result;
}

- (void)sw_insertEmoticon:(XZEmotion *)emoticon {
    if(emoticon.face_name.length > 0){//图片表情
        [self disableDragInteraction];
        NSMutableAttributedString *mutableAttributedStr = [[NSMutableAttributedString alloc] initWithAttributedString:self.attributedText];
        //创建附件对象
        SWTextAttachment *attachment = [SWTextAttachment new];
        //设置附件图片为表情图片
        attachment.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,emoticon.face_name]];
        attachment.chs = emoticon.face_name;
        CGFloat height = self.font.lineHeight;
        attachment.bounds = CGRectMake(0, self.font.descender, height, height);
        NSAttributedString *imageAttributedStr = [NSAttributedString attributedStringWithAttachment:attachment];
        NSRange range = self.selectedRange;
        [mutableAttributedStr replaceCharactersInRange:range withAttributedString:imageAttributedStr];
        //需要重新设置富文本的字体大小,否则表情会出现大小不一样的bug
        [mutableAttributedStr addAttribute:NSFontAttributeName value:self.font range:NSMakeRange(0, mutableAttributedStr.string.length)];
        //限制输入的文字长度
        if(self.sw_emoticonDelegate && [self.sw_emoticonDelegate respondsToSelector:@selector(sw_emotionTextView:shouldChangeToAttributedText:)]){
            BOOL should = [self.sw_emoticonDelegate sw_emotionTextView:self shouldChangeToAttributedText:[mutableAttributedStr copy]];
            if(should){
                self.attributedText = mutableAttributedStr;
                //直接给attributedText赋值,是不会触发textView的代理方法和通知的
                [self sendSystemMessage];
                //让光标移动到移动到最后位置
                self.selectedRange = NSMakeRange(range.location + 1, 0);
            }
        }else{
            self.attributedText = mutableAttributedStr;
            //直接给attributedText赋值,是不会触发textView的代理方法和通知的
            [self sendSystemMessage];
            //让光标移动到移动到最后位置
            self.selectedRange = NSMakeRange(range.location + 1, 0);
        }
        return;
    }
    
}

- (NSString *)transalteAllEmoticonsToNormalString {
    return [self transalteEmoticonAttributedString:self.attributedText];
}

#pragma mark - Private
- (void)sendSystemMessage {
    //手动触发代理和通知
    [[NSNotificationCenter defaultCenter] postNotificationName:UITextViewTextDidChangeNotification object:nil];
    if(self.delegate && [self.delegate respondsToSelector:@selector(textViewDidChange:)]){
        [self.delegate textViewDidChange:self];
    }
}

- (void)transalteStringEmoticonAttributedWithString:(NSString *)string {
    if (!string || [string isEqualToString:@""]) {
        return;
    }
    NSMutableAttributedString *mutableAttributedStr = [[NSMutableAttributedString alloc] initWithString:string];
    
    NSArray <XZEmotion *>*emoji = [ICFaceManager customEmotion];
    [emoji enumerateObjectsUsingBlock:^(XZEmotion * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        if ([string containsString:obj.face_name]) {
            
            //正则处理
            NSString *leftSquareBrackets = [obj.face_name stringByReplacingOccurrencesOfString:@"[" withString:@"\\["];
            NSString *rightSquareBrackets = [leftSquareBrackets stringByReplacingOccurrencesOfString:@"]" withString:@"\\]"];
            NSRegularExpression *regex = [NSRegularExpression regularExpressionWithPattern:[NSString stringWithFormat:@"%@",rightSquareBrackets] options:NSRegularExpressionDotMatchesLineSeparators error:nil];
            NSArray<NSTextCheckingResult *> *array = [regex matchesInString:mutableAttributedStr.mutableString options:NSMatchingWithTransparentBounds range:NSMakeRange(0, mutableAttributedStr.mutableString.length)];
            
            for (NSTextCheckingResult *text in array) {
                
                //创建附件对象
                SWTextAttachment *attachment = [[SWTextAttachment alloc] init];
                //设置附件图片为表情图片
                attachment.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,obj.face_name]];
                attachment.chs = obj.face_name;
                CGFloat height = self.font.lineHeight;
                
                attachment.bounds = CGRectMake(0, self.font.descender, height, height);
                NSAttributedString *imageAttributedStr = [NSAttributedString attributedStringWithAttachment:attachment];
                
                NSRange range = [mutableAttributedStr.mutableString rangeOfString:obj.face_name];
                [mutableAttributedStr replaceCharactersInRange:range withAttributedString:imageAttributedStr];
            }
        }
    }];
    //需要重新设置富文本的字体大小,否则表情会出现大小不一样的bug
    [mutableAttributedStr addAttribute:NSFontAttributeName value:self.font range:NSMakeRange(0, mutableAttributedStr.string.length)];
    self.attributedText = mutableAttributedStr;
    //直接给attributedText赋值,是不会触发textView的代理方法和通知的
    [self sendSystemMessage];
}

- (void)hanlde_sw_emtion_tapGesture:(UITapGestureRecognizer *)gesture {
    if(gesture.state == UIGestureRecognizerStateEnded){
        CGPoint point = [gesture locationInView:gesture.view];
        //closestPositionToPoint:根据一个点计算出这个点在UITextView的文本当中最合适的一个位置
        UITextPosition *position = [self closestPositionToPoint:point];
        //beginningOfDocument:UITextView的文本开始位置
        //将点击的点转换成NSRange中的location
        NSInteger location = [self offsetFromPosition:self.beginningOfDocument toPosition:position];
        [self setSelectedRange:NSMakeRange(location, 0)];
    }
}

- (BOOL)becomeFirstResponder {
    BOOL result = [super becomeFirstResponder];
    if (result) {
        self.sw_emoticonTapGesture.enabled = YES;
    }
    return result;
}

- (BOOL)resignFirstResponder {
    BOOL result = [super resignFirstResponder];
    if (result) {
        self.sw_emoticonTapGesture.enabled = NO;
    }
    return result;
}


//禁止重按拖动textView上文本的交互
- (void)disableDragInteraction {
#ifdef __IPHONE_11_0
    if([self respondsToSelector:@selector(textDragInteraction)]){
        self.textDragInteraction.enabled = NO;
    }
#endif
}

@end
