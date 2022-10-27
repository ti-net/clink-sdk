package com.tinet.clink.openapi.response.config.customer;

import com.tinet.clink.openapi.model.CustomerSearchResponse;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 获取客户资料查询条件响应
 *
 * @author jiangyang
 * @date 2019/11/12
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CustomerParamResponse extends ResponseModel {

    private List<CustomerSearchResponse> customerParams;

    public List<CustomerSearchResponse> getCustomerParams() {
        return customerParams;
    }

    public void setCustomerParams(List<CustomerSearchResponse> customerParams) {
        this.customerParams = customerParams;
    }
}
