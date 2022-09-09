package com.tinet.clink.crm.model;

import java.util.Date;
import java.util.List;

/**
 * 工单模板实体对象
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
public class WorkflowResultModel {
    /**
     * 工单模板主键id
     */
    private Integer id;

    /**
     * 工单模板名称
     */
    private String name;

    /**
     * 工单模板类型 1: 预制工作流模板，2: 人工分配模板
     */
    private Integer type;

    /**
     * 工单模板状态。 0: 停用，1: 启用。
     */
    private Integer status;

    /**
     * 工单自定义状态集合
     */
    private List<String> state;

    /**
     * 工单模板类别 Id
     */
    private Integer categoryId;

    /**
     * 工单模板类别名称
     */
    private String categoryName;

    /**
     * 工单模板创建时间
     */
    private Date createTime;

    /**
     * 工单模板更新时间
     */
    private Date updateTime;

    /**
     * 工单模板表单实体对象
     */
    private TicketFormModel form;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getState() {
        return state;
    }

    public void setState(List<String> state) {
        this.state = state;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public TicketFormModel getForm() {
        return form;
    }

    public void setForm(TicketFormModel form) {
        this.form = form;
    }
}