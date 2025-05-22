package com.tinet.clink.ticket.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.StagingTicketResponse;

/**
 * @author wangli
 * @date 2024-03-08 11:52
 */
public class StagingTicketRequest extends AbstractRequestModel<StagingTicketResponse> {

    public StagingTicketRequest() {
        super(PathEnum.StagingTicket.value(), HttpMethodType.POST);
    }

    @Override
    public boolean isMultipartFormData() {
        return true;
    }

    @Override
    public Class<StagingTicketResponse> getResponseClass() {
        return StagingTicketResponse.class;
    }

}
