package com.tinet.clink.openapi.request.config.tel.restrict;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.tel.restrict.ListTelRestrictSettingResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author libin
 * @date 2021-12-13 2:05 下午
 */
public class ListTelRestrictSettingRequest extends AbstractRequestModel<ListTelRestrictSettingResponse> {


    public ListTelRestrictSettingRequest() {
        super(PathEnum.ListTelRestrictSetting.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListTelRestrictSettingResponse> getResponseClass() {
        return ListTelRestrictSettingResponse.class;
    }


}
