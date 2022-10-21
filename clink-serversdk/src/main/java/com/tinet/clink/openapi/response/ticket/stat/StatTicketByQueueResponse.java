package com.tinet.clink.openapi.response.ticket.stat;

import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * 座席组情况统计报表响应
 *
 * @author wangli
 * @date 2022-08-24 11:10 上午
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  StatTicketByQueueResponse extends PagedResponse {

    private List<Map<String,Object>> statTicketByQueue;

    public List<Map<String, Object>> getStatTicketByQueue() {
        return statTicketByQueue;
    }

    public void setStatTicketByQueue(List<Map<String, Object>> statTicketByQueue) {
        this.statTicketByQueue = statTicketByQueue;
    }

}
