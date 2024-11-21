package com.tinet.clink.ticket.response;

import com.tinet.clink.core.response.ResponseModel;

/**
 * @author wangli
 * @date 2024-03-08 11:53
 */
public class StagingTicketResponse extends ResponseModel {

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
