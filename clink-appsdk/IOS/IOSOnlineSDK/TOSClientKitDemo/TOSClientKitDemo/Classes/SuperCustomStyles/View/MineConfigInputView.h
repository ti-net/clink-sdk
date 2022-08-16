//
//  MineConfigInputView.h
//  TOSClientKitDemo
//
//  Created by 李成 on 2022/8/2.
//

#import "BaseView.h"

NS_ASSUME_NONNULL_BEGIN

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
@property (nonatomic, assign) BOOL                isRegex;
/// 确认按钮回调
@property (nonatomic, copy) ConfirmAction                action;

- (void)show;

@end

NS_ASSUME_NONNULL_END
