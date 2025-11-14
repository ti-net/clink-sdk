//
//  OSRobotCombinationCell.m
//  TIMClientKit
//
//  Created by apple on 2021/8/31.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import "OSRobotCombinationCell.h"
#import "YYKit.h"
#import "WHToast.h"
#import "kitUtils.h"
#import "TIMMessageModel.h"
#import "TIMConstants.h"
#import "TOSKitCustomInfo.h"
#import "NSArray+TRTool.h"
#import <TOSClientLib/CombinationMessage.h>
#import "OSRobotCombinationTextSubview.h"
#import "OCRobotCombinationImageVideoSubview.h"
#import "OSRobotCombinationFileSubview.h"
#import "OSRobotCombinationVoiceSubview.h"
#import "OSRobotCombinationHotIssueSubview.h"

@interface OSRobotCombinationCell() <YYTextViewDelegate> ///<UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, strong) TIMMessageFrame *tempModelFrame;
@property (nonatomic, strong) CombinationMessage *obj;
@property (nonatomic, strong) UIView *subContentView;

@property (nonatomic, strong) UIView *answerSourceDashedLine;
@property (nonatomic, strong) YYLabel *answerSourceLabel;

@property (nonatomic, strong) UIView *helpfulAndUnHelpfulContent;
@property (nonatomic, strong) UIView *topSegmentationLine;

@property (nonatomic, strong) UIButton *helpfulBtn;
@property (nonatomic, strong) UIView *helpfulAndUnHelpfulLine;
@property (nonatomic, strong) UIButton *unHelpfulBtn;

@property (nonatomic, strong) UIView *unHelpfulTagAndTextViewContent;
@property (nonatomic, strong) UIView *bottomSegmentationLine;
@property (nonatomic, strong) NSMutableArray <UIButton *>*unHelpfulTags;
@property (nonatomic, strong) YYTextView *unHelpfulTextView;
/// 必填警告
@property (nonatomic, strong) UILabel *requiredWarning;
@property (nonatomic, strong) UIButton *unHelpfulSubmit;

@property (nonatomic, assign) CGFloat tableViewHeight;

@end

@implementation OSRobotCombinationCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.subContentView];
        
        // 注册键盘通知
        [[NSNotificationCenter defaultCenter] addObserver:self
                                                 selector:@selector(keyboardWillShow:)
                                                     name:UIKeyboardDidShowNotification
                                                   object:nil];
        
        [[NSNotificationCenter defaultCenter] addObserver:self
                                                 selector:@selector(keyboardWillHide:)
                                                     name:UIKeyboardDidHideNotification
                                                   object:nil];
    }
    return self;
}

- (void)dealloc {
    // 注册键盘通知
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardDidShowNotification object:nil];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardDidHideNotification object:nil];
}

