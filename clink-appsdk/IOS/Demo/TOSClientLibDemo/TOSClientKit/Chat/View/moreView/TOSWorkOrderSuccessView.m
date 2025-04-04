//
//  TOSWorkOrderSuccessView.m
//  TOSClientKit
//
//  Created by 李成 on 2024/10/9.
//  Copyright © 2024 YanBo. All rights reserved.
//

#import "TOSWorkOrderSuccessView.h"
#import "TIMConstants.h"
#import "TIMMasonry.h"

@interface TOSWorkOrderSuccessView ()

/// icon
@property (nonatomic, strong) UIImageView                * iconView;

@property (nonatomic, strong) UILabel                * titleView;

/// 分隔线
@property (nonatomic, strong) UIView                * separationLineView;

/// 返回按钮
@property (nonatomic, strong) UIButton                * backView;


@end

@implementation TOSWorkOrderSuccessView

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        
        self.backgroundColor = TOSHexColor(0xFFFFFF);
        
        [self addSubview:self.iconView];
        [self addSubview:self.titleView];
        [self addSubview:self.separationLineView];
        [self addSubview:self.backView];
        
        [self.iconView mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
            make.width.height.mas_TIMequalTo(SL_kScreenWidth-120.0);
            make.centerX.mas_TIMequalTo(self.mas_TIMcenterX);
            make.centerY.mas_TIMequalTo(self.mas_TIMcenterY).offset(-40.0);
        }];
        
        [self.titleView mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
            make.left.right.mas_TIMequalTo(0);
            make.height.mas_TIMequalTo(22);
            make.bottom.equalTo(self.iconView.mas_TIMbottom).offset(-46);
        }];
        
        [self.separationLineView mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
            make.left.right.mas_TIMequalTo(0);
            make.height.mas_TIMequalTo(1);
            make.bottom.mas_TIMequalTo(-(kBottomBarHeight+54.0));
        }];
        
        [self.backView mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
            make.left.mas_TIMequalTo(16.0);
            make.right.mas_TIMequalTo(-16.0);
            make.top.equalTo(self.separationLineView.mas_TIMbottom).offset(8.0);
            make.height.mas_TIMequalTo(38.0);
        }];
        
        
        
    }
    return self;
}


#pragma mark - action

- (void)backTouch {
    
    if (self.completionBlock) {
        self.completionBlock();
    }
    
}


#pragma mark - lazy

- (UIImageView *)iconView {
    if (!_iconView) {
        _iconView = [[UIImageView alloc] initWithFrame:(CGRectZero)];
        _iconView.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"leaveMessageSuccess"]];
        
    }
    return _iconView;
}

- (UILabel *)titleView {
    if (!_titleView) {
        _titleView = [[UILabel alloc] initWithFrame:(CGRectZero)];
        _titleView.text = @"留言成功";
        _titleView.font = [UIFont fontWithName:@"PingFangSC-Medium" size:14.0];
        _titleView.textAlignment = NSTextAlignmentCenter;
        
    }
    return _titleView;
}


- (UIView *)separationLineView {
    if (!_separationLineView) {
        _separationLineView = [[UIView alloc] initWithFrame:(CGRectZero)];
        _separationLineView.backgroundColor = TOSHexAColor(0x000000, 0.1f);
        
    }
    return _separationLineView;
}

- (UIButton *)backView {
    if (!_backView) {
        _backView = [[UIButton alloc] initWithFrame:(CGRectZero)];
        [_backView setTitle:@"返回" forState:(UIControlStateNormal)];
        [_backView setTitleColor:TOSHexColor(0xFFFFFF) forState:(UIControlStateNormal)];
        _backView.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Medium" size:16.0];
        _backView.backgroundColor = TOSHexAColor(0x4385FF, 1.0f);
        _backView.layer.cornerRadius = 6.0f;
        _backView.layer.masksToBounds = YES;
        [_backView addTarget:self action:@selector(backTouch) forControlEvents:(UIControlEventTouchUpInside)];
        
    }
    return _backView;
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
