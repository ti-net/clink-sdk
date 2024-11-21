package com.tinet.clink.ticket.model.childForm;

import java.util.Date;
import java.util.List;

/**
 * 工单列表查询结果对象
 *
 * @author liuhy
 * @date: 2020/8/25
 **/
public class CreateChildFormResultModel {

    /**
     * 表单名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;


    /**
     * 详细字段
     */
    private List<OpenapiFormFieldModel> fields;

    /**
     * 是否级联 0 不级联 1 级联
     */
    private Integer cascade;

    /**
     * 表单配置
     */
    private FormConfigModel formConfig;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OpenapiFormFieldModel> getFields() {
        return fields;
    }

    public void setFields(List<OpenapiFormFieldModel> fields) {
        this.fields = fields;
    }

    public Integer getCascade() {
        return cascade;
    }

    public void setCascade(Integer cascade) {
        this.cascade = cascade;
    }

    public FormConfigModel getFormConfig() {
        return formConfig;
    }

    public void setFormConfig(FormConfigModel formConfig) {
        this.formConfig = formConfig;
    }
}