package com.tinet.clink.openapi.response.sso;

import com.tinet.clink.openapi.response.PagedResponse;

/**
 * @author: wangpw
 * @date: 2021/11/9
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  SsoLoginUrlResponse extends PagedResponse {

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
