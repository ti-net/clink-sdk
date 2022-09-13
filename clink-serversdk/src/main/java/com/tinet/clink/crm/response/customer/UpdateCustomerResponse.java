package com.tinet.clink.crm.response.customer;

import com.tinet.clink.crm.model.CustomerModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * @author lizy
 * @date 2020/01/09
 */
public class UpdateCustomerResponse extends ResponseModel {

    private CustomerModel customer;

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}
