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

    private Integer formId;
    private String taskId;
    private Integer id;
    private String externalId;
    private String customId;

    public TicketMockUpdateRequest() {
        super(PathEnum.MockUpdateTicket.value(), HttpMethodType.GET);
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
        if (formId != null) {
            putQueryParameter("formId", formId);
        }
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
        if (taskId != null) {
            putQueryParameter("taskId", taskId);
        }
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id", id);
        }
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
        if (externalId != null) {
            putQueryParameter("externalId", externalId);
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