//
//  TOSKitCustomInfo.h
//  TOSClientKit
//
//  Created by 言 on 2022/7/1.
//  Copyright © 2022 YanBo. All rights reserved.
//

#import <TOSClientLib/TOSClientLib.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * 快捷入口-工单留言的eventName
 */
static NSString * const TOS_EVENT_NAME_TICKET_MESSAGE_STATUS = @"kTOSTicketMessageStatus";

/**
 * 工单插件地址
 */
static NSString * const TOS_TICKET_PLUGIN_URL = @"ticketPluginUrl";

/**
 * 工单插件返回地址
 */
static NSString * const TOS_TICKET_PLUGIN_URL_RESULT = @"ticketPluginUrlResult";

/**
 * 客服留言数量提示开关（默认：true）
 */
static NSString * const TOS_COMMENT_COUNT_ENABLE = @"commentCountEnable";

/**
 * 仅查询和展示访客创建的工单（默认：false）
 */
static NSString * const TOS_VISITOR_CREATED_TICKET = @"visitorCreatedTicket";

/**
 * 没有客服留言时不展示该快捷入口（默认：false）
 */
static NSString * const TOS_NO_COMMENT_COUNT_HIDE_QUICK_ENTRY = @"noCommentCountHideQuickEntry";

/**
 * 留言未读数数量
 */
static NSString * const TOS_STAFF_COMMENT_TOTAL_COUNT = @"staffCommentTotalCount";

/**
 * 应用阶段（
 * TOSAppLicationStageType_OFF：关闭，
 * TOSAppLicationStageType_Robot：机器人，
 * TOSAppLicationStageType_Robot：人工，
 * TOSAppLicationStageType_Human：机器人和人工，默认：10）
 */
static NSString * const TOS_APPLICATION_STAGE = @"applicationStage";

// 关闭
static NSString * const TOSAppLicationStageType_OFF = @"TOSAppLicationStageType_OFF";

// 机器人
static NSString * const TOSAppLicationStageType_Robot = @"TOSAppLicationStageType_Robot";

// 人工
static NSString * const TOSAppLicationStageType_Human = @"TOSAppLicationStageType_Human";

// 机器人和人工 默认
static NSString * const TOSAppLicationStageType_Both = @"TOSAppLicationStageType_Both";


/**
 气泡的圆角类型枚举
 */
typedef NS_ENUM(NSInteger, BubbleCornerType) {
    /// 全切圆角
    BubbleCornerTypeAll             =   0,
    /// 左边上面是圆角
    BubbleCornerTypeLeftTop         =   1,
    /// 左边下面是圆角
    BubbleCornerTypeLeftBottom      =   2,
    /// 左边两个都是圆角
    BubbleCornerTypeLeft            =   3,
    /// 右边上面是圆角
    BubbleCornerTypeRightTop        =   4,
    /// 右边下面是圆角
    BubbleCornerTypeRightBottom     =   5,
    /// 右边两个都是圆角
    BubbleCornerTypeRight           =   6,
    /// 上面两个都是圆角
    BubbleCornerTypeTop             =   7,
    /// 下面两个都是圆角
    BubbleCornerTypeBottom          =   8,
    /// 左上方不是圆角
    BubbleCornerTypeNoLeftTop       =   9,
    /// 右上方不是圆角
    BubbleCornerTypeNoRightTop      =   10,
    /// 左下方不是圆角
    BubbleCornerTypeNoLeftBottom    =   11,
    /// 右下方不是圆角
    BubbleCornerTypeNoRightBottom   =   12,
    /// 不切圆角
    BubbleCornerTypeNormal          =   13,
};

/**
 在点击退出页面按钮时，满意度的弹出模式
 */
typedef NS_ENUM(NSInteger, SatisfactionShowModel) {
    /// 只在首次弹出
    SatisfactionShowModelFirstTimePopup          =   0,
    /// 每次都弹出
    SatisfactionShowModelEveryTimePopup          =   1
};


/// 正则model
@interface TOSRegularModel : NSObject

/// 正则表达式
@property (nonatomic, copy, nullable) NSString                *regular;

/// 高亮颜色
@property (nonatomic, strong, nullable) UIColor               *highlightColor;


@end

/// 快捷入口model
@interface TOSQuickEntryModel : TIMLibBaseModel

/// 快捷入口名称
@property (nonatomic, copy) NSString                *name;

