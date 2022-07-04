package com.tinet.smartlink.openapi.exceptions;

/**
 * @author houfc
 * @date 2018/11/27
 */
public class ErrorModel {

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
