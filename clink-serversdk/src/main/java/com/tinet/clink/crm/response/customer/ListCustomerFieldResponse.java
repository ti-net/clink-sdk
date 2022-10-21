package com.tinet.clink.crm.response.customer;

import com.tinet.clink.crm.model.CustomerFieldModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * @author lizy
 * @date 2020/01/09
 */
public class ListCustomerFieldResponse extends ResponseModel {

    private List<CustomerFieldModel> customerFields;

    public List<CustomerFieldModel> getCustomerFields() {
        return customerFields;
    }

    public void setCustomerFields(List<CustomerFieldModel> customerFields) {
        this.customerFields = customerFields;
    }
}
