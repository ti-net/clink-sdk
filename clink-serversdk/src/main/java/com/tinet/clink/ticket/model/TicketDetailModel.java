package com.tinet.clink.ticket.model;

import java.util.Date;

/**
 * 工单详情对象
 *
 * @author liuhy
 * @date: 2020/9/8
 **/

public class TicketDetailModel {

    /**
     * 自定义工单编号
     */
    private String customId;

    /**
     * 工单id
     */
    private Integer id;

    /**
     * 工作流id
     */
    private Integer workflowId;

    /**
     * 工作流名称
     */
    private String workflowName;

    /**
     * 主题
     */
    private String topic;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 状态
     */
    private TicketStatusModel[] status;

    /**
     * 人工分配工作流自定义状态
     */
    private String[] state;

    /**
     * 工单类型，1、预制工作流工单，2、人工分配工单
     */
    private Integer type;

    /**
     * 创建人类型
     */
    private Integer creatorType;

    /**
     * 创建人id
     */
    private Integer creatorId;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 创建人类型
     */
    private Integer modifierType;

    /**
     * 修改人id
     */
    private Integer modifierId;

    /**
     * 修改人名称
     */
    private String modifierName;

    /**
     * 关注人id
     */
    private ClientAllModel[] focus;

    /**
     * 当前工单状态
     */
    private String stateSelected;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户号码
     */
    private String[] customerTel;

    /**
     * 工单标签
     */
    private Tag[] tags;

    /**
     * 评论人
     */
    private TicketCommentSearchResultModel[] comments;

    /**
     * 工单的起始表单
     */
    private TicketHistoryForm startForm;

    /**
     * 操作日志
     */
    private TicketOperationLogModel[] operationLogs;

    /**
     * 关闭时间
     */
    private Date closeTime;

    /**
     * 通话记录id
     */
    private String callId;


    /**
     * 历史表单
     */
    private TicketHistoryForm[] forms;

    /**
     * 工单的系统表单
     */
    private TicketSystemForm systemForm;

    /**
     * 操作日志v2
     */
    private TicketOperationLogNewModel[] operationLogsNew;


    public TicketOperationLogNewModel[] getOperationLogsNew() {
        return operationLogsNew;
    }

    public void setOperationLogsNew(TicketOperationLogNewModel[] operationLogsNew) {
        this.operationLogsNew = operationLogsNew;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public TicketStatusModel[] getStatus() {
        return status;
    }

    public void setStatus(TicketStatusModel[] status) {
        this.status = status;
    }

    public String[] getState() {
        return state;
    }

    public void setState(String[] state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(Integer creatorType) {
        this.creatorType = creatorType;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Integer getModifierType() {
        return modifierType;
    }

    public void setModifierType(Integer modifierType) {
        this.modifierType = modifierType;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public ClientAllModel[] getFocus() {
        return focus;
    }

    public void setFocus(ClientAllModel[] focus) {
        this.focus = focus;
    }

    public String getStateSelected() {
        return stateSelected;
    }

    public void setStateSelected(String stateSelected) {
        this.stateSelected = stateSelected;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String[] getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String[] customerTel) {
        this.customerTel = customerTel;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public TicketCommentSearchResultModel[] getComments() {
        return comments;
    }

    public void setComments(TicketCommentSearchResultModel[] comments) {
        this.comments = comments;
    }

    public TicketHistoryForm getStartForm() {
        return startForm;
    }

    public void setStartForm(TicketHistoryForm startForm) {
        this.startForm = startForm;
    }

    public TicketOperationLogModel[] getOperationLogs() {
        return operationLogs;
    }

    public void setOperationLogs(TicketOperationLogModel[] operationLogs) {
        this.operationLogs = operationLogs;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public TicketHistoryForm[] getForms() {
        return forms;
    }

    public void setForms(TicketHistoryForm[] forms) {
        this.forms = forms;
    }

    public TicketSystemForm getSystemForm() { return systemForm; }

    public void setSystemForm(TicketSystemForm systemForm) { this.systemForm = systemForm; }
}