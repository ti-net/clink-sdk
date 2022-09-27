package com.tinet.clink.openapi.request.config.tel.restrict;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.tel.restrict.UpdateTelRestrictSettingResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * @author libin
 * @date 2021-12-13 2:12 下午
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  UpdateTelRestrictSettingRequest extends AbstractRequestModel<UpdateTelRestrictSettingResponse> {

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
