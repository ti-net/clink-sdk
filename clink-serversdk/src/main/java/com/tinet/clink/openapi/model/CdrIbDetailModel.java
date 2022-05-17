package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 呼入通话记录明细详情返回对象
 *
 * @author huwk
 * @date 2018/09/12
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CdrIbDetailModel {

    /**
     * 通话记录详情唯一标识
     */
    private String uniqueId;

    /**
     * 通话记录主通道唯一标识
     */
    private String mainUniqueId;

    /**
     * 座席号
     */
    private String cno;

    /**
     * 座席姓名
     */
    private String clientName;

    /**
     * 座席电话
     */
    private String clientNumber;

    /**
     * 接起时间
     */
    private Long startTime;

    /**
     * 接听时间
     */
    private Long answerTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 通话时长
     */
    private Integer totalDuration;

    /**
     * 呼叫类型
     */
    private String callType;

    /**
     * 呼叫结果
     */
    private String status;

    /**
     * 呼叫情况
     */
    private String sipCause;

    /**
     * 是否开启主叫记忆
     */
    private String remember;

    /**
     * 队列号
     */
    private String qno;

    /**
     * 咨询开始时间
     */
    private Long consultStartTime;

    /**
     * 咨询接通时间
     */
    private Long consultLinkTime;

    /**
     * 咨询结束时间
     */
    private Long consultEndTime;

    /**
     * 咨询接回时间
     */
    private Long consultUnconsultTime;

    /**
     * 咨询三方时间
     */
    private Long consultThreewayTime;

    /**
     * 咨询转移时间
     */
    private Long consultTransferTime;

    /**
     * 被咨询座席振铃时长
     */
    private Integer consultRingingTime;

    /**
     * 咨询座席双方通话时长
     */
    private Integer consultBridgeTime;

    /**
     * 座席与客户双方通话时长
     */
    private Integer clientCustomerBridgeTime;

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

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

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSipCause() {
        return sipCause;
    }

    public void setSipCause(String sipCause) {
        this.sipCause = sipCause;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public Long getConsultStartTime() {
        return consultStartTime;
    }

    public void setConsultStartTime(Long consultStartTime) {
        this.consultStartTime = consultStartTime;
    }

    public Long getConsultLinkTime() {
        return consultLinkTime;
    }

    public void setConsultLinkTime(Long consultLinkTime) {
        this.consultLinkTime = consultLinkTime;
    }

    public Long getConsultEndTime() {
        return consultEndTime;
    }

    public void setConsultEndTime(Long consultEndTime) {
        this.consultEndTime = consultEndTime;
    }

    public Long getConsultUnconsultTime() {
        return consultUnconsultTime;
    }

    public void setConsultUnconsultTime(Long consultUnconsultTime) {
        this.consultUnconsultTime = consultUnconsultTime;
    }

    public Long getConsultThreewayTime() {
        return consultThreewayTime;
    }

    public void setConsultThreewayTime(Long consultThreewayTime) {
        this.consultThreewayTime = consultThreewayTime;
    }

    public Long getConsultTransferTime() {
        return consultTransferTime;
    }

    public void setConsultTransferTime(Long consultTransferTime) {
        this.consultTransferTime = consultTransferTime;
    }

    public Integer getConsultRingingTime() {
        return consultRingingTime;
    }

    public void setConsultRingingTime(Integer consultRingingTime) {
        this.consultRingingTime = consultRingingTime;
    }

    public Integer getConsultBridgeTime() {
        return consultBridgeTime;
    }

    public void setConsultBridgeTime(Integer consultBridgeTime) {
        this.consultBridgeTime = consultBridgeTime;
    }

    public Integer getClientCustomerBridgeTime() {
        return clientCustomerBridgeTime;
    }

    public void setClientCustomerBridgeTime(Integer clientCustomerBridgeTime) {
        this.clientCustomerBridgeTime = clientCustomerBridgeTime;
    }
}
