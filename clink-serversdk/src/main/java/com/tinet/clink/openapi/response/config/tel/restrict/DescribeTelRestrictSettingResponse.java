package com.tinet.clink.openapi.response.config.tel.restrict;

import com.tinet.clink.openapi.model.TelRestrictSetting;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author libin
 * @date 2021-12-13 2:05 下午
 */
public class DescribeTelRestrictSettingResponse extends ResponseModel {

    private TelRestrictSetting setting;

    public TelRestrictSetting getSetting() {
        return setting;
    }

    public void setSetting(TelRestrictSetting setting) {
        this.setting = setting;
    }
}