#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    self.tempModelFrame = modelFrame;
    
    NSArray <CombinationMessage *>*richModels = (NSMutableArray <CombinationMessage *>*)modelFrame.model.message.content;
    
    self.subContentView.frame = modelFrame.robotCombinationF;
    [self.subContentView removeAllSubviews];
    
    [self.subContentView addSubview:self.answerSourceDashedLine];
    [self.subContentView addSubview:self.answerSourceLabel];

    [self.subContentView addSubview:self.helpfulAndUnHelpfulContent];

    [self.subContentView addSubview:self.unHelpfulTagAndTextViewContent];
    
    NSMutableArray <CombinationMessage *>*elements = [NSMutableArray arrayWithArray:richModels];
    __block CGFloat y = 0;
    CGFloat width = modelFrame.robotCombinationF.size.width;
    CGFloat x = 0;
    
    @weakify(self);
    [elements enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {

        @strongify(self);
        CGFloat height = obj.contentF.size.height;

        if ([obj.type isEqualToString:@"5"] ||
            [obj.type isEqualToString:@"36"]) {
            OSRobotCombinationTextSubview *textSubview = [[OSRobotCombinationTextSubview alloc] initWithFrame:CGRectMake(x, y, width, height)];
            [textSubview setWithCombinationModel:obj];
            [self.subContentView addSubview:textSubview];
        } else if ([obj.type isEqualToString:@"2"] ||
                   [obj.type isEqualToString:@"4"]) {

            OCRobotCombinationImageVideoSubview *mediaSubview = [[OCRobotCombinationImageVideoSubview alloc] initWithFrame:CGRectMake(x, y, width, height)];
            [mediaSubview setWithCombinationModel:obj];
            [self.subContentView addSubview:mediaSubview];
        } else if ([obj.type isEqualToString:@"3"]) {

            OSRobotCombinationFileSubview *fileSubview = [[OSRobotCombinationFileSubview alloc] initWithFrame:CGRectMake(x, y, width, height)];
            [fileSubview setWithModel:obj];
            [self.subContentView addSubview:fileSubview];
        } else if ([obj.type isEqualToString:@"7"]) {

            OSRobotCombinationVoiceSubview *voiceSubview = [[OSRobotCombinationVoiceSubview alloc] initWithFrame:CGRectMake(x, y, width, height)];
            [voiceSubview setWithModel:obj];
            [self.subContentView addSubview:voiceSubview];
        } else if ([obj.type isEqualToString:@"6"] ||
                   [obj.type isEqualToString:@"15"] ||
                   [obj.type isEqualToString:@"16"] ||
                   [obj.type isEqualToString:@"17"] ||
                   [obj.type isEqualToString:@"18"] ||
                   [obj.type isEqualToString:@"19"] ||
                   [obj.type isEqualToString:@"20"] ||
                   [obj.type isEqualToString:@"31"]) {

            OSRobotCombinationHotIssueSubview *hotIssueSubview = [[OSRobotCombinationHotIssueSubview alloc] initWithFrame:CGRectMake(x, y, width, height)];
            hotIssueSubview.hotIssueTitleF = self.tempModelFrame.hotIssueTitleF;
            hotIssueSubview.refreshBtnY = self.tempModelFrame.refreshBtnY;
            hotIssueSubview.refreshIconY = self.tempModelFrame.refreshIconY;
            hotIssueSubview.tempModelFrame = self.tempModelFrame;
            hotIssueSubview.indexPath = [NSIndexPath indexPathForRow:idx inSection:0];
            [hotIssueSubview setWithModel:obj];
            [self.subContentView addSubview:hotIssueSubview];
        } else {
        }
        y += height;
    }];
    
    [elements enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        
        @strongify(self);
        if (obj.clickLikeFeature) {
            self.answerSourceDashedLine.hidden = NO;
            self.answerSourceLabel.hidden = NO;
            self.helpfulAndUnHelpfulContent.hidden = NO;
            
            [self.answerSourceDashedLine.layer addSublayer:obj.answerSourceDashedLine];
            self.answerSourceDashedLine.frame = obj.answerSourceDashedLineF;
            
            self.answerSourceLabel.frame = obj.answerSourceF;
            self.answerSourceLabel.attributedText = obj.answerSourceAtt;
            
            self.helpfulAndUnHelpfulContent.frame = obj.helpfulAndUnHelpfulContentF;
            self.topSegmentationLine.frame = CGRectMake(0, 0, self.helpfulAndUnHelpfulContent.tos_width, 1.f);
            self.helpfulBtn.frame = obj.helpfulBtnF;
            self.helpfulAndUnHelpfulLine.frame = obj.helpfulAndUnHelpfulLineF;
            self.unHelpfulBtn.frame = obj.unHelpfulBtnF;
            
            self.unHelpfulTagAndTextViewContent.frame = obj.unHelpfulTagAndTextViewContentF;
            self.bottomSegmentationLine.frame = CGRectMake(0, 8.f, self.unHelpfulTagAndTextViewContent.tos_width, 1.f);
            
            __block BOOL select = NO;
            [self.unHelpfulTags enumerateObjectsUsingBlock:^(UIButton * _Nonnull label, NSUInteger idx, BOOL * _Nonnull stop) {
                
                if (obj.unHelpfulTagsF.count - 1 >= idx) {
                    label.frame = [obj.unHelpfulTagsF[idx] CGRectValue];
                    [label setTitle:[[TOSKitCustomInfo shareCustomInfo].setUnHelpfulTagList by_ObjectAtIndex:idx] forState:(UIControlStateNormal)];
                } else {
                    label.frame = CGRectZero;
                }
                
                if ([obj.unHelpfulSelectTags containsObject:label.titleLabel.text]) {
                    label.selected = YES;
                    select = YES;
                    label.layer.borderColor = TOSHexColor(0x4385FF).CGColor;
                } else {
                    label.selected = NO;
                    label.layer.borderColor = TOSHexAColor(0x000000, 0.15f).CGColor;
                }
            }];
            
            if (!select && self.unHelpfulTextView.text.length <= 0) {
                self.unHelpfulSubmit.backgroundColor = TOSHexColor(0xBDD5FF);
            } else {
                self.unHelpfulSubmit.backgroundColor = TOSHexColor(0x4385FF);
            }
            
            self.unHelpfulTextView.frame = obj.unHelpfulTextViewF;
            
            self.requiredWarning.frame = obj.requiredWarningF;
            self.unHelpfulSubmit.frame = obj.unHelpfulSubmitF;
            
            
            switch (obj.helpfulSelectType) {
                case HelpfulAndUnHelpfulSelectType_Unknown: {
                    self.helpfulBtn.selected = NO;
                    self.unHelpfulBtn.selected = NO;
                    self.helpfulBtn.tintColor = TOSHexColor(0x262626);
                    self.unHelpfulBtn.tintColor = TOSHexColor(0x262626);
                    self.unHelpfulTagAndTextViewContent.hidden = YES;
                }
                    break;
                case HelpfulAndUnHelpfulSelectType_Helpful: {
                    self.helpfulBtn.selected = YES;
                    self.unHelpfulBtn.selected = NO;
                    self.helpfulBtn.tintColor = TOSHexColor(0x4385FF);
                    self.unHelpfulBtn.tintColor = TOSHexColor(0x262626);
                    self.unHelpfulTagAndTextViewContent.hidden = YES;
                }
                    break;
                case HelpfulAndUnHelpfulSelectType_UnHelpful:
                default: {
                    self.helpfulBtn.selected = NO;
                    self.unHelpfulBtn.selected = YES;
                    self.helpfulBtn.tintColor = TOSHexColor(0x262626);
                    self.unHelpfulBtn.tintColor = TOSHexColor(0x4385FF);
                    self.unHelpfulTagAndTextViewContent.hidden = NO;
                }
                    break;
            }
            
            if (obj.closeClick) {
                self.unHelpfulTagAndTextViewContent.hidden = YES;
            }
            
            if (![kitUtils isBlankString:obj.answerSource]) {
                *stop = YES;    //存在来源时，提前停止
            } else {
                self.answerSourceLabel.hidden = YES;
            }
        } else {
            self.answerSourceDashedLine.hidden = YES;
            self.answerSourceLabel.hidden = YES;
            self.helpfulAndUnHelpfulContent.hidden = YES;
            self.unHelpfulTagAndTextViewContent.hidden = YES;
        }
    }];
}

