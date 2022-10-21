package com.tinet.clink.ticket.response;

import com.tinet.clink.ticket.model.TicketResultModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 *
 * @author liuhy
 * @date: 2020/8/20
 **/
public class ListTicketResponse extends PagedResponse {

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