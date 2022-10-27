package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.TicketSaveResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 工单保存
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  TicketSaveRequest extends AbstractRequestModel<TicketSaveResponse> {


    public TicketSaveRequest() {
        super(PathEnum.SaveTicket.value(), HttpMethodType.POST);
    }

    @Override
    public boolean isMultipartFormData() {
        return true;
    }

    @Override
    public Class<TicketSaveResponse> getResponseClass() {
        return TicketSaveResponse.class;
    }
}