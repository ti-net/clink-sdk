//
//  TIMVideoMessage.h
//  TIMClientLib
//
//  Created by YanBo on 2020/5/6.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "TIMMessageContent.h"

NS_ASSUME_NONNULL_BEGIN

@interface TIMVideoMessage : TIMMessageContent

/**
 视频文件的URL
 */
@property (nonatomic, copy,readonly) NSString *videoUrl;

/**
 视频文件 本地路径
 */
@property (nonatomic, copy, readonly) NSString *localVideoURL;

/**
 图片消息URL
 */
@property (nonatomic, copy, readonly) NSString *thumbnailImageURL;

/**
 视频长度  秒数，最长60秒
 */
@property (nonatomic,assign,readonly) int duration;

/**
 视频宽度
 */
@property (nonatomic, assign,readonly) int videoWidth;

/**
 视频高度
 */
@property (nonatomic,assign,readonly) int videoHeight;

/**
 语音文件大小 单位bytes
 */
@property (nonatomic,assign,readonly) int videoSize;

/**
 语音消息内容
 */
@property (nonatomic, copy,readonly) NSString *content;

/**
 语音消息的附加信息 扩展信息，可以放置任意的数据内容 加密
 */
@property (nonatomic, copy,readonly) NSString *extra;

/**
 初始化视频消息

 @param videoUrl         视频消息的URL
 @param thumbnailImageURL         封面图消息的URL
 @return            视频消息对象
 */
- (instancetype)initMessageWithContent:(NSString *)videoUrl thumbnailImageURL:(NSString*)thumbnailImageURL extra:(nullable NSString *)extra;

/**
初始化视频消息

@param videoUrl         视频消息的URL
@param duration         视频时长
@param width                视频宽度
@param height              视频高度
@param videoSize       视频文件大小
@param extra                附加消息
@return            语音消息对象
*/

- (instancetype)initMessageWithContent:(NSString *)videoUrl thumbnailImageURL:(NSString*)thumbnailImageURL duration:(int)duration width:(int)width height:(int)height videoSize:(int)videoSize content:(NSString *)content extra:(nullable NSString *)extra;

@end

NS_ASSUME_NONNULL_END
