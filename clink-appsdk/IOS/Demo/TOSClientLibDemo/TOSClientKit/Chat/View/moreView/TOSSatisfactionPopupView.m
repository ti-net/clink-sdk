//
//  TOSSatisfactionPopupView.m
//  TOSClientKit
//
//  Created by 言 on 2023/9/27.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import "TOSSatisfactionPopupView.h"
#import "YYKit.h"
#import "TIMConstants.h"
#import "NSString+Extension.h"
#import "TOSSatisfactionModel.h"
#import "TOSSatisfactionSolveView.h"
#import "TOSSatisfactionEvaluationDetailsView.h"
#import "TIMMessageFrame.h"

#import "TOSSatisfactionStatusModel.h"
#import "ICMessageConst.h"
#import "UIResponder+GXRouter.h"
#import "TIMMessageModel.h"
#import "ICFileTool.h"
#import "TIMLabel.h"
#import "kitUtils.h"
#import "ICFaceManager.h"

@interface TOSSatisfactionPopupView () <UIScrollViewDelegate>

@property (nonatomic, strong) UIView *bottomView;

@property (nonatomic, strong) UIView *popupView;

@property (nonatomic, strong) UILabel *title;
@property (nonatomic, strong) UIButton *close;
@property (nonatomic, strong) UIButton *notEvaluatedBtn;

@property (nonatomic, strong) UIScrollView *scrollView;
@property (nonatomic, strong) UIView *contentView;
@property (nonatomic, strong) UIView *scrollContentView;

@property (nonatomic, strong) UILabel *welcome;
@property (nonatomic, strong) TOSSatisfactionSolveView *satisSolve;

@property (nonatomic, strong) NSMutableArray <TOSSatisfactionEvaluationDetailsView *>*options;

@property (nonatomic, strong) UIView *remarkTopLine;
@property (nonatomic, strong) YYTextView *remark;

@property (nonatomic, strong) UIView *commitBtnView;
@property (nonatomic, strong) UIButton *commitBtn;

@property (nonatomic, strong) TOSSatisfactionModel *contentModel;

@property (nonatomic, strong) UIView *resultView;
@property (nonatomic, strong) UIImageView *resultImage;
@property (nonatomic, strong) UILabel *errorLabel;

@end

@implementation TOSSatisfactionPopupView

