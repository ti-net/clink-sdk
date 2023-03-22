package com.tinet.clink.crm.request.customer;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.crm.response.customer.QueryCustomerResponse;

/**
 * 查询客户资料请求
 *
 * @author gexd
 * @date 2023/03/08
 */
public class QueryCustomerRequest extends AbstractRequestModel<QueryCustomerResponse> {

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 手机号
     */
    private String tel;

    @Override
    public Class<QueryCustomerResponse> getResponseClass() {
        return QueryCustomerResponse.class;
    }

    public QueryCustomerRequest() {
        super(PathEnum.QueryCustomer.value(), HttpMethodType.POST);
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
