//
//  MineConfigInputView.m
//  TOSClientKitDemo
//
//  Created by 李成 on 2022/8/2.
//

#import "MineConfigInputView.h"
#import <Masonry.h>

@interface MineConfigInputView ()<UITextFieldDelegate>
/// 背景视图
@property (nonatomic, strong) UIView                * bgView;

/// 关闭按钮
@property (nonatomic, strong) UIButton                * closeBtn;

/// 标题
@property (nonatomic, strong) UILabel                * titleLabel;

/// 确认按钮
@property (nonatomic, strong) UIButton                * confirmBtn;

/// 输入框背景视图
@property (nonatomic, strong) UIView                * textFieldBgView;

/// 输入框
@property (nonatomic, strong) UITextField                * textField;

/// 提示标签
@property (nonatomic, strong) UILabel                * tipsLabel;


@end


@implementation MineConfigInputView

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        self.backgroundColor = [[UIColor colorWithHexString:@"#000000"] colorWithAlphaComponent:0.25];
        [self addSubview:self.bgView];
        [self.bgView addSubview:self.closeBtn];
        [self.bgView addSubview:self.titleLabel];
        [self.bgView addSubview:self.confirmBtn];
        [self.bgView addSubview:self.textFieldBgView];
        [self.bgView addSubview:self.textField];
        [self.bgView addSubview:self.tipsLabel];
        
        [self.bgView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.mas_equalTo(0);
            make.height.mas_equalTo(143);
            make.top.mas_equalTo(300);
        }];
        
        [self.closeBtn mas_makeConstraints:^(MASConstraintMaker *make) {
            make.width.height.mas_equalTo(26);
            make.left.mas_equalTo(10);
            make.top.mas_equalTo(19);
        }];
        
        [self.confirmBtn mas_makeConstraints:^(MASConstraintMaker *make) {
            make.width.mas_equalTo(56);
            make.height.mas_equalTo(32);
            make.right.mas_equalTo(-17);
            make.top.mas_equalTo(16);
        }];
        
        [self.titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.closeBtn.mas_right);
            make.top.mas_equalTo(20);
            make.right.equalTo(self.confirmBtn.mas_left);
            make.height.mas_equalTo(22);
        }];
        
        [self.textFieldBgView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.mas_equalTo(0);
            make.top.equalTo(self.titleLabel.mas_bottom).offset(22);
            make.height.mas_equalTo(44);
        }];
        
        [self.textField mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(16);
            make.right.mas_equalTo(-16);
            make.top.equalTo(self.titleLabel.mas_bottom).offset(22);
            make.height.mas_equalTo(44);
            
        }];
        
//        [self.textClearBtn mas_makeConstraints:^(MASConstraintMaker *make) {
//            make.width.height.mas_equalTo(34);
//            make.right.mas_equalTo(-4);
//            make.centerY.equalTo(self.textField.mas_centerY);
//        }];
        
        [self.tipsLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(15);
            make.top.equalTo(self.textField.mas_bottom).offset(7);
            make.right.mas_equalTo(-15);
            make.height.mas_equalTo(22);
        }];
        
        self.textMaxLength = 7;
        
        
        
        // 键盘通知
        [[NSNotificationCenter defaultCenter] addObserver:self
                                                 selector:@selector(onKeyboardWillShowNotification:)
                                                     name:UIKeyboardWillShowNotification
                                                   object:nil];
        [[NSNotificationCenter defaultCenter] addObserver:self
                                                 selector:@selector(onKeyboardDidShowNotification:)
                                                     name:UIKeyboardDidShowNotification
                                                   object:nil];
        
        
    }
    return self;
}

#pragma mark - NSNotificationCenter

- (void)onKeyboardDidShowNotification:(NSNotification *)notification {
//    CGRect keyboardRect = [[notification.userInfo objectForKey:UIKeyboardFrameEndUserInfoKey] CGRectValue];
//    NSLog(@"keyboardRect : %@", NSStringFromCGRect(keyboardRect));
//
//    CGFloat top = CGRectGetMinY(keyboardRect) - 143;
//    NSLog(@"计算后的高度：%lf  SCREEN_HEIGHT : %lf", top, SCREEN_HEIGHT);
//    [self.bgView mas_updateConstraints:^(MASConstraintMaker *make) {
//        make.top.mas_equalTo(top);
//    }];
    
}

