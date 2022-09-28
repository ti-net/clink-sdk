package com.tinet.clink.ticket.request.stat;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.stat.StatTicketByQueueResponse;

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
