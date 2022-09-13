package com.tinet.clink.crm.response.customer;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.crm.model.CustomerModel;


/**
 * @author lizy
 * @date 2020/01/09
 */
public class UpdateCustomerByExternalIdResponse extends ResponseModel {

    private CustomerModel customer;

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}
