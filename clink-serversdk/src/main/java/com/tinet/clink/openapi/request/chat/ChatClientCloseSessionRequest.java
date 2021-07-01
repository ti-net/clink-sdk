package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatClientCloseSessionResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 客服结束会话
 *
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatClientCloseSessionRequest extends AbstractRequestModel<ChatClientCloseSessionResponse> {

    public ChatClientCloseSessionRequest() {
        super(PathEnum.ChatClientCloseSession.value(), HttpMethodType.POST);
    }

    /**
     * 会话id
     */
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        putQueryParameter("sessionId", sessionId);
        this.sessionId = sessionId;
    }

    @Override
    public Class<ChatClientCloseSessionResponse> getResponseClass() {
        return ChatClientCloseSessionResponse.class;
    }

    @Override
    public String toString() {
        return "ChatClientCloseSessionRequest{" +
                "sessionId='" + sessionId + '\'' +
                "} " + super.toString();
    }
}
