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
     * 渠道名称
     */
    private String appName;

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

    private String ip;

    private String province;

    private String city;

    private String browser;

    private String operatingSystem;

    private String website;

    /**
     * 客户资料信息
     */
    private Map<String, Object> customerFields;

    private Map<String, Object> extraInfo;

    private Map<String, Object> visitorExtraInfo;

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppName(String appName) {
        this.appName = appName;
        putQueryParameter("appName", appName);
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

    public void setIp(String ip) {
        this.ip = ip;
        putQueryParameter("ip", ip);
    }

    public void setProvince(String province) {
        this.province = province;
        putQueryParameter("province", province);
    }

    public void setCity(String city) {
        this.city = city;
        putQueryParameter("city", city);
    }

    public void setBrowser(String browser) {
        this.browser = browser;
        putQueryParameter("browser", browser);
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
        putQueryParameter("operatingSystem", operatingSystem);
    }

    public void setWebsite(String website) {
        this.website = website;
        putQueryParameter("website", website);
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

    public String getAppName() {
        return appName;
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

    public String getIp() {
        return ip;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getBrowser() {
        return browser;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getWebsite() {
        return website;
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
                ", appName='" + appName + '\'' +
                ", visitorId='" + visitorId + '\'' +
                ", visitorName='" + visitorName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", ip='" + ip + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", browser='" + browser + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", website='" + website + '\'' +
                ", customerFields=" + customerFields +
                ", extraInfo=" + extraInfo +
                ", visitorExtraInfo=" + visitorExtraInfo +
                "} " + super.toString();
    }
}
