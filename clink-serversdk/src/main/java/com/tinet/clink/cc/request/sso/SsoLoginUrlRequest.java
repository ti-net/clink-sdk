package com.tinet.clink.cc.request.sso;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.sso.SsoLoginUrlResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author: wangpw
 * @date: 2021/11/9
 */
public class SsoLoginUrlRequest extends AbstractRequestModel<SsoLoginUrlResponse> {


    private Integer loginType;


    private String username;

    private String staticUrl;

    private Integer sidebarDisplay;

    private Integer topbarDisplay;

    private Integer autoLoginCc;

    private Integer autoLoginLiveChat;


    public SsoLoginUrlRequest() {
        super(PathEnum.SsoLoginToken.value(), HttpMethodType.GET);
    }

    public void setLoginType(Integer loginType) {
        putQueryParameter("loginType", loginType);
        this.loginType = loginType;
    }

    public void setUsername(String username) {
        putQueryParameter("username", username);
        this.username = username;
    }

    public void setStaticUrl(String staticUrl){
        putQueryParameter("staticUrl",staticUrl);
        this.staticUrl = staticUrl;
    }

    public void setSidebarDisplay(Integer sidebarDisplay) {
        putQueryParameter("sidebarDisplay",sidebarDisplay);
        this.sidebarDisplay = sidebarDisplay;
    }

    public void setTopbarDisplay(Integer topbarDisplay) {
        putQueryParameter("topbarDisplay",topbarDisplay);
        this.topbarDisplay = topbarDisplay;
    }

    public void setAutoLoginCc(Integer autoLoginCc) {
        putQueryParameter("autoLoginCc",autoLoginCc);
        this.autoLoginCc = autoLoginCc;
    }

    public void setAutoLoginLiveChat(Integer autoLoginLiveChat) {
        putQueryParameter("autoLoginLiveChat",autoLoginLiveChat);
        this.autoLoginLiveChat = autoLoginLiveChat;
    }

    public Integer getAutoLoginLiveChat() {
        return autoLoginLiveChat;
    }

    public Integer getAutoLoginCc() {
        return autoLoginCc;
    }

    public Integer getSidebarDisplay() {
        return sidebarDisplay;
    }

    public Integer getTopbarDisplay() {
        return topbarDisplay;
    }

    public String getStaticUrl(){
        return staticUrl;
    }


    public Integer getLoginType() {
        return loginType;
    }

    public String getUsername() {
        return username;
    }


    @Override
    public Class<SsoLoginUrlResponse> getResponseClass() {
        return SsoLoginUrlResponse.class;

    }

    @Override
    public String toString() {
        return "SsoLoginUrlRequest{" +
                "loginType=" + loginType +
                ", username='" + username + '\'' +
                ", staticUrl='" + staticUrl + '\'' +
                ", sidebarDisplay=" + sidebarDisplay +
                ", topbarDisplay=" + topbarDisplay +
                ", autoLoginCc=" + autoLoginCc +
                ", autoLoginLiveChat=" + autoLoginLiveChat +
                '}';
    }
}
