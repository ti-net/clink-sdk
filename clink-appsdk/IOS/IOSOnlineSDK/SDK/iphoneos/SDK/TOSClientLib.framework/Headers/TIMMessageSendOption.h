//
//  TIMMessageSendOption.h
//  TIMClientLib
//
//  Created by YanBo on 2020/4/27.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIMMessageContent.h"
#import "TOSMessage.h"
#import "TIMStatusDefine.h"
#import "TIMMessagePushOption.h"

NS_ASSUME_NONNULL_BEGIN

@interface TIMMessageSendOption : NSObject

/**
 接收方userId或groupId
 */
@property (nonatomic, copy, readonly) NSString* targetId;

/**
 消息内容
 */
@property (nonatomic, strong, readonly) TOSMessage* content;


/**
 需要推送时传入，设备离线时推送
 */
@property (nonatomic, strong, readonly) TIMMessagePushOption* pushOption;


/**
 参数对象初始化方法

 @param targetId                 接收方userId或groupId
 @param content                   消息内容
 @return               参数对象
 */
- (instancetype)initWithOption:(NSString *)targetId content:(TOSMessage *)content;

/**
 参数对象初始化方法

 @param targetId                 接收方userId或groupId
 @param content                   消息内容
 @param pushOption             推送参数对象
 @return                参数对象
 */
- (instancetype)initWithOption:(NSString *)targetId content:(TOSMessage *)content pushOption:(nullable TIMMessagePushOption *)pushOption;

@end

NS_ASSUME_NONNULL_END
