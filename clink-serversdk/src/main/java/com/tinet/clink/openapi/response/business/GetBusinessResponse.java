package com.tinet.clink.openapi.response.business;

import com.tinet.clink.openapi.model.BusinessDetailModel;
import com.tinet.clink.openapi.response.PagedResponse;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 业务记录详情
 *
 * @author liuhy
 * @date: 2020/12/28
 **/
public class GetBusinessResponse extends ResponseModel {

    private BusinessDetailModel businessDetail;

    public BusinessDetailModel getBusinessDetail() {
        return businessDetail;
    }

    public void setBusinessDetail(BusinessDetailModel businessDetail) {
        this.businessDetail = businessDetail;
    }
}