- (void)setupSubviews {
    [super setupSubviews];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillHide:) name:UIKeyboardWillHideNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardFrameWillChange:) name:UIKeyboardWillChangeFrameNotification object:nil];
    
    self.backgroundColor = [UIColor clearColor];
    
    self.contentModel = [TOSSatisfactionModel yy_modelWithJSON:[[OnlineDataSave shareOnlineDataSave] getAppSetting]];
    
    [self addSubview:self.bottomView];
    [self addSubview:self.popupView];
    
    [self.popupView addSubview:self.title];
    [self.popupView addSubview:self.close];
    [self.popupView addSubview:self.notEvaluatedBtn];
    
    [self.popupView addSubview:self.scrollView];
    
    [self.scrollView addSubview:self.scrollContentView];
    [self.scrollView addSubview:self.contentView];
    [self.popupView addSubview:self.resultView];
    
    [self.resultView addSubview:self.resultImage];
    [self.resultView addSubview:self.errorLabel];
    
    [self.contentView addSubview:self.welcome];
    [self.contentView addSubview:self.satisSolve];
    
    self.options = [NSMutableArray array];
    @weakify(self)
    [self.contentModel.investigation.content.options enumerateObjectsUsingBlock:^(TOSSatisfactionOptionsModel * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        @strongify(self)
        TOSSatisfactionEvaluationDetailsView *detailsView = [[TOSSatisfactionEvaluationDetailsView alloc] initWithFrame:(CGRectZero)];
        detailsView.optionsModel = obj;
        detailsView.star = self.contentModel.investigation.star;
        detailsView.starModel = self.contentModel.investigation.star.firstObject;
        detailsView.popupView = self;
        [self.contentView addSubview:detailsView];
        [self.options addObject:detailsView];
    }];
    
    [self.contentView addSubview:self.remarkTopLine];
    [self.contentView addSubview:self.remark];
    
    [self.popupView addSubview:self.commitBtnView];
    [self.commitBtnView addSubview:self.commitBtn];
    
    self.bottomView.frame = self.bounds;
    self.bottomView.alpha = 0.f;
    self.popupView.frame = CGRectMake(0, kWindowHeight, self.width_sd, self.height_sd-112.f);
    
    self.title.frame = CGRectMake(0, 20.f, self.width_sd, 20.f);
    self.title.centerX_sd = self.popupView.centerX_sd;
    
    self.close.frame = CGRectMake(0, 20.f, 20.f, 20.f);
    self.close.right_sd = self.popupView.right_sd - 24.f;
    
    self.notEvaluatedBtn.frame = CGRectMake(0, 20.f, 80.f, 24.f);
    self.notEvaluatedBtn.centerY_sd = self.title.centerY_sd;
    
    self.scrollView.frame = CGRectMake(6.f, 56.f, self.popupView.width_sd-12.f, self.popupView.height_sd - 56.f - 54.f - kBottomBarHeight);
    self.scrollContentView.frame = self.scrollView.bounds;
    self.contentView.frame = CGRectMake(0, 0, self.scrollView.width_sd, self.scrollView.height_sd - 12.f);
    self.resultView.frame = self.scrollView.frame;
    self.resultImage.frame = CGRectMake(53.f, 60.f, self.resultView.width_sd - 106.f, self.resultView.width_sd - 106.f);
    self.errorLabel.frame = CGRectMake(53.f, self.resultImage.height_sd-40, self.width_sd - 106.f, 40.f);
    
    self.welcome.frame = CGRectMake(16.f, 12.f, self.contentView.width_sd - 32.f, 0);
    dispatch_async(dispatch_get_global_queue(0, 0), ^{
        @strongify(self)
        CGFloat height = [self.welcome.text tim_sizeWithMaxWidth:self.welcome.width_sd andFont:self.welcome.font].height;
        dispatch_async(dispatch_get_main_queue(), ^{
            @strongify(self)
            self.welcome.height_sd = height;
            [self defineLayout];
        });
    });
}

- (void)defineLayout {
    [super defineLayout];
    
    if (self.contentModel.investigation.chatSatisSolveState.enabled.integerValue == 1) {
        self.satisSolve.frame = CGRectMake(0, self.welcome.bottom_sd, self.contentView.width_sd, 106.5f);
    } else {
        self.satisSolve.frame = CGRectMake(0, self.welcome.bottom_sd, 0, 0);
    }
    
    @weakify(self)
    __block CGFloat y = self.satisSolve.bottom_sd;
    [self.options enumerateObjectsUsingBlock:^(TOSSatisfactionEvaluationDetailsView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        @strongify(self)
        
        CGFloat maxHeight = 0;
        if (obj.starModel) {
            NSInteger count = obj.optionsModel.star[obj.starModel.star.integerValue - 1].tabBar.count / 2 + obj.optionsModel.star[obj.starModel.star.integerValue - 1].tabBar.count % 2;
            maxHeight = count * (32.f + 12.f) + 12.f;
        }
        obj.frame = CGRectMake(0, y, self.contentView.width_sd, 86.f + maxHeight);
        y = obj.bottom_sd;
    }];
    
    if (self.contentModel.investigation.remark.integerValue == 1) {
        self.remarkTopLine.frame = CGRectMake(16.f, self.options.lastObject.bottom_sd, self.contentView.width_sd - 32.f, 0.5f);
        self.remark.frame = CGRectMake(16.f, 16.f + self.remarkTopLine.bottom_sd, self.contentView.width_sd - 32.f, 100.f);
    } else {
        self.remarkTopLine.frame = CGRectMake(16.f, self.options.lastObject.bottom_sd, 0, 0);
        self.remark.frame = CGRectMake(16.f, 16.f + self.remarkTopLine.bottom_sd, 0, 0);
    }
    
    
    self.contentView.height_sd = self.remark.bottom_sd+16.f;
    self.scrollView.contentSize = CGSizeMake(self.scrollView.width_sd, self.contentView.bottom_sd+16.f);
    
    self.commitBtnView.frame = CGRectMake(0, self.popupView.height_sd - 54.f - kBottomBarHeight, self.popupView.width_sd, 54.f + kBottomBarHeight);
    self.commitBtn.frame = CGRectMake(16.f, 8.f, self.commitBtnView.width_sd - 32.f, 38.f);
}

