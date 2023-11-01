package com.tinet.clink.crm.response.customer;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.crm.model.CustomerLabelsModel;

/**
 * 客户资料可用标签响应
 *
 * @author tian.jie
 * @date 2023-11-01 18:39
 */
public class ListCustomerLabelsResponse extends ResponseModel {

   private CustomerLabelsModel[] customerLabels;

   public CustomerLabelsModel[] getCustomerLabels() {
      return customerLabels;
   }

   public void setCustomerLabels(CustomerLabelsModel[] customerLabels) {
      this.customerLabels = customerLabels;
   }
}
