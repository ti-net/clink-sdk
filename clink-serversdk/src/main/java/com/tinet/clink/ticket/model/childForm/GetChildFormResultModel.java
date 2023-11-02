package com.tinet.clink.ticket.model.childForm;

import java.util.Date;

/**
 * 工单列表查询结果对象
 *
 * @author liuhy
 * @date: 2020/8/25
 **/
public class GetChildFormResultModel {

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
     * 是否级联 0 不级联 1 级联
     */
    private Integer cascade;

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

    public Integer getCascade() {
        return cascade;
    }

    public void setCascade(Integer cascade) {
        this.cascade = cascade;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}