package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.CloseResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 关闭会话
 *
 * @author wangzb
 * @date 2020/8/8
 */
public class CloseRequest extends AbstractRequestModel<CloseResponse> {

    public CloseRequest() {
        super(PathEnum.ChatClose.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CloseResponse> getResponseClass() {
        return CloseResponse.class;
    }

    private String appId;

    private String visitorId;

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
}
