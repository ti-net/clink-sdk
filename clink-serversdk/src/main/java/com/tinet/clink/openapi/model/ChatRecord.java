package com.tinet.clink.openapi.model;

import java.util.List;
import java.util.Map;

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

    /**
     * 机器人客服id
     */
    private String robotId;

    /**
     * 机器人客服名称
     */
    private String robotName;

    /**
     * 机器人客服接入时间
     */
    private Long robotStartTime;

    /**
     * 机器人客服关闭时间
     */
    private Long robotEndTime;

    /**
     * 渠道名称
     */
    private String appName;

    /**
     * 访问地区
     */
    private String customerRegion;

    /**
     * 会话渠道
     */
    private Integer contactType;

    /**
     * 接入来源（访客来源）
     */
    private String refererName;

    /**
     * 访客轨迹
     */
    private List<Map<String, Object>> visitTracks;

    /**
     * 自定义参数
     */
    private Map<String,Object> visitorExtraInfo;

    /**
     * 搜索词
     */
    private String searchWord;

    /**
     * 满意度评价
     */
    private Integer keys;

    /**
     * 满意度备注
     */
    private String remark;

    /**
     * 来源页
     */
    private String refererUrl;

    /**
     * 会话次数
     */
    private Long chatTimes;

    /**
     * 被邀请
     */
    private Long invitedTimes;

    /**
     * 结束原因 ChatCloseReasonEnum
     */
    private Integer closeReason;

    /**
     * 结束时的会话状态 ChatSessionStatus
     */
    private Integer closeStatus;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 接到类型 ChatDurationTypeEnum
     */
    private Integer chatDurationType;

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

    public String getRobotId() {
        return robotId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public Long getRobotStartTime() {
        return robotStartTime;
    }

    public void setRobotStartTime(Long robotStartTime) {
        this.robotStartTime = robotStartTime;
    }

    public Long getRobotEndTime() {
        return robotEndTime;
    }

    public void setRobotEndTime(Long robotEndTime) {
        this.robotEndTime = robotEndTime;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCustomerRegion() {
        return customerRegion;
    }

    public void setCustomerRegion(String customerRegion) {
        this.customerRegion = customerRegion;
    }

    public Integer getContactType() {
        return contactType;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }

    public String getRefererName() {
        return refererName;
    }

    public void setRefererName(String refererName) {
        this.refererName = refererName;
    }

    public List<Map<String, Object>> getVisitTracks() {
        return visitTracks;
    }

    public void setVisitTracks(List<Map<String, Object>> visitTracks) {
        this.visitTracks = visitTracks;
    }

    public Map<String, Object> getVisitorExtraInfo() {
        return visitorExtraInfo;
    }

    public void setVisitorExtraInfo(Map<String, Object> visitorExtraInfo) {
        this.visitorExtraInfo = visitorExtraInfo;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public Integer getKeys() {
        return keys;
    }

    public void setKeys(Integer keys) {
        this.keys = keys;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRefererUrl() {
        return refererUrl;
    }

    public void setRefererUrl(String refererUrl) {
        this.refererUrl = refererUrl;
    }

    public Long getChatTimes() {
        return chatTimes;
    }

    public void setChatTimes(Long chatTimes) {
        this.chatTimes = chatTimes;
    }

    public Long getInvitedTimes() {
        return invitedTimes;
    }

    public void setInvitedTimes(Long invitedTimes) {
        this.invitedTimes = invitedTimes;
    }

    public Integer getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(Integer closeReason) {
        this.closeReason = closeReason;
    }

    public Integer getCloseStatus() {
        return closeStatus;
    }

    public void setCloseStatus(Integer closeStatus) {
        this.closeStatus = closeStatus;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getChatDurationType() {
        return chatDurationType;
    }

    public void setChatDurationType(Integer chatDurationType) {
        this.chatDurationType = chatDurationType;
    }
}
