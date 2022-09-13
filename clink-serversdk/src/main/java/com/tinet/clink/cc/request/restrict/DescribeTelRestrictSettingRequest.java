package com.tinet.clink.cc.request.restrict;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.restrict.DescribeTelRestrictSettingResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

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
