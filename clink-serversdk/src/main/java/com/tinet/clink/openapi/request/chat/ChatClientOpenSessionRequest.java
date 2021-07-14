package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatClientOpenSessionResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 客服开始会话
 *
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatClientOpenSessionRequest extends AbstractRequestModel<ChatClientOpenSessionResponse> {

    public ChatClientOpenSessionRequest() {
        super(PathEnum.ChatClientOpenSession.value(), HttpMethodType.POST);
    }

    /**
     * 客服座席
     */
    private String cno;

    /**
     * 现有会话id
     */
    private String sessionId;

    /**
     * 现有会话开始时间(毫秒值)
     */
    private Long startTime;

    public void setCno(String cno) {
        putQueryParameter("cno", cno);
        this.cno = cno;
    }

    public void setSessionId(String sessionId) {
        putQueryParameter("sessionId", sessionId);
        this.sessionId = sessionId;
    }

    public void setStartTime(Long startTime) {
        putQueryParameter("startTime", startTime);
        this.startTime = startTime;
    }

    public String getCno() {
        return cno;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Long getStartTime() {
        return startTime;
    }

    @Override
    public Class<ChatClientOpenSessionResponse> getResponseClass() {
        return ChatClientOpenSessionResponse.class;
    }

    @Override
    public String toString() {
        return "ChatClientOpenSessionRequest{" +
                "cno='" + cno + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", startTime='" + startTime + '\'' +
                "} " + super.toString();
    }
}
