package com.tinet.clink.cc.request.ws;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ws.AuthTokenResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * @author libin
 * @date 2022-04-12 6:21 下午
 */
public class AuthTokenRequest extends AbstractRequestModel<AuthTokenResponse> {

    /**
     * 管理员账号
     */
    private String username;

    /**
     * 管理员密码
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        if (Objects.nonNull(username)) {
            putBodyParameter("username",username);
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        if (Objects.nonNull(password)){
            putBodyParameter("password",password);
        }
    }

    public AuthTokenRequest() {
            super(PathEnum.wsAuthToken.value(), HttpMethodType.POST);
    }

    @Override
    public Class<AuthTokenResponse> getResponseClass() {
        return AuthTokenResponse.class;
    }
}
