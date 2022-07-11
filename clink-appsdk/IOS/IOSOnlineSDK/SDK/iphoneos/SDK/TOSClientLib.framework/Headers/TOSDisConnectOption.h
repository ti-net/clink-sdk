//
//  TOSDisConnectOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/26.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TOSDisConnectOption : NSObject

/**
 App 在断开连接之后，是否还接收远程推送 只读
 */
@property(nonatomic,readonly) BOOL isReceivePush;

/**
 默认参数对象初始化方法

 @return                参数对象
 */
- (instancetype)defaultOption;

/**
 参数对象初始化方法

 @param isReceivePush                        App 在断开连接之后，是否还接收远程推送
 @return                        参数对象
 */
- (instancetype)initWithOption:(BOOL)isReceivePush;

@end

NS_ASSUME_NONNULL_END