- (void)didClickHelpfulBtnAction:(UIButton *)sender {
    [self helpfulAndUnHelpfulBtnAction:sender 
                     botAnswerFeedback:@"HELPFUL"
                               content:@""
                  robotNotHelpfulItems:[NSMutableSet set]];
}

- (void)didClickUnHelpfulBtnAction:(UIButton *)sender {
    [self helpfulAndUnHelpfulBtnAction:sender 
                     botAnswerFeedback:@"UNHELPFUL"
                               content:@""
                  robotNotHelpfulItems:[NSMutableSet set]];
}

- (void)helpfulAndUnHelpfulBtnAction:(UIButton *)sender 
                   botAnswerFeedback:(NSString *)botAnswerFeedback
                             content:(NSString *)content
                robotNotHelpfulItems:(NSMutableSet <NSString *>*)robotNotHelpfulItems {
    
    __block NSMutableArray <CombinationMessage *>*richModels = (NSMutableArray <CombinationMessage *>*)self.tempModelFrame.model.message.content;
    __block TIMMessageFrame *tempModelFrame = self.tempModelFrame;
    
    @weakify(self);
    [[OnlineRequestManager sharedCustomerManager] submitBotAnswerFeedbackWithAnswerUniqueId:richModels.firstObject.botAnswerUniqueId mainUniqueId:richModels.firstObject.mainUniqueId botId:richModels.firstObject.sender content:content botAnswerFeedback:botAnswerFeedback robotNotHelpfulItems:robotNotHelpfulItems success:^(NSString * _Nonnull result) {
        @strongify(self);
        [richModels enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            
            if (obj.clickLikeFeature) {
                
                if ([result isEqualToString:@"CANCEL"]) {           //取消
                    obj.helpfulSelectType = HelpfulAndUnHelpfulSelectType_Unknown;
                } else if ([result isEqualToString:@"HELPFUL"]) {   //点赞
                    obj.helpfulSelectType = HelpfulAndUnHelpfulSelectType_Helpful;
                } else if ([result isEqualToString:@"UNHELPFUL"]) { //点踩
                    obj.helpfulSelectType = HelpfulAndUnHelpfulSelectType_UnHelpful;
                    if (content.length > 0 ||
                        robotNotHelpfulItems.count > 0) {
                        obj.closeClick = YES;
                    }
                } else if ([result isEqualToString:@"REPETITION"]) { //重复提交
                    [WHToast showMessage:@"您已经提交过评价了" duration:2 finishHandler:^{
                        
                    }];
                }
                if (![kitUtils isBlankString:obj.answerSource]) {
                    *stop = YES;    //存在来源时，提前停止
                }
            }
        }];
        
        if (![result isEqualToString:@"REPETITION"]) {
            
            sender.selected = !sender.selected;
            if (sender.selected) {
                sender.tintColor = TOSHexColor(0x4385FF);
            } else {
                sender.tintColor = TOSHexColor(0x262626);
            }
            
            tempModelFrame.model.message.content = (NSString *)richModels;
            tempModelFrame.model = tempModelFrame.model;
            [self routerEventWithName:GXRobotCombinationHotIssueCellRefreshEventName
                             userInfo:@{MessageKey: tempModelFrame}];
        }
    } error:^(TIMConnectErrorCode errCode, NSString * _Nonnull errorDes) {
        
    }];
}