- (void)showPopupView {
    self.hidden = NO;
    self.resultView.hidden = YES;
    self.scrollView.hidden = NO;
    self.commitBtnView.hidden = NO;
    self.bottomView.alpha = 0.f;
    self.popupView.frame = CGRectMake(0, kWindowHeight, self.width_sd, self.height_sd-112.f);
    if (self.showNotEvaluated) {
        self.notEvaluatedBtn.hidden = NO;
    } else {
        self.notEvaluatedBtn.hidden = YES;
    }
    @weakify(self)
    [UIView animateWithDuration:.3f animations:^{
        @strongify(self)
        self.bottomView.alpha = 1.f;
        self.popupView.frame = CGRectMake(0, 112.f, self.width_sd, self.height_sd-112.f);
    }];
}

- (void)didClickCloseBtnAction:(UIButton *)sender {
    @weakify(self)
    [UIView animateWithDuration:.3f animations:^{
        @strongify(self)
        self.bottomView.alpha = 0.f;
        self.popupView.frame = CGRectMake(0, kWindowHeight, self.width_sd, self.height_sd-112.f);
    } completion:^(BOOL finished) {
        @strongify(self)
        self.hidden = YES;
        self.resultView.hidden = YES;
        self.commitBtnView.hidden = NO;
        
        if (self.popViewController) {
            [self routerEventWithName:GXRobotPopViewController
                             userInfo:@{}];
        }
    }];
}

- (void)didClickCommitBtnAction:(UIButton *)sender {
    // @[@{@"name": @"", @"star": @"", @"label": @[@"", @"",]}]
    //隐藏键盘
    [self.remark resignFirstResponder];
    __block NSMutableArray *dataArr = [NSMutableArray array];
    [self.options enumerateObjectsUsingBlock:^(TOSSatisfactionEvaluationDetailsView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        [dataArr addObject:@{@"name": obj.optionsModel.name?:@"", @"star": obj.starModel.star?:@(0), @"label": obj.tabBarSelect?:@[]}];
    }];
    
    @WeakObj(self);
    [[OnlineRequestManager sharedCustomerManager] submitInvestigationUniqueId:self.uniqueId?:@""
                                                                 mainUniqueId:self.mainUniqueId?:@""
                                                                      options:dataArr
                                                                        solve:self.satisSolve.resolvedSelected?@"1":@"2"
                                                                       remark:self.remark.text?:@""
                                                                      Success:^{
        @StrongObj(self)
        self.resultView.hidden = NO;
        self.scrollView.hidden = YES;
        self.commitBtnView.hidden = YES;
        self.resultImage.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"TOSSatisfaction_State_Successful"]];
        self.errorLabel.text = @"";
        if (self.popViewController) {
            [self routerEventWithName:GXRobotPopViewController
                             userInfo:@{}];
        } else {
            TOSSatisfactionStatusModel *extraModel = [TOSSatisfactionStatusModel yy_modelWithJSON:self.tempModelFrame.model.message.extra];
            
            extraModel.alreadyInvestigation = @"1";
            
            self.tempModelFrame.model.message.extra = [extraModel yy_modelToJSONString];
            
            self.tempModelFrame.model = self.tempModelFrame.model;
            
            [self routerEventWithName:GXRobotCombinationHotIssueCellRefreshEventName
                             userInfo:@{MessageKey: self.tempModelFrame}];
        }
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        NSLog(@"=====errorDes=%@",errorDes);
        @StrongObj(self)
        self.resultView.hidden = NO;
        self.scrollView.hidden = YES;
        self.commitBtnView.hidden = YES;
        self.resultImage.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"TOSSatisfaction_State_fail_default"]];
        self.errorLabel.text = errorDes;
    }];
}

