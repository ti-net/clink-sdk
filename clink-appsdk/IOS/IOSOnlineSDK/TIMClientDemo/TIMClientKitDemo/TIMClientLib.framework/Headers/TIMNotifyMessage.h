//
//  TIMNotifyMessage.h
//  TIMClientLib
//
//  Created by YanBo on 2020/5/6.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "TIMMessageContent.h"

NS_ASSUME_NONNULL_BEGIN

@interface TIMNotifyMessage : TIMMessageContent

/**
 通知的类型
 */
@property (nonatomic, copy,readonly) NSString* type;

/**
 通知的内容
 */
@property (nonatomic,copy,readonly) NSString *content;

/**
 语音消息的附加信息 扩展信息，可以放置任意的数据内容 加密
 */
@property (nonatomic, copy,readonly) NSString *extra;

/**
 初始化通知消息

 @param type                  通知的类型
 @param content           通知的内容
 @return            通知消息对象
 */
- (instancetype)initMessageWithContent:(NSString *)type content:(NSString *)content;

/**
 初始化通知消息

 @param type                  通知的类型
 @param content           通知的内容
 @param extra                附加信息
 @return            通知消息对象
 */
- (instancetype)initMessageWithContent:(NSString *)type content:(NSString *)content extra:(nullable NSData *)extra;


@end

NS_ASSUME_NONNULL_END
