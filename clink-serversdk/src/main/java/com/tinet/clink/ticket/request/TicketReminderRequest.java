package com.tinet.clink.ticket.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.TicketReminderResponse;

/**
 * 工单 - 催单
 *
 * @author fzq
 * @date 2023-10-12 19:20:31
 */
public class TicketReminderRequest extends AbstractRequestModel<TicketReminderResponse> {

    public TicketReminderRequest() {
        super(PathEnum.ReminderTicket.value(), HttpMethodType.POST);
    }

    @Override
    public boolean isMultipartFormData() {
        return true;
    }

    @Override
    public Class<TicketReminderResponse> getResponseClass() {
        return TicketReminderResponse.class;
    }
}