/// 快捷入口值
@property (nonatomic, copy) NSString                *value;

/// 快捷事件名称
@property (nonatomic, copy, nonnull) NSString       *eventName;

/// 快捷入口事件动态配置参数集合
@property (nonatomic, strong) NSMutableDictionary          *dynamicConfigParameters;

+ (TOSQuickEntryModel *)getChatLeaveMessageStatusWithTicketPluginUrl:(NSString *)ticketPluginUrl;

@end


@interface TOSKitCustomInfo : TIMLibBaseModel

+ (TOSKitCustomInfo *)shareCustomInfo;

/// 快速入口Item的圆角弧度
@property (nonatomic, assign) CGFloat               quickEntryItem_cornerRadius;

/// 发送方气泡的颜色
@property (nonatomic, strong) UIColor               *senderBubble_backGround;
/// 发送方气泡的圆角弧度
@property (nonatomic, assign) CGFloat               senderBubble_cornerRadius;

/// 头像的圆角弧度
@property (nonatomic, assign) CGFloat               portrait_cornerRadius;

/// 聊天背景颜色
@property (nonatomic, strong) UIColor               *chat_backGround;

/// 快速入口Item的背景颜色
@property (nonatomic, strong) UIColor               *quickEntryItem_backgroundColor;

/// 接收方气泡的颜色
@property (nonatomic, strong) UIColor               *receiveBubble_backGround;
/// 接收方气泡的圆角弧度
@property (nonatomic, assign) CGFloat               receiveBubble_cornerRadius;

/// 快速入口底部的背景颜色
@property (nonatomic, strong) UIColor               *quickEntryBottom_backgroundColor;

/// 接收方字体颜色
@property (nonatomic, strong) UIColor               *receiveText_Color;

/// 发送方字体颜色
@property (nonatomic, strong) UIColor               *senderText_Color;

/// 聊天底部输入中的语音按钮控制
@property (nonatomic, assign) BOOL                  ChatBox_voiceButton_enable;

/// 聊天底部输入中文本输入框的暗文设置
@property (nonatomic, strong) NSString              *ChatBox_textview_placeholder;

/// 聊天底部背景颜色
@property (nonatomic, strong) UIColor               *ChatBox_backGroundColor;

/// 聊天底部中线条颜色
@property (nonatomic, strong) UIColor               *ChatBox_lineColor;

/// 聊天中显示的时间字体颜色
@property (nonatomic, strong) UIColor               *Chat_time_textColor;

/// 启用或关闭客服或机器人昵称的显示
@property (nonatomic, assign) BOOL                  Chat_tosRobotName_show;

/// 启用或关闭访客昵称的显示
@property (nonatomic, assign) BOOL                  Chat_visitorName_show;

/// 启用或关闭客服昵称(机器人昵称)UI区域的显示
@property (nonatomic, assign) BOOL                  Chat_tosRobotName_enable;

/// 启用或关闭访客昵称UI区域的显示
@property (nonatomic, assign) BOOL                  Chat_visitorName_enable;

/// 启用或关闭客服和机器人头像UI区域的显示
@property (nonatomic, assign) BOOL                  Chat_tosRobot_portrait_enable;

/// 启用或关闭访客头像UI区域的显示
@property (nonatomic, assign) BOOL                  Chat_visitor_portrait_enable;

/// 相册展示导航栏中的文字颜色
@property (nonatomic, strong) UIColor               *imagePicker_barItemTextColor;

/// 相册展示导航栏中的背景颜色
@property (nonatomic, strong) UIColor               *imagePicker_naviBgColor;

/// 吐司提示气泡背景颜色
@property (nonatomic, strong) UIColor               *Toast_backGroundColor;

/// 吐司提示中文字颜色
@property (nonatomic, strong) UIColor               *Toast_textColor;

/// 语音按钮中文字颜色
@property (nonatomic, strong) UIColor               *VoiceButton_textColor;

/// 语音按钮中文字高亮颜色
@property (nonatomic, strong) UIColor               *VoiceButton_highlight_textColor;

/// 商品卡片-待发送  发送按钮背景颜色
@property (nonatomic, strong) UIColor               *CommodityCard_sendBtn_backgroundColor;

/// 商品卡片-待发送  发送按钮文字颜色
@property (nonatomic, strong) UIColor               *CommodityCard_sendBtn_textColor;

/// 商品卡片-待发送  标题文字颜色
@property (nonatomic, strong) UIColor               *CommodityCard_title_textColor;

