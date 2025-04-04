package com.tinet.clink.crm.response.customer;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.crm.model.UpdateRelevancyResult;

/**
 * @author yangcr
 * @description: 调用更新客户关联后的返回
 * @date 2024/6/21 14:05
 */
public class UpdateCustomerRelevancyResponse extends ResponseModel {

   private UpdateRelevancyResult result;

    public UpdateRelevancyResult getResult() {
        return result;
    }

    public void setResult(UpdateRelevancyResult result) {
        this.result = result;
    }
}
