//
//  TIMStatusDefine.h
//  TIMClientDemo
//
//  Created by YanBo on 2020/3/30.
//  Copyright © 2020 YanBo. All rights reserved.
//

#ifndef TIMErrorDefine_h
#define TIMErrorDefine_h

#pragma mark - lib层发送消息成功的UI刷新事件
static NSString * kTIMMessageUpdateChatUIFromLibNotification=@"TIMMessageUpdateChatUIFromLibNotification";

//更新群聊已读/未读 刷新
static NSString * const kTIMUpdateUnreadMessageNotification = @"kTIMUpdateUnreadMessageNotification";

//数据同步完成
static NSString * const kTIMIOnDataSynced = @"kTIMIOnDataSynced";

#pragma mark - 离线推送消息扩展标识
static NSString * kSendPushExtra_CUSTOMER_TINET = @"customer_tinet";

#pragma mark - 消息事件字串定义
static NSString * kMQTTMessage_EVENT_MESSAGE_ARRIVED = @"[MESSAGE ARRIVED]";
static NSString * kMQTTMessage_EVENT_GROUP_JOIN = @"[GROUP JOIN]";      // 加入群组事件
static NSString * kMQTTMessage_EVENT_GROUP_QUIT = @"[GROUP QUIT]";      // 退出群组事件
static NSString * kMQTTMessage_EVENT_GROUP_DELETE = @"[GROUP DELETE]";   // 删除群组事件
static NSString * kMQTTMessage_EVENT_GROUP_CREATE = @"[GROUP CREATE]";   // 创建群组事件
static NSString * kMQTTMessage_EVENT_MESSAGE_ACK = @"[MESSAGE ACK]";     // 消息发送确认事件
static NSString * kMQTTMessage_EVENT_DELETE_ACK = @"[DELETE ACK]";       // 消息删除确认事件
static NSString * kMQTTMessage_EVENT_KICK_OUT = @"[KICK OUT]";          // 剔除事件
static NSString * kMQTTMessage_EVENT_CLEAN_SESSION_ACK = @"[CLEAN SESSION ACK]";   // 清空会话确认事件
static NSString * kMQTTMessage_EVENT_REVOKE_ACK = @"[REVOKE ACK]";         // 撤回确认事件
static NSString * kMQTTMessage_EVENT_REVOKE = @"[REVOKE]";         // 撤回事件
static NSString * kMQTTMessage_EVENT_ENDPOINT_ARRIVED = @"[ENDPOINT ARRIVED]";// 客服消息事件

static NSString * kMQTTMessage_EVENT_SYNC_DATA = @"[SYNC DATA]";  //会话未读数同步数据
static NSString * kMQTTMessage_EVENT_SESSION_REPORT_ACK = @"[SESSION REPORT ACK]"; //会话上报ACK

static NSString * kMQTTMessage_EVENT_MESSAGE_RECEIVE_ACK = @"[MESSAGE RECEIVE ACK]"; //消息确认已读，服务器回给发送者消息已读，多个msgIds之间用逗号分隔

//static NSString * kMQTTMessage_EVENT_GROUP_MESSAGE_RECEIVE_ACK = @"[GROUP MESSAGE RECEIVE ACK]"; //群组消息确认已读，服务器会给发送者消息已读

#pragma mark - 消息状态

#pragma mark TIMMessageLocalStatus - 本地消息状态枚举
/*
 本地消息状态枚举
 
 */
typedef NS_ENUM(NSInteger, TIMMessageLocalStatus) {
    /*
     本地显示

     */
    TIMMessageLocalStatus_Show = 1,
    /*
     本地删除

     */
    TIMMessageLocalStatus_Remove = 2,
};

TIMMessageLocalStatus TIMMessageLocalStatusWithString(NSString *commandString);
NSString *TIMMessageLocalStatusString(TIMMessageLocalStatus timSessionType);

#pragma mark TIMMessageSenderType - 消息发送人类型
/*
 消息发送人类型
 
 */
typedef NS_ENUM(NSInteger, TIMMessageSenderType) {
    /*
    未知
     */
    TIMMessageSenderType_Unkwon = 0,
    /*
    坐席
     */
    TIMMessageSenderType_Online = 1,
    /*
     访客

     */
    TIMMessageSenderType_Visitor = 2,
    /*
    系统

    */
    TIMMessageSenderType_System = 3,
    /*
    机器人

    */
    TIMMessageSenderType_Robot = 4,
    /*
    系统通知

    */
    TIMMessageSenderType_Notify = 5,
};