/// 商品卡片-待发送  描述文字颜色
@property (nonatomic, strong) UIColor *CommodityCard_descriptions_textColor;

/// 商品卡片-待发送  商品价格文字颜色
@property (nonatomic, strong) UIColor               *CommodityCard_price_textColor;

/// 商品卡片-详情  订单号: xxx 文字颜色
@property (nonatomic, strong) UIColor               *CommodityCardDetails_orderId_sender_textColor;
@property (nonatomic, strong) UIColor               *CommodityCardDetails_orderId_receive_textColor;

/// 商品卡片-详情  时间文字颜色
@property (nonatomic, strong) UIColor               *CommodityCardDetails_time_sender_textColor;
@property (nonatomic, strong) UIColor               *CommodityCardDetails_time_receive_textColor;

/// 商品卡片-详情  标题文字颜色
@property (nonatomic, strong) UIColor               *CommodityCardDetails_title_sender_textColor;
@property (nonatomic, strong) UIColor               *CommodityCardDetails_title_receive_textColor;

/// 商品卡片-详情  商品描述文字颜色
@property (nonatomic, strong) UIColor               *CommodityCardDetails_description_sender_textColor;
@property (nonatomic, strong) UIColor               *CommodityCardDetails_description_receive_textColor;

/// 商品卡片-详情  商品价格文字颜色
@property (nonatomic, strong) UIColor               *CommodityCardDetails_price_sender_textColor;
@property (nonatomic, strong) UIColor               *CommodityCardDetails_price_receive_textColor;

/// 商品卡片-详情  到货状态:xxx 文字颜色
@property (nonatomic, strong) UIColor               *CommodityCardDetails_transportStatus_sender_textColor;
@property (nonatomic, strong) UIColor               *CommodityCardDetails_transportStatus_receive_textColor;

/// 设置聊天页面的标题名字
@property(nonatomic, copy) NSString                 *titleName;

/// 接入号名称
@property(nonatomic, copy) NSString                 *appName;

/// 快捷入口的数据
@property (nonatomic, strong) NSArray            <TOSQuickEntryModel *> *quickEntryAllItems;

/// 商品卡片配置数据
@property (nonatomic, strong, nullable) TOSClientKitCommodityCardOption *commodityCardOption;


/// 气泡的最大宽度
@property (nonatomic, assign) CGFloat                bubbleMaxWidth;

/// 头像的大小，图像是正方形只需要设置宽度就可以了  default：40
@property (nonatomic, assign) CGFloat                headWidth;

/// 头像距屏幕边缘的距离 default：10
@property (nonatomic, assign) CGFloat                headMargin;
/// 头像距离气泡的距离 default：8.0
@property (nonatomic, assign) CGFloat                headToBubble;

/// 每条消息的间距 default：10.0
@property (nonatomic, assign) CGFloat                cellMargin;

/// 气泡的内间距 default：10.0
@property (nonatomic, assign) CGFloat                bubblePadding;

/// 聊天底部输入中的表情按钮控制 default：YES
@property (nonatomic, assign) BOOL                   chatBox_emotionButton_enable;

/// 聊天底部输入中的更多按钮控制 default：YES
@property (nonatomic, assign) BOOL                   chatBox_moreButton_enable;

/// 聊天底部的整体高度 default：56.0
@property (nonatomic, assign) CGFloat                chatBox_Height;

/// 聊天底部文本框距上下方的间距 default: 8.0
@property (nonatomic, assign) CGFloat                chatBox_textView_topAndBottomMargin;

/// 聊天底部文本框的初始高度，只读属性，想要改变该值需要配合另外两个属性 （⚠️该值的计算公式是：chatBox_Height - chatBox_textView_topAndBottomMargin*2 得出）
@property (nonatomic, assign, readonly) CGFloat      chatBox_textView_height;

/// 聊天底部最左侧的item距左侧间距 default: 10.0
@property (nonatomic, assign) CGFloat                chatBox_itemLeftPadding;

/// 聊天底部最右侧的item距右侧间距 default: 10.0
@property (nonatomic, assign) CGFloat                chatBox_itemRightPadding;

/// 聊天底部每个item之间的间距 default: 10.0
@property (nonatomic, assign) CGFloat                chatBox_itemSpacing;

/// 聊天底部的每个item距离底部的间距（去除安全区域）defalut: 14
@property (nonatomic, assign) CGFloat                chatBox_itemBottomSpacing;

