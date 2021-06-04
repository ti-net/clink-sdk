package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatVisitorOpenSessionResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Map;

/**
 * 访客开始会话
 *
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatVisitorOpenSessionRequest extends AbstractRequestModel<ChatVisitorOpenSessionResponse> {

    public ChatVisitorOpenSessionRequest() {
        super(PathEnum.ChatVisitorOpenSession.value(), HttpMethodType.POST);
    }

    /**
     * 渠道id
     */
    private String appId;

    /**
     * 访客id
     */
    private String visitorId;

    /**
     * 访客名称
     */
    private String visitorName;

    /**
     * 访客头像
     */
    private String avatar;

    /**
     * 客户资料信息
     */
    private Map<String, Object> customerFields;

    private Map<String, Object> extraInfo;

    private Map<String, Object> visitorExtraInfo;

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
        putQueryParameter("visitorId", visitorId);
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
        putQueryParameter("visitorName", visitorName);
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        putQueryParameter("avatar", avatar);
    }

    public void setCustomerFields(Map<String, Object> customerFields) {
        this.customerFields = customerFields;
        putQueryParameter("customerFields", customerFields);
    }

    public void setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
        putQueryParameter("extraInfo", extraInfo);
    }

    public void setVisitorExtraInfo(Map<String, Object> visitorExtraInfo) {
        this.visitorExtraInfo = visitorExtraInfo;
        putQueryParameter("visitorExtraInfo", visitorExtraInfo);
    }

    public String getAppId() {
        return appId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public String getAvatar() {
        return avatar;
    }

    public Map<String, Object> getCustomerFields() {
        return customerFields;
    }

    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    public Map<String, Object> getVisitorExtraInfo() {
        return visitorExtraInfo;
    }

    @Override
    public Class<ChatVisitorOpenSessionResponse> getResponseClass() {
        return ChatVisitorOpenSessionResponse.class;
    }

    @Override
    public String toString() {
        return "ChatVisitorOpenSessionRequest{" +
                "appId='" + appId + '\'' +
                ", visitorId='" + visitorId + '\'' +
                ", visitorName='" + visitorName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", customerFields=" + customerFields +
                ", extraInfo=" + extraInfo +
                ", visitorExtraInfo=" + visitorExtraInfo +
                "} " + super.toString();
    }
}
