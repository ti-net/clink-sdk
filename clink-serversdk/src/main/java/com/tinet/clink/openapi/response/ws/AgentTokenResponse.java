package com.tinet.clink.openapi.response.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.openapi.response.PagedResponse;

/**
 * @author libin
 * @date 2022-04-12 6:30 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentTokenResponse extends PagedResponse {

    private String agentToken;

    public String getAgentToken() {
        return agentToken;
    }

    public void setAgentToken(String agentToken) {
        this.agentToken = agentToken;
    }
}
