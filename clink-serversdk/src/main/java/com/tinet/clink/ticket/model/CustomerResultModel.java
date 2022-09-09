package com.tinet.clink.ticket.model;

import java.util.Map;

/**
 * @author jiangyang
 * @date 2019/11/12
 */
public class CustomerResultModel {

    /**
     * customerResultFields代表一条客户资料的所有数据
     */
    private Map<String, Object> customerResultFields;

    public Map<String, Object> getCustomerResultFields() {
        return customerResultFields;
    }

    public void setCustomerResultFields(Map<String, Object> customerResultFields) {
        this.customerResultFields = customerResultFields;
    }
}
