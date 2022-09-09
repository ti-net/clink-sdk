package com.tinet.clink.cc.response.sso;

import com.tinet.clink.openapi.response.PagedResponse;

/**
 * @author: wangpw
 * @date: 2021/11/9
 */
public class SsoLoginUrlResponse extends PagedResponse {

    /**
     * 登录url
     */
    private String loginUrl;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    public String toString() {
        return "SsoLoginUrlResponse{" +
                "loginUrl='" + loginUrl + '\'' +
                "} " + super.toString();
    }
}
