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

@interface TOSSatisfactionInvestigationModel : TIMLibBaseModel

@property (nonatomic, strong) NSNumber *appEnabled;
@property (nonatomic, strong) NSDictionary *chatSatisNumEvaluation;
@property (nonatomic, strong) NSDictionary *chatSatisSolveState;


@property (nonatomic, strong) TOSSatisfactionContentModel *content;


@property (nonatomic, copy) NSString *createTime;
@property (nonatomic, strong) NSDictionary *dissatisfactionRate;
@property (nonatomic, strong) NSNumber *enabled;
@property (nonatomic, strong) NSNumber *enterpriseId;
@property (nonatomic, strong) NSNumber *evaluationType;
@property (nonatomic, strong) NSNumber *feedbackType;
@property (nonatomic, strong) NSNumber *rename_id;
@property (nonatomic, strong) NSDictionary *investigationTimeout;
@property (nonatomic, strong) NSNumber *multiInvestigation;
@property (nonatomic, strong) NSNumber *remark;
@property (nonatomic, copy) NSString *remarkContent;
@property (nonatomic, strong) NSDictionary *satisfactionRate;

/// star
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
