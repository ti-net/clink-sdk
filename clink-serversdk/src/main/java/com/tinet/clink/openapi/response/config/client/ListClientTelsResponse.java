package com.tinet.clink.openapi.response.config.client;

import com.tinet.clink.openapi.model.ClientTelModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author lizy
 * @date 2018/11/22
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListClientTelsResponse extends ResponseModel {

    private List<ClientTelModel> clientTels;

    public List<ClientTelModel> getClientTels() {
        return clientTels;
    }

    public void setClientTels(List<ClientTelModel> clientTels) {
        this.clientTels = clientTels;
    }
}
