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

    public Integer getChatType() {
        return chatType;
    }

    public void setChatType(Integer chatType) {
        this.chatType = chatType;
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

    /**
     * 会话唯一标识
     */
    private String uniqueId;
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
     * 1、接入座席，2、转移座席，3、三方座席
     */
    private Integer chatType;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;
}
