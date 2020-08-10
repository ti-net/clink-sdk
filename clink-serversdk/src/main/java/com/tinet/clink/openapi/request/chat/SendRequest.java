package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.model.chat.MessagePayload;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.SendResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 发送消息
 *
 * @author wangzb
 * @date 2020/8/8
 */
public class SendRequest extends AbstractRequestModel<SendResponse> {

    public SendRequest() {
        super(PathEnum.ChatSend.value(), HttpMethodType.POST);
    }

    @Override
    public Class<SendResponse> getResponseClass() {
        return SendResponse.class;
    }

    private String appId;

    private String visitorId;

    private String messageType;

    private MessagePayload payload;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
        if (appId != null) {
            putBodyParameter("appId", appId);
        }
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
        if (visitorId != null) {
            putBodyParameter("visitorId", visitorId);
        }
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
        if (messageType != null) {
            putBodyParameter("messageType", messageType);
        }
    }

    public MessagePayload getPayload() {
        return payload;
    }

    public void setPayload(MessagePayload payload) {
        this.payload = payload;
        if (payload != null) {
            putBodyParameter("payload", payload);
        }
    }
}
