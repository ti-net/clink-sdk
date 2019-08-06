package com.tinet.clink.openapi.response.config.client;

import com.tinet.clink.openapi.model.ClientCreateResultModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 新增座席响应
 * @author lizy
 * @date 2018/10/24
 */
public class CreateClientResponse extends ResponseModel {

    ClientCreateResultModel client;

    public ClientCreateResultModel getClient() {
        return client;
    }

    public void setClient(ClientCreateResultModel client) {
        this.client = client;
    }
}
