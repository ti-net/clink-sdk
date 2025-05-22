//
//  ICChatMessageSatisfactionPopupCell.m
//  TOSClientKit
//
//  Created by 言 on 2023/10/8.
//  Copyright © 2023 YanBo. All rights reserved.
//

#import "ICChatMessageSatisfactionPopupCell.h"
#import "YYKit.h"
#import "TOSSatisfactionModel.h"

#import "TIMMessageModel.h"
#import "ICFaceManager.h"
#import "TIMConstants.h"
#import "ICChatMessageBaseCell+CustomerUnread.h"
#import "NSDictionary+TIMTool.h"
#import "YYKit.h"
#import "XZEmotion.h"
#import "TOSCustomerChatVC.h"
#import "STBaseWebViewController.h"
#import <TOSClientLib/TOSClientLib.h>
#import <TOSClientKit/TOSClientKit.h>

#import "TOSSatisfactionModel.h"
#import "TOSSatisfactionStatusModel.h"
#import <TOSClientLib/TOSGetInvestigationInfoModel.h>
#import "TIMMessageModel.h"

@interface ICChatMessageSatisfactionPopupCell ()

@property (nonatomic, strong) UILabel *title;
@property (nonatomic, strong) UIButton *satisfactionBtn;

@property (nonatomic, strong) TIMMessageFrame *tempModelFrame;

@end

@implementation ICChatMessageSatisfactionPopupCell
- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self.contentView addSubview:self.title];
        [self.contentView addSubview:self.satisfactionBtn];
    }
    return self;
}

#pragma mark - Private Method
- (void)setModelFrame:(TIMMessageFrame *)modelFrame
{
    [super setModelFrame:modelFrame];
    self.title.frame = modelFrame.chatLabelF;
    self.satisfactionBtn.frame = CGRectMake(modelFrame.chatLabelF.origin.x, self.title.bottom_sd + 12.f, modelFrame.chatLabelF.size.width, 32.f);
    //获取存储AppSetting全局配置信息
    TOSSatisfactionModel *contentModel = [TOSSatisfactionModel yy_modelWithJSON:[[OnlineDataSave shareOnlineDataSave] getAppSetting]];
    NSLog(@"====multiInvestigation==%ld",contentModel.investigation.multiInvestigation.integerValue);
    TOSSatisfactionStatusModel *extraModel = [TOSSatisfactionStatusModel yy_modelWithJSON:modelFrame.model.message.extra];
    self.tempModelFrame = modelFrame;
    //根据AppSetting全局配置信息判断是否开启重复提交满意度评价的开关
    if (extraModel.alreadyInvestigation &&
        [extraModel.alreadyInvestigation isEqualToString:@"1"] && (contentModel && contentModel.investigation.multiInvestigation.integerValue !=1)) {  //已评价
        self.satisfactionBtn.backgroundColor = [TOSKitCustomInfo shareCustomInfo].satisfaction_evaluate_selected;
        self.satisfactionBtn.selected = YES;
    } else {
        self.satisfactionBtn.backgroundColor = [TOSKitCustomInfo shareCustomInfo].satisfaction_evaluate_normal;
        self.satisfactionBtn.selected = NO;
    }
}

- (void)didClickSatisfactionBtnAction:(UIButton *)sender {
    if (!sender.isSelected) {
        TOSSatisfactionStatusModel *extraModel = [TOSSatisfactionStatusModel yy_modelWithJSON:self.tempModelFrame.model.message.extra?:@""];
        
        [self routerEventWithName:GXRouterEventSatisfactionPopupView
                         userInfo:@{@"uniqueId": extraModel.uniqueId,
                                    MessageKey: self.tempModelFrame,
                                    @"mainUniqueId": extraModel.mainUniqueId
                                  }];
    }
}

- (UILabel *)title {
    if (!_title) {
        _title = [[UILabel alloc] initWithFrame:(CGRectZero)];
        _title.textColor = [TOSKitCustomInfo shareCustomInfo].receiveText_Color;
        _title.textAlignment = NSTextAlignmentCenter;
        _title.font = [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font;
        _title.numberOfLines = 0;
        _title.text = @"请对我本次服务进行满意度评价";
    }
    return _title;
}

- (UIButton *)satisfactionBtn {
    if (!_satisfactionBtn) {
        _satisfactionBtn = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_satisfactionBtn setTitle:@"评价" forState:(UIControlStateNormal)];
        [_satisfactionBtn setTitle:@"已评价" forState:(UIControlStateSelected)];
        _satisfactionBtn.titleLabel.font = [TOSKitCustomInfo shareCustomInfo].chatMessage_tosRobotText_font;
        [_satisfactionBtn setTitleColor:[TOSKitCustomInfo shareCustomInfo].satisfaction_evaluate_titleColor_normal forState:(UIControlStateNormal)];
        [_satisfactionBtn setTitleColor:[TOSKitCustomInfo shareCustomInfo].satisfaction_evaluate_titleColor_selected forState:(UIControlStateSelected)];
        [_satisfactionBtn addTarget:self action:@selector(didClickSatisfactionBtnAction:) forControlEvents:(UIControlEventTouchUpInside)];
        _satisfactionBtn.backgroundColor = [TOSKitCustomInfo shareCustomInfo].satisfaction_evaluate_normal;
        _satisfactionBtn.layer.cornerRadius = 4.f;
        _satisfactionBtn.layer.masksToBounds = YES;
    }
    return _satisfactionBtn;
}

@end
