package com.tinet.clink.openapi.model;

/**
 * 队列信息对象
 *
 * @author wangll
 * @date 2019-11-18
 */
public class QueueParamModel {
    /**
     * 最大等待座席数
     */
    private Integer maxWait;
    /**
     * 可用座席数
     */
    private Integer availableCount;
    /**
     * 空闲座席数
     */
    private Integer idleCount;
    /**
     * 接通电话数
     */
    private Integer completed;
    /**
     * 放弃电话数
     */
    private Integer abandoned;
    /**
     * 等待电话数
     */
    private Integer calls;
    /**
     * 服务水平秒数
     */
    private Integer serviceLevel;
    /**
     * 服务水平
     */
    private String serviceLevelPerf;
    /**
     * 队列优先级
     */
    private Integer weight;
    /**
     * 排队策略
     */
    private Integer strategy;
    /**
     * 队列超时
     */
    private Integer queueTimeout;
    /**
     * 座席超时
     */
    private Integer memberTimeout;


    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

    public Integer getIdleCount() {
        return idleCount;
    }

    public void setIdleCount(Integer idleCount) {
        this.idleCount = idleCount;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getAbandoned() {
        return abandoned;
    }

    public void setAbandoned(Integer abandoned) {
        this.abandoned = abandoned;
    }

    public Integer getCalls() {
        return calls;
    }

    public void setCalls(Integer calls) {
        this.calls = calls;
    }

    public Integer getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(Integer serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getServiceLevelPerf() {
        return serviceLevelPerf;
    }

    public void setServiceLevelPerf(String serviceLevelPerf) {
        this.serviceLevelPerf = serviceLevelPerf;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getStrategy() {
        return strategy;
    }

    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
    }

    public Integer getQueueTimeout() {
        return queueTimeout;
    }

    public void setQueueTimeout(Integer queueTimeout) {
        this.queueTimeout = queueTimeout;
    }

    public Integer getMemberTimeout() {
        return memberTimeout;
    }

    public void setMemberTimeout(Integer memberTimeout) {
        this.memberTimeout = memberTimeout;
    }
}
