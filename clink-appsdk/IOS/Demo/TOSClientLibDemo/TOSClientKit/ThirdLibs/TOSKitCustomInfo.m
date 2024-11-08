//
//  TOSKitCustomInfo.m
//  TOSClientKit
//
//  Created by 赵言 on 2022/7/1.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import "TOSKitCustomInfo.h"
#import "TIMConstants.h"
#import "kitUtils.h"


@implementation TOSRegularModel


@end

@implementation TOSQuickEntryModel

+ (TOSQuickEntryModel *)getChatLeaveMessageStatusWithTicketPluginUrl:(NSString *)ticketPluginUrl {
    
    TOSQuickEntryModel *quickEntryModel = [[TOSQuickEntryModel alloc] init];
    quickEntryModel.name = @"留言状态更新";
    quickEntryModel.value = @"留言状态更新";
    quickEntryModel.eventName = TOS_EVENT_NAME_TICKET_MESSAGE_STATUS;
    quickEntryModel.dynamicConfigParameters = [@{
        TOS_TICKET_PLUGIN_URL: ticketPluginUrl,
        TOS_COMMENT_COUNT_ENABLE: [NSNumber numberWithBool:true],
        TOS_VISITOR_CREATED_TICKET: [NSNumber numberWithBool:false],
        TOS_NO_COMMENT_COUNT_HIDE_QUICK_ENTRY: [NSNumber numberWithBool:false],
        TOS_STAFF_COMMENT_TOTAL_COUNT: @"0",
        TOS_APPLICATION_STAGE: TOSAppLicationStageType_Both,
        TOS_TICKET_PLUGIN_URL_RESULT: @"",
    } mutableCopy];
    
    return quickEntryModel;
}

@end


static TOSKitCustomInfo *customInfo = nil;

@interface TOSKitCustomInfo ()

@property (nonatomic, assign, readwrite) CGFloat                chatBox_textView_height;

@end


@implementation TOSKitCustomInfo

+ (TOSKitCustomInfo *)shareCustomInfo {
    return [[self alloc] init];
}

