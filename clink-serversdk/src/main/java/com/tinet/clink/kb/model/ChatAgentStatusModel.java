package com.tinet.clink.kb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 在线客服 队列面板座席对象
 *
 * @author dengsx
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatAgentStatusModel {
    /**
     * 座席号
     */
    private String cno;
    /**
     * 座席名
     */
    private String name;
    /**
     * 当前状态
     */
    private int status;
    /**
     * 当前状态持续时长
     */
    private int statusDuration;

    /**
     * 座席状态描述
     */
    private String statusDescription;

    /**
     * 实时会话数
     */
    private Integer bridgedCount;

    /**
     * 会话上限数
     */
    private Integer limitSessionCount;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatusDuration() {
        return statusDuration;
    }

    public void setStatusDuration(int statusDuration) {
        this.statusDuration = statusDuration;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Integer getBridgedCount() {
        return bridgedCount;
    }

    public void setBridgedCount(Integer bridgedCount) {
        this.bridgedCount = bridgedCount;
    }

    public Integer getLimitSessionCount() {
        return limitSessionCount;
    }

    public void setLimitSessionCount(Integer limitSessionCount) {
        this.limitSessionCount = limitSessionCount;
    }
}
