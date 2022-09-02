package com.tinet.clink.ticket.request;

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
public class TicketUpdateRequest extends AbstractRequestModel<TicketUpdateResponse> {


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