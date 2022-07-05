package com.tinet.tosclientkitdemo.bean;

import java.io.Serializable;

/**
 * @ProjectName: TOSClientKitDemo
 * @ClassName: ThemePropertyBean
 * @Author: zhangping
 * @CreateDate: 2022/7/4 14:38
 * @Description: 描述说明
 */
public class ThemePropertyBean implements Serializable {
    private String chatBackground;//聊天背景
    private int portraitCornerRadius;//头像圆角弧度
    private String senderBubbleBackground;//发送者气泡颜色
    private int SenderBubbleRadius;//发送者气泡圆角弧度
    private String receiveBubbleBackground;//接收者气泡颜色
    private int receiveBubbleRadius;//接收者气泡圆角弧度

    public ThemePropertyBean(String chatBackground, int portraitCornerRadius, String senderBubbleBackground, int senderBubbleRadius, String receiveBubbleBackground, int receiveBubbleRadius) {
        this.chatBackground = chatBackground;
        this.portraitCornerRadius = portraitCornerRadius;
        this.senderBubbleBackground = senderBubbleBackground;
        SenderBubbleRadius = senderBubbleRadius;
        this.receiveBubbleBackground = receiveBubbleBackground;
        this.receiveBubbleRadius = receiveBubbleRadius;
    }

    public String getChatBackground() {
        return chatBackground;
    }

    public void setChatBackground(String chatBackground) {
        this.chatBackground = chatBackground;
    }

    public int getPortraitCornerRadius() {
        return portraitCornerRadius;
    }

    public void setPortraitCornerRadius(int portraitCornerRadius) {
        this.portraitCornerRadius = portraitCornerRadius;
    }

    public String getSenderBubbleBackground() {
        return senderBubbleBackground;
    }

    public void setSenderBubbleBackground(String senderBubbleBackground) {
        this.senderBubbleBackground = senderBubbleBackground;
    }

    public int getSenderBubbleRadius() {
        return SenderBubbleRadius;
    }

    public void setSenderBubbleRadius(int senderBubbleRadius) {
        SenderBubbleRadius = senderBubbleRadius;
    }

    public String getReceiveBubbleBackground() {
        return receiveBubbleBackground;
    }

    public void setReceiveBubbleBackground(String receiveBubbleBackground) {
        this.receiveBubbleBackground = receiveBubbleBackground;
    }

    public int getReceiveBubbleRadius() {
        return receiveBubbleRadius;
    }

    public void setReceiveBubbleRadius(int receiveBubbleRadius) {
        this.receiveBubbleRadius = receiveBubbleRadius;
    }
}
