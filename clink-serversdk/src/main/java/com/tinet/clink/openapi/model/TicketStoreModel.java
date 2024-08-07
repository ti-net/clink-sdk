package com.tinet.clink.openapi.model;

/**
 * 工单保存实体对象
 *
 * @author lize
 * @date: 2022/4/1
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  TicketStoreModel {


    /**
     * 工单id
     */
    private Integer ticketId;

    /**
     * 处理人id
     */
    private Integer handlerId;

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
}