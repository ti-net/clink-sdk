package com.tinet.clink.livechat.model;

import java.util.Date;

/**
 * 数据推送配置
 */
public class ChatHttpPushModel {

    private Integer id;

    private Integer enterpriseId;

    private String url;

    private Integer pushNotice;

    private ChatHttpPushVariableModel[] variable;

    private ChatHttpPushParameterModel[] parameter;

    private Integer timeout;

    private Integer retry;

    private Integer type;

    /**
     * 0 POST，1 GET
     */
    private Integer method;

    /**
     * 0 停用，1 启用
     */
    private Integer status;

    private String contentType;

    /**
     * 0 API 推送，1 智能体推送
     */
    private Integer pushTarget;

    private String agentId;

    private String agentName;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPushNotice() {
        return pushNotice;
    }

    public void setPushNotice(Integer pushNotice) {
        this.pushNotice = pushNotice;
    }

    public ChatHttpPushVariableModel[] getVariable() {
        return variable;
    }

    public void setVariable(ChatHttpPushVariableModel[] variable) {
        this.variable = variable;
    }

    public ChatHttpPushParameterModel[] getParameter() {
        return parameter;
    }

    public void setParameter(ChatHttpPushParameterModel[] parameter) {
        this.parameter = parameter;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getPushTarget() {
        return pushTarget;
    }

    public void setPushTarget(Integer pushTarget) {
        this.pushTarget = pushTarget;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
