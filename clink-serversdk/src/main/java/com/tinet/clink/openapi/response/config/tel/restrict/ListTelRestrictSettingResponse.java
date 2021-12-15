package com.tinet.clink.openapi.response.config.tel.restrict;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author libin
 * @date 2021-12-13 2:05 下午
 */
public class ListTelRestrictSettingResponse extends ResponseModel {

    private Integer settingValue;

    public Integer getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(Integer settingValue) {
        this.settingValue = settingValue;
    }
}
