package com.tinet.clink.cc.request.investigation;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.investigation.InvestigationSettingResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

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
