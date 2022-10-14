package com.tinet.clink.openapi.response.ticket;

import com.tinet.clink.openapi.model.TicketResultModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 *
 * @author liuhy
 * @date: 2020/8/20
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  ListTicketResponse extends PagedResponse {

    /**
     * 工单实体对象集合
     */
    List<TicketResultModel> tickets;

    public List<TicketResultModel> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResultModel> tickets) {
        this.tickets = tickets;
    }
}