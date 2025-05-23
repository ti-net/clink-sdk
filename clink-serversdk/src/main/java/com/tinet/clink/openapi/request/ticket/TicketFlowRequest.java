package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.TicketFlowResponse;
import com.tinet.clink.openapi.response.ticket.TicketSaveResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 工单流转
 *
 * @author lize
 * @date: 2022/4/1
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  TicketFlowRequest extends AbstractRequestModel<TicketFlowResponse> {


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