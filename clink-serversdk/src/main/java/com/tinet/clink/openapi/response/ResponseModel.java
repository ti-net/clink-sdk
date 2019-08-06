package com.tinet.clink.openapi.response;

/**
 * @author houfc
 */
public abstract class ResponseModel {
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
