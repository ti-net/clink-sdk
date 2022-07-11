//
//  ChatBridgeMessage.h
//  OSClientLib
//
//  Created by apple on 2021/10/19.
//  Copyright © 2021 YanBo. All rights reserved.
//
#import "TIMMessageContent.h"
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface ChatBridgeMessage : TIMMessageContent

/**
 消息的内容 加密
 */
@property (nonatomic, copy,readonly) NSString *content;
@property (nonatomic, copy,readonly) NSString *sender;
@property (nonatomic, copy,readonly) NSString *clientIntroduce;
@property (nonatomic, copy,readonly) NSString *welcomStr;
@property (nonatomic, assign,readonly) BOOL *noImage;
@property (nonatomic, assign,readonly) int  imageNum;

/**
 初始化文本消息

 @param content  消息的内容
 @return        消息对象
 */
- (instancetype)initMessageWithContent:(NSString *)content;

@end

NS_ASSUME_NONNULL_END
