package com.tinet.clink.cc.request.ws;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.ws.AgentLoginResponse;
import com.tinet.clink.cc.response.ws.AuthTokenResponse;
import com.tinet.clink.core.request.AbstractRequestModel;

import java.util.Objects;

/**
 * 使用 ram 鉴权token 进行ws 登录
 *
 * @author Tinet-yinzk
 * @date 2024-03-14
 */
public class AgentLoginRequest extends AbstractRequestModel<AgentLoginResponse> {
    /**
     * 认证token
     *
     * @see AuthTokenResponse#getAuthToken()
     */
    private String authToken;

    /**
     * 座席工号 （必填）
     */
    private String cno;

    /**
     * ram 提供的鉴权token （必填）
     */
    private String ramToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
        if (Objects.nonNull(authToken)) {
            putBodyParameter("authToken", authToken);
        }
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (Objects.nonNull(cno)) {
            putBodyParameter("cno", cno);
        }
    }

    public String getRamToken() {
        return ramToken;
    }

    public void setRamToken(String ramToken) {
        this.ramToken = ramToken;
        if (Objects.nonNull(ramToken)) {
            putBodyParameter("ramToken", ramToken);
        }
    }


    public AgentLoginRequest() {
        super(PathEnum.wsAgentToken.value(), com.tinet.clink.core.utils.HttpMethodType.POST);
    }

    @Override
    public Class<AgentLoginResponse> getResponseClass() {
        return AgentLoginResponse.class;
    }
}
