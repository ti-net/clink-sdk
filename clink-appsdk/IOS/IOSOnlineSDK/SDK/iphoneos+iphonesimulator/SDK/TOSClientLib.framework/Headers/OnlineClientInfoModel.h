//
//  OnlineClientInfoModel.h
//  TIMClientLib
//
//  Created by apple on 2021/10/30.
//  Copyright © 2021 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMLibBaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface OnlineClientInfoModel : TIMLibBaseModel
/**
 avatar  座席头像
 */
@property (nonatomic, strong) NSString *avatar;
/**
 nickName 昵称
 */
@property (nonatomic, strong) NSString *nickName;

@end

NS_ASSUME_NONNULL_END
