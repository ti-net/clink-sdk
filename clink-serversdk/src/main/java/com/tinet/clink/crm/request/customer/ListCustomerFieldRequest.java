package com.tinet.clink.crm.request.customer;

import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.response.customer.ListCustomerFieldResponse;

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