- (instancetype)init {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        customInfo = [super init];
        
        NSDictionary *customDic = [kitUtils getPlistFile:@"customInfo"];
        
        NSString *receiveBubble = customDic[@"ReceiveBubble_backGround"];
        CGFloat receiveBubbleA = [customDic[@"ReceiveBubble_backGroundA"] doubleValue];
        customInfo.receiveBubble_backGround = [self colorWithHexString:receiveBubble alpha:receiveBubbleA];
        customInfo.receiveBubble_cornerRadius = [customDic[@"ReceiveBubble_cornerRadius"] doubleValue];
        NSString *receiveText = customDic[@"ReceiveText_Color"];
        customInfo.receiveText_Color = [self colorWithHexString:receiveText alpha:1];
        
        
        NSString *senderBubble = customDic[@"SenderBubble_backGround"];
        CGFloat senderBubbleA = [customDic[@"SenderBubble_backGroundA"] doubleValue];
        customInfo.senderBubble_backGround = [self colorWithHexString:senderBubble alpha:senderBubbleA];
        customInfo.senderBubble_cornerRadius = [customDic[@"SenderBubble_cornerRadius"] doubleValue];
        NSString *senderText = customDic[@"SenderText_Color"];
        customInfo.senderText_Color = [self colorWithHexString:senderText alpha:1];
        
        
        customInfo.portrait_cornerRadius = [customDic[@"Portrait_cornerRadius"] doubleValue];
        
        
        NSString *chatBackGround = customDic[@"Chat_backGround"];
        CGFloat chatBackGroundA = [customDic[@"Chat_backGroundA"] doubleValue];
        customInfo.chat_backGround = [self colorWithHexString:chatBackGround alpha:chatBackGroundA];
        
        
        NSString *quickEntryItem = customDic[@"QuickEntryItem_backgroundColor"];
        CGFloat quickEntryItemA = [customDic[@"QuickEntryItem_backgroundColorA"] doubleValue];
        customInfo.quickEntryItem_backgroundColor = [self colorWithHexString:quickEntryItem alpha:quickEntryItemA];
        customInfo.quickEntryItem_cornerRadius = [customDic[@"QuickEntryItem_cornerRadius"] doubleValue];
        
        
        NSString *quickEntryBottom = customDic[@"QuickEntryBottom_backgroundColor"];
        CGFloat quickEntryBottomA = [customDic[@"QuickEntryBottom_backgroundColorA"] doubleValue];
        customInfo.quickEntryBottom_backgroundColor = [self colorWithHexString:quickEntryBottom alpha:quickEntryBottomA];
        
        customInfo.ChatBox_voiceButton_enable = [customDic[@"ChatBox_voiceButton_enable"] boolValue];
        customInfo.ChatBox_textview_placeholder = customDic[@"ChatBox_textview_placeholder"];
        
        NSString *chatTimeTextColor = customDic[@"Chat_time_textColor"];
        CGFloat chatTimeTextColorA = [customDic[@"Chat_time_textColorA"] doubleValue];
        customInfo.Chat_time_textColor = [self colorWithHexString:chatTimeTextColor alpha:chatTimeTextColorA];
        
        NSString *imagePickerBarItemTextColor = customDic[@"imagePicker_barItemTextColor"];
        customInfo.imagePicker_barItemTextColor = [self colorWithHexString:imagePickerBarItemTextColor alpha:1.0];
        
        NSString *imagePickeraviBgColor = customDic[@"imagePicker_naviBgColor"];
        customInfo.imagePicker_naviBgColor = [self colorWithHexString:imagePickeraviBgColor alpha:1.0];
        
        NSString *ChatBoxBackGroundColor = customDic[@"ChatBox_backGroundColor"];
        CGFloat ChatBoxBackGroundColorA = [customDic[@"ChatBox_backGroundColorA"] doubleValue];
        customInfo.ChatBox_backGroundColor = [self colorWithHexString:ChatBoxBackGroundColor alpha:ChatBoxBackGroundColorA];
        
        NSString *ChatBoxLineColor = customDic[@"ChatBox_lineColor"];
        customInfo.ChatBox_lineColor = [self colorWithHexString:ChatBoxLineColor alpha:1.0];
        
        NSString *ToastBackGroundColor = customDic[@"Toast_backGroundColor"];
        CGFloat ToastBackGroundColorA = [customDic[@"Toast_backGroundColorA"] doubleValue];
        customInfo.Toast_backGroundColor = [self colorWithHexString:ToastBackGroundColor alpha:ToastBackGroundColorA];
        
        NSString *ToastTextColor = customDic[@"Toast_textColor"];
        customInfo.Toast_textColor = [self colorWithHexString:ToastTextColor alpha:1.0];
        
        customInfo.Chat_tosRobotName_show = [customDic[@"Chat_tosRobotName_show"] boolValue];
        customInfo.Chat_visitorName_show = [customDic[@"Chat_visitorName_show"] boolValue];
        customInfo.Chat_tosRobotName_enable = [customDic[@"Chat_tosRobotName_enable"] boolValue];
        customInfo.Chat_visitorName_enable = [customDic[@"Chat_visitorName_enable"] boolValue];
        customInfo.Chat_tosRobot_portrait_enable = [customDic[@"Chat_tosRobot_portrait_enable"] boolValue];
        customInfo.Chat_visitor_portrait_enable = [customDic[@"Chat_visitor_portrait_enable"] boolValue];
        
        NSString *VoiceButtonTextColor = customDic[@"VoiceButton_textColor"];
        CGFloat VoiceButtonTextColorA = [customDic[@"VoiceButton_textColorA"] doubleValue];
        customInfo.VoiceButton_textColor = [self colorWithHexString:VoiceButtonTextColor alpha:VoiceButtonTextColorA];
        
        NSString *VoiceButtonighlightTextColor = customDic[@"VoiceButton_highlight_textColor"];
        CGFloat VoiceButtonHighlightTextColorA = [customDic[@"VoiceButton_highlight_textColorA"] doubleValue];
        customInfo.VoiceButton_highlight_textColor = [self colorWithHexString:VoiceButtonighlightTextColor alpha:VoiceButtonHighlightTextColorA];
        
        NSString *CommodCardsendBtnBackColor = customDic[@"CommodityCard_sendBtn_backgroundColor"];
        customInfo.CommodityCard_sendBtn_backgroundColor = [self colorWithHexString:CommodCardsendBtnBackColor alpha:1.0];
        
        NSString *CommodCardsendBtnTextColor = customDic[@"CommodityCard_sendBtn_textColor"];
        customInfo.CommodityCard_sendBtn_textColor = [self colorWithHexString:CommodCardsendBtnTextColor alpha:1.0];
        
        NSString *CommodityCard_title_textColor = customDic[@"CommodityCard_title_textColor"];
        customInfo.CommodityCard_title_textColor = [self colorWithHexString:CommodityCard_title_textColor alpha:1.0];
        
        NSString *CommodityCard_descriptions_textColor = customDic[@"CommodityCard_descriptions_textColor"];
        customInfo.CommodityCard_descriptions_textColor = [self colorWithHexString:CommodityCard_descriptions_textColor alpha:1.f];
        
        NSString *CommodityCard_price_textColor = customDic[@"CommodityCard_price_textColor"];
        customInfo.CommodityCard_price_textColor = [self colorWithHexString:CommodityCard_price_textColor alpha:1.0];
    
        NSString *CommodCardOrderIdSenderTextColor = customDic[@"CommodityCardDetails_orderId_sender_textColor"];
        customInfo.CommodityCardDetails_orderId_sender_textColor = [self colorWithHexString:CommodCardOrderIdSenderTextColor alpha:1.0];
        
        NSString *CommodCardOrderIdReceiveTextColor = customDic[@"CommodityCardDetails_orderId_receive_textColor"];
        customInfo.CommodityCardDetails_orderId_receive_textColor = [self colorWithHexString:CommodCardOrderIdReceiveTextColor alpha:1.0];
        
        NSString *CommodCardTimeSenderTextColor = customDic[@"CommodityCardDetails_time_sender_textColor"];
        customInfo.CommodityCardDetails_time_sender_textColor = [self colorWithHexString:CommodCardTimeSenderTextColor alpha:1.0];
        
        NSString *CommodCardTimeReceiveTextColor = customDic[@"CommodityCardDetails_time_receive_textColor"];
        customInfo.CommodityCardDetails_time_receive_textColor = [self colorWithHexString:CommodCardTimeReceiveTextColor alpha:1.0];
        
        NSString *CommodCardTitleSenderTextColor = customDic[@"CommodityCardDetails_title_sender_textColor"];
        customInfo.CommodityCardDetails_title_sender_textColor = [self colorWithHexString:CommodCardTitleSenderTextColor alpha:1.0];
        
        NSString *CommodCardTitleReceiveTextColor = customDic[@"CommodityCardDetails_title_receive_textColor"];
        customInfo.CommodityCardDetails_title_receive_textColor = [self colorWithHexString:CommodCardTitleReceiveTextColor alpha:1.0];
        
        NSString *CommodCardDescriptionSenderTextColor = customDic[@"CommodityCardDetails_description_sender_textColor"];
        customInfo.CommodityCardDetails_description_sender_textColor = [self colorWithHexString:CommodCardDescriptionSenderTextColor alpha:1.0];
        
        NSString *CommodCardDescriptionReceiveTextColor = customDic[@"CommodityCardDetails_description_receive_textColor"];
        customInfo.CommodityCardDetails_description_receive_textColor = [self colorWithHexString:CommodCardDescriptionReceiveTextColor alpha:1.0];
        
        NSString *CommodCardPriceSenderTextColor = customDic[@"CommodityCardDetails_price_sender_textColor"];
        customInfo.CommodityCardDetails_price_sender_textColor = [self colorWithHexString:CommodCardPriceSenderTextColor alpha:1.0];
        
        NSString *CommodCardPriceReceiveTextColor = customDic[@"CommodityCardDetails_price_receive_textColor"];
        customInfo.CommodityCardDetails_price_receive_textColor = [self colorWithHexString:CommodCardPriceReceiveTextColor alpha:1.0];
        
        NSString *CommodCardTransStatusSenderTextColor = customDic[@"CommodityCardDetails_transportStatus_sender_textColor"];
        customInfo.CommodityCardDetails_transportStatus_sender_textColor = [self colorWithHexString:CommodCardTransStatusSenderTextColor alpha:1.0];
        
        NSString *CommodCardTransStatusReceiveTextColor = customDic[@"CommodityCardDetails_transportStatus_receive_textColor"];
        customInfo.CommodityCardDetails_transportStatus_receive_textColor = [self colorWithHexString:CommodCardTransStatusReceiveTextColor alpha:1.0];
        
        NSLog(@"customDic = %@",customDic);
        
        self.headWidth = 40.0f;
        self.headMargin = 10.0f;
        self.headToBubble = 8.0f;
        self.cellMargin = 10.0f;
        self.bubblePadding = 10.0f;
//        self.ChatBox_voiceButton_enable = NO;
        self.chatBox_emotionButton_enable = YES;
        self.chatBox_moreButton_enable = YES;
        self.chatBox_textView_topAndBottomMargin = 8.0f;
        self.chatBox_Height = 57.0f;
        self.chatBox_textView_height = self.chatBox_Height - self.chatBox_textView_topAndBottomMargin*2;
        self.chatBox_itemLeftPadding = 10.0f;
        self.chatBox_itemRightPadding = 10.0f;
        self.chatBox_itemSpacing = 10.0f;
        self.chatBox_itemBottomSpacing = 14.0f;
        self.chatBox_textView_font = [UIFont fontWithName:@"PingFangSC-Regular" size:16.0f];
        self.chatBox_textView_textContainerInset = UIEdgeInsetsMake(10, 10, 10, 10);
        self.chatBox_textView_textColor = TOSHexColor(0x262626);
        self.chatBox_textView_backgroundColor = UIColor.whiteColor;
        self.chatBox_textView_cornerRadius = 8.0f;
        self.chatBox_textView_borderColor = UIColor.clearColor;
        self.chatBox_textView_borderWidth = 0.5;
        self.chatBox_textView_maxRows = 5;
//        self.ChatBox_textview_placeholder = @"买石灰街车站的海鸥 山水禽兽与年少一梦 买太平湖底陈年水墨 哥本哈根的童年传说";
        self.chatBox_textView_placeholderMargin = 10.0f;
        self.chatBox_textview_placeholderTextColor = UIColor.grayColor;
        self.chatBox_talkText = @"按住 说话";
        self.chatBox_talkHighlightedText = @"松开 结束";
        self.chatBox_talkFont = [UIFont boldSystemFontOfSize:16.0f];
        self.chatBox_talk_borderWidth = 0.5f;
        self.chatBox_talk_borderColor = [self colorWithHexString:@"E8E8E8" alpha:1.0f];
        self.chatBox_sendButton_enable = NO;
        self.chatBox_sendButtonSize = CGSizeMake(60, self.chatBox_textView_height);
        self.chatBox_sendButton_cornerRadius = 10.0f;
        self.chatBox_sendButton_borderColor = UIColor.grayColor;
        self.chatBox_sendButton_HighlightedColor = UIColor.blackColor;
        self.chatBox_sendButton_borderWidth = 1.0f;
        UIButton * sendBtn = [[UIButton alloc] init];
        [sendBtn setTitle:@"发送" forState:(UIControlStateNormal)];
        [sendBtn setTitleColor:self.chatBox_sendButton_borderColor forState:(UIControlStateNormal)];
        [sendBtn setTitleColor:self.chatBox_sendButton_HighlightedColor forState:(UIControlStateSelected)];
        sendBtn.titleLabel.font = [UIFont systemFontOfSize:13.0];
        sendBtn.layer.cornerRadius = self.chatBox_sendButton_cornerRadius;
        sendBtn.layer.borderColor = self.chatBox_sendButton_borderColor.CGColor;
        sendBtn.layer.borderWidth = self.chatBox_sendButton_borderWidth;
        self.chatBox_sendButton = sendBtn;
        self.resendButtonSize = CGSizeMake(20.0f, 20.0f);
        UIButton * resendBtn = [[UIButton alloc] init];
        [resendBtn setImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"resendMessage"]] forState:UIControlStateNormal];
        self.resendButton = resendBtn;
        self.resendToBubblePadding = 4.0f;
        
        CGFloat headToView    = self.headMargin > UIScreen.mainScreen.bounds.size.width/4 ? UIScreen.mainScreen.bounds.size.width/4 : self.headMargin;
        self.bubbleMaxWidth = UIScreen.mainScreen.bounds.size.width - self.headWidth - self.resendButtonSize.width - headToView - self.headToBubble - 100;
        
