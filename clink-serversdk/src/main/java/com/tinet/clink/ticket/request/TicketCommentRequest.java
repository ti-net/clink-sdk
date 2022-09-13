package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.TicketCommentResponse;

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