#pragma mark TIMMessageStatus - 消息状态枚举
/*
 消息状态枚举
 
 */
typedef NS_ENUM(NSInteger, TIMMessageStatus) {
    /*
     发送失败

     */
    TIMMessageStatus_Send_Failed = -1,
    /*
     发送中

     */
    TIMMessageStatus_Sending = 0,
    /*
    已发送

    */
    TIMMessageStatus_Sended = 1,
    /*
    对方未读

    */
    TIMMessageStatus_Peer_Unread = 2,
    /*
    对方已读

    */
    TIMMessageStatus_Peer_Read = 3,
    /*
    已撤回

    */
    TIMMessageStatus_ReCall = 4,
    /*
    已删除

    */
    TIMMessageStatus_Remove = 5,
    /*
    保持

    */
    TIMMessageStatus_Keep = 8,
};

TIMMessageStatus TIMMessageStatusWithString(NSString *commandString);
NSString *TIMMessageStatusString(TIMMessageStatus timSessionType);

#pragma mark - 会话类型

#pragma mark TIMSessionType - 会话类型枚举
/*
 会话类型枚举

 @discussion 包含单聊和群聊
 */
typedef NS_ENUM(NSInteger, TIMSessionType) {
    /*
     单聊

     */
    TIMSessionType_SINGLE_CHAT = 1,
    /*
     群聊

     */
    TIMSessionType_GROUP_CHAT = 2,
    /*
     客服

     */
    TIMSessionType_OnLINESERVICE_CHAT = 3,
    
    
};

TIMSessionType TIMSessionTypeWithString(NSString *commandString);
NSString *TIMSessionTypeString(TIMSessionType timSessionType);

#pragma mark - 用户类型

#pragma mark TIMUserType - 用户类型枚举
/*
 用户类型枚举

 @discussion 目前仅包含长期有效客户和临时客户
 */
typedef NS_ENUM(NSInteger, TIMUserType) {
    /*
     长期有效客户类型

     */
    TIMUserType_LONGTERM_AVAILABLE = 1,
    /*
     临时有效客户类型

     */
    TIMUserType_TEMPORARY_AVAILABLE = 2,
};

TIMUserType TIMUserTypeWithString(NSString *commandString);
NSString *TIMUserTypeString(TIMUserType timUserType);

#pragma mark - 错误码相关

#pragma mark TIMConnectErrorCode - 建立连接返回的错误码

