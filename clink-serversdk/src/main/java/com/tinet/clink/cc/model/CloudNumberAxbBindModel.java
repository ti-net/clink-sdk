package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class For:
 * 云手机axb绑定结果
 *
 * @author Tinet-yinzk
 * @date 2023/11/28 20:49
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudNumberAxbBindModel {
    /**
     * ax 绑定授权id
     */
    private String authId;
    /**
     * axb 绑定id
     */
    private String bindId;

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }
}
