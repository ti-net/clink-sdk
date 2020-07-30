package com.tinet.clink.openapi.model;

import java.util.List;
import java.util.Map;

/**
 * 用于封装返回的ChatRecord记录数据model
 */
public class ChatRecord {
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

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
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

    public Integer getInvite() {
        return invite;
    }

    public void setInvite(Integer invite) {
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

    public String getVisitFlow() {
        return visitFlow;
    }

    public void setVisitFlow(String visitFlow) {
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public Integer getContactType() {
        return contactType;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }

    public Integer getKeys() {
        return keys;
    }

    public void setKeys(Integer keys) {
        this.keys = keys;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Map getJoin() {
        return join;
    }

    public void setJoin(Map join) {
        this.join = join;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    public Short getHour() {
        return hour;
    }

    public void setHour(Short hour) {
        this.hour = hour;
    }

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
    }

    public Long getBridgeDuration() {
        return bridgeDuration;
    }

    public void setBridgeDuration(Long bridgeDuration) {
        this.bridgeDuration = bridgeDuration;
    }

    public Long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Long totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Integer getVisitorChatCount() {
        return visitorChatCount;
    }

    public void setVisitorChatCount(Integer visitorChatCount) {
        this.visitorChatCount = visitorChatCount;
    }

    public Integer getAgentChatCount() {
        return agentChatCount;
    }

    public void setAgentChatCount(Integer agentChatCount) {
        this.agentChatCount = agentChatCount;
    }

    public Integer getSystemChatCount() {
        return systemChatCount;
    }

    public void setSystemChatCount(Integer systemChatCount) {
        this.systemChatCount = systemChatCount;
    }

    public Integer getRobotChatCount() {
        return robotChatCount;
    }

    public void setRobotChatCount(Integer robotChatCount) {
        this.robotChatCount = robotChatCount;
    }

    public Integer getTransfer() {
        return transfer;
    }

    public void setTransfer(Integer transfer) {
        this.transfer = transfer;
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

    public Integer getMaxResponseDuration() {
        return maxResponseDuration;
    }

    public void setMaxResponseDuration(Integer maxResponseDuration) {
        this.maxResponseDuration = maxResponseDuration;
    }

    public Integer getTotalResponseDuration() {
        return totalResponseDuration;
    }

    public void setTotalResponseDuration(Integer totalResponseDuration) {
        this.totalResponseDuration = totalResponseDuration;
    }

    public Integer getChatRound() {
        return chatRound;
    }

    public void setChatRound(Integer chatRound) {
        this.chatRound = chatRound;
    }

    public Integer getFirstResponseDuration() {
        return firstResponseDuration;
    }

    public void setFirstResponseDuration(Integer firstResponseDuration) {
        this.firstResponseDuration = firstResponseDuration;
    }

    public Long getVisitorFirstMsgTime() {
        return visitorFirstMsgTime;
    }

    public void setVisitorFirstMsgTime(Long visitorFirstMsgTime) {
        this.visitorFirstMsgTime = visitorFirstMsgTime;
    }

    public Long getVisitorLastMsgTime() {
        return visitorLastMsgTime;
    }

    public void setVisitorLastMsgTime(Long visitorLastMsgTime) {
        this.visitorLastMsgTime = visitorLastMsgTime;
    }

    public Long getAgentFirstMsgTime() {
        return agentFirstMsgTime;
    }

    public void setAgentFirstMsgTime(Long agentFirstMsgTime) {
        this.agentFirstMsgTime = agentFirstMsgTime;
    }

    public Long getAgentLastMsgTime() {
        return agentLastMsgTime;
    }

    public void setAgentLastMsgTime(Long agentLastMsgTime) {
        this.agentLastMsgTime = agentLastMsgTime;
    }

    public Long getReceiveDuration() {
        return receiveDuration;
    }

    public void setReceiveDuration(Long receiveDuration) {
        this.receiveDuration = receiveDuration;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String[] getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String[] keyWords) {
        this.keyWords = keyWords;
    }

    public String getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(String searchEngine) {
        this.searchEngine = searchEngine;
    }

    public List<Map<String, Object>> getVisitTracks() {
        return visitTracks;
    }

    public void setVisitTracks(List<Map<String, Object>> visitTracks) {
        this.visitTracks = visitTracks;
    }

    public String getConsultUrl() {
        return consultUrl;
    }

    public void setConsultUrl(String consultUrl) {
        this.consultUrl = consultUrl;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Integer getLastMessageSenderType() {
        return lastMessageSenderType;
    }

    public void setLastMessageSenderType(Integer lastMessageSenderType) {
        this.lastMessageSenderType = lastMessageSenderType;
    }

    public Long getLastMessageCreateTime() {
        return lastMessageCreateTime;
    }

    public void setLastMessageCreateTime(Long lastMessageCreateTime) {
        this.lastMessageCreateTime = lastMessageCreateTime;
    }

    public Integer getNoReply() {
        return noReply;
    }

    public void setNoReply(Integer noReply) {
        this.noReply = noReply;
    }

    public Integer getOpenType() {
        return openType;
    }

    public void setOpenType(Integer openType) {
        this.openType = openType;
    }

    private String type;
    private String mainUniqueId;                // 主会话唯一标识
    private String uniqueId;                     // 会话唯一标识
    private Integer enterpriseId;                // 企业id
    private String nickName;                    // 昵称
    private String visitorId;                   // visitor 访客id，此时用系统生成的uuid进行标识，关联唯一的访客
    private String visitorName;                 // 访客姓名
    private String cno;                         // 座席号
    private String clientName;                  // 座席姓名
    private String qno;                         // 队列号
    private String qname;                       // 队列名
    private String source;                      // 渠道类型
    private String status;                      // 主会话状态
    private String detailStatus;                // 从会话状态
    private Integer chatType;                    // 会话类型
    private Integer detailChatType;              // 会话类型
    private String customerNumber;               // 客户号码
    private String customerName;                 // 客户姓名
    private String customerAreaCode;              // 客户号码区号
    private String customerProvince;             // 追加客户省份
    private String customerCity;                 // 追加客户城市
    private String ip;                          // ip地址
    private Integer invite;                       // 邀请座席 0：邀请、1：未邀请
    private String browser;                       // 浏览器型号
    private String operatingSystem;                // 操作系统
    private String website;                     // 接入来源
    private String visitFlow;             // 会话轨迹
    private Long startTime;                      // 开始时间
    private Long joinQueueTime;                 // 开始时间
    private Long endTime;                        // 结束时间
    private Long bridgeTime;                     // 接听时间

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 会话详情列表
     */
    private List<ChatMessage> chatMessages;
    /**
     * 渠道类型
     */
    private Integer contactType;

    /**
     * 满意度评分
     */
    private Integer keys;

    /**
     * 头像链接地址 url
     */
    private String headerUrl;

    /**
     * 结束类型
     */
    private Integer closeReason;

    /**
     * 结束时的会话状态
     */
    private Integer closeStatus;

    /**
     * 微信公众号的appId
     */
    private String appId;

    /**
     * 公众号名称
     */
    private String appName;

    /**
     * 父子文档 父类
     */
    private Map join;

    /**
     * 记录类型，用于区分主从会话记录
     */
    private RecordType recordType;

    /**
     * 小时
     */
    private Short hour;

    /**
     * 应答时间
     */
    private Long answerTime;


    /**
     * 会话时长（加工计算得出）
     */
    private Long bridgeDuration;

    /**
     * 总时长（加工计算得出）
     */
    private Long totalDuration;


    /**
     * 访客会话数
     */
    private Integer visitorChatCount;

    /**
     * 座席会话数
     */
    private Integer agentChatCount;

    /**
     * 系统消息数
     */
    private Integer systemChatCount;

    /**
     * 机器人消息数
     */
    private Integer robotChatCount;

    /**
     * 转出标记位 （0 未转出过/1 转出过）
     */
    private Integer transfer;

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
     * 最大响应时长
     */
    private Integer maxResponseDuration;


    /**
     * 总响应时长
     */
    private Integer totalResponseDuration;


    /**
     * 会话轮次
     */
    private Integer chatRound;


    /**
     * 首次响应时长
     */
    private Integer firstResponseDuration;

    //接待时长相关

    /**
     * 访客发送第一条消息的时间
     */
    private Long visitorFirstMsgTime;


    /**
     * 访客发送最后一条消息的时间
     */
    private Long visitorLastMsgTime;


    /**
     * 座席发送第一条消息的时间
     */
    private Long agentFirstMsgTime;


    /**
     * 座席发送最后一条消息的时间
     */
    private Long agentLastMsgTime;

    /**
     * 总接待时长
     */
    private Long receiveDuration;

    /**
     * 是否留言标识(0或Null 未留言 /1 已留言)
     */
    private Integer commentStatus;

    /**
     * 留言类型 (1 直接进留言 /2 排队转留言)
     */
    private Integer commentType;

    /**
     * 会话标签
     */
    private String[] tags;

    /**
     * 搜索关键词
     */
    private String[] keyWords;

    /**
     * 搜索引擎
     */
    private String searchEngine;

    /**
     * 访客轨迹
     */
    private List<Map<String, Object>> visitTracks;

    /**
     * 会话发起页
     */
    private String consultUrl;

    /**
     * 会话的最近一条消息
     */
    private String lastMessage;

    /**
     * 会话的最近一条消息的发送者类型
     */
    private Integer lastMessageSenderType;

    /**
     * 最新一条消息创建时间
     */
    private Long lastMessageCreateTime;

    /**
     * 会话类型 1或Null有回复 2没有回复
     */
    private Integer noReply;

    /**
     * 会话发起方式  1为访客发起  2为座席主动发起  3为座席从浏览中邀请
     */
    private Integer openType;
}
