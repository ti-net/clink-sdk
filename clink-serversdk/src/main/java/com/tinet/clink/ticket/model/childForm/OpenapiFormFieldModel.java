package com.tinet.clink.ticket.model.childForm;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author dengjie
 * @date 2018/12/06
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenapiFormFieldModel {
    /**
     * 字段类型
     */
    private Integer type;

    /**
     * 属性id
     */
    private Integer fieldId;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 是否必填
     */
    private Integer required;

    /**
     * 分类名称
     */
    private String group;

    /**
     * 分类描述
     */
    private String groupDesc;

    /**
     * 是否级联 0 不级联 1 级联
     */
    private Integer cascade;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public Integer getCascade() {
        return cascade;
    }

    public void setCascade(Integer cascade) {
        this.cascade = cascade;
    }
}
