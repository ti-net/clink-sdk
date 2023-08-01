//
//  ZYSegmentControl.h
//  TIMClientKit
//
//  Created by 赵言 on 2022/8/1
//  Copyright © 2022 赵言. All rights reserved.
//  SegmentControl

#import "TOSBaseView.h"

NS_ASSUME_NONNULL_BEGIN
typedef NS_ENUM(NSUInteger, StyleType) {
    StyleTypeLine,
    StyleTypeRoundedCorners
};

@protocol ZYSegmentControlDelegate <NSObject>

@required
- (void)didClickSegmentedControl:(NSUInteger)selectedIndex;

@end

@interface ZYSegmentControl : TOSBaseView

@property (assign, nonatomic) id <ZYSegmentControlDelegate> delegate;

/**
 初始化
 
 @param frame 大小
 @param items 标签数组
 @param styleType 指示器类型
 @param arrayColor 颜色数组 titleColor selectTitleColor underlineColor指示器颜色
 @return ZYSegmentControl
 */
- (instancetype)initWithFrame:(CGRect)frame items:(NSArray <NSString *> *)items styleType:(StyleType)styleType arrayColor:(NSArray <UIColor *> *)arrayColor;

/**
 类方法
 
 @param frame 大小
 @param items 标签数组
 @param styleType 指示器类型
 @param arrayColor 颜色数组 titleColor selectTitleColor underlineColor指示器颜色
 @return ZYSegmentControl
 */
+ (instancetype)zySegmentControlWithFrame:(CGRect)frame items:(NSArray <NSString *> *)items styleType:(StyleType)styleType arrayColor:(NSArray <UIColor *> *)arrayColor;

/**
 选中的按钮
 */
@property (assign, nonatomic) NSUInteger selectedSegmentIndex;
@property (strong, nonatomic) NSMutableArray <NSString *>*itemNames;

/**
 更新后设置选中的itemName

 @param itemName 选中的项目
 */
- (void)reloadDataWithData:(NSString *)itemName;

- (void)reloadDataWithDatas:(NSMutableArray <NSString *>*)itemNames;

@end

NS_ASSUME_NONNULL_END
