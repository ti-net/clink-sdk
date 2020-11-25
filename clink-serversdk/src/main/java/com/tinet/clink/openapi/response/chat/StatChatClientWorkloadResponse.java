package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.response.ResponseModel;

public class StatChatClientWorkloadResponse extends ResponseModel {

    private Integer status;

    private String message;

    private StatChatClientResponseModel result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatChatClientResponseModel getResult() {
        return result;
    }

    public void setResult(StatChatClientResponseModel result) {
        this.result = result;
    }
}
