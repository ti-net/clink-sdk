package com.tinet.clink.ticket.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.ListTicketCategoryResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 获取工单模板类别
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
public class ListTicketCategoryRequest extends AbstractRequestModel<ListTicketCategoryResponse> {

    public ListTicketCategoryRequest() {
        super(PathEnum.ListTicketCategory.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListTicketCategoryResponse> getResponseClass() {
        return ListTicketCategoryResponse.class;
    }
}