- (void)didClickTagBtnAction:(UIButton *)sender {
    sender.selected = !sender.selected;
    
    NSMutableArray <CombinationMessage *>*richModels = (NSMutableArray <CombinationMessage *>*)self.tempModelFrame.model.message.content;
    
    if (sender.selected) {
        sender.layer.borderColor = TOSHexColor(0x4385FF).CGColor;
        
        [richModels enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if (obj.clickLikeFeature) {
                
                [obj.unHelpfulSelectTags addObject:sender.titleLabel.text];
                if (![kitUtils isBlankString:obj.answerSource]) {
                    *stop = YES;    //存在来源时，提前停止
                }
            }
        }];
        
    } else {
        sender.layer.borderColor = TOSHexAColor(0x000000, 0.15f).CGColor;
        
        [richModels enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if (obj.clickLikeFeature) {
                
                [obj.unHelpfulSelectTags removeObject:sender.titleLabel.text];
                if (![kitUtils isBlankString:obj.answerSource]) {
                    *stop = YES;    //存在来源时，提前停止
                }
            }
        }];
    }
    
    self.tempModelFrame.model.message.content = (NSString *)richModels;
    
    self.tempModelFrame.model = self.tempModelFrame.model;
    
    [self routerEventWithName:GXRobotCombinationHotIssueCellRefreshEventName
                     userInfo:@{MessageKey: self.tempModelFrame}];
}

- (void)didClickUnHelpfulSubmitAction:(UIButton *)sender {
    [self endEditing:YES];
    
    NSMutableArray <CombinationMessage *>*richModels = (NSMutableArray <CombinationMessage *>*)self.tempModelFrame.model.message.content;
    
    __block NSMutableSet *robotNotHelpfulItems = [NSMutableSet set];
    [richModels enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        if (obj.clickLikeFeature) {
            
            if (obj.unHelpfulSelectTags) {
                [robotNotHelpfulItems addObjectsFromArray:obj.unHelpfulSelectTags];
            }
            if (![kitUtils isBlankString:obj.answerSource]) {
                *stop = YES;    //存在来源时，提前停止
            }
        }
    }];
    
    if (robotNotHelpfulItems.count <= 0 && self.unHelpfulTextView.text.length <= 0) {
        return;
    }
    
    if (([TOSKitCustomInfo shareCustomInfo].isShowUnHelpfulContent &&
         [TOSKitCustomInfo shareCustomInfo].isRequiredUnHelpfulContent &&
         self.unHelpfulTextView.text.length <= 0) ||
        ([TOSKitCustomInfo shareCustomInfo].isShowUnHelpfulContent &&
         self.unHelpfulTextView.text.length <= 0 &&
         robotNotHelpfulItems.count <= 0) ||
        (![TOSKitCustomInfo shareCustomInfo].isShowUnHelpfulContent &&
         robotNotHelpfulItems.count <= 0)) {
        
        NSMutableArray <CombinationMessage *>*richModels = (NSMutableArray <CombinationMessage *>*)self.tempModelFrame.model.message.content;
        [richModels enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if (obj.clickLikeFeature) {
                obj.showRequiredWarning = YES;
                if (![kitUtils isBlankString:obj.answerSource]) {
                    *stop = YES;    //存在来源时，提前停止
                }
            }
        }];
        
        self.tempModelFrame.model.message.content = (NSString *)richModels;
        self.tempModelFrame.model = self.tempModelFrame.model;
        [self routerEventWithName:GXRobotCombinationHotIssueCellRefreshEventName
                         userInfo:@{MessageKey: self.tempModelFrame}];
    } else {
        
        [self helpfulAndUnHelpfulBtnAction:[UIButton buttonWithType:(UIButtonTypeCustom)]
                         botAnswerFeedback:@"UNHELPFUL"
                                   content:self.unHelpfulTextView.text
                      robotNotHelpfulItems:robotNotHelpfulItems];
    }
}

