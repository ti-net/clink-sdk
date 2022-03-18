//
//  TIMTextMessage.h
//  TIMClientLib
//
//  Created by YanBo on 2020/5/5.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "TIMMessageContent.h"

NS_ASSUME_NONNULL_BEGIN

/**
文本消息类

*/

@interface TIMTextMessage : TIMMessageContent<NSCoding>

/**
 文本消息的内容 加密
 */
@property (nonatomic, copy,readonly) NSString *content;

/**
 文本消息的附加信息 加密
 */
@property (nonatomic, copy,readonly) NSString *extra;

/**
 初始化文本消息

 @param content 文本消息的内容
 @return        文本消息对象
 */
- (instancetype)initMessageWithContent:(NSString *)content;

/**
初始化文本消息

@param content  文本消息的内容
@param extra      附加信息
@return        文本消息对象
*/
- (instancetype)initMessageWithContent:(NSString *)content extra:(NSString *)extra;

/// @用户的ID列表，用英文逗号拼接
/// @param userIds 用户的ID列表，例：aaa,bbb,ccc
/// @param atUsers 用户的名列表，例：@aaa, @bbb ,@ccc
- (void)atWithUserIds:(NSString *)userIds AtUsers:(NSString *)atUsers;
/// 判断是否@我
/// @param userId 当前用户的userId
- (BOOL)isATMessageWithUserId:(NSString *)userId;

@end

NS_ASSUME_NONNULL_END
