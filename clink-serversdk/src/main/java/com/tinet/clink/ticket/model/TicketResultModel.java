package com.tinet.clink.ticket.model;

import java.util.Date;
import java.util.Objects;

/**
 * 工单列表查询结果对象
 *
 * @author liuhy
 * @date: 2020/8/25
 **/
public class TicketResultModel {

    /**
     * 工单 Id
     */
    private Integer id;

    /**
     * 工单 Id
     */
    private String customId;

    /**
     * 模板 Id
     */
    private Integer workflowId;

    /**
     * 模板名称
     */
    private String workflowName;

    /**
     * 工单类型 1:预制工作流工单，2:人工分配的工单
     */
    private Integer type;

    /**
     * 工单主题
     */
    private String topic;

    /**
     * 紧急程度 0：低、1：中、2：高、3：紧急
     */
    private Integer level;

    /**
     * 工单标签
     */
    private Tag[] tags;

    /**
     * 来源
     */
    private Integer source;


    /**
     * 工单状态
     */
    private TicketStatusModel[] status;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 创建人 Id
     */
    private Integer creatorId;

    /**
     * 修改人 Id
     */
    private Integer modifierId;

    /**
     * 是否超时
     */
    private Integer timeout;

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
     * 关闭时间
     */
    private Date closeTime;

    /**
     * 当前工单状态(当工单为人工分配的工单时，该字段有值)
     */
    private String stateSelected;

    /**
     * 最近催单时间
     */
    private Date lastReminderTime;

    /**
     * 催单次数
     */
    private Integer reminderCount;

    /**
     * 客户 Id
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
     * 邮箱
     */
    private String customerEmail;

    /**
     * 地址
     */
    private String customerAddress;

    /**
     * 归属
     */
    private Integer customerShare;

    /**
     * 创建人
     */
    private Integer customerCreatorId;

    /**
     * 创建人姓名
     */
    private String customerCreatorName;

    /**
     * 修改人，新增时创建人即修改人
     */
    private Integer customerModifierId;

    /**
     * 修改人姓名
     */
    private String customerModifierName;

    /**
     * 工单的系统表单
     */
    private TicketSystemForm systemForm;

    /**
     * 关联父工单id
     */
    private Integer parentTicketId;

    /**
     * 关联父工单节点 taskKey
     */
    private String parentTicketNode;

    private String isSubWorkflow;

    public String getIsSubWorkflow() {
        return Objects.isNull(this.parentTicketId) ? "否" : "是";
    }


    /**
     * 撤回状态 1 撤回  旧数据是 0 未撤回
     */
    private Integer goBackStatus;

    /**
     * 重启状态 1 已重启  旧数据是 0 未重启
     */
    private Integer restartStatus;

    /**
     * 催单状态
     */

    private Integer reminderStatus;
    /**
     * 评论状态
     */
    private Integer commentNotRead;

    public Integer getGoBackStatus() {
        return goBackStatus;
    }

    public void setGoBackStatus(Integer goBackStatus) {
        this.goBackStatus = goBackStatus;
    }

    public Integer getRestartStatus() {
        return restartStatus;
    }

    public void setRestartStatus(Integer restartStatus) {
        this.restartStatus = restartStatus;
    }

    public Integer getReminderStatus() {
        return reminderStatus;
    }

    public void setReminderStatus(Integer reminderStatus) {
        this.reminderStatus = reminderStatus;
    }

    public Integer getCommentNotRead() {
        return commentNotRead;
    }

    public void setCommentNotRead(Integer commentNotRead) {
        this.commentNotRead = commentNotRead;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
    public Integer getSource(){return source;}
    public void setSource(Integer source){this.source = source;}

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public TicketStatusModel[] getStatus() {
        return status;
    }

    public void setStatus(TicketStatusModel[] status) {
        this.status = status;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
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

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getStateSelected() {
        return stateSelected;
    }

    public void setStateSelected(String stateSelected) {
        this.stateSelected = stateSelected;
    }

    public Date getLastReminderTime() {
        return lastReminderTime;
    }

    public void setLastReminderTime(Date lastReminderTime) {
        this.lastReminderTime = lastReminderTime;
    }

    public Integer getReminderCount() {
        return reminderCount;
    }

    public void setReminderCount(Integer reminderCount) {
        this.reminderCount = reminderCount;
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Integer getCustomerShare() {
        return customerShare;
    }

    public void setCustomerShare(Integer customerShare) {
        this.customerShare = customerShare;
    }

    public Integer getCustomerCreatorId() {
        return customerCreatorId;
    }

    public void setCustomerCreatorId(Integer customerCreatorId) {
        this.customerCreatorId = customerCreatorId;
    }

    public String getCustomerCreatorName() {
        return customerCreatorName;
    }

    public void setCustomerCreatorName(String customerCreatorName) {
        this.customerCreatorName = customerCreatorName;
    }

    public Integer getCustomerModifierId() {
        return customerModifierId;
    }

    public void setCustomerModifierId(Integer customerModifierId) {
        this.customerModifierId = customerModifierId;
    }

    public String getCustomerModifierName() {
        return customerModifierName;
    }

    public void setCustomerModifierName(String customerModifierName) { this.customerModifierName = customerModifierName; }

    public TicketSystemForm getSystemForm() { return systemForm; }

    public void setSystemForm(TicketSystemForm systemForm) { this.systemForm = systemForm; }

    public Integer getParentTicketId() {
        return parentTicketId;
    }

    public String getParentTicketNode() {
        return parentTicketNode;
    }
}