- (void)keyboardWillHide:(NSNotification *)notification {
    self.popupView.frame = CGRectMake(0, 112.f, self.width_sd, self.height_sd-112.f);
}

- (void)keyboardFrameWillChange:(NSNotification *)notification {
    
    CGRect keyboardFrame = [notification.userInfo[UIKeyboardFrameEndUserInfoKey] CGRectValue];
    CGFloat keyboardFrameHeight = keyboardFrame.size.height;
    self.popupView.frame = CGRectMake(0, 112.f - keyboardFrameHeight, self.width_sd, self.height_sd-112.f);
}

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

#pragma mark - 懒加载
- (UIView *)bottomView {
    if (!_bottomView) {
        _bottomView = [[UIView alloc] initWithFrame:(CGRectZero)];
        _bottomView.backgroundColor = TOSHexAColor(0x000000, 0.5f);
        _bottomView.userInteractionEnabled = YES;
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(didClickCloseBtnAction:)];
        [_bottomView addGestureRecognizer:tap];
    }
    return _bottomView;
}

- (UIView *)popupView {
    if (!_popupView) {
        _popupView = [[UIView alloc] initWithFrame:(CGRectZero)];
        _popupView.backgroundColor = TOSHexAColor(0xF5F5F5, 1.f);;
        _popupView.layer.cornerRadius = 16.0;
        _popupView.layer.masksToBounds = YES;
        _popupView.clipsToBounds = NO;
    }
    return _popupView;
}

- (UILabel *)title {
    if (!_title) {
        _title = [[UILabel alloc] initWithFrame:(CGRectZero)];
        _title.textColor = TOSHexColor(0x262626);
        _title.textAlignment = NSTextAlignmentCenter;
        _title.font = [UIFont fontWithName:@"PingFangSC-Regular" size:18.f];
        _title.numberOfLines = 0;
        _title.text = @"满意度评价";
    }
    return _title;
}

- (UIButton *)close {
    if (!_close) {
        _close = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_close setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"TOSSatisfaction_Close"]] forState:(UIControlStateNormal)];
        [_close addTarget:self action:@selector(didClickCloseBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _close;
}

- (UIButton *)notEvaluatedBtn {
    if (!_notEvaluatedBtn) {
        _notEvaluatedBtn = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_notEvaluatedBtn setTitle:@"暂不评价" forState:(UIControlStateNormal)];
        [_notEvaluatedBtn addTarget:self action:@selector(didClickCloseBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
        _notEvaluatedBtn.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        [_notEvaluatedBtn setTitleColor:TOSHexColor(0x262626) forState:(UIControlStateNormal)];
    }
    return _notEvaluatedBtn;
}

- (UILabel *)errorLabel {
    if (!_errorLabel) {
        _errorLabel = [[UILabel alloc] initWithFrame:(CGRectZero)];
        _errorLabel.textColor = TOSHexColor(0x262626);
        _errorLabel.textAlignment = NSTextAlignmentCenter;
        _errorLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        _errorLabel.numberOfLines = 0;
    }
    return _errorLabel;
}
- (UIScrollView *)scrollView {
    if (!_scrollView) {
        _scrollView = [[UIScrollView alloc] initWithFrame:(CGRectZero)];
        _scrollView.backgroundColor = [UIColor clearColor];
        [_scrollView setShowsHorizontalScrollIndicator:NO];
        [_scrollView setShowsVerticalScrollIndicator:NO];
        _scrollView.keyboardDismissMode = UIScrollViewKeyboardDismissModeOnDrag;
        _scrollView.delegate = self;
        _scrollView.alwaysBounceVertical = YES;
    }
    return _scrollView;
}

- (UIView *)scrollContentView {
    if (!_scrollContentView) {
        _scrollContentView = [[UIView alloc] initWithFrame:(CGRectZero)];
        _scrollContentView.backgroundColor = [UIColor clearColor];
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(didClickScrollViewAction:)];
        [_scrollContentView addGestureRecognizer:tap];
    }
    return _scrollContentView;
}

- (void)didClickScrollViewAction:(UITapGestureRecognizer *)sender {
    [self endEditing:YES];
}

- (UIView *)resultView {
    if (!_resultView) {
        _resultView = [[UIView alloc] initWithFrame:CGRectZero];
        _resultView.backgroundColor = [UIColor whiteColor];
        _resultView.layer.cornerRadius = 12.0;
        _resultView.layer.masksToBounds = YES;
        _resultView.hidden = YES;
    }
    return _resultView;
}

- (UIImageView *)resultImage {
    if (!_resultImage) {
        _resultImage = [[UIImageView alloc] initWithFrame:CGRectZero];
        _resultImage.image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"TOSSatisfaction_State_fail"]];
        _resultImage.contentMode = UIViewContentModeScaleAspectFit;
    }
    return _resultImage;
}