typedef NS_ENUM(NSInteger, TIMConnectErrorCode) {
    /**
     api请求HTTP发送失败

     @discussion 如果是偶尔出现此错误，SDK会做好自动重连，开发者无须处理。如果一直是这个错误，应该是您没有设置好ATS。
     */
    TIM_API_REQUEST_FAIL = 4003,

    /**
     api请求HTTP请求失败

     @discussion 连接相关的错误码，SDK会做好自动重连，开发者无须处理。
     */
    TIM_API_RESPONSE_ERROR = 4004,

    /**
     Api请求HTTP返回数据格式错误

     @discussion 连接相关的错误码，SDK会做好自动重连，开发者无须处理。
     */
    TIM_NODE_NOT_FOUND = 4005,

    /**
     创建Socket连接失败

     @discussion 连接相关的错误码，SDK会做好自动重连，开发者无须处理。
     */
    TIM_SOCKET_NOT_CONNECTED = 4006,

    /**
     Socket断开

     @discussion 连接相关的错误码，SDK会做好自动重连，开发者无须处理。
     */
    TIM_SOCKET_DISCONNECTED = 4007,

    /**
     PING失败

     @discussion 连接相关的错误码，SDK会做好自动重连，开发者无须处理。
     */
    TIM_PING_SEND_FAIL = 4008,

    /**
     PING超时

     @discussion 连接相关的错误码，SDK会做好自动重连，开发者无须处理。
     */
    TIM_PONG_RECV_FAIL = 4009,

    /**
     信令发送失败

     @discussion 连接相关的错误码，SDK会做好自动重连，开发者无须处理。
     */
    TIM_MSG_SEND_FAIL = 4010,

    /**
     连接过于频繁

     @discussion 连接相关的错误码，SDK会做好自动重连，开发者无须处理。
     */
    TIM_CONN_OVERFREQUENCY = 4011,

    /**
     连接ACK超时

     @discussion 连接相关的错误码，SDK会做好自动重连，开发者无须处理。
     */
    TIM_CONN_ACK_TIMEOUT = 4012,

    /**
     信令版本错误

     @discussion 连接相关的错误码，SDK会做好自动重连，开发者无须处理。
     */
    TIM_CONN_PROTO_VERSION_ERROR = 4013,

    /**
     accessKey错误

     @discussion 请检查您使用的accessKey是否正确。
     */
    TIM_CONN_ID_REJECT = 4014,

    /**
     服务器当前不可用（预留）

     @discussion 连接相关的错误码，SDK会做好自动重连，开发者无须处理。
     */
    TIM_CONN_SERVER_UNAVAILABLE = 4015,
    
    /**
     获取设备UDID失败

     @discussion 连接相关的错误码，需要主动再次调用。
     */
    TIM_CONN_GETUDID_FAILED = 4016,

    /**
     accessToken错误或无效

     @discussion accessToken无效一般有以下两种原因。
     accessToken错误，请您检查客户端初始化使用的accessKey和您服务器获取accessToken使用的accessKey是否一致；
     */
    TIM_CONN_TOKEN_INCORRECT = 10001,

    /**
     accessKey与accessToken不匹配

     @discussion
     请检查您使用的accessKey与accessToken是否正确，是否匹配。一般有以下两种原因。
     一是accessToken错误，请您检查客户端初始化使用的accessKey和您服务器获取accessToken使用的accessKey是否一致；
     二是accessToken过期，是因为您在开发者后台设置了accessToken过期时间，您需要请求您的服务器重新获取accessToken并再次用新的accessToken建立连接。
     */
    TIM_CONN_NOT_AUTHRORIZED = 4017,

    /**
     连接重定向

     @discussion 连接相关的错误码，SDK会做好自动重连，开发者无须处理。
     */
    TIM_CONN_REDIRECTED = 4018,

    /**
     BundleID不正确

     @discussion 请检查您App的BundleID是否正确。
     */
    TIM_CONN_PACKAGE_NAME_INVALID = 4019,

    /**
     accessKey被封禁或已删除

     @discussion 请检查您使用的accessKey是否被封禁或已删除。
     */
    TIM_CONN_APP_BLOCKED_OR_DELETED = 4020,

    /**
     用户被封禁

     @discussion 请检查您使用的accessToken是否正确，以及对应的UserId是否被封禁。
     */
    TIM_CONN_USER_BLOCKED = 4021,

    /**
     用户被踢下线

      @discussion 当前用户在其他设备上登录，此设备被踢下线
     */
    TIM_DISCONN_KICK = 4022,

    /**
     用户在其它设备上登录

      @discussion 重连过程中当前用户在其它设备上登录
     */
    TIM_CONN_OTHER_DEVICE_LOGIN = 4023,

    /**
     连接被拒绝

     @discussion 连接相关的错误码，SDK会做好自动重连，开发者无须处理。
     */
    TIM_CONN_REFUSED = 4024,

    /**
     SDK没有初始化

     @discussion 在使用SDK任何功能之前，必须先Init。
     */
    TIM_CLIENT_NOT_INIT = 4025,

    /**
     开发者接口调用时传入的参数错误

     @discussion 请检查接口调用时传入的参数类型和值。
     */
    TIM_INVALID_PARAMETER = 4026,

    /**
     Connection已经存在

     @discussion
     调用过connect之后，只有在accessToken错误或者被踢下线或者用户logout的情况下才需要再次调用connect。SDK会自动重连，不需要应用多次调用connect来保证连接性。
     */
    TIM_CONNECTION_EXIST = 4027,

    /**
     连接环境不正确（公有云 SDK 无法连接到私有云环境）

     @discussion 公有云 SDK 无法连接到私有云环境。请确认需要连接的环境，使用正确 SDK 版本。
     */
    TIM_ENVIRONMENT_ERROR = 4028,
    
    /**
     写入本地数据库失败

     */
    TIM_WRITE_DATABASE_ERROR = 4029,
    
    /**
     上传文件失败

     */
    TIM_UPLOAD_FILE_ERROR = 4030,

    /**
     开发者接口调用时传入的参数错误

     @discussion 请检查接口调用时传入的参数类型和值。
     */
//    TIM_INVALID_ARGUMENT = -1000
};


#endif /* TIMErrorDefine_h */
