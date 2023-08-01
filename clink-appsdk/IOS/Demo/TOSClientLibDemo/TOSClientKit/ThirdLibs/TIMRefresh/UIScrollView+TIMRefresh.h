
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
@property (strong, nonatomic, nullable) TIMRefreshHeader *mj_header;
@property (strong, nonatomic, nullable) TIMRefreshHeader *header TIMRefreshDeprecated("使用mj_header");
/** 上拉刷新控件 */
@property (strong, nonatomic, nullable) TIMRefreshFooter *mj_footer;
@property (strong, nonatomic, nullable) TIMRefreshFooter *footer TIMRefreshDeprecated("使用mj_footer");

#pragma mark - other
- (NSInteger)mj_totalDataCount;

@end

NS_ASSUME_NONNULL_END
