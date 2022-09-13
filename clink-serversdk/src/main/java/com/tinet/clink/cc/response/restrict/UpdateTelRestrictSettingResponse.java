package com.tinet.clink.cc.response.restrict;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.cc.model.TelRestrictSettingUpdateModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * @author libin
 * @date 2021-12-13 2:10 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTelRestrictSettingResponse extends ResponseModel {

    private TelRestrictSettingUpdateModel setting;

    public TelRestrictSettingUpdateModel getSetting() {
        return setting;
    }

    public void setSetting(TelRestrictSettingUpdateModel setting) {
        this.setting = setting;
    }
}
