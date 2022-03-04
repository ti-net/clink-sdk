//
//  MineUploadAvatarModel.h
//  mobileCMS
//
//  Created by YanBo on 2020/1/19.
//  Copyright © 2020 赵言. All rights reserved.
//

#import "BaseViewModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface MineUploadAvatarModel : BaseViewModel

///  头像
@property (nonatomic, strong) UIImage *avatarImage;

///  头像地址
@property (nonatomic, copy) NSString *avatarFileName;

@end

NS_ASSUME_NONNULL_END
