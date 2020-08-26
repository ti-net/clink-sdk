package com.tinet.clink.openapi.response.ticket;/**
 * @author liuhy
 * @date: 2020/8/20
 **/

import com.tinet.clink.openapi.model.TicketResultModel;
import com.tinet.clink.openapi.response.PagedResponse;

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