package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.TicketSaveResponse;

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