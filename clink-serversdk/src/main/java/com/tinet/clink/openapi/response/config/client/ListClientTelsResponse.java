package com.tinet.clink.openapi.response.config.client;

import com.tinet.clink.openapi.model.ClientTelModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author lizy
 * @date 2018/11/22
 */
public class ListClientTelsResponse extends ResponseModel {

    private List<ClientTelModel> clientTels;

    public List<ClientTelModel> getClientTels() {
        return clientTels;
    }

    public void setClientTels(List<ClientTelModel> clientTels) {
        this.clientTels = clientTels;
    }
}
