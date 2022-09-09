package com.tinet.clink.cc.request.restrict;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.tel.restrict.DescribeTelRestrictSettingResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author libin
 * @date 2021-12-13 2:05 下午
 */
public class DescribeTelRestrictSettingRequest extends AbstractRequestModel<DescribeTelRestrictSettingResponse> {


    public DescribeTelRestrictSettingRequest() {
        super(PathEnum.ListTelRestrictSetting.value(), HttpMethodType.GET);
    }

    @Override
    public Class<DescribeTelRestrictSettingResponse> getResponseClass() {
        return DescribeTelRestrictSettingResponse.class;
    }


}
