package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author libin
 * @date 2021-12-16 10:07 上午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelRestrictSettingSearchModel {

    private Integer settingValue;

    public Integer getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(Integer settingValue) {
        this.settingValue = settingValue;
    }
}
