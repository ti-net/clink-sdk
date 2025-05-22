package com.tinet.clink.ticket.model;

import com.tinet.clink.ticket.UserIdType;

/**
 * 工单流转实体对象
 *
 * @author lize
 * @date: 2022/4/1
 **/
public class TicketFlowModel {


    /**
     * 工单id
     */
    private Integer ticketId;

    /**
     * 处理人id
     */
    private Integer handlerId;

    private Integer handlerIdType =  UserIdType.USER_ID.getCode();

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 工单模板表单实体对象
     */
    private TicketFormModel form;

    /**
     * 自定义系统字段
     */
    private Field[] customizeSystemFields;

    /**
     * 下一节点 处理人 Id
     */
    private Integer nextHandlerId;

    private Integer nextHandlerIdType = UserIdType.USER_ID.getCode();

    /**
     * 自定义状态id，用于指定当前工单的下一节点的自定义状态
     */
    private Integer stateId;

    public Integer getTicketId() { return ticketId; }

    public void setTicketId(Integer ticketId) { this.ticketId = ticketId; }

    public Integer getHandlerId() { return handlerId; }

    public void setHandlerId(Integer handlerId) { this.handlerId = handlerId; }

    public String getTaskId() { return taskId; }

    public void setTaskId(String taskId) { this.taskId = taskId; }

    public TicketFormModel getForm() { return form; }

    public void setForm(TicketFormModel form) { this.form = form; }

    public Field[] getCustomizeSystemFields() { return customizeSystemFields; }

    public void setCustomizeSystemFields(Field[] customizeSystemFields) { this.customizeSystemFields = customizeSystemFields; }

    public Integer getNextHandlerId() {
        return nextHandlerId;
    }

    public void setNextHandlerId(Integer nextHandlerId) {
        this.nextHandlerId = nextHandlerId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getHandlerIdType() {
        return handlerIdType;
    }

    public void setHandlerIdType(Integer handlerIdType) {
        this.handlerIdType = handlerIdType;
    }

    public Integer getNextHandlerIdType() {
        return nextHandlerIdType;
    }

    public void setNextHandlerIdType(Integer nextHandlerIdType) {
        this.nextHandlerIdType = nextHandlerIdType;
    }
}