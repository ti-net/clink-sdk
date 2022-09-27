package com.tinet.clink.openapi.request.ticket;

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
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListTicketCategoryRequest extends AbstractRequestModel<ListTicketCategoryResponse> {

    public ListTicketCategoryRequest() {
        super(PathEnum.ListTicketCategory.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListTicketCategoryResponse> getResponseClass() {
        return ListTicketCategoryResponse.class;
    }
}