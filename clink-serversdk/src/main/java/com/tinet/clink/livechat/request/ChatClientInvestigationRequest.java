package com.tinet.clink.livechat.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatClientInvestigationResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 客服发起满意度评价
 *
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatClientInvestigationRequest extends AbstractRequestModel<ChatClientInvestigationResponse> {

    public ChatClientInvestigationRequest() {
        super(PathEnum.ChatClientInvestigation.value(), HttpMethodType.POST);
    }

    /**
     * 客服座席
     */
    private String cno;

    /**
     * 现有会话id
     */
    private String sessionId;


    public void setCno(String cno) {
        putQueryParameter("cno", cno);
        this.cno = cno;
    }

    public void setSessionId(String sessionId) {
        putQueryParameter("sessionId", sessionId);
        this.sessionId = sessionId;
    }

    public String getCno() {
        return cno;
    }

    public String getSessionId() {
        return sessionId;
    }


    @Override
    public Class<ChatClientInvestigationResponse> getResponseClass() {
        return ChatClientInvestigationResponse.class;
    }

    @Override
    public String toString() {
        return "ChatClientInvestigationRequest{" +
                "cno='" + cno + '\'' +
                ", sessionId='" + sessionId + '\'' +
                "} " + super.toString();
    }
}
