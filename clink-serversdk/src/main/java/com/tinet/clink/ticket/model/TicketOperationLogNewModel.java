package com.tinet.clink.ticket.model;

import java.util.Date;

/**
 *
 * 工单操作日志v2
 *
 * @author wangli
 * @date 2023-07-17 10:38
 */
public class TicketOperationLogNewModel {

    /**
     * 操作人id
     */
    private Integer operatorId;

    /**
     * 操作人类型
     */
    private Integer operatorType;

    /**
     * 操作人名称
     */
    private String operatorName;

    /**
     * 操作类型
     */
    private Integer type;

    /**
     * 变更内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
