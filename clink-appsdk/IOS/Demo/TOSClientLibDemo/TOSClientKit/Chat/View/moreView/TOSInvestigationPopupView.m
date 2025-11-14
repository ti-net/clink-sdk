//
//  TOSInvestigationPopupView.m
//  TOSClientKit
//
//  Created by 李成 on 2023/8/9.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import "TOSInvestigationPopupView.h"
#import "UIColor+TIMYYAdd.h"
#import "NSArray+TIMYYAdd.h"
#import "TIMConstants.h"
#import "TIMMasonry.h"
#import <TOSClientLib/OnlineDataSave.h>
#import "TOSSatisfactionModel.h"
#import "TOSInvestigationStarView.h"
#import "TOSInvestigationStarTagView.h"
#import <TOSClientLib/OnlineRequestManager.h>
#import "NSObject+TIMShowError.h"

static CGFloat bgMinHeight = 198.0f;

@interface TOSInvestigationPopupView ()<TOSInvestigationStarViewDelegate, TOSInvestigationStarTagViewDelegate>

/// 展示内容父视图
@property (nonatomic, strong) UIView                * bgView;

/// 标题
@property (nonatomic, strong) UILabel                * titleLabel;

/// starView
@property (nonatomic, strong) TOSInvestigationStarView                * startView;

/// star下面的标签
@property (nonatomic, strong) TOSInvestigationStarTagView                * tagView;

/// 提交按钮
@property (nonatomic, strong) UIButton                * commitBtn;

/// 关闭按钮
@property (nonatomic, strong) UIButton                * closeBtn;

/// 父视图的高度
@property (nonatomic, assign) CGFloat                bgHeight;

/// 标题的高度
@property (nonatomic, assign) CGFloat                titleHeight;

/// 选中的star
@property (nonatomic, strong) TOSSatisfactionStarModel                * selectStar;

/// 选中的tag
@property (nonatomic, strong) NSMutableArray                * selectTagArray;


@end



@implementation TOSInvestigationPopupView

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        
        self.backgroundColor = [UIColor colorWithRed:0 green:0 blue:0 alpha:0.5];
        
        [self addSubview:self.bgView];
        [self.bgView addSubview:self.titleLabel];
        [self.bgView addSubview:self.startView];
        [self.bgView addSubview:self.tagView];
        [self.bgView addSubview:self.commitBtn];
        [self addSubview:self.closeBtn];
        
//        [self.bgView mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
//            make.left.mas_TIMequalTo(24);
//            make.right.mas_TIMequalTo(-24);
//            make.top.mas_TIMequalTo(120);
//            make.height.mas_TIMequalTo(358);
//        }];
//
//        [self.titleLabel mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
//            make.left.top.mas_TIMequalTo(24);
//            make.right.mas_TIMequalTo(-24);
//            make.height.mas_TIMequalTo(24);
//        }];
//
//        [self.startView mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
//            make.left.equalTo(self.titleLabel.mas_TIMleft);
//            make.right.equalTo(self.titleLabel.mas_TIMright);
//            make.height.mas_TIMequalTo(110);
//            make.top.equalTo(self.titleLabel.mas_TIMbottom);
//        }];
//
//        [self.closeBtn mas_TIMmakeTIMConstraints:^(TIMMASConstraintMaker *make) {
//            make.width.height.mas_TIMequalTo(32);
//            make.top.equalTo(self.bgView.mas_TIMbottom).offset(24);
//            make.centerX.equalTo(self.mas_TIMcenterX);
//        }];
        
        
    }
    return self;
}


