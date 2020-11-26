package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.ListTicketWorkflowResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 获取工单模板列表
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
public class ListTicketWorkflowRequest extends AbstractRequestModel<ListTicketWorkflowResponse> {


    private Integer id;

    private Integer category;

    private String name;

    /**
     * 偏移量，范围 0-10000，默认值为 0
     */
    private Integer offset;

    /**
     * 查询条数，范围 10-100，默认值为 10
     */
    private Integer limit;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id", id);
        }
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
        if (category != null) {
            putQueryParameter("category", category);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (name != null) {
            putQueryParameter("name", name);
        }
    }

    public ListTicketWorkflowRequest() {
        super(PathEnum.ListTicketWorkflow.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListTicketWorkflowResponse> getResponseClass() {
        return ListTicketWorkflowResponse.class;
    }
}