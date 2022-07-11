//
//  BaseTableView.h
//  SmartHome
//
//  Created by 赵言 on 2019/7/11.
//  Copyright © 2019 赵言. All rights reserved.
//  TableView基类，包含下拉刷新和上拉加载方法

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

//typedef void(^FooterRefresh)(void);
//typedef void(^HeaderRefresh)(void);
@interface TIMBaseTableView : UITableView

//- (TIMRefreshBackNormalFooter *)setFooterRefresh:(FooterRefresh)footerRefresh;
//- (TIMRefreshNormalHeader *)setHeaderRefresh:(HeaderRefresh)headerRefresh;

@end

NS_ASSUME_NONNULL_END
