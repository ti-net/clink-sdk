//
//  TRCommonTool.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/9.
//  Copyright © 2019 赵言. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <TTGTagCollectionView/TTGTextTagCollectionView.h>

NS_ASSUME_NONNULL_BEGIN
@class CustomFieldModel;
@class CallInfoModel;
@interface TRCommonTool : NSObject

#pragma mark - 电话模块
/// 通话类型
/// @param statusInt 标识
+ (NSString *)callType:(NSInteger)statusInt;

#pragma mark - 客户模块工具
/// 客户模块，过滤不展示的控件
/// @param type type字段
+ (BOOL)filterTimeControlWithType:(NSInteger)type;

/// 客户模块，过滤不展示的控件
/// @param name name字段
+ (BOOL)filterTimeControlWithName:(NSString *)name;

/// 客户模块，获取默认字段对应的key，上传数据
/// @param model CustomFieldModel
//+ (NSString *)customerModuleGetKeyWithModel:(CustomFieldModel *)model;

/// 客户模块，获取等级、客户归属、性别的字典文件名
/// @param title 标题名称
+ (NSString *)customerModuleGetFileNameWithTitle:(NSString *)title;

/// 客户搜索模块，设置标签样式
/// @param tagView TTGTextTagCollectionView
+ (void)setupTTGTextTagView:(TTGTextTagCollectionView *)tagView;

/// 全局默认标签样式配置
+ (TTGTextTagConfig *)setupTTGTextTagConfig;

/// 客户模块，搜索功能，处理创建时间的开始和结束数据
/// @param startTime 开始时间   yyyy/MM/dd 00:00:00
/// @param endTime   结束时间   yyyy/MM/dd 23:59:59
+ (NSString *)customerSearchCreationTimeWithStartTime:(NSString *)startTime endTime:(NSString *)endTime;

/// 客户模块，客户详情，回访计划界面，回访状态Label的颜色
/// @param state 回访状态
+ (UIColor *)getAgainVisitStateColor:(NSInteger)state;


#pragma mark - 工单模块
/// 工单模块，优先级Label的颜色
/// @param state 优先级状态
+ (UIColor *)getWorkOrderLevelStateColor:(NSInteger)state;

/// 工单模块，处理状态Label的颜色
/// @param state 处理状态
+ (UIColor *)getWorkOrderHandleStatusStateColor:(NSInteger)state;

/// 工单模块-标签颜色
/// @param index 标签
+ (UIColor *)matchingColorWithIndex:(NSInteger)index;

#pragma mark - 其他
// md5 小写
+ (NSString *)md5:(NSString *)string;

/**
 获取当前控制器

 @return 返回当前控制器
 */
+ (UIViewController *)getCurrentVC;

/// 秒 -> 时分秒
/// @param totalSeconds 秒
+ (NSString *)timeFormatted:(NSInteger)totalSeconds;

/// 秒 -> 时分秒
/// @param totalSeconds 秒
+ (NSString *)callTimeFormatted:(NSInteger)totalSeconds;

/// 拨号
/// @param model 拨号数据
/// @param vc vc
//+ (void)callPhoneWithModel:(CallInfoModel *)model vc:(BaseViewController *)vc;

@end

NS_ASSUME_NONNULL_END
