package com.tinet.clink.cc.response.restrict;

import com.tinet.clink.openapi.model.TelRestrictSettingSearchModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author libin
 * @date 2021-12-13 2:05 下午
 */
public class DescribeTelRestrictSettingResponse extends ResponseModel {

    private TelRestrictSettingSearchModel setting;

    public TelRestrictSettingSearchModel getSetting() {
        return setting;
    }

    public void setSetting(TelRestrictSettingSearchModel setting) {
        this.setting = setting;
    }
}
