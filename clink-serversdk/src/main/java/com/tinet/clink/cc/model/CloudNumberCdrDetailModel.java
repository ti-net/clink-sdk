package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 云手机通话记录详情
 *
 * @author Tinet-yinzk
 * @date 2024/03/12
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudNumberCdrDetailModel {


    /**
     * 话单id
     */
    private String uniqueId;
    /**
     * 原始话单id
     */
    private String originUniqueId;
    /**
     * 座席工号
     */
    private String cno;
    /**
     * ib\ob
     */
    private String callType;
    /**
     * 座席 工作号 （云手机号）/绑定号码
     */
    private String agentNumber;
    /**
     * 客户号码
     */
    private String customerNumber;
    /**
     * 客户号码加密串
     */
    private String customerNumberEncrypt;
    /**
     * 客户所属省份
     */
    private String customerProvince;
    /**
     * 客户所属城市
     */
    private String customerCity;
    /**
     * 云号码模式 1-4 依次对应：工作号（实体卡）、虚拟工作卡、两端呼、RTC
     */
    private Integer cloudNumberMode;
    /**
     * 虚拟号
     */
    private String xnumber;
    /**
     * 开始时间 （单位：s）
     */
    private Long startTime;
    /**
     * 结束时间（单位：s）
     */
    private Long endTime;
    /**
     * 结束原因
     */
    private Integer endReason;
    /**
     * 接听状态；1-接通，0-未接通
     */
    private Integer bridgeStatus;
    /**
     * 接听时间（单位：s）
     */
    private Long bridgeTime;
    /**
     * 通话时间，单位：s
     */
    private Integer bridgeDuration;
    /**
     * 总时间，单位：s
     */
    private Integer totalDuration;
    /**
     * 外显号
     */
    private String clid;
    /**
     * 录音文件
     */
    private String recordFile;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getOriginUniqueId() {
        return originUniqueId;
    }

    public void setOriginUniqueId(String originUniqueId) {
        this.originUniqueId = originUniqueId;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
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

    public Integer getCloudNumberMode() {
        return cloudNumberMode;
    }

    public void setCloudNumberMode(Integer cloudNumberMode) {
        this.cloudNumberMode = cloudNumberMode;
    }

    public String getXnumber() {
        return xnumber;
    }

    public void setXnumber(String xnumber) {
        this.xnumber = xnumber;
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

    public Integer getEndReason() {
        return endReason;
    }

    public void setEndReason(Integer endReason) {
        this.endReason = endReason;
    }

    public Integer getBridgeStatus() {
        return bridgeStatus;
    }

    public void setBridgeStatus(Integer bridgeStatus) {
        this.bridgeStatus = bridgeStatus;
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

    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
    }

    public String getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(String recordFile) {
        this.recordFile = recordFile;
    }
}


