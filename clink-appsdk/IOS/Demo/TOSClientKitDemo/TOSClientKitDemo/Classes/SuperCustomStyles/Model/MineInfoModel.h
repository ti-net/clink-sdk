//
//  MineInfoModel.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/26.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface MineInfoModel : BaseModel

+ (MineInfoModel *)shareMineInfoModel;

/// 座席头像
@property (nonatomic, copy) NSString *avatar;

/// 可选外显号码
@property (nonatomic, strong) NSArray <NSString *>*clid;

/// 座席姓名
@property (nonatomic, copy) NSString *name;

/// 座席昵称
@property (nonatomic, copy) NSString *nickname;

/// 短信通知号码
@property (nonatomic, copy) NSString *noticeTel;

/// 外显规则：1，随机 2，轮选
@property (nonatomic, strong) NSNumber *obClidChooseRule;

/// 已选外显号码
@property (nonatomic, copy) NSString *obClidLeftAssignValue;

/// 外显号码类型，0-全部, 2-座席指定, 3-智能外显
@property (nonatomic, strong) NSNumber *obClidLeftRule;

/// 座席手机号
@property (nonatomic, copy) NSString *phone;

/// 默认工单模板类别id
@property (nonatomic, strong) NSNumber *workflowCategory;

/// 默认工单模板id
@property (nonatomic, strong) NSNumber *workflowId;

/// 头像
@property (nonatomic, strong) UIImage *image;

/// 离线接听设置 true: 接听电话 false: 完全退出系统，不接听电话
@property (nonatomic, strong) NSNumber *keepCCOnline;

/// 接听来电    1: 置忙, -1:置闲
@property (nonatomic, strong) NSNumber *pauseType;

@end

NS_ASSUME_NONNULL_END
