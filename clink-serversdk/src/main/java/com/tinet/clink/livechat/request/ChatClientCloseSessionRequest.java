package com.tinet.clink.livechat.request;

import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.response.ChatClientCloseSessionResponse;

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
