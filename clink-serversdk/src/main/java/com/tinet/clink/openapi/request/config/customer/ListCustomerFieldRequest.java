package com.tinet.clink.openapi.request.config.customer;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.customer.ListCustomerFieldResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author lizy
 * @date 2020/01/09
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListCustomerFieldRequest extends AbstractRequestModel<ListCustomerFieldResponse> {

    public ListCustomerFieldRequest() {
        super(PathEnum.ListCustomerField.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListCustomerFieldResponse> getResponseClass() {
        return ListCustomerFieldResponse.class;
    }
}
