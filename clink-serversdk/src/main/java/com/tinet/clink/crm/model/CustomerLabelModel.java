package com.tinet.clink.crm.model;

/**
 * 客户资料标签model
 *
 * @author tian.jie
 * @date 2023-11-01 18:41
 */
public class CustomerLabelModel {

    /**
     * 标签id
     */
    private Integer id;

    /**
     * 标签组ID
     */
    private Integer groupId;

    /**
     * 标签名称
     */
    private String name;

    /**
     *   标签类型 1：手动标签
     */
    private Integer type;

    /**
     * 标签来源 1：客户标签
     */
    private Integer source;

    /**
     * 标签颜色
     */
    private String color;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
