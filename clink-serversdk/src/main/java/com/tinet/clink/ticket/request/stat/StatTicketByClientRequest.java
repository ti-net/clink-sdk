package com.tinet.clink.ticket.request.stat;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.stat.StatTicketByClientResponse;

import java.util.Objects;

/**
 * 座席情况统计报表请求
 *
 * @author wangli
 * @date 2022-08-24 11:03 上午
 */
public class StatTicketByClientRequest extends AbstractStatRequest<StatTicketByClientResponse> {

    /**
     * 需要统计的座席号
     */
    private String[] cnos;

    public String[] getCnos() {
        return cnos;
    }

    public void setCnos(String[] cnos) {
        this.cnos = cnos;
        if (Objects.nonNull(cnos)) {
            putBodyParameter("cnos", cnos);
        }
    }

    @Override
    public Class<StatTicketByClientResponse> getResponseClass() {
        return StatTicketByClientResponse.class;
    }

    public StatTicketByClientRequest() {
        super(PathEnum.StatTicketByClient.value(), HttpMethodType.POST);
    }

}
