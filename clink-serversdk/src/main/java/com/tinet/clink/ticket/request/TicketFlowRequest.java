package com.tinet.clink.ticket.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.TicketFlowResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 工单流转
 *
 * @author lize
 * @date: 2022/4/1
 **/
public class TicketFlowRequest extends AbstractRequestModel<TicketFlowResponse> {


    public TicketFlowRequest() {
        super(PathEnum.FlowTicket.value(), HttpMethodType.POST);
    }

    @Override
    public boolean isMultipartFormData() {
        return true;
    }

    @Override
    public Class<TicketFlowResponse> getResponseClass() {
        return TicketFlowResponse.class;
    }
}