//        self.chat_receive_voiceImageToBubbleLeftX = 10.0f;
//        self.chat_receive_voiceLabelToBubbleRightX = 10.0f;
        self.chat_send_voiceImageToBubbleRightX = 22.0f;
        self.chat_send_voiceLabelToBubbleLeftX = 10.0f;
        self.chat_send_voiceImageToBubbleTop = 10.0f;
        self.chat_send_voiceLabelToBubbleTop = 10.0f;
        self.chat_voiceMinWidth = 60.0;
        
        self.chatBox_talk_backgroundColor = UIColor.clearColor;
        self.chatBox_talk_backgroundHighlightedColor = [self colorWithHexString:@"B2B2B2" alpha:1.0f];
        self.chatBox_talk_fontHighlightedColor = self.VoiceButton_textColor;
        
        self.chatBox_voiceRecordSoShortTime = 2.0f;
        
        self.chatBox_emotion_deleteButtonOffset = CGPointZero;
        self.chatBox_emotion_deleteButtonBackGroundColor = UIColor.whiteColor;
        self.chatBox_emotion_deleteButton_image = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_delete"]];
        self.chatBox_emotion_deleteButton_highlightedImage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"chatBox_delete"]];
        self.chatBox_emotion_deleteButtonSize = CGSizeMake(49, 38);
        self.chatBox_emotion_sendButton_text = @"发送";
        self.chatBox_emotion_sendButton_textHighlighted = @"发送";
        self.chatBox_emotion_sendButtonFont = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        self.chatBox_emotion_sendButtonSize = CGSizeMake(49, 38);
        self.chatBox_emotion_sendButtonBackGroundColor = TOSHexColor(0x4385FF);
        self.chatBox_emotion_sendButtonMargins = UIEdgeInsetsMake(0, 0, 16, 16);
        self.chatBox_emotion_sendButton_cornerRadius = 4.0f;
        self.chatBox_emotion_sendButton_textColor = UIColor.whiteColor;
        self.chatBox_emotion_sendButton_textHighlightedColor = UIColor.whiteColor;
        self.chatBox_emotion_pageControlMarginBottom = 22.0f;
        
        self.chatBox_topLineHeight = 1.0f;
        
        self.chatBox_emotion_functionItemDisplayed = NO;
        
        self.chatBox_Item_Width = 28.0f;
        self.lastMessage_spacing = 0.0f;
        
        self.nickNameToBubbleSpacing = 0.0f;
        self.chatBubble_CornerType = BubbleCornerTypeAll;
        
        self.chatMessage_system_backgroundColor = TOSHexAColor(0xFFFFFF,0.65);
        self.chatMessage_system_edgeInsets = UIEdgeInsetsMake(5, 20, 5, 20);
        self.chatMessage_system_textFont = [UIFont systemFontOfSize:11.0];
        self.chatMessage_system_textColor = TOSHexAColor(0x595959, 1.0);
        self.chatMessage_system_textAlignment = NSTextAlignmentCenter;
        self.chatMessage_system_cornerRadius = 4.0;
        self.chatMessage_system_labelTextEdgeInsets = UIEdgeInsetsMake(0, 0, 0, 0);
        self.chatMessage_system_center = YES;
        
        self.chatBox_emotion_backgroundColor = TOSHexColor(0xF3F6F9);
        self.chatBox_emotion_topLineColor = TOSHexColor(0xE8E8E8);
        self.chatBox_emotion_topLineHeight = 1.0f;
        self.chatBox_more_backgroundColor = TOSColor(237, 237, 246);
        self.chatBox_more_itemCornerRadius = 12.0f;
        self.chatBox_more_itemBackgroundColor = UIColor.whiteColor;
        self.chatBox_more_itemImageEdgeInsets = UIEdgeInsetsMake(12, 12, 12, 12);
        self.chatBox_more_itemTextColor = TOSHexColor(0x595959);
        self.chatBox_more_topLineColor = TOSHexColor(0xE8E8E8);
        self.chatBox_more_topLineHeight = 1.0f;
        
        self.chatMessage_visitorName_font = [UIFont systemFontOfSize:12.0];
        self.chatMessage_visitorName_textColor = TOSHexAColor(0x595959, 1.0);
        self.chatMessage_tosRobotName_font = [UIFont systemFontOfSize:12.0];
        self.chatMessage_tosRobotName_textColor = TOSHexAColor(0x595959, 1.0);
        
        self.chatMessage_visitorText_font = [UIFont fontWithName:@"PingFangSC-Regular" size:16.0];
        self.chatMessage_tosRobotText_font = [UIFont fontWithName:@"PingFangSC-Regular" size:16.0];
        
        self.chatBox_textView_tintColor = UIColor.blueColor;
        
        self.chatMessage_tosRobotCombination_titleFont = [UIFont fontWithName:@"PingFangSC-Regular" size:16.0];
        self.chatMessage_tosRobotCombination_titleColor = TOSHexColor(0xFAAD14);
        self.chatMessage_tosRobotCombination_subTitleFont = [UIFont fontWithName:@"PingFangSC-Regular" size:16.0];
        self.chatMessage_tosRobotCombination_hotSubIssueTitleFont = [UIFont fontWithName:@"PingFangSC-Regular" size:14.f];
        self.chatMessage_tosRobotCombination_hotSubIssueTitleColor = TOSHexColor(0x262626);
        self.chatMessage_tosRobotCombination_hotSubIssusSpacing = 10.0f;
        
        self.chatMessage_tosRobotCombination_segmentFont = [UIFont fontWithName:@"PingFangSC-Regular" size:14.0f];
        self.chatMessage_tosRobotCombination_segmentUnselectedTextColor = TOSHexColor(0x141223);
        self.chatMessage_tosRobotCombination_segmentTextColor = TOSHexColor(0x4385FF);
        self.chatMessage_tosRobotCombination_segmentLineColor = TOSHexAColor(0x000000, .04f);
        self.chatMessage_tosRobotCombination_showRefreshNumber = 5;
        
        self.chatMessage_tosRobotCombination_showRefreshImage = [UIImage imageNamed:[NSString stringWithFormat:@"%@/%@",FRAMEWORKS_BUNDLE_PATH,@"robotCell_hotIssueRefresh"]];
        self.chatMessage_tosRobotCombination_showRefreshTitle = @"换一换";
        self.chatMessage_tosRobotCombination_showRefreshTitleColor = TOSHexColor(0x4385FF);
        self.chatMessage_tosRobotCombination_showRefreshTitleFont = [UIFont fontWithName:@"PingFangSC-Regular" size:12.f];
        
        self.robotHiddenVoice = NO;
        
        self.satisfactionViewShowModel = SatisfactionShowModelFirstTimePopup;
        
        self.satisfaction_evaluate_normal = TOSHexColor(0x4385FF);
        self.satisfaction_evaluate_selected = TOSHexColor(0xF0F0F0);
        self.satisfaction_evaluate_titleColor_normal = TOSHexColor(0xFFFFFF);
        self.satisfaction_evaluate_titleColor_selected = TOSHexColor(0xBFBFBF);
        
        self.satisfaction_evaluate_submit = TOSHexColor(0x4385FF);
        self.satisfaction_evaluate_submit_titleColor = TOSHexColor(0xFFFFFF);
        
        /// 电话正则model
        TOSRegularModel * telRegular = [[TOSRegularModel alloc] init];
        /// 电话正则表达式（⚠️⚠️⚠️暂定，需要确定最终的默认正则表达式）
        telRegular.regular = @"(0\\d{2,3}-?\\d{7,8})|(\\(0\\d{2,3}\\)\\d{7,8})|1[34578]\\d{9}|400-?\\d{3}-?\\d{4}|\\+?\\d{2}1[34578]\\d{9}";
        /// 电话匹配到的高亮颜色
        telRegular.highlightColor = TOSHexColor(0x4385FF);
        self.telRegular = telRegular;
        // 订单号正则默认为null
        self.orderNumberRegular = nil;
        
        /// 超链接的正则表达式
        TOSRegularModel * urlRegular = [[TOSRegularModel alloc] init];
        /// 超链接正则表达式（⚠️⚠️⚠️暂定，需要确定最终的默认正则表达式）
        urlRegular.regular = @"(((http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(www.[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(((http[s]{0,1}|ftp)://|)((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?))";
        /// 下面是安卓端的，ftp/www开头的匹配不到，存疑是否跟安卓端对齐
