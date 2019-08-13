package com.tinet.clink.openapi.exceptions;

public class ClientException extends Exception {

    private String requestId;
    private String errCode;
    private String errMsg;

    public ClientException(String requestId, String errCode, String errMsg) {
        this(errCode, errMsg);
        this.requestId = requestId;
    }

    public ClientException(String errCode, String errMsg, Throwable cause) {
        super(errCode + " : " + errMsg, cause);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public ClientException(String errCode, String errMsg) {
        super(errCode + " : " + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + (null == getRequestId() ? "" : "\r\nRequestId : " + getRequestId());
    }

}