- (UIView *)contentView {
    if (!_contentView) {
        _contentView = [[UIView alloc] initWithFrame:CGRectZero];
        _contentView.backgroundColor = [UIColor whiteColor];
        _contentView.layer.cornerRadius = 12.0;
        _contentView.layer.masksToBounds = YES;
    }
    return _contentView;
}

- (UILabel *)welcome {
    if (!_welcome) {
        _welcome = [[UILabel alloc] initWithFrame:(CGRectZero)];
        _welcome.textColor = TOSHexColor(0x595959);
        _welcome.textAlignment = NSTextAlignmentCenter;
        _welcome.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        _welcome.numberOfLines = 0;
        _welcome.text = [NSString stringWithFormat:@"%@",self.contentModel.investigation.welcome];
    }
    return _welcome;
}

- (TOSSatisfactionSolveView *)satisSolve {
    if (!_satisSolve) {
        _satisSolve = [[TOSSatisfactionSolveView alloc] initWithFrame:(CGRectZero)];
        _satisSolve.contentModel = self.contentModel;
    }
    return _satisSolve;
}

- (NSMutableArray <TOSSatisfactionEvaluationDetailsView *>*)options {
    if (!_options) {
        _options = [NSMutableArray array];
    }
    return _options;
}

- (UIView *)remarkTopLine {
    if (!_remarkTopLine) {
        _remarkTopLine = [[UIView alloc] initWithFrame:CGRectZero];
        _remarkTopLine.backgroundColor = TOSHexAColor(0x000000, 0.1f);
    }
    return _remarkTopLine;
}

- (YYTextView *)remark {
    if (!_remark) {
        _remark = [[YYTextView alloc] initWithFrame:(CGRectZero)];
        _remark.placeholderTextColor = TOSHexColor(0xBFBFBF);
        _remark.textColor = TOSHexColor(0x262626);
        _remark.backgroundColor = TOSHexColor(0xF5F5F5);
        _remark.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        _remark.placeholderFont = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        _remark.placeholderText = [NSString stringWithFormat:@"%@",self.contentModel.investigation.remarkContent];
        _remark.layer.cornerRadius = 6.0;
        _remark.layer.masksToBounds = YES;
        _remark.textContainerInset = UIEdgeInsetsMake(12.f, 12.f, 12.f, 12.f);
    }
    return _remark;
}

- (UIView *)commitBtnView {
    if (!_commitBtnView) {
        _commitBtnView = [[UIView alloc] initWithFrame:(CGRectZero)];
        _commitBtnView.backgroundColor = [UIColor whiteColor];
    }
    return _commitBtnView;
}

- (UIButton *)commitBtn {
    if (!_commitBtn) {
        _commitBtn = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_commitBtn setTitle:@"提交" forState:(UIControlStateNormal)];
        _commitBtn.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:16.f];
        [_commitBtn setTitleColor:[TOSKitCustomInfo shareCustomInfo].satisfaction_evaluate_submit_titleColor forState:(UIControlStateNormal)];
        [_commitBtn addTarget:self action:@selector(didClickCommitBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
        _commitBtn.backgroundColor = [TOSKitCustomInfo shareCustomInfo].satisfaction_evaluate_submit;
        _commitBtn.layer.cornerRadius = 6.f;
        _commitBtn.layer.masksToBounds = YES;
    }
    return _commitBtn;
}

@end
