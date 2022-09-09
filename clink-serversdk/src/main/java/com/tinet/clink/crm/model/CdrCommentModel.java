package com.tinet.clink.crm.model;

/**
 * 留言记录返回对象
 *
 * @author huwk
 * @date 2018/09/17
 **/
public class CdrCommentModel {

    /**
     * 通话唯一标识
     */
    private String uniqueId;

    /**
     * 客户号码
     */
    private String customerNumber;

    /**
     * 客户地区
     */
    private String customerArea;

    /**
     * 热线号码
     */
    private String hotline;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 录音时长
     */
    private Integer bridgeDuration;

    /**
     * 留言邮箱名
     */
    private String voiceMail;

    /**
     * 录音文件名
     */
    private String recordFile;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerArea() {
        return customerArea;
    }

    public void setCustomerArea(String customerArea) {
        this.customerArea = customerArea;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
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

    public Integer getBridgeDuration() {
        return bridgeDuration;
    }

    public void setBridgeDuration(Integer bridgeDuration) {
        this.bridgeDuration = bridgeDuration;
    }

    public String getVoiceMail() {
        return voiceMail;
    }

    public void setVoiceMail(String voiceMail) {
        this.voiceMail = voiceMail;
    }

    public String getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(String recordFile) {
        this.recordFile = recordFile;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
