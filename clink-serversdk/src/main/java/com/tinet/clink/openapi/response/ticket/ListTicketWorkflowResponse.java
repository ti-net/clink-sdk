package com.tinet.clink.openapi.response.ticket;

import com.tinet.clink.openapi.model.WorkflowResultModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  ListTicketWorkflowResponse extends PagedResponse {


    List<WorkflowResultModel> workflows;

    public List<WorkflowResultModel> getWorkflows() {
        return workflows;
    }

    public void setWorkflows(List<WorkflowResultModel> workflows) {
        this.workflows = workflows;
    }
}