//        urlRegular.regular = @"((http[s]{0,1})://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
        urlRegular.highlightColor = TOSHexColor(0x4385FF);
        self.urlRegular = urlRegular;
        
        self.enableLocalAvatar = NO;
        
        /// 访客默认头像
        self.visitorDefaultAvatar = [UIImage imageNamed:[NSString stringWithFormat:@"%@/defaultAvatar_visitor",FRAMEWORKS_BUNDLE_PATH]];

        /// 机器人默认头像
        self.robotDefaultAvatar = [UIImage imageNamed:[NSString stringWithFormat:@"%@/defaultAvatar_robot",FRAMEWORKS_BUNDLE_PATH]];

        /// 客服默认头像
        self.customerServiceDefaultAvatar = [UIImage imageNamed:[NSString stringWithFormat:@"%@/defaultAvatar_customerService",FRAMEWORKS_BUNDLE_PATH]];
        
        /// 系统默认头像
        self.systemDefaultAvatar = [UIImage imageNamed:[NSString stringWithFormat:@"%@/defaultAvatar_customerService",FRAMEWORKS_BUNDLE_PATH]];
        
        self.isOpenHelpfulFeature = NO;
        
        /// 是否展示点踩内容输入框
        self.isShowUnHelpfulContent = YES;

        /// 是否必填点踩内容
        self.isRequiredUnHelpfulContent = YES;

        /// 设置点踩输入框提示安暗文
        self.setUnHelpfulContentHint = @"感谢评价，您可以填写具体原因，我们会努力改进";

        /// 设置点踩标签
        self.setUnHelpfulTagList = @[];
    });
    return customInfo;
}

