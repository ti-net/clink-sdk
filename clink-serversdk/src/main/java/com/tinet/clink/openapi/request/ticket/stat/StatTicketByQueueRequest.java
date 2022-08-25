package com.tinet.clink.openapi.request.ticket.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.stat.AbstractStatRequest;
import com.tinet.clink.openapi.response.ticket.stat.StatTicketByQueueResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * 座席组情况统计报表请求
 *
 * @author wangli
 * @date 2022-08-24 11:07 上午
 */
public class StatTicketByQueueRequest extends AbstractStatRequest<StatTicketByQueueResponse> {

    /**
     * 需要统计的座席号
     */
    private String[] qnos;

    public String[] getQnos() {
        return qnos;
    }

    public void setQnos(String[] qnos) {
        this.qnos = qnos;
        if (Objects.nonNull(qnos)) {
            putBodyParameter("qnos", qnos);
        }
    }

    @Override
    public Class<StatTicketByQueueResponse> getResponseClass() {
        return StatTicketByQueueResponse.class;
    }

    public StatTicketByQueueRequest() {
        super(PathEnum.StatTicketByQueue.value(), HttpMethodType.POST);
    }

}