/// 聊天底部的输入框字体 default: [UIFont fontWithName:@"PingFangSC-Regular" size:16.0]
@property (nonatomic, strong) UIFont                 *chatBox_textView_font;

/// 聊天底部的输入框内边距 default: UIEdgeInsetsMake(10, 10, 10, 10)
@property (nonatomic, assign) UIEdgeInsets           chatBox_textView_textContainerInset;

/// 聊天底部的输入框字体颜色 default：262626
@property (nonatomic, strong) UIColor                *chatBox_textView_textColor;

/// 聊天底部的输入框背景颜色 default：whiteColor
@property (nonatomic, strong) UIColor                *chatBox_textView_backgroundColor;

/// 聊天底部文本框的圆角值 default：8.0
@property (nonatomic, assign) CGFloat                chatBox_textView_cornerRadius;

/// 聊天底部文本框的边框颜色 default: clearColor
@property (nonatomic, strong) UIColor                *chatBox_textView_borderColor;

/// 聊天底部文本框的边框宽度 default: 0.5
@property (nonatomic, assign) CGFloat                chatBox_textView_borderWidth;

/// 聊天底部文本框的最多显示行数 default：5
@property (nonatomic, assign) NSInteger              chatBox_textView_maxRows;

/// 默认提示文字距离输入框左边的距离 default: 10.0
@property (nonatomic, assign) CGFloat                chatBox_textView_placeholderMargin;

/// 聊天底部文本框的暗文的字体颜色 default: grayColor
@property (nonatomic, strong) UIColor                *chatBox_textview_placeholderTextColor;

/// 录制语音的初始文本 default: 按住 说话
@property (nonatomic, copy) NSString                 *chatBox_talkText;

/// 录制语音的按中文本 default: 松开 结束
@property (nonatomic, copy) NSString                 *chatBox_talkHighlightedText;

/// 录制语音的按钮字体 default: [UIFont boldSystemFontOfSize:16.0f]
@property (nonatomic, strong) UIFont                 *chatBox_talkFont;

/// 录制语音的按钮边框 defalut: 0.5
@property (nonatomic, assign) CGFloat                chatBox_talk_borderWidth;

/// 录制语音的按钮边框颜色 defalut：E8E8E8
@property (nonatomic, strong) UIColor                *chatBox_talk_borderColor;
/// 输入框右侧的发送按钮开关 default: NO (发送按钮设置为YES时，chatBox_emotionButton_enable和chatBox_moreButton_enable 值需要为NO，且chatBox_sendButton_enable为YES，才会显示发送按钮)
@property (nonatomic, assign) BOOL                   chatBox_sendButton_enable;

/// 录制语音的按钮背景颜色 defalut: clearColor
@property (nonatomic, strong) UIColor                *chatBox_talk_backgroundColor;

/// 录制语音的按钮按住背景颜色 defalut: B2B2B2
@property (nonatomic, strong) UIColor                *chatBox_talk_backgroundHighlightedColor;

/// 录制语音的按钮按住字体颜色 defalut: 434343
@property (nonatomic, strong) UIColor                *chatBox_talk_fontHighlightedColor;

/// 输入框右侧的发送按钮大小 defatult: 60 : chatBox_textView_height
@property (nonatomic, assign) CGSize                 chatBox_sendButtonSize;

/// 输入框右侧的发送按钮 只需要设置按钮的内容，样式需要设置其他属性(chatBox_sendButtonSize/chatBox_sendButton_cornerRadius/chatBox_sendButton_borderColor/chatBox_sendButton_HighlightedColor)
@property (nonatomic, strong) UIButton               *chatBox_sendButton;

/// 输入框右侧的发送按钮的圆角 default: 10.0
@property (nonatomic, assign) CGFloat                chatBox_sendButton_cornerRadius;

/// 输入框右侧的发送按钮边框默认颜色，即输入框没有值时发送按钮的边框颜色 default: UIColor.grayColor
@property (nonatomic, strong) UIColor                *chatBox_sendButton_borderColor;

/// 输入框右侧的发送按钮高亮的颜色，即输入框有值时发送按钮的边框颜色 default: UIColor.blackColor
@property (nonatomic, strong) UIColor                *chatBox_sendButton_HighlightedColor;

