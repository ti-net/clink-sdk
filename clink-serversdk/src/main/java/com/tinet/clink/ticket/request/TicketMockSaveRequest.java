package com.tinet.clink.ticket.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.TicketMockCommonResponse;
import com.tinet.clink.ticket.response.TicketSaveResponse;

/**
 * 工单保存
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
public class TicketMockSaveRequest extends AbstractRequestModel<TicketMockCommonResponse> {
    private String workflowName;

    public TicketMockSaveRequest() {
        super(PathEnum.MockSaveTicket.value(), HttpMethodType.GET);
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
        if (workflowName != null) {
            putQueryParameter("workflowName", workflowName);
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