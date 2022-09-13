package com.tinet.clink.crm.request;

import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.response.GetBusinessResponse;

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