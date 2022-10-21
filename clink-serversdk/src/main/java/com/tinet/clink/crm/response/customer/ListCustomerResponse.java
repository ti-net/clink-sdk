package com.tinet.clink.crm.response.customer;

import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

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
