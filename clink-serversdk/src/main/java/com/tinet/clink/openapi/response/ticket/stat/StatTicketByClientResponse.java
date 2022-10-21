package com.tinet.clink.openapi.response.ticket.stat;

import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * 座席情况统计报表响应
 *
 * @author wangli
 * @date 2022-08-24 11:06 上午
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  StatTicketByClientResponse extends PagedResponse {

    private List<Map<String,Object>> statTicketByClient;

    public List<Map<String, Object>> getStatTicketByClient() {
        return statTicketByClient;
    }

    public void setStatTicketByClient(List<Map<String, Object>> statTicketByClient) {
        this.statTicketByClient = statTicketByClient;
    }

}
