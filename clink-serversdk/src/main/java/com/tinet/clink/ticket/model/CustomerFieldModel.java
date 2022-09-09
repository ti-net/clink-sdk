package com.tinet.clink.ticket.model;

/**
 * @author lizy
 * @date 2020/01/09
 */
public class CustomerFieldModel {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 字段键值
     */
    private String key;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性类型
     */
    private Integer type;

    /**
     * 是否系统默认
     */
    private Integer defaults;

    /**
     * 属性参数
     */
    private String property;


    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 是否必传
     */
    private Integer required;

    /**
     * 是否唯一
     */
    private Integer unique;

    /**
     * 是否座席只读
     */
    private Integer clientReadonly;

    /**
     * 普通座席是否只读
     */
    private Integer clientHidden;

    /**
     * 查询是否展示
     */
    private Integer searchDisplay;

    /**
     * 是否隐藏
     */
    private Integer hidden;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

}
