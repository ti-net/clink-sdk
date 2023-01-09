package com.tinet.clink.ticket.response;

import com.tinet.clink.core.response.ResponseModel;

/**
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
public class TicketSaveResponse extends ResponseModel {

    /**
     * 工单id
     */
    private Integer ticketId;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
}