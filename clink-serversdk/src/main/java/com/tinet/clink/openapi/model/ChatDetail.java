package com.tinet.clink.openapi.model;

import java.util.List;

/**
 * 用于封装返回给前端的cdr通话记录数据model
*/
public class ChatDetail {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
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

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus;
    }

    public Integer getChatType() {
        return chatType;
    }

    public void setChatType(Integer chatType) {
        this.chatType = chatType;
    }

    public Integer getDetailChatType() {
        return detailChatType;
    }

    public void setDetailChatType(Integer detailChatType) {
        this.detailChatType = detailChatType;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAreaCode() {
        return customerAreaCode;
    }

    public void setCustomerAreaCode(String customerAreaCode) {
        this.customerAreaCode = customerAreaCode;
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<String> getVisitFlow() {
        return visitFlow;
    }

    public void setVisitFlow(List<String> visitFlow) {
        this.visitFlow = visitFlow;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getJoinQueueTime() {
        return joinQueueTime;
    }

    public void setJoinQueueTime(Long joinQueueTime) {
        this.joinQueueTime = joinQueueTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getBridgeTime() {
        return bridgeTime;
    }

    public void setBridgeTime(Long bridgeTime) {
        this.bridgeTime = bridgeTime;
    }

    /**
     * 主会话唯一标识
     */
    private String mainUniqueId;
    /**
     * 会话唯一标识
     */
    private String uniqueId;
    /**
     * 企业id
     */
    private Integer enterpriseId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * visitor 访客id，此时用系统生成的uuid进行标识，关联唯一的访客
     */
    private String visitorId;
    /**
     * 访客姓名
     */
    private String visitorName;
    /**
     * 座席号
     */
    private String cno;
    /**
     * 座席名称
     */
    private String clientName;
    /**
     * 队列号
     */
    private String qno;
    /**
     * 渠道类型
     */
    private String source;
    /**
     * 主会话状态
     */
    private String status;
    /**
     * 从会话状态
     */
    private String detailStatus;
    /**
     * 会话类型
     */
    private Integer chatType;
    /**
     * 会话类型
     */
    private Integer detailChatType;
    /**
     * 客户号码
     */
    private String customerNumber;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 客户号码区号
     */
    private String customerAreaCode;
    /**
     * 追加客户省份
     */
    private String customerProvince;
    /**
     * 追加客户城市
     */
    private String customerCity;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 邀请座席 0：邀请、1：未邀请
     */
    private String invite;
    /**
     * 浏览器型号
     */
    private String browser;
    /**
     * 操作系统
     */
    private String operatingSystem;
    /**
     * 接入来源
     */
    private String website;
    /**
     * 会话轨迹
     */
    private List<String> visitFlow;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 入队时间
     */
    private Long joinQueueTime;
    /**
     * 结束时间
     */
    private Long endTime;
    /**
     * 桥接时间
     */
    private Long bridgeTime;

}
