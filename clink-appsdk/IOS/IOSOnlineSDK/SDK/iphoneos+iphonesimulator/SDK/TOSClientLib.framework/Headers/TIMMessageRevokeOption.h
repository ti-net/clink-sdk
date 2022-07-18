//
//  TIMMessageRevokeOption.h
//  TIMClientLib
//
//  Created by 赵言 on 2020/10/19.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMMessageSendOption.h"

NS_ASSUME_NONNULL_BEGIN

@interface TIMMessageRevokeOption : TIMMessageSendOption

@property (nonatomic, copy, readonly) NSString *msgId;

/**
 参数对象初始化方法

 @param targetId                 接收方userId或groupId
 @param content                   消息内容
 @param msgId                   消息ID
 @return                参数对象
 */
- (instancetype)initWithTargetId:(NSString *)targetId content:(TOSMessage *)content msgId:(NSString *)msgId;

@end

NS_ASSUME_NONNULL_END
