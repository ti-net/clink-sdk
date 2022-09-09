package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 工单字段model
 *
 * @author wangli
 * @date 2022-08-09 10:40 上午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketFieldModel {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性类型
     */
    private Integer type;

    /**
     * 属性参数
     */
    private String property;

    /**
     * 所属大类
     */
    private Integer category;

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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
