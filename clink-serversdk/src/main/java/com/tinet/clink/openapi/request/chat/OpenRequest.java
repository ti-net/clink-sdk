package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.OpenResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 发起会话
 *
 * @author wangzb
 * @date 2020/8/8
 */
public class OpenRequest extends AbstractRequestModel<OpenResponse> {

    public OpenRequest() {
        super(PathEnum.ChatOpen.value(), HttpMethodType.POST);
    }

    @Override
    public Class<OpenResponse> getResponseClass() {
        return OpenResponse.class;
    }

    /**
     * 接口接入的appId/accessId
     */
    private String appId;

    /**
     * 访客Id
     */
    private String visitorId;

    /**
     * 访客昵称/姓名
     */
    private String visitorName;

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

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
        if (visitorName != null) {
            putBodyParameter("visitorName", visitorName);
        }
    }
}
