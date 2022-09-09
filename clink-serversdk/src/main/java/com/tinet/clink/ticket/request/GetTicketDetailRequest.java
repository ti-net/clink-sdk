package com.tinet.clink.ticket.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.GetTicketDetailResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 获取工单详情
 *
 * @author liuhy
 * @date: 2020/9/8
 **/
public class GetTicketDetailRequest extends AbstractRequestModel<GetTicketDetailResponse> {


    /**
     * 工单id
     */
    private Integer id;

    /**
     * 外部工单id
     */
    private String externalId;

    public String getExternalId() { return externalId; }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
        if (externalId != null) {
            putQueryParameter("externalId", externalId);
        }
    }

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
        return GetTicketDetailResponse.class;
    }
}