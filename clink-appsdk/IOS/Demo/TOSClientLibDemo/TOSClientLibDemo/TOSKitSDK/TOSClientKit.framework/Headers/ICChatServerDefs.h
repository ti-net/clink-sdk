//
//  ICChatServerDefs.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/4/7.
//  Copyright © 2016年 gxz All rights reserved.
//

#ifndef ICChatServerDefs_h
#define ICChatServerDefs_h

// 消息发送状态
typedef enum {
    ICMessageDeliveryState_Failure_SensitiveWords = -2,   // 敏感词拦截
    ICMessageDeliveryState_Pending = 0,  // 待发送
    ICMessageDeliveryState_Delivering = 1,   // 正在发送
    ICMessageDeliveryState_Delivered = 2,    // 已发送，成功
    ICMessageDeliveryState_Failure = 3,      // 发送失败
    ICMessageDeliveryState_ServiceFaid = 4,   // 发送服务器失败(可能其它错,待扩展)
}MessageDeliveryState;

// 消息状态
typedef enum {
    ICMessageStatus_unRead = 0,          // 消息未读
    ICMessageStatus_read,                // 消息已读
    ICMessageStatus_back                 // 消息撤回
}ICMessageStatus;

typedef NS_ENUM(NSInteger, ICChatBoxStatus) {
//    ICChatBoxStatusNothing,     // 默认状态
    ICChatBoxStatusShowVoice,   // 录音状态
    ICChatBoxStatusShowFace,    // 输入表情状态
    ICChatBoxStatusShowMore,    // 显示“更多”页面状态
    ICChatBoxStatusShowKeyboard,// 正常键盘
    ICChatBoxStatusShowVideo    // 录制视频
};


typedef enum {
    ICDeliverTopStatus_NO         = 0, // 非置顶
    ICDeliverTopStatus_YES             // 置顶
}ICDeliverTopStatus;


typedef enum {
    ICFileType_Other = 0,                // 其它类型
    ICFileType_Audio,                    //
    ICFileType_Video,                    //
    ICFileType_Html,
    ICFileType_Pdf,
    ICFileType_Doc,
    ICFileType_Xls,
    ICFileType_Ppt,
    ICFileType_Img,
    ICFileType_Txt
}ICFileType;


#endif /* ICChatServerDefs_h */
