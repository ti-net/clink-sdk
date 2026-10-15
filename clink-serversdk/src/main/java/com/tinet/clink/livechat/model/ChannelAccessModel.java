package com.tinet.clink.livechat.model;

/**
 * 渠道接入号视图
 */
public class ChannelAccessModel {

    private Integer contactType;
    private String accessId;
    private String name;
    private Integer status;
    private Integer ivrId;
    /**
     * 渠道控台实体
     */
    private Object detail;
    /**
     * 微信客服、企微客服的会话配置
     */
    private Object session;

    public Integer getContactType() {
        return contactType;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    public Object getSession() {
        return session;
    }

    public void setSession(Object session) {
        this.session = session;
    }
}
