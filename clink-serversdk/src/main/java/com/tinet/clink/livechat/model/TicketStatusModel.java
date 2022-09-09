package com.tinet.clink.livechat.model;

/**
 * 工单状态实体对象
 *
 * @author liuhy
 * @date: 2020/8/25
 **/
public class TicketStatusModel {

    /**
     * 处理状态 0:待领取 2:处理中
     */
    private Integer handleStatus;

    /**
     * 处理人类型 0:座席 1:队列
     */
    private Integer handlerType;

    /**
     * 处理人名称
     */
    private String handlerName;

    /**
     * 处理人 Id
     */
    private Integer handlerId;

    /**
     * 工作流任务 Id
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 表单 Id
     */
    private Integer formId;

    public String getTaskId() { return taskId; }

    public void setTaskId(String taskId) { this.taskId = taskId; }

    public Integer getFormId() { return formId; }

    public void setFormId(Integer formId) { this.formId = formId; }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

    public Integer getHandlerType() {
        return handlerType;
    }

    public void setHandlerType(Integer handlerType) {
        this.handlerType = handlerType;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}