//
//  ResetViewModel.h
//  mobileCMS
//
//  Created by YanBo on 2020/1/16.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "BaseViewModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface ResetViewModel : BaseViewModel

/// 原密码
@property (nonatomic, copy) NSString *oldPassword;

/// 新密码
@property (nonatomic, copy) NSString *nPassword;

@end

NS_ASSUME_NONNULL_END
