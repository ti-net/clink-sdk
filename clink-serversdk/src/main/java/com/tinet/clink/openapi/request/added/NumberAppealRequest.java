package com.tinet.clink.openapi.request.added;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.added.NumberAppealResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * Class for:
 *  号码申诉
 * @author yinzk
 * @date 2021/5/26
 */
public class NumberAppealRequest extends AbstractRequestModel<NumberAppealResponse> {

    public NumberAppealRequest() {
        super(PathEnum.NumberAppeal.value(), HttpMethodType.POST);
    }

    @Override
    public boolean isMultipartFormData() {
        return true;
    }

    @Override
    public Class<NumberAppealResponse> getResponseClass() {
        return NumberAppealResponse.class;
    }
}
