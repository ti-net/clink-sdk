package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 表单字段
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormFieldModel {
    /**
     * 模板表单字段id
     */
    private Integer formFieldId;
    /**
     * 字段名称
     */
    private String name;
    /**
     * 对应默认字段的key
     */
    private String key;
    /**
     * 字段类型 ； 1：单行文本无限制、2：数值、3：邮件、4：IP地址、5：多行文本、6：下拉框、7：自定义级联、8：地区级联、9：单选框、
     * 10：复选框、11：日期时间、12：日期、13：时间、14：文件、15：签名、16：定位、100：手机号码、101：身份证号、102：银行卡号、103：合计字段
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
     * 列表快捷操作 0：否 1：是
     */
    private Integer listShortcut;
    /**
     * 级联必须选择到最后一级 0：否 1：是
     */
    private Integer chooseLast;
    /**
     * 一键复制 0：否 1：是
     */
    private Integer quickCopy;
    /**
     * 座席是否必填 0：否 1：是
     */
    private Integer clintRequired;
    /**
     * 是否唯一 0:否，1：是
     */
    private Integer unique;
    /**
     * 是否座席只读 0:否，1：是
     */
    private Integer clientReadonly = 0;
    /**
     * 普通座席隐藏 0:否，1：是
     */
    private Integer clientHidden = 0;
    /**
     * 查询是否展示 0:否，1：是
     */
    private Integer searchDisplay = 0;
    /**
     * 是否隐藏 0:否，1：是
     */
    private Integer hidden = 0;
    /**
     * 是否为系统默认字段 0：否 1：是
     */
    private Integer defaults = 0;

    /**
     * 属性参数
     */
    private String property;
    /**
     * 占位提示，单行多行文本用
     */
    private String placeholder;
    /**
     * 是否选择其他 0：否 1：是
     */
    private Integer selectOthers;
    /**
     * 是否支持多选 0：否 1：是
     */
    private Integer multipleSelection;

    public Integer getFormFieldId() {
        return formFieldId;
    }

    public void setFormFieldId(Integer formFieldId) {
        this.formFieldId = formFieldId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    public Integer getListShortcut() {
        return listShortcut;
    }

    public void setListShortcut(Integer listShortcut) {
        this.listShortcut = listShortcut;
    }

    public Integer getChooseLast() {
        return chooseLast;
    }

    public void setChooseLast(Integer chooseLast) {
        this.chooseLast = chooseLast;
    }

    public Integer getQuickCopy() {
        return quickCopy;
    }

    public void setQuickCopy(Integer quickCopy) {
        this.quickCopy = quickCopy;
    }

    public Integer getClintRequired() {
        return clintRequired;
    }

    public void setClintRequired(Integer clintRequired) {
        this.clintRequired = clintRequired;
    }

    public Integer getUnique() {
        return unique;
    }

    public void setUnique(Integer unique) {
        this.unique = unique;
    }

    public Integer getClientReadonly() {
        return clientReadonly;
    }

    public void setClientReadonly(Integer clientReadonly) {
        this.clientReadonly = clientReadonly;
    }

    public Integer getClientHidden() {
        return clientHidden;
    }

    public void setClientHidden(Integer clientHidden) {
        this.clientHidden = clientHidden;
    }

    public Integer getSearchDisplay() {
        return searchDisplay;
    }

    public void setSearchDisplay(Integer searchDisplay) {
        this.searchDisplay = searchDisplay;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public Integer getDefaults() {
        return defaults;
    }

    public void setDefaults(Integer defaults) {
        this.defaults = defaults;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Integer getSelectOthers() {
        return selectOthers;
    }

    public void setSelectOthers(Integer selectOthers) {
        this.selectOthers = selectOthers;
    }

    public Integer getMultipleSelection() {
        return multipleSelection;
    }

    public void setMultipleSelection(Integer multipleSelection) {
        this.multipleSelection = multipleSelection;
    }
}