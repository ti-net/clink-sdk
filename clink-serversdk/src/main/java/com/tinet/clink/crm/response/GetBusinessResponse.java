package com.tinet.clink.crm.response;

import com.tinet.clink.crm.model.BusinessDetailModel;
import com.tinet.clink.core.response.ResponseModel;

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