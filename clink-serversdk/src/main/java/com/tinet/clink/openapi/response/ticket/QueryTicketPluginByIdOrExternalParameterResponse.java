package com.tinet.clink.openapi.response.ticket;

import com.tinet.clink.openapi.model.TicketPluginModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @Description 根据外部参数或ID查询工单插件的响应model
 * @Author LinYang
 * @Date 2022/7/21
 * @Version 1.0
 **/
public class QueryTicketPluginByIdOrExternalParameterResponse extends ResponseModel {

    private TicketPluginModel result;

    public TicketPluginModel getResult() {
        return result;
    }

    public void setResult(TicketPluginModel result) {
        this.result = result;
    }
}
