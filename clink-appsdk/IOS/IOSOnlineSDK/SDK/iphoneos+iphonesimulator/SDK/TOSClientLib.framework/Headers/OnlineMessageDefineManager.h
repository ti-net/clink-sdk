//
//  TIMStatusDefine.h
//  TIMClientDemo
//
//  Created by YanBo on 2020/3/30.
//  Copyright © 2020 YanBo. All rights reserved.
//

#ifndef OnlineMessageDefineManager_h
#define OnlineMessageDefineManager_h

#pragma mark - 客服消息事件字串定义
static NSString * CHAT_MESSAGE  =  @"chatMessage";   // 聊天消息
static NSString * CHAT_BRIDGE  =  @"chatBridge";    //接通座席
static NSString * CHAT_CLOSE    =  @"chatClose";    //关闭座席
static NSString * CHAT_LEAVE_MESSAGE    =  @"chatLeaveMessage";   // 留言消息
static NSString * ROBOT_BRIDGE    =  @"robotBridge";    //接通机器人
static NSString * CHAT_OPEN    =  @"chatOpen";    //会话开始
static NSString * CHAT_QUEUE    =  @"chatQueue";    //进入排队
static NSString * CHAT_LOCATION    =  @"chatLocation";    //排队位置播报
static NSString * CHAT_INVESTIGATION    =  @"chatInvestigation";   // 满意度
static NSString * WITHDRAW    =  @"withdraw";    //座席撤回消息
static NSString * CHAT_SWITCH    =  @"chatSwitch";    //分支节点
static NSString * CHAT_LEADING_WORDS    =  @"chatLeadingWords";    //引导语
static NSString * CHAT_INQUIRY    =  @"chatInquiry";    //询前表单
static NSString * CHAT_OFFLINE    =  @"chatOffline";    //访客离线
static NSString * CHAT_ONLINE    =  @"chatOnline";    //访客上线
static NSString * CHAT_LEAVE_QUEUE    =  @"chatLeaveQueue";    //访客退出排队
static NSString * CHAT_SWITCH_NEXT    =  @"chatSwitchNext";    //访客选择节点
static NSString * CHAT_SUBMIT_INVESTIGATION    =  @"chatSubmitInvestigation";    //访客提交满意度
static NSString * CHAT_SUBMIT_INQUIRY    =  @"chatSubmitInquiry" ;   //访客提交询前表单

#pragma mark - 客服消息类型定义
static int    MESSAGE_TEXT   = 1;    //文本消息
static int    MESSAGE_IMAGE  =  2;    //图片消息
static int    MESSAGE_FILE  =  3 ;   //文件消息
static int    MESSAGE_VIDEO  =  4 ;   //视频消息
static int    ROBOT_HTML =   5 ;   //机器人富文本消息
static int    ROBOT_CHOOSE =   6 ;   //机器人选项消息
static int    MESSAGE_VOICE  =  7 ;   //语音消息
static int    KNOW_LEDGE  =  8 ;   //知识库文件消息
static int    ROBOT_GROUP  =  14 ;   //机器人组合消息
static int    ROBOT_ABOUT_QUESTION  =  15 ;   //机器人相关问题
static int    ROBOT_GUESS_QUESTION  =  16 ;   //机器人猜你想问
static int    ROBOT_COMMON_QUESTION  =  17 ;   //机器人常见问题
static int    ROBOT_APPROX_QUESTION  =  18 ;   //机器人近似问题
static int    ROBOT_COMMENT_QUESTION  =  19;    //机器人推荐问题
static int    ROBOT_COMMON_QUESTION_CLASSIC  =  20 ;   //机器人常见问题（分类）

#pragma mark - 消息发送人类型
static int ONLINE_SENDER  =  1;    //座席
static int VISITOR_SENDER  =  2;   // 访客
static int SYSTEM_SENDER  =  3 ;   //系统
static int ROBOT_SENDER  =  4;   //机器人
static int NOTIFY_SENDER  =  5;   //系统通知

#endif /* OnlineMessageDefineManager_h */
