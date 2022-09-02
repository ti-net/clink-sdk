package com.tinet.clink.ticket.response;

import com.tinet.clink.openapi.model.WorkflowCategoryModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
public class ListTicketCategoryResponse extends ResponseModel {

    /**
     * 工单实体对象集合
     */
    List<WorkflowCategoryModel> categoryModels;

    public List<WorkflowCategoryModel> getCategoryModels() {
        return categoryModels;
    }

    public void setCategoryModels(List<WorkflowCategoryModel> categoryModels) {
        this.categoryModels = categoryModels;
    }
}