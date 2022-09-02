package com.tinet.clink.ticket.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.TicketCommentResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @description 类说明
 * @Author DengJie
 * @Date 2022/04/21
 */
public class TicketCommentRequest extends AbstractRequestModel<TicketCommentResponse> {

    public TicketCommentRequest() {
        super(PathEnum.CommentTicket.value(), HttpMethodType.POST);
    }


    @Override
    public boolean isMultipartFormData() {
        return true;
    }
    /**
     * 获取响应对象
     *
     * @return 响应对象
     */
    @Override
    public Class<TicketCommentResponse> getResponseClass() {
        return TicketCommentResponse.class;
    }
}
