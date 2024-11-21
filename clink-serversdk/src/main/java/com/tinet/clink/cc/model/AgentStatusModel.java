package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * 座席状态监控返回对象
 *
 * @author wangll
 * @date 2019/09/11
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentStatusModel {
    /**
     * 座席号
     */
    private String cno;

    /**
     * 座席名
     */
    private String clientName;

    /**
     * 绑定类型
     */
    private Integer bindType;

    /**
     * 绑定电话
     */
    private String bindTel;

    /**
     * 主叫号码，即来电客户号码
     */
    private String customerNumber;
    /**
     * 客户号码加密串
     */
    private String customerNumberEncrypt;
    /**
     * 置忙类型
     */
    private Integer pauseType;
    /**
     * 置忙状态
     */
    private String pauseDescription;
    /**
     * 座席状态 (空闲、置忙(具体置忙原因)、通话、振铃、整理、外呼中)
     */
    private String agentStatus;

    /**
     * 客户状态，空闲，呼叫中，响铃，通话
     */
    private String customerStatus;

    /**
     * 座席状态详情
     */
    private String agentStatusDetail;

    /**
     * 来电接听数
     */
    private Integer incomingCallCount;

    /**
     * 座席来电接听次数
     */
    private Integer bridgeCallCount;

    /**
     * qno，qname 映射
     */
    private Map<String, String> queuesMap;

    /**
     * 队列来电接听数
     */
    private Integer queueIncomingCallCount;

    /**
     * 状态时长
     */
    private Long stateDuration;

    /**
     * 登录时长
     */
    private Long loginDuration;

    /**
     * 外呼接听率
     **/
    private Double obBridgeRate;

    /**
     * 外呼通话时长
     **/
    private Long obBridgeDuration;

    /**
     * 外呼平均通话时长
     **/
    private Long obAvgBridgeDuration;

    /**
     * 空闲超时时间（单位分钟）
     */
    private Integer idleTimeOut;

    /**
     * 呼入接听总响铃时长
     */
    private Long ibAnsweredRingingDuration;

    /**
     * 座席code
     */
    private String code;

    /**
     * 队列信息
     */
    private String queueInfo;

    /**
     * 队列编号
     */
    private String queues;

    /**
     * 座席外呼数
     */
    private Integer obCallCount;
    /**
     * 外呼座席未接听数
     */
    private Integer obClientUnbridgeCount;
    /**
     * 外呼客户接听数
     */
    private Integer obCustomerBridgeCount;
    /**
     * 外呼客户未接听数
     */
    private Integer obCustomerUnbridgeCount;
    /**
     * 座席标签
     */
    private String[] tagNames;

    /**
     * 客户呼入接听数
     */
    private Integer ibClientAnsweredCount;
    /**
     * 客户呼入未接听数
     */
    private Integer ibClientUnansweredCount;

    /**
     * 客户速挂数
     */
    private Integer quickUnlinkCount;

    /**
     * 客户呼入数
     */
    private Integer ibClientTotalCount;

    /**
     * 客户呼入接听率
     */
    private double ibClientAnsweredRate;

    /**
     * 座席是否处于预测外呼状态 1：是 0：否
     */
    private Integer predictToCall;

    public Integer getIdleTimeOut() {
        return idleTimeOut;
    }

    public void setIdleTimeOut(Integer idleTimeOut) {
        this.idleTimeOut = idleTimeOut;
    }

    public Long getIbAnsweredRingingDuration() {
        return ibAnsweredRingingDuration;
    }

    public void setIbAnsweredRingingDuration(Long ibAnsweredRingingDuration) {
        this.ibAnsweredRingingDuration = ibAnsweredRingingDuration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQueueInfo() {
        return queueInfo;
    }

    public void setQueueInfo(String queueInfo) {
        this.queueInfo = queueInfo;
    }

    public String getQueues() {
        return queues;
    }

    public void setQueues(String queues) {
        this.queues = queues;
    }

    public Integer getObCallCount() {
        return obCallCount;
    }

    public void setObCallCount(Integer obCallCount) {
        this.obCallCount = obCallCount;
    }

    public Integer getObClientUnbridgeCount() {
        return obClientUnbridgeCount;
    }

    public void setObClientUnbridgeCount(Integer obClientUnbridgeCount) {
        this.obClientUnbridgeCount = obClientUnbridgeCount;
    }

    public Integer getObCustomerBridgeCount() {
        return obCustomerBridgeCount;
    }

    public void setObCustomerBridgeCount(Integer obCustomerBridgeCount) {
        this.obCustomerBridgeCount = obCustomerBridgeCount;
    }

    public Integer getObCustomerUnbridgeCount() {
        return obCustomerUnbridgeCount;
    }

    public void setObCustomerUnbridgeCount(Integer obCustomerUnbridgeCount) {
        this.obCustomerUnbridgeCount = obCustomerUnbridgeCount;
    }

    public String[] getTagNames() {
        return tagNames;
    }

    public void setTagNames(String[] tagNames) {
        this.tagNames = tagNames;
    }

    public Integer getIbClientAnsweredCount() {
        return ibClientAnsweredCount;
    }

    public void setIbClientAnsweredCount(Integer ibClientAnsweredCount) {
        this.ibClientAnsweredCount = ibClientAnsweredCount;
    }

    public Integer getIbClientUnansweredCount() {
        return ibClientUnansweredCount;
    }

    public void setIbClientUnansweredCount(Integer ibClientUnansweredCount) {
        this.ibClientUnansweredCount = ibClientUnansweredCount;
    }

    public Integer getQuickUnlinkCount() {
        return quickUnlinkCount;
    }

    public void setQuickUnlinkCount(Integer quickUnlinkCount) {
        this.quickUnlinkCount = quickUnlinkCount;
    }

    public Integer getIbClientTotalCount() {
        return ibClientTotalCount;
    }

    public void setIbClientTotalCount(Integer ibClientTotalCount) {
        this.ibClientTotalCount = ibClientTotalCount;
    }

    public double getIbClientAnsweredRate() {
        return ibClientAnsweredRate;
    }

    public void setIbClientAnsweredRate(double ibClientAnsweredRate) {
        this.ibClientAnsweredRate = ibClientAnsweredRate;
    }

    public Integer getPredictToCall() {
        return predictToCall;
    }

    public void setPredictToCall(Integer predictToCall) {
        this.predictToCall = predictToCall;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getBindType() {
        return bindType;
    }

    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    public String getBindTel() {
        return bindTel;
    }

    public void setBindTel(String bindTel) {
        this.bindTel = bindTel;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Integer getPauseType() {
        return pauseType;
    }

    public void setPauseType(Integer pauseType) {
        this.pauseType = pauseType;
    }

    public String getAgentStatusDetail() {
        return agentStatusDetail;
    }

    public void setAgentStatusDetail(String agentStatusDetail) {
        this.agentStatusDetail = agentStatusDetail;
    }

    public String getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(String agentStatus) {
        this.agentStatus = agentStatus;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Integer getIncomingCallCount() {
        return incomingCallCount;
    }

    public void setIncomingCallCount(Integer incomingCallCount) {
        this.incomingCallCount = incomingCallCount;
    }

    public Integer getQueueIncomingCallCount() {
        return queueIncomingCallCount;
    }

    public void setQueueIncomingCallCount(Integer queueIncomingCallCount) {
        this.queueIncomingCallCount = queueIncomingCallCount;
    }

    public Long getStateDuration() {
        return stateDuration;
    }

    public void setStateDuration(Long stateDuration) {
        this.stateDuration = stateDuration;
    }

    public Long getLoginDuration() {
        return loginDuration;
    }

    public void setLoginDuration(Long loginDuration) {
        this.loginDuration = loginDuration;
    }

    public String getPauseDescription() {
        return pauseDescription;
    }

    public void setPauseDescription(String pauseDescription) {
        this.pauseDescription = pauseDescription;
    }

    public String getCustomerNumberEncrypt() {
        return customerNumberEncrypt;
    }

    public void setCustomerNumberEncrypt(String customerNumberEncrypt) {
        this.customerNumberEncrypt = customerNumberEncrypt;
    }

    public Integer getBridgeCallCount() {
        return bridgeCallCount;
    }

    public void setBridgeCallCount(Integer bridgeCallCount) {
        this.bridgeCallCount = bridgeCallCount;
    }

    public Map<String, String> getQueuesMap() {
        return queuesMap;
    }

    public void setQueuesMap(Map<String, String> queuesMap) {
        this.queuesMap = queuesMap;
    }


    public Double getObBridgeRate() {
        return obBridgeRate;
    }

    public void setObBridgeRate(Double obBridgeRate) {
        this.obBridgeRate = obBridgeRate;
    }

    public Long getObBridgeDuration() {
        return obBridgeDuration;
    }

    public void setObBridgeDuration(Long obBridgeDuration) {
        this.obBridgeDuration = obBridgeDuration;
    }

    public Long getObAvgBridgeDuration() {
        return obAvgBridgeDuration;
    }

    public void setObAvgBridgeDuration(Long obAvgBridgeDuration) {
        this.obAvgBridgeDuration = obAvgBridgeDuration;
    }
}