/// 展示弹窗
- (void)showPopupView {
    
    [[UIApplication sharedApplication].keyWindow addSubview:self];
    
    [UIView animateWithDuration:0.3 animations:^{
        self.alpha = 1.f;
    }];
    
    self.bgHeight = bgMinHeight;
    
    TOSSatisfactionModel *contentModel = [TOSSatisfactionModel yy_modelWithJSON:[[OnlineDataSave shareOnlineDataSave] getAppSetting]];
    /// 标题内容
    self.titleLabel.text = contentModel.investigation.welcome;
    
    self.startView.width = self.sl_width - 72;
    /// 等级赋值
    self.startView.starArray = [contentModel.investigation.star.reverseObjectEnumerator allObjects];
    TOSSatisfactionStarInfoModel *starInfoModel = [contentModel.investigation.content.options.firstObject.star objectOrNilAtIndex:self.startView.starArray.count-1];
    self.tagView.width = self.sl_width - 72;
    /// 对应的等级标签组
    self.tagView.tagArray = starInfoModel.tabBar;
    NSInteger numberOfElements = starInfoModel.tabBar.count;
    NSInteger elementsPerRow = 2;
    NSInteger numberOfRows = (numberOfElements + elementsPerRow - 1) / elementsPerRow;

    self.bgHeight += numberOfRows*(30+12);
    /// 标题的高
    CGFloat titleH = CGRectGetHeight([self.titleLabel.text boundingRectWithSize:(CGSizeMake(CGRectGetWidth(self.frame) - 36 - 36, CGFLOAT_MAX)) options:(NSStringDrawingUsesLineFragmentOrigin | NSStringDrawingUsesFontLeading) attributes:@{NSFontAttributeName: self.titleLabel.font} context:nil]);
    self.titleHeight = MAX(24, titleH);
    self.bgHeight += self.titleHeight;
    
    self.bgView.frame = CGRectMake(18, CGRectGetHeight(self.frame)/2-self.bgHeight/2, CGRectGetWidth(self.frame)-36, self.bgHeight);
    
    self.titleLabel.frame = CGRectMake(18, 24, CGRectGetWidth(self.bgView.frame)-36, self.titleHeight);
    self.startView.frame = CGRectMake(CGRectGetMinX(self.titleLabel.frame), CGRectGetMaxY(self.titleLabel.frame), self.titleLabel.sl_width, 110.0);
    self.tagView.frame = CGRectMake(CGRectGetMinX(self.startView.frame), CGRectGetMaxY(self.startView.frame), self.startView.sl_width, numberOfRows*(30+12));
    self.commitBtn.frame = CGRectMake(CGRectGetMinX(self.titleLabel.frame), CGRectGetMaxY(self.tagView.frame), self.titleLabel.sl_width, 38);
    self.closeBtn.frame = CGRectMake(CGRectGetWidth(self.frame)/2-16, CGRectGetMaxY(self.bgView.frame) + 24, 32, 32);
    
    self.selectStar = self.startView.starArray.lastObject;
    self.selectTagArray = [NSMutableArray array];
    
    
}

#pragma mark - TOSInvestigationStarViewDelegate

- (void)TOSInvestigationStarViewDidSelectedIndex:(NSInteger)index {
    
    TOSSatisfactionModel *contentModel = [TOSSatisfactionModel yy_modelWithJSON:[[OnlineDataSave shareOnlineDataSave] getAppSetting]];
    TOSSatisfactionStarInfoModel *starInfoModel = [contentModel.investigation.content.options.firstObject.star objectOrNilAtIndex:index];
    self.tagView.tagArray = starInfoModel.tabBar;
    NSInteger numberOfElements = starInfoModel.tabBar.count;
    NSInteger elementsPerRow = 2;
    NSInteger numberOfRows = (numberOfElements + elementsPerRow - 1) / elementsPerRow;
    
    self.tagView.sl_height = numberOfRows*(30+12);
    self.commitBtn.sl_top = CGRectGetMaxY(self.tagView.frame);
    self.bgHeight = bgMinHeight + numberOfRows*(30+12) + self.titleHeight;
    self.bgView.sl_centerY = CGRectGetHeight(self.frame)/2;
    self.bgView.sl_height = self.bgHeight;
    
    self.closeBtn.sl_top = CGRectGetMaxY(self.bgView.frame) + 24;
    
    self.selectStar = self.startView.starArray[index];
    [self.selectTagArray removeAllObjects];
    
}

#pragma mark - TOSInvestigationStarTagViewDelegate

- (void)TOSInvestigationStarTagViewSelected:(NSString *)item {
    
    [self.selectTagArray addObject:item];
    
}

- (void)TOSInvestigationStarTagViewUnSelected:(NSString *)item {
    
    [self.selectTagArray removeObject:item];
    
}


#pragma mark - touch

