package com.tinet.clink.crm.response.customer;

import com.tinet.clink.core.response.ResponseModel;

/**
 * 删除客户资料响应
 *
 * @author hanjiale
 * @date 2020/11/30
 */
public class DeleteCustomerResponse extends ResponseModel {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
