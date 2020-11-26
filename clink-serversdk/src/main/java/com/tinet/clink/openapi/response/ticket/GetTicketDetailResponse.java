package com.tinet.clink.openapi.response.ticket;

import com.tinet.clink.openapi.model.TicketDetailModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author liuhy
 * @date: 2020/9/8
 **/
public class GetTicketDetailResponse extends ResponseModel {


    private TicketDetailModel ticketDetail;

    public TicketDetailModel getTicketDetail() {
        return ticketDetail;
    }

    public void setTicketDetail(TicketDetailModel ticketDetail) {
        this.ticketDetail = ticketDetail;
    }
}