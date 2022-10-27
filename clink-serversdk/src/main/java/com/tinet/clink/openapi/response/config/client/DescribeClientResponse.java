package com.tinet.clink.openapi.response.config.client;

import com.tinet.clink.openapi.model.ClientDetailModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 查询座席详情响应
 *
 * @author lizy
 * @date 2018/10/24
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  DescribeClientResponse extends ResponseModel {

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
