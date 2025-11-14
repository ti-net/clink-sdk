//
//  TOSSatisfactionEvaluationDetailsView.m
//  TOSClientKit
//
//  Created by 言 on 2023/9/28.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import "TOSSatisfactionEvaluationDetailsView.h"
#import "YYKit.h"
#import "TIMConstants.h"
#import "TOSSatisfactionModel.h"
#import "TOSSatisfactionPopupView.h"

@interface TOSSatisfactionEvaluationDetailsView ()

@property (nonatomic, strong) UILabel *title;

@property (nonatomic, strong) NSMutableArray <UIButton *>*stars;

@property (nonatomic, strong) UILabel *evaluationDescribe;

@property (strong, nonatomic) UIView *tagBottomView;

@end

@implementation TOSSatisfactionEvaluationDetailsView
- (void)setupSubviews {
    [super setupSubviews];
    
    self.backgroundColor = [UIColor clearColor];
    
    self.stars = [NSMutableArray array];
    
    [self addSubview:self.title];
    
    for (NSInteger i = 1; i <= 5; i++) {
        UIButton *star = [UIButton buttonWithType:(UIButtonTypeCustom)];
        star.tag = 10000+i;
        [star setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"TOSSatisfaction_Star"]] forState:(UIControlStateNormal)];
        [star setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"TOSSatisfaction_Star_gray"]] forState:(UIControlStateSelected)];
        [star addTarget:self action:@selector(didClickStarBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
        [self addSubview:star];
        [self.stars addObject:star];
    }
    
    [self addSubview:self.evaluationDescribe];
    [self addSubview:self.tagBottomView];
    
    self.tabBarSelect = [NSMutableArray array];
}

- (void)defineLayout {
    [super defineLayout];
    
    self.title.text = [NSString stringWithFormat:@"%@",self.optionsModel.name];
    self.evaluationDescribe.text = [NSString stringWithFormat:@"%@",self.starModel.desc];
    
    self.title.frame = CGRectMake(16.f, 16.f, self.width_sd - 32.f, 22.f);
    
    @weakify(self)
    [self.stars enumerateObjectsUsingBlock:^(UIButton * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        @strongify(self)
        obj.frame = CGRectMake(idx * (36.f + 16.f) + 16.f, 12.f + self.title.bottom_sd, 36.f, 36.f);
        obj.hidden = NO;
        if (idx > self.star.count - 1) {
            obj.hidden = YES;
        }
    }];
    
    self.evaluationDescribe.frame = CGRectMake(16.f, 19.f + self.title.bottom_sd, self.width_sd - 16.f - 260.f - 5.f, 22.f);
    self.evaluationDescribe.right_sd = self.right_sd - 16.f;
    
    [self.tagBottomView removeAllSubviews];
    
    __block CGFloat maxHeight = 0.f;
    if (self.starModel) {
        [self.optionsModel.star[self.starModel.star.integerValue - 1].tabBar enumerateObjectsUsingBlock:^(NSString * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            @strongify(self)
            
            UIButton *btn = [UIButton buttonWithType:(UIButtonTypeCustom)];
            [btn setTitle:[NSString stringWithFormat:@"%@",obj] forState:(UIControlStateNormal)];
            [btn setTitleColor:TOSHexColor(0x262626) forState:(UIControlStateNormal)];
            [btn setTitleColor:TOSHexColor(0x4385FF) forState:(UIControlStateSelected)];
            btn.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
            [btn addTarget:self action:@selector(didClickTagBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
            btn.backgroundColor = TOSHexColor(0xFFFFFF);
            btn.layer.cornerRadius = 6.f;
            btn.layer.masksToBounds = YES;
            btn.layer.borderWidth = 1.f;
            btn.layer.borderColor = TOSHexColor(0x262626).CGColor;
            NSInteger y = idx / 2;
            NSInteger x = idx % 2;
            CGFloat width = (self.width_sd - 32.f - 12.f) / 2;
            btn.frame = CGRectMake(x * (width + 12.f), (32.f + 12.f) * y + 12.f, width, 32.f);
            [self.tagBottomView addSubview:btn];
            maxHeight = btn.bottom_sd;
        }];
    }
    
    self.tagBottomView.frame = CGRectMake(16.f, self.stars.firstObject.bottom_sd, self.width_sd - 32.f, maxHeight);
}

- (void)didClickTagBtnAction:(UIButton *)sender {
    sender.selected = !sender.selected;
    if (sender.isSelected) {
        sender.layer.borderColor = TOSHexColor(0x4385FF).CGColor;
        [self.tabBarSelect addObject:sender.titleLabel.text];
    } else {
        sender.layer.borderColor = TOSHexColor(0x262626).CGColor;
        [self.tabBarSelect removeObject:sender.titleLabel.text];
    }
}

- (void)didClickStarBtnAction:(UIButton *)sender {
    @weakify(self)
    [self.stars enumerateObjectsUsingBlock:^(UIButton * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        if (sender.tag < obj.tag) {
            obj.selected = YES;
        } else {
            obj.selected = NO;
        }
    }];
    [self.star enumerateObjectsUsingBlock:^(TOSSatisfactionStarModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        @strongify(self)
        if (obj.star.integerValue == sender.tag - 10000) {
            self.evaluationDescribe.text = [NSString stringWithFormat:@"%@",obj.desc];
            self.starModel = obj;
            obj.isSelect = YES;
        } else {
            obj.isSelect = NO;
        }
    }];
    
    self.tabBarSelect = [NSMutableArray array];
    
    [self.popupView defineLayout];
    [self defineLayout];
}

#pragma mark - 懒加载
- (UILabel *)title {
    if (!_title) {
        _title = [[UILabel alloc] initWithFrame:(CGRectZero)];
        _title.textColor = TOSHexColor(0x262626);
        _title.textAlignment = NSTextAlignmentLeft;
        _title.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        _title.numberOfLines = 1;
    }
    return _title;
}

- (UILabel *)evaluationDescribe {
    if (!_evaluationDescribe) {
        _evaluationDescribe = [[UILabel alloc] initWithFrame:(CGRectZero)];
        _evaluationDescribe.textColor = TOSHexColor(0x8C8C8C);
        _evaluationDescribe.textAlignment = NSTextAlignmentRight;
        _evaluationDescribe.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        _evaluationDescribe.numberOfLines = 1;
    }
    return _evaluationDescribe;
}

- (UIView *)tagBottomView {
    if (!_tagBottomView) {
        _tagBottomView = [[UIView alloc] init];
    }
    return _tagBottomView;
}

@end