// 键盘将要显示
- (void)keyboardWillShow:(NSNotification *)notification {
    NSDictionary *userInfo = notification.userInfo;
    CGRect keyboardFrame = [userInfo[UIKeyboardFrameEndUserInfoKey] CGRectValue];
    CGFloat keyboardHeight = keyboardFrame.size.height;

    [self adjustTableViewForKeyboardHeight:keyboardHeight];
}

// 键盘将要隐藏
- (void)keyboardWillHide:(NSNotification *)notification {
    [self adjustTableViewForKeyboardHeight:0];
}

// 调整TableView的contentOffset
- (void)adjustTableViewForKeyboardHeight:(CGFloat)keyboardHeight {
    if (self.unHelpfulTextView &&
        [self.unHelpfulTextView isFirstResponder]) {
        if (keyboardHeight <= 0) {
            [self tableView].tos_height = self.tableViewHeight;
        } else {
            self.tableViewHeight = [self tableView].tos_height; //保存TableView高度
            [self tableView].tos_height = kWindowHeight - keyboardHeight - kNavTop - kBottomBarHeight;
            
            NSIndexPath *indexPath = [[self tableView] indexPathForCell:self];
            [self.tableView scrollToRowAtIndexPath:indexPath
                                  atScrollPosition:UITableViewScrollPositionBottom
                                          animated:YES];
        }
    }
}

- (UITableView *)tableView {
    UIView *view = self.superview;
    
    while (view && ![view isKindOfClass:[UITableView class]]) {
        view = view.superview;
    }
    
    return (UITableView *)view;
}

- (void)textViewDidBeginEditing:(YYTextView *)textView {
}

- (void)textViewDidChange:(YYTextView *)textView {
    if ([textView isEqual:self.unHelpfulTextView]) {
        
        NSMutableArray <CombinationMessage *>*richModels = (NSMutableArray <CombinationMessage *>*)self.tempModelFrame.model.message.content;
        
        __block NSMutableSet *robotNotHelpfulItems = [NSMutableSet set];
        [richModels enumerateObjectsUsingBlock:^(CombinationMessage * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            if (obj.clickLikeFeature) {
                
                if (obj.unHelpfulSelectTags) {
                    [robotNotHelpfulItems addObjectsFromArray:obj.unHelpfulSelectTags];
                }
                if (![kitUtils isBlankString:obj.answerSource]) {
                    *stop = YES;    //存在来源时，提前停止
                }
            }
        }];
        
        if (robotNotHelpfulItems.count <= 0 && self.unHelpfulTextView.text.length <= 0) {
            self.unHelpfulSubmit.backgroundColor = TOSHexColor(0xBDD5FF);
        } else {
            self.unHelpfulSubmit.backgroundColor = TOSHexColor(0x4385FF);
        }
    }
}

- (void)textViewDidEndEditing:(YYTextView *)textView {
}

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

#pragma mark - 懒加载
- (UIView *)subContentView {
    if (!_subContentView) {
        _subContentView = [[UIView alloc] initWithFrame:CGRectZero];
        _subContentView.backgroundColor = [UIColor clearColor];
    }
    return _subContentView;
}

- (UIView *)answerSourceDashedLine {
    if (!_answerSourceDashedLine) {
        _answerSourceDashedLine = [[UIView alloc] initWithFrame:CGRectZero];
    }
    return _answerSourceDashedLine;
}

