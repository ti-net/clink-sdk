package com.tinet.clink.cc.model;

import java.util.Date;

/**
 * 工单保存实体对象
 *
 * @author liuhy
 * @date: 2020/11/25
 **/
public class TicketSaveModel {


    /**
     * 工作流id
     */
    private Integer workflowId;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 工单主题
     */
    private String topic;

    /**
     * 工单优先级。 0: 低，1: 中，2: 高，3: 紧急。默认为0。
     */
    private Integer level;

    /**
     * 创建人 Id。此处传座席 Id， 如不传值，默认外部用户，Id为 -2。
     */
    private Integer creatorId;

    /**
     * 处理类型。 0: 座席， 1: 队列。
     */
    private Integer handlerType;

    /**
     * 处理人 Id
     */
    private Integer handlerId;

    /**
     * 关注人 Id数组
     */
    private Integer[] focus;

    /**
     * 工单标签数组
     */
    private Tag[] tags;

    /**
     * 关联的工单 Id 数组
     */
    private Integer[] relateTicketId;

    /**
     * 当前工单状态（若是预制流类型的工单，该状态可不填，人工类型的工单，该状态必填）
     */
    private String stateSelected;

    /**
     * 外部工单 Id(第三方用户可根据该 Id 进行更新或者查询)
     */
    private String externalId;

    /**
     * 表单数据
     */
    private TicketFormModel form;

    /**
     * 表单数据
     */
    private Date createTime;

    /**
     * 是否关闭工单 0 否 1 关闭
     */
    private Integer close;

    /**
     * 自定义系统字段
     */
    private Field[] customizeSystemFields;

    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getHandlerType() {
        return handlerType;
    }

    public void setHandlerType(Integer handlerType) {
        this.handlerType = handlerType;
    }

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
    }

    public Integer[] getFocus() {
        return focus;
    }

    public void setFocus(Integer[] focus) {
        this.focus = focus;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public Integer[] getRelateTicketId() {
        return relateTicketId;
    }

    public void setRelateTicketId(Integer[] relateTicketId) {
        this.relateTicketId = relateTicketId;
    }

    public String getStateSelected() {
        return stateSelected;
    }

    public void setStateSelected(String stateSelected) {
        this.stateSelected = stateSelected;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public TicketFormModel getForm() {
        return form;
    }

    public void setForm(TicketFormModel form) {
        this.form = form;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getClose() {
        return close;
    }

    public void setClose(Integer close) {
        this.close = close;
    }

    public Field[] getCustomizeSystemFields() { return customizeSystemFields; }

    public void setCustomizeSystemFields(Field[] customizeSystemFields) { this.customizeSystemFields = customizeSystemFields; }
}