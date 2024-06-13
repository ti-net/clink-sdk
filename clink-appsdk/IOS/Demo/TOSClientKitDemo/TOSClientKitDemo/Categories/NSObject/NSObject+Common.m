//
//  NSObject+Common.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/10.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "NSObject+Common.h"

@implementation NSObject (Common)

- (BOOL)dictionaryContainsKey:(NSString *)key {
    if (self &&
        [self isKindOfClass:[NSDictionary class]] &&
        [[(NSDictionary *)self allKeys] containsObject:key]) {
        return YES;
    }
    return NO;
}

- (NSString *)stringValue {
    NSString *finalString = @"";
    NSString *_string = [NSString stringWithFormat:@"%@", self];
    if (_string &&
        ![_string isEqual:[NSNull null]] &&
        ![_string isEqualToString:@"null"] &&
        ![_string isEqualToString:@"(null)"] &&
        ![_string isEqualToString:@"<null>"]) {
        finalString = [NSString stringWithFormat:@"%@", _string];
    }
    return finalString;
}

- (NSString *)jsonStr {
    NSString *jsonStr = @"";
    @try {
        NSData *jsonData = [NSJSONSerialization dataWithJSONObject:self options:NSJSONWritingPrettyPrinted error:nil];
        jsonStr = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    }
    @catch (NSException *exception) {
        NSLog(@"%s [Line %d] 对象转换成JSON字符串出错了-->\n%@",__PRETTY_FUNCTION__, __LINE__,exception);
    }
    @finally {
    }
    return jsonStr;
}

- (NSData *)jsonData {
    NSData *jsonData = [NSData data];
    @try {
        jsonData = [NSJSONSerialization dataWithJSONObject:self options:NSJSONWritingPrettyPrinted error:nil];
    }
    @catch (NSException *exception) {
        NSLog(@"%s [Line %d] 对象转换成JSON字符串出错了-->\n%@",__PRETTY_FUNCTION__, __LINE__,exception);
    }
    @finally {
    }
    return jsonData;
}

/// TextField限制字符数
/// @param textField textField
/// @param maxLength 字数限制
- (void)tr_TextFieldDidChange:(UITextField *)textField maxLength:(NSInteger)maxLength {
    NSString *toBeString = textField.text;
    
    //获取高亮部分
    UITextRange *selectedRange = [textField markedTextRange];
    UITextPosition *position = [textField positionFromPosition:selectedRange.start offset:0];
    
    // 没有高亮选择的字，则对已输入的文字进行字数统计和限制
    if (!position) {
        if (toBeString.length > maxLength) {
            NSRange rangeIndex = [toBeString rangeOfComposedCharacterSequenceAtIndex:maxLength];
            if (rangeIndex.length == 1) {
                textField.text = [toBeString substringToIndex:maxLength];
            } else {
                NSRange rangeRange = [toBeString rangeOfComposedCharacterSequencesForRange:NSMakeRange(0, maxLength)];
                textField.text = [toBeString substringWithRange:rangeRange];
            }
        }
    }
}

@end
