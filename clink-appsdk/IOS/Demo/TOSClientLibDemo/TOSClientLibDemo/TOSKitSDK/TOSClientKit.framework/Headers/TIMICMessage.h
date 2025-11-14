//
//  ICMessage.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/10.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <Foundation/Foundation.h>
//#import "ICChatServerDefs.h"
#import <TOSClientKit/ICChatServerDefs.h>

@interface TIMICMessage : NSObject

// 消息来源用户名
@property (nonatomic, copy) NSString *senderName;
// 消息来源用户id
@property (nonatomic, copy) NSString *from;
// 消息目的地群组id
@property (nonatomic, copy) NSString *to;
// 消息ID
@property (nonatomic, copy) NSString *messageId;
// 大图fileId
@property (nonatomic, copy) NSString *bigImageFileId;
// 消息发送状态
@property (nonatomic, assign) MessageDeliveryState deliveryState;
// 是否显示时间
@property (nonatomic, assign) BOOL showTime;
// 消息时间
@property (nonatomic, strong) NSDate *msgDate;
// 本地消息标识:(消息+时间)的MD5
@property (nonatomic, copy) NSString *localMsgId;
// 消息文本内容
@property (nonatomic, copy) NSString *content;
// 撤回的消息文本
@property (nonatomic, copy) NSString *revokeContent;
// 消息原始内容
@property (nonatomic, copy) NSString *contentSrc;
// 音频文件的fileKey
@property (nonatomic, copy) NSString *fileKey;
// 发送消息对应的type类型:1,2,3
@property (nonatomic, copy) NSString *type;
// 时长，宽高，首帧id
@property (nonatomic, strong) NSString *lnk;
// 语音的时长 秒
@property (nonatomic, strong) NSNumber *voiceDuration;
//
@property (nonatomic, strong) NSString *extra;

//@property (nonatomic, copy) NSDictionary *dict;

// (0:未读 1:已读 2:撤回)
@property (nonatomic, assign) ICMessageStatus status;

@property (nonatomic, copy) NSString *knowledge;



@end