/// 提交按钮
- (void)commitTouch {
    
    NSMutableDictionary * dic = [NSMutableDictionary dictionary];
    [dic setObject:self.selectStar.desc?:@"" forKey:@"name"];
    [dic setObject:self.selectStar.star forKey:@"star"];
    [dic setObject:self.selectTagArray?:@[] forKey:@"label"];
    NSLog(@"评价上传的数据：%@", dic);
    NSLog(@"uniqueId : %@", self.uniqueId);
    @WeakObj(self);
    [[OnlineRequestManager sharedCustomerManager] submitInvestigationUniqueId:self.uniqueId?:@""
                                                                 mainUniqueId:self.mainUniqueId?:@""
                                                                      options:@[dic]
                                                                        solve:@""
                                                                       remark:@""
                                                                      Success:^{
        @StrongObj(self)
        // GXRobotCombinationHotIssueCellRefreshEventName
        NSLog(@"评价成功");
        if (self.delegate && [self.delegate respondsToSelector:@selector(TOSInvestigationPopupViewCommitEvaluateSuccess)]) {
            [self.delegate TOSInvestigationPopupViewCommitEvaluateSuccess];
            [self removeFromSuperview];
        }
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        @StrongObj(self)
        [self tim_showMBErrorView:errorDes];
        NSLog(@"评价失败：%@", errorDes);
    }];
    
}

/// 关闭按钮
- (void)closeTouch {
    
    
    if (self.delegate && [self.delegate respondsToSelector:@selector(TOSInvestigationPopupViewCloseView)]) {
        [self.delegate TOSInvestigationPopupViewCloseView];
    }
    [self removeFromSuperview];
    
}

#pragma mark - lazy

- (UIView *)bgView {
    if (!_bgView) {
        _bgView = [[UIView alloc] initWithFrame:(CGRectZero)];
        _bgView.backgroundColor = [UIColor colorWithHexString:@"#FFFFFF"];
        _bgView.layer.cornerRadius = 12.0;
        _bgView.layer.masksToBounds = YES;
        
    }
    return _bgView;
}

- (UILabel *)titleLabel {
    if (!_titleLabel) {
        _titleLabel = [[UILabel alloc] initWithFrame:(CGRectZero)];
        _titleLabel.textAlignment = NSTextAlignmentCenter;
        _titleLabel.font = [UIFont fontWithName:@"PingFangSC-Medium" size:16];
        _titleLabel.numberOfLines = 0;
        
    }
    return _titleLabel;
}

- (TOSInvestigationStarView *)startView {
    if (!_startView) {
        _startView = [[TOSInvestigationStarView alloc] initWithFrame:(CGRectZero)];
        _startView.delegate = self;
        
    }
    return _startView;
}

- (TOSInvestigationStarTagView *)tagView {
    if (!_tagView) {
        _tagView = [[TOSInvestigationStarTagView alloc] initWithFrame:(CGRectZero)];
        _tagView.delegate = self;
        
    }
    return _tagView;
}

- (UIButton *)commitBtn {
    if (!_commitBtn) {
        _commitBtn = [[UIButton alloc] initWithFrame:(CGRectZero)];
        [_commitBtn setTitle:@"提交" forState:(UIControlStateNormal)];
        [_commitBtn setTitleColor:[UIColor whiteColor] forState:(UIControlStateNormal)];
        _commitBtn.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Medium" size:16];
        [_commitBtn setBackgroundColor:[UIColor colorWithHexString:@"#4385FF"]];
        _commitBtn.layer.cornerRadius = 6.0f;
        _commitBtn.layer.masksToBounds = YES;
        [_commitBtn addTarget:self action:@selector(commitTouch) forControlEvents:(UIControlEventTouchUpInside)];
        
    }
    return _commitBtn;
}


- (UIButton *)closeBtn {
    if (!_closeBtn) {
        _closeBtn = [[UIButton alloc] initWithFrame:(CGRectZero)];
        [_closeBtn setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"Investigation_Close"]] forState:(UIControlStateNormal)];
        [_closeBtn addTarget:self action:@selector(closeTouch) forControlEvents:(UIControlEventTouchUpInside)];
        
    }
    return _closeBtn;
}


/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
