package com.tinet.clink.openapi.request.sso;

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
                "} " + super.toString();
    }
}
