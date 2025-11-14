//
//  UITextField+ExtenRange.m
//  janus-gateway-ios
//
//  Created by YanBo on 2019/11/26.
//  Copyright © 2019 MineWave. All rights reserved.
//

#import "UITextField+ExtenRange.h"

@implementation UITextField (ExtenRange)
//_placeholderLabel.textColor

+ (void)load {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        
        Class selfClass = [self class];
        
        SEL oriSEL = @selector(drawPlaceholderInRect:);
        Method oriMethod = class_getInstanceMethod(selfClass, oriSEL);
        
        SEL cusSEL = @selector(TR_drawPlaceholderInRect:);
        Method cusMethod = class_getInstanceMethod(selfClass, cusSEL);
        
        BOOL addSucc = class_addMethod(selfClass, oriSEL, method_getImplementation(cusMethod), method_getTypeEncoding(cusMethod));
        if (addSucc) {
            class_replaceMethod(selfClass, cusSEL, method_getImplementation(oriMethod), method_getTypeEncoding(oriMethod));
        }else {
            method_exchangeImplementations(oriMethod, cusMethod);
        }
        
    });
}


- (void)TR_drawPlaceholderInRect:(CGRect)rect {
    
    NSMutableAttributedString *placeholderAtbString = [[NSMutableAttributedString alloc] initWithString:self.placeholder];
    [placeholderAtbString addAttribute:NSForegroundColorAttributeName
                                 value:kHexColor(0xCFD8E0)
                                 range:NSMakeRange(0, self.placeholder.length)];
    self.attributedPlaceholder = placeholderAtbString;
    
    [self TR_drawPlaceholderInRect:rect];
}

- (NSRange) selectedRange
{
    UITextPosition* beginning = self.beginningOfDocument;
    
    UITextRange* selectedRange = self.selectedTextRange;
    UITextPosition* selectionStart = selectedRange.start;
    UITextPosition* selectionEnd = selectedRange.end;
    
    const NSInteger location = [self offsetFromPosition:beginning toPosition:selectionStart];
    const NSInteger length = [self offsetFromPosition:selectionStart toPosition:selectionEnd];
    
    return NSMakeRange(location, length);
}

- (void) setSelectedRange:(NSRange) range  // 备注：UITextField必须为第一响应者才有效
{
    UITextPosition* beginning = self.beginningOfDocument;
    
    UITextPosition* startPosition = [self positionFromPosition:beginning offset:range.location];
    UITextPosition* endPosition = [self positionFromPosition:beginning offset:range.location + range.length];
    UITextRange* selectionRange = [self textRangeFromPosition:startPosition toPosition:endPosition];
    
    [self setSelectedTextRange:selectionRange];
}

@end
