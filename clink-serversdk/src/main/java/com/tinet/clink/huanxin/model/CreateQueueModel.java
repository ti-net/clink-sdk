package com.tinet.clink.huanxin.model;

public class CreateQueueModel {

    // 技能组id
    private String queueId;
    // 技能组名称
    private String queueName;
    // 技能组类型，在线用，UserDefined自定义 SystemDefault默认组
    private String queueGroupType;
    // 技能组内坐席数量
    private Integer userCount;

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

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }
}
