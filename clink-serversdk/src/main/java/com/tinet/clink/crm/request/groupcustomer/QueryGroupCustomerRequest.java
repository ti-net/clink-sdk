package com.tinet.clink.crm.request.groupcustomer;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.crm.response.groupCustomer.QueryGroupCustomerResponse;

/**
 * @author yangcr
 * @description: 查询企微群客户请求
 * @date 2024/07/30
 */
public class QueryGroupCustomerRequest extends AbstractRequestModel<QueryGroupCustomerResponse> {

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 访客标识
     */
    private String visitorId;

    @Override
    public Class<QueryGroupCustomerResponse> getResponseClass() {
        return QueryGroupCustomerResponse.class;
    }

    public QueryGroupCustomerRequest() {
        super(PathEnum.QueryGroupCustomer.value(), HttpMethodType.POST);
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }
}
