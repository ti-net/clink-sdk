package com.tinet.clink.openapi.response.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author libin
 * @date 2022-04-12 6:22 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  AuthTokenResponse extends ResponseModel {

    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
