package com.tinet.clink.openapi.response.ticket;

import com.tinet.clink.openapi.model.TicketDetailModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author liuhy
 * @date: 2020/9/8
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  GetTicketDetailResponse extends ResponseModel {


    private TicketDetailModel ticketDetail;

    public TicketDetailModel getTicketDetail() {
        return ticketDetail;
    }

    public void setTicketDetail(TicketDetailModel ticketDetail) {
        this.ticketDetail = ticketDetail;
    }
}