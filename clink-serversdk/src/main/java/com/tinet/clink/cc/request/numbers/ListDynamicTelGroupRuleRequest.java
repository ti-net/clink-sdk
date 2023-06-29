package com.tinet.clink.cc.request.numbers;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.numbers.ListDynamicTelGroupRuleResponse;
import com.tinet.clink.core.request.AbstractRequestModel;

/**
 * @author libin
 * @date 2023-06-29 14:33
 */
public class ListDynamicTelGroupRuleRequest extends AbstractRequestModel<ListDynamicTelGroupRuleResponse> {

    public ListDynamicTelGroupRuleRequest() {
        super(PathEnum.ListDynamicTelGroupRule.value());
    }

    @Override
    public Class<ListDynamicTelGroupRuleResponse> getResponseClass() {
        return ListDynamicTelGroupRuleResponse.class;
    }
}
