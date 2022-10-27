package com.tinet.clink.cc.model;

/**
 * Ivr对象
 * @author huwk
 * @date 2018/11/05
 */
public class IvrModel {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * IVR名称
     */
    private String name;

    /**
     * IVR描述
     */
    private String description;

    /**
     * 是否开启节点统计，0：关闭、1：开启
     */
    private Integer statistic;

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

    public Integer getStatistic() {
        return statistic;
    }

    public void setStatistic(Integer statistic) {
        this.statistic = statistic;
    }
}
