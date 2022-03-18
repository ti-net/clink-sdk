//
//  MineSubHeadImageViewModel.h
//  mobileCMS
//
//  Created by YanBo on 2020/1/16.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "BaseViewModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface MineProfileViewModel : BaseViewModel


///  姓名
@property (nonatomic, copy) NSString *name;

///  昵称
@property (nonatomic, copy) NSString *nickName;

///  手机
@property (nonatomic, copy) NSString *phone;

///  头像
@property (nonatomic, copy) NSString *avatar;

/// 离线接听    true: 接听电话 false: 完全退出系统，不接听电话
@property (nonatomic, strong) NSNumber *keepCCOnline;

@end

NS_ASSUME_NONNULL_END
