package com.tinet.clink.openapi.model;

import java.util.Date;

/**
 * Class for:
 * 号码申诉记录
 *
 * @author yinzk
 * @date 2021/5/27
 */
public class NumberAppeal {

    /**
     * 申诉提交时间
     */
    private Date addTime;
    /**
     * 申诉原因
     */
    private String appealReason;
    /**
     * 客户号码
     */
    private String customerNumber;
    /**
     * 申诉凭证，url 链接
     */
    private String fileName;
    /**
     * 冻结原因
     */
    private String freezeReasonName;
    /**
     * 打回原因
     */
    private String rebikeReason;
    /**
     * 申诉状态，1：通过，2：打回，3：未审核
     */
    private Integer status;

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAppealReason() {
        return appealReason;
    }

    public void setAppealReason(String appealReason) {
        this.appealReason = appealReason;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFreezeReasonName() {
        return freezeReasonName;
    }

    public void setFreezeReasonName(String freezeReasonName) {
        this.freezeReasonName = freezeReasonName;
    }

    public String getRebikeReason() {
        return rebikeReason;
    }

    public void setRebikeReason(String rebikeReason) {
        this.rebikeReason = rebikeReason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