- (YYLabel *)answerSourceLabel {
    if (!_answerSourceLabel) {
        _answerSourceLabel = [[YYLabel alloc] initWithFrame:CGRectZero];
        _answerSourceLabel.numberOfLines = 0;
    }
    return _answerSourceLabel;
}

- (UIView *)helpfulAndUnHelpfulContent {
    if (!_helpfulAndUnHelpfulContent) {
        _helpfulAndUnHelpfulContent = [[UIView alloc] initWithFrame:CGRectZero];
        [_helpfulAndUnHelpfulContent addSubview:self.topSegmentationLine];
        [_helpfulAndUnHelpfulContent addSubview:self.helpfulBtn];
        [_helpfulAndUnHelpfulContent addSubview:self.helpfulAndUnHelpfulLine];
        [_helpfulAndUnHelpfulContent addSubview:self.unHelpfulBtn];
    }
    return _helpfulAndUnHelpfulContent;
}

- (UIView *)topSegmentationLine {
    if (!_topSegmentationLine) {
        _topSegmentationLine = [[UIView alloc] initWithFrame:CGRectZero];
        _topSegmentationLine.backgroundColor = TOSHexColor(0xF5F5F5);
    }
    return _topSegmentationLine;
}

- (UIView *)bottomSegmentationLine {
    if (!_bottomSegmentationLine) {
        _bottomSegmentationLine = [[UIView alloc] initWithFrame:CGRectZero];
        _bottomSegmentationLine.backgroundColor = TOSHexColor(0xF5F5F5);
    }
    return _bottomSegmentationLine;
}

