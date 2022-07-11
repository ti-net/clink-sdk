//
//  RobotRichMessage.h
//  TIMClientLib
//
//  Created by apple on 2021/10/26.
//  Copyright © 2021 YanBo. All rights reserved.
//
#import "TIMMessageContent.h"
#import <TOSClientLib/TOSClientLib.h>

NS_ASSUME_NONNULL_BEGIN

@interface RobotRichMessage : TIMMessageContent

@property (nonatomic, copy,readonly) NSString *content;
@property (nonatomic, copy,readonly) NSString *type;
@property (nonatomic, copy,readonly) NSString *textContent;
@property (nonatomic, copy,readonly) NSString *sender;
@property (nonatomic, copy,readonly) NSString *robotMediaUrl;
@property (nonatomic, copy,readonly) NSString *robotProvider;


/**
 初始化文本消息

 @param content  消息的内容
 @return        消息对象
 */
- (instancetype)initMessageWithContent:(NSString *)content;

@end

NS_ASSUME_NONNULL_END
