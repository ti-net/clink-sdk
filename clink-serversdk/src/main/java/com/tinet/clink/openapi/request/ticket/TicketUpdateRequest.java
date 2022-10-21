package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.TicketUpdateResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 工单更新
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  TicketUpdateRequest extends AbstractRequestModel<TicketUpdateResponse> {


    public TicketUpdateRequest() {
        super(PathEnum.UpdateTicket.value(), HttpMethodType.POST);
    }

    @Override
    public boolean isMultipartFormData() {
        return true;
    }

    @Override
    public Class<TicketUpdateResponse> getResponseClass() {
        return TicketUpdateResponse.class;
    }
}