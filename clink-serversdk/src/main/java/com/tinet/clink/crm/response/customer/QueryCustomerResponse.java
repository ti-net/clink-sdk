package com.tinet.clink.crm.response.customer;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.crm.model.QueryCustomerModel;


/**
 * 获取客户资料请求结果
 *
 * @author gexd
 * @date 2023/03/08
 */
public class QueryCustomerResponse extends ResponseModel {

    /**
     * 客户资料列表 --Map中存放每一条客户资料的字段值
     */
    private QueryCustomerModel customer;

    public QueryCustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(QueryCustomerModel customer) {
        this.customer = customer;
    }
}
