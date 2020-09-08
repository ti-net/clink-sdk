package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.GetTicketDetailResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author liuhy
 * @date: 2020/9/8
 **/
public class GetTicketDetailRequest extends AbstractRequestModel<GetTicketDetailResponse> {


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

    public GetTicketDetailRequest() {
        super(PathEnum.GetTicketDetail.value(), HttpMethodType.GET);
    }

    @Override
    public Class<GetTicketDetailResponse> getResponseClass() {
        return null;
    }
}