//
//  LoginModel.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/11.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface LoginModel : BaseModel

+ (LoginModel *)loginModel;
// 保存LoginModel
- (BOOL)saveLoginModelWithDic:(NSDictionary *)dic;
- (BOOL)saveLoginModel:(LoginModel *)loginModel;
// 删除LoginModel
- (void)removeLoginModel;

@property (nonatomic, strong) NSNumber *clientId;
@property (nonatomic, copy) NSString *clientName;

/// 呼叫权限 0:关闭，1无限制 ，2国内长途 3:国内本地
@property (nonatomic, strong) NSNumber *callPower;

/// 座席类型，1：全渠道、2：呼叫中心、3：在线客服、4：工单座席
@property (nonatomic, strong) NSNumber *clientType;

/// 录音试听下载权限 2：仅试听 1：试听下载 0:关闭
@property (nonatomic, strong) NSNumber *downloadRecord;

/// 软电话开关 0：关闭  1：打开
@property (nonatomic, strong) NSNumber *softPhoneSwitch;




/// 座席编号
@property (nonatomic, copy) NSString *cno;
/// 登录密码
@property (nonatomic, copy) NSString *pwd;

@property (nonatomic, strong) NSNumber *enterpriseId;

@property (nonatomic, assign) BOOL isLogin;

/// 选择忙碌状态
@property (nonatomic, copy) NSString *busyType;

/// 融云Token
@property (nonatomic, copy) NSString *rongCloudToken;
/// 融云userId
@property (nonatomic, copy) NSString *rongCloudUserId;

@end

NS_ASSUME_NONNULL_END
