package com.tinet.clink.ticket.response.stat;

import com.tinet.clink.core.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * 座席组情况统计报表响应
 *
 * @author wangli
 * @date 2022-08-24 11:10 上午
 */
public class StatTicketByQueueResponse extends PagedResponse {

    private List<Map<String,Object>> statTicketByQueue;

    public List<Map<String, Object>> getStatTicketByQueue() {
        return statTicketByQueue;
    }

    public void setStatTicketByQueue(List<Map<String, Object>> statTicketByQueue) {
        this.statTicketByQueue = statTicketByQueue;
    }

}
