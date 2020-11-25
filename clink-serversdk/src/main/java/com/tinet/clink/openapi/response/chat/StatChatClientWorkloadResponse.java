package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 在线客服-座席工作量报表 Response
 *
 * @author ningkun
 * @date 2020/11/25
 */
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
