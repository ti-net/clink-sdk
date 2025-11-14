package com.tinet.clink.ticket.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.TicketPreserveResponse;
import com.tinet.clink.ticket.response.TicketStoreResponse;

/**
 * 工单保存
 *
 * @author lize
 * @date: 2022/4/1
 **/
public class TicketPreserveRequest extends AbstractRequestModel<TicketPreserveResponse> {


    public TicketPreserveRequest() {
        super(PathEnum.PreserveTicket.value(), HttpMethodType.POST);
    }

    @Override
    public boolean isMultipartFormData() {
        return true;
    }

    @Override
    public Class<TicketPreserveResponse> getResponseClass() {
        return TicketPreserveResponse.class;
    }
}