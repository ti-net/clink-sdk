package com.tinet.clink.openapi.response.ticket;

import com.tinet.clink.openapi.model.TicketPluginModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @Description 根据外部参数或ID查询工单插件的响应model
 * @Author LinYang
 * @Date 2022/7/21
 * @Version 1.0
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  QueryTicketPluginResponse extends ResponseModel {

    private TicketPluginModel ticketPlugin;

    public TicketPluginModel getTicketPlugin() {
        return ticketPlugin;
    }

    public void setTicketPlugin(TicketPluginModel ticketPlugin) {
        this.ticketPlugin = ticketPlugin;
    }
}
