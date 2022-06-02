package com.tinet.clink.openapi.response.config.client;

import com.tinet.clink.openapi.model.ClientObClidModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author wangli
 * @date 2022-05-30 7:16 PM
 */
public class DescribeClientObClidResponse extends ResponseModel {

    /**
     * 座席详情
     */
    private ClientObClidModel clientObClid;

    public ClientObClidModel getClientObClidModel() {
        return clientObClid;
    }

    public void setClientObClidModel(ClientObClidModel clientObClid) {
        this.clientObClid = clientObClid;
    }

}
