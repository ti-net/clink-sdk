//
//  OnlineTokenModel.h
//  TIMClientLib
//
//  Created by apple on 2021/10/29.
//  Copyright © 2021 YanBo. All rights reserved.
//

//#import <TOSClientLib/TOSClientLib.h>
#import <Foundation/Foundation.h>
#import "TIMLibBaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface OnlineTokenModel : TIMLibBaseModel

/**
 accountId
 */
@property (nonatomic, strong) NSString *accountId;
/**
 appId 用于链接IM服务器
 */
@property (nonatomic, strong) NSString *appId;
/**
 userId  用于链接IM服务器
 */
@property (nonatomic, strong) NSString *userId;
/**
 accessToken  用于链接IM服务器
 */
@property (nonatomic, strong) NSString *accessToken;
/**
 endpointId 客服的Id
 */
@property (nonatomic, strong) NSString *endpointId;

@end

NS_ASSUME_NONNULL_END
