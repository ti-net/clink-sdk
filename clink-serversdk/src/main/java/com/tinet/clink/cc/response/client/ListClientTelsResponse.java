package com.tinet.clink.cc.response.client;

import com.tinet.clink.cc.model.ClientTelModel;
import com.tinet.clink.core.response.ResponseModel;

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