/// 输入框右侧的发送按钮边框宽度 default: 1
@property (nonatomic, assign) CGFloat                chatBox_sendButton_borderWidth;

/// 重新发送按钮的大小 default: 20:20
@property (nonatomic, assign) CGSize                 resendButtonSize;

/// 重新发送按钮，设置后会显示这个按钮，不需要设置大小，大小由 resendButtonSize 控制。default: 红色感叹号的图片样式
@property (nonatomic, strong) UIButton               *resendButton;

/// 重新发送按钮距离气泡的间距 default: 4.0
@property (nonatomic, assign) CGFloat                resendToBubblePadding;

/**
 自定义cell的注册事件
 要在页面加载前进行赋值，key：事件名，value：对应的cell。
 示例
 @{
    @"TypeEventQueue" :  [CustomTableViewCell class]
 }
 然后在页面实现- (UITableViewCell *)customTableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath withModel:(nonnull TIMMessageModel *)model;方法
 自定义的cell需要继承于 TOSChatCustomBaseTableViewCell 
 */
@property (nonatomic, strong) NSMutableDictionary   *customCellRegister;

/// 发送侧语音按钮的语音图标距离气泡右侧的X值 default: 22.0
@property (nonatomic, assign) CGFloat               chat_send_voiceImageToBubbleRightX;

/// 发送侧语音按钮的语音时长距离气泡左侧的X值 default: 10.0
@property (nonatomic, assign) CGFloat               chat_send_voiceLabelToBubbleLeftX;

/// 发送侧语音按钮的语音图标距离气泡顶部的Y值 default: 10.0
@property (nonatomic, assign) CGFloat               chat_send_voiceImageToBubbleTop;

/// 发送侧语音按钮的语音时长距离气泡顶部的Y值 default: 10.0
@property (nonatomic, assign) CGFloat               chat_send_voiceLabelToBubbleTop;

/// 语音消息的最低宽度 default: 60
@property (nonatomic, assign) CGFloat               chat_voiceMinWidth;

/// 语音录制view，需要设置位置及大小
@property (nonatomic, strong) UIView                *chatBox_voiceRecordView;

/// 语音取消录制view，需要设置位置及大小
@property (nonatomic, strong) UIView                *chatBox_voiceCancelRecordView;

/// 语音录制时间过短的提示view，需要设置位置及大小
@property (nonatomic, strong) UIView                *chatBox_voiceRecordSoShortView;

/// 语音录制时间过短的提示view显示时间 default: 2.0
@property (nonatomic, assign) CGFloat               chatBox_voiceRecordSoShortTime;

/// 表情面板的删除按钮的默认图片
@property (nonatomic, strong) UIImage               *chatBox_emotion_deleteButton_image;

/// 表情面板的删除按钮的高亮图片
@property (nonatomic, strong) UIImage               *chatBox_emotion_deleteButton_highlightedImage;

/// 表情面板的删除按钮背景色 default：whiteColor
@property (nonatomic, strong) UIColor               *chatBox_emotion_deleteButtonBackGroundColor;

/// 表情面板的删除按钮大小 default: CGSizeMake(49, 38)
@property (nonatomic, assign) CGSize                chatBox_emotion_deleteButtonSize;

/// 删除按钮位置的 (x,y) 的偏移，默认为 CGPointZero
@property (nonatomic, assign) CGPoint               chatBox_emotion_deleteButtonOffset;

/// 表情面板的删除按钮的圆角 default: 4.0
@property (nonatomic, assign) CGFloat               chatBox_emotion_deleteButton_cornerRadius;

/// 表情面板的删除按钮的calayer
@property (nonatomic, strong) CALayer               *chatBox_emotion_deleteCALayer;

/// 表情面板的发送按钮文案 default: 发送
@property (nonatomic, copy) NSString                *chatBox_emotion_sendButton_text;

/// 表情面板的发送按钮文案 default: 发送
@property (nonatomic, copy) NSString                *chatBox_emotion_sendButton_textHighlighted;

/// 表情面板的发送按钮大小 default: CGSizeMake(49, 38)
@property (nonatomic, assign) CGSize                chatBox_emotion_sendButtonSize;

/// 表情面板的发送按钮背景色 default: 4385FF
@property (nonatomic, strong) UIColor               *chatBox_emotion_sendButtonBackGroundColor;

/// 发送按钮布局时的外边距，相对于控件右下角。仅right/bottom有效，默认为{0, 0, 16, 16}
@property(nonatomic, assign) UIEdgeInsets           chatBox_emotion_sendButtonMargins;

