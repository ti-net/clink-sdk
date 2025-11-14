package com.tinet.clink.livechat.model;

/**
 * 在线客服-窗口配置
 *
 * @author lj
 * @date 2023/9/19
 */
public class ChatWindowsSetting {

    /**
     * 窗口图标
     */
    private String logo;

    /**
     * 窗口标题
     */
    private String title;

    /**
     * 转人工按钮
     */
    private Integer artificialEnabled;

    /**
     * 访客端公告
     */
    private AnnouncementSetting announcement;

    /**
     * 客服/机器人头像 0：全部头像关闭(默认)，1：全部头像开启
     */
    private Integer avatarEnable;

    /**
     * 客服昵称展示0：关闭，1：开启
     */
    private Integer nickNameEnable;

    /**
     * 机器人昵称展示0：关闭，1：开启
     */
    private Integer robotNickNameEnable;

    /**
     * 座席工号显示，1开启 0关闭
     */
    private Integer cnoEnabled;

    /**
     * 访客头像展示 0：关闭，1：开启
     */
    private Integer visitorAvatarEnabled;

    /**
     * 访客端字体大小  0默认  1大
     */
    private Integer fontSize;

    /**
     * 访客端链接开关配置
     */
    private Integer visitorLink;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getArtificialEnabled() {
        return artificialEnabled;
    }

    public void setArtificialEnabled(Integer artificialEnabled) {
        this.artificialEnabled = artificialEnabled;
    }

    public AnnouncementSetting getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(AnnouncementSetting announcement) {
        this.announcement = announcement;
    }

    public Integer getAvatarEnable() {
        return avatarEnable;
    }

    public void setAvatarEnable(Integer avatarEnable) {
        this.avatarEnable = avatarEnable;
    }

    public Integer getNickNameEnable() {
        return nickNameEnable;
    }

    public void setNickNameEnable(Integer nickNameEnable) {
        this.nickNameEnable = nickNameEnable;
    }

    public Integer getRobotNickNameEnable() {
        return robotNickNameEnable;
    }

    public void setRobotNickNameEnable(Integer robotNickNameEnable) {
        this.robotNickNameEnable = robotNickNameEnable;
    }

    public Integer getCnoEnabled() {
        return cnoEnabled;
    }

    public void setCnoEnabled(Integer cnoEnabled) {
        this.cnoEnabled = cnoEnabled;
    }

    public Integer getVisitorAvatarEnabled() {
        return visitorAvatarEnabled;
    }

    public void setVisitorAvatarEnabled(Integer visitorAvatarEnabled) {
        this.visitorAvatarEnabled = visitorAvatarEnabled;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getVisitorLink() {
        return visitorLink;
    }

    public void setVisitorLink(Integer visitorLink) {
        this.visitorLink = visitorLink;
    }
}