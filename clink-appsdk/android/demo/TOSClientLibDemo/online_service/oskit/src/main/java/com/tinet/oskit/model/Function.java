package com.tinet.oskit.model;

import android.graphics.drawable.Drawable;

import androidx.annotation.IntDef;

/**
 * @ProjectName: TIMSDK
 * @ClassName: Function
 * @Author: liuzr
 * @CreateDate: 2021-08-24 15:15
 * @Description: 更多功能定义
 */
public class Function {

    /**
     * 发送图片
     */
    public static final int SEND_IMAGE = 100;

    /**
     * 发送视频
     */
    public static final int SEND_VIDEO = 101;

    /**
     * 发送文件
     */
    public static final int SEND_FILE = 102;

    /**
     * 结束会话
     */
    public static final int CHAT_OVER = 103;

    /**
     * 转人工
     */
    public static final int TO_ONLINE = 104;

    /**
     * 系统定义的
     * 主要包括图片、视频和文件
     */
    public static final int TYPE_SYSTEM = 1;

    /**
     * 用户自定义的
     */
    public static final int TYPE_USER = 2;

    @IntDef({TYPE_SYSTEM, TYPE_USER})
    public @interface TYPE {
    }

    /**
     * 限用系统定义的
     */
    private @TYPE
    int type = TYPE_USER;

    public Function(@TYPE int type, int typeId) {
        this.type = type;
        this.typeId = typeId;
    }

    public Function(Drawable logo, String title) {
        this.type = TYPE_USER;
        this.logo = logo;
        this.title = title;
    }

    public Function(int typeId, Drawable logo, String title) {
        this.type = TYPE_SYSTEM;
        this.typeId = typeId;
        this.logo = logo;
        this.title = title;
    }

    /**
     * 如果是系统定义的，则此处必填，
     * 用户定义的，无需填写
     */
    private int typeId = -1;

    /**
     * 图片
     */
    private Drawable logo;

    /**
     * 标题
     */
    private String title;

    public Drawable getLogo() {
        return logo;
    }

    public void setLogo(Drawable logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
