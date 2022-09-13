package com.tinet.clink.crm.request.customer;

import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.response.customer.CustomerParamResponse;

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
