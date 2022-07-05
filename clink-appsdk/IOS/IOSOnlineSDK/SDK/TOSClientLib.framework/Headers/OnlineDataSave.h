//
//  OnlineDataSave.h
//  TIMClientLib
//
//  Created by apple on 2021/10/29.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TOSSessionInfoModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface OnlineDataSave : NSObject<NSCopying, NSMutableCopying>

+ (OnlineDataSave *)shareOnlineDataSave;

/// 保存用户输入框数据
/// @param inputText 输入文本
- (void)saveUserInputText:(NSString *)inputText;
- (NSString *)getUserInputText;

//客服域名
- (void)saveOnlineUrl:(NSString*)saveStr;
-(NSString*)getOnlineUrl;

//accessSecret在座席端管理平台创建渠道时生成
- (void)saveAccessSecret:(NSString*)saveStr;
-(NSString*)getAccessSecret;

//企业ID
- (void)saveEnterpriseId:(NSString*)saveStr;
-(NSString*)getEnterpriseId;

//移动端唯一标识，对应座席端渠道ID
- (void)saveAccessId:(NSString*)saveStr;
-(NSString*)getAccessId;

//用户Id
- (void)saveVisitorId:(NSString*)saveStr;
-(NSString*)getVisitorId;

//用户名称
- (void)saveVisitorName:(NSString*)saveStr;
-(NSString*)getVisitorName;

//用户手机号
- (void)saveVisitorPhoneNum:(NSString*)saveStr;
-(NSString*)getVisitorPhoneNum;

//用户头像
- (void)saveVisitorHeaderUrl:(NSString*)saveStr;
-(NSString*)getVisitorHeaderUrl;

//客服id
- (void)saveEndpointId:(NSString*)saveStr;
-(NSString*)getEndpointId;

//自定义Title
- (void)saveCustomTitle:(NSString*)title;
-(NSString*)getCustomTitle;

//客服头像地址
- (void)saveKefuAvaterUrl:(NSString*)saveStr;
-(NSString*)getKefuAvaterUrl;

//客服会话Id
- (void)saveMainUniqueId:(NSString*)saveStr;
-(NSString*)getMainUniqueId;

//客服会话model
- (void)saveMainUniqueModel:(NSString*)sessModel;
-(NSString*)getMainUniqueModel;

//AppId
- (void)saveAppId:(NSString*)saveStr;
-(NSString*)getAppId;

// 存储会话状态
- (void)saveMainUniqueIdRunningStatus:(NSString*)saveStr runningStatus:(NSString*)runningStatus;
-(NSString*)getMainUniqueIdRunningStatus:(NSString*)mainUniqueId;

// 存储AppSetting全局配置信息
- (void)saveAppSetting:(NSString*)appSetting;
- (NSString *)getAppSetting;
// 获取配置的上传文件的限制大小
-(NSNumber *)getAppSettingFileSize;

@end

NS_ASSUME_NONNULL_END
