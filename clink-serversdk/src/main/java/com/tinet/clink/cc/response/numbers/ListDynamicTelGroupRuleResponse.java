package com.tinet.clink.cc.response.numbers;


import com.tinet.clink.cc.model.DynamicTelGroupRuleModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * @author libin
 * @date 2023-06-29 14:34
 */
public class ListDynamicTelGroupRuleResponse extends ResponseModel {
        private List<DynamicTelGroupRuleModel> data;

    public List<DynamicTelGroupRuleModel> getData() {
        return data;
    }

    public void setData(List<DynamicTelGroupRuleModel> data) {
        this.data = data;
    }
}
