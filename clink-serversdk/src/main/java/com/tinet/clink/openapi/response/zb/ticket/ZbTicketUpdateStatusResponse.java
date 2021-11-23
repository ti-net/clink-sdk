package com.tinet.clink.openapi.response.zb.ticket;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author liuhy
 * @date: 2021/11/3
 **/
public class ZbTicketUpdateStatusResponse extends ResponseModel {

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