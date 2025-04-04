package com.tinet.clink.ticket.model;

import com.tinet.clink.ticket.UserIdType;

/**
 * 工单催办model
 *
 * @author feizq
 * @date 2023/10/18
 */
public class TicketReminderModel {

    /**
     * 催单员工id
     */
    private Integer userId;

    private Integer userIdType = UserIdType.USER_ID.getCode();

    /**
     * 催单员工名
     */
    private String userName;

    /**
     * 催单员工工号
     */
    private String cno;

    /**
     * 催单备注
     */
    private String reminderRemark;

    /**
     * 催单任务的id
     */
    private String taskId;

    /**
     * 工单的id
     */
    private Integer ticketId;

    /**
     * 自定义工单编号 (id和customId 二者必有一个)
     */
    private String customId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getReminderRemark() {
        return reminderRemark;
    }

    public void setReminderRemark(String reminderRemark) {
        this.reminderRemark = reminderRemark;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public Integer getUserIdType() {
        return userIdType;
    }

    public void setUserIdType(Integer userIdType) {
        this.userIdType = userIdType;
    }
}
