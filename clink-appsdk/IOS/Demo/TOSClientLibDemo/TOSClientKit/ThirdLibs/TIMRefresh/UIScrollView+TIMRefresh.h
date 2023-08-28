
//  UIScrollView+TIMRefresh.h
//  TIMRefreshExample
//
//  Created by MJ Lee on 15/3/4.
//  Copyright (c) 2015年 小码哥. All rights reserved.
//  给ScrollView增加下拉刷新、上拉刷新的功能

#import <UIKit/UIKit.h>
#import "TIMRefreshConst.h"

@class TIMRefreshHeader, TIMRefreshFooter;

NS_ASSUME_NONNULL_BEGIN

@interface UIScrollView (TIMRefresh)
/** 下拉刷新控件 */
@property (strong, nonatomic, nullable) TIMRefreshHeader *tos_header;
@property (strong, nonatomic, nullable) TIMRefreshHeader *header TIMRefreshDeprecated("使用tos_header");
/** 上拉刷新控件 */
@property (strong, nonatomic, nullable) TIMRefreshFooter *tos_footer;
@property (strong, nonatomic, nullable) TIMRefreshFooter *footer TIMRefreshDeprecated("使用tos_header");

#pragma mark - other
- (NSInteger)tosmj_totalDataCount;

@end

NS_ASSUME_NONNULL_END
