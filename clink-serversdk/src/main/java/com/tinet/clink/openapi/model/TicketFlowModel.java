package com.tinet.clink.openapi.model;

import java.util.Date;

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

    /**
     * 下一节点 处理人 Id
     */
    private Integer nextHandlerId;

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
}