/// 表情面板的发送按钮的圆角 default: 4.0
@property (nonatomic, assign) CGFloat               chatBox_emotion_sendButton_cornerRadius;

/// 表情面板的发送按钮字体 default: [UIFont fontWithName:@"PingFangSC-Regular" size:14.f]
@property (nonatomic, strong) UIFont                *chatBox_emotion_sendButtonFont;

/// 表情面板的发送按钮默认的文字颜色，即输入框有值时发送按钮的文字颜色 default: UIColor.whiteColor
@property (nonatomic, strong) UIColor               *chatBox_emotion_sendButton_textColor;

/// 表情面板的发送按钮高亮的文字颜色，即输入框有值时发送按钮的文字颜色 default: UIColor.whiteColor
@property (nonatomic, strong) UIColor               *chatBox_emotion_sendButton_textHighlightedColor;

/// 表情面板的发送按钮的calayer
@property (nonatomic, strong) CALayer               *chatBox_emotion_sendCALayer;

/// 分页控件距离底部的间距，default: 22.0
@property(nonatomic, assign) CGFloat                chatBox_emotion_pageControlMarginBottom;

/// 输入区域上方的线条高度 default: 1
@property (nonatomic, assign) CGFloat               chatBox_topLineHeight;

/// 是否在删除和发送按钮下方显示表情 default: NO
@property (nonatomic, assign) BOOL                  chatBox_emotion_functionItemDisplayed;

/// 输入区域item的宽度 default: 28
@property (nonatomic, assign) CGFloat               chatBox_Item_Width;

/// 最后一条消息距离输入区域的间距 default: 0
@property (nonatomic, assign) CGFloat               lastMessage_spacing;

/// 昵称和气泡之间的间距 default: 0
@property (nonatomic, assign) CGFloat               nickNameToBubbleSpacing;

/// 气泡的圆角类型以【发送方】为标准，【接收方】自动对称。 defalut: 全切圆角 BubbleCornerTypeAll (BubbleCornerTypeNormal 时 senderBubble_cornerRadius/receiveBubble_cornerRadius无效)
@property (nonatomic, assign) BubbleCornerType      chatBubble_CornerType;

/// 系统消息的背景颜色 default: FFFFFF, 0.65
@property (nonatomic, strong) UIColor               *chatMessage_system_backgroundColor;

/// 系统消息的字体 default: [UIFont systemFontOfSize:11.0]
@property (nonatomic, strong) UIFont                *chatMessage_system_textFont;

/// 系统消息的字体颜色 default: 595959
@property (nonatomic, strong) UIColor               *chatMessage_system_textColor;

/// 系统消息的对齐方式 default: NSTextAlignmentCenter
@property (nonatomic, assign) NSTextAlignment       chatMessage_system_textAlignment;

/// 系统消息的内容背景圆角 default: 4.0
@property (nonatomic, assign) CGFloat               chatMessage_system_cornerRadius;

/// 系统消息的Label是否居中显示 default: YES （若设置为NO，就根据chatMessage_system_edgeInsets的值进行布局）
@property (nonatomic, assign) BOOL                  chatMessage_system_center;

/// 系统消息的文本控件距离屏幕上下左右间距 default: UIEdgeInsetsMake(5, 20, 5, 20)
@property (nonatomic, assign) UIEdgeInsets          chatMessage_system_edgeInsets;

/// 系统消息的文本控件上下左右内间距 default: UIEdgeInsetsMake(0, 0, 0, 0)
@property (nonatomic, assign) UIEdgeInsets          chatMessage_system_labelTextEdgeInsets;

/// 表情面板的背景颜色 default: F3F6F9
@property (nonatomic, strong) UIColor               *chatBox_emotion_backgroundColor;

/// 表情面板顶部的分割线颜色 default: E8E8E8
@property (nonatomic, strong) UIColor               *chatBox_emotion_topLineColor;

/// 表情面板顶部的分割线高度 default: 1
@property (nonatomic, assign) CGFloat               chatBox_emotion_topLineHeight;

/// 更多面板的背景颜色 default: 237, 237, 246, 1
@property (nonatomic, strong) UIColor               *chatBox_more_backgroundColor;

/// 更多面板的item图片圆角配置 default: 12.0f （最大设置28，超过28会造成图片畸形，超过28的数值按28取值）
@property (nonatomic, assign) CGFloat               chatBox_more_itemCornerRadius;

