//
//  ICMessageConst.h
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/17.
//  Copyright © 2016年 gxz All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

#define MessageFont [UIFont fontWithName:@"PingFangSC-Regular" size:16.0]
#define MessageFont12 [UIFont fontWithName:@"PingFangSC-Regular" size:12.0]
#define MessageCustTitleFont [UIFont fontWithName:@"Helvetica-Bold" size:16]

/************Const*************/
extern CGFloat const HEIGHT_CHATBOXVIEW;






/************Event*************/

extern NSString *const GXHotIssueSendMessageEventName;
extern NSString *const RouterEventGetSendTextMessage;
extern NSString *const RouterEventGetSendTextMessageKnowledge;
extern NSString *const GXRouterEventVoiceTapEventName;
extern NSString *const GXRouterEventCombinationVoiceTapEventName;
extern NSString *const GXRouterEventCombinationFileTapEventName;
extern NSString *const GXRouterEventImageTapEventName;
extern NSString *const GXRobotCombinationHotIssueCellRefreshEventName;
extern NSString *const GXRouterEventImageRejectedTapEventName;
extern NSString *const GXRouterEventImageApprovedTapEventName;
extern NSString *const GXRouterEventTextUrlTapEventName;
extern NSString *const GXRouterEventMenuTapEventName;
extern NSString *const GXRouterEventVideoTapEventName;
extern NSString *const GXRouterEventShareTapEvent;

extern NSString *const GXRouterEventVideoRecordExit;
extern NSString *const GXRouterEventVideoRecordCancel;
extern NSString *const GXRouterEventVideoRecordFinish;
extern NSString *const GXRouterEventVideoRecordStart;
extern NSString *const GXRouterEventURLSkip;
extern NSString *const GXRouterEventScanFile;

extern NSString *const TinetRouterClickEventUrl;
extern NSString *const TinetRouterClickEventMiniProgramCard;
extern NSString *const TinetRouterClickEventLogisticsCard;
extern NSString *const TinetRouterClickEventKnowledge;
extern NSString *const TinetRouterClickEventOrderNumber;
extern NSString *const TinetRouterClickEventPhone;

extern NSString *const TinetRouterSenderCommodityCardEventUrl;
extern NSString *const TinetRouterClickCommodityCardEvent;



/************Name*************/

extern NSString *const MessageKey;
extern NSString *const VoiceIcon;
extern NSString *const RedView;
// 消息类型
extern NSString *const TypeSystem;
extern NSString *const TypeCommodityCard;
extern NSString *const TypeCommodityCardDetails;
extern NSString *const TypeSmallProgramCard;
extern NSString *const TypeLogisticsCard;
extern NSString *const TypeText;
extern NSString *const TypeTextTag;
extern NSString *const TypeRevoke;
extern NSString *const TypeUnsupport;
extern NSString *const TypeVoice;
extern NSString *const TypePic;
extern NSString *const TypeVideo;
extern NSString *const TypeFile;
extern NSString *const TypePicText;
extern NSString *const TypeCustom;
extern NSString *const TypeCustomFile;
extern NSString *const TypeInvestigation;
extern NSString *const GXRichText;

extern NSString *const TySystomCombination;//系统富文本消息
extern NSString *const TypeRobotCombination;//机器人组合消息
extern NSString *const TypeRobotCombinationList;//机器人多条选择消息
extern NSString *const TypeRobotSelectCombination;//机器人组合选择消息
extern NSString *const TypeRobotWelcome;//机器人欢迎消息
extern NSString *const TypeEventQueue;//机器人欢迎消息

/// 未知消息
extern NSString *const TypeUnknown;

/** 消息类型的KEY */
extern NSString *const VideoPathKey;

extern NSString *const GXSelectEmotionKey;






/************Notification*************/

extern NSString *const GXEmotionDidSelectNotification;
extern NSString *const GXEmotionDidDeleteNotification;
extern NSString *const GXEmotionDidSendNotification;
