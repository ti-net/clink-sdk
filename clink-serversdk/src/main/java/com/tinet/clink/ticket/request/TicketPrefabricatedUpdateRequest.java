package com.tinet.clink.ticket.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.TicketPrefabricatedUpdateResponse;

/**
 * @author wangli
 * @date 2022-12-26 17:52
 */
public class TicketPrefabricatedUpdateRequest extends AbstractRequestModel<TicketPrefabricatedUpdateResponse> {

    public TicketPrefabricatedUpdateRequest() {
        super(PathEnum.UpdateTicketPrefabricated.value(), HttpMethodType.POST);
    }

    @Override
    public boolean isMultipartFormData() {
        return true;
    }

    @Override
    public Class<TicketPrefabricatedUpdateResponse> getResponseClass() {
        return TicketPrefabricatedUpdateResponse.class;
    }

}
