package com.tinet.tosclientkitdemo.bean;

import java.io.Serializable;

/**
 * @ProjectName: TOSClientKitDemo
 * @ClassName: ThemeItemBean
 * @Author: zhangping
 * @CreateDate: 2022/7/4 14:29
 * @Description: 描述说明
 */
public class CustomItemBean implements Serializable {

    public static final int CUSTOM_ITEM_TYPE_SEPARATE = 0;

    public static final int CUSTOM_ITEM_TYPE_GROUP_TITLE = 1;

    public static final int CUSTOM_ITEM_TYPE_CONTENT_VIEW = 2;


    public static final int CUSTOM_CONTENT_TYPE_CHOOSE_PAGE = 0;

    public static final int CUSTOM_CONTENT_TYPE_SWITCH = 1;

    public static final int CUSTOM_CONTENT_TYPE_CHOOSE_DIALOG = 2;


    private int viewType;

    private String title;

    private String tagName;

    private String strValue;

    private String remark;

    private boolean boolValue;

    private int contentType;

    private ThemePropertyBean themePropertyBean;

    public CustomItemBean(int viewType) {
        this.viewType = viewType;
    }

    public CustomItemBean(int viewType, String title) {
        this.viewType = viewType;
        this.title = title;
    }

    public CustomItemBean(int viewType, String title, String tagName, int contentType) {
        this.viewType = viewType;
        this.title = title;
        this.tagName = tagName;
        this.contentType = contentType;
    }

    public CustomItemBean(int viewType, String title, String tagName, int contentType, String strValue) {
        this.viewType = viewType;
        this.title = title;
        this.tagName = tagName;
        this.contentType = contentType;
        this.strValue = strValue;
    }

    public CustomItemBean(int viewType, String title, String tagName, int contentType, boolean boolValue) {
        this.viewType = viewType;
        this.title = title;
        this.tagName = tagName;
        this.contentType = contentType;
        this.boolValue = boolValue;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isBoolValue() {
        return boolValue;
    }

    public void setBoolValue(boolean boolValue) {
        this.boolValue = boolValue;
    }

    public ThemePropertyBean getThemePropertyBean() {
        return themePropertyBean;
    }

    public void setThemePropertyBean(ThemePropertyBean themePropertyBean) {
        this.themePropertyBean = themePropertyBean;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }
}
