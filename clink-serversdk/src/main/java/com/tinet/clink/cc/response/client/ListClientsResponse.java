package com.tinet.clink.cc.response.client;



import com.tinet.clink.cc.model.ClientSearchResultModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * 查询座席列表响应
 *
 * @author lizy
 * @date 2018/10/29
 */
public class ListClientsResponse extends PagedResponse {

    List<ClientSearchResultModel> clients;

    public List<ClientSearchResultModel> getClients() {
        return clients;
    }

    public void setClients(List<ClientSearchResultModel> clients) {
        this.clients = clients;
    }
}
