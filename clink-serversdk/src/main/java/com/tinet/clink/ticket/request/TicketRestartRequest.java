package com.tinet.clink.ticket.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.TicketRestartResponse;

/**
 * 工单 - 重启请求
 *
 * @author fzq
 * @date 2023-10-12 19:25:42
 */
public class TicketRestartRequest extends AbstractRequestModel<TicketRestartResponse> {

    /**
     * 工单ID
     */
    private Integer ticketId;

    /**
     * 自定义工单编号 (id和customId 二者必有一个)
     */
    private String customId;

    /**
     * 处理人Id
     */
    private Integer handlerId;

    /**
     * 处理人工号
     */
    private String handlerCno;

    /**
     * 处理人名
     */
    private String handlerUserName;

    /**
     * 描述
     */
    private String content;

    /**
     * 重启后处理人Id
     */
    private Integer afterRestartHandlerId;

    /**
     * 重启后处理人工号
     */
    private String afterRestartHandlerCno;

    /**
     * 重启后处理人名
     */
    private String afterRestartHandlerUserName;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
        if (ticketId != null) {
            putQueryParameter("ticketId", ticketId);
        }
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
        if (customId != null) {
            putQueryParameter("customId", customId);
        }
    }

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
        if (handlerId != null) {
            putQueryParameter("handlerId", handlerId);
        }
    }

    public String getHandlerCno() {
        return handlerCno;
    }

    public void setHandlerCno(String handlerCno) {
        this.handlerCno = handlerCno;
        if (handlerCno != null) {
            putQueryParameter("handlerCno", handlerCno);
        }
    }

    public String getHandlerUserName() {
        return handlerUserName;
    }

    public void setHandlerUserName(String handlerUserName) {
        this.handlerUserName = handlerUserName;
        if (handlerUserName != null) {
            putQueryParameter("handlerUserName", handlerUserName);
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        if (content != null) {
            putQueryParameter("content", content);
        }
    }

    public Integer getAfterRestartHandlerId() {
        return afterRestartHandlerId;
    }

    public void setAfterRestartHandlerId(Integer afterRestartHandlerId) {
        this.afterRestartHandlerId = afterRestartHandlerId;
        if (afterRestartHandlerId != null) {
            putQueryParameter("afterRestartHandlerId", afterRestartHandlerId);
        }
    }

    public String getAfterRestartHandlerCno() {
        return afterRestartHandlerCno;
    }

    public void setAfterRestartHandlerCno(String afterRestartHandlerCno) {
        this.afterRestartHandlerCno = afterRestartHandlerCno;
        if (afterRestartHandlerCno != null) {
            putQueryParameter("afterRestartHandlerCno", afterRestartHandlerCno);
        }
    }

    public String getAfterRestartHandlerUserName() {
        return afterRestartHandlerUserName;
    }

    public void setAfterRestartHandlerUserName(String afterRestartHandlerUserName) {
        this.afterRestartHandlerUserName = afterRestartHandlerUserName;
        if (afterRestartHandlerUserName != null) {
            putQueryParameter("afterRestartHandlerUserName", afterRestartHandlerUserName);
        }
    }

    public TicketRestartRequest() {
        super(PathEnum.RestartTicket.value(), HttpMethodType.POST);
    }

    @Override
    public Class<TicketRestartResponse> getResponseClass() {
        return TicketRestartResponse.class;
    }
}
