package com.tinet.clink.crm.model;

/**
 * ivr节点对象
 * @author huwk
 * @date 2018/11/05
 */
public class IvrNodeModel {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 节点
     */
    private String endpoint;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 常用节点
     */
    private String frequentlyPath;
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

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrequentlyPath() {
        return frequentlyPath;
    }

    public void setFrequentlyPath(String frequentlyPath) {
        this.frequentlyPath = frequentlyPath;
    }

    public Integer getStatistic() {
        return statistic;
    }

    public void setStatistic(Integer statistic) {
        this.statistic = statistic;
    }
}
