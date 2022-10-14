package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.ListTicketCategoryResponse;

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