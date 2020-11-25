package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.response.ResponseModel;

public class StatChatQueueWorkloadResponse extends ResponseModel {
    private Integer status;

    private String message;

    private StatChatQueueResponseModel result;

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

    public StatChatQueueResponseModel getResult() {
        return result;
    }

    public void setResult(StatChatQueueResponseModel result) {
        this.result = result;
    }
}
