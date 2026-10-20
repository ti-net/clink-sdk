package com.tinet.clink.livechat.model;

/**
 * 渠道接入号与路由导航绑定
 */
public class ChannelIvrBindingModel {

    /**
     * 渠道类型
     */
    private String contactType;

    /**
     * 接入号ID
     */
    private String accessId;

    /**
     * 接入号名称
     */
    private String accessName;

    /**
     * 绑定的路由导航ID，未绑定时为空
     */
    private Integer ivrId;

    /**
     * 路由导航名称。未绑定，或 ivrId 为 0 时为空
     */
    private String ivrName;

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
    }

    public String getIvrName() {
        return ivrName;
    }

    public void setIvrName(String ivrName) {
        this.ivrName = ivrName;
    }
}
