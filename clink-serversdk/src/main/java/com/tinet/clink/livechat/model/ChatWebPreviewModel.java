package com.tinet.clink.livechat.model;

/**
 * 网页接入预览地址
 */
public class ChatWebPreviewModel {

    private String accessId;
    private String webUrl;
    private String appUrl;
    private String centerAppUrl;

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getCenterAppUrl() {
        return centerAppUrl;
    }

    public void setCenterAppUrl(String centerAppUrl) {
        this.centerAppUrl = centerAppUrl;
    }
}
