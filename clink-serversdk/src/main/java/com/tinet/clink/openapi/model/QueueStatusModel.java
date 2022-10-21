package com.tinet.clink.openapi.model;

import java.util.List;

/**
 * 队列状态监控返回对象
 *
 * @author wangll
 * @date 2019/11/20
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  QueueStatusModel {
    /**
     * 队列编号
     */
    private String qno;
    /**
     * 队列名称
     */
    private String qname;
    /**
     * 队列参数
     */
    private QueueParamModel queueParams;
    /**
     * 队列成员
     */
    private List<AgentStatusModel> agentStatus;
    /**
     * 排队成员
     */
    private List<QueueEntryModel> queueEntries;


    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public QueueParamModel getQueueParams() {
        return queueParams;
    }

    public void setQueueParams(QueueParamModel queueParams) {
        this.queueParams = queueParams;
    }

    public List<AgentStatusModel> getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(List<AgentStatusModel> agentStatus) {
        this.agentStatus = agentStatus;
    }

    public List<QueueEntryModel> getQueueEntries() {
        return queueEntries;
    }

    public void setQueueEntries(List<QueueEntryModel> queueEntries) {
        this.queueEntries = queueEntries;
    }
}
