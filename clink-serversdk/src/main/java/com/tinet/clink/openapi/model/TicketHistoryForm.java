package com.tinet.clink.openapi.model;

import java.util.Date;

/**
 * 工单中的表单实体对象
 *
 * @author liuhy
 * @date: 2020/9/8
 **/
public class TicketHistoryForm {

    /**
     * 表单id
     */
    private Integer formId;


    /**
     * 表单名称
     */
    private String formName;

    /**
     * 审核人
     */
    private String operator;

    /**
     * 操作人id
     */
    private Integer operatorId;

    /**
     * 当前节点的名称
     */
    private String taskName;

    /**
     * 工作流转过程中表单唯一标识
     */
    private String taskId;


    /**
     * 字段名称及字段值
     */
    private TicketHistoryField[] fields;

    /**
     * 节点流入时间
     */
    private Date nodeBeginTime;

    /**
     * 节点完成时间
     */
    private Date nodeEndTime;

    /**
     * 节点唯一标识
     */
    private String taskKey;

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TicketHistoryField[] getFields() {
        return fields;
    }

    public void setFields(TicketHistoryField[] fields) {
        this.fields = fields;
    }

    public Date getNodeBeginTime() {
        return nodeBeginTime;
    }

    public void setNodeBeginTime(Date nodeBeginTime) {
        this.nodeBeginTime = nodeBeginTime;
    }

    public Date getNodeEndTime() {
        return nodeEndTime;
    }

    public void setNodeEndTime(Date nodeEndTime) {
        this.nodeEndTime = nodeEndTime;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}