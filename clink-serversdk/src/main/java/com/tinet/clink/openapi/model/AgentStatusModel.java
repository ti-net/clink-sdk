package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 座席状态监控返回对象
 *
 * @author wangll
 * @date 2019/09/11
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentStatusModel {
    /**
     * 座席号
     */
    private String cno;

    /**
     * 座席名
     */
    private String clientName;

    /**
     * 绑定类型
     */
    private Integer bindType;

    /**
     * 绑定电话
     */
    private String bindTel;

    /**
     * 主叫号码，即来电客户号码
     */
    private String customerNumber;
    /**
     * 客户号码加密串
     */
    private String customerNumberEncrypt;
    /**
     * 置忙类型
     */
    private Integer pauseType;
    /**
     * 置忙状态
     */
    private String pauseDescription;
    /**
     * 座席状态 (空闲、置忙(具体置忙原因)、通话、振铃、整理、外呼中)
     */
    private String agentStatus;

    /**
     * 座席状态详情
     */
    private String agentStatusDetail;

    /**
     * 来电接听数
     */
    private Integer incomingCallCount;

    /**
     * 座席来电接听次数
     */
    private Integer bridgeCallCount;

    /**
     * 队列来电接听数
     */
    private Integer queueIncomingCallCount;

    /**
     * 状态时长
     */
    private Long stateDuration;

    /**
     * 登录时长
     */
    private Long loginDuration;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getBindType() {
        return bindType;
    }

    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    public String getBindTel() {
        return bindTel;
    }

    public void setBindTel(String bindTel) {
        this.bindTel = bindTel;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Integer getPauseType() {
        return pauseType;
    }

    public void setPauseType(Integer pauseType) {
        this.pauseType = pauseType;
    }

    public String getAgentStatusDetail() {
        return agentStatusDetail;
    }

    public void setAgentStatusDetail(String agentStatusDetail) {
        this.agentStatusDetail = agentStatusDetail;
    }

    public String getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(String agentStatus) {
        this.agentStatus = agentStatus;
    }

    public Integer getIncomingCallCount() {
        return incomingCallCount;
    }

    public void setIncomingCallCount(Integer incomingCallCount) {
        this.incomingCallCount = incomingCallCount;
    }

    public Integer getQueueIncomingCallCount() {
        return queueIncomingCallCount;
    }

    public void setQueueIncomingCallCount(Integer queueIncomingCallCount) {
        this.queueIncomingCallCount = queueIncomingCallCount;
    }

    public Long getStateDuration() {
        return stateDuration;
    }

    public void setStateDuration(Long stateDuration) {
        this.stateDuration = stateDuration;
    }

    public Long getLoginDuration() {
        return loginDuration;
    }

    public void setLoginDuration(Long loginDuration) {
        this.loginDuration = loginDuration;
    }

    public String getPauseDescription() {
        return pauseDescription;
    }

    public void setPauseDescription(String pauseDescription) {
        this.pauseDescription = pauseDescription;
    }

    public String getCustomerNumberEncrypt() {
        return customerNumberEncrypt;
    }

    public void setCustomerNumberEncrypt(String customerNumberEncrypt) {
        this.customerNumberEncrypt = customerNumberEncrypt;
    }

    public Integer getBridgeCallCount() {
        return bridgeCallCount;
    }

    public void setBridgeCallCount(Integer bridgeCallCount) {
        this.bridgeCallCount = bridgeCallCount;
    }
}