- (void)onKeyboardWillShowNotification:(NSNotification *)notification {
    CGRect keyboardRect = [[notification.userInfo objectForKey:UIKeyboardFrameEndUserInfoKey] CGRectValue];
    CGFloat top = CGRectGetMinY(keyboardRect) - 143;
    [self.bgView mas_updateConstraints:^(MASConstraintMaker *make) {
        make.top.mas_equalTo(top);
    }];
    
}

- (void)show {
    
    [[UIApplication sharedApplication].keyWindow addSubview:self];
    /// 让输入框成为第一响应者
    [self.textField becomeFirstResponder];
    [UIView animateWithDuration:.3f animations:^{
        self.alpha = 1.f;
    } completion:^(BOOL finished) {
        
    }];
    
}


#pragma mark - UITextFieldDelegate

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
//    unichar single = [string characterAtIndex:0];//当前输入的字符
//    if ((single >= '0' && single <= '9')) {
//
//    }
    return YES;
}

/// 16进制正则表达式
- (NSString *)regular {
    return @"^#[A-Fa-f0-9]+$";
}

/// 校验正则
- (BOOL)regexValidate:(NSString *)regex withString:(NSString *)string {
    
    if (string.length == 0) {
        NSLog(@"不能为空！");
        return NO;
    }
    NSPredicate *accountStringPredicate = [NSPredicate predicateWithFormat:@"SELF MATCHES %@",regex];
    if(![accountStringPredicate evaluateWithObject:string]) {
        NSLog(@"%@格式不正确！", string);
        self.tipsLabel.text = @"格式错误，请输入有效的 6 位数色值";
        self.tipsLabel.textColor = [UIColor colorWithHexString:@"#FF4D4F"];
        return NO;
    };
    return YES;
}

- (void)textFieldDidChangeSelection:(UITextField *)textField {
    
    NSLog(@"textField : %@", textField.text);
    NSString *toBeString = textField.text;
    if (self.isRegex && toBeString.length > 1) {
        BOOL regex = [self regexValidate:[self regular] withString:toBeString];
        if (regex) {
            self.tipsLabel.text = self.tipString;
            self.tipsLabel.textColor = self.tipTextColor;
        }
    }
    
    /// 获取高亮部分
    UITextRange *selectedRange = [textField markedTextRange];
    UITextPosition *position = [textField positionFromPosition:selectedRange.start offset:0];
    
    /// 没有高亮选择的字，则对已输入的文字进行字数统计和限制
    if (!position) {
        if (toBeString.length > self.textMaxLength) {
            NSRange rangeIndex = [toBeString rangeOfComposedCharacterSequenceAtIndex:self.textMaxLength];
            if (rangeIndex.length == 1) {
                textField.text = [toBeString substringToIndex:self.textMaxLength];
            } else {
                NSRange rangeRange = [toBeString rangeOfComposedCharacterSequencesForRange:NSMakeRange(0, self.textMaxLength)];
                textField.text = [toBeString substringWithRange:rangeRange];
            }
        }
        
    }
    
}

//- (void)tr_TextFieldDidChange:(UITextField *)textField maxLength:(NSInteger)maxLength {
//
//
//}

#pragma mark - Touch

- (void)closeTouch {
    
    [self removeFromSuperview];
    
}

- (void)confirmTouch {
    
    if (self.isRegex) {
        BOOL regex = [self regexValidate:[self regular] withString:self.textField.text];
        if (regex) {
            if (self.action) {
                self.action(self.textField.text);
                [self closeTouch];
            }
        }
        else {
            [self showErrorView:@"格式错误，请输入有效的 6 位数色值"];
        }
    }
    else {
        if (self.action) {
            self.action(self.textField.text);
            [self closeTouch];
        }
    }
    
}

- (void)clearBtn {
    
    self.textField.text = @"";
    
}

#pragma mark - setter

