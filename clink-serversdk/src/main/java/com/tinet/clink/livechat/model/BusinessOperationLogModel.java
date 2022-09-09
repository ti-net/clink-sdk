package com.tinet.clink.livechat.model;

import java.util.Date;

/**
 * 业务记录操作日志model
 *
 * @author liuhy
 * @date: 2020/12/28
 **/
public class BusinessOperationLogModel {

    /**
     * 操作人名称
     */
    private String operatorName;

    /**
     * 操作人id
     */
    private Integer operatorId;

    /**
     *操作类型 1 创建 2 更新
     */
    private Integer operationType;

    /**
     *字段名称
     */
    private String fieldName;

    /**
     *原来字段的值
     */
    private String oldValue;

    /**
     *新值
     */
    private String newValue;

    /**
     *头像
     */
    private String avatar;

    /**
     *创建时间
     */
    private Date createTime;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}