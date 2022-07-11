//
//  TIMSessionListViewController.h
//  TIMClientKit
//
//  Created by YanBo on 2020/5/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TIMBaseViewController.h"
#import <TOSClientLib/TOSClientLib.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMSessionListViewController : TIMBaseViewController <UITableViewDataSource,UITableViewDelegate>

#pragma mark - 列表属性
/**
 列表中会话数据模型的数据源

 @discussion 数据源中存放的元素为会话Cell的数据模型，即TIMSession对象。
 @warning 非线程安全，请在主线程操作此属性
 */
@property (nonatomic, strong) NSMutableArray *sessionListDataSource;

/**
 列表的TableView
 */
@property (nonatomic, strong) UITableView *sessionListTableView;

#pragma mark override
/**
 *  表格选中事件
 *
 *  @param sessionType    会话类型
 *  @param model                 数据模型
 *  @param indexPath             索引
 */
-(void)onSelectedTableRow:(TIMSessionType)sessionType
         sessionModel:(TIMSession *)model
               atIndexPath:(NSIndexPath *)indexPath;

@end

NS_ASSUME_NONNULL_END
