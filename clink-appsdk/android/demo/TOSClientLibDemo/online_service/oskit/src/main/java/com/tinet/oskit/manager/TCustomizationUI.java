package com.tinet.oskit.manager;

import java.io.Serializable;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TCustomizationUI
 * @Author: zhangping
 * @CreateDate: 2022/8/1 10:28
 * @Description: 描述说明
 */
public class TCustomizationUI implements Serializable {

    // : 2022/8/1 命名规则，将colors或者dimen文件内id名去除ti前缀及下划线，改为驼峰命名即可

    // <!--  ++++++++++++++ 聊天背景设置 ++++++++++++++  -->
    //聊天背景颜色
    public int sessionBackgroundColor;


    // <!--  ++++++++++++++ 聊天消息样式设置 ++++++++++++++  -->
    //发送端气泡背景
    public int sendBubbleBackground;
    //接收端气泡背景
    public int receiveBubbleBackground;
    //发送端气泡背景颜色
    public int sendBubbleBackgroundColor;
    //接收端气泡背景颜色
    public int receiveBubbleBackgroundColor;
    //时间字体文字颜色
    public int msgTimeColor;
    //头像圆角弧度
    public int chatAvatarRadius;

    // <!--  ++++++++++++++  输入栏设置 ++++++++++++++  -->
    //输入区域背景颜色
    public int inputAreaBgColor;
    //输入区域按住录音按钮文字颜色
    public int inputAreaVoicePressTextColor;
    //会话页面输入框hint文案
    public String inputBoxHintText;

    // <!--  ++++++++++++++ 快捷入口设置 ++++++++++++++  -->


    // <!--  ++++++++++++++ 商品卡片设置 ++++++++++++++  -->

    // <!--  ++++++++++++++ 图像昵称显示开关 ++++++++++++++  -->
    //访客昵称
    public Boolean showVisitorNickname = true;
    //访客头像
    public Boolean showVisitAvatar = true;
    //客服、机器人昵称
    public Boolean showAgentRobotNickname = true;
    //客服、机器人头像
    public Boolean showAgentRobotAvatar = true;
    //语音按钮
    public Boolean showVoiceButton = true;
}


//public class  {
//
//    // <!--  ++++++++++++++ 聊天背景设置 ++++++++++++++  -->
//
//    // <!--  ++++++++++++++ 聊天消息样式设置 ++++++++++++++  -->
//    // <!-- 颜色  -->
//    //    //发送端超链接文字颜色
////    public int sendSuperLineTextColor;
//    //    //接收端超链接文字颜色
////    public int receiveSuperLineTextColor;
//    //    //猜你所想文字颜色
////    public int guessTextColor;
////    //排队文字颜色
////    public int lineUpTextColor;
////    //会话提示字体颜色
////    public int tipsTextColor;
////    //会话提示气泡颜色
////    public int tipsBubbleBgColor;
////    //富文本视频播放器的背景
////    public int richVideoBgColor;
////    //富文本视频播放器进度条的背景色
////    public int richVideoProgressBgColor;
////    //富文本视频播放器进度条的前景色
////    public int richVideoForegroundColor;
//接收端文字颜色
//    public int receiveMsgTextColor;
//发送端文字颜色
//    public int sendMsgTextColor;
//
//    // <!-- 尺寸  -->
//
//
//    // <!--  ++++++++++++++  输入栏设置 ++++++++++++++  -->
//    // <!-- 颜色  -->
//    //    //聊天输入框文字颜色
////    public int inputBoxTextColor;
////    //聊天输入框边框线颜色
////    public int inputBoxBorderColor;
////    //聊天输入框背景色
////    public int inputBoxBgColor;
////    //输入区域按住录音按钮文字颜色
////    public int inputAreaVoicePressTextColor;
////    //输入区域按住录音按钮背景颜色
////    public int inputAreaVoicePressBgColor;
////    //输入区发送按钮背景颜色
////    public int sendBtnBgColor;
////    //输入区发送按钮字体颜色
////    public int sendBtnTextColor;
////    //拓展面板条目背景颜色
////    public int sessionFuncItemBgColor;
////    //扩展面板条目文字颜色
////    public int funcItemTextColor;
//    // <!-- 尺寸  -->
//
//
//    // <!--  ++++++++++++++ 快捷入口设置 ++++++++++++++  -->
//    // <!-- 颜色  -->
////    //快捷入口标签字体颜色
////    public int quickEntryTextColor;
////    //快捷入口标签背景色
////    public int entryBgColor;
////    //快捷入口底部背景色
////    public int quickEntryRvBgColor;
//    // <!-- 尺寸  -->
//
//
//    // <!--  ++++++++++++++ 商品卡片设置 ++++++++++++++  -->
//    // <!-- 颜色  -->
////    //商品卡片一发送按钮背景色
////    public int commodityCardBgColor;
////    //商品卡片一发送文字颜色
////    public int commodityCardColor;
////    //聊天接受商品卡片分割线颜色
////    public int msgCardReceiveDividingLineColor;
////    //聊天发送商品卡片分割线颜色
////    public int msgCardSendDividingLineColor;
////    //聊天接受商品卡片描述颜色
////    public int msgCardReceiveTextColor;
////    //聊天发送商品卡片描述颜色
////    public int msgCardSendTextColor;
////    //聊天文件名颜色
////    public int msgReceiveFilenameTextColor;
//    // <!-- 尺寸  -->
//
//
//}
