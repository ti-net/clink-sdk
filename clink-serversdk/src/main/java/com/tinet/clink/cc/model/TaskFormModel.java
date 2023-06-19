package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 外呼任务表单对象
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskFormModel {
    /**
     * 模板id
     */
    private Integer formId;
    /**
     * 模板名称
     */
    private String formName;
    /**
     * 展示形式 0：横向展示（默认） 1：纵向展示
     */
    private Integer format;
    /**
     * 外呼任务模板字段
     */
    private List<FormFieldModel> formFields;

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public List<FormFieldModel> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<FormFieldModel> formFields) {
        this.formFields = formFields;
    }
}