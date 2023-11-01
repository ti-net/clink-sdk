package com.tinet.clink.crm.model;

/**
 * 客户资料标签返回
 *
 * @author tian.jie
 * @date 2023-11-01 18:40
 */
public class CustomerLabelsModel {

    private String labelGroupName;

    private CustomerLabelModel[] customerLabelList;


    public String getLabelGroupName() {
        return labelGroupName;
    }

    public void setLabelGroupName(String labelGroupName) {
        this.labelGroupName = labelGroupName;
    }

    public CustomerLabelModel[] getCustomerLabelList() {
        return customerLabelList;
    }

    public void setCustomerLabelList(CustomerLabelModel[] customerLabelList) {
        this.customerLabelList = customerLabelList;
    }
}
