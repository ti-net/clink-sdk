package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.TicketUpdateResponse;

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