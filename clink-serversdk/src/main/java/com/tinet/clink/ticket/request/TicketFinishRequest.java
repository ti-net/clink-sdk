package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.UserIdType;
import com.tinet.clink.ticket.response.TicketFinishResponse;

/**
 * 工单 完成
 *
 * @Author DengJie
 * @Date 2022/04/21
 */
public class TicketFinishRequest extends AbstractRequestModel<TicketFinishResponse> {

    public TicketFinishRequest() {
        super(PathEnum.FinishTicket.value(), HttpMethodType.POST);
    }

    /**
     * 操作人id
     */
    private Integer operatorId;

    private Integer operatorIdType = UserIdType.USER_ID.getCode();

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

    public Integer getOperatorIdType() {
        return operatorIdType;
    }

    public void setOperatorIdType(Integer operatorIdType) {
        this.operatorIdType = operatorIdType;
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
