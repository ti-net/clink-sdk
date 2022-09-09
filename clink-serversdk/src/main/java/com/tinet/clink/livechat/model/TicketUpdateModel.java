package com.tinet.clink.livechat.model;

import java.util.Date;

/**
 * 工单更新实体对象
 *
 * @author liuhy
 * @date: 2020/11/25
 **/
public class TicketUpdateModel {

    /**
     * 工单 Id (id和externalId 二者必有一个)
     */
    private Integer id;

    /**
     * 工单 外部 Id (id和externalId 二者必有一个)
     */
    private String externalId;

    /**
     * 工单主题
     */
    private String topic;

    /**
     * 工单优先级。 0: 低，1: 中，2: 高，3: 紧急。默认为0。
     */
    private Integer level;

    /**
     * 关注人 Id数组
     */
    private Integer[] focus;

    /**
     * 工单标签数组
     */
    private Tag[] tags;

    /**
     * 当前工单状态
     */
    private String stateSelected;

    /**
     * 表单数据
     */
    private TicketFormModel form;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 自定义系统字段
     */
    private Field[] customizeSystemFields;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
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

    public String getStateSelected() {
        return stateSelected;
    }

    public void setStateSelected(String stateSelected) {
        this.stateSelected = stateSelected;
    }

    public TicketFormModel getForm() {
        return form;
    }

    public void setForm(TicketFormModel form) {
        this.form = form;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Field[] getCustomizeSystemFields() { return customizeSystemFields; }

    public void setCustomizeSystemFields(Field[] customizeSystemFields) { this.customizeSystemFields = customizeSystemFields; }
}