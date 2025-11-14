package com.tinet.smartlink.openapi.exceptions;

/**
 * @author houfc
 * @date 2018/11/26
 */
public class ServerException extends Exception {

    private String requestId;

    private String code;

    private String message;

    private ErrorType errorType;

    public ServerException(String code, String message, String requestId) {
        this.requestId = requestId;
        this.code = code;
        this.message = message;
        this.errorType = ErrorType.Server;
    }

    public ServerException(String code, String message) {
        this.code = code;
        this.message = message;
        this.errorType = ErrorType.Server;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }
}