- (void)setTitleString:(NSString *)titleString {
    _titleString = titleString;
    
    self.titleLabel.text = titleString;
}

- (void)setTextString:(NSString *)textString {
    _textString = textString;
    
    self.textField.text = textString;
}

- (void)setTipString:(NSString *)tipString {
    _tipString = tipString;
    
    self.tipsLabel.text = tipString;
}

#pragma mark - 懒加载

- (UIView *)bgView {
    if (!_bgView) {
        _bgView = [[UIView alloc] initWithFrame:(CGRectZero)];
        _bgView.backgroundColor = [UIColor colorWithHexString:@"#F6F7F8"];
        _bgView.layer.cornerRadius = 8.0f;
        /// 顶部
        _bgView.layer.maskedCorners = kCALayerMinXMinYCorner | kCALayerMaxXMinYCorner;
        _bgView.layer.masksToBounds = YES;
        
    }
    return _bgView;
}

- (UIButton *)closeBtn {
    if (!_closeBtn) {
        _closeBtn = [[UIButton alloc] initWithFrame:(CGRectZero)];
        [_closeBtn setImage:[UIImage imageNamed:@"configInput_Close"] forState:(UIControlStateNormal)];
        [_closeBtn addTarget:self action:@selector(closeTouch) forControlEvents:(UIControlEventTouchUpInside)];
        
    }
    return _closeBtn;
}

- (UILabel *)titleLabel {
    if (!_titleLabel) {
        _titleLabel = [[UILabel alloc] initWithFrame:(CGRectZero)];
        _titleLabel.font = [UIFont fontWithName:@"PingFang SC-Medium" size:16];
        _titleLabel.textColor = [UIColor colorWithHexString:@"#333333"];
        _titleLabel.textAlignment = 1;
        
    }
    return _titleLabel;
}

- (UIButton *)confirmBtn {
    if (!_confirmBtn) {
        _confirmBtn = [[UIButton alloc] initWithFrame:(CGRectZero)];
        [_confirmBtn setTitle:@"确认" forState:(UIControlStateNormal)];
        [_confirmBtn setTitleColor:[UIColor colorWithHexString:@"#FFFFFF"] forState:(UIControlStateNormal)];
        _confirmBtn.titleLabel.font = [UIFont systemFontOfSize:15];
        _confirmBtn.layer.cornerRadius = 4.0f;
        [_confirmBtn setBackgroundColor:[UIColor colorWithHexString:@"#4385FF"]];
        [_confirmBtn addTarget:self action:@selector(confirmTouch) forControlEvents:(UIControlEventTouchUpInside)];
        
    }
    return _confirmBtn;
}

- (UIView *)textFieldBgView {
    if (!_textFieldBgView) {
        _textFieldBgView = [[UIView alloc] initWithFrame:(CGRectZero)];
        _textFieldBgView.backgroundColor = [UIColor colorWithHexString:@"#FFFFFF"];
        
    }
    return _textFieldBgView;
}

- (UITextField *)textField {
    if (!_textField) {
        _textField = [[UITextField alloc] initWithFrame:(CGRectZero)];
        _textField.backgroundColor = [UIColor colorWithHexString:@"#FFFFFF"];
        _textField.font = [UIFont systemFontOfSize:16];
        _textField.delegate = self;
        _textField.rightViewMode = UITextFieldViewModeWhileEditing;
        UIButton * btn = [[UIButton alloc] initWithFrame:(CGRectMake(0, 0, 40, 40))];
        [btn setImage:[UIImage imageNamed:@"configInput_TextFiledClose"] forState:(UIControlStateNormal)];
        [btn addTarget:self action:@selector(clearBtn) forControlEvents:(UIControlEventTouchUpInside)];
        _textField.rightView = btn;
        
        
        
    }
    return _textField;
}

- (UILabel *)tipsLabel {
    if (!_tipsLabel) {
        _tipsLabel = [[UILabel alloc] initWithFrame:(CGRectZero)];
        _tipsLabel.font = [UIFont systemFontOfSize:14];
        _tipsLabel.textColor = [UIColor colorWithHexString:@"#8C8C8C"];
        
    }
    return _tipsLabel;
}


/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
