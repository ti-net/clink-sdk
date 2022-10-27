package com.tinet.clink.openapi.response.ticket;

import com.tinet.clink.openapi.model.WorkflowCategoryModel;
import com.tinet.clink.openapi.response.ResponseModel;

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
public class  ListTicketCategoryResponse extends ResponseModel {

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