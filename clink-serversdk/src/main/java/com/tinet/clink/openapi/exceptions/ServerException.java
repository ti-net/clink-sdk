package com.tinet.clink.openapi.exceptions;

/**
 * @author houfc
 */
public class ServerException extends Exception {

    private String requestId;
    private String code;
    private String message;

    public ServerException(String requestId, String code, String message) {
        this(code, message);
        this.requestId = requestId;
    }

    public ServerException(String code, String message) {
        super(code + " : " + message);
        this.code = code;
        this.message = message;
    }

    public ServerException(String code, String message, Throwable cause) {
        super(code + " : " + message,cause);
        this.code = code;
        this.message = message;
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
}
