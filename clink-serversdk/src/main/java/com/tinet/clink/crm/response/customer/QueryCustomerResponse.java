package com.tinet.clink.crm.response.customer;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.crm.model.CustomerResultModel;


/**
 * 查询客户资料请求结果
 *
 * @author gexd
 * @date 2023/03/08
 */
public class QueryCustomerResponse extends ResponseModel {

    private CustomerResultModel customer;

    public CustomerResultModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResultModel customer) {
        this.customer = customer;
    }
}
