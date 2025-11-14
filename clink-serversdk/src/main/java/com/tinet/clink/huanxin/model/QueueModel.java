package com.tinet.clink.huanxin.model;

public class QueueModel {

    // 技能组id
    private String queueId;
    // 技能组名称
    private String queueName;
    // 技能组类型，在线用，UserDefined自定义 SystemDefault默认组
    private String queueGroupType;

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueGroupType() {
        return queueGroupType;
    }

    public void setQueueGroupType(String queueGroupType) {
        this.queueGroupType = queueGroupType;
    }
}