/// 更多面板的item的背景颜色 default: whiteColor
@property (nonatomic, strong) UIColor               *chatBox_more_itemBackgroundColor;

/// 更多面板的item的图片UIEdgeInsets  default: UIEdgeInsetsMake(12, 12, 12, 12)
@property (nonatomic, assign) UIEdgeInsets          chatBox_more_itemImageEdgeInsets;

/// 更多面板的item的文字颜色 default: 595959
@property (nonatomic, strong) UIColor               *chatBox_more_itemTextColor;

/// 更多面板的顶部分割线颜色 default: E8E8E8
@property (nonatomic, strong) UIColor               *chatBox_more_topLineColor;

/// 更多面板的顶部分割线高度 default: 1.0
@property (nonatomic, assign) CGFloat               chatBox_more_topLineHeight;

/// 访客昵称字体 default: [UIFont systemFontOfSize:12.0]
@property (nonatomic, strong) UIFont                *chatMessage_visitorName_font;

/// 访客昵称字体颜色 default: 595959
@property (nonatomic, strong) UIColor               *chatMessage_visitorName_textColor;

/// 机器人/座席字体 default: [UIFont systemFontOfSize:12.0]
@property (nonatomic, strong) UIFont                *chatMessage_tosRobotName_font;

/// 机器人/座席字体颜色 default: 595959
@property (nonatomic, strong) UIColor               *chatMessage_tosRobotName_textColor;

/// 访客气泡文本字体 default: [UIFont fontWithName:@"PingFangSC-Regular" size:16.0]
@property (nonatomic, strong) UIFont                *chatMessage_visitorText_font;

/// 机器人/座席字体 default: [UIFont fontWithName:@"PingFangSC-Regular" size:16.0]
@property (nonatomic, strong) UIFont                *chatMessage_tosRobotText_font;

/// 输入区域文本框的光标颜色 default: blueColor
@property (nonatomic, strong) UIColor               *chatBox_textView_tintColor;

/// 机器人组合消息标题字体 default: [UIFont fontWithName:@"PingFangSC-Regular" size:16.0]
@property (nonatomic, strong) UIFont                *chatMessage_tosRobotCombination_titleFont;

/// 机器人组合消息副标题字体颜色 default: FAAD14
@property (nonatomic, strong) UIColor               *chatMessage_tosRobotCombination_titleColor;

/// 机器人组合消息副标题字体 default: [UIFont fontWithName:@"PingFangSC-Regular" size:16.0]
@property (nonatomic, strong) UIFont                *chatMessage_tosRobotCombination_subTitleFont;

/// 机器人组合消息的字体 default: [UIFont fontWithName:@"PingFangSC-Regular" size:14.f]
@property (nonatomic, strong) UIFont                *chatMessage_tosRobotCombination_hotSubIssueTitleFont;

/// 机器人组合消息分类选项卡的字体 default: [UIFont fontWithName:@"PingFangSC-Regular" size:14.0f]
@property (nonatomic, strong) UIFont                *chatMessage_tosRobotCombination_segmentFont;

/// 机器人组合消息分类选项卡未选择的字体颜色 default: 141223
@property (nonatomic, strong) UIColor               *chatMessage_tosRobotCombination_segmentUnselectedTextColor;

/// 机器人组合消息分类选项卡的字体颜色 default: 4385FF
@property (nonatomic, strong) UIColor               *chatMessage_tosRobotCombination_segmentTextColor;

/// 机器人组合消息分类选项卡的下划线颜色 default: 000000, 0.04f
@property (nonatomic, strong) UIColor               *chatMessage_tosRobotCombination_segmentLineColor;

/// 机器人组合消息的字体颜色 default: 262626
@property (nonatomic, strong) UIColor               *chatMessage_tosRobotCombination_hotSubIssueTitleColor;

/// 机器人组合消息的间距 default: 10.0f
@property (nonatomic, assign) CGFloat               chatMessage_tosRobotCombination_hotSubIssusSpacing;

/// 机器人组合消息的换一换按钮是否展示的数量限制 default: 5 (超过5条会显示换一换按钮)
@property (nonatomic, assign) NSInteger             chatMessage_tosRobotCombination_showRefreshNumber;

/// 机器人组合消息的换一换按钮的图片 default: robotCell_hotIssueRefresh
@property (nonatomic, strong) UIImage               *chatMessage_tosRobotCombination_showRefreshImage;

