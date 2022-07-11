//
//  TIMMessageReCallOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/27.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMMessageReCallOption : NSObject

/**
 消息id
 */
@property (nonatomic, copy, readonly) NSString * messageId;

/**
 参数对象初始化方法

 @param messageId            消息Id
 @return                参数对象
 */
- (instancetype)initWithOption:(NSString *)messageId;

@end

NS_ASSUME_NONNULL_END
