package com.tinet.clink.ticket.model.childForm;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import java.util.List;

/**
 * 表单模板
 *
 * @author wangll
 * @date 2018/12/11
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenapiFormModel {
    /**
     * 表单id
     */
    private Integer id;

    /**
     * 表单名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否启用 0 不启用 1 启用
     */
    private Integer enabled;
    /**
     * 子表单分类名称及属性
     */
    private List<OpenapiChildrenFormModel> childrenFormModelList;

    /**
     * 是否级联 0 不级联 1 级联
     */
    private Integer cascade;

    /**
     * 表单配置
     */
    private FormConfigModel formConfig;

    /**
     * 级联表单内容
     */
    private String property;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public List<OpenapiChildrenFormModel> getChildrenFormModelList() {
        return childrenFormModelList;
    }

    public void setChildrenFormModelList(List<OpenapiChildrenFormModel> childrenFormModelList) {
        this.childrenFormModelList = childrenFormModelList;
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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
