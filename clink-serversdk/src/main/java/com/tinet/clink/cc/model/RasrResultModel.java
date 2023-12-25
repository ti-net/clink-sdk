package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * @author Chenjf
 * @date 2020/9/28 15:16
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class RasrResultModel {
    /**
     * 唯一标识
     */
    private String uniqueId;
    /**
     * 关联通话记录的标识
     */
    private String mainUniqueId;
    /**
     * 客户号码
     */
    private String customerNumber;

    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;
    /**
     * 企业id
     */
    private Integer enterpriseId;
    /**
     * 转写内容
     */
    private List<RasrDialogModel> rasrDialogs;
    /**
     * 扩展字段
     */
    private List<Map<String, Object>> additions;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
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
    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public List<RasrDialogModel> getRasrDialogs() {
        return rasrDialogs;
    }

    public void setRasrDialogs(List<RasrDialogModel> rasrDialogs) {
        this.rasrDialogs = rasrDialogs;
    }

    public List<Map<String, Object>> getAdditions() {
        return additions;
    }

    public void setAdditions(List<Map<String, Object>> additions) {
        this.additions = additions;
    }

}
