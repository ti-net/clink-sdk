package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.TicketCloseResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 *
 * 工单关闭
 *
 * @description
 * @Author DengJie
 * @Date 2022/04/21
 */
public class TicketCloseRequest extends AbstractRequestModel<TicketCloseResponse> {

    public TicketCloseRequest() {
        super(PathEnum.CloseTicket.value(), HttpMethodType.POST);
    }

    /**
     * 操作人
     */
    private String operator;

    /**
     * 评论内容
     */
    private String content;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取响应对象
     *
     * @return 响应对象
     */
    @Override
    public Class<TicketCloseResponse> getResponseClass() {
        return TicketCloseResponse.class;
    }
}
