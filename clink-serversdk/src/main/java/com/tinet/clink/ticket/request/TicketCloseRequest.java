package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.TicketCloseResponse;

/**
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
     * 操作人id
     */
    private Integer operatorId;

    /**
     * 评论内容
     */
    private String content;


    /**
     * 工单id
     */
    private Integer ticketId;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
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
