//
//  TIMATMessageReadOption.h
//  TIMClientLib
//
//  Created by 赵言 on 2020/12/9.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMATMessageReadOption : NSObject

/**
 群组Id
 */
@property (nonatomic, copy, readonly) NSString *groupId;

/**
 消息Id
 */
@property (nonatomic, copy, readonly) NSString *msgId;


/**
 参数对象初始化方法

 @param groupId        群组Id
 @param msgId          消息Id
 @return                参数对象
 */
- (instancetype)initWithOption:(NSString *)groupId msgId:(NSString *)msgId;

@end

NS_ASSUME_NONNULL_END