- (void)setEnableLocalAvatar:(BOOL)enableLocalAvatar {
    _enableLocalAvatar = enableLocalAvatar;
}


//固定底部弹出 不支持设置其他模式
- (BOOL)satisfactionViewPopupMode{
    return YES;
}
- (void)setChatBox_Height:(CGFloat)chatBox_Height {
    _chatBox_Height = chatBox_Height;
    
    self.chatBox_textView_height = chatBox_Height - self.chatBox_textView_topAndBottomMargin*2;
}

- (void)setChatBox_textView_topAndBottomMargin:(CGFloat)chatBox_textView_topAndBottomMargin {
    _chatBox_textView_topAndBottomMargin = chatBox_textView_topAndBottomMargin;
    
    self.chatBox_textView_height = self.chatBox_Height - chatBox_textView_topAndBottomMargin*2;
}

- (void)setChatBox_sendButton_cornerRadius:(CGFloat)chatBox_sendButton_cornerRadius {
    _chatBox_sendButton_cornerRadius = chatBox_sendButton_cornerRadius;
    
    self.chatBox_sendButton.layer.cornerRadius = chatBox_sendButton_cornerRadius;
}

- (void)setResendButtonSize:(CGSize)resendButtonSize {
    _resendButtonSize = resendButtonSize;
    
    CGFloat headToView    = self.headMargin > UIScreen.mainScreen.bounds.size.width/4 ? UIScreen.mainScreen.bounds.size.width/4 : self.headMargin;
    self.bubbleMaxWidth = UIScreen.mainScreen.bounds.size.width - self.headWidth - resendButtonSize.width - headToView - self.headToBubble;
}

