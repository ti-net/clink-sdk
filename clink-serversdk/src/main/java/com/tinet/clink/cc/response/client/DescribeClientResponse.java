package com.tinet.clink.cc.response.client;

import com.tinet.clink.cc.model.ClientDetailModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * 查询座席详情响应
 *
 * @author lizy
 * @date 2018/10/24
 */
public class DescribeClientResponse extends ResponseModel {

    /**
     * 座席详情
     */
    private ClientDetailModel client;

    public ClientDetailModel getClient() {
        return client;
    }

    public void setClient(ClientDetailModel client) {
        this.client = client;
    }
}
