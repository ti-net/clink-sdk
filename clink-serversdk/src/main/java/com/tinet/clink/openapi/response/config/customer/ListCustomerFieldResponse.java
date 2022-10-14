package com.tinet.clink.openapi.response.config.customer;

import com.tinet.clink.openapi.model.CustomerFieldModel;
import com.tinet.clink.openapi.model.CustomerModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author lizy
 * @date 2020/01/09
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListCustomerFieldResponse extends ResponseModel {

    private List<CustomerFieldModel> customerFields;

    public List<CustomerFieldModel> getCustomerFields() {
        return customerFields;
    }

    public void setCustomerFields(List<CustomerFieldModel> customerFields) {
        this.customerFields = customerFields;
    }
}
