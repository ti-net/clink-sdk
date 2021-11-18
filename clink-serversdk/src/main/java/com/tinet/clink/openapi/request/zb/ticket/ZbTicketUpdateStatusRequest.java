package com.tinet.clink.openapi.request.zb.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.zb.ticket.ZbTicketUpdateStatusResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author liuhy
 * @date: 2021/11/3
 **/
public class ZbTicketUpdateStatusRequest extends AbstractRequestModel<ZbTicketUpdateStatusResponse> {


    /**
     * 工单id
     */
    private Integer ticketId;

    /**
     * 外部ID
     */

    private String externalId;
    /**
     * 工单状态
     */
    private Integer status;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;

        if (ticketId != null) {
            putBodyParameter("ticketId", ticketId);
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;

        if (status != null) {
            putBodyParameter("status", status);
        }
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;

        if (externalId != null) {
            putBodyParameter("externalId", externalId);
        }
    }

    public ZbTicketUpdateStatusRequest() {
        super(PathEnum.ZbTicketUpdateStatus.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ZbTicketUpdateStatusResponse> getResponseClass() {
        return ZbTicketUpdateStatusResponse.class;
    }
}