//
//  TOSSatisfactionModel.h
//  TOSClientKit
//
//  Created by 言 on 2022/8/23.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <TOSClientLib/TIMLibBaseModel.h>

NS_ASSUME_NONNULL_BEGIN

@interface TOSSatisfactionStarInfoModel : TIMLibBaseModel

@property (nonatomic, strong) NSArray <NSString *>*tabBar;

@end

@interface TOSSatisfactionOptionsModel : TIMLibBaseModel

@property (nonatomic, copy) NSString *name;
@property (nonatomic, strong) NSArray <TOSSatisfactionStarInfoModel *>*star;

@end

@interface TOSSatisfactionContentModel : TIMLibBaseModel

@property (nonatomic, strong) NSArray <TOSSatisfactionOptionsModel *>*options;
@property (nonatomic, strong) NSArray *star;
@property (nonatomic, strong) NSArray *text;

@end


@interface TOSSatisfactionStarModel : TIMLibBaseModel

@property (nonatomic, copy) NSString *desc;
@property (nonatomic, strong) NSNumber *star;
@property (nonatomic, assign) BOOL isSelect;

@end

@interface TOSSatisfactionChatSatisSolveStateModel : TIMLibBaseModel

/// 0：不展示，1：展示
@property (nonatomic, strong) NSNumber *enabled;
@property (nonatomic, strong) NSNumber *required;

@end

@interface TOSSatisfactionInvestigationModel : TIMLibBaseModel

@property (nonatomic, strong) NSNumber *appEnabled;
@property (nonatomic, strong) NSDictionary *chatSatisNumEvaluation;

/// 判断是否展示问题已解决
@property (nonatomic, strong) TOSSatisfactionChatSatisSolveStateModel *chatSatisSolveState;

@property (nonatomic, strong) NSDictionary *chatWeixinTemplateState;


@property (nonatomic, strong) TOSSatisfactionContentModel *content;


@property (nonatomic, copy) NSString *createTime;
@property (nonatomic, strong) NSDictionary *dissatisfactionRate;
@property (nonatomic, strong) NSNumber *enabled;
@property (nonatomic, strong) NSNumber *enterpriseId;
@property (nonatomic, strong) NSNumber *evaluationType;
@property (nonatomic, strong) NSNumber *feedbackType;
@property (nonatomic, strong) NSNumber *rename_id;
@property (nonatomic, strong) NSDictionary *investigationTimeout;
/// 是否重复提交满意度评价 0：不重复。1：重复
@property (nonatomic, strong) NSNumber *multiInvestigation;

/// 是否显示备注评价 0：不显示。1：显示
@property (nonatomic, strong) NSNumber *remark;

/// 备注评价的占位文本
@property (nonatomic, copy) NSString *remarkContent;
@property (nonatomic, strong) NSDictionary *satisfactionRate;

/// 评价星级数
@property (nonatomic, strong) NSArray <TOSSatisfactionStarModel *>*star;

@property (nonatomic, strong) NSNumber *starType;
@property (nonatomic, strong) NSNumber *timeout;
@property (nonatomic, strong) NSNumber *type;
@property (nonatomic, copy) NSString *updateTime;

/// 请您为我们的服务进行评价
@property (nonatomic, copy) NSString *welcome;

@property (nonatomic, strong) NSNumber *wxwbEvaluationType;

@end

@interface TOSSatisfactionModel : TIMLibBaseModel

//[[OnlineDataSave shareOnlineDataSave] saveAppSetting:appSettingStr]
@property (nonatomic, strong) NSDictionary *appSettings;
@property (nonatomic, strong) NSDictionary *clientTimeout;
@property (nonatomic, copy) NSString *closeMessage;
@property (nonatomic, copy) NSString *inquiryForm;

@property (nonatomic, strong) TOSSatisfactionInvestigationModel *investigation;

@property (nonatomic, strong) NSDictionary *leaveMessage;
@property (nonatomic, strong) NSNumber *noStatusTransfer;
@property (nonatomic, strong) NSDictionary *visitorTimeout;

@end

NS_ASSUME_NONNULL_END
