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
//#import <TOSClientLib/TIMRole.h>
#import <TOSClientLib/TIMInitOption.h>
#import <TOSClientLib/TOSInitOption.h>
#import <TOSClientLib/TIMConnectOption.h>
#import <TOSClientLib/TOSConnectOption.h>
#import <TOSClientLib/TIMClient.h>

// 用户信息类
//#import <TOSClientLib/TIMUserInfo.h>
//#import <TOSClientLib/TIMGroup.h>

// 会话类
#import <TOSClientLib/TIMSessionListOption.h>
#import <TOSClientLib/TIMSession.h>
#import <TOSClientLib/TOSClientKitCommodityCardOption.h>

// 消息类
//#import <TOSClientLib/TIMMessageHistoryOption.h>
#import <TOSClientLib/TIMMessageSendOption.h>
#import <TOSClientLib/TOSMessage.h>
#import <TOSClientLib/TIMMessageContent.h>
#import <TOSClientLib/TIMTextMessage.h>
#import <TOSClientLib/TIMImageMessage.h>
#import <TOSClientLib/TIMVoiceMessage.h>
#import <TOSClientLib/TIMVideoMessage.h>
#import <TOSClientLib/TIMNotifyMessage.h>
#import <TOSClientLib/TIMCustomizeMessage.h>

//online消息类
#import <TOSClientLib/TextMessage.h>
#import <TOSClientLib/ImageMessage.h>
#import <TOSClientLib/VideoMessage.h>
#import <TOSClientLib/VoiceMessage.h>
#import <TOSClientLib/FileMessage.h>
#import <TOSClientLib/RichTextMessage.h>
#import <TOSClientLib/ChatOpenMessage.h>
#import <TOSClientLib/ChatBridgeMessage.h>
#import <TOSClientLib/ChatCloseMessage.h>
#import <TOSClientLib/ChatQueueMessage.h>
#import <TOSClientLib/ChatLocationMessage.h>
#import <TOSClientLib/RobotBridgeMessage.h>
#import <TOSClientLib/WithdrawMessage.h>
#import <TOSClientLib/ChatOfflineMessage.h>
#import <TOSClientLib/ChatOnlineMessage.h>
#import <TOSClientLib/ChatLeaveQueueMessage.h>
#import <TOSClientLib/ChatLeadingWordsMessage.h>
#import <TOSClientLib/RobotRichMessage.h>
#import <TOSClientLib/ChatLeaveMessage.h>
#import <TOSClientLib/ChatLeaveReceiveMessage.h>
#import <TOSClientLib/ChatInvestigationMessage.h>


#import <TOSClientLib/OnlineEventSendManager.h>
#import <TOSClientLib/OnlineMessageSendManager.h>


// 联系人类
//#import <TOSClientLib/TIMContact.h>
//#import <TOSClientLib/TIMContactGroup.h>
//#import <TOSClientLib/TIMContactDetail.h>

// 群组类
//#import <TOSClientLib/TIMUserGroup.h>
//#import <TOSClientLib/TIMUserGroupMember.h>
//#import <TOSClientLib/TIMJoinGroupOption.h>
//#import <TOSClientLib/TIMInviteUserToGroupOption.h>
//#import <TOSClientLib/TIMUpdateGroupOption.h>

// 工具类
#import <TOSClientLib/TIMLibBaseModel.h>
#import <TOSClientLib/NSObject+TIMModel.h>

// 错误码定义
#import <TOSClientLib/TIMStatusDefine.h>

//客服模块
#import <TOSClientLib/OnlineMessageDefineManager.h>
#import <TOSClientLib/OnlineRequestManager.h>

#import <TOSClientLib/OnlineInitOption.h>
#import <TOSClientLib/OnlineDataSave.h>

#import <TOSClientLib/OnlineTokenModel.h>
#import <TOSClientLib/OnlineChatRecordModel.h>
#import <TOSClientLib/OnlineClientInfoModel.h>
#import <TOSClientLib/TOSSessionInfoModel.h>




