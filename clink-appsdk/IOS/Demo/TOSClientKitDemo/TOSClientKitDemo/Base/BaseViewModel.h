//
//  BaseViewModel.h
//  SmartHome
//
//  Created by 赵言 on 2019/7/4.
//  Copyright © 2019 赵言. All rights reserved.
//  所有ViewModel的基类

#import <Foundation/Foundation.h>
#import <YTKNetwork/YTKNetwork.h>

#import "YTKNetworkConfig.h"

#import "ErrorModel.h"
#import "PagingInfoModel.h"

NS_ASSUME_NONNULL_BEGIN

typedef enum : NSUInteger {
    NetworkStateBegin = 1,
    NetworkStateSuccess = 2,
    NetworkStateFail = 3, // 服务器返回失败信息
    NetworkStateConnectFail = 4, // 网络请求失败
} NetworkState;

@interface BaseViewModel : YTKRequest

/// 网络状态
@property (nonatomic, assign) NetworkState networkState;

/// 错误提示
@property (nonatomic, copy)   NSString *errorMessage;

/// error
@property (nonatomic, strong) ErrorModel *connectError;

/// 分页加载的固定属性
@property (nonatomic, strong) PagingInfoModel *pagingLoadModel;

/// 适配宽比例
@property (nonatomic, assign) CGFloat scale;

/// 适配高比例
@property (nonatomic, assign) CGFloat heightScale;

/// 发起请求
- (void)requestData;

- (void)requestDataWithReload:(BOOL)reload;

@end

NS_ASSUME_NONNULL_END
