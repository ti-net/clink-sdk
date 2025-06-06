package com.tinet.clink.ticket.response;

import com.tinet.clink.ticket.model.WorkflowResultModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
public class ListTicketWorkflowResponse extends PagedResponse {


    List<WorkflowResultModel> workflows;

    public List<WorkflowResultModel> getWorkflows() {
        return workflows;
    }

    public void setWorkflows(List<WorkflowResultModel> workflows) {
        this.workflows = workflows;
    }
}