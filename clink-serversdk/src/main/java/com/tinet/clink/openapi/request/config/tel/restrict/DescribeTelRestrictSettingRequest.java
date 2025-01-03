package com.tinet.clink.openapi.request.config.tel.restrict;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.tel.restrict.DescribeTelRestrictSettingResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author libin
 * @date 2021-12-13 2:05 下午
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DescribeTelRestrictSettingRequest extends AbstractRequestModel<DescribeTelRestrictSettingResponse> {


    public DescribeTelRestrictSettingRequest() {
        super(PathEnum.ListTelRestrictSetting.value(), HttpMethodType.GET);
    }

    @Override
    public Class<DescribeTelRestrictSettingResponse> getResponseClass() {
        return DescribeTelRestrictSettingResponse.class;
    }


}
