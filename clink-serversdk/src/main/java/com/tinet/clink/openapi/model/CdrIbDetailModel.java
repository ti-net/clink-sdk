package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 呼入通话记录明细详情返回对象
 *
 * @author huwk
 * @date 2018/09/12
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CdrIbDetailModel {

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
     * 座席响铃时间
     */
    private Long clientRingingTime;

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

    public Long getClientRingingTime() {
        return clientRingingTime;
    }

    public void setClientRingingTime(Long clientRingingTime) {
        this.clientRingingTime = clientRingingTime;
    }

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
}
