package com.tinet.clink.cc.response.assistant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.core.response.ResponseModel;

/**
 * Class For:
 * 助手数据推送接口响应
 *
 * @author Tinet-yinzk
 * @date 2024/3/19 15:07
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushAssistantDataResponse extends ResponseModel {
    /**
     * 推送结果
     */
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}