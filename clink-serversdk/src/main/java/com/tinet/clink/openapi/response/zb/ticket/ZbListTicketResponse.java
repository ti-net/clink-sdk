package com.tinet.clink.openapi.response.zb.ticket;

import com.tinet.clink.openapi.model.TicketResultModel;
import com.tinet.clink.openapi.model.zb.ZbTicketResultModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * @author liuhy
 * @date: 2020/8/20
 **/
public class ZbListTicketResponse extends PagedResponse {

    /**
     * 工单实体对象集合
     */
    private List<ZbTicketResultModel> tickets;

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

    public List<ZbTicketResultModel> getTickets() {
        return tickets;
    }

    public void setTickets(List<ZbTicketResultModel> tickets) {
        this.tickets = tickets;
    }
}