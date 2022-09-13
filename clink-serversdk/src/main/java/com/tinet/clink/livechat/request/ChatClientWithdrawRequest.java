package com.tinet.clink.livechat.request;

import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.response.ChatClientWithdrawResponse;

/**
 * 客服撤回消息
 *
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatClientWithdrawRequest extends AbstractRequestModel<ChatClientWithdrawResponse> {

    public ChatClientWithdrawRequest() {
        super(PathEnum.ChatClientWithdraw.value(), HttpMethodType.POST);
    }

    /**
     * 主会话标识
     */
    private String sessionId;

    /**
     * 访客ID
     */
    private String visitorId;

    /**
     * 消息发送人座席工号
     */
    private String cno;

    /**
     * 消息唯一标识
     */
    private String messageId;

    public void setSessionId(String sessionId) {
        putQueryParameter("sessionId", sessionId);
        this.sessionId = sessionId;
    }

    public void setVisitorId(String visitorId) {
        putQueryParameter("visitorId", visitorId);
        this.visitorId = visitorId;
    }

    public void setCno(String cno) {
        putQueryParameter("cno", cno);
        this.cno = cno;
    }

    public void setMessageId(String messageId) {
        putQueryParameter("messageId", messageId);
        this.messageId = messageId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public String getCno() {
        return cno;
    }

    public String getMessageId() {
        return messageId;
    }

    @Override
    public Class<ChatClientWithdrawResponse> getResponseClass() {
        return ChatClientWithdrawResponse.class;
    }

    @Override
    public String toString() {
        return "ChatClientWithdrawRequest{" +
                "sessionId='" + sessionId + '\'' +
                ", visitorId='" + visitorId + '\'' +
                ", cno='" + cno + '\'' +
                ", messageId='" + messageId + '\'' +
                "} " + super.toString();
    }
}
