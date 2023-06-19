package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; /**
 * Class for:
 * 外呼任务明细详情
 * @author : Tinet-yinzk
 * @date : 2023/6/4 23:14
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskInventoryDetailModel {
    /**
     * id
     */
    private Integer inventoryId;


    /**
     * 外呼任务id
     */
    private Integer taskId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户号码
     */
    private String customerTel;

    /**
     * 加密客户号码
     */
    private String encryptTel;
    /**
     * 分配坐席姓名(工号)回显
     */
    private String assignClient;

    /**
     * 最近呼叫时间
     */
    private Long lastContactTime;

    /**
     * 接听状态(最后一次呼叫为准)，0：未执行；1：座席未接听；2：客户未接听；3：双方接听；
     */
    private Integer status = 0;
    /**
     * 处理状态，1：已处理；2：待处理；
     */
    private Integer handleStatus;

    /**
     * 处理状态详情
     */
    private String handleStatusDetail;
    /**
     * 呼叫次数
     */
    private Integer callTimes;
    /**
     * 备注
     */
    private String remark;
    /**
     * 自定义字段
     */
    private CustomizeField[] customize;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 最后修改时间
     */
    private Long updateTime;
    /**
     * 任务名称
     */
    private String name;

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
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

    public String getAssignClient() {
        return assignClient;
    }

    public void setAssignClient(String assignClient) {
        this.assignClient = assignClient;
    }

    public Long getLastContactTime() {
        return lastContactTime;
    }

    public void setLastContactTime(Long lastContactTime) {
        this.lastContactTime = lastContactTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getHandleStatusDetail() {
        return handleStatusDetail;
    }

    public void setHandleStatusDetail(String handleStatusDetail) {
        this.handleStatusDetail = handleStatusDetail;
    }

    public Integer getCallTimes() {
        return callTimes;
    }

    public void setCallTimes(Integer callTimes) {
        this.callTimes = callTimes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public CustomizeField[] getCustomize() {
        return customize;
    }

    public void setCustomize(CustomizeField[] customize) {
        this.customize = customize;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