/// 机器人组合消息的换一换按钮的文字内容 default: 换一换
@property (nonatomic, copy) NSString                *chatMessage_tosRobotCombination_showRefreshTitle;

/// 机器人组合消息的换一换按钮的文字颜色 default: 4385FF
@property (nonatomic, strong) UIColor               *chatMessage_tosRobotCombination_showRefreshTitleColor;

/// 机器人组合消息的换一换按钮的文字字体 default: [UIFont fontWithName:@"PingFangSC-Regular" size:12.f]
@property (nonatomic, strong) UIFont                *chatMessage_tosRobotCombination_showRefreshTitleFont;

/// 会话状态为机器人时是否隐藏语音按钮 default: NO
@property (nonatomic, assign) BOOL                   robotHiddenVoice;

/// 满意度评价的弹出样式 : 固定底部弹出
@property (nonatomic, assign, readonly) BOOL         satisfactionViewPopupMode;

/// 在点击退出页面按钮时，满意度的弹出模式
@property (nonatomic, assign) SatisfactionShowModel  satisfactionViewShowModel;

/// 列表消息中的满意度评价按钮颜色-未选中 default: 0x4385FF
@property (nonatomic, strong) UIColor                *satisfaction_evaluate_normal;

/// 列表消息中的满意度评价按钮颜色-选中 default: 0xF0F0F0
@property (nonatomic, strong) UIColor                *satisfaction_evaluate_selected;

/// 列表消息中的满意度评价按钮标题颜色-未选中 default: 0xFFFFFF
@property (nonatomic, strong) UIColor                *satisfaction_evaluate_titleColor_normal;

/// 列表消息中的满意度评价按钮标题颜色-选中 default: 0xBFBFBF
@property (nonatomic, strong) UIColor                *satisfaction_evaluate_titleColor_selected;


/// 满意度弹窗的评价提交按钮颜色-未选中 default: 0x4385FF
@property (nonatomic, strong) UIColor                *satisfaction_evaluate_submit;

/// 满意度弹窗的评价提交按钮标题颜色-未选中 default: 0xFFFFFF
@property (nonatomic, strong) UIColor                *satisfaction_evaluate_submit_titleColor;

/**
 * 电话正则
 * urlRegualr的highlightColor默认为：4385FF
 * telRegular的regular默认为：
 * @"(0\\d{2,3}-?\\d{7,8})|(\\(0\\d{2,3}\\)\\d{7,8})|1[34578]\\d{9}|400-?\\d{3}-?\\d{4}|\\+?\\d{2}1[34578]\\d{9}"
 */
@property (nonatomic, strong, nullable) TOSRegularModel     *telRegular;

/// 订单号正则默认为null
@property (nonatomic, strong, nullable) TOSRegularModel     *orderNumberRegular;

/**
 * 链接正则
 * urlRegualr的highlightColor默认为：4385FF
 * urlRegular的regular默认为：
 * @"(((http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(www.[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(((http[s]{0,1}|ftp)://|)((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?))"
 */
@property (nonatomic, strong, nullable) TOSRegularModel     *urlRegular;

/// 只使用本地上传头像
@property (nonatomic, assign) BOOL                   enableLocalAvatar;

/// 访客默认头像
@property (nonatomic, strong) UIImage                *visitorDefaultAvatar;

/// 机器人默认头像
@property (nonatomic, strong) UIImage                *robotDefaultAvatar;

/// 客服默认头像
@property (nonatomic, strong) UIImage                *customerServiceDefaultAvatar;

/// 系统默认头像
@property (nonatomic, strong) UIImage                *systemDefaultAvatar;

/// 是否开启点赞点踩功能    YES：开启   NO：关闭  默认为NO
@property (nonatomic, assign) BOOL isOpenHelpfulFeature;

/// 是否展示点踩内容输入框
@property (nonatomic, assign) BOOL isShowUnHelpfulContent;

/// 是否必填点踩内容
@property (nonatomic, assign) BOOL isRequiredUnHelpfulContent;

/// 设置点踩输入框提示安暗文
@property (nonatomic, copy)   NSString               *setUnHelpfulContentHint;

/// 设置点踩标签
@property (nonatomic, strong) NSArray    <NSString *>*setUnHelpfulTagList;

@end

NS_ASSUME_NONNULL_END
