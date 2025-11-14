package com.tinet.clink.cc.response.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.core.response.ResponseModel;

/**
 * @Author: zhoujiang
 * @Date: 2023/10/19 18:27
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnterpriseSessionKeyResponse extends ResponseModel {

    private String sessionKey;

    private String wsUrl;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getWsUrl() {
        return wsUrl;
    }

    public void setWsUrl(String wsUrl) {
        this.wsUrl = wsUrl;
    }
}
