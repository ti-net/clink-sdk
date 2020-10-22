package com.tinet.clink.openapi.model;

import java.util.Date;

/**
 *
 * @author liuhy
 * @date: 2020/9/8
 **/
public class TicketOperationLogModel {

    /**
     * 操作人名称
     */
    private String operatorName;
    /**
     * 头像
     */
    private String avatar;

    /**
     * 属性变更（属性名：属性值）
     */
    private String propertyChanges;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPropertyChanges() {
        return propertyChanges;
    }

    public void setPropertyChanges(String propertyChanges) {
        this.propertyChanges = propertyChanges;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}