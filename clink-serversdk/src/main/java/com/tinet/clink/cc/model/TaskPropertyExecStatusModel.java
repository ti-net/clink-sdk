package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskPropertyExecStatusModel {
    /**
     * 状态id
     */
    private Integer statusId;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态类型 1:已处理 2:待处理
     */
    private Integer type;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 是否默认状态 1: 是  0：否（默认否）
     */
    private Integer defaults = 0;

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getDefaults() {
        return defaults;
    }

    public void setDefaults(Integer defaults) {
        this.defaults = defaults;
    }
}