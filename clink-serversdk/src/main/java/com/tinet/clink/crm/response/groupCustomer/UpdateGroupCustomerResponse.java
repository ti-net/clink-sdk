package com.tinet.clink.crm.response.groupCustomer;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.crm.model.GroupCustomerResultModel;

/**
 * @author yangcr
 * @description: 更新企微群客户的返回值
 * @date 2024/07/29
 */
public class UpdateGroupCustomerResponse extends ResponseModel {

    private GroupCustomerResultModel customer;

    public GroupCustomerResultModel getCustomer() {
        return customer;
    }

    public void setCustomer(GroupCustomerResultModel customer) {
        this.customer = customer;
    }
}