- (void)setChatBox_more_itemCornerRadius:(CGFloat)chatBox_more_itemCornerRadius {
    if (chatBox_more_itemCornerRadius > 28.0) {
        chatBox_more_itemCornerRadius = 28.0;
    }
    _chatBox_more_itemCornerRadius = chatBox_more_itemCornerRadius;
    
}



-  (NSMutableDictionary *)customCellRegister {
    if (!_customCellRegister) {
        _customCellRegister = [[NSMutableDictionary alloc] init];
    }
    return _customCellRegister;
}
               
- (UIColor *)colorWithHexString:(NSString *)colorStr alpha:(CGFloat)alpha {
    NSString *cString = [[colorStr stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]] uppercaseString];
        
    NSRange range;
    range.location = 0;
    range.length = 2;
    NSString *rString = [cString substringWithRange:range];
    
    range.location = 2;
    NSString *gString = [cString substringWithRange:range];
    
    range.location = 4;
    NSString *bString = [cString substringWithRange:range];
    
    
    unsigned int r, g, b;
    [[NSScanner scannerWithString:rString] scanHexInt:&r];
    [[NSScanner scannerWithString:gString] scanHexInt:&g];
    [[NSScanner scannerWithString:bString] scanHexInt:&b];
    
    return TOSAColor(r, g, b, alpha);
}

+ (instancetype)allocWithZone:(struct _NSZone *)zone {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        customInfo = [super allocWithZone:zone];
    });
    return customInfo;
}

- (id)copyWithZone:(NSZone *)zone {
    return customInfo;
}

- (id)mutableCopyWithZone:(NSZone *)zone {
    return customInfo;
}

@end
