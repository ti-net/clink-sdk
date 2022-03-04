//
//  TIMURLDefine.h
//  TIMClientDemo
//
//  Created by YanBo on 2020/6/2.
//  Copyright © 2020 YanBo. All rights reserved.
//

#ifndef TIMURLDefine_h
#define TIMURLDefine_h

//AppStore 版本
static NSString * const kAppStore = @"http://itunes.apple.com/lookup?id=1495589474";
//AppStore 下载链接
static NSString * const kAppStoreURL = @"itms-apps://itunes.apple.com/app/id1495589474";

//自动升级
static NSString * const kUpgrade = @"package/upgrade";

// 获取token
static NSString * const kLogin = @"login";

// 获取accessToken和用户id
static NSString * const kGetIMUser = @"getImUser";

// 获取tcbus的accessToken和用户id
static NSString * const kGetTcbusUser = @"getTcbusUser";

// 获取应用中的用户列表
static NSString * const kGetListUser = @"listUser";

// 参数设置
static CGFloat const kImageUploadNetworkRequestTimeout = 30.f;

#endif /* TIMURLDefine_h */
