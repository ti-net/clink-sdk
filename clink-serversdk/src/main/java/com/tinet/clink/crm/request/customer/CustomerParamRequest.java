package com.tinet.clink.crm.request.customer;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.customer.CustomerParamResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 获取客户资料可用查询条件请求
 *
 * @author jiangyang
 * * @date 2019/11/12
 */
public class CustomerParamRequest extends AbstractRequestModel<CustomerParamResponse> {

    public CustomerParamRequest() {
        super(PathEnum.CustomerParam.value(), HttpMethodType.GET);
    }

    @Override
    public Class getResponseClass() {
        return CustomerParamResponse.class;
    }

}
