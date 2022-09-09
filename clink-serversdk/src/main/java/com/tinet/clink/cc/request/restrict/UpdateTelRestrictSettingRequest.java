package com.tinet.clink.cc.request.restrict;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.tel.restrict.UpdateTelRestrictSettingResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * @author libin
 * @date 2021-12-13 2:12 下午
 */
public class UpdateTelRestrictSettingRequest extends AbstractRequestModel<UpdateTelRestrictSettingResponse> {

    private Integer settingValue;

    public UpdateTelRestrictSettingRequest() {
        super(PathEnum.UpdateTelRestrictSetting.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateTelRestrictSettingResponse> getResponseClass() {
        return UpdateTelRestrictSettingResponse.class;
    }

    public void setSettingValue(Integer settingValue) {
        this.settingValue = settingValue;
        if (Objects.nonNull(settingValue)) {
            putQueryParameter("settingValue", settingValue);
        }
    }


    public Integer getSettingValue() {
        return settingValue;
    }
}
