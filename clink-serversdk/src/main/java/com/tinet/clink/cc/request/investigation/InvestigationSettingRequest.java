package com.tinet.clink.cc.request.investigation;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.investigation.InvestigationSettingResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author: wangpw
 * @date: 2022/3/28
 * @description:
 */
public class InvestigationSettingRequest extends AbstractRequestModel<InvestigationSettingResponse> {


    public InvestigationSettingRequest() {
        super(PathEnum.investigationSetting.value(), HttpMethodType.GET);
    }

    @Override
    public Class<InvestigationSettingResponse> getResponseClass() {
        return InvestigationSettingResponse.class;
    }
}
