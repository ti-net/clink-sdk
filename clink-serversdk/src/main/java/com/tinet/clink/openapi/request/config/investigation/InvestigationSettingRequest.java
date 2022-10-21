package com.tinet.clink.openapi.request.config.investigation;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.investigation.InvestigationSettingResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author: wangpw
 * @date: 2022/3/28
 * @description:
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  InvestigationSettingRequest extends AbstractRequestModel<InvestigationSettingResponse> {


    public InvestigationSettingRequest() {
        super(PathEnum.investigationSetting.value(), HttpMethodType.GET);
    }

    @Override
    public Class<InvestigationSettingResponse> getResponseClass() {
        return InvestigationSettingResponse.class;
    }
}
