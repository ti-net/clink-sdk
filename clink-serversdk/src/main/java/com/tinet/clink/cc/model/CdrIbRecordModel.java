package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 呼入通话记录返回对象
 *
 * @author huwk
 * @date 2018/09/05
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CdrIbRecordModel {

    /**
     * callId
     */
    private String callId;

    /**
     * 通话记录唯一标识
     */
    private String uniqueId;

    /**
     * 来电热线号码
     */
    private String hotline;

    /**
     * 客户来电号码，带区号
     */
    private String customerNumber;

    /**
     * 客户来电号码加密串
     */
    private String customerNumberEncrypt;

    /**
     * 客户来电省份
     */
    private String customerProvince;

    /**
     * 客户来电城市
     */
    private String customerCity;

    /**
     * 来电队列号
     */
    private String qno;

    /**
     * 座席号
     */
    private String cno;

    /**
     * 座席名称
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
     * 呼入类型
     */
    private String callType;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 接通时间
     */
    private Long bridgeTime;

    /**
     * 接通时长
     */
    private Integer bridgeDuration;

    /**
     * 总时长
     */
    private Integer totalDuration;

    /**
     * IVR名称
     */
    private String ivrName;

    /**
     * 接听状态
     */
    private String status;

    /**
     * 接听状态映射
     */
    private String statusCode;

    /**
     * 挂机原因 1000主通道挂机 1001非主通道挂机 1002被强拆
     */
    private String endReason;

    /**
     * 呼叫结果
     */
    private String sipCause;

    /**
     * 录音文件名
     */
    private String recordFile;

    /**
     * 自定义字段
     */
    private String userField;

    /**
     * 标记
     */
    private Integer mark;

    /**
     * 标签
     */
    private String[] tags;

    /**
     * 来电队列名称
     */
    private String qname;

    /**
     * 备注
     */
    private String markData;

    /**
     * 加入队列时间
     */
    private Long joinQueueTime;

    /**
     * 离开队列时间
     */
    private Long leaveQueueTime;

    /**
     * 排队时长
     */
    private Long ibWaitDuration;

    /**
     * 座席接起时间
     */
    private Long clientOffhookTime;

    /**
     * 语音播报时长
     */
    private Integer sayVoiceDuration;

    /**
     * 客户呼入座席振铃时长
     */
    private Integer ibRingingDuration;

    /**
     * 队列及时应答
     */
    private Integer queueAnswerInTime;

    /**
     * 是否邀评
     */
    private Integer evaluation;

    /**
     * 热线别名
     */
    private String hotlineName;

    public Integer getQueueAnswerInTime() {
        return queueAnswerInTime;
    }

    public void setQueueAnswerInTime(Integer queueAnswerInTime) {
        this.queueAnswerInTime = queueAnswerInTime;
    }

    public Long getClientRingingTime() {
        return clientRingingTime;
    }

    public void setClientRingingTime(Long clientRingingTime) {
        this.clientRingingTime = clientRingingTime;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getMarkData() {
        return markData;
    }

    public void setMarkData(String markData) {
        this.markData = markData;
    }

    public Long getJoinQueueTime() {
        return joinQueueTime;
    }

    public void setJoinQueueTime(Long joinQueueTime) {
        this.joinQueueTime = joinQueueTime;
    }

    public Long getLeaveQueueTime() {
        return leaveQueueTime;
    }

    public void setLeaveQueueTime(Long leaveQueueTime) {
        this.leaveQueueTime = leaveQueueTime;
    }

    public Long getIbWaitDuration() {
        return ibWaitDuration;
    }

    public void setIbWaitDuration(Long ibWaitDuration) {
        this.ibWaitDuration = ibWaitDuration;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumberEncrypt() {
        return customerNumberEncrypt;
    }

    public void setCustomerNumberEncrypt(String customerNumberEncrypt) {
        this.customerNumberEncrypt = customerNumberEncrypt;
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
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

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getBridgeTime() {
        return bridgeTime;
    }

    public void setBridgeTime(Long bridgeTime) {
        this.bridgeTime = bridgeTime;
    }

    public Integer getBridgeDuration() {
        return bridgeDuration;
    }

    public void setBridgeDuration(Integer bridgeDuration) {
        this.bridgeDuration = bridgeDuration;
    }

    public Integer getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getIvrName() {
        return ivrName;
    }

    public void setIvrName(String ivrName) {
        this.ivrName = ivrName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getEndReason() {
        return endReason;
    }

    public void setEndReason(String endReason) {
        this.endReason = endReason;
    }

    public String getSipCause() {
        return sipCause;
    }

    public void setSipCause(String sipCause) {
        this.sipCause = sipCause;
    }

    public String getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(String recordFile) {
        this.recordFile = recordFile;
    }

    public String getUserField() {
        return userField;
    }

    public void setUserField(String userField) {
        this.userField = userField;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }


    public Long getClientOffhookTime() {
        return clientOffhookTime;
    }

    public void setClientOffhookTime(Long clientOffhookTime) {
        this.clientOffhookTime = clientOffhookTime;
    }

    public Integer getSayVoiceDuration() {
        return sayVoiceDuration;
    }

    public void setSayVoiceDuration(Integer sayVoiceDuration) {
        this.sayVoiceDuration = sayVoiceDuration;
    }

    public Integer getIbRingingDuration() {
        return ibRingingDuration;
    }

    public void setIbRingingDuration(Integer ibRingingDuration) {
        this.ibRingingDuration = ibRingingDuration;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public String getHotlineName() {
        return hotlineName;
    }

    public void setHotlineName(String hotlineName) {
        this.hotlineName = hotlineName;
    }
}

