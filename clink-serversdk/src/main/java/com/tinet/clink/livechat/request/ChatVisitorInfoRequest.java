package com.tinet.clink.livechat.request;

import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.response.ChatVisitorInfoResponse;

/**
 * @author midong
 * @date 2022/6/8 15:48
 */
public class ChatVisitorInfoRequest extends AbstractRequestModel<ChatVisitorInfoResponse> {
    private String visitorId;
    @Deprecated
    private String accessId;
    private String appId;


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
        return appId;
    }

    @Deprecated
    public void setAccessId(String accessId) {
        this.appId = accessId;
        if (accessId!=null){
            putQueryParameter("appId", accessId);
        }
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
        if (appId!=null){
            putQueryParameter("appId", appId);
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
                ", appId='" + appId + '\'' +
                '}';
    }
}
