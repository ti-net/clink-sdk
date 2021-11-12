package com.tinet.clink.openapi.request.zb.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.zb.ticket.ZbTicketDetailResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 *
 * @author liuhy
 * @date: 2021/11/5
 **/

public class ZbTicketDetailRequest extends AbstractRequestModel<ZbTicketDetailResponse> {


    /**
     * 工单id
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id", id);
        }
    }

    public ZbTicketDetailRequest() {
        super(PathEnum.ZbTicketDetail.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ZbTicketDetailResponse> getResponseClass() {
        return ZbTicketDetailResponse.class;
    }
}