//
//  ICMessageConst.m
//  XZ_WeChat
//
//  Created by 赵言 on 16/3/17.
//  Copyright © 2016年 gxz All rights reserved.
//

#import "ICMessageConst.h"
#import <UIKit/UIKit.h>

/************Const*************/
CGFloat const HEIGHT_CHATBOXVIEW = 258.f;





/************Event*************/

NSString *const GXHotIssueSendMessageEventName   = @"GXHotIssueSendMessageEventName";

NSString *const GXRouterEventVoiceTapEventName   = @"GXRouterEventVoiceTapEventName";

NSString *const GXRouterEventCombinationVoiceTapEventName   = @"GXRouterEventCombinationVoiceTapEventName";

NSString *const GXRouterEventCombinationFileTapEventName   = @"GXRouterEventCombinationFileTapEventName";

NSString *const GXRouterEventImageTapEventName   = @"GXRouterEventImageTapEventName";
NSString *const GXRobotCombinationHotIssueCellRefreshEventName   = @"GXRobotCombinationHotIssueCellRefreshEventName";
NSString *const GXRobotPopViewController   = @"GXRobotPopViewController";
NSString *const GXRouterEventImageRejectedTapEventName   = @"GXRouterEventImageRejectedTapEventName";
NSString *const GXRouterEventImageApprovedTapEventName   = @"GXRouterEventImageApprovedTapEventName";
NSString *const GXRouterEventTextUrlTapEventName =
    @"GXRouterEventTextUrlTapEventName";
NSString *const GXRouterEventMenuTapEventName    =
    @"GXRouterEventMenuTapEventName";
NSString *const GXRouterEventVideoTapEventName   =
    @"GXRouterEventVideoTapEventName";
NSString *const GXRouterEventShareTapEvent       =
    @"GXRouterEventShareTapEvent";

NSString *const GXRouterEventVideoRecordExit     =
    @"GXRouterEventVideoRecordExit";
NSString *const GXRouterEventVideoRecordCancel   =
    @"GXRouterEventVideoRecordCancel";
NSString *const GXRouterEventVideoRecordFinish   = @"GXRouterEventVideoRecordFinish";
NSString *const GXRouterEventVideoRecordStart    =
    @"GXRouterEventVideoRecordStart";
NSString *const GXRouterEventURLSkip             =
    @"GXRouterEventURLSkip";
NSString *const GXRouterEventSatisfactionPopupView             =
    @"GXRouterEventSatisfactionPopupView";
NSString *const GXRouterEventScanFile            = @"GXRouterEventScanFile";


NSString *const TinetRouterClickEventUrl                 = @"TinetRouterClickEventUrl";

NSString *const TinetRouterClickEventMiniProgramCard     = @"TinetRouterClickEventMiniProgramCard";

NSString *const TinetRouterClickEventLogisticsCard     = @"TinetRouterClickEventLogisticsCard";

NSString *const TinetRouterClickEventKnowledge                 = @"TinetRouterClickEventKnowledge";
NSString *const TinetRouterClickEventRobotFormIntents                 = @"TinetRouterClickEventRobotFormIntents";

NSString *const TinetRouterClickEventOrderNumber  = @"TinetRouterClickEventOrderNumber";

NSString *const TinetRouterClickEventPhone            = @"TinetRouterClickEventPhone";


NSString *const TinetRouterSenderCommodityCardEventUrl                 = @"TinetRouterSenderCommodityCardEventUrl";

/// 底部代发送的商品卡片发送操作
NSString *const TinetRouterBeSendCommodityCardEventUrl                 = @"TinetRouterBeSendCommodityCardEventUrl";

NSString *const TinetRouterClickCommodityCardEvent                 = @"TinetRouterClickCommodityCardEvent";






/************Name*************/
NSString *const RouterEventGetSendTextMessage      = @"GXRouterEventGetSendTextMessage";

/// 文本类型消息中附加热点问题的回传数据
NSString *const RouterEventGetSendTextMessageKnowledge   = @"RouterEventGetSendTextMessageKnowledge";
NSString *const RouterEventGetSendTextMessageRobotFormIntents   = @"RouterEventGetSendTextMessageRobotFormIntents";

NSString *const MessageKey      = @"GXMessageKey";
NSString *const VoiceIcon       = @"GXVoiceIcon";
NSString *const RedView         = @"GXRedView";
NSString *const TypeSystem      = @"GXSystem";
NSString *const TypeCommodityCard = @"GXCommodityCard";
NSString *const TypeCommodityCardDetails = @"GXCommodityCardDetails";
NSString *const TypeSmallProgramCard  = @"GXSmallProgramCard";
NSString *const TypeLogisticsCard     = @"GXLogisticsCard";
NSString *const TypeText        = @"GXText";
NSString *const TypeSatisfactionPopup        = @"SatisfactionPopup";
NSString *const TypeRevoke      = @"GXRevoke";
NSString *const TypeUnsupport   = @"GXUnsupport";
NSString *const TypeVoice       = @"GXVoice";
NSString *const TypePic         = @"GXPic";
NSString *const TypeTextTag     = @"GXTextTag";
NSString *const TypeVideo       = @"GXVideo";
NSString *const TypeFile        = @"GXFile";
NSString *const TypePicText     = @"GXPicText";
NSString *const TypeCustom      = @"GXCustom";
NSString *const TypeCustomFile  = @"GXCustomFile";
NSString *const TypeInvestigation = @"GXInvestigation";                 // 满意度评价
NSString *const GXRichText  = @"GXRichText";

NSString *const TySystomCombination      = @"GXSystomCombination";
NSString *const TypeRobotCombination      = @"GXRobotCombination";
NSString *const TypeRobotCombinationList      = @"TypeRobotCombinationList";

NSString *const TypeRobotSelectCombination      = @"GXRobotSelectCombination";
NSString *const TypeRobotWelcome      = @"GXRobotWelcome";

NSString *const TypeEventQueue      = @"TypeEventQueue";

/// 未知消息类型
NSString *const TypeUnknown         = @"TypeUnknown";

NSString *const VideoPathKey    = @"VideoPathKey";

NSString *const GXSelectEmotionKey              =
    @"GXSelectEmotionKey";









/************Notification*************/

NSString *const GXEmotionDidSelectNotification   =
    @"GXEmotionDidSelectNotification";
NSString *const GXEmotionDidDeleteNotification   =
    @"GXEmotionDidDeleteNotification";
NSString *const GXEmotionDidSendNotification     =
    @"GXEmotionDidSendNotification";

