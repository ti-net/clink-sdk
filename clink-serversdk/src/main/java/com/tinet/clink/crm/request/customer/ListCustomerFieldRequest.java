package com.tinet.clink.crm.request.customer;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.customer.ListCustomerFieldResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author lizy
 * @date 2020/01/09
 */
public class ListCustomerFieldRequest extends AbstractRequestModel<ListCustomerFieldResponse> {

    public ListCustomerFieldRequest() {
        super(PathEnum.ListCustomerField.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListCustomerFieldResponse> getResponseClass() {
        return ListCustomerFieldResponse.class;
    }
}
