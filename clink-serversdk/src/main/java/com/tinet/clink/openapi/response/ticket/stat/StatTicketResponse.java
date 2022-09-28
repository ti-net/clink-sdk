package com.tinet.clink.openapi.response.ticket.stat;

import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * 工单整体统计报表响应
 *
 * @author wangli
 * @date 2022-08-24 10:47 上午
 */
public class StatTicketResponse extends PagedResponse {

    private List<Map<String,Object>> statTicket;

    public List<Map<String, Object>> getStatTicket() {
        return statTicket;
    }

    public void setStatTicket(List<Map<String, Object>> statTicket) {
        this.statTicket = statTicket;
    }

}
