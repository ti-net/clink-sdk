package com.tinet.clink.openapi.model;

import java.util.List;

/**
 * 用于封装返回给前端的cdr通话记录数据model
*/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ChatDetail {

    /**
     * 主会话唯一标识
     */
    private String mainUniqueId;
    /**
     * 会话唯一标识
     */
    private String uniqueId;
    /**
     * 访客姓名
     */
    private String visitorName;

    /**
     * 访客ID
     */
    private String visitorId;
    /**
     * 座席号
     */
    private String cno;
    /**
     * 座席名称
     */
    private String clientName;

    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 会话类型 1、接入座席，2、转移座席，5、机器人，6、群聊/三方，7、排队面板处理，8、排队面板分配
     */
    private Integer chatType;


    /**
     * 机器人id
     */
    private String robotId;

    /**
     * 机器人名
     */
    private String robotName;

    /**
     * 最后一条消息发送者
     */
    private Integer lastSenderMessageType;

    /**
     * 机器人有效会话
     */
    private Integer botChatValid;

    /**
     * 机器人会话轮数
     */
    private Integer botRound;

    /**
     * 访客消息数
     */
    private Integer visitorChatCount = 0;

    /**
     * 座席消息数
     */
    private Integer agentChatCount = 0;

    /**
     * 机器人消息数
     */
    private Integer botChatCount = 0;

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
     * 满意度发起方式 1：座席主动发起、2：系统邀评、3: 访客主动发起
     */
    private Integer invitationInitiator;


    public Integer getInvitationInitiator() { return invitationInitiator; }

    public void setInvitationInitiator(Integer invitationInitiator) { this.invitationInitiator = invitationInitiator; }

    public String getMainUniqueId() { return mainUniqueId; }

    public void setMainUniqueId(String mainUniqueId) { this.mainUniqueId = mainUniqueId; }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getChatType() {
        return chatType;
    }

    public void setChatType(Integer chatType) {
        this.chatType = chatType;
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

    public Integer getLastSenderMessageType() {
        return lastSenderMessageType;
    }

    public void setLastSenderMessageType(Integer lastSenderMessageType) {
        this.lastSenderMessageType = lastSenderMessageType;
    }

    public Integer getBotChatValid() {
        return botChatValid;
    }

    public void setBotChatValid(Integer botChatValid) {
        this.botChatValid = botChatValid;
    }

    public Integer getBotRound() {
        return botRound;
    }

    public void setBotRound(Integer botRound) {
        this.botRound = botRound;
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

    public Integer getBotChatCount() {
        return botChatCount;
    }

    public void setBotChatCount(Integer botChatCount) {
        this.botChatCount = botChatCount;
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
}
