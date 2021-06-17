package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatMessageToVisitorResponse extends ResponseModel {


    /**
     * 会话id
     */
    private String sessionId;

    /**
     * 访客id
     */
    private String visitorId;

    /**
     * 消息id
     */
    private String messageId;

    /**
     * 发送状态
     */
    private Integer sendStatus;

    /**
     * 消息创建时间
     */
    private Long createTime;

    /**
     * 包含敏感词内容
     */
    private String sensitiveWord;


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getSensitiveWord() {
        return sensitiveWord;
    }

    public void setSensitiveWord(String sensitiveWord) {
        this.sensitiveWord = sensitiveWord;
    }
}
