package com.tinet.clink.openapi.model;

import java.util.List;

/**
 * 用于封装返回给前端的cdr通话记录数据model
*/
public class ChatDetail {

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
}
