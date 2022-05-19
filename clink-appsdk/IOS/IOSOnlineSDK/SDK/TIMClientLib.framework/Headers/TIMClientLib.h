//
//  TIMClient.h
//  TIMClient
//
//  Created by YanBo on 2020/3/28.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import <Foundation/Foundation.h>

//! Project version number for TIMClient.
FOUNDATION_EXPORT double TIMClientVersionNumber;

//! Project version string for TIMClient.
FOUNDATION_EXPORT const unsigned char TIMClientVersionString[];

// In this header, you should import all the public headers of your framework using statements like #import <TIMClient/PublicHeader.h>

// TIM核心类
//#import <TIMClientLib/TIMRole.h>
#import <TIMClientLib/TIMInitOption.h>
#import <TIMClientLib/TIMConnectOption.h>
#import <TIMClientLib/TIMClient.h>

// 用户信息类
//#import <TIMClientLib/TIMUserInfo.h>
//#import <TIMClientLib/TIMGroup.h>

// 会话类
#import <TIMClientLib/TIMSessionListOption.h>
#import <TIMClientLib/TIMSession.h>

// 消息类
//#import <TIMClientLib/TIMMessageHistoryOption.h>
#import <TIMClientLib/TIMMessageSendOption.h>
#import <TIMClientLib/MYHTIMMessage.h>
#import <TIMClientLib/TIMMessageContent.h>
#import <TIMClientLib/TIMTextMessage.h>
#import <TIMClientLib/TIMImageMessage.h>
#import <TIMClientLib/TIMVoiceMessage.h>
#import <TIMClientLib/TIMVideoMessage.h>
#import <TIMClientLib/TIMNotifyMessage.h>
#import <TIMClientLib/TIMCustomizeMessage.h>

//online消息类
#import <TIMClientLib/TextMessage.h>
#import <TIMClientLib/ImageMessage.h>
#import <TIMClientLib/VideoMessage.h>
#import <TIMClientLib/VoiceMessage.h>
#import <TIMClientLib/FileMessage.h>
#import <TIMClientLib/RichTextMessage.h>
#import <TIMClientLib/ChatOpenMessage.h>
#import <TIMClientLib/ChatBridgeMessage.h>
#import <TIMClientLib/ChatCloseMessage.h>
#import <TIMClientLib/ChatQueueMessage.h>
#import <TIMClientLib/ChatLocationMessage.h>
#import <TIMClientLib/RobotBridgeMessage.h>
#import <TIMClientLib/WithdrawMessage.h>
#import <TIMClientLib/ChatOfflineMessage.h>
#import <TIMClientLib/ChatOnlineMessage.h>
#import <TIMClientLib/ChatLeaveQueueMessage.h>
#import <TIMClientLib/ChatLeadingWordsMessage.h>
#import <TIMClientLib/RobotRichMessage.h>
#import <TIMClientLib/ChatLeaveMessage.h>
#import <TIMClientLib/ChatLeaveReceiveMessage.h>
#import <TIMClientLib/ChatInvestigationMessage.h>

#import <TIMClientLib/OnlineEventSendManager.h>
#import <TIMClientLib/OnlineMessageSendManager.h>


// 联系人类
//#import <TIMClientLib/TIMContact.h>
//#import <TIMClientLib/TIMContactGroup.h>
//#import <TIMClientLib/TIMContactDetail.h>

// 群组类
//#import <TIMClientLib/TIMUserGroup.h>
//#import <TIMClientLib/TIMUserGroupMember.h>
//#import <TIMClientLib/TIMJoinGroupOption.h>
//#import <TIMClientLib/TIMInviteUserToGroupOption.h>
//#import <TIMClientLib/TIMUpdateGroupOption.h>

// 工具类
#import <TIMClientLib/TIMLibBaseModel.h>
#import <TIMClientLib/NSObject+TIMModel.h>

// 错误码定义
#import <TIMClientLib/TIMStatusDefine.h>

//客服模块
#import <TIMClientLib/OnlineMessageDefineManager.h>
#import <TIMClientLib/OnlineRequestManager.h>

#import <TIMClientLib/OnlineInitOption.h>
#import <TIMClientLib/OnlineDataSave.h>

#import <TIMClientLib/OnlineTokenModel.h>
#import <TIMClientLib/OnlineChatRecordModel.h>
#import <TIMClientLib/OnlineClientInfoModel.h>




