package com.tinet.clink.openapi.model;

/**
 * 用于封装返回的ChatRecord记录数据model
 */
public class ChatRecord {

    private String mainUniqueId;                // 主会话唯一标识
    private String visitorId;                   // visitor 访客id，此时用系统生成的uuid进行标识，关联唯一的访客
    private String visitorName;                 // 访客姓名
    private String cno;                         // 座席号
    private String clientName;                  // 座席姓名
    private String qno;                         // 队列号
    private String qname;                       // 队列名
    private String ip;                          // ip地址
    private String browser;                       // 浏览器型号
    private String operatingSystem;                // 操作系统
    private Long startTime;                      // 开始时间
    private Long joinQueueTime;                 // 开始时间
    private Long endTime;                        // 结束时间
    private Long bridgeTime;                     // 接听时间
    private String referrerName;                //接入来源
    private String firstVisitPageUrl;           // 受访页
    private String initiationPageUrl;           // 会话发起页
    private String province;                    //省份
    private String city;                        //城市
    private Integer appType;                    // 渠道类型

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
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

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getReferrerName() {
        return referrerName;
    }

    public void setReferrerName(String referrerName) {
        this.referrerName = referrerName;
    }

    public String getFirstVisitPageUrl() {
        return firstVisitPageUrl;
    }

    public void setFirstVisitPageUrl(String firstVisitPageUrl) {
        this.firstVisitPageUrl = firstVisitPageUrl;
    }

    public String getInitiationPageUrl() {
        return initiationPageUrl;
    }

    public void setInitiationPageUrl(String initiationPageUrl) {
        this.initiationPageUrl = initiationPageUrl;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}
