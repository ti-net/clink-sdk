//
//  MineConfigInputView.h
//  TOSClientKitDemo
//
//  Created by 李成 on 2022/8/2.
//

#import "BaseView.h"

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(NSInteger, RegexType) {
    /// 不进行校验
    RegexTypeNormal             =   0,
    /// 颜色校验
    RegexTypeColor              =   1,
    /// 纯数字校验
    RegexTypeNumber             =   2,
    /// 浮点数校验
    RegexTypeFloat              =   3
    
};


typedef void(^ConfirmAction)(NSString * string);

@interface MineConfigInputView : BaseView

/// 标题
@property (nonatomic, copy) NSString                * titleString;
/// 提示
@property (nonatomic, copy) NSString                * tipString;
/// 输入框的值
@property (nonatomic, copy) NSString                * textString;
/// 提示文案颜色
@property (nonatomic, strong) UIColor                * tipTextColor;
/// 输入框文本长度
@property (nonatomic, assign) NSInteger                textMaxLength;
/// 输入文本是否需要正则校验
@property (nonatomic, assign) RegexType                regex;
/// 输入文本去除空格
@property (nonatomic, assign) BOOL                removeSpaces;
/// 确认按钮回调
@property (nonatomic, copy) ConfirmAction                action;

- (void)show;

@end

NS_ASSUME_NONNULL_END
