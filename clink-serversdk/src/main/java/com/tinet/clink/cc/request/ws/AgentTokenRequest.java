package com.tinet.clink.cc.request.ws;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.ws.AgentTokenResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


import java.util.Objects;

/**
 * @author libin
 * @date 2022-04-12 6:29 下午
 */
public class AgentTokenRequest extends AbstractRequestModel<AgentTokenResponse> {
    /**
     * 认证token
     */
    private String authToken;

    /**
     * 座席工号
     */
    private String cno;

    /**
     * 座席姓名
     */
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (Objects.nonNull(name)) {
            putBodyParameter("name", name);
        }
    }

    public AgentTokenRequest() {
        super(PathEnum.wsAgentToken.value(), HttpMethodType.POST);
    }

    @Override
    public Class<AgentTokenResponse> getResponseClass() {
        return AgentTokenResponse.class;
    }
}
