package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.TicketFinishResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 工单 完成
 * @Author DengJie
 * @Date 2022/04/21
 */
public class TicketFinishRequest extends AbstractRequestModel<TicketFinishResponse> {

    public TicketFinishRequest() {
        super(PathEnum.FinishTicket.value(), HttpMethodType.POST);
    }

    /**
     * 评论人/操作人
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
    public Class<TicketFinishResponse> getResponseClass() {
        return TicketFinishResponse.class;
    }
}
