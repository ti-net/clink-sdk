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
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  GetBusinessResponse extends ResponseModel {

    private BusinessDetailModel businessDetail;

    public BusinessDetailModel getBusinessDetail() {
        return businessDetail;
    }

    public void setBusinessDetail(BusinessDetailModel businessDetail) {
        this.businessDetail = businessDetail;
    }
}