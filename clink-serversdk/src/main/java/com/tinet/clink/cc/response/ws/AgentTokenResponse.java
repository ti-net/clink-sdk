package com.tinet.clink.cc.response.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.core.response.ResponseModel;

/**
 * @author libin
 * @date 2022-04-12 6:30 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentTokenResponse extends ResponseModel {

    private String agentToken;

    public String getAgentToken() {
        return agentToken;
    }

    public void setAgentToken(String agentToken) {
        this.agentToken = agentToken;
    }
}
