package com.tinet.clink.openapi.response.config.client;

import com.tinet.clink.openapi.model.ClientUpdateResultModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 更新座席响应
 *
 * @author lizy
 * @date 2018/10/24
 */
public class UpdateClientResponse extends ResponseModel {

    /**
     * 更新座席的结果model(同传入参数)
     */
    ClientUpdateResultModel client;

    public ClientUpdateResultModel getClient() {
        return client;
    }

    public void setClient(ClientUpdateResultModel client) {
        this.client = client;
    }
}
