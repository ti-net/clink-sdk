package com.tinet.clink.openapi.response.config.customer;

import com.tinet.clink.openapi.model.CustomerResultModel;
import com.tinet.clink.openapi.response.PagedResponse;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;
import java.util.Map;

/**
 * 客户资料查询列表结果
 *
 * @author jiangyang
 * @date 2019/11/12
 */
public class ListCustomerResponse extends PagedResponse {

    /**
     * 客户资料列表 --Map中存放每一条客户资料的字段值
     */
    private List<List<CustomerField>> customers;

    public List<List<CustomerField>> getCustomers() {
        return customers;
    }

    public void setCustomers(List<List<CustomerField>> customers) {
        this.customers = customers;
    }
}
