package com.tinet.clink.livechat.request;

import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.response.ChatRobotTransferResponse;

/**
 * 访客转人工
 *
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatRobotTransferRequest extends AbstractRequestModel<ChatRobotTransferResponse> {

    public ChatRobotTransferRequest() {
        super(PathEnum.ChatRobotTransfer.value(), HttpMethodType.POST);
    }

    /**
     * 会话id
     */
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
        putQueryParameter("sessionId", sessionId);
    }

    @Override
    public Class<ChatRobotTransferResponse> getResponseClass() {
        return ChatRobotTransferResponse.class;
    }

    @Override
    public String toString() {
        return "ChatRobotTransferRequest{" +
                "sessionId='" + sessionId + '\'' +
                "} " + super.toString();
    }
}
