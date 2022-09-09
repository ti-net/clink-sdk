package com.tinet.clink.crm.model;

/**
 * @author houfc
 * @date 2018/10/16
 */
public class OpenapiError {
    private int httpStatus;
    private String requestId;
    private ErrorCode error;

    public OpenapiError() {
    }

    public OpenapiError(String code, String message) {
        this.error = new ErrorCode(code, message);
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public ErrorCode getError() {
        return error;
    }

    public void setError(ErrorCode error) {
        this.error = error;
    }
}