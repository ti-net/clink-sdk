package com.tinet.clink.ticket.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.TicketSaveResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 工单保存
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
public class TicketSaveRequest extends AbstractRequestModel<TicketSaveResponse> {


    public TicketSaveRequest() {
        super(PathEnum.SaveTicket.value(), HttpMethodType.POST);
    }

    @Override
    public boolean isMultipartFormData() {
        return true;
    }

    @Override
    public Class<TicketSaveResponse> getResponseClass() {
        return TicketSaveResponse.class;
    }
}