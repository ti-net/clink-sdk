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
public class TicketMockReminderRequest extends AbstractRequestModel<TicketMockCommonResponse> {
    private Integer ticketId;
    private String customId;

    public TicketMockReminderRequest() {
        super(PathEnum.MockReminderTicket.value(), HttpMethodType.GET);
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
        if (ticketId != null) {
            putQueryParameter("ticketId", ticketId);
        }
    }

    public void setCustomId(String customId) {
        this.customId = customId;
        if (customId != null) {
            putQueryParameter("customId", customId);
        }
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