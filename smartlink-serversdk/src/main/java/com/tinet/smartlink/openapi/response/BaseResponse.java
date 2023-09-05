package com.tinet.smartlink.openapi.response;

/**
 * @author houfc
 * @date 2018/11/26
 */
public abstract class BaseResponse {
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
