package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.Date;

/**
 * 业务记录详情model
 *
 * @author liuhy
 * @date: 2020/12/28
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  BusinessDetailModel {

    /**
     * 业务记录id
     */
    private Integer id;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户号码数组
     */
    private String[] customerTel;

    /**
     * 业务记录主题
     */
    private String topic;

    /**
     * 创建人
     */
    private Integer creatorId;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 修改人
     */
    private Integer modifierId;

    /**
     * 修改人名称
     */
    private String modifierName;

    /**
     * 来源
     */
    private Integer source;

    /**
     * 自定义字段
     */
    private ArrayNode customize;

    /**
     * 操作日志数组
     */
    private BusinessOperationLogModel[] operationLogs;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 通话记录id
     */
    private String callId;

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String[] getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String[] customerTel) {
        this.customerTel = customerTel;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public ArrayNode getCustomize() {
        return customize;
    }

    public void setCustomize(ArrayNode customize) {
        this.customize = customize;
    }

    public BusinessOperationLogModel[] getOperationLogs() {
        return operationLogs;
    }

    public void setOperationLogs(BusinessOperationLogModel[] operationLogs) {
        this.operationLogs = operationLogs;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}