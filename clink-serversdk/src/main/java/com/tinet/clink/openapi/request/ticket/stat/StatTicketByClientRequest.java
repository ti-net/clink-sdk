package com.tinet.clink.openapi.request.ticket.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.stat.AbstractStatRequest;
import com.tinet.clink.openapi.response.ticket.stat.StatTicketByClientResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * 座席情况统计报表请求
 *
 * @author wangli
 * @date 2022-08-24 11:03 上午
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  StatTicketByClientRequest extends AbstractStatRequest<StatTicketByClientResponse> {

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
