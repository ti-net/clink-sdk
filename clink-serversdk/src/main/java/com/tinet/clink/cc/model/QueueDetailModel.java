package com.tinet.clink.cc.model;

import java.util.List;

/**
 * 队列详情对象
 *
 * @author lizy
 * @date 2018/09/13
 */
public class QueueDetailModel {

    private Integer enterpriseId;

    /**
     * 队列号 默认四位数字支持3-5位
     */
    private String qno;

    /**
     * 队列名
     */
    private String name;

    /**
     * 队列超时时间
     */
    private Integer queueTimeout;

    /**
     * 语音报号
     */
    private Integer sayCno;

    /**
     * 座席超时时间
     */
    private Integer memberTimeout;

    /**
     * 座席超时无应答,呼叫下一座席的延迟秒数,<=0时采用默认值5;座席电话忙,马上呼叫下一座席,不受此参数影响
     */
    private Integer retry;

    /**
     * 整理时间
     */
    private Integer wrapupTime;

    /**
     * 最大等待数
     */
    private Integer maxWait;

    /**
     * 呼叫策略
     */
    private Integer strategy;

    /**
     * 服务水平秒数，低于此时间内接听的认为是高服务水平
     */
    private Integer serviceLevel;

    /**
     * 队列优先级
     */
    private Integer weight;

    /**
     * 队列是否支持vip级别 0:不支持 1:支持
     */
    private Integer vipSupport;

    /**
     * 队列中为空时是否可以join
     */
    private String joinEmpty;

    /**
     * 允许呼入队列 0 不允许 1 允许
     */
    private Integer ibAllowed;

    /**
     * 在线客服最大排队数
     */
    private Integer chatMaxWait;

    /**
     * 在线客服分配策略
     */
    private Integer chatStrategy;

    /**
     * 在线客服排队位置推送(小于该位置则推送)
     */
    private Integer chatLocation;

    /**
     * 关联的座席和技能值
     */
    private List<QueueMemberModel> queueMembers;

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQueueTimeout() {
        return queueTimeout;
    }

    public void setQueueTimeout(Integer queueTimeout) {
        this.queueTimeout = queueTimeout;
    }

    public Integer getSayCno() {
        return sayCno;
    }

    public void setSayCno(Integer sayCno) {
        this.sayCno = sayCno;
    }

    public Integer getMemberTimeout() {
        return memberTimeout;
    }

    public void setMemberTimeout(Integer memberTimeout) {
        this.memberTimeout = memberTimeout;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public Integer getWrapupTime() {
        return wrapupTime;
    }

    public void setWrapupTime(Integer wrapupTime) {
        this.wrapupTime = wrapupTime;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Integer getStrategy() {
        return strategy;
    }

    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
    }

    public Integer getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(Integer serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getVipSupport() {
        return vipSupport;
    }

    public void setVipSupport(Integer vipSupport) {
        this.vipSupport = vipSupport;
    }

    public String getJoinEmpty() {
        return joinEmpty;
    }

    public void setJoinEmpty(String joinEmpty) {
        this.joinEmpty = joinEmpty;
    }

    public Integer getIbAllowed() {
        return ibAllowed;
    }

    public void setIbAllowed(Integer ibAllowed) {
        this.ibAllowed = ibAllowed;
    }

    public Integer getChatMaxWait() {
        return chatMaxWait;
    }

    public void setChatMaxWait(Integer chatMaxWait) {
        this.chatMaxWait = chatMaxWait;
    }

    public Integer getChatStrategy() {
        return chatStrategy;
    }

    public void setChatStrategy(Integer chatStrategy) {
        this.chatStrategy = chatStrategy;
    }

    public Integer getChatLocation() {
        return chatLocation;
    }

    public void setChatLocation(Integer chatLocation) {
        this.chatLocation = chatLocation;
    }

    public List<QueueMemberModel> getQueueMembers() {
        return queueMembers;
    }

    public void setQueueMembers(List<QueueMemberModel> queueMembers) {
        this.queueMembers = queueMembers;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    @Override
    public String toString() {
        return "QueueDetailModel{" +
                "qno='" + qno + '\'' +
                ", name='" + name + '\'' +
                ", queueTimeout=" + queueTimeout +
                ", sayCno=" + sayCno +
                ", memberTimeout=" + memberTimeout +
                ", retry=" + retry +
                ", wrapupTime=" + wrapupTime +
                ", maxWait=" + maxWait +
                ", strategy=" + strategy +
                ", serviceLevel=" + serviceLevel +
                ", weight=" + weight +
                ", vipSupport=" + vipSupport +
                ", joinEmpty='" + joinEmpty + '\'' +
                ", ibAllowed=" + ibAllowed +
                ", chatMaxWait=" + chatMaxWait +
                ", chatStrategy=" + chatStrategy +
                ", chatLocation=" + chatLocation +
                ", queueMembers=" + queueMembers +
                '}';
    }
}
