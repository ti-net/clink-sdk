package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.TicketFlowResponse;

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