- (UIButton *)helpfulBtn {
    if (!_helpfulBtn) {
        _helpfulBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [_helpfulBtn addTarget:self action:@selector(didClickHelpfulBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
        _helpfulBtn.frame = CGRectZero;
        [_helpfulBtn setTitle:@"有用" forState:(UIControlStateNormal)];
        [_helpfulBtn setImage:[[UIImage imageNamed:[NSString stringWithFormat:@"%@/helpful",FRAMEWORKS_BUNDLE_PATH]] imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate] forState:(UIControlStateNormal)];
        _helpfulBtn.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        [_helpfulBtn setTitleColor:TOSHexColor(0x262626) forState:(UIControlStateNormal)];
        [_helpfulBtn setTitleColor:TOSHexColor(0x4385FF) forState:(UIControlStateSelected)];
        _helpfulBtn.tintColor = TOSHexColor(0x262626);
        
        // 设置图片和文字的间距
        CGFloat spacing = 5.0;
        // 设置imageEdgeInsets
        _helpfulBtn.imageEdgeInsets = UIEdgeInsetsMake(0, -spacing / 2, 0, spacing / 2);
        // 设置titleEdgeInsets
        _helpfulBtn.titleEdgeInsets = UIEdgeInsetsMake(0, spacing / 2, 0, -spacing / 2);
    }
    return _helpfulBtn;
}

- (UIView *)helpfulAndUnHelpfulLine {
    if (!_helpfulAndUnHelpfulLine) {
        _helpfulAndUnHelpfulLine = [[UIView alloc] initWithFrame:CGRectZero];
        _helpfulAndUnHelpfulLine.backgroundColor = TOSHexColor(0xF5F5F5);
    }
    return _helpfulAndUnHelpfulLine;
}

- (UIButton *)unHelpfulBtn {
    if (!_unHelpfulBtn) {
        _unHelpfulBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [_unHelpfulBtn addTarget:self action:@selector(didClickUnHelpfulBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
        _unHelpfulBtn.frame = CGRectZero;
        [_unHelpfulBtn setTitle:@"无用" forState:(UIControlStateNormal)];
        [_unHelpfulBtn setImage:[[UIImage imageNamed:[NSString stringWithFormat:@"%@/unHelpful",FRAMEWORKS_BUNDLE_PATH]] imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate] forState:(UIControlStateNormal)];
        _unHelpfulBtn.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        [_unHelpfulBtn setTitleColor:TOSHexColor(0x262626) forState:(UIControlStateNormal)];
        [_unHelpfulBtn setTitleColor:TOSHexColor(0x4385FF) forState:(UIControlStateSelected)];
        _unHelpfulBtn.tintColor = TOSHexColor(0x262626);
        
        // 设置图片和文字的间距
        CGFloat spacing = 5.0;
        // 设置imageEdgeInsets
        _unHelpfulBtn.imageEdgeInsets = UIEdgeInsetsMake(0, -spacing / 2, 0, spacing / 2);
        // 设置titleEdgeInsets
        _unHelpfulBtn.titleEdgeInsets = UIEdgeInsetsMake(0, spacing / 2, 0, -spacing / 2);
    }
    return _unHelpfulBtn;
}

- (UIView *)unHelpfulTagAndTextViewContent {
    if (!_unHelpfulTagAndTextViewContent) {
        _unHelpfulTagAndTextViewContent = [[UIView alloc] initWithFrame:CGRectZero];
        
        [_unHelpfulTagAndTextViewContent addSubview:self.bottomSegmentationLine];
        [self.unHelpfulTags enumerateObjectsUsingBlock:^(UIButton * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            [_unHelpfulTagAndTextViewContent addSubview:obj];
        }];
        [_unHelpfulTagAndTextViewContent addSubview:self.unHelpfulTextView];
        
        [_unHelpfulTagAndTextViewContent addSubview:self.requiredWarning];
        [_unHelpfulTagAndTextViewContent addSubview:self.unHelpfulSubmit];
    }
    return _unHelpfulTagAndTextViewContent;
}

- (NSMutableArray<UIButton *> *)unHelpfulTags {
    if (!_unHelpfulTags) {
        _unHelpfulTags = [NSMutableArray array];
        
        for (NSInteger i = 0; i < 6; i++) {
            UIButton *btn = [UIButton buttonWithType:(UIButtonTypeCustom)];
            btn.frame = CGRectZero;
            btn.selected = NO;
            [btn addTarget:self action:@selector(didClickTagBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
            btn.backgroundColor = [UIColor clearColor];
            btn.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Regular" size:12.f];
            [btn setTitleColor:TOSHexColor(0x262626) forState:(UIControlStateNormal)];
            [btn setTitleColor:TOSHexColor(0x4385FF) forState:(UIControlStateSelected)];
            
            btn.tag = 1000 + i;
            btn.layer.masksToBounds = YES;
            btn.layer.cornerRadius = 6.f;
            btn.layer.borderWidth = 1.f;
            btn.layer.borderColor = TOSHexAColor(0x000000, 0.15f).CGColor;
            [_unHelpfulTags addObject:btn];
        }
    }
    return _unHelpfulTags;
}

- (YYTextView *)unHelpfulTextView {
    if (!_unHelpfulTextView) {
        _unHelpfulTextView = [[YYTextView alloc] initWithFrame:CGRectZero];
        _unHelpfulTextView.placeholderText = [TOSKitCustomInfo shareCustomInfo].setUnHelpfulContentHint;
        _unHelpfulTextView.textContainerInset = UIEdgeInsetsMake(12.f, 12.f, 12.f, 12.f);
        _unHelpfulTextView.placeholderTextColor = TOSHexColor(0xBFBFBF);
        _unHelpfulTextView.backgroundColor = TOSHexColor(0xF5F5F5);
        _unHelpfulTextView.font = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        _unHelpfulTextView.layer.masksToBounds = YES;
        _unHelpfulTextView.layer.cornerRadius = 6.f;
        _unHelpfulTextView.delegate = self;
    }
    return _unHelpfulTextView;
}

- (UILabel *)requiredWarning {
    if (!_requiredWarning) {
        _requiredWarning = [[UILabel alloc] initWithFrame:CGRectZero];
        _requiredWarning.text = @"备注不能为空";
        _requiredWarning.textColor = TOSHexColor(0xFF4D4F);
    }
    return _requiredWarning;
}

- (UIButton *)unHelpfulSubmit {
    if (!_unHelpfulSubmit) {
        _unHelpfulSubmit = [UIButton buttonWithType:UIButtonTypeCustom];
        _unHelpfulSubmit.frame = CGRectZero;
        _unHelpfulSubmit.backgroundColor = TOSHexColor(0x4385FF);
        [_unHelpfulSubmit setTitle:@"提交" forState:(UIControlStateNormal)];
        _unHelpfulSubmit.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Medium" size:14.f];
        _unHelpfulSubmit.layer.cornerRadius = 6.f;
        _unHelpfulSubmit.layer.masksToBounds = YES;
        [_unHelpfulSubmit addTarget:self action:@selector(didClickUnHelpfulSubmitAction:) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _unHelpfulSubmit;
}

@end
