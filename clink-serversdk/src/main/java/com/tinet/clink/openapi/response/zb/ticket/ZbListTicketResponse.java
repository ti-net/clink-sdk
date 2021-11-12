package com.tinet.clink.openapi.response.zb.ticket;

import com.tinet.clink.openapi.model.TicketResultModel;
import com.tinet.clink.openapi.model.zb.ZbTicketResultModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 *
 * @author liuhy
 * @date: 2020/8/20
 **/
public class ZbListTicketResponse extends PagedResponse {

    /**
     * 工单实体对象集合
     */
    List<ZbTicketResultModel> tickets;

    public List<ZbTicketResultModel> getTickets() {
        return tickets;
    }

    public void setTickets(List<ZbTicketResultModel> tickets) {
        this.tickets = tickets;
    }
}