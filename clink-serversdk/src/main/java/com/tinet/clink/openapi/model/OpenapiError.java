package com.tinet.clink.openapi.model;

/**
 * @author houfc
 * @date 2018/10/16
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  OpenapiError {
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