package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author wangpw
 * @date 2021年5月14日
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ChatMessageToVisitorResponse extends ResponseModel {


    /**
     * 会话id
     */
    private String sessionId;

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

    @Override
    public String toString() {
        return "ChatMessageToVisitorResponse{" +
                "sessionId='" + sessionId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", sendStatus=" + sendStatus +
                ", createTime=" + createTime +
                ", sensitiveWord='" + sensitiveWord + '\'' +
                "} " + super.toString();
    }
}
