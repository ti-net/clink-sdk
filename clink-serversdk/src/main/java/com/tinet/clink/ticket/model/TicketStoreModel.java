package com.tinet.clink.ticket.model;

import com.tinet.clink.ticket.UserIdType;

/**
 * 工单保存实体对象
 *
 * @author lize
 * @date: 2022/4/1
 **/
public class TicketStoreModel {


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

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public TicketFormModel getForm() {
        return form;
    }

    public void setForm(TicketFormModel form) {
        this.form = form;
    }

    public Integer getHandlerIdType() {
        return handlerIdType;
    }

    public void setHandlerIdType(Integer handlerIdType) {
        this.handlerIdType = handlerIdType;
    }
}