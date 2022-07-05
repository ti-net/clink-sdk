//
//  UITableView+EmptyData.h
//  SmartHome
//
//  Created by 赵言 on 2019/7/11.
//  Copyright © 2019 赵言. All rights reserved.
//  解决TableView空数据的缺省页

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface UITableView (EmptyData)

/**
 根据数据源的个数显示内容
 
 @param networkStatus    当前网络状态 YES 无网，NO 有网
 @param count            UITableView Cell个数
 @return Cell个数
 */
- (NSInteger)addNetworkStatus:(BOOL)networkStatus byDataSourceCount:(NSInteger)count noDataImage:(NSString *)noDataImage;

/**
 * 根据数据源的个数显示内容
 */
- (NSInteger)addByDataSourceCount:(NSInteger)count noDataImage:(NSString *)noDataImage describe:(NSString *)describe;

@end

NS_ASSUME_NONNULL_END
