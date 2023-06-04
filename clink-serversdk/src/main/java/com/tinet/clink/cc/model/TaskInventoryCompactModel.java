package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; /**
 * 外呼任务明细（简版）
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskInventoryCompactModel {
    /**
     * 外呼任务清单id
     */
    private Integer inventoryId;
    /**
     * 客户号码
     */
    private String customerTel;
    /**
     * 加密后的客户号码
     */
    private String encryptTel;
    /**
     * 任务执行状态
     */
    private Integer taskStatus;
    /**
     * 任务id
     */
    private Integer taskId;
    /**
     * 客户号码
     */
    private String customerName;
    /**
     * 清单处理状态，1：已处理；null or 2：待处理；
     */
    private Integer handleStatus;

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getEncryptTel() {
        return encryptTel;
    }

    public void setEncryptTel(String encryptTel) {
        this.encryptTel = encryptTel;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }
}