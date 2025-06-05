package com.tinet.clink.ticket.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.TicketMockCommonResponse;

/**
 * 工单保存
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
public class TicketMockUpdateRequest extends AbstractRequestModel<TicketMockCommonResponse> {


    public TicketMockUpdateRequest() {
        super(PathEnum.MockUpdateTicket.value(), HttpMethodType.GET);
    }

    @Override
    public boolean isMultipartFormData() {
        return false;
    }

    @Override
    public Class<TicketMockCommonResponse> getResponseClass() {
        return TicketMockCommonResponse.class;
    }
}