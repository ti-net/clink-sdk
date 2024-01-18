package com.tinet.clink.huanxin.model;

import java.util.List;

public class IMRegisterUserRespModel {

    private String action; //请求方式

    private String application; //系统内为应用生成的唯一标识，开发者无需关心。

    private String path; // 请求路径，属于请求 URL 的一部分，开发者无需关注。

    private String uri; //请求 URL，开发者无需关注。

    private List<IMUserModel> entities;

    private Long timestamp;  //HTTP 响应的 Unix 时间戳，单位为毫秒。

    private Integer duration;// 从发送 HTTP 请求到响应的时长, 单位为毫秒。

    private String organization; //即 org_name，即时通讯服务分配给每个企业（组织）的唯一标识。你可以通过控制台获取该字段。

    private String applicationName; //即 app_name，你在环信即时通讯云控制台注册项目时填入的应用名称。

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<IMUserModel> getEntities() {
        return entities;
    }

    public void setEntities(List<IMUserModel> entities) {
        this.entities = entities;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
