package com.tinet.clink.openapi.response.zb.ticket;

import com.tinet.clink.openapi.model.TicketDetailModel;
import com.tinet.clink.openapi.model.zb.ZbTicketDetailModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author liuhy
 * @date: 2021/11/5
 **/
public class ZbTicketDetailResponse extends ResponseModel {

    private ZbTicketDetailModel ticketDetail;

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZbTicketDetailModel getTicketDetail() {
        return ticketDetail;
    }

    public void setTicketDetail(ZbTicketDetailModel ticketDetail) {
        this.ticketDetail = ticketDetail;
    }
}