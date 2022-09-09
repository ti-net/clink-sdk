package com.tinet.clink.ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author libin
 * @date 2021-12-15 4:18 下午
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class TelRestrictSettingUpdateModel {

    private Integer settingValue;

    public Integer getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(Integer settingValue) {
        this.settingValue = settingValue;
    }
}
