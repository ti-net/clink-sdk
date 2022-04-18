package com.tinet.clink.openapi.response.dada;

import com.tinet.clink.openapi.response.ResponseModel;

import java.util.Map;

/**
 * 登录响应
 *
 * @author lizy
 * @date 2018/10/24
 */
public class GetHotlineResponse extends ResponseModel {

    private Map<String, Object> result;

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
