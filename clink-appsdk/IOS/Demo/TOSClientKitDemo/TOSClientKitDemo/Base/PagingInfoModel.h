//
//  PagingInfoModel.h
//  mobileCMS
//
//  Created by 赵言 on 2019/12/12.
//  Copyright © 2019 赵言. All rights reserved.
//  分页加载的数据

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface PagingInfoModel : BaseModel

/* 用于分页加载的固定属性 */

@property (nonatomic, strong) NSNumber *start;
@property (nonatomic, strong) NSNumber *currentPageNo;
@property (nonatomic, strong) NSNumber *pageSize;
@property (nonatomic, strong) NSNumber *totalCount;
@property (nonatomic, strong) NSDictionary *countMap;
@property (nonatomic, strong) NSNumber *totalPageCount;

/// 列表数据
@property (nonatomic, strong) NSArray *data;

@end

NS_ASSUME_NONNULL_END
