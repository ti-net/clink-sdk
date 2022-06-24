//
//  TIMVoiceMessage.h
//  TIMClientLib
//
//  Created by YanBo on 2020/5/6.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "TIMMessageContent.h"

NS_ASSUME_NONNULL_BEGIN

@interface TIMVoiceMessage : TIMMessageContent

/**
 语音消息的URL
 */
@property (nonatomic, copy,readonly) NSString *voiceUrl;

/**
 语音长度  毫秒单位，最长60秒
 */
@property (nonatomic,assign,readonly) int duration;

/**
 语音文件大小 单位bytes
 */
@property (nonatomic,assign,readonly) int voiceSize;

/**
 消息的内容 加密
 */
@property (nonatomic, copy,readonly) NSString *content;

/**
 语音消息的附加信息 扩展信息，可以放置任意的数据内容 加密
 */
@property (nonatomic, copy,readonly) NSString *extra;

/**
 初始化语音消息

 @param voiceUrl         语音消息的URL
 @return                 语音消息对象
 */
- (instancetype)initMessageWithUrl:(NSString *)voiceUrl extra:(nullable NSString *)extra;

/**
初始化语音消息

@param voiceUrl         语音消息的URL
@param duration         语音时长
@param voiceSize       语音文件大小
@param extra                附加消息
@return            语音消息对象
*/

- (instancetype)initMessageWithContent:(NSString *)voiceUrl duration:(int)duration voiceSize:(int)voiceSize content:(NSString *)content extra:(nullable NSString *)extra;

@end

NS_ASSUME_NONNULL_END
