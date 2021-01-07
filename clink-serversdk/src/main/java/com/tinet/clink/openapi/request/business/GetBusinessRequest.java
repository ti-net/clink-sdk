package com.tinet.clink.openapi.request.business;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.business.GetBusinessResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 业务记录详情
 *
 * @author liuhy
 * @date: 2020/12/28
 **/
public class GetBusinessRequest extends AbstractRequestModel<GetBusinessResponse> {


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

    public GetBusinessRequest() {
        super(PathEnum.GetBusinessDetail.value(), HttpMethodType.GET);
    }

    @Override
    public Class<GetBusinessResponse> getResponseClass() {
        return GetBusinessResponse.class;
    }


}