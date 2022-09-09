package com.tinet.clink.crm.response.customer;

import com.tinet.clink.openapi.model.CustomerModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author lizy
 * @date 2020/01/09
 */
public class CreateCustomerResponse  extends ResponseModel {

   private CustomerModel customer;

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}
