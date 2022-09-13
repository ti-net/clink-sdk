package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.TicketStoreResponse;

/**
 * 工单保存
 *
 * @author lize
 * @date: 2022/4/1
 **/
public class TicketStoreRequest extends AbstractRequestModel<TicketStoreResponse> {


    public TicketStoreRequest() {
        super(PathEnum.StoreTicket.value(), HttpMethodType.POST);
    }

    @Override
    public boolean isMultipartFormData() {
        return true;
    }

    @Override
    public Class<TicketStoreResponse> getResponseClass() {
        return TicketStoreResponse.class;
    }
}