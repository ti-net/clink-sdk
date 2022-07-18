//
//  TIMImageMessage.h
//  TIMClientLib
//
//  Created by YanBo on 2020/5/6.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "TIMMessageContent.h"
#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface TIMImageMessage : TIMMessageContent

/**
 图片消息URL 暂时用于接收
 */
@property (nonatomic, strong, readonly) NSString *originImageURL;

/**
 图片消息URL 暂时用于接收 接收的url默认值
 */
@property (nonatomic, strong, readonly) NSString *thumbnailImageURL;

/**
 图片消息URL 本地路径
 */
@property (nonatomic, strong, readonly) NSString *localImageURL;

/**
 图片消息对象
 */
@property (nonatomic, strong, readonly) UIImage *image;

/**
 图片宽度
 */
@property (nonatomic,assign,readonly) int imageWidth;

/**
 图片高度
 */
@property (nonatomic,assign,readonly) int imageHeight;

/**
 图片大小 单位bytes
 */
@property (nonatomic,assign,readonly) long imageSize;

/**
 图片消息的内容 加密
 */
@property (nonatomic, copy,readonly) NSString *content;

/**
 图片消息的附加信息 扩展信息，可以放置任意的数据内容 加密
 */
@property (nonatomic, copy,readonly) NSString *extra;

/**
 初始化图片消息 发送使用

 @param ImageURI   图片消息的URL或本地地址
 @return         图片消息对象
 */
- (instancetype)initMessageWithImageURI:(NSString *)ImageURI extra:(nullable NSString *)extra;

- (instancetype)initMessageWithImageURI:(NSString *)ImageURI image:(nullable UIImage *)image width:(int)width height:(int)height size:(long)size originUrl:(NSString *)originUrl content:(NSString *)content extra:(nullable NSString *)extra;

@end

NS_ASSUME_NONNULL_END
