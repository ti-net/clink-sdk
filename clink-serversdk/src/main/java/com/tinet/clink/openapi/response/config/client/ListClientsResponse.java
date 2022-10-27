package com.tinet.clink.openapi.response.config.client;

import com.tinet.clink.openapi.model.ClientSearchResultModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 查询座席列表响应
 *
 * @author lizy
 * @date 2018/10/29
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListClientsResponse extends PagedResponse {

    List<ClientSearchResultModel> clients;

    public List<ClientSearchResultModel> getClients() {
        return clients;
    }

    public void setClients(List<ClientSearchResultModel> clients) {
        this.clients = clients;
    }
}
