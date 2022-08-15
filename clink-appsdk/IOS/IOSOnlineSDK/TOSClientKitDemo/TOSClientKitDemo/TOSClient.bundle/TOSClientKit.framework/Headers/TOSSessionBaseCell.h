//
//  TOSSessionBaseCell.h
//  TIMClientKit
//
//  Created by YanBo on 2020/5/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <TOSClientLib/TIMSession.h>
#import "TOSSessionBaseTableViewCell.h"

NS_ASSUME_NONNULL_BEGIN
/**
 会话Cell的基类
 */

@interface TOSSessionBaseCell : TOSSessionBaseTableViewCell

/**
 会话Cell的数据模型
 */
@property (nonatomic, strong) TIMSession *session;

@property (nonatomic, weak) UIButton *unreadLabel;

+ (instancetype)cellWithTableView:(UITableView *)tableView;

@end

NS_ASSUME_NONNULL_END
