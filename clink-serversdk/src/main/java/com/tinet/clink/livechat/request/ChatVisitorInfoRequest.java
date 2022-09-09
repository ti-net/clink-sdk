package com.tinet.clink.livechat.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatVisitorInfoResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author midong
 * @date 2022/6/8 15:48
 */
public class ChatVisitorInfoRequest extends AbstractRequestModel<ChatVisitorInfoResponse> {
    private String visitorId;
    private String accessId;


    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
        if (visitorId!=null){
            putQueryParameter("visitorId", visitorId);
        }
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
        if (accessId!=null){
            putQueryParameter("accessId", accessId);
        }
    }
    public ChatVisitorInfoRequest() {
        super(PathEnum.ChatVisitorUnreadCount.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ChatVisitorInfoResponse> getResponseClass() {
        return ChatVisitorInfoResponse.class;
    }

    @Override
    public String toString() {
        return "ChatVisitorInfoRequest{" +
                "visitorId='" + visitorId + '\'' +
                ", accessId='" + accessId + '\'' +
                '}';
    }
}