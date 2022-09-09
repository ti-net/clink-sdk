package com.tinet.clink.crm.response.customer;

import com.tinet.clink.openapi.model.CustomerSearchResponse;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 获取客户资料查询条件响应
 *
 * @author jiangyang
 * @date 2019/11/12
 */
public class CustomerParamResponse extends ResponseModel {

    private List<CustomerSearchResponse> customerParams;

    public List<CustomerSearchResponse> getCustomerParams() {
        return customerParams;
    }

    public void setCustomerParams(List<CustomerSearchResponse> customerParams) {
        this.customerParams = customerParams;
    }
}
